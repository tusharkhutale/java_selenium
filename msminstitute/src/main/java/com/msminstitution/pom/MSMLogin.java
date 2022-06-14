package com.msminstitution.pom;

import com.msminstitution.base.MSMBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.msminstitution.base.MSMBase;

public class MSMLogin extends MSMBase{
	WebDriver driver;
	Actions a;
	public MSMLogin(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//input[@name='UserName']")
	WebElement uname;
	@FindBy(xpath = "//input[@name='Password']")
	WebElement upass;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement login;
	/*
	@FindBy(name = "UserName")
	WebElement uname;
	@FindBy(name = "Password")
	WebElement upass;
	@FindBy(xpath = "//input[@value='Continue' and @type='submit']")
	WebElement login;
	*/
	public void LoginToMSMPortal()
	{
		try 
		{
			uname.sendKeys(prop.getProperty("uname"));
			upass.sendKeys(prop.getProperty("upass"));
			login.click();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

