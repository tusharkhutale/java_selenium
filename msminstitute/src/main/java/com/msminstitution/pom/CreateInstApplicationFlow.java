package com.msminstitution.pom;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.msminstitution.base.MSMBase;

public class CreateInstApplicationFlow extends MSMBase{
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	XSSFSheet sheet; 
	XSSFRow row;
	XSSFCell cell;
	public CreateInstApplicationFlow(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void fetchExcel(String excelPath){
		
		try {
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheetAt(6);
			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public XSSFCell getData(int sheetNumber, int row, int colum) {
		XSSFSheet sheet3 = wb.getSheetAt(sheetNumber);
		XSSFCell data = sheet3.getRow(row).getCell(colum);
		return data;
		
	}
	public void GetCreateInstApplicationFlowData() throws InterruptedException {
		
		CreateInstApplicationFlow ap = new CreateInstApplicationFlow(driver);
		//fetch data
		//ap.fetchExcel("T:\\University of Sopron\\Updated_Polytechnic Institute Australia_Institution Details Sheet.xlsx");
		ap.fetchExcel(prop.getProperty("institute_path"));
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		//Regular Flow
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=\"mat-tab-header-pagination-chevron\"])[2]"))).click();
		//driver.findElement(By.xpath("(//div[@class=\"mat-tab-header-pagination-chevron\"])[2]")).click();
		//driver.findElement(By.xpath("(//div[@class='mat-tab-label-content'])[10]")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='mat-tab-label-content'])[10]"))).click();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//span[text()='Region']")).click();
		Thread.sleep(2000);
		cell = ap.getData(0, 2, 1);
		if(cell!=null && cell.getCellType().toString() != "BLANK") {
			driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+cell.toString()+"']")).click();
		}
		else {
			driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='All']")).click();
		}
		
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		Thread.sleep(7000);
		
		
		
		/*
		//Indirect Flow for only this
		 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//click on xpath to open "Institution Page"
		driver.findElement(By.xpath("//p[text()='Institution']")).click();
		//Thread.sleep(10000);
		
		driver.findElement(By.xpath("//td[@id='dx-col-12-fixed']//span[@class='dx-header-filter dx-header-filter-empty']")).click();
		//search
		driver.findElement(By.xpath("//input[@aria-label='Search']")).sendKeys("Polytechnic Institute Australia");
		driver.findElement(By.xpath("//div[contains(@class, 'dx-item-content dx-list-item-content') and normalize-space(text()) ='Polytechnic Institute Australia']")).click();
		//click on OK button   
		driver.findElement(By.xpath("//span[@class='dx-button-text' and text()='OK']")).click();
		driver.findElement(By.xpath("(//div[@class='dx-datagrid-group-closed'])[2]")).click();
		//(//td[text()='Anti virus'])[1]
		//double click
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath("(//td[text()='Polytechnic Institute Australia'])[1]"));
		actions.doubleClick(elementLocator).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@class=\"mat-tab-header-pagination-chevron\"])[2]")).click();
		driver.findElement(By.xpath("(//div[@class=\"mat-tab-header-pagination-chevron\"])[2]")).click();
		driver.findElement(By.xpath("(//div[@class='mat-tab-label-content'])[10]")).click();
		
		driver.findElement(By.xpath("//span[text()='Region']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='All']")).click();
		
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		Thread.sleep(7000);
		*/
		
		ap.row = ap.sheet.getRow(4);
		int cols = ap.row.getLastCellNum();
		//System.out.println("Total no of columns:"+cols);
		int rows = ap.sheet.getLastRowNum();
		//System.out.println("Total no of rows:"+rows);
		
		Actions a=new Actions(driver);
		WebElement drop = driver.findElement(By.xpath("//div[@id='flowItem.name']"));
		
		
		for(int i=3;i<rows;i++) {
			//for(int i=3;i<4;i++) for first row
			XSSFRow row2 = ap.sheet.getRow(i);
			for(int j=1; j<cols;j++) {
				XSSFCell cell2 = row2.getCell(j);

				if(cell2 != null && cell2.getCellType().toString() != "BLANK") {
					//System.out.print("  "+j+"  "+i+"  true");
					if(i == 3 && j==1) {
						System.out.print("*******"+cell2.toString());
						////input[@placeholder="Enter Title"]
						//(//app-click-to-edit//span)[1]
						driver.findElement(By.xpath("(//app-click-to-edit//span)[1]")).click();
						Thread.sleep(3000);
						driver.findElement(By.xpath("//input[@placeholder=\"Enter Title\"]")).clear();
						driver.findElement(By.xpath("(//app-click-to-edit//span)[1]")).click();
						driver.findElement(By.xpath("//input[@placeholder=\"Enter Title\"]")).sendKeys(cell2.toString());
					}					
					//second
					if(i == 3 && j==2) {
						System.out.print("*******"+cell2.toString());
						////input[@placeholder="Enter Title"]
						//(//app-click-to-edit//span)[1]
						//mat-icon[text()='add']
						driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
						driver.findElement(By.xpath("(//app-click-to-edit//span)[3]")).click();
						Thread.sleep(3000);
						driver.findElement(By.xpath("(//input[@placeholder='Enter Title'])[2]")).clear();
						driver.findElement(By.xpath("(//app-click-to-edit//span)[3]")).click();
						driver.findElement(By.xpath("(//input[@placeholder='Enter Title'])[2]")).sendKeys(cell2.toString());
						Thread.sleep(3000);
					}
					//third
					if(i == 3 && j==3) {
						System.out.print("*******"+cell2.toString());
						////input[@placeholder="Enter Title"]
						//(//app-click-to-edit//span)[1]
						//mat-icon[text()='add']
						driver.findElement(By.xpath("(//mat-icon[text()='add'])[2]")).click();
						driver.findElement(By.xpath("(//app-click-to-edit//span)[5]")).click();
						Thread.sleep(3000);
						driver.findElement(By.xpath("(//input[@placeholder='Enter Title'])[3]")).clear();
						driver.findElement(By.xpath("(//app-click-to-edit//span)[5]")).click();
						driver.findElement(By.xpath("(//input[@placeholder='Enter Title'])[3]")).sendKeys(cell2.toString());
						Thread.sleep(3000);
					}
					//forth
					if(i == 3 && j==4) {
						System.out.print("*******"+cell2.toString());
						////input[@placeholder="Enter Title"]
						//(//app-click-to-edit//span)[1]
						//mat-icon[text()='add']
						driver.findElement(By.xpath("(//mat-icon[text()='add'])[3]")).click();
						driver.findElement(By.xpath("(//app-click-to-edit//span)[7]")).click();
						Thread.sleep(3000);
						driver.findElement(By.xpath("(//input[@placeholder='Enter Title'])[4]")).clear();
						driver.findElement(By.xpath("(//app-click-to-edit//span)[7]")).click();
						driver.findElement(By.xpath("(//input[@placeholder='Enter Title'])[4]")).sendKeys(cell2.toString());
						Thread.sleep(3000);
					}
					if(i>3 && j==1) {
						//div[@class = 'flow-list cdk-drop-list']//div[normalize-space(text()) ='Application Fee Declined']
						driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						Thread.sleep(2000);
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[normalize-space(text()) ='"+cell2.toString()+"']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(i>3 && j==2) {
						driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[2]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[normalize-space(text()) ='"+cell2.toString()+"']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(i>3 && j==3) {
						driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[3]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[normalize-space(text()) ='"+cell2.toString()+"']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(i>3 && j==4) {
						driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[4]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[normalize-space(text()) ='"+cell2.toString()+"']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					/*
					if(cell2.toString().equals("Application Received") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Application Received ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Initial Screening done by Admissions Team") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Initial Screening done by Admissions Team ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Application Fees Paid") && j==1) {
						//div[@class = 'flow-list cdk-drop-list']//div[text() =' Application Fees Paid ']
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Application Fees Paid ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Application Fee Declined") && j==1) {
						//div[contains(@class, 'mat-option-text') and normalize-space(text()) ='Application Fee Declined']
						//div[@class = 'flow-list cdk-drop-list']//div[normalize-space(text()) =' Application Fee Declined ']
						//div[@class = 'flow-list cdk-drop-list']//div[normalize-space(text()) ='Application Fee Declined']
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Application Fee Declined ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Application completed with documents") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Application completed with documents ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Unconditional Application") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Uncondititional Application ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Application Approved") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Application Approved ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Uncondititional Offer letter Issued") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Uncondititional Offer letter Issued ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Tuition Deposit Done") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Tuition Deposit Done ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("TT Copy sent") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' TT Copy sent ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Tuition Fee Receipt Issued") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Tuition Fee Receipt Issued ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Visa File Under process") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Visa File Under process ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Biometrics Pending") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Biometrics Pending ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Passport Request Received (PPR)") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Passport Request Received (PPR) ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Biometrics Done") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Biometrics Done ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Pay for Accommodation") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Pay for Accommodation ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Pay for Accommodation") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Study Permit Approval ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Study Permit Received") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Study Permit Received ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Visa Scanned Copy Upload") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Visa Scanned Copy Upload ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Pre Departure Completed") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Pre Departure Completed ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Travel Details Sent") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Travel Details Sent ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Apply for Airport Pick Up") && j==1) {
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Apply for Airport Pick Up ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					
					if(cell2.toString().equals("Incomplete Application ") && j==2) {
						driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						Thread.sleep(3000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[2]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Incomplete Application ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Initial Deposit Pending") && j==2) {
						//driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						//Thread.sleep(7000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[2]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Initial Deposit Pending ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Application Fees Pending") && j==2) {
						//driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						//Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[2]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Application Fees Pending ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Tuition Deposit Pending") && j==2) {
						//driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						//Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[2]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Tuition Deposit Pending ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Offer Letter Pending ") && j==2) {
						//driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						//Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[2]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Offer Letter Pending ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Scholarship Letter pending") && j==2) {
						//driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						//Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[2]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Scholarship Letter pending ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Application is revoked") && j==3) {
						driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[3]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Application is revoked ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Application Rejected") && j==3) {
						//driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						//Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[3]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Application Rejected ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Application Rejected by the International Team") && j==3) {
						//driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						//Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[3]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Application Rejected by the International Team ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Drop Out") && j==3) {
						//driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						//Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[3]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Drop Out ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Deferred") && j==3) {
						//driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						//Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[3]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Deferred ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Conditional Application") && j==4) {
						driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[4]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Conditional Application ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Documents Pending") && j==4) {
						driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[4]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Documents Pending ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					if(cell2.toString().equals("Conditional Offer Letter Issued") && j==4) {
						driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
						Thread.sleep(2000);
						WebElement drop2 = driver.findElement(By.xpath("(//div[@id='flowItem.name'])[4]"));
						
						WebElement drag = driver.findElement(By.xpath("//div[@class = 'flow-list cdk-drop-list']//div[text() =' Conditional Offer Letter Issued ']"));
						a.moveToElement(drag)
						.pause(Duration.ofSeconds(1))
						.clickAndHold(drag)
						.pause(Duration.ofSeconds(1))
						.moveByOffset(1, 0)
						.moveToElement(drop2)
						.moveByOffset(1, 0)
						.pause(Duration.ofSeconds(1)).release().perform();
						Thread.sleep(2000);
						drag =null;
					}
					*/
					cell2=null;
					
				}
				else
					System.out.print("false");
				System.out.println();
			}
		}
		
		//span[text()='Update']
		driver.findElement(By.xpath("//span[text()='Update']")).click();
		Thread.sleep(2000);
		
	}
}
