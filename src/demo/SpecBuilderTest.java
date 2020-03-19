package demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import POJO.AddPlace;
import POJO.Location;

public class SpecBuilderTest {
	public static void main(String[] args) {

		RestAssured.baseURI = "";
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("munnekola, bangalore");
		// p.setLocation("bangaloreindia");
		p.setName("Rupom Roy");
		p.setWebsite("www.google.com");
		p.setPhone_number("8197497002");
		p.setLanguage("bengali");
		Location l = new Location();
		l.setLat(-32.3);
		l.setLng(-45.3);
		p.setLocation(l);
		ArrayList<String> addmytypes = new ArrayList<String>();
		addmytypes.add("shoe shop");
		addmytypes.add("nagaland");

		for (String s : addmytypes) {
			System.out.println(s);
		}
		p.setTypes(addmytypes);
		// One way
		/*
		 * RequestSpecification req =new
		 * RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
		 * addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		 * Response res=given().spec(req).body(p).
		 * when().post("/maps/api/place/add/json").
		 * then().log().all().assertThat().statusCode(200).extract().response();
		 */

		// other way:

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		ResponseSpecification resspecification = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		RequestSpecification res = given().spec(req).body(p);
		Response response = res.when().post("/maps/api/place/add/json").then().assertThat().spec(resspecification)
				.extract().response();
		System.out.println(response);
		String finalresult = response.asString();
		System.out.println(finalresult);

	}

}
