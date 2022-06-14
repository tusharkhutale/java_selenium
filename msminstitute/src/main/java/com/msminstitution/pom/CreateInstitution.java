package com.msminstitution.pom;

import com.msminstitution.base.MSMBase;



import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

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

public class CreateInstitution extends MSMBase{
	
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	XSSFSheet sheet; 
	XSSFRow row;
	XSSFTableColumn tcolumn;
	XSSFCell cell;
	XSSFCell InstitutionName=null;
	XSSFCell InstitutionAlias=null;
	XSSFCell DLNO=null;
	XSSFCell PartnerType=null;
	XSSFCell Category=null;
	XSSFCell Logo=null;
	XSSFCell Address1=null;
	XSSFCell Address2=null;
	XSSFCell zipcode=null;
	XSSFCell FoundedYear=null;
	XSSFCell AvgTutionCost=null;
	XSSFCell CostOfLiving=null;
	XSSFCell ApplicationFee=null;
	XSSFCell website=null;
	XSSFCell instituteVideo=null;
	XSSFCell howToReach=null;
	XSSFCell About=null;
	XSSFCell Country=null;
	XSSFCell Province=null;
	XSSFCell InstituteType=null;
	XSSFCell CurrencyCode=null;
	XSSFCell Features=null;
	
	WebDriver driver;
	Actions a;
	JavascriptExecutor exi;
	
