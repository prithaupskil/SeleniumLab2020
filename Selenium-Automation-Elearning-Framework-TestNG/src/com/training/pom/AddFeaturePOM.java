package com.training.pom;

import java.awt.List;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddFeaturePOM {
	private WebDriver driver; 
	public AddFeaturePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath="//h1[contains(@class,'wp-heading-inline')]")
	public WebElement Pageheading;
	
	
	@FindBy(xpath="//input[@id='tag-name']")
		public WebElement featureName; 
		
		@FindBy(xpath="//input[@id='tag-slug']")
		public WebElement Slug; 
		
		@FindBy(xpath="//textarea[@id='tag-description']")
		public WebElement Description;
		
		@FindBy(id="parent")
		public WebElement featuredropdown; 
		
	
		@FindBy(xpath="//form[@class='validate']//input[@class='button button-primary']")
		public WebElement addFeature;
		
		//@FindBy(xpath="//input[@id='doaction2']")
		@FindBy(xpath="//div[@class='tablenav bottom']//input[@class='button action']")
		public WebElement Apply;
		
		@FindBy(xpath="/table[contains(@class,'wp-list-table widefat fixed striped tags')]")
		//@FindBy(id="the-list")
		public WebElement featureTable;
		
	//	@FindBy(xpath="//div[contains(@class,'wrap nosubsub')]//tbody//tr")
		//public List TableRow;
		
		@FindBy(xpath="//p[@class='search-box']//input[1]")
		public WebElement SearchIP;
		
		@FindBy(xpath="//p[contains(@class,'search-box')]//input[contains(@class,'button')]")
		public WebElement Sbutton; 
		
		@FindBy(xpath="//td[contains(@class,'name column-name has-row-actions column-primary')]")
		public WebElement Tabledata1;
	@FindBy(xpath="//td[@class='description column-description']")
	public WebElement Tabledata2;
	@FindBy(xpath="//td[@class='slug column-slug']")
	public WebElement Tabledata3;
	@FindBy(xpath="//li[contains(@class,'wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-property')]//li[3]//a[1]")
	public WebElement addproplink;
	

	
		
		

}
