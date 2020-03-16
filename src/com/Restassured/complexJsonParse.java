package com.Restassured;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//we can write automation script till the developer builds 
		JsonPath Js=new JsonPath(payload.CoursePrice());
	//Print No of courses returned by API
	int count	=Js.getInt("courses.size()");
	System.out.println(count);
		//print purchase amount
int totalAmount	=Js.getInt("dashboard.purchaseAmount");
System.out.println(totalAmount);
//print title of the 1st course
    String titlefirstcourse         =Js.getString("courses[0].title");
    System.out.println(titlefirstcourse);
    //print all course tile and their respective titles
   for(int i=0;i<count;i++) 
   {
	   System.out.println(Js.get("courses["+i+"].title").toString());
	        int Courseprice     =Js.get("courses["+i+"].price");
	        System.out.println(Courseprice);
   }
    //print number of copies sold by RPA course dynamically
   for(int i=0;i<count;i++) 
   {
	String CourseTitle =  Js.get("courses["+i+"].title");
	if(CourseTitle.equalsIgnoreCase("RPA"))
	{
	int RPAcourses	=Js.get("courses["+i+"].copies");
	System.out.println(RPAcourses);
	
	//once it finds the RPA we dont want to iterate so,
	break;
	
	}
   }
    
   
   
   
   
   
    
    
    
    
		
		

	}

}
