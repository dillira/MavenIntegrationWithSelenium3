package com.hasbrop2m.views;
/**
 * @author Govind Pandey
 * 
 * Create Retail Item product and Assortment Product
 * Both the above product must have colorway, Funskool Source, specification created
 * In Excel Column 2 is created to mention that cost sheet type is Internal or Vendor
 * Ensure that in excel sheet Column 8 has "Hasbro Internal" source for Internal Cost Sheet and "002 : FUNSKOOL" source for vendor cost sheet
 * Since Product and Retail cost sheet differs with mandatory Wave attribute, so in excel column 14 is created to handle it.
 *
 */

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

import com.hasbrop2m.security.Colorway;
import com.hasbrop2m.security.CostsheetExternalProduct;
import com.hasbrop2m.security.CostsheetInternal;
import com.hasbrop2m.security.CostsheetTooling;
import com.hasbrop2m.security.InternalBOMSoftG;
import com.hasbrop2m.security.Material;
import com.hasbrop2m.security.Product;
import com.hasbrop2m.security.SourcingConfig;
import com.hasbrop2m.security.Specifications;
import com.hasbrop2m.security.Specifications_old;
import com.hasbrop2m.security.CostingApprovalWF;

import com.hasbrop2m.core.SeleniumDriver;
import com.hasbrop2m.mostCommonFunctions.CommonFunctions;
import com.hasbrop2m.mostCommonFunctions.CommonProjectFunctions;
import com.hasbrop2m.core.ErrorUtil;
import com.hasbrop2m.core.Utility;


