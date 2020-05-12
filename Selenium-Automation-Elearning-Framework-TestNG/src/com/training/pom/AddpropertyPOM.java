package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddpropertyPOM {
	private WebDriver driver; 
	public AddpropertyPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
		
		@FindBy(xpath="//h1[@class='wp-heading-inline']")
		public WebElement Addproppagetitle; 
		
		@FindBy(xpath="//input[@id='title']")
		public WebElement Title; 
		
		@FindBy(xpath="//textarea[@id='content']")
		public WebElement Content;
		
		//@FindBy(xpath="//input[@id='publish']")
		//public WebElement Publish;
		
		@FindBy(xpath="//input[@class='button button-primary button-large']")
		public WebElement Publish;
		
		

}
