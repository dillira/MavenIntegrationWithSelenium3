package com.hasbrop2m.sanity;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.hasbrop2m.mostCommonFunctions.CommonFunctions;
import com.hasbrop2m.mostCommonFunctions.CommonProjectFunctions;
import com.hasbrop2m.core.ErrorUtil;
import com.hasbrop2m.core.Utility;

public class SanitySuite5_Workflow2 extends TestsuiteBase{
	String runmodes[]=null;
	//static String AssortmentProductNumberForWorkFlow="E6883";
	static String csName = null;
	static String costSheetName;
	static int count=-1;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	String loginVal;
	Boolean flaglogin=false;
	int y=0;
	
	// Variables
	public static By lstCostSheetAction 				= By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]//select[@onchange='evalList(this)']");
	public static By lstCSStatus 						= By.xpath("//td[contains(text(),'Cost Sheet Status')]//following::select[1]");	 //*[@id='ptc_str_2']
	public static By btnSave 							= By.xpath("//a[text()='Save']");
	public static By MyWorktab                      	= By.xpath("//li[@id='siteNavLink']/a");
	public static By MyWorkPlusButton               	= By.xpath("//img[@id='myWorkContentIcon']");
	public static By LCSProductCostSheet            	= By.xpath("//div[@id='workListDiv']/div[2]/a");
	public static By LeadReviewerReviewCostSheet    	= By.xpath("//a[contains(text(),'Lead Reviewer: Review Cost Sheet')]");
	public static By SecondaryReviewerReviewCostSheet   = By.xpath("//a[contains(text(),'Secondary Reviewer: Review Cost Sheet')]");
	public static By selectApprovalType			    	= By.xpath("//a[contains(text(),'Select Approval Type')]");
	public static By setUpApprovers			    		= By.xpath("//a[contains(text(),'Setup Approvers and Approval Workflow')]");
	public static By completeBtn 						= By.xpath("//a[text()='Complete']");
	public static By tblCostSheetIdentification 		= By.xpath("//td[contains(text(),'Cost Sheet Identification')]");
	public static By SecondaryReviewerOption;
	public static By ApprovalTypeTask;
	public static By changeteamoption 					= By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]//select[@onchange='evalList(this)']/option[contains(text(),'Change Team:  CostSheet')]");
	
	public static By MyWorkFlowTask;
	public static By MyWorkFlowTaskCommentBox;
	public static By MyWorkFlowCheckBox;
	public static By T1DefinitionOption;
	public static By T1DefinitionOption2;
	public static By T1DefinitionOption3;
	public static By T1DefinitionOption4;
	public static By T1DefinitionOption5;
	public static By T1DefinitionOption6;
	public static By T3DefinitionOption;
	
	public static String WorkFlowTask;
	public static String getTextOfTheTask;
	public static String FirstWorkFlowReference;
	public static String myWorkFlowTaskCommentBox;
	public static String myWorkFlowCheckBox;
	public static String T1DefinitionOptionPath;
	public static String T1DefinitionOptionPath2;
	public static String T1DefinitionOptionPath3;
	public static String T1DefinitionOptionPath4;
	public static String T1DefinitionOptionPath5;
	public static String T1DefinitionOptionPath6;
	public static String T3DefinitionOptionPath;
	
	
	@Test(dataProvider="testDataTest")
	
	public void tcSCFunctional(String[] data) throws Exception{
		count++;
		System.out.println(runmodes[count]);
		if(!runmodes[count].equalsIgnoreCase("y")){
			skip=true;
			log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
			throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
		}
		try{
			log.debug("Inside testcase for Sanity suite 5 - Workflow");
			// User Name, Password and Action
			log.info("col0 :" + data[0]); 
			log.info("col1 :" + data[1]);
			log.info("col1 :" + data[2]);
			
			if(flaglogin==true)
			{
				if(!loginVal.equalsIgnoreCase(data[0])){
					y=0;
					flaglogin=false;
					CommonProjectFunctions.logOut();
					
				}
			}
			if(runmodes[count].equalsIgnoreCase("y")){
				if(y==0){
					
					openBrowser();
					launchApp(data[0],data[1]);
					y++;
					System.out.println("y: "+y);
					loginVal=data[0];
					flaglogin=true;
				}
			}
			switch (data[2]) {
			case "Pre-Requisite":
				log.info("In side :-> " + data[2]);
				WorkFlowPreRequisite.workFlow_PreRequisite_ForTwoRetailItems(data);			
			break;
			
			case "PC90 - Manually update the Status on a Vendor Product Cost Sheet to Ready for Review" :
				log.info("In side PC90 Workflow Test :-> " + data[2]);	
				PC90_ManuallyUpdateStatusOnVendorProductCSToRFR(data);
				log.info("Outside PC90 Workflow Test :-> " + data[2]);
			break;
			
			case "PC101 - 2.06 Secondary Reviewer Lead Reviewer":
				log.info("In side PC101 Workflow Test :-> " + data[2]);
				SanitySuite5_WorkFlow.PC101_SecondaryReviewerLeadReviewer(data);
				log.info("Out side PC101 Workflow Test :-> " + data[2]);
			break;
			
			case "PC103 - 4.01 Lead Reviewer Submit for Approval to Select Approval Type for Responsible Cost Engineer":
				log.info("In side PC101 Workflow Test :-> " + data[2]);
				PC103_LeadReviewerToSubmitForApprovalAndVerifyT3Definition(data);
				log.info("Out side PC101 Workflow Test :-> " + data[2]);
			break;
			
			case "PC107 - Lead Reviewer to Submit for Approval and Setup Approvers" :
				log.info("In side PC107:-> " + data[2]);	
				PC107_LeadReviewertoSubmitForApprovalandSetUpApprover(data);
				log.info("Out side PC107:-> " + data[2]);
				break;			
				
			case "PC120 - Validate Approvers Populated with Category Lead Workflow" :
				log.info("In side :-> " + data[2]);	
				PC120_ValidateApproversPopulatedwithCategoryLeadWorkflow(data);
				log.info("Out side :-> " + data[2]);
				break;
			
			case "PC114 - Happy Path 2-Level Approval with Cat Lead, FEP" : 
				 log.info("In side :->" + data[2]); 
				 //PC114_HappyPath2LevelApprovalwithCATLead(data);
				 log.info("Out side :->" + data[2]);
				break;
				
		    default:
			fail=true;
			log.info("Default is executed");
			
			
			}
		}
	   catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	//The script test whether the Lead and Secondary Reviewer are assigned correctly
	public static boolean PC90_ManuallyUpdateStatusOnVendorProductCSToRFR(String [] data) throws Exception
	{
		try {
			// Activate the Vendor Product Cost sheet 
			SanitySuite5_WorkFlow.PC86_ActivateVendorProductCostSheet(data);
			VerifySecondaryReviewerCSTask_T10Definition(data);
			//CommonProjectFunctions.searchProduct(WorkFlowPreRequisite.AssortmentProductNumberForWorkFlow);
			/*CommonProjectFunctions.searchProduct(AssortmentProductNumberForWorkFlow);
			
			CommonProjectFunctions.clickSourcingTab(data[3]);
			CommonProjectFunctions.clickCostingTab();
			costSheetName =AssortmentProductNumberForWorkFlow+data[94]+" "+data[28];
			CommonProjectFunctions.clickCostingTab();
			searchandClickforRequriedCostsheet();
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			// To update the Cost Sheet Status to Ready For Review
			updateCSStatus("Ready for Review");*/
			//verifyLeadandSecondaryReviewerReviewCSTask(data);
			
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in PC90_ManuallyUpdateStatusOnVendorProductCSToRFR"+ e);
			throw e;
		}
		return true;
	}

	public static boolean PC103_LeadReviewerToSubmitForApprovalAndVerifyT3Definition(String [] data) throws Exception
	{
		try {
			//opening the Browser
			openBrowser();			
			//Launching the URL with Responsible Costing Engineering user
			launchApp(data[102],data[103]);
			//Open My Work - Lead Reviewer Review CS Task		
			SanitySuite5_WorkFlow.OpenMyWork_LeadReviewerReviewCostSheet(data);
			//Find My Work Flow task and assertion Verification of Task Definition
			SanitySuite5_WorkFlow.FindWorkFlowTaskAndAssertionVerificationOfTask_T1_Definition(data);
			// Open My Work - Select Approval Type
			OpenMyWork(data,selectApprovalType);
			//Find Workflow task and assertion for T3 definition
			//FindWorkFlowTaskForSelectApprovalTypeAndAssertionVerificationOfTask_T3_Definition(data);
			//Find Approval Type Task and verify T3 Definition, do not complete task by passing second param as blank
			FindandCompleteWorkFlowTask(data,"");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in PC103_LeadReviewerToSubmitForApprovalAndVerifyT3Definition"+ e);
			throw e;
		}
		return true;
	}
	public static boolean PC107_LeadReviewertoSubmitForApprovalandSetUpApprover(String [] data)throws Exception
	{
		
		try {
			OpenMyWork(data,selectApprovalType);
			FindandCompleteWorkFlowTask(data,"ApprovalTypeTask");
			OpenMyWork(data,setUpApprovers);
			//VerifySetUpApprovers_T6Definition(data);
			 
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in PC107_LeadReviewertoSubmitForApprovalandSetUpApprover"+ e);
			throw e;
		}
		return true;
	}
	
	public static boolean PC120_ValidateApproversPopulatedwithCategoryLeadWorkflow(String [] data)throws Exception
	{
		
		try {
			OpenMyWork(data,selectApprovalType);
			FindandCompleteWorkFlowTask(data,"");
			changeTeamForCS(data);
			CommonProjectFunctions.searchProduct(WorkFlowPreRequisite.AssortmentProductNumberForWorkFlow);
			CommonProjectFunctions.clickSourcingTab(data[3]);
			
			
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in PC120_ValidateApproversPopulatedwithCategoryLeadWorkflow"+ e);
			throw e;
		}
		return true;
	}
	
	
	public static String searchandClickforRequriedCostsheet() throws Exception
	{
		
		try{
			int colIndex=1;
			List<WebElement> row1 = driver.findElements(By.xpath("//div[@id='costSheetResults']//div[3]/table/tbody/tr/td[2]"));
			
			for(WebElement e: row1){
				colIndex=colIndex+1;
				csName =e.getText().trim(); //.replaceAll("\\s+", " ");
				String csName1 =e.getText().trim().replaceAll("\\s+", " ");
				//System.out.println("current row CS): "+ csName);
				//System.out.println("expected CS: "+costSheetName);
				if (csName1.contains(costSheetName)) {
					Thread.sleep(2000);
					CommonFunctions.clickButtonOrLink(By.linkText(csName),"link","Cost sheet name");
					return csName;
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in searchandClickforRequriedCostsheet()", e);
			//return "";
			throw e;
		}
		return csName;
	}
	//Update cost Sheet Status to Ready For Review
		public static boolean updateCSStatus(String sStatus) throws Exception{
			try {
				CommonFunctions.waitForVisibilityOfElement(lstCostSheetAction);
				//Thread.sleep(3000);	
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
				CommonFunctions.waitForVisibilityOfElement(lstCSStatus);
				// update Status
				CommonFunctions.selectFromDropDownByVisibleText(lstCSStatus,sStatus);
				log.info("Updated status to: "+sStatus);
				CommonFunctions.waitForVisibilityOfElement(btnSave);
				// click on Save button
				CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");	
				return true;
			}catch(Exception e){
				fail=true;
				log.error("Exception in updateCSStatus()", e);
				//return false;
				throw e;
			}
		}
	
		public static boolean VerifySecondaryReviewerCSTask_T10Definition(String [] data)throws Exception{
			try {
				SanitySuite5_WorkFlow.OpenMyWork_SecondaryReviewerReviewCostSheet(data);
				//opening the Secondary Reviewer
				SanitySuite5_WorkFlow.FindWorkFlowTaskSecondTimeAndAssertionVerificationOf_T10_Definition(data);
				//Find My Work Flow task and assertion Verification of Task 10 Definition
				return true;
			}catch(Exception e) {
				fail=true;
				log.error("Exception in VerifySecondaryReviewerCSTask_T10Definition()", e);
				throw e;
			}
			
		}
		
		public static boolean FindWorkFlowTaskForSelectApprovalTypeAndAssertionVerificationOf_T3_Definition(String [] data)throws Exception{
			try {
				
				return true;
			}catch(Exception e) {
				throw e;
			}
			
		}
		
		public static boolean verifyLeadandSecondaryReviewerReviewCSTask(String [] data)throws Exception{
			try {
				//open task for Lead Reviewer
				OpenMyWork(data,LeadReviewerReviewCostSheet);
				FindandCompleteWorkFlowTask(data, "");
				//open task for Secondary Reviewer
				OpenMyWork(data,SecondaryReviewerReviewCostSheet);
				FindandCompleteWorkFlowTask(data,"SecondaryReviewer");
				return true;
			}catch(Exception e) {
				throw e;
			}
		}
	
	
		public static boolean OpenMyWork(String [] data, By ReviewCostSheet) throws Exception{
			try{
				driver.switchTo().defaultContent();
				driver.switchTo().frame("sidebarframe");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(MyWorktab);
				CommonFunctions.clickButtonOrLink(MyWorktab, "tab", "MyWorktab");
				//Clicking my work Tab
				CommonFunctions.waitForElementTobeClickable(MyWorkPlusButton);
				CommonFunctions.clickButtonOrLink(MyWorkPlusButton, "Image", "MyWorkPlusButton");
				//Clicking on my work Plus Button
				CommonFunctions.waitForElementTobeClickable(LCSProductCostSheet);
				CommonFunctions.clickButtonOrLink(LCSProductCostSheet, "Hyper_link", "LCSProductCostSheet");
				//Clicking LCS ProductCost Sheet
				wait.until(ExpectedConditions.visibilityOfElementLocated(ReviewCostSheet));
				CommonFunctions.clickButtonOrLink(ReviewCostSheet, "HyperLink", "LeadReviewerReviewCostSheet");
				//Clicking on  Lead reviewer Review Cost Sheet
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				wait.until(ExpectedConditions.titleIs(data[21]));
				CommonFunctions.waitForPageLoaded();
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in OpenMyWork"+e);
				throw e;
			}
			return true;
		}

		public static boolean FindandCompleteWorkFlowTask(String [] data, String toCompleteTask) throws Exception{
			try{
				
				WorkFlowTask =WorkFlowPreRequisite.AssortmentProductNumberForWorkFlow+data[94]+" "+data[28];
				//WorkFlowTask =AssortmentProductNumberForWorkFlow+data[94]+" "+data[28];
				for(int i=2;i<=100;i++){
				FirstWorkFlowReference="//a[text()='Complete']//following::a["+i+"]";
				MyWorkFlowTask=By.xpath(FirstWorkFlowReference);
				myWorkFlowTaskCommentBox="//a[text()='Complete']//following::a["+i+"]"+"//following::img[1]";
				MyWorkFlowTaskCommentBox= By.xpath(myWorkFlowTaskCommentBox);		
				myWorkFlowCheckBox="//a[text()='Complete']//following::a["+i+"]//preceding::input[1]";
				MyWorkFlowCheckBox=By.xpath(myWorkFlowCheckBox);
				i=i++;
				CommonFunctions.waitForElementTobeClickable(MyWorkFlowTask);
				getTextOfTheTask= driver.findElement(MyWorkFlowTask).getText();
				if(getTextOfTheTask.contains(WorkFlowTask)){
					SanitySuite1.GettingText(MyWorkFlowTask);
					log.info("Task Name is "+SanitySuite1.ActualValue);
					log.info("Work flow Task has been found");
					break;
					}}
					if(data[95].equals("T1_Definition")){
						for(int i=7,j=2,k=0;i<12;i++,k++){
							T1DefinitionOptionPath="//a[text()='Complete']//following::a[4]//following::tr["+i+"]/td["+j+"]";
							T1DefinitionOption=By.xpath(T1DefinitionOptionPath);
							SanitySuite1.GettingText(T1DefinitionOption);
							CommonFunctions.waitForElementTobeClickable(T1DefinitionOption);
							CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[96+k], "Actual and expected values of T1Definition values are Not matched.Assertion failed.Please check");
							//verification Of First Option					
					}	
					if(data[95].equals("T3_Definition")){
							for(int i=7,j=2,k=0;i<10;i++,k++){
								T3DefinitionOptionPath="//a[text()='Complete']//following::a[4]//following::tr["+i+"]/td["+j+"]";
								T3DefinitionOption=By.xpath(T1DefinitionOptionPath);
								SanitySuite1.GettingText(T3DefinitionOption);
								CommonFunctions.waitForElementTobeClickable(T3DefinitionOption);
								CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[96+k], "Actual and expected values of T3Definition values are Not matched.Assertion failed.Please check");
								//verification Of First Option					
						}	
				}
				if (toCompleteTask.equalsIgnoreCase("SecondaryReviewer")) {
				SecondaryReviewerOption=By.xpath("//a[contains(text(),'Secondary Reviewer')]");
				CommonFunctions.clickButtonOrLink(SecondaryReviewerOption, "btn", "SecondaryReviewerOptionButton");
				CommonFunctions.clickButtonOrLink(MyWorkFlowTaskCommentBox, "cell", "Workflow Comments");
				CommonFunctions.enterTextInTextbox(MyWorkFlowTaskCommentBox, data[6]);
				CommonFunctions.clickButtonOrLink(completeBtn, "btn", "SecondaryReviewerOptionButton");
				
				}
				
				if (toCompleteTask.equalsIgnoreCase("ApprovalTypeTask")) {
					ApprovalTypeTask=By.xpath("//a[contains(text(),'FEP Approved')]");
					CommonFunctions.clickButtonOrLink(ApprovalTypeTask, "btn", "FEPApprovedTypeOptionButton");
					CommonFunctions.clickButtonOrLink(MyWorkFlowTaskCommentBox, "cell", "Workflow Comments");
					CommonFunctions.enterTextInTextbox(MyWorkFlowTaskCommentBox, data[6]);
					CommonFunctions.clickButtonOrLink(completeBtn, "btn", "FEPApprovedTypeOptionButton");
					
					}
					}
			}
			
			catch(Exception e){
				fail=true;
				log.error("Exception in FindWorkFlowTask"+e);
				throw e;
			}
			return true;
		}
		
	
		public static boolean changeTeamForCS(String [] data)throws Exception {
			try {
				
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in changeTeamForCS"+e);
				throw e;
			}
			return true;
		}
	@BeforeMethod
	  public void Refresh(){
		  if(driver!=null && runmodes[count+1].equalsIgnoreCase("y")){
			  driver.navigate().refresh();
			  log.info("Driver is refreshed");
			}
		  else{
			  log.info("No Driver is Launched");
		  }
	  }


	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "SKIP");
		
		else if(fail||CommonFunctions.error){
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
	}
		else
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
		CommonFunctions.error=false;	
	}
	
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteSanityXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		runmodes=Utility.getDataSetRunmodeTest(suiteSanityXls, this.getClass().getSimpleName());
	}
	
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestPass){
			Utility.dataSetResult(suiteSanityXls,"Testcases", Utility.getRowNum(suiteSanityXls, this.getClass().getSimpleName()),"PASS");
			
		}else
			Utility.dataSetResult(suiteSanityXls,"Testcases", Utility.getRowNum(suiteSanityXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteSanityXls, this.getClass().getSimpleName());
	}


}
