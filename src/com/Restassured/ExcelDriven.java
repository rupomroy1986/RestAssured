package com.Restassured;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import Files.ReusableMethods;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class ExcelDriven {
	 String id;

	@Test
	public void addBook() throws IOException {
		dataDriven d=new dataDriven();
		ArrayList<String> data=d.getdata("AddBook", "RestAssured");	
		HashMap<String, Object>  map = new HashMap<>();
		map.put("name", data.get(1));
		map.put("isbn", data.get(2));
		map.put("aisle", data.get(3));
		map.put("author", data.get(4));
		
		/*HashMap<String, Object>  map2 = new HashMap<>();
		map2.put("lat", "12");
		map2.put("lng", "34");
		map.put("location", map2);
*/
		RestAssured.baseURI = "http://216.10.245.166";
		Response response = given().header("Content-Type", "application/json").
         body(map).
				when().post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract()
				.response();
		System.out.println(response);
		         JsonPath js    =  ReusableMethods.rawToJson(response);
		               String id       =js.get("ID");
		               System.out.println(id);
		               
		            /*   given().header("Content-Type", "application/json").body("{\\r\\n\\t\\\"ID\\\":\\\""+id+"\\\"\\r\\n}")
		       		.when().post("/Library/DeleteBook.php").then().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted")); */     
		               
		               
		               
	}    
		/*@Test
		public void deleteBook()
		{
		               RestAssured.baseURI = "http://216.10.245.166";
		               given().header("Content-Type", "application/json").body("{\\r\\n\\t\\\"ID\\\":\\\""+id+"\\\"\\r\\n}").when()
		               .post("/Library/DeleteBook.php").then().assertThat().statusCode(200);
		         }*/
		
		
}
