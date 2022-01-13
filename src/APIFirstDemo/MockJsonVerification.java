package APIFirstDemo;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class MockJsonVerification {

	@Test
	public void complexjosn()
	{
		JsonPath js = new JsonPath(MockJson.SlotJSON());
		
		int sz = js.getInt("AvailableSlots.size()");
	}
}
