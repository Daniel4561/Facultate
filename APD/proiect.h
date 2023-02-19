/**************************************************************************/
/* LabWindows/CVI User Interface Resource (UIR) Include File              */
/*                                                                        */
/* WARNING: Do not add to, delete from, or otherwise modify the contents  */
/*          of this include file.                                         */
/**************************************************************************/

#include <userint.h>

#ifdef __cplusplus
    extern "C" {
#endif

     /* Panels and Controls: */

#define  PANEL                            1       /* callback function: PanelFunction */
#define  PANEL_LED                        2       /* control type: LED, callback function: (none) */
#define  PANEL_BINARYSWITCH               3       /* control type: binary, callback function: PowerSwith */
#define  PANEL_LOADBUTTON                 4       /* control type: command, callback function: OnLoadButtonCB */
#define  PANEL_GRAPH_WAVE                 5       /* control type: graph, callback function: (none) */
#define  PANEL_GRAPH_FILTRED_WAVE         6       /* control type: graph, callback function: (none) */
#define  PANEL_NUMERIC_MINIM              7       /* control type: numeric, callback function: (none) */
#define  PANEL_NUMERIC_MAXIM              8       /* control type: numeric, callback function: (none) */
#define  PANEL_NUMERIC_MEDIE              9       /* control type: numeric, callback function: (none) */
#define  PANEL_NUMERIC_INDEX_MINIM        10      /* control type: numeric, callback function: (none) */
#define  PANEL_NUMERIC_INDEX_MAXIM        11      /* control type: numeric, callback function: (none) */
#define  PANEL_NUMERIC_MEDIANA            12      /* control type: numeric, callback function: (none) */
#define  PANEL_NUMERIC_DISPERSIE          13      /* control type: numeric, callback function: (none) */
#define  PANEL_NUMERIC_ZERO_CROSSING      14      /* control type: numeric, callback function: (none) */
#define  PANEL_GRAPH_HISTOGRAM            15      /* control type: graph, callback function: (none) */
#define  PANEL_RING_FILTRE                16      /* control type: ring, callback function: (none) */
#define  PANEL_BUTTON_FILTRE              17      /* control type: command, callback function: FilterFunction */
#define  PANEL_RING_ESANTION              18      /* control type: ring, callback function: (none) */
#define  PANEL_NUMERIC_ALPHA              19      /* control type: numeric, callback function: (none) */
#define  PANEL_BUTTON_PREV                20      /* control type: command, callback function: OnPrev */
#define  PANEL_BUTTON_NEXT                21      /* control type: command, callback function: OnNext */
#define  PANEL_NUMERIC_START              22      /* control type: numeric, callback function: (none) */
#define  PANEL_NUMERIC_STOP               23      /* control type: numeric, callback function: (none) */
#define  PANEL_TEXTMSG_STOP               24      /* control type: textMsg, callback function: (none) */
#define  PANEL_TEXTMSG_START              25      /* control type: textMsg, callback function: (none) */
#define  PANEL_COMMANDBUTTON              26      /* control type: command, callback function: OnDerivate */
#define  PANEL_COMMANDBUTTON_2            27      /* control type: command, callback function: OnAnvelope */
#define  PANEL_SWITCH_PANEL               28      /* control type: binary, callback function: OnSwitchPanelCB */
#define  PANEL_TEXTMSG_INTERVALS          29      /* control type: textMsg, callback function: (none) */

#define  PANEL_2                          2       /* callback function: OnPanel_2 */
#define  PANEL_2_SWITCH_PANEL             2       /* control type: binary, callback function: OnSwitchPanelCB */
#define  PANEL_2_GRAPH_5                  3       /* control type: graph, callback function: (none) */
#define  PANEL_2_GRAPH_6                  4       /* control type: graph, callback function: (none) */
#define  PANEL_2_GRAPH_4                  5       /* control type: graph, callback function: (none) */
#define  PANEL_2_GRAPH_3                  6       /* control type: graph, callback function: (none) */
#define  PANEL_2_GRAPH_2                  7       /* control type: graph, callback function: (none) */
#define  PANEL_2_GRAPH_1                  8       /* control type: graph, callback function: (none) */
#define  PANEL_2_BINARYSWITCH_TYPE        9       /* control type: binary, callback function: OnWindowType */
#define  PANEL_2_COMMANDBUTTON_6          10      /* control type: command, callback function: LoadSpectru */
#define  PANEL_2_COMMANDBUTTON            11      /* control type: command, callback function: LoadFerestruire */
#define  PANEL_2_RING                     12      /* control type: ring, callback function: OnRing */
#define  PANEL_2_COMMANDBUTTON_5          13      /* control type: command, callback function: OnNext3 */
#define  PANEL_2_COMMANDBUTTON_3          14      /* control type: command, callback function: OnNext2 */
#define  PANEL_2_COMMANDBUTTON_4          15      /* control type: command, callback function: OnPrev3 */
#define  PANEL_2_COMMANDBUTTON_2          16      /* control type: command, callback function: OnPrev2 */
#define  PANEL_2_NUMERIC_4                17      /* control type: numeric, callback function: (none) */
#define  PANEL_2_NUMERIC_2                18      /* control type: numeric, callback function: (none) */
#define  PANEL_2_NSidePoint               19      /* control type: numeric, callback function: (none) */
#define  PANEL_2_Pol_Order                20      /* control type: numeric, callback function: (none) */
#define  PANEL_2_NUMERIC_3                21      /* control type: numeric, callback function: (none) */
#define  PANEL_2_NUMERIC                  22      /* control type: numeric, callback function: (none) */
#define  PANEL_2_NUMERIC_9                23      /* control type: numeric, callback function: (none) */
#define  PANEL_2_NUMERIC_10               24      /* control type: numeric, callback function: (none) */
#define  PANEL_2_NUMERIC_7                25      /* control type: numeric, callback function: (none) */
#define  PANEL_2_NUMERIC_8                26      /* control type: numeric, callback function: (none) */
#define  PANEL_2_NUMERIC_6                27      /* control type: numeric, callback function: (none) */
#define  PANEL_2_NUMERIC_5                28      /* control type: numeric, callback function: (none) */
#define  PANEL_2_SPLITTER                 29      /* control type: splitter, callback function: (none) */
#define  PANEL_2_SPLITTER_2               30      /* control type: splitter, callback function: (none) */
#define  PANEL_2_TEXTMSG                  31      /* control type: textMsg, callback function: (none) */


     /* Control Arrays: */

          /* (no control arrays in the resource file) */


     /* Menu Bars, Menus, and Menu Items: */

          /* (no menu bars in the resource file) */


     /* Callback Prototypes: */

int  CVICALLBACK FilterFunction(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK LoadFerestruire(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK LoadSpectru(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnAnvelope(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnDerivate(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnLoadButtonCB(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnNext(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnNext2(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnNext3(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnPanel_2(int panel, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnPrev(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnPrev2(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnPrev3(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnRing(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnSwitchPanelCB(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnWindowType(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK PanelFunction(int panel, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK PowerSwith(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);


#ifdef __cplusplus
    }
#endif