package com.Restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.ReusableMethods;
import Files.payload;
public class RestAssuredAPIAutomation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//validate if add API is working fine as expected
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//as given is static so we have to manually write the packahe name
		String response=given().log().all().
		queryParam("key", "qaclick123").
		header("Content-Type","application/json").
		body(payload.addplace()).
		when().post("/maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).
		header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		System.out.println(response);
		//to do the further validation from the json path, we have to write the customized code
          JsonPath js=new JsonPath(response);//for parshing json
         String placeid =js.getString("place_id");
         System.out.println(placeid);
          //update place with new place Id
         String newAddress = "Summer Walk, Africa";
         given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").
         body("{\r\n" + 
 				"\"place_id\":\""+placeid+"\",\r\n" + 
 				"\"address\":\""+newAddress+"\",\r\n" + 
 				"\"key\":\"qaclick123\"\r\n" + 
 				"}");
         
         when().put("/maps/api/place/update/json")
     
 		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
         
         //Get Place
      String getplaceresponse   =given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid).when().get("maps/api/place/get/json").
         then().assertThat().statusCode(200).extract().response().asString();
      //here we have to use other assertion method to do that as it is independent of restassured
      
      JsonPath js1=new JsonPath(getplaceresponse);
    /*JsonPath Js1  =ReusableMethods.rawToJson(getplaceresponse);*/
      //for parshing json
      String actualaddress =js1.getString("address");
      System.out.println(actualaddress);
     Assert.assertEquals(actualaddress, newAddress);
         
}
}

