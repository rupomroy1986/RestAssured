package demo;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import POJO.Api;
import POJO.GetCourse;
import POJO.WebAutomation;
public class OAuthTest {
public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	//expected array
		String[] courseTitles= { "Selenium Webdriver Java","Cypress","Protractor"};
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\rupom\\Desktop\\chromedriver_win32.zip\\chromedriver.exe");
			WebDriver driver= new ChromeDriver();
			driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
			driver.findElement(By.cssSelector("input[type='email']")).sendKeys("fdfd");
			driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("input[type='password']")).sendKeys("fxfe");
			driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
			Thread.sleep(4000);
			String url=driver.getCurrentUrl();
			String partialcode=url.split("code=")[1];
			String code=partialcode.split("&scope")[0];
			System.out.println(code);
			String accessTokenResponse=	given().urlEncodingEnabled(false)
				.queryParams("code",code)
				.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type","authorization_code")
				.when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();
			JsonPath js=new JsonPath(accessTokenResponse);
			String accessToken=js.getString("access_token");
			//pojo classes
			//here we have to give the parent class, to tell the rest assures in  which format you want the response, so we will use Parser,json, if the content-type is application/json, then we can avoid it. 
			GetCourse gc=given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
					.when()
					.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
			             System.out.println(gc.getLinkedIn());
			             System.out.println(gc.getInstructor());
			             //without p
			             System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
			             List<Api> apiCourses=gc.getCourses().getApi();
			     		for(int i=0;i<apiCourses.size();i++)
			     		{
			     			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			     					{
			     				System.out.println(apiCourses.get(i).getPrice());
			     					}
			     		}
			//solving Json complex queries
			     		//get the course name for web automation and comparision, for dynamic array, we will go for arraylist, in run time we can increase the array size.Array is fixed in size whereas arraylist is dynamic.
			     		//one more 
			     		
			     		ArrayList<String> a= new ArrayList<String>();
			     		List<WebAutomation> w=gc.getCourses().getWebAutomation();
			     		for(int j=0;j<w.size();j++)
			     		{
			     			a.add(w.get(j).getCourseTitle());
			     		}
			     		//to convert array into array list
			     	List<String> expectedList	=Arrays.asList(courseTitles);
			     	       Assert.assertTrue(a.equals(expectedList));
			     	       
			     		
			     		
			     		
			     		
				

	}

}