public class CostSheetView extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	static String strCSInWork;
	static int cwi;
	static String strTestCaseName = null;
	public static By tblCostSheetIdentification = By.xpath("//td[contains(text(),'Cost Sheet Identification')]");

	
	static int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String csInWork;
	static String strViewOwner;
	static String strSpec;
	static String strCW;
	static String strCostSheetName;
	public static Boolean flagVal=true;
	static int i;
	
	public static By csHeading=By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]/td");
	public static By tabCostsheet =By.xpath("//a[text()='Cost Sheet List']");
	public static By selectSource= By.id("sourcingConfigId");
	public static By lstcostingActions = By.id("costingActions");	
	public static By lstcolorwayGroupOptions = By.id("colorwayGroupOptions");	
	public static By lnkAdd =  By.xpath("//a[text()='Add']");
	public static By lstWave = By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By lstQuoteCurrency = By.xpath("//td[contains(text(),'Quote Currency')]//following::select[1]");
	public static By btnSave =  By.xpath("//a[text()='Save']");  
	public static By viewIcon = By.xpath("//img[contains(@src,'view.png')]");
	public static By updateTableLayout = By.xpath("//img[contains(@src,'customize_tablebutton.gif')]");
	public static By viewOwner = By.xpath("//td[contains(text(),'View Owner')]//following::td[1]");
	public static By updateTableLayoutHidden = By.xpath("//div[@id='updateViewDiv' and @style='display: none;']//img[contains(@src,'customize_tablebutton.gif')]");
	public static By csCostSheetList = By.xpath("//a[@title='Cost Sheet List']");
	public static By csName = By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By imgClose =By.xpath("//img[contains(@src,'deleteXsmall.png')]");
	public static By viewupdatepage = By.xpath("//td[contains(text(),'Update Search Preference')]");
	
	public static By costSheetType                                = By.linkText("Internal");
	public static By costSheetTypeVendor                          = By.linkText("Vendor");
	public static By QuoteCurrencyDropDown                        = By.xpath("//select[@id='ptc_str_2']");
	public static By BOMDropDown                                  = By.xpath("//select[@id='ptc_str_20']");
	public static By Details                                      = By.linkText("Details");
	public static By csProductNumber                              = By.xpath("//td[contains(text(),'Product Number')]//following::a[1]");
	public static By SourceDropDown                               = By.xpath("//select[@id='sourcingConfigId']");
	public static By SpecificationsTabThroughSideBar              = By.linkText("Specifications");
	public static By ClickAgainProduct                            = By.xpath("//td[@id='contextBar']/table/tbody/tr/td[1]/a[2]");
	public static By CostingHyperLink                             = By.xpath("//a[text()='Costing']");
	public static By SourcingHyperLink                            = By.xpath("//a[text()='Sourcing']");
	public static By SortOption                                   = By.xpath("//a[contains(text(),'Hide/Show columns')]//following::a[1]");
	public static By CostingTabThroughSideBar                     = By.linkText("Costing");
	public static By CostingTab                                   = By.xpath("//a[contains(text(),'Costing')]");
	public static By SourcingTab                                  = By.xpath("//a[contains(text(),'Sourcing')]");
	public static By SeasonDropDown                               = By.xpath("//select[@id='splId']");
	public static By wave                                         = By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By ProductLink                                  = By.linkText("Product");
	public static By specAction                                   = By.xpath("//span[@class='PRIMARYINDICATOR']//following::select[1]");
	public static String strSource;
	public static By strShowAll                                   = By.xpath(".//*[@id='chooserResultsDiv']/table/tbody/tr[3]/td/span/a[3]");
	public static By sourcingLead                                 = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[1]");
	public static By sourcingHead                                 = By.xpath("//td[contains(text(),'Sourcing Head')]//following::select[1]");
	public static String prodNum1;
	public static String prodNum2;
	public	static String cstCreateCS;
	
	
	@Test(dataProvider="testDataTest")
	public void tcView(String[] data) throws Exception{
		
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			try{
			strTestCaseName = "User:"+data[0] + " for testcase:"+data[3]+" for Cost Type: "+data[2] ;			
			log.info("Inside Test Case:-> " + strTestCaseName);

			log.info("col0 :" + data[0]);
			log.info("col2 :" + data[2]);
			log.info("col3 :" + data[3]);
			if(flaglogin==true)
			{
				if(!loginVal.equalsIgnoreCase(data[0])){
					y=0;
					flaglogin=false;
					CommonProjectFunctions.logOut();
					driver.quit();
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

			switch (data[3]) {

			/**
			 * Internal Product CostSheet:Pavithra
			 * */
			
			case "CreateIntCostSheet_Prerequisites":
				log.info("In side :-> " + data[3]);	
				CreateIntCostSheet_Prerequisites(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Create Internal Assrt CS":
				log.info("In side :-> " + data[3]);	
				VerifyIntPrdCSView(data);
				log.info("Out side :-> " + data[3]);
				break;
				
				/**
				 * Vendor Product CostSheet:Pavithra
				 * */
			case "CreateVenCostSheet_Prerequisites":
				log.info("In side :-> " + data[3]);	
				CreateVenCostSheet_Prerequisites(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Create Vendor Assrt CS":
				log.info("In side :-> " + data[3]);	
				VerifyVenPrdCSView(data);
				log.info("Out side :-> " + data[3]);
				break;
				
				/**
				 * Internal Retail CostSheet:Pavithra
				 * */
				
				case "CreateIntRetailItemCostSheet_Prerequisites":
					log.info("In side :-> " + data[3]);	
					CreateIntRetCostSheet_Prerequisites(data);
					log.info("Out side :-> " + data[3]);
					break;
				case "Create Internal RI CS":
					log.info("In side :-> " + data[3]);	
					VerifyIntRetCSView(data);
					log.info("Out side :-> " + data[3]);
					break;
					
					/**
					 * Vendor Retail CostSheet:Pavithra
					 * */
				case "CreateVenRetCostSheet_Prerequisites":
					log.info("In side :-> " + data[3]);	
					CreateVenRetCostSheet_Prerequisites(data);
					log.info("Out side :-> " + data[3]);
					break;
				case "Create Vendor RI CS":
					log.info("In side :-> " + data[3]);	
					VerifyVenRetCSView(data);
					log.info("Out side :-> " + data[3]);
					break;
			default:
				fail=true;
				log.info("Default is executed");
			}
		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	/**
	 * Create cost sheet
	 */
	public static String CreateIntCostSheet_inwork(String[] data) throws Exception{
		try{
			//Create cost sheet
			createCS(data);
			//read newly created cost sheet
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			//Get the Name of cost sheet created
			strCSInWork = driver.findElement(CostsheetTooling.csHeading).getText().substring(20);
			//Remove the Action text from cost sheet name
			String []varCSInWork = strCSInWork.split("Actions:");
			//trim the name of the cost Sheet
			csInWork = varCSInWork[0].trim();
			log.info("Internal Retail Item/Product Cost Sheet in Inwork status is : "+csInWork);
			// close the open cost sheet
			closeTheOpenCostSheet();
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateIntCostSheet_inwork()", e);
			return "";
		}
		return csInWork;
	}
	
	public static boolean closeTheOpenCostSheet() throws Exception
	{
		try{
			//Wait for the visibility of cost sheet close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			// close the open cost sheet
			CommonFunctions.clickButtonOrLink(imgClose,"icon", "Close");
			log.info("Costsheet closed");
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in closeTheOpenCostSheet", e);
			return false;
		}
	}

	
	public static Boolean createCS(String[] data) throws Exception{
		try{
			
			String strPrdNo=driver.findElement(csProductNumber).getText();
			CommonProjectFunctions.searchProduct(strPrdNo);
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			
			//Click on Sourcing tab
			CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				
			//Wait for 	Costing Tab
			//wait.until(ExpectedConditions.visibilityOfElementLocated(CostingTab));
			CommonFunctions.waitForPageLoaded();
			//Click on Costing tab
			CommonFunctions.clickButtonOrLink(CostingTab, "link", "CostingTab");
			CommonFunctions.waitForPageLoaded();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
			CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
			CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
			CommonFunctions.waitForPageLoaded();
		 
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
			//Select Specification
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
			Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded();
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
			CommonFunctions.waitForPageLoaded();
		
		}catch(Exception e){
			fail=true;
			log.error("Exception in createCS()", e);
			return false;
		}
		return true;
	}
	
	
	
	public static boolean clickOnCostSheetListTab() throws Exception
	{
		try{
			//Click on Cost Sheet Tab
			driver.findElement(tabCostsheet).click();   
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in clickOnCostSheetListTab()", e);
			return false;
		}
	}
	
	public static String searchandClickforRequriedCostsheet(String costSheetName) throws Exception
	{
		try{
			int colIndex=1;
			//Get the list of cost sheets
			List<WebElement> col1 = driver.findElements(By.xpath("//div[@id='costSheetResults']//div[3]/table/tbody/tr/td[2]"));
			System.out.println(col1.size());
			//Get the name of the cost sheet from cost sheet list
			for(WebElement e: col1){
				colIndex=colIndex+1;
				System.out.println("e.getText(): "+ e.getText());
				System.out.println("Segment: "+costSheetName);
				//trim the name of the cost sheet and get th correct cost sheet which is matching
				if (e.getText().trim().equals(costSheetName)) {
					String strCostSheetName = e.getText().trim();
					driver.findElement(By.linkText(costSheetName)).click();
					return strCostSheetName;
				}
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in searchandClickforRequriedCostsheet()", e);
			return "";
		}
		return strCostSheetName;
	}
	
	public static void verifyBOMView(String[] data) throws Exception{
		try{
			
			//Browse to Costing
			CostsheetInternal.nevigationToCostsheet(data);
			
			if(data[3].equalsIgnoreCase("(-- None --)")){
				
				//Verify BOM VIew
				verifySequence(data);
				//Verify User group
				verifyUserGroup(data);				
			}
			
			else{
			
			//Select view type
			CommonFunctions.clickButtonOrLink(viewIcon, "icon", "View");
			CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[3]+"']"), "option", data[3]);
			
			//Click on update table layout icon
			CommonFunctions.clickButtonOrLink(updateTableLayout, "Icon", "update table layout");
			
			//switch to default frame
			driver.switchTo().defaultContent();
			
			//switch to content frame
			driver.switchTo().frame("contentframe");
			
			//Wait for View Update page
			CommonFunctions.waitForVisibilityOfElement(viewupdatepage);
			
			//Click on Cancel
			CommonFunctions.clickButtonOrLink(CostsheetExternalProduct.btnCancel, "btn", "Cancel");
			
			
			verifySequence(data);
			verifyUserGroup(data);
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in "+data[3], e);
		}
	}
	
	/*
	 * Below function verify Sequencing for View Type
	 */
	public static boolean verifySequence(String[] data) throws Exception{
		try {
			int i=39;
			int j=1;
			//Get the list of column header for selected view
			List<WebElement> col1 = driver.findElements(By.xpath("//a[contains(text(),'Hide/Show columns')]//following::table[4]/tbody/tr[1]/td/a[2]"));
			log.info(col1.size());
			//Match the view header in UI with the actual data from sheet
			for(WebElement e: col1){
				System.out.println("UI data: "+ e.getText());
				System.out.println("excel data: "+data[i]);
				if(data[i].equalsIgnoreCase("NA")){
					break;
				}
				//Verify that view in UI is matching with the actual data from sheet
				Assert.assertEquals(e.getText().trim(), data[i]);
				log.info("Col"+j+" :matched for "+ e.getText());
				i++;
				j++;
			}
			log.info("Sequence verified for : "+ data[2]+" for Cost Type: "+data[3]);
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySequence()", e);
			return false;
		}
		return true;
	}
	public static boolean verifyUserGroup(String[] data) throws Exception{
		try {
			if(!data[3].equalsIgnoreCase("(-- None --)")) {
			//Click on update table layout icon
			CommonFunctions.clickButtonOrLink(updateTableLayout, "Icon", "update table layout");
			//Get the name of the View Owner group
			strViewOwner = driver.findElement(viewOwner).getText();
			//Verify that the View Owner group in UI is matching with the actual
			Assert.assertEquals(strViewOwner, data[62]);
			log.info("Owner Group verified for : "+strTestCaseName);
			//Click on Cancel
			CommonFunctions.clickButtonOrLink(CostsheetExternalProduct.btnCancel, "btn", "Cancel");
			}
			else {
				//Verify that the Update Table Layout button is hidden 
				Assert.assertEquals(driver.findElements(updateTableLayoutHidden).size(),1);
				log.info("For None Update icon is not present");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyUserGroup()", e);
			return false;
		}
		return true;
	}
	//Navigate to Details Tab

	public static boolean NavigateToDetailsTab(String[] data) throws Exception{
		try{
			 //wait.until(ExpectedConditions.visibilityOfElementLocated(Details));
			 //CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(Details, "link", "Details");
			 //Click on Details Tab
			// if(wait.until(ExpectedConditions.titleIs(data[144]))||wait.until(ExpectedConditions.titleIs(data[222])))
			 //{
				 CommonFunctions.waitForPageLoaded();
				 log.info("Product Details Page Appears");
			// }
			
			
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in NavigateToDetailsTab"+e);
			throw e;
		}
		return true;
	}
	//Select Source and Season
	public static boolean ClickSeasonAndSource(String [] data) throws Exception{
		try{
			// wait.until(ExpectedConditions.visibilityOfElementLocated(Product.detailPageSeasonDD));
			 //CommonFunctions.waitForPageLoaded();
             //CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.detailPageSeasonDD, data[6],"Season year");
             //CommonFunctions.handleAlertPopUp1();
             //CommonFunctions.waitForPageLoaded();
           //select Source
				CommonFunctions.waitForElementTobeClickable(SourceDropDown);
				CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
             
          
    		    
             //}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in ClickSeasonAndSource"+e);
			throw e;
		}
		return true;
	}
	
	//Navigate to Specification Tab
	
	public static boolean navigateToSpecificationTabThroughSideBar(String [] data) throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
		    CommonFunctions.waitForElementTobeClickable(SpecificationsTabThroughSideBar);
		    CommonFunctions.clickButtonOrLink(SpecificationsTabThroughSideBar, "Hyper-Link", "SpecificationsTab");
		    driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			return true;
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in navigateToSpecificationTab()", e);
			throw e;
		}
	}
	
	
	//Add Specification
	public static String AddSpecification(String[] data) throws Exception{
		try{
			//Add Specification
		CommonFunctions.waitForElementTobeClickable(Specifications.specificationsTablink);
			CommonFunctions.clickButtonOrLink(Specifications.specificationsTablink, "link", "specifications tab");
            CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
			Select dropDownSpec = new Select(SeleniumDriver.driver.findElement(InternalBOMSoftG.selectSpecification));
			List<WebElement> elementCount = dropDownSpec.getOptions();
			int count =elementCount.size();
			if(count>=2)
			{
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
				wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.selectSpecification));
				wait.until(ExpectedConditions.titleIs(data[33]));
				CommonFunctions.waitForPageLoaded();
				//CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
				InternalBOMSoftG.strSpec=new Select(driver.findElement(InternalBOMSoftG.selectSpecification)).getFirstSelectedOption().getText();
			
			}
			else
			{
				InternalBOMSoftG.strSpec= CommonProjectFunctions.Create_Specifications(data[34],data[35]);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
				InternalBOMSoftG.strSpec=new Select(driver.findElement(InternalBOMSoftG.selectSpecification)).getFirstSelectedOption().getText();
			}
			log.info("Specification is: "+InternalBOMSoftG.strSpec);
		
			
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddSpecification", e);
			throw e;
		}
		
		return InternalBOMSoftG.strSpec;
	}
	
	public static boolean CreateMultiple_Colorway() throws Exception{
		try{
			//Click on Details tab
			//SeleniumDriver.driver.findElement(Colorway.Details).click();
			// Select season from DD
			CommonFunctions.selectFromDropDownByVisibleText(Colorway.actionDD,"Create Multiple Colorways");
			//Switch window
			Set set = SeleniumDriver.driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			SeleniumDriver.driver.switchTo().window(child);
			CommonFunctions.waitForElementTobeClickable(Colorway.btnSearch);
			//Click on search
			CommonFunctions.clickButtonOrLink(Colorway.btnSearch,"Btn","Search");
			for(int i=2;i<5;i++)
			{	
				System.out.println(i);
				System.out.println(By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[" + i + "]/td/input"));
				CommonFunctions.selectCheckbox(By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[" + i + "]/td/input"));	
			}
			//Click on select btn
			CommonFunctions.clickButtonOrLink(Colorway.selectBtn, "btn", "Select");
			SeleniumDriver.driver.switchTo().window(parent);
			SeleniumDriver.driver.switchTo().frame("contentframe");
			Select select = new Select(SeleniumDriver.driver.findElement(Colorway.colorwayDropDown));
			List<WebElement> options = select.getOptions();
			if(options.size()>=3)
				SeleniumDriver.log.info("***Verification : Colorway created and Added in dropdown***");
			else
				Assert.fail();
		}catch(Exception e){ 
			//fail=true;
			log.error("Exception in CreateColorway()", e);
			throw e;
		}
		return true;
	}
	
	//Navigate to Costing Tab
	public static boolean navigateToCostingTabThroughSideBar(String [] data) throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
		    CommonFunctions.waitForElementTobeClickable(CostingTabThroughSideBar);
		    CommonFunctions.clickButtonOrLink(CostingTabThroughSideBar, "Hyper-Link", "CostingTab");
		    driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			return true;
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in navigateToCostingTabThroughSideBar()", e);
			throw e;
		}
	}
	//Update Source Status
			public static boolean updateSourceStatus(String state) throws Exception{
				try{
					CommonFunctions.selectFromDropDownByVisibleText(SourcingConfig.editable_Status,state);
				}catch(Exception e){
					fail=true;
					log.error("Exception in updateBOMStatus()", e);
					return false;
				}
				return true;
			}
	//Update Source
			public static boolean updateSource(String[] data,String status) throws Exception{
				try{
					if(driver.findElements(SourcingConfig.sourcingDetails).size()== 0) {
						CommonProjectFunctions.clickSourcingTab(data[6]);
						//Select Source
						//select Source
						CommonFunctions.waitForElementTobeClickable(SourceDropDown);
						CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
					}
					//Apply Action
					CommonFunctions.selectFromDropDownByVisibleText(SourcingConfig.sourcingAction,"Update Source");
					//String valULCSAfterChange=sourcing_selectUpdateLifecycleState(data);
					Thread.sleep(1000);
					updateSourceStatus(status);
					//Click on Update
					CommonFunctions.clickButtonOrLink(SourcingConfig.btnSave, "btn", "Save");

				}catch(Exception e){
					fail=true;
					log.error("Exception in updateSource()", e);
					return false;
				}
				return true;
			}
			
			//Update specification to 'Supplier released' state
			public static boolean updateSpecStatus(String[] data) throws Exception{
				try{
					//Select 'Update Spec' from Action dropdown
					CommonFunctions.selectFromDropDownByVisibleText(specAction,"Update Spec");
					//Update Status
					CommonFunctions.selectFromDropDownByVisibleText(Specifications_old.specificationstatusinput,"Supplier Released");
					//Click on Save
					CommonFunctions.clickButtonOrLink(Specifications_old.updatespecSave, "Save button");
				}catch(Exception e){
					fail=true;
					log.error("Exception in updateBOMStatus()", e);
					return false;
				}
				return true;
			}
	/**
	 * Create Assortment Product
	 */
	
	public static String CreateInternalAssrtProduct(String[] data) throws Exception{
	try{
			
			//******Create Product : Assortment********
		//********Create Product : Retail Item*********
		CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
		data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
		data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
			//Navigate to Details Tab
			NavigateToDetailsTab(data);
			prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
			log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum1);
			//Selecting Season and Source
			ClickSeasonAndSource(data);
			//Navigate To specifications tab
			navigateToSpecificationTabThroughSideBar(data);
			//Adding a Specification
			AddSpecification(data);
			CommonFunctions.waitForPageLoaded();
			//Update the Specification Status
		    //updateSpecStatus(data);
	        //Create Multiple Colorway
			CreateMultiple_Colorway();
			//Select Colorway
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
			CommonFunctions.waitForPageLoaded();
			//Navigating to Costing Tab
			navigateToCostingTabThroughSideBar(data);
	}
		
		catch(Exception e){
			fail=true;
			log.error("Exception in CreateInternalAssrtProduct()", e);
			//return "";
			throw e;
		}
		return prodNum1;
		
		}
	
	public static String AddSource(String sourcename,String source) throws Exception{
		try{
			CommonFunctions.waitForPageLoaded();
			//Add Source
			Select dropDownSource = new Select(SeleniumDriver.driver.findElement(selectSource));
			List<WebElement> elementCountSource = dropDownSource.getOptions();
			int countSource =elementCountSource.size();
			if(countSource>2)
			{
				CommonFunctions.selectFromDropDownByVisibleText(selectSource, source);
				strSource=new Select(SeleniumDriver.driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}
			else
			{
				AddSourcingConfiguration(sourcename);
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(selectSource);
				CommonFunctions.selectFromDropDownByVisibleText(selectSource, source);
				CommonFunctions.waitForPageLoaded();
				strSource=new Select(SeleniumDriver.driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}
			SeleniumDriver.log.info("Source is: "+strSource);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddSource()", e);
			throw e;
		}
		return strSource;
	}

	public static Boolean AddSourcingConfiguration(String sourcename) throws Exception{
		try{
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");	
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.actionDD, "Add Sourcing Configuration");
			SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingConfig.supplier));
			//Supplier Selection
			CommonFunctions.clickButtonOrLink(SourcingConfig.supplier, "link", "supplier");
			CommonFunctions.waitForPageLoaded();
			//CommonFunctions.switchToChildWindow();
			Set set = SeleniumDriver.driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			SeleniumDriver.driver.switchTo().window(child);
			
			CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
			Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(strShowAll, "Search For  All Supplier");
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+sourcename+"')]/preceding::td[1]/a"), "Supplier selected");
			SeleniumDriver.driver.switchTo().window(parent);

			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");

			//Sourcing Lead 
			CommonFunctions.selectFromDropDownByIndex(sourcingLead, 5);
			//*Sourcing Head 
			CommonFunctions.selectFromDropDownByIndex(sourcingHead, 6);
			CommonFunctions.waitForPageLoaded();
			//click on Create
			CommonFunctions.clickButtonOrLink(SourcingConfig.CreateSourcebtn, "btn", "Create Source");
			CommonFunctions.waitForPageLoaded();
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in AddSourcingConfiguration()", e);
			throw e;
		}
	}

	//Create Vendor Product
	public static String CreateVendorAssrtProduct(String[] data) throws Exception{
		try{
				
			//********Create Product : Retail Item*********
			CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
			data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
			data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
				//Navigate to Details Tab
				NavigateToDetailsTab(data);
				prodNum2 = SeleniumDriver.driver.findElement(csProductNumber).getText();
				log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum2);
				//Add Source
				AddSource("AEQUS",data[39]);
				//Selecting Season and Source
				ClickSeasonAndSource(data);
				//Update the source to Approved state
				updateSource(data,"Approved");
				//Navigate To specifications tab
				navigateToSpecificationTabThroughSideBar(data);
				//Adding a Specification
				AddSpecification(data);
				CommonFunctions.waitForPageLoaded();
				//Update the Specification Status
			    updateSpecStatus(data);
		        //Create Multiple Colorway
				CreateMultiple_Colorway();
				//Select Colorway
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
				CommonFunctions.waitForPageLoaded();
				//Navigating to Costing Tab
				navigateToCostingTabThroughSideBar(data);
		}
			
			catch(Exception e){
				fail=true;
				log.error("Exception in CreateVendorAssrtProduct()", e);
				//return "";
				throw e;
			}
			return prodNum2;
			
			}
	
		
		
	
	/**
	 * 
	 * Create Internal Product CS
	 */
	
	public static String CreateInternalAssrtCS(String[] data) throws Exception{
		try{
			//Costsheet
			createCS(data);
			CostsheetTooling.clickOnCostSheetListTab();
			CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.costAction);
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet");
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForVisibilityOfElement(costSheetType);
			CommonFunctions.clickButtonOrLink(costSheetType, "link", "Costsheet Type-Internal Retail CS");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForVisibilityOfElement(CostsheetInternal.csName);
			driver.findElement(CostsheetInternal.csName).clear();
			CommonFunctions.enterTextInTextbox(CostsheetInternal.csName, data[38]);
			if(data[5].contains("Assortment/Solid")){
			//Wait for Wave Dropdown
			CommonFunctions.waitForElementTobeClickable(wave);
			//Click on Wave Dropdown
			//CommonFunctions.selectFromDropDownByVisibleTextUpdated(wave, data[34], "Wave DropDown");
			CommonFunctions.selectFromDropDownByIndex(wave, 1);
			}
			//Wait for Quote Currency Dropdown
			CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
			//Click on Quote Currency Dropdown
			CommonFunctions.selectFromDropDownByIndex(QuoteCurrencyDropDown, 2);
			
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
			CommonFunctions.waitForPageLoaded();
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in CreateInternalAssrtCS()", e);
			//return "";
			throw e;
		}
		return cstCreateCS;
		}
	
	
	/**
	 * 
	 * Create Vendor Product CS
	 */
	
	public static String CreateVendorAssrtCS(String[] data) throws Exception{
		try{
			//Costsheet
			createCS(data);
			CostsheetTooling.clickOnCostSheetListTab();
			CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.costAction);
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet");
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForVisibilityOfElement(costSheetTypeVendor);
			CommonFunctions.clickButtonOrLink(costSheetTypeVendor, "link", "Costsheet Type-Internal Retail CS");
			CommonFunctions.waitForPageLoaded();
		
			if(data[5].contains("Assortment/Solid")){
			//Wait for Wave Dropdown
			CommonFunctions.waitForElementTobeClickable(wave);
			//Click on Wave Dropdown
			//CommonFunctions.selectFromDropDownByVisibleTextUpdated(wave, data[34], "Wave DropDown");
			CommonFunctions.selectFromDropDownByIndex(wave, 1);
			}
			//Wait for Quote Currency Dropdown
			CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
			//Click on Quote Currency Dropdown
			CommonFunctions.selectFromDropDownByIndex(QuoteCurrencyDropDown, 2);
			
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
			CommonFunctions.waitForPageLoaded();
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorAssrtCS()", e);
			//return "";
			throw e;
		}
		return cstCreateCS;
		}
	
	/**
	 * Create Internal Product and Cost Sheet
	 */
	
	public static String CreateIntCostSheet_Prerequisites(String[] data) throws Exception{
		try{
			//Create Assortment Product
			CreateInternalAssrtProduct(data);
			//Create Internal Assortment Cost Sheet
			CreateInternalAssrtCS(data);
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in CreateIntCostSheet_Prerequisites");
			throw e;
		}
		return prodNum1;
		}
	
	/**
	 * Create Internal Retail Item Cost Sheet
	 */
	
	public static String CreateIntRetCostSheet_Prerequisites(String[] data) throws Exception{
		try{
			//Create Assortment Product
			CreateInternalAssrtProduct(data);
			//Create Internal Assortment Cost Sheet
			CreateInternalAssrtCS(data);
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in CreateIntCostSheet_Prerequisites");
			throw e;
		}
		return prodNum1;
		}
	
	/**
	 * Create Vendor Product and Cost Sheet
	 */
	
	public static String CreateVenCostSheet_Prerequisites(String[] data) throws Exception{
		try{
			//Create Assortment Product
			CreateVendorAssrtProduct(data);
			//Create Internal Assortment Cost Sheet
			CreateVendorAssrtCS(data);
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in CreateIntCostSheet_Prerequisites");
			throw e;
		}
		return prodNum2;
		}
		
	

	/**
	 * Create Vendor Retail Item and Cost Sheet
	 */
	
	public static String CreateVenRetCostSheet_Prerequisites(String[] data) throws Exception{
		try{
			//Create Assortment Product
			CreateVendorAssrtProduct(data);
			//Create Internal Assortment Cost Sheet
			CreateVendorAssrtCS(data);
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in CreateVenRetCostSheet_Prerequisites");
			throw e;
		}
		return prodNum2;
		}
	//PC55:Navigate To Product :
	public static boolean navigateToProduct(String[] data) throws Exception  
	{
		try{
			//Added refresh code below as to create another material BOM require this as we need to close library + sign
		   driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			if(driver.findElements(ProductLink).size()==0) 
			{
				// Click on Libraries
				
				CommonFunctions.clickButtonOrLink(Material.libraryLink, "Link", "Library Link");
			}
			//Click on Color link
		    driver.findElement(ProductLink).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			//Switch frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			//wait.until(ExpectedConditions.titleIs(data[40]));
		}
          catch(Exception e){
			fail=true;
			log.error("Exception in navigateUptoCreateDiffrntTypesOfMaterial()"+e);
		   throw e;
          }
		return true;
	}
	/**
	 * 
	 * Verify CS view Internal Product :
	 * 
	 */
	public static boolean VerifyIntPrdCSView(String[] data) throws Exception{
		try{
			//Navigating to product page  
			navigateToProduct(data);
			//Search Product
            CommonProjectFunctions.searchProduct(prodNum1);
			CommonFunctions.waitForPageLoaded();
			//Navigating to Costing Tab
			navigateToCostingTabThroughSideBar(data);
			
			if((data[0].equalsIgnoreCase("badmin"))||(data[0].equalsIgnoreCase("pmo_user"))||(data[0].equalsIgnoreCase("la_user"))||(data[0].equalsIgnoreCase("ma_user"))||(data[0].equalsIgnoreCase("en_user"))||(data[0].equalsIgnoreCase("el_user")||(data[0].equalsIgnoreCase("ewc_user"))||(data[0].equalsIgnoreCase("cs_user")))) {
				//Click on Costsheet List Tab
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				CostsheetTooling.clickOnCostSheetListTab();
				//Select view type
				CommonFunctions.clickButtonOrLink(viewIcon, "icon", "View");
				log.info("The Cost Sheet User Access View :Asst Cost Sheet View verified for Internal Product Costsheet");
				CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[37]+"']"), "option", data[3]);
				//Click on update table layout icon
				CommonFunctions.clickButtonOrLink(updateTableLayout, "Icon", "update table layout");
				//switch to default frame
				driver.switchTo().defaultContent();
				//switch to content frame
				driver.switchTo().frame("contentframe");
				//Wait for View Update page
				CommonFunctions.waitForVisibilityOfElement(viewupdatepage);
				//Click on Cancel
				CommonFunctions.clickButtonOrLink(CostsheetExternalProduct.btnCancel, "btn", "Cancel");
				//Click on Costsheet List Tab
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				CostsheetTooling.clickOnCostSheetListTab();
				//Verify Sequence
				verifySequence(data);
				//Verify User Group
				verifyUserGroup(data);
			}
			else{
				//Select view type
				CommonFunctions.clickButtonOrLink(viewIcon, "icon", "View");
				CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[37]+"']"), "option", data[3]);
				log.info("*******The Asst Cost Sheet View: verified for ********"+data[0]);
				//Assert.assertEquals(ActualValue, data[69]);
			}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in VerifyIntPrdCSView");
			throw e;
		}
		return true;
		}
	
	
	/**
	 * 
	 * Verify CS view Internal Retail Item :
	 * 
	 */
	public static boolean VerifyIntRetCSView(String[] data) throws Exception{
		try{
			//Navigating to product page  
			navigateToProduct(data);
			//Search Product
            CommonProjectFunctions.searchProduct(prodNum1);
			CommonFunctions.waitForPageLoaded();
			//Navigating to Costing Tab
			navigateToCostingTabThroughSideBar(data);
			
			if((data[0].equalsIgnoreCase("badmin"))||(data[0].equalsIgnoreCase("pmo_user"))||(data[0].equalsIgnoreCase("la_user"))||(data[0].equalsIgnoreCase("ma_user"))||(data[0].equalsIgnoreCase("en_user"))||(data[0].equalsIgnoreCase("el_user")||(data[0].equalsIgnoreCase("ewc_user"))||(data[0].equalsIgnoreCase("cs_user")))) {
				//Click on Costsheet List Tab
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				CostsheetTooling.clickOnCostSheetListTab();
				//Select view type
				CommonFunctions.clickButtonOrLink(viewIcon, "icon", "View");
				log.info("The Cost Sheet User Access View :Retail Item - Cost View verified for Internal Retail Item Costsheet");
				CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[37]+"']"), "option", data[3]);
				//Click on update table layout icon
				CommonFunctions.clickButtonOrLink(updateTableLayout, "Icon", "update table layout");
				//switch to default frame
				driver.switchTo().defaultContent();
				//switch to content frame
				driver.switchTo().frame("contentframe");
				//Wait for View Update page
				CommonFunctions.waitForVisibilityOfElement(viewupdatepage);
				//Click on Cancel
				CommonFunctions.clickButtonOrLink(CostsheetExternalProduct.btnCancel, "btn", "Cancel");
				//Click on Costsheet List Tab
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				CostsheetTooling.clickOnCostSheetListTab();
				//Verify Sequence
				verifySequence(data);
				//Verify User Group
				verifyUserGroup(data);
			}
			else{
				//Select view type
				CommonFunctions.clickButtonOrLink(viewIcon, "icon", "View");
				CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[37]+"']"), "option", data[3]);
				log.info("*******The Retail Item Cost Sheet View: verified for ********"+data[0]);
				//Assert.assertEquals(ActualValue, data[69]);
			}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in VerifyIntRetCSView");
			throw e;
		}
		return true;
		}
	
	/**
	 * 
	 * Verify CS view Vendor Product :
	 * 
	 */
	public static boolean VerifyVenPrdCSView(String[] data) throws Exception{
		try{
			//Navigating to product page  
			navigateToProduct(data);
			//Search Product
            CommonProjectFunctions.searchProduct(prodNum2);
			CommonFunctions.waitForPageLoaded();
			//Navigating to Costing Tab
			navigateToCostingTabThroughSideBar(data);
			
			//if((data[0].equalsIgnoreCase("badmin"))||(data[0].equalsIgnoreCase("pmo_user"))||(data[0].equalsIgnoreCase("la_user"))||(data[0].equalsIgnoreCase("ma_user"))||(data[0].equalsIgnoreCase("en_user"))||(data[0].equalsIgnoreCase("el_user")||(data[0].equalsIgnoreCase("ewc_user"))||(data[0].equalsIgnoreCase("cs_user")))) {
				//Click on Costsheet List Tab
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				CostsheetTooling.clickOnCostSheetListTab();
				//Select view type
				CommonFunctions.clickButtonOrLink(viewIcon, "icon", "View");
				log.info("The Cost Sheet User Access View :Vendor Asst Cost Sheet View verified for Vendor Product Costsheet");
				CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[4]+"']"), "option", data[3]);
				//Click on update table layout icon
				CommonFunctions.clickButtonOrLink(updateTableLayout, "Icon", "update table layout");
				//switch to default frame
				driver.switchTo().defaultContent();
				//switch to content frame
				driver.switchTo().frame("contentframe");
				//Wait for View Update page
				CommonFunctions.waitForVisibilityOfElement(viewupdatepage);
				//Click on Cancel
				CommonFunctions.clickButtonOrLink(CostsheetExternalProduct.btnCancel, "btn", "Cancel");
				//Click on Costsheet List Tab
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				CostsheetTooling.clickOnCostSheetListTab();
				//Verify Sequence
				verifySequence(data);
				//Verify User Group
				verifyUserGroup(data);
			//}
			/*else{
				//Select view type
				CommonFunctions.clickButtonOrLink(viewIcon, "icon", "View");
				CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[37]+"']"), "option", data[3]);
				log.info("*******The Asst Cost Sheet View: verified for ********"+data[0]);
				//Assert.assertEquals(ActualValue, data[69]);
			}*/
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in VerifyVenPrdCSView");
			throw e;
		}
		return true;
		}
	
	
	/**
	 * 
	 * Verify CS view Vendor Retail Item :
	 * 
	 */
	public static boolean VerifyVenRetCSView(String[] data) throws Exception{
		try{
			//Navigating to product page  
			navigateToProduct(data);
			//Search Product
            CommonProjectFunctions.searchProduct(prodNum2);
			CommonFunctions.waitForPageLoaded();
			//Navigating to Costing Tab
			navigateToCostingTabThroughSideBar(data);
			
			//if((data[0].equalsIgnoreCase("badmin"))||(data[0].equalsIgnoreCase("pmo_user"))||(data[0].equalsIgnoreCase("la_user"))||(data[0].equalsIgnoreCase("ma_user"))||(data[0].equalsIgnoreCase("en_user"))||(data[0].equalsIgnoreCase("el_user")||(data[0].equalsIgnoreCase("ewc_user"))||(data[0].equalsIgnoreCase("cs_user")))) {
				//Click on Costsheet List Tab
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				CostsheetTooling.clickOnCostSheetListTab();
				//Select view type
				CommonFunctions.clickButtonOrLink(viewIcon, "icon", "View");
				log.info("The Cost Sheet User Access View :Vendor Asst Cost Sheet View verified for Vendor Product Costsheet");
				CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[4]+"']"), "option", data[3]);
				//Click on update table layout icon
				CommonFunctions.clickButtonOrLink(updateTableLayout, "Icon", "update table layout");
				//switch to default frame
				driver.switchTo().defaultContent();
				//switch to content frame
				driver.switchTo().frame("contentframe");
				//Wait for View Update page
				CommonFunctions.waitForVisibilityOfElement(viewupdatepage);
				//Click on Cancel
				CommonFunctions.clickButtonOrLink(CostsheetExternalProduct.btnCancel, "btn", "Cancel");
				//Click on Costsheet List Tab
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				CostsheetTooling.clickOnCostSheetListTab();
				//Verify Sequence
				verifySequence(data);
				//Verify User Group
				verifyUserGroup(data);
			//}
			/*else{
				//Select view type
				CommonFunctions.clickButtonOrLink(viewIcon, "icon", "View");
				CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[37]+"']"), "option", data[3]);
				log.info("*******The Asst Cost Sheet View: verified for ********"+data[0]);
				//Assert.assertEquals(ActualValue, data[69]);
			}*/
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in VerifyVenPrdCSView");
			throw e;
		}
		return true;
		}
	public static boolean bFlagVal(String[] productData) throws Exception{
		try{
			if (flagVal)
			{ return true;}
			else{
				fail=true;
				log.info("Cascading fail for Class:" +productData[10] + " Division : "+productData[11]+" Brand: "+productData[12]+" Segment: " +productData[13]);
				Assert.fail("Cascading not working");
				return false;
			}
		}catch(Exception e){  
			fail=true;
			log.error("Exception in bFlagVal()", e);
			return false;
		}
	}

	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteViewsXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteViewsXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteViewsXls, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
	}
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteViewsXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		runmodes=Utility.getDataSetRunmodeTest(suiteViewsXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestPass){
			Utility.dataSetResult(suiteViewsXls,"Testcases", Utility.getRowNum(suiteViewsXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteViewsXls,"Testcases", Utility.getRowNum(suiteViewsXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteViewsXls, this.getClass().getSimpleName());
	}

}

