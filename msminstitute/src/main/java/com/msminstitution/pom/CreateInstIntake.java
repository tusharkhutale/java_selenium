package com.msminstitution.pom;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.msminstitution.base.MSMBase;

public class CreateInstIntake extends MSMBase{
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	XSSFSheet sheet; 
	XSSFRow row;
	XSSFCell cell;
	public CreateInstIntake(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void fetchExcel(String excelPath){
		
		try {
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("Intake");
			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public XSSFCell getData(int sheetNumber, int row, int colum) {
		XSSFSheet sheet3;
		sheet3 = wb.getSheetAt(sheetNumber);
		XSSFCell data = sheet3.getRow(row).getCell(colum);
		return data;
		
	}
	public void GetCreateInstitutionIntakeData() throws InterruptedException, ParseException {
		CreateInstIntake ci = new CreateInstIntake(driver);
		
		//ci.fetchExcel("T:\\University of Sopron\\Updated_Polytechnic Institute Australia_Institution Details Sheet.xlsx");
		ci.fetchExcel(prop.getProperty("institute_path"));
		
		//driver.findElement(By.xpath("//p[text()='Institution']")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Institution']"))).click();
		//Thread.sleep(3000);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("(//td[@aria-label=\"Column Name\"]//span)[2]")).click();
		cell = ci.getData(0, 1, 0);
		if(cell != null && cell.getCellType().toString() != "BLANK") {
			driver.findElement(By.xpath("//input[@aria-label='Search']")).sendKeys(cell.toString().trim());
			
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//Thread.sleep(3000);
			
			driver.findElement(By.xpath("//div[contains(@class, 'dx-item-content dx-list-item-content') and normalize-space(text()) ='"+cell.toString().trim()+"']")).click();
			//click on OK button   
			driver.findElement(By.xpath("//span[@class='dx-button-text' and text()='OK']")).click();
			driver.findElement(By.xpath("(//div[@class='dx-datagrid-group-closed'])[2]")).click();
			
			Actions actions = new Actions(driver);
			WebElement elementLocator = driver.findElement(By.xpath("(//td[text()='"+cell.toString().trim()+"'])[1]"));
			actions.doubleClick(elementLocator).perform();
			
			//click on all page content
			//driver.findElement(By.xpath("//div[@class='cdk-overlay-backdrop cdk-overlay-transparent-backdrop cdk-overlay-backdrop-showing']")).click();
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='cdk-overlay-backdrop cdk-overlay-transparent-backdrop cdk-overlay-backdrop-showing']"))).click();
			
			//driver.findElement(By.xpath("(//div[@class='mat-tab-label-content'])[6]")).click();
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='mat-tab-label-content'])[6]"))).click();
			
			
			
			
			ci.row = ci.sheet.getRow(0);
			int cols = ci.row.getLastCellNum();
			//System.out.println("Total no of columns:"+cols);
			int rows = ci.sheet.getLastRowNum();
			//System.out.println("Total no of rows:"+rows);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement Element;
			XSSFCell cell2=null;
		
			System.out.println("rows::::>"+rows);
		
		
		for(int i=1;i<=rows;i++) { 
			
			driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
			Thread.sleep(2000);
			
			XSSFRow row2 = ci.sheet.getRow(i);
			for(int j=0; j<cols;j++) {
				cell2 = row2.getCell(j);
				
				
				//if(cell2.getCellType().toString() == "STRING")
				if(cell2 != null && cell2.getCellType().toString() != "BLANK")
				{
					if(j==1) {
						System.out.println(j + "  "+ cell2.toString());
						driver.findElement(By.xpath("//span[text()='Region']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+cell2.toString()+"']")).click();
					}	
					if(j==0) {
						System.out.println(j + "  "+ cell2.toString());
						String s = cell2.toString();
						SimpleDateFormat month_name = new SimpleDateFormat("MMM", Locale.ENGLISH);
						SimpleDateFormat month_year = new SimpleDateFormat("yyyy", Locale.ENGLISH);
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

						Date date = sdf.parse(s);

						String month = month_name.format(date);
						String year = month_year.format(date);
						String monthYear = month+"-"+year;
						System.out.println("Month :" + monthYear);  
						driver.findElement(By.xpath("//input[@name='IntakeName']")).sendKeys(monthYear);
					}
					
					if(j==2) {
						System.out.println(j + "  "+ cell2.toString());
						@SuppressWarnings("deprecation")
						Date date = new Date(cell2.toString());  
					    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
					    String strDate= formatter.format(date);  
					    //System.out.println(strDate);  
						System.out.println("Month 2:" + strDate);  
						driver.findElement(By.xpath("//input[@placeholder='Intake Date']")).sendKeys(strDate);
					}
					if(j==5) {
						System.out.println(j + "  "+ cell2.toString());
						//Integer a = Integer.parseInt(cell2.toString());
						//String b = Integer.toString(a);
						double d = Double.parseDouble(cell2.toString());
						int b = (int) d;
						String s=Integer.toString(b);
						System.out.println("********:"+s);
						driver.findElement(By.xpath("//input[@name='OfferLetterTAT']")).sendKeys(s);
					}
					if(j==6) {
						System.out.println(j + "  "+ cell2.toString());
						double d = Double.parseDouble(cell2.toString());
						int b = (int) d;
						String s=Integer.toString(b);
						driver.findElement(By.xpath("//input[@name='LOA_TAT']")).sendKeys(s);
					}
					if(j==7) {
						System.out.println(j + "  "+ cell2.toString());	
						double d = Double.parseDouble(cell2.toString());
						int b = (int) d;
						String s=Integer.toString(b);
						driver.findElement(By.xpath("//input[@name='FeeReceiveTAT']")).sendKeys(s);
					}
					if(j==8) {
						System.out.println(j + "  "+ cell2.toString());
						double d = Double.parseDouble(cell2.toString());
						int b = (int) d;
						String s=Integer.toString(b);
						driver.findElement(By.xpath("//input[@name='RefundTAT']")).sendKeys(s);
						}
					if(j==9) {
						System.out.println(j + "  "+ cell2.toString());
						if(cell2.toString().equals("Yes")) {
							
							driver.findElement(By.xpath("(//div[@class=\"mat-checkbox-inner-container\"])[1]")).click();
							
						}
						//scroll down
						Element = driver.findElement(By.xpath("//*[@name='IntekStatus']"));
						jse.executeScript("arguments[0].scrollIntoView(true);", Element);
						Thread.sleep(1000);
					}
					if(j==10) {
						System.out.println(j + "  "+ cell2.toString());
						//td[text()='BSc International Business Economics']
						if(cell2.toString().contains(","))
						{
							WebElement e=null;
							String[] splited = cell2.toString().split(",");
							for(int x=0;x < splited.length;x++) {
								Thread.sleep(2000);
								System.out.println("prog@@:"+splited[x]);
								
								e =driver.findElement(By.xpath("//td[text()='"+splited[x].toString().trim()+"']"));
								//WebElement e =driver.findElement(By.xpath("(//tr[td[position()=1 and text()='"+splited[x].toString().trim()+"']]//mat-checkbox)[2]"));
								jse.executeScript("arguments[0].scrollIntoView(true);", e);
							
								
								
								
								if(e!=null) {
									
									//new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//tr[td[position()=1 and text()='"+splited[x].toString().trim()+"']]//mat-checkbox)[2]"))).click();
									driver.findElement(By.xpath("(//tr[td[position()=1 and text()='"+splited[x].toString().trim()+"']]//mat-checkbox)[2]")).click();
									
									//div[@class="mat-dialog-title"]
									new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='mat-dialog-title']"))).click();
									
									//span[text()='Conditional Application']
									/*
									WebElement r =driver.findElement(By.xpath("//span[text()='Conditional Application']"));
									jse.executeScript("arguments[0].scrollIntoView(true);", r);
									Thread.sleep(3000);
									*/
									
								}
							}
						}
						else {
							WebElement e =driver.findElement(By.xpath("//td[text()='"+cell2.toString().trim()+"']"));
							//Element = driver.findElement(e);
							jse.executeScript("arguments[0].scrollIntoView(true);", e);
							Thread.sleep(1000);
							if(e!=null) {
								//div class="mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin"
								//(//td[text()='BSc International Business Economics'] /following :: input[@type="checkbox"])[2]
								//driver.findElement(By.xpath("")).click();
								//driver.findElement(By.xpath("(//td[text()='"+cell2.toString()+"'] /following :: input[@type=\"checkbox\"])[2]")).click();	
								//(//tr[td[position()=1 and text()="BSc International Business Economics"]]//mat-checkbox)[2]
								//driver.findElement(By.xpath("(//tr[td[position()=1 and text()='"+cell2.toString()+"']]//mat-checkbox)[2]")).click();
								new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//tr[td[position()=1 and text()='"+cell2.toString().trim()+"']]//mat-checkbox)[2]"))).click();
								
							}
						}
						
					
					}
					if(j==11) {
						System.out.print(cell2+ " "+ j);
						driver.findElement(By.xpath("//*[@name='IntekStatus']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+cell2.toString()+"']")).click();
					}
					
				}
				
			}//j  cell2.getCellType().toString() == "STRING" && 
			//if(cell2 != null && i < 4)
		
				//   (//span[text()='Cancel'])[2]
				//driver.findElement(By.xpath("//span[text()='Save']")).click();
				//Thread.sleep(3000);
				//button[text()='Add']
			driver.findElement(By.xpath("//button[text()='Add']")).click();
			Thread.sleep(3000);
			cell2 = null;
			
		}
		}
		//search
		

	}
}
