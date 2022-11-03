import io.restassured.RestAssured;
import requests.payloads;

import static io.restassured.RestAssured.*;


public class UPC {

	public static void main(String[] args) {

		RestAssured.baseURI = "http://10.151.32.200:30386/upc/rest/productmanager/system/generateAuthTokenForStaff";
		
		String response = given().log().all().header("Content-Type","application/json").body(payloads.getAuthentication("127.0.0.1","sysadmin","admin"))
		.when().post()
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
	
	}
	

}
