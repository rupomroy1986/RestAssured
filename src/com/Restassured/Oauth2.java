package com.Restassured;

import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class Oauth2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "C:\\API TESTING CONTINUE\\chromedriver.exe"); WebDriver driver=new
		 * ChromeDriver(); driver.get(
		 * "https://accounts.google.com/signin/oauth/identifier?client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&as=UwFobQou4ZznRhLbBdKLsg&destination=https%3A%2F%2Frahulshettyacademy.com&approval_state=!ChROZ1V0Q1VqLXZSSVF4a1VoTHhCbxIfNDJwaWRBU2M0Y0lUOEhuU1JuY2dubXJPZGI5TEVoYw%E2%88%99AF-3PDcAAAAAXoGBnqyeLdANnlOpaX_PJLbDRoSjmv1f&oauthgdpr=1&xsrfsig=ChkAeAh8T4vYMp6pXVk1Pq61RfSNF66DHZ6cEg5hcHByb3ZhbF9zdGF0ZRILZGVzdGluYXRpb24SBXNvYWN1Eg9vYXV0aHJpc2t5c2NvcGU&flowName=GeneralOAuthFlow"
		 * ); driver.findElement(By.cssSelector("input[type='email']")).sendKeys(
		 * "roy.rupom6@gmail.com");
		 * driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER
		 * ); Thread.sleep(5000);
		 * driver.findElement(By.cssSelector("input[type='password']")).sendKeys(
		 * "silcharrups1@");
		 * driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER
		 * ); Thread.sleep(5000); String url=driver.getCurrentUrl();
		 */

		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2FyAGYscvIHnbu5nuZZbbZSzSogLg2IzUapbhpCkAyHIrkroqJ8-XlqXOMqQe-hdRD-H9hfnjuhUpJs2HLYxqoCfQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		System.out.println(url);

		String partialcode = url.split("code=")[1];
		System.out.println(partialcode);
		// String partialcode=url.split("code=")[1];
		String code = partialcode.split("&scope")[0];
		System.out.println(code);
		// 2nd step
		// here url encoding is used to tell rest assured not to do url encoding for
		// special characters
		String accessTokenResponse = given().urlEncodingEnabled(false).queryParams("code", code)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();
		// System.out.println(accessTokenResponse);
		JsonPath JsonPath = new JsonPath(accessTokenResponse);
		String accessToken = JsonPath.getString("access_token");
		System.out.println(accessToken);

		// bottom down approach you should follow=Step1
		String response = given().queryParam("access_token", accessToken).when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);

	}

}
