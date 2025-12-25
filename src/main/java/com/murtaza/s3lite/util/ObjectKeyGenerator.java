package com.murtaza.s3lite.util;

import java.time.LocalDate;

public class ObjectKeyGenerator {
	
	private static int counter = 1000;


	
	public static String keyGenerator() {
		
	    return  LocalDate.now().toString()+ "_" + counter++;
		
	}
	
}
