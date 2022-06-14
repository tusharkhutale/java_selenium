package com.msminstitution.pom;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.msminstitution.base.MSMBase;

public class CreateProgram extends MSMBase{
	
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	XSSFSheet sheet; 
	XSSFRow row;
	XSSFCell cell;
	
	public CreateProgram(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void fetchExcel(String excelPath){
		
		try {
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("Program Details");
			
		
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
	public void GetCreateProgramFlowData() throws InterruptedException {
		CreateProgram cp = new CreateProgram(driver);
		//cp.fetchExcel("T:\\University of Sopron\\Polytechnic Institute Australia_Program Details Sheet.xlsx");
		cp.fetchExcel(prop.getProperty("program_path"));
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		//driver.findElement(By.xpath("//p[text()='Institution']")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Institution']"))).click();
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		//((//td[@role='columnheader'])[2]//div)[1]
		//driver.findElement(By.xpath("//td[@id='dx-col-12-fixed']//span[@class='dx-header-filter dx-header-filter-empty']")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("((//td[@role='columnheader'])[2]//div)[1]"))).click();
		driver.findElement(By.xpath("((//td[@role='columnheader'])[2]//div)[1]")).click();
		//search
		cell = cp.getData(0, 2, 1);
		
		if(cell != null && cell.getCellType().toString() != "BLANK") {
			
			driver.findElement(By.xpath("//input[@aria-label='Search']")).sendKeys(cell.toString().trim());
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[contains(@class, 'dx-item-content dx-list-item-content') and normalize-space(text()) ='"+cell.toString().trim()+"']")).click();
			
			//click on OK button   
			driver.findElement(By.xpath("//span[@class='dx-button-text' and text()='OK']")).click();
			driver.findElement(By.xpath("(//div[@class='dx-datagrid-group-closed'])[2]")).click();
			
			driver.findElement(By.xpath("(//mat-icon[text()='keyboard_arrow_down'])[2]")).click();
			
		
			driver.findElement(By.xpath("//button[@ng-reflect-router-link=\"/member/institutions/programs/\"]")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(5000);
			
			
			try {
				
				cp.row = cp.sheet.getRow(0);
				int cols = cp.row.getLastCellNum();
				//System.out.println("Total no of columns:"+cols);
				int rows = cp.sheet.getLastRowNum();
				//System.out.println("Total no of rows:"+rows);
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				WebElement Element;
				XSSFCell cell2=null;
			
			
				
			//for(int i=2;i<rows;i++) {-> for all rows
			for(int i=2;i<rows;i++) { //
				//for(int i=3;i<4;i++) for first row
				/*
				driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(2000);
				*/
				XSSFRow row2 = cp.sheet.getRow(i);
				if(row2.getCell(2)==null || row2.getCell(2).getCellType().toString()== "BLANK") {
					break;
				}
				for(int j=1; j<cols;j++) {
					cell2 = row2.getCell(j);
				
					if(j==39) { //Region All - Requirements
						
						Thread.sleep(3000);
						if(row2.getCell(1)!=null) {
							if(row2.getCell(1).getCellType().toString()!= "BLANK") {
								new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Save']"))).click();
								//driver.findElement(By.xpath("//span[text()='Save']")).click();
							}
								
						}
					
						
						Thread.sleep(3000);
						//(//td[text()='Bachelor of Business (Accounting)'])[2]
						if(row2.getCell(39) !=null || row2.getCell(78) !=null) {
							if(row2.getCell(39).getCellType().toString()!= "BLANK" || row2.getCell(78).getCellType().toString()!= "BLANK") {
								new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//td[text()='"+row2.getCell(2).toString().trim()+"'])[2]"))).click();
								Thread.sleep(3000);
								//(//div[@class="mat-tab-label-content"])[4]
								//driver.findElement(By.xpath("(//div[@class='mat-tab-label-content'])[4]")).click();
								new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='mat-tab-label-content'])[4]"))).click();
								//driver.findElement(By.xpath("(//td[text()='"+row2.getCell(2).toString().trim()+"'])[2]")).click();
							}
						}
						
						
						if(row2.getCell(39) !=null && row2.getCell(40) !=null) {
							if(row2.getCell(39).getCellType().toString()!= "BLANK" && row2.getCell(40).getCellType().toString()!= "BLANK") {
								
								//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
								new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add']"))).click();
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
								Thread.sleep(2000);
								driver.findElement(By.xpath("//span[text()='Region']")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+row2.getCell(39).toString().trim()+"']")).click();
								Thread.sleep(1000);
								
								driver.findElement(By.xpath("//span[text()='Requirement']")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='IELTS']")).click();
								
								//input[@name="Score"]
								driver.findElement(By.xpath("//input[@name='Score']")).sendKeys(row2.getCell(40).toString().trim());
								//input[@name='ScoreL']
								
								if(row2.getCell(41).getCellType().toString()!= "BLANK")
									driver.findElement(By.xpath("//input[@name='ScoreL']")).sendKeys(row2.getCell(41).toString().trim());
								if(row2.getCell(42).getCellType().toString()!= "BLANK")
									driver.findElement(By.xpath("//input[@name='ScoreR']")).sendKeys(row2.getCell(42).toString().trim());
								if(row2.getCell(43).getCellType().toString()!= "BLANK")
									driver.findElement(By.xpath("//input[@name='ScoreW']")).sendKeys(row2.getCell(43).toString().trim());
								if(row2.getCell(44).getCellType().toString()!= "BLANK")
									driver.findElement(By.xpath("//input[@name='ScoreS']")).sendKeys(row2.getCell(44).toString().trim());
								//button[text()='Add']
								driver.findElement(By.xpath("//button[text()='Add']")).click();
								Thread.sleep(1000);
							}
						}
						
						if(row2.getCell(39) != null && row2.getCell(50) !=null) {
							if(row2.getCell(39).getCellType().toString()!= "BLANK" && row2.getCell(50).getCellType().toString()!= "BLANK") {
								//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
								Thread.sleep(2000);
								new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add']"))).click();
								Thread.sleep(2000);
								driver.findElement(By.xpath("//span[text()='Region']")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+row2.getCell(39).toString().trim()+"']")).click();
								Thread.sleep(1000);
								
								driver.findElement(By.xpath("//span[text()='Requirement']")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='Duolingo']")).click();
								
								driver.findElement(By.xpath("//input[@name='Score']")).sendKeys(row2.getCell(50).toString().trim());
								driver.findElement(By.xpath("//button[text()='Add']")).click();
								Thread.sleep(1000);
							}
						}
						
						if(row2.getCell(39) !=null && row2.getCell(51) !=null) {
							if(row2.getCell(39).getCellType().toString()!= "BLANK" && row2.getCell(51).getCellType().toString()!= "BLANK") {
								//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
								Thread.sleep(2000);
								new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add']"))).click();
								Thread.sleep(2000);
								driver.findElement(By.xpath("//span[text()='Region']")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+row2.getCell(39).toString().trim()+"']")).click();
								Thread.sleep(1000);
								
								driver.findElement(By.xpath("//span[text()='Requirement']")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='TOEFL iBT']")).click();
								
								driver.findElement(By.xpath("//input[@name='Score']")).sendKeys(row2.getCell(51).toString().trim());
								driver.findElement(By.xpath("//button[text()='Add']")).click();
								Thread.sleep(1000);
							}
						}
						if(row2.getCell(39) !=null && row2.getCell(56) !=null) {
							if(row2.getCell(39).getCellType().toString()!= "BLANK" && row2.getCell(56).getCellType().toString()!= "BLANK") {
								//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
								Thread.sleep(2000);
								new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add']"))).click();
								Thread.sleep(2000);
								driver.findElement(By.xpath("//span[text()='Region']")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+row2.getCell(39).toString().trim()+"']")).click();
								Thread.sleep(1000);
								
								driver.findElement(By.xpath("//span[text()='Requirement']")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='TOEFL PBT']")).click();
								
								driver.findElement(By.xpath("//input[@name='Score']")).sendKeys(row2.getCell(56).toString().trim());
								driver.findElement(By.xpath("//button[text()='Add']")).click();
								Thread.sleep(1000);
							}
						}
						
						if(row2.getCell(39) !=null && row2.getCell(61) !=null) {
							if(row2.getCell(39).getCellType().toString()!= "BLANK" && row2.getCell(61).getCellType().toString()!= "BLANK") {
								//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
								Thread.sleep(2000);
								new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add']"))).click();
								Thread.sleep(2000);
								driver.findElement(By.xpath("//span[text()='Region']")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+row2.getCell(39).toString().trim()+"']")).click();
								Thread.sleep(1000);
								
								driver.findElement(By.xpath("//span[text()='Requirement']")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='Cambridge English']")).click();
								
								driver.findElement(By.xpath("//input[@name='Score']")).sendKeys(row2.getCell(61).toString().trim());
								driver.findElement(By.xpath("//button[text()='Add']")).click();
								Thread.sleep(1000);
							}
						}
						
						//(//div[@class="mat-tab-label-content"])[12]
						/*
						if(row2.getCell(76).getCellType().toString()!= "BLANK" && row2.getCell(77).getCellType().toString()!= "BLANK") {
							//driver.findElement(By.xpath("(//div[@class='mat-tab-label-content'])[12]")).click();
							new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='mat-tab-label-content'])[12]"))).click();
							Thread.sleep(2000);
							//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
							new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add']"))).click();
							
							Thread.sleep(2000);
							driver.findElement(By.xpath("//span[text()='Region']")).click();
							Thread.sleep(1000);
							driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+row2.getCell(76).toString().trim()+"']")).click();
							Thread.sleep(1000);
							driver.findElement(By.xpath("//div[@data-placeholder='Detail']//p")).sendKeys(row2.getCell(77).toString().trim());
							
							driver.findElement(By.xpath("//button[text()='Add']")).click();
							Thread.sleep(1000);
						}
						*/
						
						if(row2.getCell(78) !=null && row2.getCell(79) !=null) {
							if(row2.getCell(78).getCellType().toString()!= "BLANK" && row2.getCell(79).getCellType().toString()!= "BLANK") {
								//driver.findElement(By.xpath("(//div[@class='mat-tab-label-content'])[12]")).click();
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
								Thread.sleep(2000);
								new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='mat-tab-label-content'])[13]"))).click();
								Thread.sleep(2000);
								//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
								//new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add']"))).click();
								System.out.println("79:"+row2.getCell(79).toString().trim()+"  Type:"+row2.getCell(79).getCellType().toString());
								String str = row2.getCell(79).toString().trim();
								//str = "Hello I'm your String";
								String[] splited = str.split("\\r?\\n");
								for(int x=0;x < splited.length;x++) {
									System.out.println("@@:"+splited[x]);
									driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
									Thread.sleep(3000);
									new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add']"))).click();
									driver.findElement(By.xpath("//span[text()='Region']")).click();
									Thread.sleep(1000);
									driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+row2.getCell(78).toString().trim()+"']")).click();
									
									driver.findElement(By.xpath("//span[text()='Document']")).click();
									Thread.sleep(1000);
							
									driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+splited[x].trim()+"']")).click();
									driver.findElement(By.xpath("//button[text()='Add']")).click();
									Thread.sleep(1000);
								}
								
							
							}
						}
						
						
						
						if(row2.getCell(1)!=null) {
							if(row2.getCell(1).getCellType().toString()!= "BLANK") {
								driver.navigate().back();
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
								Thread.sleep(3000);
							}
								
						}
						
								
						
						
					} //close Requirements
					
					//if(cell2.getCellType().toString() == "STRING")
					if(cell2 != null && cell2.getCellType().toString() != "BLANK")
					{
						if(j==1) {
							//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
							new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add']"))).click();
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							Thread.sleep(2000);
						}
						if(j==2) {
							
							//new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.nsg-button"))).click();
							new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='ProgramName']"))).click();
							driver.findElement(By.xpath("//input[@name='ProgramName']")).sendKeys(cell2.toString().trim());
							//System.out.print(cell2.toString());
						}
							
						if(j==3) {
							driver.findElement(By.xpath("//input[@name='AliasName']")).sendKeys(cell2.toString().trim());
							//System.out.print(cell2.toString());
						}
						if(j==4) {
							//input[@name="ProgramCode"]
							driver.findElement(By.xpath("//input[@name='ProgramCode']")).sendKeys(cell2.toString().trim());
						}
						if(j==5) {
							//Discipline
							driver.findElement(By.xpath("(//input[@type='text'])[1]")).click();
							Thread.sleep(1000);
							driver.findElement(By.xpath("//span[contains(@class, 'ng-option-label ng-star-inserted') and normalize-space(text()) ='"+cell2.toString()+"']")).click();		
						}
						if(j==7) {
							//Mode
							
							if(cell2.toString().contains("/"))
							{
								driver.findElement(By.xpath("//span[text()='Modes']")).click();
								String[] splited = cell2.toString().split("/");
								for(int x=0;x < splited.length;x++) {
									System.out.println("Mode@@:"+splited[x]);
									
									Thread.sleep(1000);
									driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+splited[x].trim()+"']")).click();
									Thread.sleep(1000);
									//click on all page content
									
								}
								driver.findElement(By.xpath("//div[@class='cdk-overlay-backdrop cdk-overlay-transparent-backdrop cdk-overlay-backdrop-showing']")).click();
								
							}
							else {
								driver.findElement(By.xpath("//span[text()='Modes']")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+cell2.toString()+"']")).click();
								//click on all page content
								driver.findElement(By.xpath("//div[@class='cdk-overlay-backdrop cdk-overlay-transparent-backdrop cdk-overlay-backdrop-showing']")).click();
							
							}
						}
						if(j==8) {
							//status
							driver.findElement(By.xpath("//mat-select[@name='ProgramStatus']")).click();
							Thread.sleep(1000);
							driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+cell2.toString()+"']")).click();
							
							//scroll down
							Element = driver.findElement(By.xpath("//input[@name='DurationTime']"));
							jse.executeScript("arguments[0].scrollIntoView(true);", Element);
							Thread.sleep(3000);
						}
						if(j==9) {
							//Duration of program
	
							driver.findElement(By.xpath("//input[@name='DurationTime']")).sendKeys(cell2.toString().trim());
							System.out.print(cell2+ " "+ j);
							
						}
						if(j==10) {
							//Integer a= Integer.parseInt(cell2.toString());
							double d = Double.parseDouble(cell2.toString());
							int b = (int) d;
							String s=Integer.toString(b);
							driver.findElement(By.xpath("//input[@name='NoOfSemester']")).sendKeys(s);
							System.out.print(cell2+ " "+ j);
						}
						if(j==11) {
							System.out.print(cell2+ " "+ j);
							//span[text()='Currency']
							driver.findElement(By.xpath("//span[text()='Currency']")).click();
							Thread.sleep(2000);
							driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) ='"+cell2.toString().trim()+"']")).click();
							
						}
						if(j==12) {
							//Level of education
							System.out.print(cell2+ " "+ j);
							driver.findElement(By.xpath("//span[text()='Level of Education']")).click();
							Thread.sleep(2000);
							driver.findElement(By.xpath("//span[contains(@class, 'mat-option-text') and normalize-space(text()) =\""+cell2.toString()+"\"]")).click();
							
						}
						if(j==13) {
							//input[@name='AverageProcessingDay']
							driver.findElement(By.xpath("//input[@name='AverageProcessingDay']")).sendKeys(cell2.toString().trim());
							
						}
						if(j==20) {
							//input[@name="OfferLetterTAT"]
							driver.findElement(By.xpath("//input[@name='OfferLetterTAT']")).sendKeys(cell2.toString().trim());
						}
						if(j==23) {
							//check box
							//if(cell2.toString().equals("Available")) {
							if(cell2.getCellType().toString()!= "BLANK") {
								driver.findElement(By.xpath("(//div[@class='mat-checkbox-inner-container'])[1]")).click();
							}
						}
						if(j==24) {
							if(cell2.getCellType().toString()!= "BLANK") {
								driver.findElement(By.xpath("(//div[@class='mat-checkbox-inner-container'])[2]")).click();
							}
						}
						if(j==25) {
							if(cell2.getCellType().toString()!= "BLANK") {
								driver.findElement(By.xpath("(//div[@class='mat-checkbox-inner-container'])[3]")).click();
							}
						}
						if(j==26) {
							if(cell2.getCellType().toString()!= "BLANK") {
								driver.findElement(By.xpath("(//div[@class='mat-checkbox-inner-container'])[4]")).click();
							}
						}
						if(j==34) {
							//link
							driver.findElement(By.xpath("//input[@name='ProgramLink']")).sendKeys(cell2.toString().trim());
						}
						if(j==36) {
							//search keywords
							driver.findElement(By.xpath("//input[@name='Keyword']")).sendKeys(cell2.toString().trim());
							//scroll down
							
							Element = driver.findElement(By.xpath("(//span[text()='Cancel'])[2]"));
							jse.executeScript("arguments[0].scrollIntoView(true);", Element);
							Thread.sleep(1000);
							
						}
						
						
					}// if blank
					
					
					
				}//j  cell2.getCellType().toString() == "STRING" && 
				//if(cell2 != null && i < 4)
				//if(cell2 != null && i < 4)
				
					//   (//span[text()='Cancel'])[2]
				
					//driver.findElement(By.xpath("//mat-icon[text()='add']")).click();
				if(cell2 != null && cell2.getCellType().toString() != "BLANK")
				{
					
				}
				
					
				cell2 = null;
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
