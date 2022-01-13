package APIFirstDemo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Appoitment {

	public static String FName="YPA4";
	public static String LName="YPA4";
	public static String Email="YPA4@gmail.com";
	public static String AppDate="01/19/2022";
	public static String AppTime="12:30AM";
	public static String DesiredMoveInDate="04/20/2022";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://marketingapi.rcqatol.com";
		
		String AppoitStr =given().log().all().queryParam("MarketingAPIKey", "409d6302-1d6b-4bcf-83b8-af4a250b07e9").queryParam("CompanyCode", "c00000085736")
		.queryParam("PropertyId","409082").queryParam("FirstName", FName).queryParam("LastName", LName).queryParam("Email",Email)
		.queryParam("Phone","5555555557").queryParam("ApptDate", AppDate).queryParam("ApptTime", AppTime).queryParam("DesiredMoveInDate", DesiredMoveInDate)
		.queryParam("DesiredBedrooms", "1").when().post("marketingapi/api/appointments/createleadwithappointment").then().log().all().assertThat().statusCode(200).extract().response().asString();

		
		JsonPath js = new JsonPath(AppoitStr);
		String ProspectApptId =js.getString("Response[0].VoyProspectApptId");
		String ProspectId = js.getString("Response[0].VoyProspectId");
		String ProspectCode = js.getString("Response[0].VoyProspectCode");
		
		System.out.println("Prospect ApptID - > " + ProspectApptId);
		System.out.println("Prospect ID - > " + ProspectId);
		System.out.println("Prospect CodeID - > " + ProspectCode);
		
		String CancelStr = given().log().all().queryParam("MarketingAPIKey", "409d6302-1d6b-4bcf-83b8-af4a250b07e9").queryParam("CompanyCode", "c00000085736")
		.queryParam("PropertyId", "409082").queryParam("VoyProspectId",ProspectId).queryParam("VoyApptId",ProspectApptId).when().post("marketingapi/api/appointments/cancelappointment")
		.then().assertThat().statusCode(200).extract().response().asString();
	
		JsonPath js1 = new JsonPath(CancelStr);
			
		//Assert.assertEquals("", "")
		
		System.out.println("Cancel API Response :" + js1.getString("ErrorMessage"));
		System.out.println("Cancel Response :" + CancelStr);
	}

}
