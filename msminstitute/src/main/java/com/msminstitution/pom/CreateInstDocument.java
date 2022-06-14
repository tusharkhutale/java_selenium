package com.msminstitution.pom;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTableColumn;
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



public class CreateInstDocument extends MSMBase{
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	XSSFSheet sheet; 
	XSSFRow row;
	Row row2;
	Cell cell4;
	XSSFTableColumn tcolumn;
	XSSFCell cell;
	XSSFCell Document1;
	XSSFRichTextString cell3;
	
	public CreateInstDocument(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void fetchExcel(String excelPath){
		
		try {
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(" Document");
			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public XSSFCell getData(int sheetNumber, int row, int colum) {
		sheet = wb.getSheetAt(sheetNumber);
		XSSFCell data = sheet.getRow(row).getCell(colum);
		return data;
		
	}
	public int getRowCount(int sheetIndex) {
		int row = wb.getSheetAt(sheetIndex).getLastRowNum();
		row = row + 1;
		return row;
	}
	public int getCellByName(String cellValue, CreateInstDocument p) {
		p.row = p.sheet.getRow(0);
		int colNum = -1;
		for(int i=0;i< p.row.getLastCellNum();i++) {
			if(p.row.getCell(i).getStringCellValue().trim().equals(cellValue)) {
				colNum = i;
			}	
		}	
		return colNum;
	}
	public void GetCreateInstDocumentData() throws InterruptedException {
		
		CreateInstDocument d = new CreateInstDocument(driver);
		//d.fetchExcel("T:\\University of Sopron\\Updated_Polytechnic Institute Australia_Institution Details Sheet.xlsx");
		d.fetchExcel(prop.getProperty("institute_path"));
		//int rows = d.getRowCount(0);
		d.row = d.sheet.getRow(4);
		
		int cols = d.row.getLastCellNum();
		//System.out.println("Total no of columns:"+cols);
		int rows = d.sheet.getLastRowNum();
		//System.out.println("Total no of rows:"+rows);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement Element;
		XSSFCell cell2;
		
		/*
		driver.findElement(By.xpath("//p[text()='Institution']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//td[@id='dx-col-12-fixed']//span[@class='dx-header-filter dx-header-filter-empty']")).click();
		//search
		driver.findElement(By.xpath("//input[@aria-label='Search']")).sendKeys("Anti virus");
		driver.findElement(By.xpath("//div[contains(@class, 'dx-item-content dx-list-item-content') and normalize-space(text()) ='Anti virus']")).click();
		//click on OK button   
		driver.findElement(By.xpath("//span[@class='dx-button-text' and text()='OK']")).click();
		driver.findElement(By.xpath("(//div[@class='dx-datagrid-group-closed'])[2]")).click();
		//(//td[text()='Anti virus'])[1]
		//double click
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath("(//td[text()='Anti virus'])[1]"));
		actions.doubleClick(elementLocator).perform();
		//document
		driver.findElement(By.xpath("(//div[@class=\"mat-tab-header-pagination-chevron\"])[2]")).click();
		driver.findElement(By.xpath("(//div[@class=\"mat-tab-label-content\"])[8]")).click();
		
		*/
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
		//driver.findElement(By.xpath("(//div[@class='mat-tab-header-pagination-chevron'])[2]")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='mat-tab-header-pagination-chevron'])[2]"))).click();
		
		//driver.findElement(By.xpath("(//div[@class=\"mat-tab-label-content\"])[8]")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=\"mat-tab-label-content\"])[8]"))).click();
		
		
		for(int i=4;i<rows;i++) { //-> for first two rows
			//for(int i=3;i<4;i++) for first row
			//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//Thread.sleep(2000);
			
			XSSFRow row2 = d.sheet.getRow(i);
			if(row2.getCell(0)!=null && row2.getCell(0).getCellType().toString()!= "BLANK" && 
					row2.getCell(1)!=null && row2.getCell(1).getCellType().toString()!= "BLANK")
			{
				//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
				new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add']"))).click();
				
				Thread.sleep(2000);
			}
			
			
			for(int j=0; j<cols;j++) {
				cell2 = row2.getCell(j);
				//
				if(cell2 != null)
				{
					if(cell2.getCellType().toString() == "STRING") {
						
						System.out.println(i+" "+j+" "+cell2+" Type:"+cell2.getCellType().toString());
						if(j==0) {
							//region
							//mat-select[@aria-label="Region"]
							//driver.findElement(By.xpath("//mat-select[@aria-label='Region']")).click();
							new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-select[@aria-label='Region']"))).click();
							Thread.sleep(1000);
							driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+cell2.toString()+"']")).click();
							
						}
						if(j==1) {
							
							//Thread.sleep(2000);
							//((//div[@class='col-md-12'])[3]//div[2])[1]
							//(//div[@class='col-md-12'])[3]
							//driver.findElement(By.xpath("//span[text()='Document']")).click();
							new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Document']"))).click();
							
							Thread.sleep(1000);
							if(cell2.toString().equals("Tuition Receipt")) {
								driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='Tuition Fee Receipt']")).click();
							}
							else {
								driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+cell2.toString()+"']")).click();
							}
							
						}
						//driver.findElement(By.xpath("//button[text()='Add']")).click();
						new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add']"))).click();
						
						Thread.sleep(1000);
					
					}
				}
			}
		}
		
		
		/*
		 * iterate by COLUMN->
		XSSFCell cell1=null;
		XSSFCell cell2=null;
		int columnIndex = 1;
		for(int i=0;i<cols;i++) {
			d.row2 = CellUtil.getRow(3, d.sheet);
			d.cell4 = CellUtil.getCell(d.row2, columnIndex);
			System.out.println("**********"+d.cell4+" Type:"+d.cell4.getCellType().toString());
			for (int rowIndex = 4; rowIndex<rows; rowIndex++){
			    d.row2 = CellUtil.getRow(rowIndex, d.sheet);
			    //d.cell4 = CellUtil.getCell(d.row2, columnIndex);
			    d.cell4 = CellUtil.getCell(d.row2, i);
			    if(d.cell4 != null) {
			    	if(d.cell4.getCellType().toString()!="BLANK") {
			    		System.out.println(i+" "+rowIndex+" "+d.cell4+" Type:"+d.cell4.getCellType().toString());
			    	}
					    	
			    }
			   
			   // System.out.println(d.cell4);
			}
		}
		*/
		
		/*
		//cell2 = d.getData(5, 3, 3);
		driver.findElement(By.xpath("(//div[@class=\"mat-tab-header-pagination-chevron\"])[2]")).click();
		driver.findElement(By.xpath("(//div[@class=\"mat-tab-label-content\"])[8]")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
		Thread.sleep(2000);
		*/
		/*
		for(int i=4;i<rows;i++) {
			int j_found=-1;
			for(int j=1; j<cols;j++) {
				cell2 = d.getData(5, i, j);
				if(cell2 == null)
					continue;
				if(cell2.getCellType().toString() == "STRING") {
					System.out.println(cell2 + " "+ i +" "+j);
					j_found = j;
					cell1 = d.getData(5, 3, j_found);
					String[] splited = cell1.toString().split("\\s+");
					System.out.println("****:"+splited[0]);
					/*
					driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("(//div[@class='col-md-12'])[2]")).click();
					//driver.findElement(By.xpath("//span[text()='Region']")).click();
					driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='All']")).click();
					driver.findElement(By.xpath("(//div[@class='col-md-12'])[3]")).click();
					driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+d.Document1+"']")).click();
					driver.findElement(By.xpath("//button[text()='Add']")).click();
					Thread.sleep(3000);
					*/
			/*	}
				
			}
		}
		
	/*
		driver.findElement(By.xpath("(//div[@class=\"mat-tab-header-pagination-chevron\"])[2]")).click();
		driver.findElement(By.xpath("(//div[@class=\"mat-tab-label-content\"])[8]")).click();
		
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
		Thread.sleep(2000);
		int colNum = d.getCellByName("Europe", d);
		
		d.row = d.sheet.getRow(1);
		d.Document1 = d.row.getCell(colNum);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(7000);
		//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
		//driver.findElement(By.xpath("(//div[@class=\"col-md-12\"])[2]")).click();
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[2]")).click();
		//driver.findElement(By.xpath("//span[text()='Region']")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='All']")).click();
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[3]")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+d.Document1+"']")).click();
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		Thread.sleep(3000);
		
		d.row = d.sheet.getRow(2);
		d.Document1 = d.row.getCell(colNum);
		//driver.findElement(By.xpath("//div[@class='cdk-overlay-backdrop backdropBackground cdk-overlay-backdrop-showing']")).click();
		driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[2]")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='All']")).click();
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[3]")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+d.Document1+"']")).click();
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		Thread.sleep(3000);
		
		d.row = d.sheet.getRow(3);
		d.Document1 = d.row.getCell(colNum);
		//driver.findElement(By.xpath("//div[@class='cdk-overlay-backdrop backdropBackground cdk-overlay-backdrop-showing']")).click();
		driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
		//driver.findElement(By.xpath("(//div[@class=\"mat-select-arrow-wrapper\"])[2]")).click();
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[2]")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='All']")).click();
		//driver.findElement(By.xpath("(//div[@class=\"mat-select-arrow-wrapper\"])[3]")).click();
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[3]")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+d.Document1+"']")).click();
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		Thread.sleep(3000);
		
		d.row = d.sheet.getRow(4);
		d.Document1 = d.row.getCell(colNum);
		//driver.findElement(By.xpath("//div[@class='cdk-overlay-backdrop backdropBackground cdk-overlay-backdrop-showing']")).click();
		driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[2]")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='All']")).click();
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[3]")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+d.Document1+"']")).click();
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		Thread.sleep(3000);
		
		d.row = d.sheet.getRow(5);
		d.Document1 = d.row.getCell(colNum);
		//driver.findElement(By.xpath("//div[@class='cdk-overlay-backdrop backdropBackground cdk-overlay-backdrop-showing']")).click();
		driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[2]")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='All']")).click();
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[3]")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+d.Document1+"']")).click();
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		Thread.sleep(3000);
		
		d.row = d.sheet.getRow(6);
		d.Document1 = d.row.getCell(colNum);
		//driver.findElement(By.xpath("//div[@class='cdk-overlay-backdrop backdropBackground cdk-overlay-backdrop-showing']")).click();
		driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[2]")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='All']")).click();
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[3]")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+d.Document1+"']")).click();
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		Thread.sleep(3000);
		
		d.row = d.sheet.getRow(7);
		d.Document1 = d.row.getCell(colNum);
		//driver.findElement(By.xpath("//div[@class='cdk-overlay-backdrop backdropBackground cdk-overlay-backdrop-showing']")).click();
		driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
		//span[text()='Region']
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[2]")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='All']")).click();
		//span[text()='Document']
		driver.findElement(By.xpath("(//div[@class='col-md-12'])[3]")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+d.Document1+"']")).click();
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		Thread.sleep(3000);
		
		*/
	}
}
