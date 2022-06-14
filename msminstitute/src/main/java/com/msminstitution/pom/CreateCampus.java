package com.msminstitution.pom;

import com.msminstitution.base.MSMBase;




import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
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

public class CreateCampus extends MSMBase{
	
	//WebDriver driver;
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	XSSFSheet sheet; 
	XSSFRow row;
	XSSFCell cell;
	
	XSSFSheet sheet2;
	XSSFSheet sheet3;

	
	XSSFCell InstitutionName;
	XSSFCell CampusName;
	XSSFCell City;
	XSSFCell Province;
	XSSFCell Country;
	XSSFCell MapLink;
	
	public CreateCampus(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void fetchExcel(String excelPath){
		
		try {
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("Campus");
			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public XSSFRichTextString getData(int sheetNumber, int row, int colum) {
		sheet1 = wb.getSheetAt(sheetNumber);
		XSSFRichTextString data = sheet1.getRow(row).getCell(colum).getRichStringCellValue();
		return data;
		
	}
	public int getRowCount(int sheetIndex) {
		int row = wb.getSheetAt(sheetIndex).getLastRowNum();
		row = row + 1;
		return row;
	}
	public int getCellByName(String cellValue, CreateCampus campus) {
		campus.row = campus.sheet.getRow(0);
		int colNum = -1;
		for(int i=0;i< campus.row.getLastCellNum();i++) {
			if(campus.row.getCell(i).getStringCellValue().trim().equals(cellValue)) {
				colNum = i;
			}	
		}	
		return colNum;
	}
	public void GetCreateCampusAndAddAllSubModuleData() throws InterruptedException {
		CreateCampus campus = new CreateCampus(driver);
		
		try {
			//campus.fetchExcel("T:\\University of Sopron\\Updated_Polytechnic Institute Australia_Institution Details Sheet.xlsx");
			campus.fetchExcel(prop.getProperty("institute_path"));
			//fetch data from excel
			//Pom createIn = new Pom(""");
			int rows = campus.getRowCount(0);
			//Object[][] d= new Object[rows][6];
			int colNum = campus.getCellByName("InstitutionName", campus);
			campus.row = campus.sheet.getRow(1);
			campus.InstitutionName = campus.row.getCell(colNum);
			
			colNum = campus.getCellByName("campus name", campus);
			campus.row = campus.sheet.getRow(1);
			campus.CampusName = campus.row.getCell(colNum);
			
			colNum = campus.getCellByName("City", campus);
			campus.row = campus.sheet.getRow(1);
			campus.City = campus.row.getCell(colNum);
			
			colNum = campus.getCellByName("State/Province", campus);
			campus.row = campus.sheet.getRow(1);
			campus.Province = campus.row.getCell(colNum);
			
			colNum = campus.getCellByName("Country/ Continent", campus);
			campus.row = campus.sheet.getRow(1);
			campus.Country = campus.row.getCell(colNum);
			
			colNum = campus.getCellByName("GoogleMapLink", campus);
			campus.row = campus.sheet.getRow(1);
			campus.MapLink = campus.row.getCell(colNum);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		/*
		//click on xpath to open "Institution Page"
		driver.findElement(By.xpath("//p[text()='Institution']")).click();
		
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
		//Thread.sleep(10000);//td[@id='dx-col-12-fixed']//span[@class='dx-header-filter dx-header-filter-empty']
		//(//td[@aria-label="Column Name"]//span)[2]
		driver.findElement(By.xpath("(//td[@aria-label=\"Column Name\"]//span)[2]")).click();
		//search
		driver.findElement(By.xpath("//input[@aria-label='Search']")).sendKeys("University of Sopron");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//div[contains(@class, 'dx-item-content dx-list-item-content') and normalize-space(text()) ='University of Sopron']")).click();
		//click on OK button   
		driver.findElement(By.xpath("//span[@class='dx-button-text' and text()='OK']")).click();
		driver.findElement(By.xpath("(//div[@class='dx-datagrid-group-closed'])[2]")).click();
		//(//td[text()='Anti virus'])[1]
		//double click
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath("(//td[text()='University of Sopron'])[1]"));
		actions.doubleClick(elementLocator).perform();
		
		//campus
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4000);
		//driver.findElement(By.xpath("(//div[@class='mat-tab-label-content'])[2]")).click();
		//div[@class='cdk-overlay-backdrop cdk-overlay-transparent-backdrop cdk-overlay-backdrop-showing']
		driver.findElement(By.xpath("//div[@class='cdk-overlay-backdrop cdk-overlay-transparent-backdrop cdk-overlay-backdrop-showing']")).click();
		*/
		
		//driver.findElement(By.xpath("(//div[@class='mat-tab-label-content'])[2]")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='mat-tab-label-content'])[2]"))).click();
		
		
		//class="cdk-overlay-backdrop cdk-overlay-transparent-backdrop cdk-overlay-backdrop-showing"
		//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add']"))).click();
		if(campus.InstitutionName.getCellType().toString() != "BLANK" && campus.City.getCellType().toString() != "BLANK" && campus.Country.getCellType().toString() != "BLANK" 
				&& campus.Province.getCellType().toString() != "BLANK" && campus.MapLink.getCellType().toString() != "BLANK")
		{
			driver.findElement(By.xpath("//input[@name='CampusName']")).sendKeys(campus.InstitutionName.toString());
			driver.findElement(By.xpath("//input[@name='City']")).sendKeys(campus.City.toString());
			
			//Country
			//driver.findElement(By.xpath("//span[text()='Country']")).click(); //Province
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Country']"))).click();
			//driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+campus.Country.toString()+"']")).click();
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+campus.Country.toString()+"']"))).click();
			
			
			//dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//driver.findElement(By.xpath("//span[text()='Province']")).click(); 
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Province']"))).click();
			//driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+campus.Province.toString()+"']")).click();
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+campus.Province.toString()+"']"))).click();
			
			driver.findElement(By.xpath("//textarea[@name='GoogleMapLink']")).sendKeys(campus.MapLink.toString());
			//button[text()='Close']
			//Thread.sleep(2000);
			//driver.findElement(By.xpath("//button[@type='submit']")).click();
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
		}
		else {
			//driver.findElement(By.xpath("//button[text()='Close']")).click();
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Close']"))).click();
		}
		
		//div[@class="mat-dialog-actions"]
		//driver.findElement(By.xpath("//div[@class='mat-dialog-actions']")).click();
		//driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
	}
}
