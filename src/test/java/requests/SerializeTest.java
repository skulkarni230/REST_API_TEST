package requests;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

public class SerializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace addplace = new AddPlace();
		
		addplace.setAccuracy(30);
		addplace.setAddress("New Delhi India");
		addplace.setLanguage("French-IN");
		addplace.setName("Test3010");
		addplace.setPhone_number("(+91) 983 893 3937");
		addplace.setWebsite("http://google.com");
		/*
		 * To add values in array list first create an object of arrylist and add values to it
		 * then pass the object of arraylist in types method
		 * */
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		
		addplace.setTypes(mylist);//passing object of array list
		
		/*
		 * creating object of location class and passing values 
		 * */
		Location location = new Location();		
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		addplace.setLocation(location);//passing object of location
		
		Response res = given().log().all().queryParam("key","qaclick123").body(addplace)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		String responseString = res.asString();
		System.out.println(responseString);
		
		

	}

}
