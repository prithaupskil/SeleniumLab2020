package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.AddFeaturePOM;
import com.training.pom.AddpropertyPOM;
import com.training.pom.DashboardPOM;
import com.training.pom.EditpropertyPOM;
import com.training.pom.LoginPOM;
import com.training.pom.LoginRealEstatePOM;
import com.training.pom.LogoutPOM;
import com.training.pom.publishpropPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC75 {
	private WebDriver driver;
	private String baseUrl;
	private LoginRealEstatePOM loginPOM;
	private DashboardPOM dbPOM;
	private AddpropertyPOM PropPOM;
	private EditpropertyPOM EditpropPOM;
	private publishpropPOM PublishPOM;
	private AddFeaturePOM FeaturePOM;
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
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM= new LoginRealEstatePOM(driver); 
		dbPOM=new DashboardPOM(driver);
		PropPOM=new AddpropertyPOM(driver);
		EditpropPOM= new EditpropertyPOM(driver);
		PublishPOM=new publishpropPOM(driver);
		FeaturePOM=new AddFeaturePOM(driver);
		logoutPOM= new LogoutPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test(dataProvider = "excel-inputs", dataProviderClass = LoginDataProviders.class)
	public void loginDBTest(String FeatureName, String Slug, String Description) throws Exception{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		loginPOM.clickLoginorregister();
		loginPOM.userName.sendKeys("admin");
		loginPOM.password.sendKeys("admin@123");
		screenShot.captureScreenShot("TC75_Login");
		loginPOM.clickLoginBtn();
		
		//screenShot.captureScreenShot(userName);
		
	Thread.sleep(3000);
		
		Actions Act=new Actions(driver);
				Act.moveToElement(dbPOM.propertylink).build().perform();
				Thread.sleep(3000);
			screenShot.captureScreenShot("TC75_adding new feature");
			dbPOM.Feature.click();
			Thread.sleep(3000);
			
	screenShot.captureScreenShot("TC75_Add feature screen");
		
		//String pageTitle=driver.getTitle();
		//System.out.println(pageTitle);
	//Assert.assertEquals(pageTitle,"Features ‹ Real Estate — WordPress");
	
	//Add feature
	
	FeaturePOM.featureName.sendKeys(FeatureName);
	
	FeaturePOM.Slug.sendKeys(Slug);
	
	FeaturePOM.Description.sendKeys(Description);
	
	screenShot.captureScreenShot("TC75_Add Feature");
	FeaturePOM.addFeature.click();
	
	js.executeScript("arguments[0].scrollIntoView(true);",FeaturePOM.featureName);
	
	FeaturePOM.SearchIP.sendKeys(FeatureName);
	
	screenShot.captureScreenShot("TC75_seacrh added feature");
	FeaturePOM.Sbutton.click();
	
	WebElement Tabledata11=FeaturePOM.Tabledata1;
	WebElement Tabledata2=FeaturePOM.Tabledata2;
	WebElement Tabledata3=FeaturePOM.Tabledata3;
	
	String Actualtest=Tabledata11.getText();
	String Actualdescription=Tabledata2.getText();
	String Actualslug=Tabledata3.getText();
	
	Assert.assertEquals(Actualtest,FeatureName);
	Assert.assertEquals(Actualdescription,Description);
	Assert.assertTrue(Actualslug.contains(Slug));
	
	screenShot.captureScreenShot("TC75_feature added successfully");
	

	}

}