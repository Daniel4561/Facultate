#include <advanlys.h>
#include <utility.h>
#include <formatio.h>
#include <ansi_c.h>
#include <cvirte.h>		
#include <userint.h>
#include "toolbox.h"
#include "proiect.h"

#define SAMPLE_RATE		0
#define NPOINTS			1
#define Rectangle 1
#define Blackman  2

// Global variables
int waveInfo[2]; //waveInfo[0] = sampleRate
				 //waveInfo[1] = number of elements
int sampleRate = 0;
int npoints = 0;
double *waveData = 0;
double *filtredData = 0;
double *derivata = 0;
double *ferestruire = 0;
double *savitsky = 0;
double *vector = 0;
double *spectru_savitsky = 0;
double *spectru_semnal = 0;
double * spectru_notch = 0;
double * norch = 0;
double * norch1 = 0;

double *sec_semnal = 0;
double *sec_savitzky = 0;
double *sec_notch = 0;

int panelHandle = 0;
int acqPanel = 0;

int powerSwitch = 0;

int loaded3 =0;

double min = 0;
double max = 0;
double medie = 0;
int minInd = 0;
int maxInd = 0;
double stDev = 0;
double mediana = 0;
int zeroCrossing = 0;

int loaded = 0;

int start = 0;
int stop = 1;
int derivateON = 0;
int next_prev = 0;
int filterAloc = 0;

int onAnvelope = 0;

double* anvelope = 0;
int plot = 0;

int windType = 1;

int start2 = 0;
int stop2 = 1;

int N = 2048;
int loaded2 = 0;
double df = 0;
double df1 =0;
double df3 = 0;

int start3 =0;
int stop3 =1;

WindowConst windowConst;

int main (int argc, char *argv[])
{
	int error = 0;
	
	/* initialize and load resources */
	nullChk (InitCVIRTE (0, argv, 0));
	errChk (panelHandle = LoadPanel (0, "proiect.uir", PANEL));
	errChk (acqPanel = LoadPanel (0, "proiect.uir", PANEL_2));
	
	/* display the panel and run the user interface */
	errChk (DisplayPanel (panelHandle));
	errChk (RunUserInterface ());

Error:
	/* clean up */
	if (panelHandle > 0)
		DiscardPanel (panelHandle);
	return 0;
}

int CVICALLBACK PanelFunction (int panel, int event, void *callbackData,
							   int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_GOT_FOCUS:

			break;
		case EVENT_LOST_FOCUS:

			break;
		case EVENT_CLOSE:
				QuitUserInterface(0);
			break;
	}
	return 0;
}


int CVICALLBACK PowerSwith (int panel, int control, int event,
							void *callbackData, int eventData1, int eventData2)
{
	int numeric1[] = {PANEL_NUMERIC_MINIM, PANEL_NUMERIC_MAXIM, PANEL_NUMERIC_MEDIE, PANEL_NUMERIC_MEDIANA, PANEL_NUMERIC_DISPERSIE };
	int numeric2[] = {PANEL_NUMERIC_INDEX_MINIM, PANEL_NUMERIC_INDEX_MAXIM, PANEL_NUMERIC_ZERO_CROSSING};
	switch (event)
	{
		case EVENT_COMMIT:
				GetCtrlVal (panelHandle, PANEL_BINARYSWITCH, &powerSwitch);
				
				SetCtrlVal(panelHandle, PANEL_LED, powerSwitch);
				for(int i = 4;i<16;++i)
				{	

					SetCtrlAttribute(panelHandle, i, ATTR_DIMMED, !powerSwitch);
				}
				
				loaded = !powerSwitch;
				DeleteGraphPlot(panelHandle, PANEL_GRAPH_WAVE, -1, VAL_IMMEDIATE_DRAW);
				DeleteGraphPlot(panelHandle, PANEL_GRAPH_HISTOGRAM, -1, VAL_IMMEDIATE_DRAW);
				DeleteGraphPlot(panelHandle, PANEL_GRAPH_FILTRED_WAVE, -1, VAL_IMMEDIATE_DRAW);
				
				for(int i=0; i<5;i++)
				{
					SetCtrlVal(panelHandle, numeric1[i], 0.0);
				}
				for(int i=0; i<3;i++)
				{
					SetCtrlVal(panelHandle, numeric2[i], 0);
				}
				
				if(loaded)
				{
					for(int i = 16;i<27;++i)
					{	

						SetCtrlAttribute(panelHandle, i, ATTR_DIMMED, !powerSwitch);
					}
				}
				
				if(powerSwitch == 0)
				{
					derivateON = 0;
					free(waveData);
					waveData = 0;
				}
				
			break;
	}
	return 0;
}


