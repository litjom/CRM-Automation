package com.crm.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	String sheetName = "";
	
	public ContactPageTest(){
		super();
	}
	@BeforeMethod
	public void setup(){
		initializaton();
		TestUtil	testUtil = new TestUtil();
		contactsPage =new ContactsPage();
		loginPage = new LoginPage();
		homePage =loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	    testUtil.switchToFrame();
		homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabel(){
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page");
	}
	
	@Test(priority =2)
	public void selectMultipleContactTest(){
		contactsPage.selectContactsByName("test2 test2");
		contactsPage.selectContactsByName("ui uiii");
	}
	
	@DataProvider
	public Object[][] getCRMTestData(){//since its returning 2 object array
	Object data [][]=	testUtil.getTestData(sheetName);
	return data;
	}
	
	
	
	@Test(priority =4,dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstName, String lastName, String company){
		homePage.clickOnNewContactLink();
	//	contactsPage.createNewContact("Mr", "Tom", "peter", "Google");
		contactsPage.createNewContact(title, firstName, lastName, company);
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
