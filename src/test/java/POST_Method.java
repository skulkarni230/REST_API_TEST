import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import requests.payloads;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POST_Method {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//POST operation
		System.out.println("=========== POST Operation ==========");
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(payloads.AddPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();

		//System.out.println(response);
		//to extract particular field from resopnse
		JsonPath js = new JsonPath(response);
		String reference = js.get("reference");
		String placeid = js.get("place_id");
		
		
		System.out.println("Reference is " +reference);
		
		
		
		System.out.println("=========== PUT Operation ==========");
		String updateeResponse = given().log().all().queryParam("key", "qaclick123").body(payloads.UpdatePlace(placeid))
		.when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();
		
		
		//get operation 
		System.out.println("=========== GET Operation ==========");
		
		String getResponse = given().queryParam("key", "qaclick123").queryParam("place_id", placeid)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js2 = new JsonPath(getResponse);
		String updatedAddress = js2.get("address");
		
		
		System.out.println("The updated address is : " +updatedAddress);
		
	}

}
