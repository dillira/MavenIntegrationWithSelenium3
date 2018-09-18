package com.hasbrop2m.security;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hasbrop2m.core.SeleniumDriver;
import com.hasbrop2m.mostCommonFunctions.CommonFunctions;
import com.hasbrop2m.mostCommonFunctions.CommonProjectFunctions;
import com.hasbrop2m.core.ErrorUtil;
import com.hasbrop2m.core.Utility;

/**
 * @author anjali Gupta
 *
 */

public class SourcingConfig extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;

	public static By selectSource= By.id("sourcingConfigId");
	public static By ActionDropdown= By.id("prodseasonActions");
	public static By detailsTablink= By.xpath("//a[contains(text(),'Details')]");
	public static By detailPageSeasonDD= By.id("splId");
	public static By season= By.id("splId");
	public static By productLifecycleState= By.xpath("//td[contains(text(),'Product Lifecycle State')]//following::td[1]");
	public static By name= By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By headingcreateNewSourcingConfig= By.xpath("//td[contains(text(),'Create New Sourcing Configuration')]");
	public static By supplier = By.xpath("//a[contains(text(),'Supplier:')]");
	public static By factory = By.xpath("//a[contains(text(),'Factory:')]"); 
	public static By search= By.id("SearchButton1");
	public static By choose = By.xpath("//a[contains(text(),'choose')]");
	public static By CreateSourcebtn = By.xpath("//a[contains(text(),'Create')]");

	//Read Attributes:	
	public static By sourcingTablink= By.xpath("//a[contains(text(),'Sourcing')]");
	public static By labelGeneralAttribute = By.xpath("//td[contains(text(),'General Attributes')]");
	public static By labelGlobalSourcing = By.xpath("//td[contains(text(),'Global Sourcing')]");
	public static By sourcingAction= By.id("sourcingActions");
	public static By sourcingDetails= By.xpath("//td[contains(text(),'Sourcing Details:')]");
	public static By actionDD= By.id("prodseasonActions");
	public static By btnSave= By.xpath("//a[text()='Save']");
	public static By btnCancel= By.xpath("//a[text()='Cancel']");

	//Sourcing Lifecycle State:
	// Xpath for Source lifecycle Stste on Product Details Page
	public static By RO_UpdateLifecycleState= By.xpath("//td[contains(text(),'Source Lifecycle State')]//following::td[1]");
	//Xpath for Set Lifecycle State Page Header
	public static By setLifecycleState= By.xpath("//td[contains(text(),'Set Lifecycle State')]");
	//Xpath for Update button on Set life cycle state page
	public static By linkUpdate= By.xpath("//a[text()='Update']");
	//Xpath for dropdown on Set life cycle state page
	public static By editable_UpdateLifecycleState= By.id("lcstate");
	public static By editable_Status= By.xpath("//td[contains(text(),'Sourcing Status')]//following::select[1]");
	public static By ddStrategicSC= By.xpath("//td[contains(text(),'Strategic Sourcing Category')]//following::select[1]");
	public static By ddStrategicSSubCat= By.xpath("//td[contains(text(),'Strategic Sourcing Sub Category')]//following::select[1]");
	public static By roStrategicSourcingCategory= By.id("hbStrategicSourcingCategory");
	public static By roStrategicSourcingSubCategory= By.id("hbStrategicSourcingSubCategory");

	public static By scApprovers= By.name("Sourcing Config Approvers");
	public static By lblManager= By.xpath("//td[contains(text(),'Manager')]");
	public static By lblSourcingLead = By.xpath("//td[contains(text(),'Sourcing Lead')]");
	public static By lblSourcingHead = By.xpath("//td[contains(text(),'Sourcing Head')]");
	public static By lblManagement = By.xpath("//td[contains(text(),'Management')]");
	public static By ddManager = By.xpath("//td[contains(text(),'Manager')]//following::select[1]");
	public static By roManager = By.xpath("//td[contains(text(),'Manager')]//following::a[1]");
	public static By processHistory = By.xpath("//td[contains(text(),'Process History')]");
	public static By sourcingComments  = By.xpath("//td[contains(text(),'Sourcing Comments:')]");
	public static By expandComment = By.xpath("//img[contains(@src,'collapse_tree.png')]");
	public static By taComment = By.xpath("//td[contains(text(),'Post New Comment:')]//following::textarea[1]");
	public static By CommentsEdit = By.xpath("	//td[contains(text(),'Sourcing Comments:')]//following::a[text()='Edit'][1]");
	public static By statusErrorMsg = By.xpath("//td[contains(text(),'Sorry, You do not have the necessary privileges to update the Status field.')]");
	
	static String errMsgStatus="Sorry, You do not have the necessary privileges to update the Status field." ;
	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	//static String valULCSAfterChange;
	static String inwork ="In Work";
	static String strSource;
	static String initial_LifecycleState;
	static String strIRSource;
	static String strApprSource;
	static String  strRejSource;
	static String strCreate;
	static String strAppForBiddSource;
	static int i;
	static String strTestCaseName = null;


	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSourcingConfig(String[] data) throws Exception{
		try{
			count++;
			log.info(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for  Sourcing Configuration Security");
			strTestCaseName = "User:"+data[0] + " for testcase:"+data[3];	
			// User Name, Password and Action
			log.info("col0 :" + data[0]); 
			log.info("col1 :" + data[3]);
			log.info("col4 :" + data[4]);
			//	driver.manage().timeouts().pageLoadTimeout(myAutomationWait, TimeUnit.SECONDS);
			if(flaglogin==true)
			{
				if(!loginVal.equalsIgnoreCase(data[0])){
					y=0;
					flaglogin=false;
					CommonProjectFunctions.logOut();
					//driver.quit();
					
				}
			}
			if(runmodes[count].equalsIgnoreCase("y")){
				if(y==0){
					openBrowser();
					launchApp(data[0],data[1]);
					y++;
					log.info("y: "+y);
					loginVal=data[0];
					flaglogin=true;
				}
			}

			switch (data[3]) {
			/******************/
			case "CreateInReview":
				log.info("In side :-> " + data[3]);	
				createSCInReview(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateApproved":
				log.info("In side :-> " + data[3]);	
				createSCApproved(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateRejected":
				log.info("In side :-> " + data[3]);	
				createSCRejeted(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateApprovedforBidding":
				log.info("In side :-> " + data[3]);	
				createApprovedforBidding(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Create":
				log.info("In side :-> " + data[3]);	
				create_SourcingConfig(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "SCReadView":
				log.info("In side :-> " + data[3]);	
				verifySCReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "SCUpdate":
				log.info("In side :-> " + data[3]);	
				verifySCUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Delete":
				log.info("In side :-> " + data[3]);	
				delete_SourcingConfig(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "InReviewRead_View":
				log.info("In side :-> " + data[3]);	
				verifyInReviewRead_View(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "InReviewUpdate":
				log.info("In side :-> " + data[3]);	
				verifyInReviewUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "ApprovedforBiddingRead_View":
				log.info("In side :-> " + data[3]);	
				verifyApprovedforBiddingRead_View(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "ApprovedforBiddingUpdate":
				log.info("In side :-> " + data[3]);	
				verifyApprovedforBiddingUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "ApprovedRead_View":
				log.info("In side :-> " + data[3]);	
				verifyApprovedRead_View(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "ApprovedUpdate":
				log.info("In side :-> " + data[3]);	
				verifyApprovedUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "RejectedRead_View":
				log.info("In side :-> " + data[3]);	
				verifyRejectedRead_View(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "RejectedUpdate":
				log.info("In side :-> " + data[3]);	
				verifyRejectedUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "GeneralAttirbutesRead_View":
				log.info("In side :-> " + data[3]);	
				verifyGeneralAttributesReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "GeneralAttirbutesUpdate":
				log.info("In side :-> " + data[3]);	
				verifyGeneralAttributesUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "GlobalSourcingRead_View":
				log.info("In side :-> " + data[3]);	
				verifyGlobalSourcingReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "GlobalSourcingUpdate":
				log.info("In side :-> " + data[3]);	
				verifyGlobalSourcingUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "SCARead_View":
				log.info("In side :-> " + data[3]);	
				verifySCARead_View(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "SCAUpdate":
				log.info("In side :-> " + data[3]);	
				verifySCAUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "SourcingCommentsRead_View":
				log.info("In side :-> " + data[3]);	
				verifySourcingCommentsRead_View(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "SourcingCommentsUpdate":
				log.info("In side :-> " + data[3]);	
				verifySourcingCommentsUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PHisRead_View":
				log.info("In side :-> " + data[3]);	
				verifyPHisRead_View(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PHisUpdate":
				log.info("In side :-> " + data[3]);	
				verifyPHisUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			default:
				fail=true;
				log.info("Default is executed");
			}
		}catch(Throwable t){
			fail=true;
			log.info("****Varification failed or Skipped for**** "+strTestCaseName);
			ErrorUtil.addVerificationFailure(t);
		}	
	}


	public static String create_SourcingConfig(String[] data) throws Exception{
		try{
			log.info("Code entered for Creating Sourcing Configuration.......");
			nevigateSourcingTab(data);

			if(data[4].equalsIgnoreCase("Yes")) { 
				AddSourcingConfiguration(data);
				i=countOptionValue();
				CommonFunctions.selectFromDropDownByIndex(selectSource, i);
				strCreate=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}else if(data[4].equalsIgnoreCase("No")){
				//	CommonFunctions.selectFromDropDownByIndex(selectSource, 1);
				CommonFunctions.waitForPageLoaded();	
				String strPrimary =driver.findElement(ActionDropdown).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("With user :"+data[0]+" can not create source");
			}
			else{
				log.info("Create Sourcing config is not applicale");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_SourcingConfig()", e);
			throw e;
		}
		return strCreate;
	}

	public static String createSCInReview(String[] data) throws Exception{
		try{
			log.info("Code entered for Creating Sourcing Configuration.......");
			nevigateSourcingTab(data);
			AddSourcingConfiguration(data);
			i=countOptionValue();
			CommonFunctions.selectFromDropDownByIndex(selectSource, i);
			strIRSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			//Apply Action
			CommonFunctions.selectFromDropDownByVisibleText(sourcingAction,"Update Source");
			CommonFunctions.waitForElementTobeClickable(editable_Status);
			CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "In-Review");
			//Click on Update
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
		}catch(Exception e){
			fail=true;
			log.error("Exception in createSCInReview()", e);
			throw e;
		}
		return strIRSource;
	}

	public static int countOptionValue() throws Exception{
		try{
			Select se = new Select(driver.findElement(selectSource));
			List<WebElement> ec = se.getOptions();
			System.out.println("Number of items: " + ec.size());
			i=ec.size()-2;
			System.out.println(i);
		}catch(Exception e){
			fail=true;
			log.error("Exception in countOptionValue()", e);
			throw e;
		}
		return i;
	}

	public static String createSCApproved(String[] data) throws Exception{
		try{
			log.info("Code entered for Creating Sourcing Configuration.......");
			nevigateSourcingTab(data);
			AddSourcingConfiguration(data);
			i=countOptionValue();
			CommonFunctions.selectFromDropDownByIndex(selectSource, i);
			strApprSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			//Apply Action
			CommonFunctions.selectFromDropDownByVisibleText(sourcingAction,"Update Source");
			CommonFunctions.waitForElementTobeClickable(editable_Status);
			CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "Approved");
			//Click on Update
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_SourcingConfig()", e);
			throw e;
		}
		return strApprSource;
	}

	public static String createSCRejeted(String[] data) throws Exception{
		try{
			log.info("Code entered for Creating Sourcing Configuration.......");
			nevigateSourcingTab(data);
			AddSourcingConfiguration(data);
			i=countOptionValue();
			CommonFunctions.selectFromDropDownByIndex(selectSource, i);
			strRejSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			//Apply Action
			CommonFunctions.selectFromDropDownByVisibleText(sourcingAction,"Update Source");
			CommonFunctions.waitForElementTobeClickable(editable_Status);
			CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "Rejected");
			//Click on Update
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_SourcingConfig()", e);
			throw e;
		}
		return strRejSource;
	}
	
	public static String createApprovedforBidding(String[] data) throws Exception{
		try{
			log.info("Code entered for Creating Sourcing Configuration.......");
			nevigateSourcingTab(data);
			AddSourcingConfiguration(data);
			i=countOptionValue();
			CommonFunctions.selectFromDropDownByIndex(selectSource, i);
			strAppForBiddSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			//Apply Action
			CommonFunctions.selectFromDropDownByVisibleText(sourcingAction,"Update Source");
			CommonFunctions.waitForElementTobeClickable(editable_Status);
			CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "Approved for Bidding");
			//Click on Update
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_SourcingConfig()", e);
			throw e;
		}
		return strAppForBiddSource;
	}

	public static boolean verifySCReadView(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.waitForElementTobeClickable(selectSource);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource, strApprSource);

			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				String GALabel=driver.findElement(labelGeneralAttribute).getText();
				log.info(" General Attributes Value is    :"+GALabel);
				Assert.assertEquals(GALabel.trim(), "General Attributes:");
				log.info("General Attributes label is Present");
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					fail=true;
					log.error("General Attirbutes label is Present");
				}else{
					log.info("General Attirbutes label is Absent");
				}
			}
			else
			{
				log.info("For this General Attributes is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySCReadView()", e);
			throw e;
		}
		return true;
	}


	//Function consist scenario : General Attributes://Update
	public static boolean verifySCUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.waitForElementTobeClickable(selectSource);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			if(data[4].equalsIgnoreCase("Yes")) {
				log.info("Code is in side Update General Attribute !!");
				initial_LifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
				updateStatus("In-Review", data);
				Thread.sleep(1000);
				//Verification
				String updated_LifecysleState = driver.findElement(RO_UpdateLifecycleState).getText();
				log.info("Initial State of Source   :"+initial_LifecycleState);
				log.info("Updated State of Source   :"+updated_LifecysleState);
				//	log.info("State return from method  :"+valULCSAfterChange);
				Assert.assertNotEquals(updated_LifecysleState, initial_LifecycleState);
				log.info("General Attribute is Updatable .....");
				//updateStatus("Approved", data);
				//Reverting back
				revertStatus("Approved",data,strApprSource);
				//updateStatus("Approved",data);
			}
			else if(data[4].equalsIgnoreCase("No")){
				String strPrimary =driver.findElement(sourcingAction).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("General Attribute is Not Updatable for source !!!");
			}	

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySCUpdate()", e);
			throw e;
		}
		return true;
	}




	public static String sourcing_selectUpdateLifecycleState(String[] data) throws Exception {
		try{
			valULCS = new Select(driver.findElement(editable_Status)).getFirstSelectedOption().getText();
			if(valULCS.contains("Open")){
				CommonFunctions.selectFromDropDownByVisibleText(editable_Status,"Confirmed");
			}
			else if(valULCS.contains("Confirmed")){
				CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "In-Review");
			}
			else if(valULCS.contains("In-Review")){
				CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "Rejected");
			}
			else if(valULCS.contains("Rejected")){
				CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "Open");
			}
			else if(valULCS.contains("Approved")){
				CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "In-Review");
			}
			//valULCSAfterChange = new Select(driver.findElement(editable_Status)).getFirstSelectedOption().getText();
			//log.info("valULCS: "+valULCSAfterChange);
			//	}
		}catch(Exception e){
			fail=true;
			log.error("Exception in sourcing_selectUpdateLifecycleState()", e);
			throw e;
		}
		//return valULCSAfterChange;
		return "";
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static boolean delete_SourcingConfig(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			if(!data[11].equalsIgnoreCase("vendor")) {
				CommonFunctions.selectFromDropDownByVisibleText(selectSource, strCreate);
			}
			else {
				CommonFunctions.selectFromDropDownByVisibleText(selectSource, strApprSource);	
			}
			log.info("Code is in side---------Delete");
			if(data[4].equalsIgnoreCase("Yes")){
				CommonFunctions.selectFromDropDownByVisibleText(sourcingAction,data[8]);
				log.info("about to delete the sourcing Config-----");
				Thread.sleep(1000);
				//Accept AletPopup
				String msg= driver.switchTo().alert().getText(); 
				log.info("msg is -------"+msg);
				driver.switchTo().alert().dismiss();
				Assert.assertEquals(msg, "Are you sure you want to delete the Sourcing Configuration?");
				log.info("User is having Delete Access ..");
			}

			else if(data[4].equalsIgnoreCase("No")){
				String strPrimary =driver.findElement(actionDD).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("*****User is not having Delete Access !!****");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in delete_SourcingConfig()", e);
			throw e;
		}
		return true;
	}

	public static boolean verifyInReviewRead_View(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			if(!data[11].equalsIgnoreCase("vendor")) {
				CommonFunctions.selectFromDropDownByVisibleText(selectSource,strIRSource);
				if(data[4].equalsIgnoreCase("Yes")){//Read_View
					if(driver.findElements(labelGeneralAttribute).size() != 0){
						log.info("Code is in side---------");
						String GALabel=driver.findElement(labelGeneralAttribute).getText();
						log.info(" General Attributes Value is    :"+GALabel);
						Assert.assertEquals(GALabel.trim(), "General Attributes:");
						log.info("General Attributes label is Present");
					}else{
						fail=true;
						log.error("General Attributes label is Absent");
					}
				}
				else if(data[4].equalsIgnoreCase("No")){
					if(driver.findElements(labelGeneralAttribute).size() != 0){
						fail=true;
						log.error("General Attirbutes label is Present");
					}else{
						log.info("General Attirbutes label is Absent");
					}
				}
			}
			else {
				//Verification : For vendor user inReview source is not coming
				Select select = new Select(driver.findElement(selectSource));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strIRSource,options);
				Assert.assertFalse(bVal);
				log.info("Verification : For vendor user inReview source is not displayed");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyInReviewRead_View()", e);
			throw e;
		}
		return true;
	}


	//Function consist scenario : General Attributes://Update
	public static boolean verifyInReviewUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			if(!data[11].equalsIgnoreCase("vendor")) {
				CommonFunctions.selectFromDropDownByVisibleText(selectSource,strIRSource);

				if(data[4].equalsIgnoreCase("Yes")) {
					log.info("We are in process to Update Sourcing Configuration General Attribute if Product Lifecycle State is In Work");
					log.info("Code is in side Update General Attribute !!");
					initial_LifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
					//Select Source from drop down as "002 : Source1"

					//String valULCSAfterChange=sourcing_selectUpdateLifecycleState(data);
					updateStatus("Rejected",data);
					verifyErrorMessageStatus();
					//***Below lines commented out as functionality is not clear. Send mail to Nalin for the same.****/
					/**********************************************************/
					/*//Verification
					String updated_LifecysleState = driver.findElement(RO_UpdateLifecycleState).getText();
					log.info("Initial State of Source   :"+initial_LifecycleState);
					log.info("Updated State of Source   :"+updated_LifecysleState);
					//log.info("State return from method  :"+valULCSAfterChange);
					Assert.assertEquals(updated_LifecysleState, "Rejected");
					log.info("General Attribute is Updatable .....");
					//Reverting back
					updateStatus("In-Review",data);*/
				}
				else if(data[4].equalsIgnoreCase("No")){
					Select select = new Select(driver.findElement(sourcingAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[8],options);
					Assert.assertFalse(bVal);
					log.info("Not Updatable when source is in Review state !!!");
				}
			}
			else {
				//Verification : For vendor user inReview source is not coming
				Select select = new Select(driver.findElement(selectSource));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strIRSource,options);
				Assert.assertFalse(bVal);
				log.info("Verification : For vendor user inReview source is not displayed");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyInReviewUpdate()", e);
			throw e;
		}
		return true;
	}
	

	public static boolean verifyApprovedforBiddingRead_View(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			if(!data[11].equalsIgnoreCase("vendor")) {
				CommonFunctions.selectFromDropDownByVisibleText(selectSource,strAppForBiddSource);
				if(data[4].equalsIgnoreCase("Yes")){//Read_View
					if(driver.findElements(labelGeneralAttribute).size() != 0){
						log.info("Code is in side---------");
						String GALabel=driver.findElement(labelGeneralAttribute).getText();
						log.info(" General Attributes Value is    :"+GALabel);
						Assert.assertEquals(GALabel.trim(), "General Attributes:");
						log.info("General Attributes label is Present");
					}else{
						fail=true;
						log.error("General Attributes label is Absent");
					}
				}
				else if(data[4].equalsIgnoreCase("No")){
					if(driver.findElements(labelGeneralAttribute).size() != 0){
						fail=true;
						log.error("General Attirbutes label is Present");
					}else{
						log.info("General Attirbutes label is Absent");
					}
				}
			}
			else {
				//Verification : For vendor user inReview source is not coming
				Select select = new Select(driver.findElement(selectSource));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strAppForBiddSource,options);
				Assert.assertFalse(bVal);
				log.info("Verification : For vendor user inReview source is not displayed");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyInReviewRead_View()", e);
			throw e;
		}
		return true;
	}

	//Function consist scenario : General Attributes://Update
	public static boolean verifyApprovedforBiddingUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			if(!data[11].equalsIgnoreCase("vendor")) {
				CommonFunctions.selectFromDropDownByVisibleText(selectSource,strAppForBiddSource);

				if(data[4].equalsIgnoreCase("Yes")) {
					log.info("We are in process to Update Sourcing Configuration General Attribute if Product Lifecycle State is In Work");
					log.info("Code is in side Update General Attribute !!");
					initial_LifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
					//Select Source from drop down as "002 : Source1"

					//String valULCSAfterChange=sourcing_selectUpdateLifecycleState(data);
					updateStatus("In-Review",data);

					//Verification
					String updated_LifecysleState = driver.findElement(RO_UpdateLifecycleState).getText();
					log.info("Initial State of Source   :"+initial_LifecycleState);
					log.info("Updated State of Source   :"+updated_LifecysleState);
					//log.info("State return from method  :"+valULCSAfterChange);
					Assert.assertEquals(updated_LifecysleState, "In-Review");
					log.info("General Attribute is Updatable .....");
					//Reverting back
					updateStatus("Approved for Bidding",data);
				}
				else if(data[4].equalsIgnoreCase("No")){
					Select select = new Select(driver.findElement(sourcingAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[8],options);
					Assert.assertFalse(bVal);
					log.info("Not Updatable when source is in Review state !!!");
				}
			}
			else {
				//Verification : For vendor user inReview source is not coming
				Select select = new Select(driver.findElement(selectSource));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strAppForBiddSource,options);
				Assert.assertFalse(bVal);
				log.info("Verification : For vendor user inReview source is not displayed");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyInReviewUpdate()", e);
			throw e;
		}
		return true;
	}

	public static boolean updateStatus(String state,String[] data) throws Exception{
		try{
			CommonFunctions.selectFromDropDownByVisibleText(sourcingAction, data[8]);
			CommonFunctions.selectFromDropDownByVisibleText(editable_Status, state);
			Thread.sleep(1000);
			//Click on Update
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateStatus()", e);
			throw e;
		}
		return true;
	}


	public static boolean verifyApprovedRead_View(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					log.info("Code is in side---------");
					String GALabel=driver.findElement(labelGeneralAttribute).getText();
					log.info(" General Attributes Value is    :"+GALabel);
					Assert.assertEquals(GALabel.trim(), "General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					fail=true;
					log.error("General Attributes label is Absent");
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					fail=true;
					log.error("General Attirbutes label is Present");
				}else{
					log.info("General Attirbutes label is Absent");
				}
			}
			else
			{
				log.info("For this General Attributes is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyApprovedRead_View()", e);
			throw e;
		}
		return true;
	}


	//Function consist scenario : General Attributes://Update
	public static boolean verifyApprovedUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			if(data[4].equalsIgnoreCase("Yes")) {

				log.info("Code is in side Update General Attribute !!");
				initial_LifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
				updateStatus("Rejected",data);
				//Verification
				String updated_LifecysleState = driver.findElement(RO_UpdateLifecycleState).getText();
				log.info("Initial State of Source   :"+initial_LifecycleState);
				log.info("Updated State of Source   :"+updated_LifecysleState);
				Assert.assertEquals(updated_LifecysleState, "Rejected");
				log.info("General Attribute is Updatable .....");
				updateStatus("Approved",data);
			}
			else if(data[4].equalsIgnoreCase("No")){

				String strPrimary =driver.findElement(sourcingAction).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("Not Updatable when source is in Approved state !!!");
			}	
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyApprovedUpdate()", e);
			throw e;
		}
		return true;
	}

	public static boolean verifyRejectedRead_View(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			if(!data[11].equalsIgnoreCase("vendor")) {
				CommonFunctions.selectFromDropDownByVisibleText(selectSource,strRejSource);
				if(data[4].equalsIgnoreCase("Yes")){//Read_View
					if(driver.findElements(labelGeneralAttribute).size() != 0){
						log.info("Code is in side---------");
						String GALabel=driver.findElement(labelGeneralAttribute).getText();
						log.info(" General Attributes Value is    :"+GALabel);
						Assert.assertEquals(GALabel.trim(), "General Attributes:");
						log.info("General Attributes label is Present");
					}else{
						fail=true;
						log.error("General Attributes label is Absent");
					}
				}
				else if(data[4].equalsIgnoreCase("No")){
					if(driver.findElements(labelGeneralAttribute).size() != 0){
						fail=true;
						log.error("General Attirbutes label is Present");
					}else{
						log.info("General Attirbutes label is Absent");
					}
				}
			}
			else {
				//Verification : For vendor user inReview source is not coming
				Select select = new Select(driver.findElement(selectSource));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strRejSource,options);
				Assert.assertFalse(bVal);
				log.info("Verification : For vendor user Rejected source is not displayed");
			}	
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyRejectedRead_View()", e);
			throw e;
		}
		return true;
	}


	//Function consist scenario : General Attributes://Update
	public static boolean verifyRejectedUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			if(!data[11].equalsIgnoreCase("vendor")) {
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strRejSource);
			if(data[4].equalsIgnoreCase("Yes")) {// && plcState.equalsIgnoreCase("In Work") || plcState.equalsIgnoreCase("Under Review")){//Update
				log.info("Code is in side Update General Attribute !!");
				initial_LifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
				updateStatus("Approved",data);
				//Verification
				String updated_LifecysleState = driver.findElement(RO_UpdateLifecycleState).getText();
				log.info("Initial State of Source   :"+initial_LifecycleState);
				log.info("Updated State of Source   :"+updated_LifecysleState);
				Assert.assertEquals(updated_LifecysleState,"Approved");
				log.info("General Attribute is Updatable .....");
				updateStatus("Rejected",data);
			}
			else if(data[4].equalsIgnoreCase("No")){

				String strPrimary =driver.findElement(sourcingAction).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("General Attribute is Not Updatable for rejected status source !!!");
			}	
			}
			else {
				//Verification : For vendor user inReview source is not coming
				Select select = new Select(driver.findElement(selectSource));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strRejSource,options);
				Assert.assertFalse(bVal);
				log.info("Verification : For vendor user Rejected source is not displayed");
			}	
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyRejectedUpdate()", e);
			throw e;
		}
		return true;
	}
	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					log.info("Code is in side---------");
					String GALabel=driver.findElement(labelGeneralAttribute).getText();
					log.info(" General Attributes Value is    :"+GALabel);
					Assert.assertEquals(GALabel.trim(), "General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					fail=true;
					log.error("General Attributes label is Absent");
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					fail=true;
					log.error("General Attirbutes label is Present");
				}else{
					log.info("General Attirbutes label is Absent");
				}
			}
			else
			{
				log.info("For this General Attributes is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGeneralAttributesReadView()", e);
			throw e;
		}
		return true;
	}


	//Function consist scenario : General Attributes://Update
	public static boolean verifyGeneralAttributesUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			if(data[4].equalsIgnoreCase("Yes")) {// && plcState.equalsIgnoreCase("In Work") || plcState.equalsIgnoreCase("Under Review")){//Update

				log.info("Code is in side Update General Attribute !!");
				initial_LifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
				log.info("Befor update staus is: " +initial_LifecycleState );
				updateStatus("Rejected",data);
				//Verification
				String updated_LifecysleState = driver.findElement(RO_UpdateLifecycleState).getText();
				//log.info("Initial State of Source   :"+initial_LifecycleState);
				log.info("Updated State of Source   :"+updated_LifecysleState);
				//log.info("State return from method  :"+valULCSAfterChange);
				CommonFunctions.waitForPageLoaded();	
				Assert.assertNotEquals(updated_LifecysleState,initial_LifecycleState);
				log.info("General Attribute is Updatable .....");
				revertStatus("Approved",data,strApprSource);

			}
			else if(data[4].equalsIgnoreCase("No")){

				String strPrimary =driver.findElement(sourcingAction).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("General Attribute is Not Updatable !!!");
			}	
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			throw e;
		}
		return true;
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Global Sourcing:Read_View
	public static boolean verifyGlobalSourcingReadView(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelGlobalSourcing).size() != 0){
					log.info("Code inside ---- GlobalSourcingRead_View---");
					String GSLabel=driver.findElement(labelGlobalSourcing).getText();
					log.info(" Global Sourcing: Attribute label Value is  :"+GSLabel);
					Assert.assertEquals(GSLabel.trim(), "Global Sourcing:");
					log.info("Global Sourcing: label is Present");
				}else{
					fail=true;
					log.error("Global Sourcing: label is Absent");
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGlobalSourcing).size() != 0){
					fail=true;
					log.error("Global Sourcing: label is Present");
				}else{
					log.info("Global Sourcing: label is Absent");
				}
			}
			else
			{
				log.info("For this Global Sourcing: is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGlobalSourcingReadView()", e);
			throw e;
			}
		return true;
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Global Sourcings://Update
	public static boolean verifyGlobalSourcingUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			CommonFunctions.waitForPageLoaded();
			if(data[4].equalsIgnoreCase("Yes")) {
				log.info("Code is in side---------verifyGlobalSourcingUpdate");
				//Click on Action as 'Update Source To Season'
				CommonFunctions.selectFromDropDownByVisibleText(sourcingAction, data[8]);
				//Update Strategic Sourcing Category
				CommonFunctions.selectFromDropDownByVisibleText(ddStrategicSC, data[13]);
				// Update Strategic Sourcing Sub Category 	
				CommonFunctions.selectFromDropDownByVisibleText(ddStrategicSSubCat,data[14]);
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
				CommonFunctions.waitForVisibilityOfElement(roStrategicSourcingCategory);
				Assert.assertEquals(driver.findElement(roStrategicSourcingCategory).getText().trim(), data[13]);
				log.info("Value matched for Strategic Sourcing Category: "+data[13]);
				Assert.assertEquals(driver.findElement(roStrategicSourcingSubCategory).getText().trim(), data[14]);
				log.info("Value matched for Strategic Sourcing Sub Category: "+data[14]);
				log.info("Global Sourcing is Updatable .....");
			}
			else if(data[4].equalsIgnoreCase("No")){
				String strPrimary =driver.findElement(sourcingAction).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				if(val) {
					CommonFunctions.selectFromDropDownByVisibleText(sourcingAction,"Update Source To Season");
					Assert.assertEquals(driver.findElements(ddStrategicSC).size(), 0);
					log.info("Strategic Sourcing Category  is not editable");
					Assert.assertEquals(driver.findElements(ddStrategicSSubCat).size(), 0);
					log.info("Strategic Sourcing Category  is not editable");
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				}
				else{
					Assert.assertFalse(val);
					log.info("Global Sourcing is Not Updatable !!!!");
				}
			}	
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGlobalSourcingUpdate()", e);
			throw e;
		}
		return true;
	}

	public static boolean verifySCARead_View(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				Assert.assertEquals(driver.findElements(scApprovers).size(), 1);
				log.info("Sourcing Config Approvers label is present");
				Assert.assertEquals(driver.findElements(lblManager).size(), 1);
				log.info("Manager label is present under Sourcing Config Approvers heading ");
				Assert.assertEquals(driver.findElements(lblSourcingLead).size(), 1);
				log.info("SourcingLead label is present under Sourcing Config Approvers heading ");
				Assert.assertEquals(driver.findElements(lblSourcingHead).size(), 1);
				log.info("SourcingHead label is present under Sourcing Config Approvers heading ");
				Assert.assertEquals(driver.findElements(lblManagement).size(), 1);
				log.info("Management label is present under Sourcing Config Approvers heading ");
			}
			else if(data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(scApprovers).size(), 0);
				log.info("Sourcing Config Approvers label is not present");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySCARead_View()", e);
			throw e;
		}
		return true;
	}


	//Function consist scenario :  Sourcing Config Approvers://Update
	public static boolean verifySCAUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			if(data[4].equalsIgnoreCase("Yes")) {
				//Click on Action as 'Update Source'
				CommonFunctions.selectFromDropDownByVisibleText(sourcingAction, data[8]);
				CommonFunctions.selectFromDropDownByIndex(ddManager, 1);
				String strManager =new Select(driver.findElement(ddManager)).getFirstSelectedOption().getText();
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Button");
				Assert.assertEquals(driver.findElement(roManager).getText().trim(), strManager);
				log.info(" Sourcing Config Approvers is updatable");
			}
			else if(data[4].equalsIgnoreCase("No")){
				String strPrimary =driver.findElement(sourcingAction).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("Sourcing Config Approvers section is Not Updatable !!!!");
			}	

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySCAUpdate()", e);
			throw e;
		}
		return true;
	}
	
	public static boolean verifySourcingCommentsRead_View(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				//int i=1;
				Assert.assertEquals(driver.findElement(sourcingComments).getText().trim(), "Sourcing Comments:");
				log.info("Sourcing comments section exist");
				//Click on expend comment
				CommonFunctions.clickButtonOrLink(expandComment, "plus sign", "Comment");
				//enter comment in testarea
				CommonFunctions.enterTextInTextboxUpdated(taComment, data[12], "Comment");
				//Click on post
				CommonFunctions.clickButtonOrLink(CostsheetExternalProduct.btnPost, "btn", "Post");
				CommonFunctions.waitForPageLoaded();
			//	i=i+1;
				System.out.println(driver.findElements(By.xpath("//table[contains(@id,'TBLT')]/tbody//td[text()='"+data[12]+"']")).size());
				//Verification
				Assert.assertEquals(driver.findElements(By.xpath("//table[contains(@id,'TBLT')]/tbody//td[text()='"+data[12]+"']")).size(),1);
				log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" :updation of comment");
			}
			else if(data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(taComment).size(),0);
				log.info("Verified(" +strTestCaseName+ ") :"+data[13] +"Enter Comment link is not present");
				Assert.assertEquals(driver.findElements(CommentsEdit).size(),0);
				log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" Comment Edit link is not present");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySCARead_View()", e);
			throw e;
		}
		return true;
	}


	//Function consist scenario :  Sourcing Config Approvers://Update
	public static boolean verifySourcingCommentsUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			if(data[4].equalsIgnoreCase("Yes")) {
				Assert.assertEquals(driver.findElement(sourcingComments).getText().trim(), "Sourcing Comments:");
				log.info("Sourcing comments section exist");
				System.out.println(driver.findElements(expandComment).size());
				if(driver.findElements(taComment).size()==0) {
				//Click on expend comment
				CommonFunctions.clickButtonOrLink(expandComment, "plus sign", "Comment");
				}
				//enter comment in testarea
				CommonFunctions.enterTextInTextboxUpdated(taComment, data[12], "Comment");
				//Click on post
				CommonFunctions.clickButtonOrLink(CostsheetExternalProduct.btnPost, "btn", "Post");
				CommonFunctions.waitForPageLoaded();
				System.out.println(driver.findElements(By.xpath("//table[contains(@id,'TBLT')]/tbody//td[text()='"+data[12]+"']")).size());
				//Verification
				Assert.assertEquals(driver.findElements(By.xpath("//table[contains(@id,'TBLT')]/tbody//td[text()='"+data[12]+"']")).size(),1);
				log.info("****Verified(" +strTestCaseName+ ") :updation of comment");
			}
			else if(data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(taComment).size(),0);
				log.info("Verified(" +strTestCaseName+ ") :"+data[13] +"Enter Comment link is not present");
				Assert.assertEquals(driver.findElements(CommentsEdit).size(),0);
				log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" Comment Edit link is not present");
			}	

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySCAUpdate()", e);
			throw e;
		}
		return true;
	}

	public static boolean verifyPHisRead_View(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				Assert.assertEquals(driver.findElements(processHistory).size(), 1);
				log.info("Process Histrory section is present");
			}
			else if(data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(processHistory).size(), 0);
				log.info("Process Histrory section is not present");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyPHisRead_View()", e);
			throw e;
		}
		return true;
	}


	//Function consist scenario : General Attributes://Update
	public static boolean verifyPHisUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);

			if(data[4].equalsIgnoreCase("Yes")) {
				log.info("Yes scenario is not applicable for Process history update");
			}
			else if(data[4].equalsIgnoreCase("No")){

				String strPrimary =driver.findElement(sourcingAction).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				if(val) {
					CommonFunctions.selectFromDropDownByVisibleText(sourcingAction,"Update Source");
					Assert.assertEquals(driver.findElements(processHistory).size(), 0);
					log.info("Process Histrory section is not present for update");
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				}
				else{
					Assert.assertFalse(val);
					log.info("Process History section is Not Updatable !!!!");
				}
			}	
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyPHisUpdate()", e);
			throw e;
		}
		return true;
	}

	public static Boolean nevigateSourcingTab(String[] data) throws Exception{
		try{
			if(driver.findElements(sourcingDetails).size()==0) {
				//Search for Assortment / Solid Product Type
				CommonProjectFunctions.searchProduct(data[5]);
				SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");	
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[9]);
			}
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in nevigateSourcingTab()", e);
			throw e;
		}
	}

	public static boolean AddSourcingConfiguration(String[] data) throws Exception{
		try{
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");	
			CommonFunctions.selectFromDropDownByVisibleText(actionDD, "Add Sourcing Configuration");

			//Supplier Selection
			CommonFunctions.clickButtonOrLink(SourcingConfig.supplier, "link", "supplier");
			//CommonFunctions.switchToChildWindow();
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[7]+"')]/preceding::td[1]/a"), "Supplier selected");
			driver.switchTo().window(parent);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Sourcing Lead 
			CommonFunctions.selectFromDropDownByIndex(CostsheetExternalProduct.sourcingLead, 1);
			//*Sourcing Head 
			CommonFunctions.selectFromDropDownByIndex(CostsheetExternalProduct.sourcingHead, 1);
			// Management 
			//	CommonFunctions.selectFromDropDownByVisibleText(management, data[26]);
			//click on Create
			CommonFunctions.clickButtonOrLink(SourcingConfig.CreateSourcebtn, "btn", "Create Source");
			CommonFunctions.handleAlertPopUp();
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in AddSourcingConfiguration()", e);
			throw e;
		}
	}

	public static boolean findString (String data1, String regex){
		log.info("Calling Find String");
		boolean flag = false;
		try {
			Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(data1);
			if (matcher.find())
			{
				flag = true;
				log.info("Start index: " + matcher.start());
			}
		} catch (Exception e) {
			fail=true;
			// TODO Auto-generated catch block
			log.error("Error in Handling Findstring  :"+e);
			throw e;
		}
		return flag;
	}

	public static boolean revertStatus(String statusBOM,String[] data,String sourceName) throws Exception{
		try{
			reLogin();	
			nevigateSourcingTab(data);
			//Select BOM
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,sourceName);
			// Update Status
			updateStatus("Approved",data);
			//Log Out
			CommonProjectFunctions.logOut();
			//Launch App with required login\Pwd
			openBrowser();
			Thread.sleep(3000);
			launchApp(data[0],data[1]);	
			return true;	
		}catch(Exception e){
			fail=true;
			log.error("Exception in revertStatus()", e);
			throw e;
		}
	}

	public static boolean reLogin() throws Exception{
		try{
			prop=new Properties();
			FileInputStream ip1=new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\config.properties");
			prop.load(ip1);
			//Logout
			CommonProjectFunctions.logOut();
			//Login with Admin user : taking admin username\password from property file
			openBrowser();
			Thread.sleep(3000);
			launchApp(prop.getProperty("adminuser"),prop.getProperty("adminpwd"));	
			return true;	
		}catch(Exception e){
			fail=true;
			log.error("Exception in reLoin()", e);
			throw e;
		}
	}
	
	public static boolean verifyErrorMessageStatus() throws Exception{
		try {
			Assert.assertEquals(driver.findElement(statusErrorMsg).getText().trim(),errMsgStatus);
			log.info("Verified error message as : " +errMsgStatus +"for" + CommonProjectFunctions.strTestCaseName);
			CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyErrorMessageStatus()", e);
			throw e;
		}
	}


	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteSecurityXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteSecurityXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteSecurityXls, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
	}
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteSecurityXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		runmodes=Utility.getDataSetRunmodeTest(suiteSecurityXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestPass){
			Utility.dataSetResult(suiteSecurityXls,"Testcases", Utility.getRowNum(suiteSecurityXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteSecurityXls,"Testcases", Utility.getRowNum(suiteSecurityXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteSecurityXls, this.getClass().getSimpleName());
	}

}
