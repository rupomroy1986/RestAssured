package com.Restassured;

public class BodyPostBook {
	public static String getBody(String isbn, String aisle){
		String s = "{\n" + 
		  		"\n" + 
		  		"\"name\":\"Learn Aomation in java by rest assured technology \",\n" + 
		  		"\"isbn\":\""+isbn+"\",\n" + 
		  		"\"aisle\":\""+aisle+"\",\n" + 
		  		"\"author\":\"Jn foe\"\n" + 
		  		"}\n" + 
		  		" ";
				//System.out.println(s);
				 return s;	
		
	}
	
	public static String getDeleteBody(String isbn, String aisle){
		String id = isbn + aisle;
		String s = "{\n" + 
				" \n" + 
				"\"ID\" : \""+id+"\"\n" + 
				" \n" + 
				"}";
		return s;
	}

}
