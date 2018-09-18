package com.hasbrop2m.security;
/**
 * @author Anjali Gupta
 *
 */
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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



public class Supplier extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
//	public static By supplierLink= By.linkText("Vendor (Supplier)");
//	public static By newLink= By.xpath("//a[text()='New']");
//	public static By headingCreateNewSupplier= By.xpath("//td[contains(text(),'Create New Supplier')]");
//	public static By dropdownAction1= By.xpath("//td[contains(text(),'Supplier Details')]//following::select[1]");
//	public static By RO_LCState= By.xpath("//td[contains(text(),'Lifecycle State')]//following::td[1]");
//	public static By supplierPageheading= By.xpath("//span[contains(text(),'Find: Vendor (Supplier)')]");
//	public static By search= By.id("SearchButton2");
//	public static By searchPageHeading= By.xpath("//span[contains(text(),'Search Results for')]");
//	public static By firstSupplier= By.xpath("//table[@class='TABLE_OUTLINE']/tbody/tr[2]/td[1]/a");

	public static By supplierLink= By.linkText("Vendor (Supplier)");
	public static By newLink= By.xpath("//a[text()='New']");
	public static By supplier= By.xpath("//td[@id='hbSupplier']/a");
	public static By factoryRegionValue= By.xpath("//td[@id='hbFactoryRegion']");
	public static By unitFreightCostHKDValue= By.xpath("//td[@id='hbFCLYTHKD']");
	public static By unitFreightCostUSDValue= By.xpath("//td[@id='hbFCLYTUSD']");
	public static By headingCreateNewSupplier= By.xpath("//td[contains(text(),'Create New Supplier')]");
	public static By headingCreateNewCountry= By.xpath("//td[contains(text(),'Create New Country')]");
	public static By dropdownAction1= By.xpath("//td[contains(text(),'Supplier Details')]//following::select[1]");
	public static By RO_LCState= By.xpath("//td[contains(text(),'Lifecycle State')]//following::td[1]");
	public static By supplierPageheading= By.xpath("//span[contains(text(),'Find: Vendor (Supplier)')]");
	public static By search= By.id("SearchButton2");
	public static By searchPageHeading= By.xpath("//span[contains(text(),'Search Results for')]");
	public static By firstSupplier= By.xpath("//table[@class='TABLE_OUTLINE']/tbody/tr[2]/td[1]/a");
	public static By country=By.linkText("Country");
	public static By countrysearchname= By.xpath("//input[contains(@id,'LCSCOUNTRY_ptc_str')]");
	public static By countryname=By.xpath("//*[@id='null']/div/div[2]/table/tbody/tr/td[1]//following::input[1]");
	public static By SAPCountryAbbreviation=By.xpath("//*//*[contains(@id,'ptc_str')]//following::input[1]");
	public static By countryfirstsearch= By.xpath("//a[@id='COL0']/following::a[5]");
	public static By countrygeneralattribute=By.xpath("//td[contains(text(),'General Attributes')]");
	public static By countryidentification=By.xpath("//td[contains(text(),'Country Identification')]");
	public static By associateddoc=By.xpath("//td[contains(text(),'Associated Documents:')]");
	public static By systeminfo=By.xpath("//td[contains(text(),'System Information:')]");
	public static By factoryRegion=By.xpath("//td[contains(text(),'Factory Region')]");
	public static By factoryRegionUpdate=By.xpath("//td[contains(text(),'*Factory Region')]//following::Select[1]");
	public static By UnitFreightCostHKD=By.xpath("//td[contains(text(),'Unit Freight Cost (HKD)')]");
	public static By UnitFreightCostHKDUpdate=By.xpath("//td[contains(text(),'FCL-YT (HKD)')]//following::input[1]");
	public static By UnitFreightCostUSD=By.xpath("//td[contains(text(),'Unit Freight Cost (USD)')]");
	public static By UnitFreightCostUSDUpdate=By.xpath("//td[contains(text(),'FCL-YT (USD)')]//following::input[1]");
	public static By countryactions=By.xpath(".//*[@id='contentDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/select");
	public static By countrystateupdate=By.xpath(".//*[@id='contentDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/a[1]");
	public static By countrydelete= By.xpath(".//*[@id='contentDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/a[1]");
	public static By countrynameupdate= By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By countryname2= By.xpath("//td[contains(text(),'Name')]//following::td[1]");
	public static By countryupdatepage= By.xpath("//td[contains(text(),'Update')]");
	public static By save=By.xpath(".//*[@id='contentDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/a[1]");
	public static By SAPCountryAbbreviationread=By.xpath(".//*[@id='hbSAPCountryAbbr']");
	public static By SAPCountryAbbreviationupdate=By.xpath("//td[contains(text(),'SAP Country Abbreviation')]//following::input[1]");
	public static By countrynameread=By.xpath(".//*[@id='null']/div/div[2]/table/tbody/tr/td[2]");
	
	public static By status=By.xpath("//td[contains(text(),'Status')]//following::select[1]");
	
	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;



	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSupplier(String[] data) throws Exception{
	
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			try{
			log.debug("Inside testcase for Supplier Security");
			System.out.println("col0 :" + data[0]);
			System.out.println("col1 :" + data[1]);
			System.out.println("col4 :" + data[4]);
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
					System.out.println("y: "+y);
					loginVal=data[0];
					flaglogin=true;
				}
			}
			//	wait = new WebDriverWait(driver, 10);
			//	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchDropDownSelect")));

			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("Create"))
			{ Create_Supplier(data); }
			//SetState
			if(data[3].equalsIgnoreCase("SetState"))
			{ SetState_Supplier(data); }
			//Read view verification
			if(data[3].equalsIgnoreCase("GeneralAttirbutesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification
			if(data[3].equalsIgnoreCase("GeneralAttirbutesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }
						
			if(data[3].equalsIgnoreCase("FactoryRegionReadView"))
			{  verifyFactoryRegionReadView(data); }
			if(data[3].equalsIgnoreCase("FactoryRegionUpdate"))
			{  verifyFactoryRegionUpdate(data); }
			//Update Verification
			
			if(data[3].equalsIgnoreCase("UnitFreightCostHKDReadView"))
			{  verifyUnitFreightCostHKDReadView(data); }
			if(data[3].equalsIgnoreCase("UnitFreightCostHKDUpdate"))
			{  verifyUnitFreightCostHKDUpdate(data); }
			
			if(data[3].equalsIgnoreCase("UnitFreightCostUSDReadView"))
			{  verifyUnitFreightCostUSDReadView(data); }
			
			if(data[3].equalsIgnoreCase("UnitFreightCostUSDUpdate"))
			{  verifyUnitFreightCostUSDUpdate(data); }
		
			//Update Verification
			
			
			
			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_Supplier(data); }
			/**************Country***********************************/
			//Create Country
			if(data[3].equalsIgnoreCase("CreateCountry"))
			{ Create_Country(data); }

			//View Country
			if(data[3].equalsIgnoreCase("verifyCountryReadView"))
			{verifyCountryReadView(data);}

			//Set State Country
			if(data[3].equalsIgnoreCase("SetState_Country"))
			{SetState_Country(data);}

			//Delete Country
			if(data[3].equalsIgnoreCase("DeleteCountry"))
			{Delete_Country(data);}

			//Read Country Identification
			if(data[3].equalsIgnoreCase("Read_Country_Identification"))
			{Read_Country_Identification(data);}

			//Read Country Identification
			if(data[3].equalsIgnoreCase("Update_Country_Identification"))
			{Update_Country_Identification(data);}

			//Read_General_Attribute
			if(data[3].equalsIgnoreCase("Read_Country_General_Attribute"))
			{Read_Country_General_Attribute(data);}

			//Update_General_Attribute
			if(data[3].equalsIgnoreCase("Update_Country_General_Attribute"))
			{Update_Country_General_Attribute(data);}


		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	//Prerequisite: Create Product
	/*
	 * Create_Supplier is blocked as it is integarted with SAP,There is no form to fill
	 */
	public static boolean Create_Supplier(String[] data) throws Exception{
		try{
			//driver.navigate().refresh();
			if(!data[0].contains("vuser") && !data[0].contains("vendora") ){
				// Switch to Sidebar Frame
				driver.switchTo().defaultContent(); 
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			
				
				//Click on Supplier
				
				CommonProjectFunctions.clickSupplierLink();			

				//	Boolean strNew =driver.findElement(newLink).isDisplayed();
				if(data[4].equalsIgnoreCase("Yes")){
					//Click on new Link
					CommonFunctions.clickButtonOrLink(newLink, "link", "New");
					wait = new WebDriverWait(driver, 10);
					driver.switchTo().defaultContent(); 
					driver.switchTo().frame("contentframe");
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingCreateNewSupplier));
					
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"') and @class='LABEL']"), "link", "Product Type");
					//Fill page
					//Nothing on page to fill 
					//click on Create
					wait.until(ExpectedConditions.titleIs("Create Supplier"));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(Product.createBtn);
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
					//	Assert.assertEquals(strPrimary,"Primary");
				}
				else if(data[4].equalsIgnoreCase("No")){
					Assert.assertEquals(driver.findElements(newLink).size(), 0, "New Not Visible"); 
					//	Assert.(strNew);
				}
				else{
					log.info("Create New is not applicable(NA)");
				}
			}
			else{
				Assert.assertEquals(driver.findElements(supplierLink).size(), 0);
			}	
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in Create_Supplier()", e);
			throw e;
		}
		return true;
	}



	public static boolean SetState_Supplier(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			if(!data[0].contains("vuser")&& !data[0].contains("vendora")){
				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
			
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//Click on Supplier
				CommonProjectFunctions.clickSupplierLink();

				//Click on factory or Supplier
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"') and @class='LABEL']"), "link", "Supplier Type");
			wait.until(ExpectedConditions.titleIs("Supplier Search Criteria"));
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent(); 
			driver.switchTo().frame("contentframe");
				CommonProjectFunctions.Search_Supplier();
				CommonFunctions.waitForPageLoaded();
				
				String strRO_LCStateBefore = driver.findElement(RO_LCState).getText();
				//	CommonFunctions.enterTextInTextbox(dropdownAction1, data[5]);
				CommonFunctions.clickButtonOrLink(dropdownAction1, "drop down", "Action");
				CommonFunctions.clickButtonOrLink(By.xpath("//td[contains(text(),'Supplier Details')]//following::select[1]/option[contains(text(),'"+data[5]+"')]"), "drop down", "Action");
				//driver.findElement(specificationAction).sendKeys(Keys.ENTER);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
			
				String valULCSAfterChange=Supplier_selectUpdateLifecycleState(data);
			
				//Click on Update
				CommonFunctions.clickButtonOrLink(Product.SaveBtn, "btn", "Save");
				//verification
				CommonFunctions.waitForVisibilityOfElement(RO_LCState);
				
				String strRO_LCState = driver.findElement(RO_LCState).getText();

				if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("Yes")){
					Assert.assertEquals(strRO_LCState, valULCSAfterChange);
				}
				else if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("No")){
					Assert.assertEquals(strRO_LCStateBefore, strRO_LCState);
				}
				else
					log.info("SetState or chageState is not applicable(NA)");
			}	
			else{
				Assert.assertEquals(driver.findElements(supplierLink).size(), 0);
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in SetState_Supplier()", e);
		throw e;
		}
		return true;
	}

	public static boolean delete_Supplier(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			if(!data[0].contains("vuser")&& !data[0].contains("vendora")){
				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//Click on Supplier
				CommonProjectFunctions.clickSupplierLink();
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"') and @class='LABEL']"), "link", "Supplier Type");
				wait.until(ExpectedConditions.titleIs("Supplier Search Criteria"));
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent(); 
				driver.switchTo().frame("contentframe");
				CommonProjectFunctions.Search_Supplier();
				CommonFunctions.waitForPageLoaded();
				if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
					CommonFunctions.waitForElementTobeClickable(dropdownAction1);
					CommonFunctions.clickButtonOrLink(dropdownAction1, "btn", "Action dropdown");
					CommonFunctions.clickButtonOrLink(By.xpath("//td[contains(text(),'Supplier Details')]//following::select[1]/option[contains(text(),'"+data[5]+"')]"), "drop down", "Action");
					CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
					driver.findElement(Product.deleteButton).click();
				
					Boolean bAlert = CommonFunctions.handleAlertPopUp2();
					Assert.assertTrue(bAlert);
				}
				else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
					Select select = new Select(driver.findElement(dropdownAction1));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[5],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal);
				}
				else
					log.info("Delete is not applicable(NA)");
			}
			else{
				Assert.assertEquals(driver.findElements(supplierLink).size(), 0);
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in delete_Supplier()", e);
		throw e;
		}
		return true;
	}

	

	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			if(!data[0].contains("vuser")&& !data[0].contains("vendora")){
				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//Click on Supplier
				CommonProjectFunctions.clickSupplierLink();
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"') and @class='LABEL']"), "link", "Supplier Type");
				wait.until(ExpectedConditions.titleIs("Supplier Search Criteria"));
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent(); 
				driver.switchTo().frame("contentframe");
				CommonProjectFunctions.Search_Supplier();
				if(data[3].contains("GeneralAttirbutesRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
					if(driver.findElements(Product.labelGeneralAttri).size() != 0){
						String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
						System.out.println(GALabel);
						System.out.println(" General Attributes:");
						Assert.assertEquals(GALabel, " General Attributes:");
						log.info("General Attributes label is Present");
					}else{
						log.error("General Attributes label is Absent");
						fail=true;
					}
				}
				else if(data[3].contains("GeneralAttirbutesRead_View")&& data[4].equalsIgnoreCase("No")){
					if(driver.findElements(Product.labelGeneralAttri).size() != 0){
						System.out.println("General Attirbutes label is Present");
						log.error("General Attirbutes label is Present");
						fail=true;
					}else{
						log.info("General Attirbutes label is Absent");
					}
				}
				else
				{
					log.info("For this General Attributes is not applicable(NA)");
				}
			}
			else{
				Assert.assertEquals(driver.findElements(supplierLink).size(), 0);
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyGeneralAttributesReadView()", e);
		return false;
		}
		return true;
	}
	
	//Function consist scenario : Factory Region:Read_View
		public static boolean verifyFactoryRegionReadView(String[] data) throws Exception{
			try{
				driver.navigate().refresh();
				if(!data[0].contains("vuser")&& !data[0].contains("vendora")){
					// Switch to Sidebar Frame
					driver.switchTo().frame("sidebarframe");
					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
					//Click on Supplier
					CommonProjectFunctions.clickSupplierLink();
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"') and @class='LABEL']"), "link", "Supplier Type");
					
					CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent(); 
					driver.switchTo().frame("contentframe");
					CommonProjectFunctions.Search_Supplier();
					if(data[3].contains("FactoryRegionReadView")&& data[4].equalsIgnoreCase("Yes")){//Read_View
						if(driver.findElements(factoryRegion).size() != 0){
							String FRlabel=driver.findElement(factoryRegion).getText();
							System.out.println(FRlabel);
							System.out.println("Factory Region:");
							Assert.assertEquals(FRlabel, " Factory Region:");
							log.info("Factory Region label is Present");
						}else{
							log.error("Factory Region label is Absent");
							fail=true;
						}
					}
					else if(data[3].contains("FactoryRegionReadView")&& data[4].equalsIgnoreCase("No")){
						if(driver.findElements(factoryRegion).size() != 0){
							System.out.println("Factory Region label is Present");
							log.error("Factory Region label is Present");
							fail=true;
						}else{
							log.info("Factory Region label is Absent");
						}
					}
					else
					{
						log.info("For this Factory Region is not applicable(NA)");
					}
				}
				else{
					Assert.assertEquals(driver.findElements(supplierLink).size(), 0);
				}
			}catch(Exception e){  fail=true;
			log.error("Exception in verifyFactoryRegionReadView()", e);
			return false;
			}
			return true;
		}
		
		//Function consist scenario : UnitFreightCostHKDReadView:Read_View
				public static boolean verifyUnitFreightCostHKDReadView(String[] data) throws Exception{
					try{
						driver.navigate().refresh();
						if(!data[0].contains("vuser")&& !data[0].contains("vendora")){
							// Switch to Sidebar Frame
							driver.switchTo().frame("sidebarframe");
							// Click on Libraries
							CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
							//Click on Supplier
							CommonProjectFunctions.clickSupplierLink();
							CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"') and @class='LABEL']"), "link", "Supplier Type");
							
							CommonFunctions.waitForPageLoaded();
							driver.switchTo().defaultContent(); 
							driver.switchTo().frame("contentframe");
							CommonProjectFunctions.Search_Supplier();
							if(data[3].contains("UnitFreightCostHKDReadView")&& data[4].equalsIgnoreCase("Yes")){//Read_View
								if(driver.findElements(UnitFreightCostHKD).size() != 0){
									String FRlabel=driver.findElement(UnitFreightCostHKD).getText();
									System.out.println(FRlabel);
									System.out.println("Unit Freight Cost (HKD):");
									Assert.assertEquals(FRlabel, " Unit Freight Cost (HKD):");
									log.info("Unit Freight Cost (HKD) label  is Present");
								}else{
									log.error("Unit Freight Cost (HKD) label is Absent");
									fail=true;
								}
							}
							else if(data[3].contains("UnitFreightCostHKDReadView")&& data[4].equalsIgnoreCase("No")){
								if(driver.findElements(UnitFreightCostHKD).size() != 0){
									System.out.println("Unit Freight Cost (HKD) label is Present");
									log.error("Unit Freight Cost (HKD) label is Present");
									fail=true;
								}else{
									log.info("Unit Freight Cost (HKD) label is Absent");
								}
							}
							else
							{
								log.info("For this Unit Freight Cost (HKD) is not applicable(NA)");
							}
						}
						else{
							Assert.assertEquals(driver.findElements(supplierLink).size(), 0);
						}
					}catch(Exception e){  fail=true;
					log.error("Exception in verifyFactoryRegionReadView()", e);
					return false;
					}
					return true;
				}
				
				//Function consist scenario : UnitFreightCostUSDReadView:Read_View
				public static boolean verifyUnitFreightCostUSDReadView(String[] data) throws Exception{
					try{
						driver.navigate().refresh();
						if(!data[0].contains("vuser")&& !data[0].contains("vendora")){
							// Switch to Sidebar Frame
							driver.switchTo().frame("sidebarframe");
							// Click on Libraries
							CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
							//Click on Supplier
							CommonProjectFunctions.clickSupplierLink();
							CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"') and @class='LABEL']"), "link", "Supplier Type");
							
							CommonFunctions.waitForPageLoaded();
							driver.switchTo().defaultContent(); 
							driver.switchTo().frame("contentframe");
							CommonProjectFunctions.Search_Supplier();
							if(data[3].contains("UnitFreightCostUSDReadView")&& data[4].equalsIgnoreCase("Yes")){//Read_View
								if(driver.findElements(UnitFreightCostUSD).size() != 0){
									String FRlabel=driver.findElement(UnitFreightCostUSD).getText();
									System.out.println(FRlabel);
									System.out.println("Unit Freight Cost (USD):");
									Assert.assertEquals(FRlabel, " Unit Freight Cost (USD):");
									log.info("Unit Freight Cost (USD) label  is Present");
								}else{
									log.error("Unit Freight Cost (USD) label is Absent");
									fail=true;
								}
							}
							else if(data[3].contains("UnitFreightCostUSDReadView")&& data[4].equalsIgnoreCase("No")){
								if(driver.findElements(UnitFreightCostUSD).size() != 0){
									System.out.println("Unit Freight Cost (USD) label is Present");
									log.error("Unit Freight Cost (USD) label is Present");
									fail=true;
								}else{
									log.info("Unit Freight Cost (USD) label is Absent");
								}
							}
							else
							{
								log.info("For this Unit Freight Cost (USD) is not applicable(NA)");
							}
						}
						else{
							Assert.assertEquals(driver.findElements(supplierLink).size(), 0);
						}
					}catch(Exception e){  fail=true;
					log.error("Exception in verifyUnitFreightCostUSDReadView()", e);
					return false;
					}
					return true;
				}
	//Function consist scenario : General Attributes://Update
	public static boolean verifyGeneralAttributesUpdate(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			if(!data[0].contains("vuser")&& !data[0].contains("vendora")){
				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//Click on Supplier
				CommonProjectFunctions.clickSupplierLink();
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"') and @class='LABEL']"), "link", "Supplier Type");
				CommonFunctions.waitForPageLoaded();
				
			
			
				CommonProjectFunctions.Search_Supplier();
				CommonFunctions.waitForPageLoaded();
				
			
				if(data[3].contains("GeneralAttirbutesUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
					SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");	
					
					Select select = new Select(driver.findElement(dropdownAction1));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[5],options);
					//	dropDownOptionVerification
					Assert.assertTrue(bVal);
					//	CommonFunctions.enterTextInTextbox(dropdownAction1, data[5]);
					CommonFunctions.clickButtonOrLink(dropdownAction1, "drop down", "Action");
					CommonFunctions.clickButtonOrLink(By.xpath("//td[contains(text(),'Supplier Details')]//following::select[1]/option[contains(text(),'"+data[5]+"')]"), "drop down", "Action");
					//driver.findElement(specificationAction).sendKeys(Keys.ENTER);
					
					
					CommonFunctions.waitForPageLoaded();
					SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");	
					CommonFunctions.waitForElementTobeClickable(SourcingConfig.supplier);
					//Supplier Selection
					CommonFunctions.clickButtonOrLink(SourcingConfig.supplier, "link", "supplier");
					Set set = SeleniumDriver.driver.getWindowHandles();
					Iterator iter = set.iterator();
					String parent = (java.lang.String) iter.next();
					String child = (java.lang.String) iter.next();
					SeleniumDriver.driver.switchTo().window(child);
					CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[6]+"')]/preceding::td[1]/a"), "Supplier selected");
					SeleniumDriver.driver.switchTo().window(parent);
					SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");	
					Supplier_selectUpdateLifecycleState(data);
					CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
					Assert.assertEquals(driver.findElement(supplier).getText(), data[6]); 
					

				
				}
				else if(data[3].contains("GeneralAttirbutesUpdate")&& data[4].equalsIgnoreCase("No")){
					Select select = new Select(driver.findElement(dropdownAction1));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[5],options);
					
					//	dropDownOptionVerification
					if(!bVal)
						Assert.assertFalse(bVal);
					else{
						CommonFunctions.enterTextInTextbox(dropdownAction1, data[5]);
					//	Assert.fail("Update is present");
							System.out.println(driver.findElements(status).size());
							Assert.assertEquals(driver.findElements(status).size(), 0, "Supplier is not Editable"); 
					}
				}	
				else
				{
					log.info("For this General Attributes is not applicable(NA)");
				}}
			else{
				Assert.assertEquals(driver.findElements(supplierLink).size(), 0);
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyGeneralAttributesUpdate()", e);
		return false;
		}
		return true;
	}
	
	//Function consist scenario : Factory Region://Update
	public static boolean verifyFactoryRegionUpdate(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			if(!data[0].contains("vuser")&& !data[0].contains("vendora")){
				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//Click on Supplier
				CommonProjectFunctions.clickSupplierLink();
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"') and @class='LABEL']"), "link", "Supplier Type");
				CommonFunctions.waitForPageLoaded();
				
			
			
				CommonProjectFunctions.Search_Supplier();
				CommonFunctions.waitForPageLoaded();
				
			
				if(data[3].contains("FactoryRegionUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
					SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");	
					
					Select select = new Select(driver.findElement(dropdownAction1));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[5],options);
					//	dropDownOptionVerification
					Assert.assertTrue(bVal);
					//	CommonFunctions.enterTextInTextbox(dropdownAction1, data[5]);
					CommonFunctions.clickButtonOrLink(dropdownAction1, "drop down", "Action");
					CommonFunctions.clickButtonOrLink(By.xpath("//td[contains(text(),'Supplier Details')]//following::select[1]/option[contains(text(),'"+data[5]+"')]"), "drop down", "Action");
					//driver.findElement(specificationAction).sendKeys(Keys.ENTER);
					
					
					CommonFunctions.waitForPageLoaded();
					SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");	
					
					
					Select regionSelect = new Select(driver.findElement(factoryRegionUpdate));
				
					List<WebElement> optionsRegion = regionSelect.getOptions();
					boolean bValRegion=CommonFunctions.dropDownOptionVerificationActions(data[6],optionsRegion);
					if(!bValRegion)
						Assert.assertFalse(bVal);
					else{	CommonFunctions.selectFromDropDownByVisibleText(factoryRegionUpdate,data[6] );
					}
					
					SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");	
					Supplier_selectUpdateLifecycleState(data);
					CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
					Assert.assertEquals(driver.findElement(factoryRegionValue).getText(), data[6]); 
					

				
				}
				else if(data[3].contains("FactoryRegionUpdate")&& data[4].equalsIgnoreCase("No")){
					Select select = new Select(driver.findElement(dropdownAction1));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[5],options);
					
					//	dropDownOptionVerification
					if(!bVal)
						Assert.assertFalse(bVal);
					else{
						CommonFunctions.enterTextInTextbox(dropdownAction1, data[5]);
					//	Assert.fail("Update is present");
							System.out.println(driver.findElements(status).size());
							Assert.assertEquals(driver.findElements(status).size(), 0, "Supplier is not Editable"); 
					}
				}	
				else
				{
					log.info("For this Factory Region is not applicable(NA)");
				}}
			else{
				Assert.assertEquals(driver.findElements(supplierLink).size(), 0);
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyFactoryRegionUpdate()", e);
		return false;
		}
		return true;
	}
	
	//Function consist scenario UnitFreightCostHKDUpdate://Update
		public static boolean verifyUnitFreightCostHKDUpdate(String[] data) throws Exception{
			try{
 				driver.navigate().refresh();
				if(!data[0].contains("vuser")&& !data[0].contains("vendora")){
					// Switch to Sidebar Frame
					driver.switchTo().frame("sidebarframe");
					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
					//Click on Supplier
					CommonProjectFunctions.clickSupplierLink();
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"') and @class='LABEL']"), "link", "Supplier Type");
					CommonFunctions.waitForPageLoaded();
					
				
				
					CommonProjectFunctions.Search_Supplier();
					CommonFunctions.waitForPageLoaded();
					
				
					if(data[3].contains("UnitFreightCostHKDUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
						SeleniumDriver.driver.switchTo().defaultContent();
						SeleniumDriver.driver.switchTo().frame("contentframe");	
					
						Select select = new Select(driver.findElement(dropdownAction1));
						List<WebElement> options = select.getOptions();
						boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[5],options);
						//	dropDownOptionVerification
						Assert.assertTrue(bVal);
						//	CommonFunctions.enterTextInTextbox(dropdownAction1, data[5]);
						CommonFunctions.clickButtonOrLink(dropdownAction1, "drop down", "Action");
						CommonFunctions.clickButtonOrLink(By.xpath("//td[contains(text(),'Supplier Details')]//following::select[1]/option[contains(text(),'"+data[5]+"')]"), "drop down", "Action");
						//driver.findElement(specificationAction).sendKeys(Keys.ENTER);
						
						
						CommonFunctions.waitForPageLoaded();
						SeleniumDriver.driver.switchTo().defaultContent();
						SeleniumDriver.driver.switchTo().frame("contentframe");	
						
						
						
					
						
						driver.findElement(UnitFreightCostHKDUpdate).click();
						CommonFunctions.clearTextBox(UnitFreightCostHKDUpdate, "unitfrieghtcosthkd");
						CommonFunctions.enterTextInTextbox(UnitFreightCostHKDUpdate, data[6]);
						
						
						SeleniumDriver.driver.switchTo().defaultContent();
						SeleniumDriver.driver.switchTo().frame("contentframe");	
						Supplier_selectUpdateLifecycleState(data);
						CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
						Assert.assertEquals(driver.findElement(unitFreightCostHKDValue).getText(), data[6]); 
						

					
					}
					else if(data[3].contains("UnitFreightCostHKDUpdate")&& data[4].equalsIgnoreCase("No")){
						Select select = new Select(driver.findElement(dropdownAction1));
						List<WebElement> options = select.getOptions();
						boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[5],options);
						
						//	dropDownOptionVerification
						if(!bVal)
							Assert.assertFalse(bVal);
						else{
							CommonFunctions.enterTextInTextbox(dropdownAction1, data[5]);
						//	Assert.fail("Update is present");
								System.out.println(driver.findElements(status).size());
								Assert.assertEquals(driver.findElements(status).size(), 0, "Supplier is not Editable"); 
						}
					}	
					else
					{
						log.info("For this Unit Freight Cost(HKD) is not applicable(NA)");
					}}
				else{
					Assert.assertEquals(driver.findElements(supplierLink).size(), 0);
				}
			}catch(Exception e){  fail=true;
			log.error("Exception in verifyUnitFreightCostHKDUpdate()", e);
			return false;
			}
			return true;
		}
		
		//Function consist scenario UnitFreightCostUSDUpdate://Update
				public static boolean verifyUnitFreightCostUSDUpdate(String[] data) throws Exception{
					try{
		 				driver.navigate().refresh();
						if(!data[0].contains("vuser")&& !data[0].contains("vendora")){
							// Switch to Sidebar Frame
							driver.switchTo().frame("sidebarframe");
							// Click on Libraries
							CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
							//Click on Supplier
							CommonProjectFunctions.clickSupplierLink();
							CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"') and @class='LABEL']"), "link", "Supplier Type");
							CommonFunctions.waitForPageLoaded();
							
						
						
							CommonProjectFunctions.Search_Supplier();
							CommonFunctions.waitForPageLoaded();
							
						
							if(data[3].contains("UnitFreightCostUSDUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
								SeleniumDriver.driver.switchTo().defaultContent();
								SeleniumDriver.driver.switchTo().frame("contentframe");	
							
								Select select = new Select(driver.findElement(dropdownAction1));
								List<WebElement> options = select.getOptions();
								boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[5],options);
								//	dropDownOptionVerification
								Assert.assertTrue(bVal);
								//	CommonFunctions.enterTextInTextbox(dropdownAction1, data[5]);
								CommonFunctions.clickButtonOrLink(dropdownAction1, "drop down", "Action");
								CommonFunctions.clickButtonOrLink(By.xpath("//td[contains(text(),'Supplier Details')]//following::select[1]/option[contains(text(),'"+data[5]+"')]"), "drop down", "Action");
								//driver.findElement(specificationAction).sendKeys(Keys.ENTER);
								
								
								CommonFunctions.waitForPageLoaded();
								SeleniumDriver.driver.switchTo().defaultContent();
								SeleniumDriver.driver.switchTo().frame("contentframe");	
								
								
								
							
								
								driver.findElement(UnitFreightCostUSDUpdate).click();
								CommonFunctions.clearTextBox(UnitFreightCostUSDUpdate, "unitfrieghtcostUSD");
								CommonFunctions.enterTextInTextbox(UnitFreightCostUSDUpdate, data[6]);
								
								
								SeleniumDriver.driver.switchTo().defaultContent();
								SeleniumDriver.driver.switchTo().frame("contentframe");	
								Supplier_selectUpdateLifecycleState(data);
								CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
								Assert.assertEquals(driver.findElement(unitFreightCostUSDValue).getText(), data[6]); 
								

							
							}
							else if(data[3].contains("UnitFreightCostUSDUpdate")&& data[4].equalsIgnoreCase("No")){
								Select select = new Select(driver.findElement(dropdownAction1));
								List<WebElement> options = select.getOptions();
								boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[5],options);
								
								//	dropDownOptionVerification
								if(!bVal)
									Assert.assertFalse(bVal);
								else{
									CommonFunctions.enterTextInTextbox(dropdownAction1, data[5]);
								//	Assert.fail("Update is present");
										System.out.println(driver.findElements(status).size());
										Assert.assertEquals(driver.findElements(status).size(), 0, "Supplier is not Editable"); 
								}
							}	
							else
							{
								log.info("For this Unit Freight Cost(USD) is not applicable(NA)");
							}}
						else{
							Assert.assertEquals(driver.findElements(supplierLink).size(), 0);
						}
					}catch(Exception e){  fail=true;
					log.error("Exception in verifyUnitFreightCostUSDUpdate()", e);
					return false;
					}
					return true;
				}
	//This funcion is to select Update Lifecycle State	
	public static String Supplier_selectUpdateLifecycleState(String[] data) throws Exception{
		try{
			CommonFunctions.waitForPageLoaded();
			
			valULCS = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			if(valULCS.contains("Unassigned")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState,"Approved");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Approved");
			}
			else if(valULCS.contains("Approved")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Unassigned");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Conditionally Approved");
			}
			else if(valULCS.contains("Conditionally Approved")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Unassigned");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Approved");
			}
			else if(valULCS.contains("Inactive")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Unassigned");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Active");
			}
			else if(valULCS.contains("Active")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Unassigned");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Inactive");
			}
			else if(valULCS.contains("Active")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Unassigned");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Inactive");
			}
			else{//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Unassigned");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Active");}
			valULCSAfterChange = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){  fail=true;
		log.error("Exception in Supplier_selectUpdateLifecycleState()", e);
		}
		return valULCSAfterChange;
	}
	//Create Country
		public static boolean Create_Country(String[] data) throws Exception{
			try{
				driver.navigate().refresh();
				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//Click on Country
				driver.findElement(country).click();

				if(data[3].contains("CreateCountry")&& data[4].equalsIgnoreCase("Yes")){

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//Click on new Link
					CommonFunctions.clickButtonOrLink(newLink, "link", "New");
					wait = new WebDriverWait(driver, 10);
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingCreateNewCountry));
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));

					//Enter the value for name
					driver.findElement(countryname).click();
					CommonFunctions.enterTextInTextbox(countryname, data[6]);

					//Enter the value for SAPCountryAbbreviation
					driver.findElement(SAPCountryAbbreviation).click();
					CommonFunctions.enterTextInTextbox(SAPCountryAbbreviation, data[7]);

					//click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");


					//Get text for country name
					String countryname01=driver.findElement(countrynameread).getText();

					//Compare the country name value
					Assert.assertEquals(countryname01, data[6]);

					//Get text for SAP Abbreviation Country
					String sapcountryabb01=driver.findElement(SAPCountryAbbreviationread).getText();

					//Compare the SAP Abbreviation Country value
					Assert.assertEquals(sapcountryabb01, data[7]);

				}
				else if(data[3].contains("CreateCountry")&& data[4].equalsIgnoreCase("No")){
					Assert.assertEquals(driver.findElements(newLink).size(), 0, "New Not Visible");

				}
			}

			catch(Exception e){ 
				fail=true;
				log.error("Create_Country()", e);
				return false;
			}
			return true;

		}

		public static boolean verifyCountryReadView(String[] data) throws Exception{
			try{
				//refresh the page
				driver.navigate().refresh();

				//switch to default frame
				driver.switchTo().defaultContent();

				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

				if(data[3].contains("verifyCountryReadView")&& data[4].equalsIgnoreCase("Yes")){//Read_View
					//Click on Country
					driver.findElement(country).click();

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//Click on search
					CommonFunctions.clickButtonOrLink(Supplier.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Supplier.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();
					//General Attribute table
					String CountryGALabel=driver.findElement(countrygeneralattribute).getText();

					//Print screen
					System.out.println(CountryGALabel);

					//Print General Attribute
					System.out.println("General Attributes:");
					//Verify that General Attribute table exist
					Assert.assertEquals(CountryGALabel.trim(), "General Attributes:");
					log.info("General Attributes label is Present");

					//Country Identification
					String CI=driver.findElement(countryidentification).getText();
					System.out.println(CI);
					System.out.println("Country Identification");
					//Verify that Country Identification table exist
					Assert.assertEquals(CI, "Country Identification");
					log.info("Country Identification label is Present");

					//Associated document
					String associateddocument=driver.findElement(associateddoc).getText();
					System.out.println(associateddocument);
					System.out.println("Associated Documents:");
					//Verify that Associated document table exist
					Assert.assertEquals(associateddocument, "Associated Documents:");
					log.info("Associated Documents: label is Present");

					//System Information
					String systeminformation=driver.findElement(systeminfo).getText();
					System.out.println(systeminformation);
					System.out.println("System Information:");
					//Verify that System Information table exist
					Assert.assertEquals(systeminformation, "System Information:");
					log.info("Associated Documents: label is Present");


				}
				else if(data[3].contains("verifyCountryReadView")&& data[4].equalsIgnoreCase("No")){
					//Click on Country
					if(driver.findElements(country).size() !=0){
						System.out.println("Country label is Present");
					}

				}else{
					log.info("Country label is Absent");
				}
			}


			catch(Exception e){  fail=true;
			log.error("verifyCountryReadView()", e);
			return false;
			}
			return true;
		}

		public static boolean SetState_Country(String[] data) throws Exception{
			try{
				//Refresh the page
				driver.navigate().refresh();

				//switch to default content
				driver.switchTo().defaultContent();

				//switch to side bar frame
				driver.switchTo().frame("sidebarframe");


				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

				//Click country
				driver.findElement(country).click();

				//switch to default frame
				driver.switchTo().defaultContent();

				//switch to content frame
				driver.switchTo().frame("contentframe");



				if(data[3].contains("SetState_Country")&& data[4].equalsIgnoreCase("Yes")){

					//Click on new Link
					CommonFunctions.clickButtonOrLink(newLink, "link", "New");
					wait = new WebDriverWait(driver, 10);
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingCreateNewCountry));
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));

					//Enter the value for name
					driver.findElement(countryname).click();
					CommonFunctions.enterTextInTextbox(countryname, data[6]);

					//Enter the value for SAPCountryAbbreviation
					driver.findElement(SAPCountryAbbreviation).click();
					CommonFunctions.enterTextInTextbox(SAPCountryAbbreviation, data[7]);

					//click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");

					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to side bar frame
					driver.switchTo().frame("sidebarframe");


					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

					//Click country
					driver.findElement(country).click();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//enter name
					CommonFunctions.enterTextInTextbox(countrysearchname, data[6]);

					//Click on search
					CommonFunctions.clickButtonOrLink(Supplier.search, "btn", "Search");

					//Click on Actions menu
					driver.findElement(countryactions).click();
					//Click on Change State
					CommonFunctions.selectFromDropDownByVisibleText(countryactions, data[5]);

					//Select Released state from the Update Lifecycle dropdown
					CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, data[8]);

					//click Update in Change State wizard
					driver.findElement(countrystateupdate).click();

					//verification
					String country_LCStateafter = driver.findElement(RO_LCState).getText();
					Assert.assertEquals(country_LCStateafter, data[8]);
				}
				else if(data[3].contains("SetState_Country")&& data[4].equalsIgnoreCase("No")){	

					//Click on search
					CommonFunctions.clickButtonOrLink(Supplier.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Supplier.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();

					//Click on Actions menu
					driver.findElement(countryactions).click();
					boolean changestate=CommonFunctions.selectFromDropDownByVisibleText(countryactions, data[5]);
					Assert.assertFalse(changestate);
				}
				else
					log.info("SetState or changeState is not applicable(NA)");


			}catch(Exception e){  fail=true;
			log.error("SetState_Country()", e);
			return false;
			}
			return true;
		}

		public static boolean Delete_Country(String[] data) throws Exception{
			try{

				//Refresh the page
				driver.navigate().refresh();

				//switch to default content
				driver.switchTo().defaultContent();

				//switch to side bar frame
				driver.switchTo().frame("sidebarframe");

				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

				//Click country
				driver.findElement(country).click();

				//switch to default frame
				driver.switchTo().defaultContent();

				//switch to content frame
				driver.switchTo().frame("contentframe");

				if(data[3].contains("DeleteCountry")&& data[4].equalsIgnoreCase("Yes")){

					//Click on new Link
					CommonFunctions.clickButtonOrLink(newLink, "link", "New");
					wait = new WebDriverWait(driver, 10);
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingCreateNewCountry));
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));

					//Enter the value for name
					driver.findElement(countryname).click();
					CommonFunctions.enterTextInTextbox(countryname, data[6]);

					//Enter the value for SAPCountryAbbreviation
					driver.findElement(SAPCountryAbbreviation).click();
					CommonFunctions.enterTextInTextbox(SAPCountryAbbreviation, data[7]);

					//click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");

					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to side bar frame
					driver.switchTo().frame("sidebarframe");


					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

					//Click country
					driver.findElement(country).click();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//enter name
					CommonFunctions.enterTextInTextbox(countrysearchname, data[6]);

					//Click on search
					CommonFunctions.clickButtonOrLink(Supplier.search, "btn", "Search");

					//Click on Actions menu
					driver.findElement(countryactions).click();
					//Click on Delete
					CommonFunctions.selectFromDropDownByVisibleText(countryactions, data[5]);					
					CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
					driver.findElement(countrydelete).click();
					Thread.sleep(2000);
					Boolean bAlert = CommonFunctions.AlertPopUpPresent();			
					Assert.assertTrue(bAlert);

					//Click on delete and complete the action
					driver.findElement(countrydelete).click();
					wait.until(ExpectedConditions.alertIsPresent());
					//Alert alt=SeleniumDriver.driver.switchTo().alert();
					//alt.accept();
					CommonFunctions.handleAlertPopUp();

				}
				else if(data[3].contains("DeleteCountry")&& data[4].equalsIgnoreCase("No")){

					//Click on search
					CommonFunctions.clickButtonOrLink(Supplier.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Supplier.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();

					//Click on Actions menu
					driver.findElement(countryactions).click();
					boolean deletecountry=CommonFunctions.selectFromDropDownByVisibleText(countryactions, data[5]);
					Assert.assertFalse(deletecountry);

				}

			}catch(Exception e){  fail=true;
			log.error("Delete_Country()", e);
			return false;
			}
			return true;
		}


		public static boolean Read_Country_Identification(String[] data) throws Exception{
			try{

				//Refresh the page
				driver.navigate().refresh();

				//switch to default content
				driver.switchTo().defaultContent();
				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

				if(data[3].contains("Read_Country_Identification")&& data[4].equalsIgnoreCase("Yes")){//Read_View
					//Click on Country
					driver.findElement(country).click();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//Click on search
					CommonFunctions.clickButtonOrLink(Supplier.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Supplier.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();

					//Country Identification
					String CI=driver.findElement(countryidentification).getText();
					System.out.println(CI);
					System.out.println("Country Identification");
					//Verify that Country Identification table exist
					Assert.assertEquals(CI, "Country Identification");
					log.info("Country Identification label is Present");			

				}
				else if(data[3].contains("Read_Country_Identification")&& data[4].equalsIgnoreCase("No")){
					//Click on Country
					if(driver.findElements(country).size() !=0){
						System.out.println("Country label is Present");
					}

					else{
						log.info("Country label is Absent");
					}
				}

			}
			catch(Exception e){  fail=true;
			log.error("Read_Country_Identification()", e);
			return false;
			}
			return true;
		}


		public static boolean Update_Country_Identification(String[] data) throws Exception{
			try{

				//Refresh the page
				driver.navigate().refresh();

				//switch to default content
				driver.switchTo().defaultContent();

				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");

				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

				//Click on Country
				driver.findElement(country).click();


				//switch to default frame
				driver.switchTo().defaultContent();

				//switch to content frame
				driver.switchTo().frame("contentframe");


				if(data[3].contains("Update_Country_Identification")&& data[4].equalsIgnoreCase("Yes")){//Read_View

					//Click on new Link
					CommonFunctions.clickButtonOrLink(newLink, "link", "New");
					wait = new WebDriverWait(driver, 10);
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingCreateNewCountry));
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));

					//Enter the value for name
					driver.findElement(countryname).click();
					CommonFunctions.enterTextInTextbox(countryname, data[6]);

					//Enter the value for SAPCountryAbbreviation
					driver.findElement(SAPCountryAbbreviation).click();
					CommonFunctions.enterTextInTextbox(SAPCountryAbbreviation, data[7]);

					//click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");

					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to side bar frame
					driver.switchTo().frame("sidebarframe");


					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

					//Click country
					driver.findElement(country).click();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//enter name
					CommonFunctions.enterTextInTextbox(countrysearchname, data[6]);

					//Click on search
					CommonFunctions.clickButtonOrLink(Supplier.search, "btn", "Search");

					//Country Identification
					String CI=driver.findElement(countryidentification).getText();

					//Click on Actions
					driver.findElement(countryactions).click();
					//Click on Update
					CommonFunctions.selectFromDropDownByVisibleText(countryactions, data[5]);

					CommonFunctions.waitForVisibilityOfElement(countryupdatepage);

					//clear the Name value
					driver.findElement(countrynameupdate).clear();

					//Update the value of Country Identification
					CommonFunctions.enterTextInTextbox(countrynameupdate, data[9]);
					driver.findElement(save).click();

					//Get the Updated value for Country Identification
					String CIupdate=driver.findElement(countryname2).getText();
					
					//Print the conutry name 
					log.info("CIupdate: "+CIupdate);
					log.info("data: "+data[9]);
					
					//Compare the updated value
					Assert.assertEquals(CIupdate, data[9]);			

					//Verify that earlier and new updated value are not same
					//Assert.assertNotEquals(CI, CIupdate);

				}
				else if(data[3].contains("Update_Country_Identification")&& data[4].equalsIgnoreCase("No")){

					//Click on search
					CommonFunctions.clickButtonOrLink(Supplier.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Supplier.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();

					//Click on Actions
					driver.findElement(countryactions).click();
					//Click on Update
					boolean updatecountry=CommonFunctions.selectFromDropDownByVisibleText(countryactions, data[5]);

					Assert.assertFalse(updatecountry);		
				}

			}
			catch(Exception e){  fail=true;
			log.error("Update_Country_Identification()", e);
			return false;
			}
			return true;
		}



		public static boolean Read_Country_General_Attribute(String[] data) throws Exception{
			try{

				//Refresh the page
				driver.navigate().refresh();

				//switch to default content
				driver.switchTo().defaultContent();

				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");

				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

				if(data[3].contains("Read_Country_General_Attribute")&& data[4].equalsIgnoreCase("Yes")){

					//Read View

					//Click on Country
					driver.findElement(country).click();

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//Click on search
					CommonFunctions.clickButtonOrLink(Supplier.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Supplier.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();

					//General Attribute table
					String CountryGALabel=driver.findElement(countrygeneralattribute).getText();
					System.out.println(CountryGALabel);
					System.out.println("General Attributes:");
					//Verify that General Attribute table exist
					Assert.assertEquals(CountryGALabel.trim(), "General Attributes:");
					log.info("General Attributes label is Present");


				}
				else if(data[3].contains("Read_Country_General_Attribute")&& data[4].equalsIgnoreCase("No")){
					//Click on Country
					if(driver.findElements(country).size() !=0){
						System.out.println("Country label is Present");
					}

				}else{
					log.info("Country label is Absent");
				}
			}


			catch(Exception e){  fail=true;
			log.error("Read_General_Attribute()", e);
			return false;
			}
			return true;
		}


		public static boolean Update_Country_General_Attribute(String[] data) throws Exception{
			try{

				//Refresh the page
				driver.navigate().refresh();

				//switch to default content
				driver.switchTo().defaultContent();

				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

				//Click on Country
				driver.findElement(country).click();

				//switch to default frame
				driver.switchTo().defaultContent();

				//switch to content frame
				driver.switchTo().frame("contentframe");



				if(data[3].contains("Update_Country_General_Attribute")&& data[4].equalsIgnoreCase("Yes")){//Read_View


					//Click on new Link
					CommonFunctions.clickButtonOrLink(newLink, "link", "New");
					wait = new WebDriverWait(driver, 10);
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingCreateNewCountry));
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));

					//Enter the value for name
					driver.findElement(countryname).click();
					CommonFunctions.enterTextInTextbox(countryname, data[6]);

					//Enter the value for SAPCountryAbbreviation
					driver.findElement(SAPCountryAbbreviation).click();
					CommonFunctions.enterTextInTextbox(SAPCountryAbbreviation, data[7]);

					//click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");

					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to side bar frame
					driver.switchTo().frame("sidebarframe");


					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

					//Click country
					driver.findElement(country).click();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//enter name
					CommonFunctions.enterTextInTextbox(countrysearchname, data[6]);

					//Click on search
					CommonFunctions.clickButtonOrLink(Supplier.search, "btn", "Search");

					//General Attribute
					//String SAP=driver.findElement(SAPCountryAbbreviationread).getText();

					//Click on Actions
					driver.findElement(countryactions).click();

					//Click on Update
					CommonFunctions.selectFromDropDownByVisibleText(countryactions, data[5]);

					CommonFunctions.waitForVisibilityOfElement(countryupdatepage);

					//Clear the Sap Abbreviation attribute value
					driver.findElement(SAPCountryAbbreviationupdate).clear();

					//Update the value of Country Identification
					CommonFunctions.enterTextInTextbox(SAPCountryAbbreviationupdate, data[9]);
					driver.findElement(save).click();

					//Get the Updated value for Country Identification
					String SAPUpdate=driver.findElement(SAPCountryAbbreviationread).getText();

					//Compare the updated value
					Assert.assertEquals(SAPUpdate, data[9]);


				}
				else if(data[3].contains("Update_Country_General_Attribute")&& data[4].equalsIgnoreCase("No")){

					//Click on search
					CommonFunctions.clickButtonOrLink(Supplier.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Supplier.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();

					//Click on Actions
					driver.findElement(countryactions).click();
					//Click on Update
					boolean updatecountry=CommonFunctions.selectFromDropDownByVisibleText(countryactions, data[5]);

					Assert.assertFalse(updatecountry);		
				}

			}
			catch(Exception e){  fail=true;
			log.error("Update_General_Attribute()", e);
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
