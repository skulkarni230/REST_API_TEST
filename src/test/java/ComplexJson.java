import io.restassured.path.json.JsonPath;
import requests.payloads;

public class ComplexJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js = new JsonPath(payloads.CoursePrice());
		
		//get number of courses test UPDATE FROM LOCAL
		int courseCount = js.getInt("courses.size()");
		System.out.println(courseCount);
		
		// get purchase amount 
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		//title of first course
		String firstCourseTitle = js.get("courses[0].title");
		System.out.println(firstCourseTitle);
		
		//all course and title
		for (int i=0;i<courseCount;i++)
		{
		String allCourseTitles = js.get("courses["+i+"].title");
		System.out.println(allCourseTitles);	
		System.out.println(js.get("courses["+i+"].price").toString());//direct prints price
		//int allCoursePrice = js.get("courses["+i+"].price");
		//System.out.println(allCoursePrice);
		}
		
		
		//specific course and its copies
		//all course and title
		
		for (int i=0;i<courseCount;i++)
		{
			String allCourseTitles = js.get("courses["+i+"].title");
			if(allCourseTitles.equalsIgnoreCase("Cypress"))
			{
				System.out.println(js.get("courses["+i+"].copies").toString());
				break;
			}
			
		}

	}

}
