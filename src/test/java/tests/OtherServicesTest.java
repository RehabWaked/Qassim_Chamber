package tests;

import org.testng.annotations.Test;

import Pages.OtherServicesPage;

public class OtherServicesTest extends TestBase{


	OtherServicesPage OtherServicesOBJ;


	@Test
	void UpdateInfoTest() throws InterruptedException
	{
		OtherServicesOBJ = new OtherServicesPage(driver);
		OtherServicesOBJ.DisplayAndCheckUpdateInfoService();
	}
}
