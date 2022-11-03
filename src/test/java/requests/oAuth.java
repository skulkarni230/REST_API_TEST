package requests;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.GetCourses;



public class oAuth {

	public static void main(String[] args) {
		
		
		/*
		 * Providing url and to initiate the process as 
		 * the login to google scenario is restricted by google
		 */
		
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0ARtbsJrcDXjpaHeid82tYfqxWBz4ujoEpwQAZjIpobW8Ys3R9Cgj3ivlR4xIT_U9BbTJHA&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		RestAssured.useRelaxedHTTPSValidation();//this method handles sshHandshake exception (certificate not found)
		String partialCode = url.split("code=")[1];//takes second portion of splitted string 
		String code = partialCode.split("&scope")[0];//takes first portion of the string
		System.out.println(code);
		
		
		
		/*
		 * Exchanging code to get access token
		 */
 		 String accessTokenResponse = given().urlEncodingEnabled(false)
 		.queryParams("code",code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("state","verifyfjdss")
		.queryParams("session_state","ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.queryParams("scope","email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
		.when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		System.out.println(accessTokenResponse);
		
		//this block will extract access token from request
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		
		/*
		 * actual request to use with access token 
		 
		
		String response = given().contentType("application/json").queryParam("access_token", accessToken)
		.when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		*/
		
		//System.out.println(response); //prints response
		
		/*
		 *  this block uses getter and setter to get and set the data from getCourse class
		 */
		GetCourses course = given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);
		
		
		
		// Retrieving data by using getter methods
		System.out.println(course.getExpertise());
		System.out.println(course.getInstructor());
		System.out.println(course.getCourses().getApi().get(1).getCourseTitle());
		//System.out.println(course.getCourses().getApi().get(1)); //to retrieve the nested data at specific index

	}

}
