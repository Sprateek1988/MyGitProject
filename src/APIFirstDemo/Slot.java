package APIFirstDemo;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Slot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI= "https://marketingapi.rcqatol.com";
	    String responseapi=	given().log().all().queryParam("MarketingAPIKey", "409d6302-1d6b-4bcf-83b8-af4a250b07e9")
	    .queryParam("CompanyCode", "c00000085736").queryParam("PropertyId","409082").queryParam("scheduledate","01/19/2022")
		.when().post("marketingapi/api/appointments/AvailableSlots").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	    
		
	    JsonPath JS = new JsonPath(responseapi);
	    String str = JS.getString("Response[0].AvailableSlots[0].TypeofSlot").toString();
	    System.out.println("JSOn Response ->>>>" +str);

	    
	    
	}

}
