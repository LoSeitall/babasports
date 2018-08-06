package com.oracle.sport.util;

public class ArrayToString {
	
	public static String changeArray(String[] strs){
		String str = "";
		for(int i = 0; i<strs.length ; i++){
			if(i!=strs.length-1){
				str += strs[i] + ",";
			}else{
				str += strs[i];
			}
		}
		return str;
	}
}
