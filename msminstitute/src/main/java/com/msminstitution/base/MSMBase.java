package com.msminstitution.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MSMBase {
	public Properties prop;
	public static WebDriver driver;
	
	
	public MSMBase()
	{
		try {
			prop=new Properties();
			FileInputStream ip=new FileInputStream("T:\\MSM Application\\msminstitute\\target\\config.properties");
			prop.load(ip);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}	
			

	public void initialization() throws Throwable {
		String browsewName=prop.getProperty("browser");
		if(browsewName.equals("chrome")) 
		{
			
			System.setProperty("webdriver.chrome.driver", "T:\\MSM Application\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browsewName.equals("firefox"))
		{
			//System.setProperty("webdriver.chrome.driver", "T:\\MSM Application\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));

	}
}
