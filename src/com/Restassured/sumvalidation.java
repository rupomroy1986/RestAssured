package com.Restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class sumvalidation {
	int sum=0;
// TODO Auto-generated method stub
		//verify sum of all courses matches with purchase amount
		@Test
		public void sumOfCourses()
		{
			JsonPath Js=new JsonPath(payload.CoursePrice());
			int count	=Js.getInt("courses.size()");
			for(int i=0;i<count;i++) 
			   {
			int price	=Js.get("courses["+i+"].price");
			int copies	=Js.get("courses["+i+"].copies");
			int amount=price * copies;
			System.out.println(amount);
			 sum=sum+amount;
			 
			}
			System.out.println(sum);
		int purchaseAmount	=Js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		Assert.assertEquals(sum, purchaseAmount);
		
		
			
			
			
		}}


