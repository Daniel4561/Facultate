module onePeriod(clock, reset, in, out);
    
    input clock, reset, in;
    output out;
    
    reg[1:0] ns, cs;
    
    always@(posedge clock)
        if(reset)
            cs <= 2'b00;
        else
            cs <= ns;
       
       assign out = (cs == 2'b01);
       
    always@(cs or in)
        case({cs, in})
             3'b00_0: ns = 2'b00;
              3'b00_1: ns = 2'b01;
              3'b01_0: ns = 2'b00;
              3'b01_1: ns = 2'b10;
              3'b10_0: ns = 2'b00;
              3'b10_1: ns = 2'b10;
              default:
                ns = 2'b00;
        endcase
    
endmodule

module regShift(clock, reset, pl, shl, dataIn, dataOut, zeroFlag);

    input clock, reset, pl, shl;
    input [7 : 0] dataIn;
    
    output reg [15 : 0] dataOut;
    output zeroFlag;
    
    always@(posedge clock)
        if(reset)
            dataOut <= 16'b0;
        else
            begin
                if(pl)
                    dataOut <= {8'b0, dataIn};
                else
                    begin
                        if(shl)
                            dataOut <= dataOut << 1;
                    end
            end
    assign zeroFlag = (dataIn == 8'b0);       
endmodule

module mux2_1 (dataA, dataB, sel, dataOut);

    input [15 : 0] dataA, dataB;
    input sel;
    
    output reg[15 : 0] dataOut;
    
    always@(dataA or dataB or sel)
        begin
            case(sel)
                1'b0 : dataOut = dataA;
                1'b1 : dataOut = dataB;
                default: dataOut = 16'b0;
            endcase
        end
        
endmodule

module sumator(dataA, dataB, dataOut, carryOut);

    input [15 : 0] dataA, dataB;
    
    output [15 : 0] dataOut;
    output carryOut;
    
    assign {carryOut, dataOut} = dataA + dataB;
    
endmodule

module register(clock, reset, pl, dataIn, dataOut);

    input clock, reset, pl;
    input [7 : 0] dataIn;
    output reg[15 : 0] dataOut;
    
    always@(posedge clock)
        begin
            if(reset)
                dataOut <= 16'b0;
            else
                begin
                    if(pl)
                        dataOut <= dataIn;
                end
        end
    
endmodule


module counter(clock, reset, inc, cnt1, cnt3, cnt4, cnt6);
    input clock, reset, inc;
    output cnt1, cnt3, cnt4, cnt6;
    reg [2 : 0] cntTmp;
    
    always@(posedge clock)
        begin
            if(reset)
                cntTmp <= 3'b0;
            else
                if(inc)
                    cntTmp <= cntTmp + 1;
        end
    
    assign cnt1 = (cntTmp == 3'b001);
    assign cnt3 = (cntTmp == 3'b011);
    assign cnt4 = (cntTmp == 3'b100);
    assign cnt6 = (cntTmp == 3'b110);
    
endmodule

module decoder(
    input[5 : 0] CMD,
    output reset,
    output inc,
    output SHLx,
    output SHLy,
    output sel,
    output plRez
    );
    
    assign {reset, inc, SHLx, SHLy, sel, plRez} = CMD;
endmodule

module sync(clk_100MHz, in, out);
	input clk_100MHz, in;
	output reg out;
	reg c;
	
	always @(posedge clk_100MHz)
	   begin
			c <= in;
			out <= c;
		end
endmodule		


module secventiator(
    input clock,
    input [5 : 0] STATUS,
    
    output reg [5 : 0] CMD
    );
    
    reg [5 : 0] cs, ns;
    
    parameter S0 = 6'b000000;
    parameter S1 = 6'b000001;
    parameter S2 = 6'b000010;
    parameter S3 = 6'b000011;
    parameter S4 = 6'b000100;
    parameter S5 = 6'b000101;
    parameter S6 = 6'b000110;
    parameter S7 = 6'b000111;
    parameter S8 = 6'b001000;
    parameter S9 = 6'b001001;
    parameter S10 = 6'b001010;
    parameter S11 = 6'b001011;
    parameter S12 = 6'b001100;
    parameter S13 = 6'b001101;
    parameter S14 = 6'b001110;
    parameter S15 = 6'b001111;
    parameter S16 = 6'b010000;
    parameter S17 = 6'b010001;
    parameter S18 = 6'b010010;
    parameter S19 = 6'b010011;
    parameter S20 = 6'b010100;
    parameter S21 = 6'b010101;
    parameter S22 = 6'b010110;
    parameter S23 = 6'b010111;
    parameter S24 = 6'b011000;
    parameter S25 = 6'b011001;
    parameter S26 = 6'b011010;
    parameter S27 = 6'b011011;
    parameter S28 = 6'b011100;
    parameter S29 = 6'b011101;
    parameter S30 = 6'b011110;
    parameter S31 = 6'b011111;
    parameter S32 = 6'b100000;
    parameter S33 = 6'b100001;
    parameter S34 = 6'b100010;
    
    // STATUS -> zeroX, zeroY, i1, i3, i4, i6
    
    always@ (posedge clock)
    begin
        cs <= ns;
    end
    
    always@(cs or STATUS)
    begin
        casex({cs, STATUS})
        
            //state 0 
            12'b000000_xxxxxx: ns = S1;
            
            //state 1
            12'b000001_xxxxxx: ns = S2;
            
            //state 2
            12'b000010_0xxxxx: ns = S15;
            12'b000010_1xxxxx: ns = S3;
            
            //state 3
            12'b000011_x0xxxx: ns = S6;
            12'b000011_x1xxxx: ns = S4;
            
            //state 4
            12'b000100_xxxxxx: ns = S5;
            
            //state 5
            12'b000101_xxxxxx: ns = S0;
            
            //state 6
            12'b000110_xxxxxx: ns = S7;
            
            //state 7
            12'b000111_xxx0xx: ns = S6;
            12'b000111_xxx1xx: ns = S8;
            
            //state 8 
            12'b001000_xxxxxx: ns = S9;
            
            //state 9
            12'b001001_xxxxxx: ns = S10;
            
            //state 10
            12'b001010_xxxx0x: ns = S9;
            12'b001010_xxxx1x: ns = S11;
            
            //state 11
            12'b001011_xxxxxx: ns = S12;
            
            //state 12
            12'b001100_xxxxxx: ns = S13;
            
            //state 13
            12'b001101_xxxxx0: ns = S12;
            12'b001101_xxxxx1: ns = S14;
            
            //state 14
            12'b001110_xxxxxx: ns = S4;
            
            //state 15
            12'b001111_x1xxxx: ns = S16;
            12'b001111_x0xxxx: ns = S22;
            
            //state 16
            12'b010000_xxxxxx: ns = S17;
            
            //state 17
            12'b010001_xx0xxx: ns = S16;
            12'b010001_xx1xxx: ns = S18;
            
            //state 18
            12'b010010_xxxxxx: ns = S19;
            
            //state 19
            12'b010011_xxxxxx: ns = S20;
            
            //state 20
            12'b010100_xxxxx0: ns = S19;
            12'b010100_xxxxx1: ns = S21;
            
            //state 21
            12'b010101_xxxxxx: ns = S4;
            
            //state 22
            12'b010110_xxxxxx: ns = S23;
            
            //state 23
            12'b010111_xx0xxx: ns = S22;
            12'b010111_xx1xxx: ns = S24;
            
            //state 24
            12'b011000_xxxxxx: ns = S25;
            
            //state 25
            12'b011001_xxxxxx: ns = S26;
            
            //state 26
            12'b011010_xxx0xx: ns = S25;
            12'b011010_xxx1xx: ns = S27;
            
            //state 27
            12'b011011_xxxxxx: ns = S28;
            
            //state 28
            12'b011100_xxxxxx: ns = S29;
            
            //state 29
            12'b011101_xxxx0x: ns = S28;
            12'b011101_xxxx1x: ns = S30;
            
            //state 30
            12'b011110_xxxxxx: ns = S31;
            
            //state 31
            12'b011111_xxxxxx: ns = S32;
            
            //state 32
            12'b100000_xxxxx0: ns = S31;
            12'b100000_xxxxx1: ns = S33;
            
            //state 33
            12'b100001_xxxxxx: ns = S34;
            
            //state 34
            12'b100010_xxxxxx: ns = S4;
            
            default: ns = S0;
                   
        endcase
    end
    
    //CMD = reset, inc, SHLx, SHLy, sel, plRez
    
    always@(cs)
    begin
        case(cs)
            S1: CMD = 6'b100000;
            
            S6: CMD = 6'b010100;
            
            S8: CMD = 6'b000011;
            
            S9: CMD = 6'b010100;
            
            S11: CMD = 6'b000011;
            
            S12: CMD = 6'b010100;
            
            S14: CMD = 6'b000011;
            
            S16: CMD = 6'b011000;
            
            S18: CMD = 6'b000001;
            
            S19: CMD = 6'b011000;
            
            S21: CMD = 6'b000001;
            
            S22: CMD = 6'b011100;
            
            S24: CMD = 6'b000001;
            
            S25: CMD = 6'b011100;
            
            S27: CMD = 6'b000011;
            
            S28: CMD = 6'b011100;
            
            S30: CMD = 6'b000011;
            
            S31: CMD = 6'b011100;
            
            S33: CMD = 6'b000001;
            
            S34: CMD = 6'b000011;
            
            default: CMD = 6'b000000;
        endcase
    end
    
    
endmodule