	public CreateInstitution(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void fetchExcel(String excelPath){
		
		try {
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("Institution Details");
			
		
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
	public int getCellByName(String cellValue, CreateInstitution createIn) {
		createIn.row = createIn.sheet.getRow(0);
		int colNum = -1;
		for(int i=0;i< createIn.row.getLastCellNum();i++) {
			if(createIn.row.getCell(i).getStringCellValue().trim().equals(cellValue)) {
				colNum = i;
			}	
		}	
		return colNum;
	}
	
	//public void GetCreateInstitutionAndAddAllSubModuleData() throws Throwable{
	public void GetCreateInstitutionAndAddAllSubModuleData() throws InterruptedException{
		CreateInstitution createIn= new CreateInstitution(driver);
		
			//createIn.fetchExcel("T:\\University of Sopron\\Updated_Polytechnic Institute Australia_Institution Details Sheet.xlsx");
			
			createIn.fetchExcel(prop.getProperty("institute_path"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(5000);
			
			driver.findElement(By.xpath("//p[text()='Institution']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
			createIn.row = createIn.sheet.getRow(1);
			int cols = createIn.row.getLastCellNum();
			//System.out.println("Total no of columns:"+cols);
			int rows = createIn.sheet.getLastRowNum();
			XSSFCell cell2 = null;
			XSSFCell cell3 = null;
			XSSFCell cell4 = null;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			for(int i=1;i<2;i++) {
				int j_found=-1;
				for(int j=0; j<cols;j++) {
					cell2 = createIn.getData(0, i, j);
					if(cell2 == null)
						continue;
					if(cell2 != null && cell2.getCellType().toString() != "BLANK") {
						System.out.println(cell2 + " "+ i +" "+j);
						if(j==0) {
							driver.findElement(By.xpath("//input[@name='InstName']")).sendKeys(cell2.toString());
							//new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();
						}
						if(j==1) {
							driver.findElement(By.xpath("//input[@name='InstAlias']")).sendKeys(cell2.toString());
							//new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();
						}
						if(j==2) {
							driver.findElement(By.xpath("//input[@name='DLINo']")).sendKeys(cell2.toString());
						}
						if(j==3) {
							driver.findElement(By.xpath("//mat-select[@name='PartnerTypeId']")).click();
							if(cell2.toString().equals("GMO") || !cell2.toString().equals("Abcodo")) 
								driver.findElement(By.xpath("//mat-option[@ng-reflect-value ='1']")).click();
							else
								driver.findElement(By.xpath("//mat-option[@ng-reflect-value ='2']")).click();
					
						}
						if(j==4) {
							//Category
							driver.findElement(By.xpath("//*[@name='InstCategoryId']//div[@class='mat-select-value']")).click();
							if(cell2.toString().equals("Sponserd")) 
								driver.findElement(By.xpath("//mat-option[@ng-reflect-value ='1']")).click();
							else if(cell2.toString().equals("High Priority"))
								driver.findElement(By.xpath("//mat-option[@ng-reflect-value ='2']")).click();
							else
								driver.findElement(By.xpath("//mat-option[@ng-reflect-value ='3']")).click();
						}
						if(j==8) {
							 driver.findElement(By.xpath("//input[@name='InstAddress1']")).sendKeys(cell2.toString());
						}
						if(j==9) {
							driver.findElement(By.xpath("//input[@name='InstAddress2']")).sendKeys(cell2.toString());
						}
						if(j==10) {
							driver.findElement(By.xpath("//mat-select[@name='InstCountry']")).click();
							Thread.sleep(1000);
							driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+cell2.toString()+"']")).click();
						}
						if(j==11) {
							driver.findElement(By.xpath("//mat-select[@ng-reflect-name='InstProvince']")).click();
							Thread.sleep(1000);
							driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+cell2.toString()+"']")).click();
						}
						if(j==12) {
							//city
							driver.findElement(By.xpath("//span[text()='City']")).click();
							Thread.sleep(1000);
							driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+cell2.toString()+"']")).click();
						
						}
						if(j==13) {
							 driver.findElement(By.xpath("//input[@name='InstZipCode']")).sendKeys(cell2.toString());
						}
						if(j==14) {
							//type
							
							driver.findElement(By.xpath("//span[text()='Type']")).click();
							Thread.sleep(1000);
							driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+cell2.toString()+"']")).click();
						}
						if(j==15) {
							driver.findElement(By.xpath("//input[@name='InstFounded']")).sendKeys(cell2.toString());
						}
						if(j==17) {
							driver.findElement(By.xpath("//mat-select[@ng-reflect-name='InstCurrency']")).click();
							Thread.sleep(1000);
							driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+cell2.toString()+"']")).click();
						}
						if(j==19) {
							//cost of living
							//input[@name="InstCostofLiving_Year"
							String str = cell2.toString();
							StringBuilder sb = new StringBuilder(str);
							  
					        // Removing the last character
					        //sb.deleteCharAt(str.length() - 1);
					        // Removing the first character
					        sb.deleteCharAt(0);
							driver.findElement(By.xpath("//input[@name='InstCostofLiving_Year']")).sendKeys(str);
						}
						if(j==20) {
							//Application Fee
							//input[@name="InstApplicationFee"]
							String str = cell2.toString();
							StringBuilder sb = new StringBuilder(str);
							// Removing the last character
					        //sb.deleteCharAt(str.length() - 1);
					        // Removing the first character
					        sb.deleteCharAt(0);
							driver.findElement(By.xpath("//input[@name='InstApplicationFee']")).sendKeys(str);
						}
						//here
						if(j==22) {
							//scroll down
							jse = (JavascriptExecutor) driver;
							WebElement Element = driver.findElement(By.xpath("//input[@name='Website']"));
							jse.executeScript("arguments[0].scrollIntoView(true);", Element);
							driver.findElement(By.xpath("//input[@name='Website']")).sendKeys(cell2.toString());
						}
						if(j==23) {
							driver.findElement(By.xpath("//input[@name='InstVideo']")).sendKeys(cell2.toString());	
						}
						if(j==24) {
							driver.findElement(By.xpath("//textarea[@name='HowToReach']")).sendKeys(cell2.toString());
							WebElement Element2 = driver.findElement(By.xpath("//button[@type='submit']"));
							jse.executeScript("arguments[0].scrollIntoView(true);", Element2);
							Thread.sleep(3000);
						}
						
					}

				
				}
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
			}
			/*
			int colNum = createIn.getCellByName("Institution Name", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.InstitutionName = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("Institution Alias", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.InstitutionAlias = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("DLINo (If applicable)", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.DLNO = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("Partner Type", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.PartnerType = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("Category", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.Category = createIn.row.getCell(colNum);
			
			
			colNum = createIn.getCellByName("InstAddress1", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.Address1 = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("InstAddress2", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.Address2 = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("Country Name", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.Country = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("Province Name", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.Province = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("InstZipCode", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.zipcode = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("InstTypeName", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.InstituteType = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("InstFounded", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.FoundedYear = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("InstCurrency", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.CurrencyCode = createIn.row.getCell(colNum);
			//
			colNum = createIn.getCellByName("Avg Tuition Cost / Year", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.AvgTutionCost = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("Cost of Living / Year", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.CostOfLiving = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("Cost of Living / Year", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.CostOfLiving = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("Application Fee", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.ApplicationFee = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("Website", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.website = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("InstVideo", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.instituteVideo = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("HowToReach", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.howToReach = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("About", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.About = createIn.row.getCell(colNum);
			
			colNum = createIn.getCellByName("Institute's Features", createIn);
			createIn.row = createIn.sheet.getRow(1);
			createIn.Features = createIn.row.getCell(colNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		//click on xpath to open "Add Institution Page"
		//p[text()='Institution']
		//createIn[text()='Institution']
		driver.findElement(By.xpath("//p[text()='Institution']")).click();
		driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
		
		
		
		
		//fill the form .. DLINo  Category
		if(createIn.InstitutionName != null) driver.findElement(By.xpath("//input[@name='InstName']")).sendKeys(createIn.InstitutionName.toString());
		if(createIn.InstitutionAlias!=null) driver.findElement(By.xpath("//input[@name='InstAlias']")).sendKeys(createIn.InstitutionAlias.toString());
		if(createIn.DLNO!=null) driver.findElement(By.xpath("//input[@name='DLINo']")).sendKeys(createIn.DLNO.toString());
		
		
		//Partner type
		if(createIn.PartnerType != null) {
		driver.findElement(By.xpath("//mat-select[@name='PartnerTypeId']")).click();
		if(createIn.PartnerType.toString() == "GMO" || createIn.PartnerType.toString() != "Abcodo") 
			driver.findElement(By.xpath("//mat-option[@ng-reflect-value ='1']")).click();
		else
			driver.findElement(By.xpath("//mat-option[@ng-reflect-value ='2']")).click();
		}
		//Category
		if(createIn.Category!=null) {
			driver.findElement(By.xpath("//*[@name='InstCategoryId']//div[@class='mat-select-value']")).click();
			if(createIn.Category.toString() == "Sponserd") 
				driver.findElement(By.xpath("//mat-option[@ng-reflect-value ='1']")).click();
			else if(createIn.Category.toString() == "High Priority")
				driver.findElement(By.xpath("//mat-option[@ng-reflect-value ='2']")).click();
			else
				driver.findElement(By.xpath("//mat-option[@ng-reflect-value ='3']")).click();
		}
		else {
			driver.findElement(By.xpath("//*[@name='InstCategoryId']//div[@class='mat-select-value']")).click();
			driver.findElement(By.xpath("//mat-option[@ng-reflect-value ='3']")).click();
		}
		//WebElement category_dropdown = dr.findElement(By.xpath("//mat-option[@class ='mat-option mat-active']"));
		//Select select = new Select(category_dropdown);  
		//select.selectByVisibleText("None");
		
		/*List<WebElement> options = dr.findElements(By.xpath("//mat-select[@name='PartnerTypeId']"));
		for(WebElement option : options) {
			if (option.getText().contains(createIn.PartnerType.toString())) 
			{
			 option.click();
			 break;
			}	
		}
		*/
		//WebElement file_upload = dr.findElement(By.xpath("//app-file-upload[@label='Logo']//span[@class='optionText']"));
		//file_upload.sendKeys("T:\\University of Sopron\\Logo\\University of Sopron Logo 02.jpg");
		
			
			
			
			
			
			
			/*
		if(createIn.Address1!=null) driver.findElement(By.xpath("//input[@name='InstAddress1']")).sendKeys(createIn.Address1.toString());
		if(createIn.Address2!=null) driver.findElement(By.xpath("//input[@name='InstAddress2']")).sendKeys(createIn.Address2.toString());
		//Country
		if(createIn.Country!=null) {
		driver.findElement(By.xpath("//mat-select[@name='InstCountry']")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+createIn.Country.toString()+"']")).click();
		}
		//Province
		if(createIn.Province!=null) {
		driver.findElement(By.xpath("//mat-select[@ng-reflect-name='InstProvince']")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+createIn.Province.toString()+"']")).click();
		}
		//zip code
		if(createIn.zipcode!=null) driver.findElement(By.xpath("//input[@name='InstZipCode']")).sendKeys(createIn.zipcode.toString());
		//Institute Type
		if(createIn.InstituteType!=null) {
		driver.findElement(By.xpath("//mat-select[@ng-reflect-name='InstType']")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+createIn.InstituteType.toString()+"']")).click();
		}
		//Founded
		
		if(createIn.FoundedYear!=null) driver.findElement(By.xpath("//input[@name='InstFounded']")).sendKeys(createIn.FoundedYear.toString());
		//Currency Code
		if(createIn.CurrencyCode!=null) {
		driver.findElement(By.xpath("//mat-select[@ng-reflect-name='InstCurrency']")).click();
		driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+createIn.CurrencyCode.toString()+"']")).click();
		}
		if(createIn.AvgTutionCost!=null) driver.findElement(By.xpath("//input[@name='InstAvgCostTuition_Year']")).sendKeys(createIn.AvgTutionCost.toString());
		if(createIn.CostOfLiving!=null) driver.findElement(By.xpath("//input[@name='InstCostofLiving_Year']")).sendKeys(createIn.CostOfLiving.toString());
		if(createIn.ApplicationFee!=null) driver.findElement(By.xpath("//input[@name='InstApplicationFee']")).sendKeys(createIn.ApplicationFee.toString());
		
		//Scroll down	
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(By.xpath("//input[@name='Website']"));
		jse.executeScript("arguments[0].scrollIntoView(true);", Element);
		Thread.sleep(3000);
		
		if(createIn.website!=null) driver.findElement(By.xpath("//input[@name='Website']")).sendKeys(createIn.website.toString());
		if(createIn.instituteVideo!=null) driver.findElement(By.xpath("//input[@name='InstVideo']")).sendKeys(createIn.instituteVideo.toString());
		if(createIn.howToReach!=null) driver.findElement(By.xpath("//textarea[@name='HowToReach']")).sendKeys(createIn.howToReach.toString());
		
		//div[@data-placeholder='About Institutions']//createIn
		//driver.findElement(By.xpath("//div[@data-placeholder='About Institutions']//p")).click();
		//driver.findElement(By.xpath("//div[@data-placeholder='About Institutions']//p")).sendKeys(createIn.About.toString());
		
		//Scroll down	//button[@type='submit']
		WebElement Element2 = driver.findElement(By.xpath("//button[@type='submit']"));
		jse.executeScript("arguments[0].scrollIntoView(true);", Element2);
		Thread.sleep(3000);
		
		//div[@data-placeholder='Features']//createIn
		//driver.findElement(By.xpath("//div[@data-placeholder='Features']//p")).click();
		//driver.findElement(By.xpath("//div[@data-placeholder='Features']//p")).sendKeys(createIn.Features.toString());
		//button[text()='Cancel']
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		*/
		
	}
	
}