int CVICALLBACK OnLoadButtonCB (int panel, int control, int event,
								void *callbackData, int eventData1, int eventData2)
{
		switch (event)
	{
		case EVENT_COMMIT:
			//executa script python pentru conversia unui fisierului .wav in .txt
			LaunchExecutable("python main.py");
			
			loaded = 1;
			
			//incarc informatiile privind rata de esantionare si numarul de valori
			FileToArray("wafeInfo.txt", waveInfo, VAL_INTEGER, 2, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
			
			npoints = waveInfo[NPOINTS];
			sampleRate = waveInfo[SAMPLE_RATE];
			
			//alocare memorie pentru numarul de puncte
			if(waveData != NULL)
			{
				free(waveData);
				waveData = 0;
			}
			waveData = (double *) calloc(npoints, sizeof(double));
			
			//incarcare din fisierul .txt in memorie (vector)
			FileToArray("waveData.txt", waveData, VAL_DOUBLE, npoints, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
			
			//afisare pe grapf
			PlotY(panel, PANEL_GRAPH_WAVE, waveData, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			
			//calculare max, min medie.......
			MaxMin1D(waveData, npoints, &max, &maxInd, &min, &minInd);
			StdDev(waveData, npoints, &medie, &stDev);
			Median(waveData, npoints, &mediana);
			
			
			//calculare zero crossing;
			
			for(int i=0;i<npoints-1;i++)
			{
				if(waveData[i]*waveData[i+1] < 0 || waveData[i] == 0)
					zeroCrossing++;
			}
			
			
			//zetarea controalelor numerice
			SetCtrlVal(panel, PANEL_NUMERIC_MINIM, min);
			SetCtrlVal(panel, PANEL_NUMERIC_MAXIM, max);
			SetCtrlVal(panel, PANEL_NUMERIC_MEDIE, medie);
			SetCtrlVal(panel, PANEL_NUMERIC_INDEX_MINIM, minInd);
			SetCtrlVal(panel, PANEL_NUMERIC_INDEX_MAXIM, maxInd);
			SetCtrlVal(panel, PANEL_NUMERIC_MEDIANA, mediana);
			SetCtrlVal(panel, PANEL_NUMERIC_DISPERSIE, stDev);
			SetCtrlVal(panel, PANEL_NUMERIC_ZERO_CROSSING, zeroCrossing);
			
			int * histo = 0;
			double * axys = 0;
			//histograma
			if(histo != NULL)
			{
				free(histo);
				histo = 0;
			}
			histo = (int *)calloc(npoints, sizeof(int));
			
			if(axys != NULL)
			{
				free(axys);
				axys = 0;
			}
			axys = (double *) calloc(npoints, sizeof(double));
			
			Histogram(waveData, npoints, min, max, histo, axys, 10);
			PlotXY(panel, PANEL_GRAPH_HISTOGRAM,axys, histo, 10, VAL_DOUBLE, VAL_INTEGER, VAL_VERTICAL_BAR, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			
			
			for(int i = 16;i<20;++i)
				{	

					SetCtrlAttribute(panelHandle, i, ATTR_DIMMED, !powerSwitch);
				}
			
			SetCtrlAttribute(panel, PANEL_COMMANDBUTTON_2, ATTR_DIMMED, !powerSwitch);
			if(filtredData != NULL && filterAloc == 0)
			{
				free(filtredData);
				filtredData = 0;
				filterAloc = 1;
		
			}
			filtredData = (double*)malloc(sampleRate*sizeof(double));
				
			break;
	}
	return 0;
}

int CVICALLBACK FilterFunction (int panel, int control, int event,
								void *callbackData, int eventData1, int eventData2)
{
	int filtru = 1;
	double alpha = 0;
	int esantion;
	int Sn = 0;
	
	
	switch (event)
	{
		case EVENT_COMMIT:
			if(loaded == 1)
			{
				GetCtrlAttribute(panelHandle, PANEL_RING_FILTRE, ATTR_CTRL_INDEX, &filtru);
				//filtrare alpha
				if(filtru == 1)
				{
					GetCtrlAttribute(panelHandle, PANEL_NUMERIC_ALPHA, ATTR_CTRL_VAL, &alpha);
					filtredData[0] = waveData[0];
					for(int i = start*sampleRate+1; i<stop*sampleRate; i++)
					{
						filtredData[i-start*sampleRate] = (1-alpha) * filtredData[i-start*sampleRate-1] + alpha * waveData[i];
					}
				}
				else
				{
					GetCtrlAttribute(panelHandle, PANEL_RING_ESANTION, ATTR_CTRL_VAL, &esantion);
					for(int i=start*sampleRate;i<stop*sampleRate - esantion;++i)
					{
						Sn = 0;
						for(int j=0;j<esantion;++j)
						{
							Sn += waveData[i+j];
						}
						filtredData[i-start*sampleRate] = Sn/esantion;
					}
					
					int j = 0;
					for(int i=stop*sampleRate-esantion;i<stop*sampleRate;++i)
					{
						Sn = 0;
						while(j != esantion - 1)
						{
							Sn+=waveData[i+j];
							++j;
						}
						if(j != 0)
							filtredData[i - stop*sampleRate+esantion] = Sn/j;
						else
							filtredData[i - stop*sampleRate+esantion] = waveData[i];
						j = 0;
						--esantion;
					}
				
				}
				
				DeleteGraphPlot(panelHandle, PANEL_GRAPH_FILTRED_WAVE, -1, VAL_IMMEDIATE_DRAW);
				PlotY(panel, PANEL_GRAPH_FILTRED_WAVE, filtredData, sampleRate, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
				SetCtrlAttribute(panel, PANEL_GRAPH_FILTRED_WAVE, ATTR_XAXIS_OFFSET, (double)start * sampleRate);
				
				
				for(int i = 20;i<27;++i)
				{	

					SetCtrlAttribute(panelHandle, i, ATTR_DIMMED, !powerSwitch);
				}
			}
			break;
	}
	return 0;
}

int CVICALLBACK OnPrev (int panel, int control, int event,
						void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			next_prev = 0;
			if(start > 0)
			{
				start--;
				stop--;
				SetCtrlVal(panel, PANEL_NUMERIC_STOP, stop);
				SetCtrlVal(panel, PANEL_NUMERIC_START, start);
				FilterFunction(panel, control, event, callbackData, eventData1, eventData2);
				if(derivateON == 1)
					OnDerivate(panel, control, event, callbackData, eventData1, eventData2);
			}
			break;
	}
	return 0;
}

int CVICALLBACK OnNext (int panel, int control, int event,
						void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			next_prev = 0;
			if(stop < 6)
			{
				stop++;
				start++;
				SetCtrlVal(panel, PANEL_NUMERIC_STOP, stop);
				SetCtrlVal(panel, PANEL_NUMERIC_START, start);
				FilterFunction(panel, control, event, callbackData, eventData1, eventData2);
				if(derivateON == 1)
					OnDerivate(panel, control, event, callbackData, eventData1, eventData2);
			}
			break;
	}
	return 0;
}

int CVICALLBACK OnDerivate (int panel, int control, int event,
							void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
					if(derivata != NULL)
					{
						free(derivata);
						derivata = 0;
					}
					
					derivata = (double*)calloc(sampleRate,sizeof(double));
					DifferenceEx(filtredData, sampleRate, 1.0, filtredData , 1, filtredData + sampleRate -1 , 1, DIFF_SECOND_ORDER_CENTRAL, derivata);
					PlotY(panel, PANEL_GRAPH_FILTRED_WAVE, derivata, sampleRate, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_BLUE);

		
				break;
	}
	return 0;
}

int CVICALLBACK OnAnvelope (int panel, int control, int event,
							void *callbackData, int eventData1, int eventData2)
{
	
	switch (event)
	{
		case EVENT_COMMIT:
				if(onAnvelope == 0)
				{
					anvelope = (double*)malloc(264987*sizeof(double));
					FileToArray("anvelopa.txt", anvelope, VAL_DOUBLE, 264987, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);				
					
					plot = PlotY(panel, PANEL_GRAPH_WAVE, anvelope, 264987, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS,  VAL_BLUE);
											   
					onAnvelope++;
				}
				else
				{
					DeleteGraphPlot(panel, PANEL_GRAPH_WAVE, plot, VAL_IMMEDIATE_DRAW);
					onAnvelope--;
				}
			break;
	}
	return 0;
}

int CVICALLBACK OnSwitchPanelCB (int panel, int control, int event,
								 void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			if(panel == panelHandle)
			{
				SetCtrlVal(acqPanel, PANEL_2_SWITCH_PANEL, 1);
				DisplayPanel(acqPanel);
				HidePanel(panel);
			}
			else
			{
				SetCtrlVal(panelHandle, PANEL_2_SWITCH_PANEL, 0);
				DisplayPanel(panelHandle);
				HidePanel(panel);
			}
			break;
	}
	return 0;
}


int CVICALLBACK OnPanel_2 (int panel, int event, void *callbackData,
						   int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_GOT_FOCUS:

			break;
		case EVENT_LOST_FOCUS:

			break;
		case EVENT_CLOSE:
			QuitUserInterface(0);
			break;
	}
	return 0;
}

