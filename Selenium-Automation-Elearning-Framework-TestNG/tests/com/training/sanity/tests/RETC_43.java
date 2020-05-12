package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginRealEstatePOM;
import com.training.pom.AddpropertyPOM;
import com.training.pom.DashboardPOM;
import com.training.pom.EditpropertyPOM;
import com.training.pom.LogoutPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_43 {

	private WebDriver driver;
	private String baseUrl;
	private LoginRealEstatePOM loginPOM;
	private DashboardPOM dbPOM;
	private AddpropertyPOM PropPOM;
	private EditpropertyPOM EditpropPOM;
	private LogoutPOM logoutPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.FIREFOX);
		loginPOM= new LoginRealEstatePOM(driver); 
		dbPOM=new DashboardPOM(driver);
		PropPOM=new AddpropertyPOM(driver);
		EditpropPOM= new EditpropertyPOM(driver);
		logoutPOM= new LogoutPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		//driver.quit();
	}
	@Test(priority=1)
	public void validLoginTest() throws Exception {
		//login//
		loginPOM.clickLoginorregister();
		System.out.println("Username:");
		Scanner User = new Scanner(System.in);
		String Username = User.nextLine();
		loginPOM.userName.sendKeys(Username);
				System.out.println("Password:");
		Scanner PWD = new Scanner(System.in);
		String UserPWD = PWD.nextLine();
		loginPOM.password.sendKeys(UserPWD);
		Thread.sleep(5000);
		screenShot.captureScreenShot("TC43_Login");
		loginPOM.clickLoginBtn(); 
			//dashboarddisplay//	
	
		//String dashboardTitle=dbPOM.pageheading.getText();
		//Assert.assertEquals(dashboardTitle,"Dashboard");
		Thread.sleep(3000);
	//	screenShot.captureScreenShot("TC43_Dashboard");
		Actions Act=new Actions(driver);
				Act.moveToElement(dbPOM.propertylink).build().perform();
				Thread.sleep(3000);
			screenShot.captureScreenShot("TC43_adding new property");
			dbPOM.Addnewprop.click();
			Thread.sleep(3000);
			
	screenShot.captureScreenShot("TC43_Add property screen");
		
		String pageTitle=driver.getTitle();
	Assert.assertEquals(pageTitle,"Add Property ‹ Real Estate — WordPress");
	
	System.out.println("Enter Title:");
	Scanner scantest= new Scanner(System.in);
	String titletest=scantest.nextLine();
	//PropPOM.Title.click();
	PropPOM.Title.sendKeys(titletest);
	System.out.println("Enter content:");
	Scanner scantest1= new Scanner(System.in);
	String containtest=scantest1.nextLine();
	PropPOM.Content.sendKeys(containtest);
	screenShot.captureScreenShot("TC43_add details");
	Actions Act1=new Actions(driver);
	Act1.moveToElement(PropPOM.Publish).click(PropPOM.Publish).build().perform();
	//PropPOM.Publish.click();
	
	
	Thread.sleep(8000);
	
	String Editproppage=EditpropPOM.editprop.getText();
	
	Assert.assertEquals(Editproppage, "Edit Property");
	
	EditpropPOM.viewPost.click();
	Thread.sleep(5000);
	
	String Pagetitle= driver.getTitle();
	String Expectedtitle= titletest+" – Real Estate";
	//System.out.println(Pagetitle);
	
			Assert.assertEquals(Pagetitle,Expectedtitle);
	
		/*Assert.assertEquals(pageTitle, "");
		String Pageelement= logoutPOM.loginPage.getText();
		Assert.assertEquals(Pageelement, "Real Estate");
		String Pageelement2= logoutPOM.login.getText();
		Assert.assertEquals(Pageelement2, "LOG IN / REGISTER");
		*/
	}
	
	
	}
		
	
