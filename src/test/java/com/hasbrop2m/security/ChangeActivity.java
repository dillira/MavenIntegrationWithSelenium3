package com.hasbrop2m.security;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

import com.hasbrop2m.mostCommonFunctions.CommonFunctions;
import com.hasbrop2m.mostCommonFunctions.CommonProjectFunctions;
import com.hasbrop2m.core.ErrorUtil;
import com.hasbrop2m.core.Utility;

/**
 * @author Govind Pandey
 * 
 * We need to run create test cases with admin user everytime we are doing partial run which would less than 20 user. As everyuser who dont have create acces of Retail change activity
 *  will browse to Retail change activity created by admin user
 * Column 9 is created in excel sheet so as to understand that user is vendor or non-vendor. For vendor user they are not able to see Retail change activity
 * Column 11 is created to mention the update in season attribute what we will be doing while perform General attribute table update
 *
 */
public class ChangeActivity extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	static String changeActivityName;

	//Variable required before Create Page
	public static By libraryLink = By.id("librariesContentIcon");
	public static By rCALink = By.linkText("Retail Change Activity");
	public static By rCAHeadning = By.xpath("//span[contains(text(),'Find: Retail Change Activity')]");
	public static By newLink = By.linkText("New");
	public static By newChangeActHeading = By.xpath("//td[contains(text(),'Create New Change Activity')]");
	public static By rCAName= By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By seasonCreated= By.xpath("//td[contains(text(),'*Season (Year) Created')]//following::select[1]");
	public static By seasonFirst= By.xpath("//td[contains(text(),'Seasons (Years) Impacted')]//following::option[1]");
	public static By seasonSecond= By.xpath("//td[contains(text(),'Seasons (Years) Impacted')]//following::option[2]");
	public static By seasonADD= By.xpath("//*[@class='LABEL']/a[text()='Add']");
	public static By crateRCA= By.xpath("//a[text()='Create']");
	public static By changeActDetails = By.xpath("//td[contains(text(),'Change Activity Details:')]");
	public static By rCAAction = By.xpath("//select[contains(@onchange,'evalList(this)')]");
	public static By changeState = By.xpath("//select[contains(@onchange,'evalList(this)')]//following::option[contains(text(),'Change State:  Retail Change Activity')]");
	public static By inputBoxName = By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By btnSearch = By.id("SearchButton2");
	public static By rCAIdentification = By.xpath("//td[contains(text(),'Change Activity Details:')]");
	public static By labelGeneralAttribute = By.xpath("//td[contains(text(),'General Attributes')]");
	public static By labelChangeActivity = By.xpath("//td[contains(text(),'Change Activity Identification')]");
	public static By ro_RCAUpdateLifecycleState = By.xpath("//td[contains(text(),'Lifecycle State')]//following::td[1]");
	public static By deleteObject= By.xpath("//td[contains(text(),'Delete Object')]");
	public static By deleteButton= By.xpath("//a[text()='Delete']");
	public static By linkUpdate= By.xpath("//a[text()='Update']");
	public static By caName= By.xpath("//td[contains(text(),'Retail Change Activity')]//preceding::td[contains(text(),'Name')]//following::td[1]");
	public static By site = By.xpath(".//*[@id='siteNavLink']/a");
	public static By rfq = By.linkText("RFQ");
	public static By deleteSucessful = By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td");
	public static By actionsmenu = By.xpath("//td[contains(text(),'Retail Change Activity')]//preceding::select[1]");
	public static By updateHeading = By.xpath("//td[contains(text(),'Update Change Activity')]");
	public static By save = By.xpath("//a[contains(text(),'Save')]");
	public static By seasonCreatedInfo = By.xpath(".//*[@id='hbSeason']");
	public static By updatebutton = By.xpath("//td[contains(text(),'Retail Change Activity')]//preceding::select[1]//option[contains(text(),'Update')]");
	public static By changestateUpdate = By.xpath("//a[contains(text(),'Update')]");
	
	
	
	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcRCA(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Security Retail Change Activity");
			log.info("User Name     :" + data[0]);
			log.info("Att  Type     :" + data[2]);
			log.info("Verification  :" + data[3]);
			log.info("Action        :" + data[4]);
			if(flaglogin==true)
			{
				if(!loginVal.equalsIgnoreCase(data[0])){
					y=0;
					flaglogin=false;
					CommonProjectFunctions.logOut();
				//	driver.quit();
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

			//Create Retail Change Activity
			if(data[3].equalsIgnoreCase("Create"))
			{ create_RCA(data); }
			//SetState
			if(data[3].equalsIgnoreCase("SetState"))
			{ setState_RCA(data); }
			//Read view verification For General Attributes
			if(data[3].equalsIgnoreCase("GeneralAttirbutesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification For General Attributes
			if(data[3].equalsIgnoreCase("GeneralAttirbutesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }
			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_RCA(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	
	
	public static void vendorSearchRCA(){
	
	try{
	
		//switch to default frame
		driver.switchTo().defaultContent();
			
		// Switch to Sidebar Frame
		driver.switchTo().frame("sidebarframe");
			
		//Click on Site
		CommonFunctions.clickButtonOrLink(site, "Click on Site");
							
		//Click on library					
		CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
	
		//Verify that RFQ link present or not	
		if(driver.findElements(rfq).size()==0){
			//Click on library
			CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");						
			
			//Click on RFQ link
			CommonFunctions.clickButtonOrLink(rfq, " Click on RFQ");						
			}						
			else {							
				
			//Click on RFQ link
			CommonFunctions.clickButtonOrLink(rfq, " Click on RFQ");
				
			}
		
		//Retail Change Activity 
		Assert.assertEquals(driver.findElements(rCALink).size(), 0);
		log.info("Vendor User dont have access to create retail change activity");
			
	}
	catch(Exception e){
		fail=true;
		log.error("Exception in vendorSearchRCA()", e);
		
	}
	
		
	}
	
	public static void clickRetailChangeActivity(){
		try{
			//switch to default frame
			driver.switchTo().defaultContent();
			
			// Switch to Sidebar Frame
			driver.switchTo().frame("sidebarframe");
			
			//Click on Site
			CommonFunctions.clickButtonOrLink(site, "Click on Site");
			
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			
			//Verify if Retail Change Activity link present or not
			if(driver.findElements(rCALink).size()==0){
				//Click on library
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				
				//Click on Retail Change Activity
				CommonFunctions.clickButtonOrLink(rCALink, " Click on Retail Change Activity");
				
				}
				
				else {
				//Click on Retail Change Activity
				CommonFunctions.clickButtonOrLink(rCALink, " Click on Retail Change Acivity");
					
				}

			//Switch to default frame
			driver.switchTo().defaultContent();
			//Switch to contetnt frame
			driver.switchTo().frame("contentframe");
		}	
		catch(Exception e){
			fail=true;
			log.error("Exception in clickRetailChangeActivity()", e);
			
		}
		}
	
	public static void createRetailChangeActivity (String [] data) {
		
	try {
		//Click on Retail Change Activity
		clickRetailChangeActivity();
		
		//Click on new
		CommonFunctions.clickButtonOrLink(newLink, "link", "New Link");
		wait = new WebDriverWait(driver, 10);
		wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(newChangeActHeading));
		Date date = new Date();
		//Send Name in *Name Text Box
		CommonFunctions.enterTextInTextbox(rCAName,data[5]+date.getTime());
		//Select 2018 for  *Season (Year) Created
		CommonFunctions.selectFromDropDownByVisibleText(seasonCreated, data[6]);
		//Select 2017 for  *Seasons (Years) Impacted
		CommonFunctions.clickButtonOrLink(seasonFirst, "link", "season17 is Selected!!!");
		//Click on Add button to Add for MultiSelect
		CommonFunctions.clickButtonOrLink(seasonADD, "link", "Add is Click");
		//Select 2018 for  *Seasons (Years) Impacted
		CommonFunctions.clickButtonOrLink(seasonSecond, "link", "season18 is Selected!!!");
		//Click on Add button to Add for MultiSelect
		CommonFunctions.clickButtonOrLink(seasonADD, "link", "Add is Click");
		//click on Create Button
		CommonFunctions.clickButtonOrLink(crateRCA, "btn", "Create Retail Change Activity");
		wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(changeActDetails));
		log.info("User : "+data[0]+ "  have Access to Create Retail Change Activity, and Created Sucessfully ..");

	
	}
	catch(Exception e){
		fail=true;
		log.error("Exception in createRetailChangeActivity()", e);	
	}			
	}

	//Prerequisite: Create Product
	public static String create_RCA(String[] data) throws Exception{
		try{
				
				if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){
					
					//Click on Retail Change Activity
					clickRetailChangeActivity();
									
					//Click on new
					CommonFunctions.clickButtonOrLink(newLink, "link", "New Link");
					wait = new WebDriverWait(driver, 10);
					//Wait for Create New change activity page
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(newChangeActHeading));
					//Get the unique name
					Date date = new Date();
					//Send Name in *Name Text Box
					CommonFunctions.enterTextInTextbox(rCAName,data[5]+date.getTime());
					//Select 2018 for  *Season (Year) Created
					CommonFunctions.selectFromDropDownByVisibleText(seasonCreated, data[6]);
					//Select 2017 for  *Seasons (Years) Impacted
					CommonFunctions.clickButtonOrLink(seasonFirst, "link", "First season is Selected!!!");
					//Click on Add button to Add for MultiSelect
					CommonFunctions.clickButtonOrLink(seasonADD, "link", "Add is Click");
					//Select 2018 for  *Seasons (Years) Impacted
					CommonFunctions.clickButtonOrLink(seasonSecond, "link", "Second season is Selected!!!");
					//Click on Add button to Add for MultiSelect
					CommonFunctions.clickButtonOrLink(seasonADD, "link", "Add is Click");
					//click on Create Button
					CommonFunctions.clickButtonOrLink(crateRCA, "btn", "Create Retail Change Activity");
					//Wait for the detail page of Retail change activity got created
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(changeActDetails));
					log.info("User : "+data[0]+ "  have Access to Create Retail Change Activity, and Created Sucessfully ..");
					//Get the name of Change Activity
					changeActivityName = driver.findElement(caName).getText();
					
					log.info("The name of the Retail Change Activity which got created is:" + changeActivityName);
				}
				else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
					
					if(data[9].equalsIgnoreCase("Yes")){
					
					//search Retail Change Activity
					vendorSearchRCA();	
					}
					
					else {
						
					//Click on Retail Change Activity
					clickRetailChangeActivity();
					
					//Verify that New link is not present for creating Retail Chage Activity
					Assert.assertEquals(driver.findElements(newLink).size(), 0);
					
					//Assert.assertFalse(newType, "New Link not available");
					log.info("User : "+data[0]+ "  don't have Access to Create Retail Change Activity !!");
					
					}
				}
				}

		catch(Exception e){
			fail=true;
			log.error("Exception in create_RCA()", e);
			return "";
		}
		return changeActivityName;
	}


	public static boolean setState_RCA(String[] data) throws Exception{
		try{

			if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("Yes")){
					
					//Create Change Activity
					createRetailChangeActivity(data);
					
					//Current LifeCycle State 
					String initial_LifecycleState = driver.findElement(ro_RCAUpdateLifecycleState).getText();
					log.info("Initial LIfeCycle State of Material   :"+initial_LifecycleState);
;
					//Click on Actions Menu
					CommonFunctions.clickButtonOrLink(rCAAction, "Action Menu");
					
					//Click on Update
					CommonFunctions.clickButtonOrLink(changeState, "link", "change State");
					//Change state
					String valULCSAfterChange=selectUpdateLifecycleState(data);
					Thread.sleep(1000);
					
					//Click on Update button
					CommonFunctions.clickButtonOrLink(changestateUpdate, "Change State Update button");										
					
					//Verification
					String updated_LifecysleState = driver.findElement(ro_RCAUpdateLifecycleState).getText();
					log.info("Initial LIfeCycle State of Source   :"+initial_LifecycleState);
					log.info("Updated LIfeCycle State of Source   :"+updated_LifecysleState);
					log.info("Lifecycle State return from method  :"+valULCSAfterChange);
					//Verify that User has permission to change the state
					Assert.assertEquals(updated_LifecysleState, valULCSAfterChange);
					log.info("User : "+data[0]+ "  has the permission and able to change the Lifecycle State..");
				
				}
				else if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("No")){

					if(data[9].equalsIgnoreCase("Yes")){
					//Searcj Retail Change Activity link	
					vendorSearchRCA();	
					}					
					
					else {
					
					//search Change Activity
					searchRCA(data);
									
					//Current LifeCycle State 
					String initial_LS = driver.findElement(ro_RCAUpdateLifecycleState).getText();
					log.info("Initial LIfeCycle State of Material   :"+initial_LS);
					// Select Change State:  Sourcing Configuration from Action dropdown
					String str = driver.findElement(rCAAction).getText();
					//Click on Actions Menu
					CommonFunctions.clickButtonOrLink(rCAAction, "Action Menu");
					
					//Click on Update
					CommonFunctions.clickButtonOrLink(changeState, "link", "change State");
					//Change state
					String valULCSAfterChange=selectUpdateLifecycleState(data);
					Thread.sleep(1000);
					
					//Click on Update button
					CommonFunctions.clickButtonOrLink(changestateUpdate, "Change State Update button");										

					//Verification
					String updated_LifecysleState = driver.findElement(ro_RCAUpdateLifecycleState).getText();
					log.info("Initial LIfeCycle State of Source   :"+initial_LS);
					log.info("Updated LIfeCycle State of Source   :"+updated_LifecysleState);
					log.info("Lifecycle State return from method  :"+valULCSAfterChange);			
					//Verify that User don't have permission to update the state
					Assert.assertEquals(updated_LifecysleState, initial_LS);
					log.info("User : "+data[0]+ "  don't have the permission hence not able to change the Lifecycle State !!!");
				}
				}

		}catch(Exception e){
			fail=true;
			log.error("Exception in setState_RCA()", e);
			return false;
		}
		return true;
	}

	public static boolean delete_RCA(String[] data) throws Exception{
		try{
			if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
				
				//Create Change Activity
				createRetailChangeActivity(data);
				
				//Select Delete action
				CommonFunctions.selectFromDropDownByVisibleText(rCAAction, data[8]);
				
				//Wait for Delete Object Heading
				CommonFunctions.waitForVisibilityOfElement(deleteObject);
				
				//Click on delete button
				CommonFunctions.clickButtonOrLink(deleteButton,"btn", "Delete");
				
				//Accept AletPopup
				log.info("about to delete the Retail CHange Activity-----");
				Thread.sleep(1000);
				
				//Accept AletPopup
				String msg= driver.switchTo().alert().getText(); 
				System.out.println("Pop up message before delete: "+msg);
				
				//Click ok on pop up message
				driver.switchTo().alert().accept();
				
				//Verify that deletion message is correct
				Assert.assertEquals(msg, "This action will completely delete this object from the system.  Are you sure you want to do this?");
				
				//Click on Delete button
				CommonFunctions.waitForVisibilityOfElement(deleteSucessful);

				//Verify that Delete successful message appear
				String deleteMess =driver.findElement(deleteSucessful).getText();
				Assert.assertEquals(deleteMess,"Delete Successful");
				
				//Retail Change Activity get deleted
				log.info("Delete got deleted successfully");
				
				log.info("User : "+data[0]+ " has the permission to delete Retail Change Activity ...");

				}
				else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
					
					if(data[9].equalsIgnoreCase("Yes")){
						
					//Search Retail Change Activity link
					vendorSearchRCA();
					
					}					
					else {
						
					//Search existing retail change activity
					searchRCA(data);
					
					//Check that Deleted BOM is visible or not
					Select select = new Select(driver.findElement(rCAAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[8],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal);
					log.info("Does User is able to see Delete Spec option: " + bVal);
				}
				}

		}catch(Exception e){
			log.error("Exception in delete()", e);
			return false;
		}
		return true;
	}
	//This funcion is to select Update Lifecycle State	
	public static String selectUpdateLifecycleState(String[] productData) throws Exception{
		try{
			valULCS = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			//	System.out.println("valULCS: "+valULCS);
			if(valULCS.contains("In Work")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Under Review");
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Under Review");
			}
			else if(valULCS.contains("Rework")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Resolved");
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Resolved");
			}
			else if(valULCS.contains("Resolved")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Under Review");
				//CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Under Review");
			}
			else if(valULCS.contains("Under Review")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Resolved");
				//CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Resolved");
			}
			else if(valULCS.contains("Implementation")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Under Review");
				//CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Under Review");
			}
			valULCSAfterChange = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			log.error("Exception in selectUpdateLifecycleState()", e);
			//	return false;
		}
		return valULCSAfterChange;
	}


	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
				if(data[3].contains("GeneralAttirbutesRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
					
					//Search for existing Retail Change Activity
					searchRCA(data);
					//Verify that General Attribute table exist or not
					if(driver.findElements(labelGeneralAttribute).size() != 0){										
						log.info("Code is in side---------");
						String GALabel=driver.findElement(labelGeneralAttribute).getText();
						log.info(" General Attributes Value is :"+GALabel);
						Assert.assertEquals(GALabel.trim(), "General Attributes:");
						log.info("General Attributes label is Present");
					}else if(driver.findElements(labelGeneralAttribute).size()==0){
						fail=true;
						log.error("General Attributes label is Absent");
					}
				}
				else if(data[3].contains("GeneralAttirbutesRead_View")&& data[4].equalsIgnoreCase("No")){
					if(data[9].equalsIgnoreCase("Yes")){
						//Search Retail Change Activity
						vendorSearchRCA();
					}					
					
					else {
						
					//search for Retail Change activity	
					searchRCA(data);
					//Verify that General attribute label present or not
					if(driver.findElements(labelGeneralAttribute).size() != 0){
						fail=true;
						log.error("General Attirbutes label is Present");
					}else if (driver.findElements(labelGeneralAttribute).size() == 0){
						log.info("General Attirbutes label is Absent");
					}
					}
				}

		}catch(Exception e){
			log.error("Exception in verifyGeneralAttributesReadView()", e);
			return false;
		}
		return true;
	}


	//Function consist scenario : General Attributes://Update
	public static boolean verifyGeneralAttributesUpdate(String[] data) throws Exception{
		try{			
				if(data[3].contains("GeneralAttirbutesUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
					
					//Browse to existing Retail Change Activity
					searchRCA(data);					
					log.info("Code is in side Update General Attribute !!");
					//Click on Actions menu
					CommonFunctions.clickButtonOrLink(actionsmenu, "Click on Actions menu");
					
					//Click on Update button
					CommonFunctions.clickButtonOrLink(updatebutton, "click on Update button");
					
					//Identify the season attribute value
					Select selectSeason= new Select(driver.findElement(seasonCreated));
					String seasonName = selectSeason.getFirstSelectedOption().getText();										
					
					//Update season
					if (seasonName.equals(data[6])){
						CommonFunctions.enterTextInTextbox(seasonCreated, data[11]);
					}
					else{
						CommonFunctions.enterTextInTextbox(seasonCreated, data[6]);
					}
					
					//Capture the season attribute value
					String seasonUpdate= selectSeason.getFirstSelectedOption().getText();
					
					//Season updated in Update page
					log.info("Season updated to: " + seasonUpdate);
					
					//Click on Save button
					CommonFunctions.clickButtonOrLink(save, "click on Save button");
					
					//Wait for Change Activity Details page
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(changeActDetails));				
					
					//Get the text for Season created
					String seasondetailPage= driver.findElement(seasonCreatedInfo).getText();
					
					//Verify that season updated in edit page and detail page are same
					Assert.assertEquals(seasondetailPage, seasonUpdate);
					log.info("General Attribute is Updated:" + seasondetailPage );
				}
				
				else if(data[3].contains("GeneralAttirbutesUpdate")&& data[4].equalsIgnoreCase("No")){
					
					if(data[9].equalsIgnoreCase("Yes")){
					
					//Search for Retail Change Activity Link
					vendorSearchRCA();	
					}										
					else {
					
					//Browse to existing Retail Change Activity
					searchRCA(data);
					
					//Verify that Update action is not enable
					Select select = new Select(driver.findElement(actionsmenu));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[10],options);				

					//Verify that User is not able to see Update action
					Assert.assertFalse(bVal);
					log.info("User is able to see the Update action: " + bVal);
					
					}
				}	

		}catch(Exception e){
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			return false;
		}
		return true;
	}


	//Function consist scenario : searchMaterial
	public static boolean searchRCA(String[] data) throws Exception{
		try{
			
			if(driver.findElements(changeActDetails).size()==0){
			//Refresh the page
			driver.navigate().refresh();
			
			//Switch to default frame
			driver.switchTo().defaultContent();
			//Switch to side bar frame
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(libraryLink, "Link", "Library Link");
			//Click on Color link
			driver.findElement(rCALink).click();
			//Switch frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			wait = new WebDriverWait(driver, 10);
			//Wait for Find: Retail Change Activity
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(rCAHeadning));
			//Add name
			CommonFunctions.enterTextInTextbox(inputBoxName, changeActivityName);
			//Click on Search
			CommonFunctions.clickButtonOrLink(btnSearch, "Btn", "Search");
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(rCAIdentification));

				
			}
			else {
			
			log.info("User at the detail page of specification");	
				
			}
			

		}catch(Exception e){
			log.error("Exception in searchRCA()", e);
			return false;
		}
		return true;
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
