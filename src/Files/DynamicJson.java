package Files;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	
	@Test(dataProvider="Bookdata")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
String response=given().log().all().header("Content-Type","application/json").

//sending parameters to payload from test
body(payload.AddBook(isbn, aisle)).when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
JsonPath js1=ReusableMethods.rawToJson(response);
String id= js1.get("ID");
System.out.println(id);
}
	@DataProvider(name="Bookdata")
	public Object[][] getData() {
		//collection of elements=array
		//multidimensional arrays=collection of arrays
		//here it will run for 3 times
		return new Object[][] {{"roy","india"}, {"soma", "gope"}, {"victor","dey" }};
		 }
	
	
	
	
	}

