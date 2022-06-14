package com.msminstitution.endtoend;

import com.msminstitution.base.MSMBase;
import com.msminstitution.pom.CreateInstitution;
import com.msminstitution.pom.CreateProgram;
import com.msminstitution.pom.CreateCampus;
import com.msminstitution.pom.CreateInstApplicationFlow;
import com.msminstitution.pom.CreateInstDocument;
import com.msminstitution.pom.CreateInstIntake;
import com.msminstitution.pom.CreateInstRegionOfMarketing;
import com.msminstitution.pom.MSMLogin;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.msminstitution.base.MSMBase;
import com.msminstitution.pom.MSMLogin;
import org.openqa.selenium.WebDriver;

public class MSMEndToEnd extends MSMBase{
	
	@BeforeTest(alwaysRun = true)
	public void intibase() 
	{
		try 
		{
			MSMBase base = new MSMBase();
			base.initialization();
		} catch (Throwable e)
		{
			e.printStackTrace();
		}
	}
	
	
	 @Test(priority = 0)
	  public void MSMInstitutionCreationFields()
	  {
		  try 
		  {
			  MSMLogin login=new MSMLogin(driver);
			  login.LoginToMSMPortal();
			  
			  CreateInstitution cint=new CreateInstitution(driver);
			  cint.GetCreateInstitutionAndAddAllSubModuleData(); 
		  } 
		  catch (Exception e) 
		  {
			e.printStackTrace();
		  }
	  
	  }
	
	  
	 @Test(priority = 1)
	  public void MSMCampusCreationFields()
	  {
		  try 
		  {
			  //MSMLogin login=new MSMLogin(driver);
			  //login.LoginToMSMPortal();
			  
			  CreateCampus ccampnew = new CreateCampus(driver);
			  ccampnew.GetCreateCampusAndAddAllSubModuleData(); 
		  } 
		  catch (Exception e) 
		  {
			e.printStackTrace();
		  }
	  
	  }
	 
	 @Test(priority = 2)
	  public void CreateInstRegionOfMarketingFields()
	  {
		  try 
		  {
			  
			  CreateInstRegionOfMarketing ccampnew = new CreateInstRegionOfMarketing(driver);
			  ccampnew.GetCreateInstRegionOfMarketingModuleData(); 
		  } 
		  catch (Exception e) 
		  {
			e.printStackTrace();
		  }
	  
	  }
	  
	 
	 @Test(priority = 3)
	  public void CreateInstDocumentFlowFields()
	  {
		  try 
		  {
			 // MSMLogin login=new MSMLogin(driver);
			  //login.LoginToMSMPortal();
			  
			  CreateInstDocument ccampnew = new CreateInstDocument(driver);
			  ccampnew.GetCreateInstDocumentData(); 
		  } 
		  catch (Exception e) 
		  {
			e.printStackTrace();
		  }
	  
	  }
	
	 @Test(priority = 4)
	  public void CreateInstApplicationFlowFields()
	  {
		  try 
		  {
			  //MSMLogin login=new MSMLogin(driver);
			  //login.LoginToMSMPortal();
			  
			  CreateInstApplicationFlow ccampnew = new CreateInstApplicationFlow(driver);
			  ccampnew.GetCreateInstApplicationFlowData(); 
		  } 
		  catch (Exception e) 
		  {
			e.printStackTrace();
		  }
	  
	  }
	 
	
	 @Test(priority = 5)
	  public void CreateProgramFields()
	  {
		  try 
		  {
			 // MSMLogin login=new MSMLogin(driver);
			  //login.LoginToMSMPortal();
			  
			  CreateProgram ccampnew = new CreateProgram(driver);
			  ccampnew.GetCreateProgramFlowData(); 
		  } 
		  catch (Exception e) 
		  {
			e.printStackTrace();
		  }
	  
	  }
	  
	 @Test(priority = 6)
	  public void MSMInstitutionIntakeFields()
	  {
		  try 
		  {
			  //MSMLogin login=new MSMLogin(driver);
			  //login.LoginToMSMPortal();
			  
			  CreateInstIntake cint=new CreateInstIntake(driver);
			  cint.GetCreateInstitutionIntakeData(); 
		  } 
		  catch (Exception e) 
		  {
			e.printStackTrace();
		  }
	  
	  }
	  
	  
	
}
