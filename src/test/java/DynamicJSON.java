import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DynamicJSON {

	@Test
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		
		
	}
}
