package com.technoforensis.skilldevelopment.utility;

import java.util.Random;

public class OTP_Gen {
	
	public int gen_otp()
	{
		int minimum= 1000;
		int maximum= 9999;
		Random rn = new Random();
		int range = maximum - minimum + 1;
		int randomNum =  rn.nextInt(range) + minimum;
		
		return randomNum;
	}

}