int CVICALLBACK OnWindowType (int panel, int control, int event,
							  void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
				if(windType == Rectangle)
					
					windType = Blackman;
					
				else

					windType = Rectangle;
				
				LoadFerestruire(panel, control, event, callbackData, eventData1, eventData2);
			break;
	}
	return 0;
}

int CVICALLBACK LoadFerestruire (int panel, int control, int event,
								 void *callbackData, int eventData1, int eventData2)
{
	
	int type = 0;
	int PolOrder = 0;
	int NSidePoints = 0;
	
	switch (event)
	{
		
		case EVENT_COMMIT:
			loaded2 = 1;
			
			ferestruire = (double*)calloc(sampleRate, sizeof(double));
				
			savitsky = (double*)calloc(sampleRate, sizeof(double));
			
			vector = (double*)calloc(sampleRate, sizeof(double));
			
			norch = (double*)calloc(sampleRate, sizeof(double));
			
			//vectorul de npoints/6 elemente
			if(npoints - sampleRate*start2 > sampleRate){
				Copy1D(waveData+sampleRate*start2, sampleRate, ferestruire);
			}
			else
			{
				for(int i=sampleRate*start2;i<npoints;++i)
				{
					ferestruire[i-sampleRate*start2] = waveData[i];
				}
				for(int i = npoints-sampleRate*start2;i<sampleRate;++i)
				{
					ferestruire[i]=0;
				}
			}
			
			
			Copy1D(ferestruire, sampleRate, vector);
			
			//savitzky
			GetCtrlVal(panel, PANEL_2_NSidePoint, &NSidePoints);
			GetCtrlVal(panel, PANEL_2_Pol_Order, &PolOrder);
			
			SavitzkyGolayFiltering(vector, sampleRate, PolOrder, NSidePoints, NULL, savitsky);
			
			
			//filtru Notch
			double Numerator[3];
			double Denominator[3];
			Numerator[0]= 0.958216646376661040918065737059805542231;
			Numerator[1]=-1.897014980249072779727725901466328650713;
			Numerator[2]= 0.958216646376661040918065737059805542231;


			Denominator[0]=1;
			Denominator[1]=-1.897014980249072779727725901466328650713;
			Denominator[2]= 0.916433292753322081836131474119611084461;
			
			norch[0]=vector[0]; norch[1]=vector[1];// wfm – semnalul dat, wfm3 rezultatul filtrarii
			for(int i=2;i<sampleRate;i++) //dimensiunea bufferului =1024 sau se inlocuieste
				norch[i]=Numerator[0]*vector[i]+Numerator[1]*vector[i-1]+Numerator[2]*vector[i-2] - Denominator[1]*norch[i-1]-Denominator[2]*norch[i-2];
			
				
			//ferestruire
			
			
			GetCtrlVal(panel, PANEL_2_BINARYSWITCH_TYPE, &type);
			if(type==0)
			{
				ScaledWindow(ferestruire, sampleRate, RECTANGLE_, &windowConst);
				ScaledWindow(savitsky, sampleRate, RECTANGLE_, &windowConst);
				ScaledWindow(norch, sampleRate, RECTANGLE_, &windowConst);
								
			}
			if(type==1)
			{
				ScaledWindow(ferestruire, sampleRate, BLKMAN_, &windowConst);
				ScaledWindow(savitsky, sampleRate, BLKMAN_, &windowConst);
				ScaledWindow(norch, sampleRate, BLKMAN_, &windowConst);
			}
		
			
			//plotare
			DeleteGraphPlot(panel, PANEL_2_GRAPH_1, -1, VAL_IMMEDIATE_DRAW);
			PlotY(panel, PANEL_2_GRAPH_1, ferestruire, sampleRate, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			SetCtrlAttribute(panel, PANEL_2_GRAPH_1, ATTR_XAXIS_OFFSET, (double)start2 * sampleRate);
		
			DeleteGraphPlot(panel, PANEL_2_GRAPH_2, -1, VAL_IMMEDIATE_DRAW);
			PlotY(panel, PANEL_2_GRAPH_2, savitsky, sampleRate, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			SetCtrlAttribute(panel, PANEL_2_GRAPH_2, ATTR_XAXIS_OFFSET, (double)start2 * sampleRate);
			
			DeleteGraphPlot(panel, PANEL_2_GRAPH_3, -1, VAL_IMMEDIATE_DRAW);
			PlotY(panel, PANEL_2_GRAPH_3, norch, sampleRate, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			SetCtrlAttribute(panel, PANEL_2_GRAPH_3, ATTR_XAXIS_OFFSET, (double)start2 * sampleRate);
			
			break;
	}
	return 0;
}

int CVICALLBACK OnPrev2 (int panel, int control, int event,
						 void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			
			if(loaded2 !=0 && start2 >0)
			{
				start2--;
				stop2--;
				
			
				SetCtrlVal(panel, PANEL_2_NUMERIC, start2);
				SetCtrlVal(panel, PANEL_2_NUMERIC_2, stop2);
				LoadFerestruire(panel, control, event, callbackData, eventData1, eventData2);
			}
			
			break;
	}
	return 0;
}

int CVICALLBACK OnNext2 (int panel, int control, int event,
						 void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			if(loaded2 !=0 && npoints/sampleRate >= stop2)
			{
				start2++;
				stop2++;
				
				
				SetCtrlVal(panel, PANEL_2_NUMERIC, start2);
				SetCtrlVal(panel, PANEL_2_NUMERIC_2, stop2);
				LoadFerestruire(panel, control, event, callbackData, eventData1, eventData2);
			}
			
			
			break;
	}
	return 0;
}

int CVICALLBACK OnRing (int panel, int control, int event,
						void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:		
			start3 = 0;
			stop3 = 1;
			GetCtrlVal(panel, PANEL_2_RING, &N);
			LoadSpectru(panel, control, event, callbackData, eventData1, eventData2); 
			break;
	}
	return 0;
}

