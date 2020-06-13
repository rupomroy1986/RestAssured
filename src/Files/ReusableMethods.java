	package Files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js1=new JsonPath(response);
		return js1;
	}
	
	
	public static JsonPath rawToJson(Response response)
	{
		String respon     = response.asString();
		JsonPath js1=new JsonPath(respon);
		System.out.println(respon);
		return js1;
	}
	
	

}
