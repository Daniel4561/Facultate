module RSHL(clk_100MHz, reset, pl, shift, in, out);
    input clk_100MHz, reset, pl, shift;
    input [7:0] in;
    output reg[16:0] out;
    
    always @(posedge clk_100MHz)
    if(reset)
        out <= 8'b0;
    else
        if(pl)
            out <= in;
        else
            if(shift)
                out <= out << 1;
    
endmodule 


module one_period(clk_100MHz, reset, in, out);

	input clk_100MHz, reset, in;
	output out;
	
	reg [1:0] cs, ns;
	always @(posedge clk_100MHz)
		if(reset)
			cs <= 2'b0;
		else
			cs <= ns;
			
	assign out = (cs == 2'b01);
	
	always @(cs or in)
		casex({cs, in})
			3'b00_0 : ns = 2'b00;
			3'b00_1 : ns = 2'b01;
			3'b01_0 : ns = 2'b00;
			3'b01_1 : ns = 2'b10;
			3'b10_0 : ns = 2'b00;
			3'b10_1 : ns = 2'b10;
			default : ns = 2'b00;
		endcase

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
		
		
module fsm_int(clk, reset, in, _force, NSF, clk_pulse, cs, out);
    input clk, reset, _force, clk_pulse;
    input [1:0] in;
    input [2:0] NSF;
    output reg [1:0] out;
    output reg [2:0] cs;
    
    reg [2:0] ns;
    always @(posedge clk)
        if(reset)
            cs <= 3'b000;
        else
            if(_force)
                cs <= NSF;
            else
                if(clk_pulse)
                    cs <= ns;
        
    always @(cs)
        casex(cs)
            3'b0x0 : out = 2'b01;
            3'b001 : out = 2'b10;
            3'b011 : out = 2'b11;
            3'b100 : out = 2'b10;
            default : out = 2'b00;
        endcase
    always @(in or cs)
        casex({cs, in})
            5'b000_11, 5'b000_00 : ns = 3'b000;
			5'b000_01 : ns = 3'b001;
			5'b000_10 : ns = 3'b011;
			5'b001_0x : ns = 3'b001;
			5'b001_1x : ns = 3'b010;
			5'b010_00 : ns = 3'b010;
			5'b010_10 : ns = 3'b001;
			5'b010_x1 : ns = 3'b011;
			5'b011_00, 5'b011_1x : ns = 3'b011;
			5'b011_01 : ns = 3'b100;
			5'b100_xx : ns = 3'b000;
			default : ns = 3'b000;
		endcase
					
endmodule	

module fsm(clk_100MHz, reset_butt, force_butt, clk_butt, in_sw, nsf_sw, cs_out, out);
	input clk_100MHz, reset_butt, force_butt, clk_butt;
	input [1:0] in_sw;
	input [2:0] nsf_sw;
	output [2:0] cs_out;
	output [1:0] out;
	
	wire reset_out_sync, force_out_sync, force_out_op, clk_out_sync, clk_out_op;
	wire [1:0] in_out_sync;
	wire [2:0] nsf_out_sync;
		
	sync sync_reset(clk_100MHz,  reset_butt, reset_out_sync);
	
	sync sync_force(clk_100MHz, force_butt, force_out_sync);
	one_period one_period_force(clk_100MHz, reset_out_sync, force_out_sync, force_out_op);
	
	sync sync_clk(clk_100MHz, clk_butt, clk_out_sync);
	one_period one_period_clk(clk_100MHz, reset_out_sync, clk_out_sync, clk_out_op);
	
	sync sync_in_sw0(clk_100MHz, in_sw[0], in_out_sync[0]);
	sync sync_in_sw1(clk_100MHz, in_sw[1], in_out_sync[1]);
	
	sync sync_nsf_sw0(clk_100MHz, nsf_sw[0], nsf_out_sync[0]);
	sync sync_nsf_sw1(clk_100MHz, nsf_sw[1], nsf_out_sync[1]);
	sync sync_nsf_sw2(clk_100MHz, nsf_sw[2], nsf_out_sync[2]);
	
	
	fsm_int fsm_int_1(clk_100MHz, reset_out_sync, in_sw, force_out_op, nsf_out_sync, clk_out_op, cs_out, out);
	
	
		
endmodule