int CVICALLBACK LoadSpectru (int panel, int control, int event,
							 void *callbackData, int eventData1, int eventData2)
{
	loaded3 = 1;
	char unit[32]="V";
	int N = 0;
	
	switch (event)
	{
		case EVENT_COMMIT:
			GetCtrlVal(panel, PANEL_2_RING, &N);
			
			spectru_semnal = (double *)calloc(N/2, sizeof(double));
			spectru_savitsky = (double *)calloc(N/2, sizeof(double));
			spectru_notch = (double *)calloc(N/2, sizeof(double));
			
			sec_semnal = (double*)calloc(N, sizeof(double));
			sec_savitzky = (double*)calloc(N, sizeof(double));
			sec_notch = (double*)calloc(N, sizeof(double));
			
			if(sampleRate - N*start3 > N)
			{
				Copy1D(ferestruire+start3*N, N, sec_semnal);
				Copy1D(savitsky+start3*N, N, sec_savitzky);
				Copy1D(norch+start3*N, N, sec_notch);
			}
			else
			{
				for(int i = N*start3 ;i<sampleRate;++i)
				{
					sec_semnal[i-N*start3] = ferestruire[i];
					sec_savitzky[i-N*start3] = savitsky[i];
					sec_notch[i-N*start3] = norch[i];
				}
				for(int i = N*start3;i<N;++i)
				{
					sec_semnal[i] = 0;
					sec_savitzky[i] = 0;
					sec_notch[i] = 0;
				}
			}
			
			SetCtrlVal(panel, PANEL_2_NUMERIC_3, stop3);
			SetCtrlVal(panel, PANEL_2_NUMERIC_4, sampleRate/N +1);
			//spectru semnal initial
			
			double freqPeak, powerPeak;
			AutoPowerSpectrum(sec_semnal, N , 1.0/sampleRate, spectru_semnal, &df);
			PowerFrequencyEstimate(spectru_semnal, N/2, -1.0, windowConst, df, 7, &freqPeak, &powerPeak);
			SpectrumUnitConversion(spectru_semnal, N/2, 0, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df, windowConst, spectru_semnal, unit);
			SetCtrlVal(panel, PANEL_2_NUMERIC_5, freqPeak);
			SetCtrlVal(panel, PANEL_2_NUMERIC_6, powerPeak);
			
			
			//spectru savitzky
			AutoPowerSpectrum(sec_savitzky, N , 1.0/sampleRate, spectru_savitsky, &df1);
			PowerFrequencyEstimate(spectru_savitsky, N/2, -1.0, windowConst, df, 7, &freqPeak, &powerPeak);
			SpectrumUnitConversion(sec_savitzky, N/2, 0, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df1, windowConst, sec_savitzky, unit);
			SetCtrlVal(panel, PANEL_2_NUMERIC_8, freqPeak);
			SetCtrlVal(panel, PANEL_2_NUMERIC_7, powerPeak);
			
			//spectru notch
			AutoPowerSpectrum(sec_notch, N , 1.0/sampleRate, spectru_notch, &df3);
			PowerFrequencyEstimate(spectru_notch, N/2, -1.0, windowConst, df, 7, &freqPeak, &powerPeak);
			SpectrumUnitConversion(sec_notch, N/2, 0, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df3, windowConst, sec_notch, unit);
			SetCtrlVal(panel, PANEL_2_NUMERIC_10, freqPeak);
			SetCtrlVal(panel, PANEL_2_NUMERIC_9, powerPeak);
			
			
			//plotare spectru
			DeleteGraphPlot(panel, PANEL_2_GRAPH_6, -1, VAL_IMMEDIATE_DRAW);
			PlotWaveform(panel, PANEL_2_GRAPH_6, spectru_semnal, N/2, VAL_DOUBLE, 1.0, 0.0, 0.0, df, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GREEN);

			
			DeleteGraphPlot(panel, PANEL_2_GRAPH_4, -1, VAL_IMMEDIATE_DRAW);
			PlotWaveform(panel, PANEL_2_GRAPH_4, spectru_savitsky, N/2, VAL_DOUBLE, 1.0, 0.0, 0.0, df1, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GREEN);

			DeleteGraphPlot(panel, PANEL_2_GRAPH_5, -1, VAL_IMMEDIATE_DRAW);
			PlotWaveform(panel, PANEL_2_GRAPH_5, spectru_notch, N/2, VAL_DOUBLE, 1.0, 0.0, 0.0, df3, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GREEN);
			
			break;
	}
	return 0;
}

int CVICALLBACK OnPrev3 (int panel, int control, int event,
						 void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			if(loaded3 != 0 & start3>0)
			{
				start3--;
				stop3--;
				SetCtrlVal(panel, PANEL_2_NUMERIC_3, stop3);
				SetCtrlVal(panel, PANEL_2_NUMERIC_4, sampleRate/N +1);
				LoadSpectru(panel, control, event, callbackData, eventData1, eventData2);
			}

			break;
	}
	return 0;
}

int CVICALLBACK OnNext3 (int panel, int control, int event,
						 void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			if(loaded3 != 0 && sampleRate/N>=stop3)
			{
				start3++;
				stop3++;
				SetCtrlVal(panel, PANEL_2_NUMERIC_3, stop3);
				SetCtrlVal(panel, PANEL_2_NUMERIC_4, sampleRate/N +1);
				LoadSpectru(panel, control, event, callbackData, eventData1, eventData2);
			}
			break;
	}
	return 0;
}
