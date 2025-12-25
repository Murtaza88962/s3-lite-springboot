package com.murtaza.s3lite.util;

import java.security.SecureRandom;

public class ObjectKeyGenerator {
	
     private static final SecureRandom RANDOM = new SecureRandom();
     private static final Integer keyLength = 20;

     
     private static final char[] charSet = "ABCDEFJHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

     private ObjectKeyGenerator() {
         // prevent instantiation
     }
     
	public static String keyGenerator() {
		
		StringBuilder sb = new StringBuilder(keyLength);
		
		for(int i=0 ; i<keyLength ; i++) {
			sb.append(charSet[RANDOM.nextInt(charSet.length)]);
		}
		
		String op = sb.toString();
		return op;
	    
		
	}
	
}
