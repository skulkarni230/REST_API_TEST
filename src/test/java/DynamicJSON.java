import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import requests.payloads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class DynamicJSON {

	@Test(dataProvider = "BookData")
	public void addBook(String bookName, int lsbn, int aisle, String authorName) throws IOException {
		RestAssured.baseURI = "http://216.10.245.166";

		/*
		* Sending data by calling payload
		* */
		String response = given().header("Content-Type","application/json").body(payloads.addBook(bookName,lsbn,aisle,authorName))
				.when().post("Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();

		/*
		* Sending data by giving the path of JSON file just add files read all in body
		*
		String response = given().header("Content-Type","application/json").body(new String(Files.readAllBytes(Paths.get("c:/user"))))
				.when().post("Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();*/
	}

	@DataProvider(name = "BookData")
	public Object[][] getData()
	{
		return new Object[][] {{"Wing_U",89,809,"Mark_U"}};
	}
}
