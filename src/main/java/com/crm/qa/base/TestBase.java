package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	public static WebDriver driver;
	 public static Properties prop;
	 public static EventFiringWebDriver e_driver;
	 public static WebEventListener eventListener;
	
	 public TestBase(){

	

try{	
prop =new Properties();
FileInputStream ip = new FileInputStream(System.getProperty("C:\\Users\\LITTIN JOMON\\workspace\\PracticeProject\\src\\main\\java\\com\\crm\\qa\\config\\config.properties"));
prop.load(ip);
}
catch (FileNotFoundException e){
	e.printStackTrace();
}catch(IOException e){
	e.printStackTrace();
}}

public static void initializaton(){
	String browserName = prop.getProperty("browser");
	if(browserName.equals("chrome")){
		
	
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\LITTIN JOMON\\Downloads\\exe\\chromedriver.exe");
	
	 driver = new ChromeDriver ();
}
	e_driver =new EventFiringWebDriver(driver);
	//Now create the object of EventListnerHandler to register it with EventfiringWebDriver
	eventListener = new WebEventListener();
	e_driver.register(eventListener);
	driver = e_driver;//elistener = e_driver = Naveen pom 4 last part
	
	
	
	
driver.manage().window().maximize();
driver.manage().deleteAllCookies();
driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
driver.get(prop.getProperty("url"));








	}


}
