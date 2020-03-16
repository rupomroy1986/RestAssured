package Files;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files; //to be remembered
import java.nio.file.Paths; //to be remembered

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJson {
	@Test
	public void addBook() throws IOException
	{
		RestAssured.baseURI="http://216.10.245.166";
String response=given().log().all().header("Content-Type","application/json").

//sending parameters to payload from test
body(GenerateStringFromResource("D:\\API TESTING\\handling dynamic Json payload with Parameterization\\adddetails.json")).when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
JsonPath js1=ReusableMethods.rawToJson(response);
String id= js1.get("ID");
System.out.println(id);
}
	
	public static String GenerateStringFromResource(String path) throws IOException {
      return new String(Files.readAllBytes(Paths.get(path)));
	
	

	}	

}
