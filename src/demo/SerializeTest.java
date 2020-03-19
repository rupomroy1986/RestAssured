package demo;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import POJO.AddPlace;
import POJO.Location;
public class SerializeTest {
public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		AddPlace p=new AddPlace();
		p.setAccuracy(50);
		p.setAddress("munnekola, bangalore");
		//p.setLocation("bangaloreindia");
		p.setName("Rupom Roy");
		p.setWebsite("www.google.com");
		p.setPhone_number("8197497002");
		p.setLanguage("bengali");
		Location l=new Location();
		l.setLat(-32.3);
		l.setLng(-45.3);
		p.setLocation(l);
		ArrayList<String> addmytypes=new ArrayList<String>();
		addmytypes.add("shoe shop");
		addmytypes.add("nagaland");
		 
		for( String s:addmytypes )
		{
			System.out.println(s);
			}
		p.setTypes(addmytypes);
		String responseString	=given().log().all().queryParam("key", "qaclick123").
		body(p).when().post("/maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(responseString);
		

	}

}
