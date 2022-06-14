package com.msminstitution.pom;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.msminstitution.base.MSMBase;

public class CreateInstRegionOfMarketing extends MSMBase{
	
	//WebDriver driver;
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	XSSFSheet sheet; 
	XSSFRow row;
	XSSFCell cell;
	XSSFCell RegionOfMArketing;
	
	public CreateInstRegionOfMarketing(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void fetchExcel(String excelPath){
		
		try {
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("Region of Marketing");
			
		
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
	public int getCellByName(String cellValue, CreateInstRegionOfMarketing campus) {
		campus.row = campus.sheet.getRow(0);
		int colNum = -1;
		for(int i=0;i< campus.row.getLastCellNum();i++) {
			if(campus.row.getCell(i).getStringCellValue().trim().equals(cellValue)) {
				colNum = i;
			}	
		}	
		return colNum;
	}
	public void GetCreateInstRegionOfMarketingModuleData() throws InterruptedException {
		CreateInstRegionOfMarketing rm = new CreateInstRegionOfMarketing(driver);
		
		//rm.fetchExcel("T:\\University of Sopron\\_NVR Education Australia-Institution Details.xlsx");
		rm.fetchExcel(prop.getProperty("institute_path"));
		int rows = rm.getRowCount(0);
		
		int colNum = rm.getCellByName("Country", rm);
		rm.row = rm.sheet.getRow(1);
		rm.RegionOfMArketing = rm.row.getCell(colNum);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4000);
		//driver.findElement(By.xpath("//div[@class='cdk-overlay-backdrop cdk-overlay-transparent-backdrop cdk-overlay-backdrop-showing']")).click();
		//driver.findElement(By.xpath("(//div[@class='mat-tab-label-content'])[3]")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='mat-tab-label-content'])[3]"))).click();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add']"))).click();
		//driver.findElement(By.xpath("//span[text()='Region']")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Region']"))).click();
		
		if(rm.RegionOfMArketing.toString().equals("GMO") || rm.RegionOfMArketing.toString().equals("Global Marketing Office")) {
			driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='Global Marketing Office']")).click();
		}
		else {
			driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+rm.RegionOfMArketing.toString()+"']")).click();
		}
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//button[text()='Add']")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add']"))).click();
		
	
	}
}
