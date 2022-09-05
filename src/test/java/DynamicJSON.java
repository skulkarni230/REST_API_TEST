import org.testng.annotations.Test;

import io.restassured.RestAssured;
import requests.payloads;

import static io.restassured.RestAssured.given;

public class DynamicJSON {

	@Test
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type","application/json").body(payloads.addBook("Gita",2,001,"krishna"))
				.when().post("Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
	}
}
