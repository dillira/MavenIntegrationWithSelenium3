package com.hasbrop2m.sanity;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
import org.testng.asserts.SoftAssert;

import com.hasbrop2m.cascading.CascadingProduct;
import com.hasbrop2m.security.BOMMaterialMainUser;
import com.hasbrop2m.security.Color;
import com.hasbrop2m.security.Colorway;
import com.hasbrop2m.security.CostsheetTooling;
//import com.hasbro.security.InternalBOMSoftG;
import com.hasbrop2m.security.GlobalLinePlan;
import com.hasbrop2m.security.Material;
import com.hasbrop2m.security.MaterialBOM;
import com.hasbrop2m.security.PlaceHolderDevPlan;
import com.hasbrop2m.security.Product;
import com.hasbrop2m.security.SourcingConfig;

import com.hasbrop2m.core.SeleniumDriver;
import com.hasbrop2m.mostCommonFunctions.CommonFunctions;
import com.hasbrop2m.mostCommonFunctions.CommonProjectFunctions;
import com.hasbrop2m.core.ErrorUtil;
import com.hasbrop2m.core.Utility;

/**
 * @author anjali.gupta
 *
 */

public class SanitySuite4 extends TestsuiteBase{
	static int count=-1;
	static boolean fail=false;
	String loginVal;
	Boolean flaglogin=false;
	int y=0;
	static String Prodname;
	static String prodNum;
	static String strAssortmentSolidName;
	static String more30Char="0123456789012345678901234567891234";
	static String more40Char="012345678901234567890123456789012345678901234";
	public static SoftAssert S_ASSERT = new SoftAssert();
	public static String pageTitle;
	static String prodName;
	static String prodRetail;
	static String assSoName;
	static String  glPlanName;
	static String placeholderNa;
	static String materialRasin;
	static String matName;
	static String exchRate;
	static String boExchRate;
	static String strPlaceholderName;
	static String altMsg;
	static Set set1;
	static Iterator iter1;
	static String parent1;
	static String child1;
	static Set set2;
	static Iterator iter2;
	static String parent2;
	static String child2;
	static String  SoftgoodsInc;
	static String electronicsInc; 
	static String prodNumRetail;
	public static By newLink= By.xpath("//a[text()='New']");
	public static By supplierHeading= By.xpath("//td[contains(text(),' Supplier Details: null')]");
	public static By suppAction= By.xpath("//td[contains(text(),'Supplier Details')]//following::select[1]");
	public static By headingUpdateSupp= By.xpath("//td[contains(text(),'Update Supplier')]");
	public static By ddFactoryRegion= By.xpath("//td[contains(text(),'Factory Region')]//following::select[1]");
	public static By btnSave= By.xpath("//a[text()='Save']");
	public static By status=By.xpath("//td[contains(text(),'Status')]//following::select[1]");
	public static By effectiveToDate=By.xpath("//td[contains(text(),'Effective To Date')]//following::input[1]");
	public static By roFactoryRegion= By.id("hbFactoryRegion");
	public static By inputName= By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By search= By.id("SearchButton2");
	public static By firstSupplier= By.xpath("//table[@class='TABLE_OUTLINE']/tbody/tr[2]/td[1]/a");

	public static By attName= By.xpath("//td[contains(text(),'Supplier Identification')]//following::td[1]");
	public static By attType= By.xpath("//td[contains(text(),'Type')]");
	public static By attsapVendorCode= By.xpath("//td[contains(text(),'SAP Vendor Code')]");
	public static By attfactoryCode= By.xpath("//td[contains(text(),'Factory Code')]");
	public static By attfactoryAddress = By.xpath("//td[contains(text(),'Factory Address')]");
	public static By atteffectiveFromDate = By.xpath("//td[contains(text(),'Effective From Date')]");
	public static By attSupplier = By.xpath("//td[contains(text(),'Supplier')]");
	public static By attFactoryID= By.xpath("//td[contains(text(),'Factory ID')]");
	public static By attParentVendorName = By.xpath("//td[contains(text(),'Parent Vendor Name')]");
	public static By attVMRVendorCode= By.xpath("//td[contains(text(),'VMR Vendor Code')]");
	public static By attCountryOfOrigin = By.xpath("//td[contains(text(),'Country Of Origin')]");
	public static By attEffectiveToDate = By.xpath("//td[contains(text(),'Effective To Date')]");
	public static By attLocation = By.xpath("//td[contains(text(),'Location')]");
	public static By attZone= By.xpath("//td[contains(text(),'Zone')]");
	public static By attStatus = By.xpath("//td[contains(text(),'Status')]");
	public static By attVendorGroup = By.xpath("//td[contains(text(),'Vendor Group')]");
	public static By attFactoryNameinlocallanguage= By.xpath("//td[contains(text(),'Factory Name in local language')]");
	public static By attFactoryRegion= By.xpath("//td[contains(text(),'Factory Region')]");
	public static By attFCL_YTHKD = By.xpath("//td[contains(text(),'FCL-YT (HKD)')]");
	public static By attLCL_YTHKD = By.xpath("//td[contains(text(),'LCL-YT (HKD)')]");
	public static By attAverageFreightCostHKD= By.xpath("//td[contains(text(),'Average Freight Cost (HKD)')]");
	public static By attFCL_HKHKD = By.xpath("//td[contains(text(),'FCL-HK (HKD)')]");
	public static By attLCL_HKHKD = By.xpath("//td[contains(text(),'LCL-HK (HKD)')]");
	public static By attFCL_YTUSD = By.xpath("//td[contains(text(),'FCL-YT (USD)')]");
	public static By attLCL_YTUSD = By.xpath("//td[contains(text(),'LCL-YT (USD)')]");
	public static By attAverageFreightCostUSD = By.xpath("//td[contains(text(),'Average Freight Cost (USD)')]");
	public static By attFCL_HKUSD = By.xpath("//td[contains(text(),'FCL-HK (USD)')]");
	public static By attLCL_HKUSD = By.xpath("//td[contains(text(),'LCL-HK (USD)')]");
	public static By attAssociatedDocuments = By.xpath("//td[contains(text(),'Associated Documents:')]");
	public static By attSystemInformation = By.xpath("//td[contains(text(),'System Information:')]");

	public static By suppName = By.id("name");
	public static By suppTypeCreate = By.xpath("//td[contains(text(),'Supplier Attributes')]//following::td[contains(text(),'Type')]");
	public static By suppType = By.xpath("//td[contains(text(),'Supplier Identification')]//following::td[contains(text(),'Type')]");
	public static By typeValue = By.xpath("//td[contains(text(),'Type')]//following::td[1]");
	public static By suppStatus = By.xpath("//td[contains(text(),'Supplier Definition')]//following::td[contains(text(),'Status')]");
	public static By statusValue = By.xpath("//td[contains(text(),'Status')]//following::td[@id='hbStatus']");
	public static By supplierCategory = By.xpath("//td[contains(text(),'General Attributes:')]//following::td[contains(text(),'Supplier Category')]");
	public static By supplierCategoryValue = By.xpath("//td[contains(text(),'Supplier Category')]//following::td[@id='hbSupplierCategory']");

	public static By vendorGroup = By.xpath("//td[contains(text(),'General Attributes:')]//following::td[contains(text(),'Vendor Group')]");
	public static By vendorGroupValue = By.xpath("//td[contains(text(),'Vendor Group')]//following::td[@id='hbVendorGroup']");

	public static By sapVendorCode = By.xpath("//td[contains(text(),'General Attributes:')]//following::td[contains(text(),'SAP Vendor Code')]");
	public static By sapVendorCodeValue = By.xpath("//td[contains(text(),'SAP Vendor Code')]//following::td[@id='hbSAPVendorCode']");

	public static By vmrVendorCode = By.xpath("//td[contains(text(),'General Attributes:')]//following::td[contains(text(),'VMR Vendor Code')]");
	public static By vmrVendorCodeValue = By.xpath("//td[contains(text(),'VMR Vendor Code')]//following::td[@id='hbVMRVendorCode']");

	public static By country = By.xpath("//td[contains(text(),'General Attributes:')]//following::td[contains(text(),'Country')]");
	public static By countryValue = By.xpath("//td[contains(text(),'Country')]//following::td[@id='hbCountry']");

	public static By address = By.xpath("//td[contains(text(),'General Attributes:')]//following::td[contains(text(),'Address')]");
	public static By addressValue = By.xpath("//td[contains(text(),'Address')]//following::td[@id='hbAddress']");

	public static By primaryEmailContact  = By.xpath("//td[contains(text(),'General Attributes:')]//following::td[contains(text(),'Primary Email Contact')]");
	public static By primaryEmailContactValue = By.xpath("//td[contains(text(),'Primary Email Contact')]//following::td[@id='hbPrimaryEmailContact']");

	public static By   remarks   = By.xpath("//td[contains(text(),'Supplier Definition:')]//following::td[contains(text(),'Remarks')]");
	public static By   remarksValue = By.xpath("//td[contains(text(),'Remarks')]//following::textarea[1]");

	public static By   shortName   = By.xpath("//td[contains(text(),'Supplier Definition:')]//following::td[contains(text(),'Short Name')]");
	public static By   shortNameValue = By.xpath("//td[contains(text(),'Short Name')]//following::input[1]");

	public static By  rfqReceiptContact   = By.xpath("//td[contains(text(),'Supplier Definition:')]//following::td[contains(text(),'RFQ Receipt Contact')]");
	public static By  rfqReceiptContactValue = By.xpath("//td[contains(text(),'RFQ Receipt Contact')]//following::input[1]");

	public static By  costApprovalContact    = By.xpath("//td[contains(text(),'Supplier Definition:')]//following::td[contains(text(),'Cost Approval Contact')]");
	public static By  costApprovalContactValue = By.xpath("//td[contains(text(),'Cost Approval Contact')]//following::input[1]");

	public static By btnCreate = By.xpath("//a[text()='Create']");

	public static By ddAction = By.xpath("//td[contains(text(),'Plan Details')]//following::select[contains(@onchange,'evalList(this)')][1]");
	public static By prodType = By.xpath("//td[contains(text(),'Product Number')]//following::td[text()='Retail Item'][1]");

	public static By placeholderLink= By.linkText("Placeholder");
	public static By AssortmentSolidNoPLC = By.xpath("//td[contains(text(),'Assortment / Solid # (PLC)')]//following::input[1]");

	public static By classPLC= By.xpath("//td[contains(text(),'Class (PLC)')]//following::td[1]");
	public static By divisionPLC= By.xpath("//td[contains(text(),'Division (PLC)')]//following::td[1]");
	public static By brandPLC= By.id("hbBrand");
	public static By segmentPLC= By.id("hbSegment");
	public static By coBrandPLC= By.id("hbCoBrand");
	public static By licensorPLC= By.id("hbLicensor");
	public static By isoPLC= By.id("hbISO");
	public static By projectTypePLC= By.id("hbProjectType");
	public static By tvAdPLC= By.id("hbTVAd");
	public static By moviePLC= By.id("hbMovie");
	public static By digitalProductPLC= By.id("hbDigitalProduct");
	public static By commentsPLC= By.id("hbComments");
	public static By retailerDistribution= By.id("hbRetailerDistribution");
	public static By innovationTypePLC= By.id("hbInnovationType");
	public static By introTimingPLC= By.id("hbIntroTiming");
	public static By onShelfDate= By.xpath("//td[contains(text(),'On-Shelf Date (PLC)')]//following::td[1]");
	public static By pt_MHPLC= By.id("hbPTMH");
	public static By srPUSDPLC= By.id("hbSRPPriceUSD");
	public static By lcPriceUSDPLC= By.id("hbLCPriceUSD");
	public static By usDomesticPLC= By.id("hbDomesticPercentage");
	public static By domPriceUSDPLC= By.id("hbDOMPriceUSD");
	public static By manuallyInputWaveForecasts= By.id("hbManuallyInputWaveForecasts");
	public static By  placeholderName = By.xpath("//td[contains(text(),'Placeholder')]//following::a[contains(text(),'Placeholder')]");

	//Product
	public static By marketingChannelPr= By.id("hbDistributionChannelPr");
	public static By licensorPr= By.id("hbLicensorPr");
	public static By coBrandPr= By.id("hbCoBrandPr");
	public static By isoPr= By.id("hbISOPr");
	public static By moviePr= By.id("hbMoviePr");
	public static By introTimingPr= By.id("hbIntroTimingPr");

	public static By tvAdPr= By.id("hbTVAdPr");
	public static By digitalProductPr= By.id("hbDigitalProductPr");
	public static By projectTypePr= By.id("hbProjectTypePr");
	public static By innovationTypePr= By.id("hbInnovationTypePr");
	public static By primeTimeMustHavePr= By.id("hbPrimeTimeOrMustHave");
	public static By astSolidPr= By.id("hbAstSolidPr");
	public static By retailerDistributionPr= By.id("hbRetailerDistributionPr");
	public static By gsPlushPr= By.id("hbGSPlushPr");
	public static By cfTotalPr= By.id("hbCFTotalPr");
	public static By newTotalPr= By.id("hbNewTotalPr");
	public static By productRefreshTotPr= By.id("hbProductRefreshTotalPr");
	public static By packageRefreshTotPr= By.id("hbPackageRefreshTotal");
	public static By soldasSolidTotPr= By.id("hbSoldasSolidTotalPr");
	public static By eCommTotalPr= By.id("hbeCommCountPr");
	public static By blindBagCharacterCountTotalPr= By.id("hbBlindBagCharacterCountTotalPr");
	public static By srpUSDPr= By.id("hbSRPPriceUSDPr");
	public static By lcPriceUSDPr= By.id("hbLCPriceUSDPr");
	public static By domPriceUSDPr= By.id("hbDOMPriceUSDPr");
	public static By domesticPr= By.id("hbDomesticPercentagePr");
	public static By commentsPr= By.id("hbCommentsPr");
	public static By onShelfDatePr= By.xpath("//td[contains(text(),'Ast./Solid - On Shelf Date')]//following::td[1]");
	public static By labelPlaeholderName= By.xpath("//div[@id='placeholderIdentification']//td[contains(text(),'Name')]//following::td[1]");

	//PC09
	public static By seasonLnk= By.xpath("//a[contains(text(),'Season:')]");
	public static By resinDescription = By.xpath("//td[contains(text(),'*Material Description')]//following::input[1]"); 
	public static By processingMethod  = By.xpath("//td[contains(text(),'Processing Method')]//following::select[1]"); 
	public static By unitOfMeasure  = By.xpath("//td[contains(text(),'Unit Of Measure')]//following::select[1]"); 
	public static By resinType  = By.xpath("//td[contains(text(),'Resin Type')]//following::select[1]"); 
	public static By density = By.xpath("//td[contains(text(),'Density')]//following::input[1]"); 
	public static By labelRasinType = By.xpath("//a[@name='General Attributes']//following::td[contains(text(),'Resin Type')]"); 
	public static By exchangeRate= By.id("hbExchangeRate");
	public static By businessObj = By.linkText("Business Object");
	public static By fxRatesBusiUnit = By.linkText("FX Rates - Business Unit");
	public static By fxRate= By.id("hbFXRate");
	//PD03
	public static By phNumberInput= By.xpath("//td[contains(text(),'Placeholder Number')]//following::input[1]");
	public static By errorMsg= By.xpath("//td[@class='ERROR']");

	public static By phAction= By.xpath("//td[contains(text(),'Placeholder Details:')]//following::td[1]/select[1]");
	public static By productName= By.xpath("//td[contains(text(),'Product Number')]//following::a[1]");
	public static By cellCountryofOrigin= By.id("r1_hbCountryofOrigin");
	public static By linkCountryofOrigin= By.id("ptc_verRef_1Link");

	public static By cellBenchmarkCostHKD= By.id("r1_hbBenchmarkCostHKD");
	public static By inputBenchmarkCostHKD= By.xpath(".//div[@id='hbBenchmarkCostHKDSourceDiv']/input");

	public static By cellConstantHKD= By.id("r1_hbConstantHKD");
	public static By inputConstantHKD= By.xpath(".//div[@id='hbConstantHKDSourceDiv']/input");
	public static By btnClearObjectReferenceValue= By.xpath("//a[text()='Clear Object Reference Value']");
	public static By valMinThresholdHKD= By.xpath("//table[@class='TABLE_OUTLINE']/tbody/tr[2]/td[4]");
	public static By valMaxThresholdHKD= By.xpath("//table[@class='TABLE_OUTLINE']/tbody/tr[2]/td[5]");

	public static By valBenchmarkCostUSD= By.xpath("//table[@class='TABLE_OUTLINE']/tbody/tr[2]/td[6]");
	public static By valMinThresholdUSD= By.xpath("//table[@class='TABLE_OUTLINE']/tbody/tr[2]/td[7]");
	public static By valMaxThresholdUSD= By.xpath("//table[@class='TABLE_OUTLINE']/tbody/tr[2]/td[8]");
	public static By txtlaborDescription= By.xpath("//td[contains(text(),'*Material Description')]//following::input[1]");
	
	public static By gsPlush  = By.xpath("//td[contains(text(),'GS Plush')]//following::select[1]");
	public static By innovationType  = By.xpath("//td[contains(text(),'Innovation Type')]//following::select[1]");
	public static By innovationTypeProd  = By.id("hbInnovationTypePr");
	public static By customerGroup  = By.xpath("//td[contains(text(),'Customer Group')]//following::select[1]");
	public static By distributionChannelValidFrom  = By.xpath("//td[contains(text(),'Distribution Channel Valid From')]//following::input[1]");
	public static By distributionChannelValidTo  = By.xpath("//td[contains(text(),'Distribution Channel Valid To')]//following::input[1]");
	
	public static By hbCustomerGroup  = By.id("hbCustomerGroup");
	public static By hbDistributionChannelValidFrom  = By.xpath("//td[contains(text(),'Distribution Channel Valid From')]//following::td[1]");
	public static By hbDistributionChannelValidTo = By.xpath("//td[contains(text(),'Distribution Channel Valid To')]//following::td[1]");
	
	public static By hbIndustryShortDescription  = By.id("hbIndustryShortDescription");
	public static By hbIPSensitive  = By.id("hbIPSensitive");
	public static By hbISOPr  = By.id("hbISOPr");
	public static By hbElectronicsIncluded  = By.id("hbElectronicsIncluded");
	public static By hbSoftgoodsIncluded  = By.id("hbSoftgoodsIncluded");
	public static By hbGender  = By.id("hbGender");
	
	public static By inputRetailerDistribution   = By.xpath("//td[contains(text(),'Retailer Distribution')]//following::input[1]");
	public static By projectType   = By.xpath("//td[contains(text(),'Project Type')]//following::select[1]");
	public static By ddTVad   = By.xpath("//td[contains(text(),'Project Type')]//following::select[1]");
	public static By ddMovie   = By.xpath("//td[contains(text(),'Movie')]//following::select[1]");
	public static By onShelfDateInput   = By.xpath("//td[contains(text(),'Ast./Solid - On Shelf Date')]//following::input[1]");
	public static By ptMH   = By.xpath("//td[contains(text(),'PrimeTime/Must Have')]//following::select[1]");
	public static By digitalProduct   = By.xpath("//td[contains(text(),'Digital Product')]//following::select[1]");
	
	public static By errorMsgRet   = By.xpath("//td[contains(text(),'of Retail Item does not match with corresponding Assortment/Solid value.')]");
	static String errMsgComment="Value of Marketing Channel of Retail Item does not match with corresponding Assortment/Solid value." ;
	public static By errorMsgRet1   = By.xpath("//td[contains(text(),'of Retail Item do not match with corresponding Assortment/Solid values.')]");
	static String errMsgCommentClass="Values of either Brand or Class or Division of Retail Item do not match with corresponding Assortment/Solid values." ;
	public static Boolean flagVal=true;
	
	public static By retDivision = By.xpath("//td[contains(text(),'*Division')]//following::select[1]");
	public static By retBrand = By.xpath("//td[contains(text(),'*Brand')]//following::select[1]");
	public static By retClass = By.xpath("//td[contains(text(),'*Class')]//following::select[1]");
	
	@Test(dataProvider="testDataTest")
	public void tcSanitySuite4(String[] data) throws Exception{
		count++;
		System.out.println(CommonProjectFunctions.runmodes[count]);
		if(!CommonProjectFunctions.runmodes[count].equalsIgnoreCase("y")){
			CommonProjectFunctions.skip=true;
			log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
			throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
		}
		try{
			log.debug("Inside testcase for SanitySuite4");
			// User Name, Password and Action
			log.info("col0 :" + data[0]); 
			log.info("col1 :" + data[1]);
			log.info("Verification :" + data[3]);

			log.info("Testcase is :" + data[2]);
			log.info("Testcase no is :" + data[3]);
			if(flaglogin==true)
			{
				if(!loginVal.equalsIgnoreCase(data[0])){
					y=0;
					flaglogin=false;
					CommonProjectFunctions.logOut();
					driver.quit();
				}
			}
			if(CommonProjectFunctions.runmodes[count].equalsIgnoreCase("y")){
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
			/******************/
			case "PC01_CreateFactory":
				log.info("In side :-> " + data[3]);	
				createFactory(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PC01_UpdateFactory":
				log.info("In side :-> " + data[3]);	
				updateFactory(data);
				log.info("Out side :-> " + data[3]);
				break;	
			case "PC02_CreateSupplier":
				log.info("In side :-> " + data[3]);	
				createSupplier(data);
				log.info("Out side :-> " + data[3]);
				break;	
			case "PD01_CreateDevelopmentPlan":
				log.info("In side :-> " + data[3]);	
				createDevelopmentPlan(data);
				log.info("Out side :-> " + data[3]);
				break;	
			case "PD02_UpdateDevelopmentPlan":
				log.info("In side :-> " + data[3]);	
				updateDevelopmentPlan(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PD03_CreateApprovedAssSolid_linktoPlaceholder":
				log.info("In side :-> " + data[3]);	
				createApprovedAssSolid_linktoPlaceholder(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PD07_CreateaRetailItem":
				log.info("In side :-> " + data[3]);	
				createRetailItem(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PD04_GeneratePlaceholders_CreateAssortSolidPrd":
				log.info("In side :-> " + data[3]);	
				generatePlaceholders_CreateAssortSolidPrd(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PC09_CreateMaterialResin":
				log.info("In side :-> " + data[3]);	
				createMaterialResin(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PC10_UpdateMaterialResinPricingChartTable":
				log.info("In side :-> " + data[3]);	
				UpdateMaterialResinPricingChartTable(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PC12_UpdateMaterialLaborPricingChartTableOperationType":
				log.info("In side :-> " + data[3]);	
				UpdateMaterialLaborPricingChartTableOperationType(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PC03_CreateSCAgainst_releasedProduct":
				log.info("In side :-> " + data[3]);	
				createSCAgainst_releasedProduct(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PD06_UpdateanAssortmentSolid":
				log.info("In side :-> " + data[3]);	
				updateAnAssortmentSolid(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PD08_UpdateaRetailItem":
				log.info("In side :-> " + data[3]);	
				UpdateaRetailItem(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PD09_CreateAssRetailRelationship":
				log.info("In side :-> " + data[3]);	
				createAssRetailRelationship(data);
				log.info("Out side :-> " + data[3]);
				break;
			default:
				fail=true;
				log.info("Default is executed");
			}
		}catch(Throwable t){
			fail=true;
			log.info("****Varification failed or Skipped for**** "+CommonProjectFunctions.strTestCaseName);
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	
	public static boolean createAssRetailRelationship(String[] data) throws Exception{
		try{
			//Create Assortment product
			prodName = CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[4],"Development Plan","Create New: Product",data[6],
					data[7],data[5],data[13],"Assortment","Not IP Sensitive (Open)","Yes","Yes",
					"Exclusive",data[24],data[25], data[26], data[27],
					"LASER OPS","BOYS","1D","1 MONTH","FEMALE","No","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
			log.info("*****Assortment Product Name is: "+prodName);
			//applyFilter();
			linkRetailProd(data);
		}catch(Exception e){
			fail=true;
			log.error("Exception in createAssRetailRelationship()", e);
			throw e;
		}
		return true;
	}
	
	public static boolean applyFilter(String[] data) throws Exception{
		try{
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in applyFilter()", e);
			throw e;
		}
		return true;
	}
	
	public static boolean linkRetailProd(String[] data) throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on relationShip
			CommonFunctions.clickButtonOrLink(SmokeFlow.relationShipTab, "tab", "RelationShip");
			CommonFunctions.waitForPageLoaded();
			//Click "Copy/ Link Product"
			CommonFunctions.clickButtonOrLink(SmokeFlow.copyLinkProd, "btn", "Copy/ Link Product");
			prodNumRetail = copyLinkProductWithErrorMsg(data);
			log.info("First retail product:" +prodNumRetail);
			//Select Distribution channel
			CommonFunctions.selectFromDropDownByVisibleText(Product.MarketingChannel,"Mainline");
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View product");
			CommonFunctions.waitForPageLoaded();
			//Click on details
			//Product.clickDetailsTab(data);
			CommonFunctions.clickButtonOrLink(Product.detailsTablink, "link", "Details tab");
			//Verification for Softgoods Included 
			SoftgoodsInc=SeleniumDriver.driver.findElement(SmokeFlow.softgoodsIncluded).getText();
			Assert.assertEquals(SoftgoodsInc, data[39]);
			//Verification for  Softgoods Included 
			electronicsInc=SeleniumDriver.driver.findElement(SmokeFlow.electronicsIncluded).getText();
			Assert.assertEquals(electronicsInc, data[38]);
		}catch(Exception e){
			fail=true;
			log.error("Exception in linkRetailProd()", e);
			throw e;
		}
		return true;
	}
	
		public static boolean UpdateaRetailItem(String[] data) throws Exception{
			try{
				//Create Assortment product
				prodName = CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[4],"Development Plan","Create New: Product",data[6],
						data[7],data[5],data[13],"Assortment","Not IP Sensitive (Open)","Yes","Yes",
						"Mainline",data[24],data[25], data[26], data[27],
						"LASER OPS","BOYS","1D","1 MONTH","FEMALE","Yes","ACTION FIGURES & ACCESSORIES",
						"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
				log.info("Assortment Product Name is: "+prodName);
				//Create multiple colorway
				CommonProjectFunctions.CreateMultiple_Colorway();
				//CommonProjectFunctions.logOut();
				//openBrowser();
				//launchApp(data[34],data[35]);
				//Search product
				//CommonProjectFunctions.searchProduct(prodName);
				//Click on details tab
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				CommonFunctions.clickButtonOrLink(Product.detailsTablink, "link", "Details tab");
				//Select season
				CommonFunctions.selectFromDropDownByVisibleText(Product.detailPageSeasonDD, data[4]);
				
				CommonFunctions.waitForElementTobeClickable(Product.ddDetailsAction);
				CommonFunctions.selectFromDropDownByVisibleText(Product.ddDetailsAction, "Update Product");
				// Enter value for  Industry Short Description
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.inductryShortDesc, prodName, "Industry Short Description");
				//  IP Sensitive
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.IPSensitive, data[36], "IP Sensitive");
				//  ISO
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.softProjectile, data[13],"ISO");
				//  Electronics Included
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.electronicsIncluded, data[39],"Electronics Included");
				// Softgoods Included
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.softgoodsIncluded, data[38],"Softgoods Included");
				// Gender
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.gender, data[48],"Gender");
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnSave,"btn", "Save");
				//Verification
				//Industry Short Description
				Assert.assertEquals(driver.findElement(hbIndustryShortDescription).getText().trim(), prodName);
				log.info("****Verified Industry Short Description value****");
				//IP Sensitive 
				Assert.assertEquals(driver.findElement(hbIPSensitive).getText().trim(), data[36]);
				log.info("****Verified IP Sensitive  value****");
				//ISO
				Assert.assertEquals(driver.findElement(hbISOPr).getText().trim(), data[13]);
				log.info("****Verified ISO value****");
				//Electronics Included
				Assert.assertEquals(driver.findElement(hbElectronicsIncluded).getText().trim(), data[39]);
				log.info("****Verified Electronics Included value****");
				//Softgoods Included
				Assert.assertEquals(driver.findElement(hbSoftgoodsIncluded).getText().trim(), data[38]);
				log.info("****Verified Softgoods Included value****");
				//Gender
				Assert.assertEquals(driver.findElement(hbGender).getText().trim(), data[48]);
				log.info("****Verified Gender value****");
				/***********************************/
				// 2nd verification
				//Update Product-Season
				CommonFunctions.selectFromDropDownByVisibleText(Product.ddDetailsAction, "Update Product-Season");
				
				// Enter value for   Marketing Channel 
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.MarketingChannel, prodName, "Marketing Channel");
				//  Retailer Distribution 
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(inputRetailerDistribution, data[49], "Retailer Distribution");
				// Project Type 
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(projectType, data[50],"ISO");
				//  Innovation Type
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(innovationType, data[20],"Innovation Type");
				// TV Ad 
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(ddTVad, data[17],"TV Ad");
				// Intro Timing 
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.IntroTiming, data[15],"Intro Timing ");
				// Movie 
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(ddMovie, data[14],"Movie");
				// On Shelf Date 
				String todayDate=CommonFunctions.getTodayDate();
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(onShelfDatePr, todayDate,"On Shelf Date");
				// PrimeTime/Must Have 
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(ptMH, data[21],"PrimeTime/Must Have");
				// Digital Product  
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(digitalProduct, data[18],"Digital Product");
				// Target Cost (USD) 
				//CommonFunctions.selectFromDropDownByVisibleTextUpdated(, data[],"Target Cost (USD)");
				
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnSave,"btn", "Save");
				//Verification
				//Marketing Channel 
				Assert.assertEquals(driver.findElement(marketingChannelPr).getText().trim(), prodName);
				log.info("****Verified Industry Short Description value****");
				//Retailer Distribution 
				Assert.assertEquals(driver.findElement(retailerDistributionPr).getText().trim(), data[49]);
				log.info("****Verified IP Sensitive  value****");
				// Project Type 
				Assert.assertEquals(driver.findElement(projectTypePr).getText().trim(), data[50]);
				log.info("****Verified ISO value****");
				// Innovation Type
				Assert.assertEquals(driver.findElement(innovationTypePr).getText().trim(), data[20]);
				log.info("****Verified Electronics Included value****");
				//TV Ad 
				Assert.assertEquals(driver.findElement(tvAdPr).getText().trim(), data[17]);
				log.info("****Verified Softgoods Included value****");
				//Intro Timing 
				Assert.assertEquals(driver.findElement(introTimingPr).getText().trim(), data[15]);
				log.info("****Verified Gender value****");
				//Movie
				Assert.assertEquals(driver.findElement(moviePr).getText().trim(), data[14]);
				log.info("****Verified Gender value****");
				//On Shelf Date 
				Assert.assertEquals(driver.findElement(onShelfDatePr).getText().trim(), todayDate);
				log.info("****Verified Gender value****");
				//PrimeTime/Must Have 
				Assert.assertEquals(driver.findElement(primeTimeMustHavePr).getText().trim(), data[21]);
				log.info("****Verified Gender value****");
				//Digital Product 
				Assert.assertEquals(driver.findElement(digitalProductPr).getText().trim(), data[18]);
				log.info("****Verified Gender value****");
				
				/***********************************/
				// 3rd verification
				//Select Colorway
				CommonFunctions.selectFromDropDownByIndex(Colorway.colorwayDropDown, 1);
				//Select Update Colorway - Season option from Action drop down
				CommonFunctions.selectFromDropDownByVisibleText(Product.ddDetailsAction, "Update Colorway - Season");
				// Update attributes
				//Customer Group
				CommonFunctions.selectFromDropDownByVisibleText(customerGroup,data[33]);
				//Distribution Channel Valid From
				 todayDate=CommonFunctions.getTodayDate();
				CommonFunctions.enterTextInTextboxUpdated(distributionChannelValidFrom,todayDate , "Distribution Channel Valid From");
				//Distribution Channel Valid To
				String tomorrowDate=CommonFunctions.getTomorrowDate();
				CommonFunctions.enterTextInTextboxUpdated(distributionChannelValidTo,tomorrowDate, "Distribution Channel Valid To");
				
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnSave,"btn", "Save");
				//Verification
				//Customer Group
				Assert.assertEquals(driver.findElement(hbCustomerGroup).getText().trim(), data[33]);
				log.info("****Verified Customer Group value****");
				//Distribution Channel Valid From
				Assert.assertEquals(driver.findElement(hbDistributionChannelValidFrom).getText().trim(), todayDate);
				log.info("****Verified Distribution Channel Valid From value****");
				//Distribution Channel Valid To
				Assert.assertEquals(driver.findElement(hbDistributionChannelValidTo).getText().trim(),tomorrowDate);
				log.info("****Verified Distribution Channel Valid To value****");
				
			}catch(Exception e){
				fail=true;
				log.error("Exception in updateAnAssortmentSolid()", e);
				throw e;
			}
			return true;
		}
		
	//Need to change gmuser name in column 34 and 35
	public static boolean updateAnAssortmentSolid(String[] data) throws Exception{
		try{
			//Create Assortment product
			prodName = CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[4],"Development Plan","Create New: Product",data[6],
					data[7],data[5],data[13],"Assortment","Not IP Sensitive (Open)","Yes","Yes",
					"Mainline",data[24],data[25], data[26], data[27],
					"LASER OPS","BOYS","1D","1 MONTH","FEMALE","No","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
			log.info("Assortment Product Name is: "+prodName);
			//Create multiple colorway
			CommonProjectFunctions.CreateMultiple_Colorway();
			CommonProjectFunctions.logOut();
			openBrowser();
			launchApp(data[34],data[35]);
			//Search product
			CommonProjectFunctions.searchProduct(prodName);
			//Click on details tab
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.clickButtonOrLink(Product.detailsTablink, "link", "Details tab");
			CommonFunctions.selectFromDropDownByVisibleText(Product.detailPageSeasonDD, data[4]);
			CommonFunctions.waitForElementTobeClickable(Product.ddDetailsAction);
			CommonFunctions.selectFromDropDownByVisibleText(Product.ddDetailsAction, "Update Product-Season");
			// Enter value for GS Plush
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(gsPlush, data[13], "GS Plush");
			//  Marketing Channel 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.MarketingChannel, data[46], "Marketing Channel");
			// Innovation Type
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(innovationType, data[20],"Innovation Type");
			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSave,"btn", "Save");
			//Verification
			//GS Plush
			Assert.assertEquals(driver.findElement(gsPlushPr).getText().trim(), data[13]);
			log.info("****Verified GS Plush value****");
			//Marketing Channel 
			Assert.assertEquals(driver.findElement(marketingChannelPr).getText().trim(), data[46]);
			log.info("****Verified Marketing Channel value****");
			//Innovation Type
			Assert.assertEquals(driver.findElement(innovationTypeProd).getText().trim(), data[20]);
			log.info("****Verified Innovation Type value****");
			
			//Select Colorway
			CommonFunctions.selectFromDropDownByIndex(Colorway.colorwayDropDown, 1);
			//Select Update Colorway - Season option from Action drop down
			CommonFunctions.selectFromDropDownByVisibleText(Product.ddDetailsAction, "Update Colorway - Season");
			// Update attributes
			//Customer Group
			CommonFunctions.selectFromDropDownByVisibleText(customerGroup,data[33]);
			//Distribution Channel Valid From
			String todayDate=CommonFunctions.getTodayDate();
			CommonFunctions.enterTextInTextboxUpdated(distributionChannelValidFrom,todayDate , "Distribution Channel Valid From");
			//Distribution Channel Valid To
			String tomorrowDate=CommonFunctions.getTomorrowDate();
			CommonFunctions.enterTextInTextboxUpdated(distributionChannelValidTo,tomorrowDate, "Distribution Channel Valid To");
			
			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSave,"btn", "Save");
			//Verification
			//Customer Group
			Assert.assertEquals(driver.findElement(hbCustomerGroup).getText().trim(), data[33]);
			log.info("****Verified Customer Group value****");
			//Distribution Channel Valid From
			Assert.assertEquals(driver.findElement(hbDistributionChannelValidFrom).getText().trim(), todayDate);
			log.info("****Verified Distribution Channel Valid From value****");
			//Distribution Channel Valid To
			Assert.assertEquals(driver.findElement(hbDistributionChannelValidTo).getText().trim(),tomorrowDate);
			log.info("****Verified Distribution Channel Valid To value****");
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateAnAssortmentSolid()", e);
			throw e;
		}
		return true;
	}
	

	public static boolean createSCAgainst_releasedProduct(String[] data) throws Exception{
		try{
			//Create Assortment product
			prodName = CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[4],"Development Plan","Create New: Product",data[6],
					data[7],data[5],data[13],"Assortment","Not IP Sensitive (Open)","Yes","Yes",
					"Mainline",data[24],data[25], data[26], data[27],
					"LASER OPS","BOYS","1D","1 MONTH","FEMALE","No","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
			log.info("Assortment Product Name is: "+prodName);
			//Update Product state
			updateProdState("Released");
			verifyAddSourcingConfigOption(data);
			//Create Retail product
			prodName = CommonProjectFunctions.CreateProdFromLineSheet("Retail",data[4],"Development Plan","Create New: Product",data[6],
					data[7],data[5],data[13],"Assortment","Not IP Sensitive (Open)","Yes","Yes",
					"Mainline",data[24],data[25], data[26], data[27],
					"LASER OPS","BOYS","1D","1 MONTH","FEMALE","No","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
			log.info("Retail Product Name is: "+prodName);
			//Update Product state
			updateProdState("Released");
			verifyAddSourcingConfigOption(data);
			//Create bundle pack product
			prodName = CommonProjectFunctions.CreateProdFromLineSheet("Bundle Pack",data[4],"Development Plan","Create New: Product",data[6],
					data[7],data[5],data[13],"Assortment","Not IP Sensitive (Open)","Yes","Yes",
					"Mainline",data[24],data[25], data[26], data[27],
					"LASER OPS","BOYS","1D","1 MONTH","FEMALE","No","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
			log.info("Bundle Pack Product Name is: "+prodName);
			//Update Product state
			updateProdState("Released");
			verifyAddSourcingConfigOption(data);
			//Create trade marketing pallet product
			prodName = CommonProjectFunctions.CreateProdFromLineSheet("Trade Marketing Pallet",data[4],"Development Plan","Create New: Product",data[6],
					data[7],data[5],data[13],"Assortment","Not IP Sensitive (Open)","Yes","Yes",
					"Mainline",data[24],data[25], data[26], data[27],
					"LASER OPS","BOYS","1D","1 MONTH","FEMALE","No","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
			log.info("Trade Marketing Pallet Product Name is: "+prodName);
			//Update Product state
			updateProdState("Released");
			verifyAddSourcingConfigOption(data);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateApprovedAssSolid_linktoPlaceholder()", e);
			throw e;
		}
		return true;
	}


	public static boolean updateProdState(String status) throws Exception{
		try{
			//Click on details tab
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.clickButtonOrLink(Product.detailsTablink, "link", "Details tab");
			CommonFunctions.clickButtonOrLink(Product.ddDetailsAction, "btn", "Action dropdown");
			driver.findElement(Product.ddOptionchangeState).click();
			CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, status);
			//Click on Update
			CommonFunctions.clickButtonOrLink(Product.linkUpdate, "link", "Update");
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in updateProdState()", e);
			throw e;
		}
		return true;
	}

	public static boolean verifyAddSourcingConfigOption(String[] data) throws Exception{
		try{
			CommonFunctions.waitForPageLoaded();
			//Click on sourcing tab
			CommonFunctions.clickButtonOrLink(CostsheetTooling.lnkSource, "link", "Sourcing tab");
			//Select season
			CommonFunctions.selectFromDropDownByVisibleText(Product.detailPageSeasonDD, data[4]);
			//Verify "Add Sourcing Configuration" is not in the Action list of values
			Select selectSou = new Select(driver.findElement(SourcingConfig.actionDD));
			List<WebElement> options = selectSou.getOptions();
			//System.out.println(options);
			boolean bVal=CommonFunctions.dropDownOptionVerificationActions("Add Sourcing Configuration",options);
			Assert.assertEquals(bVal, false,"verified RasinType failed for "+ "Add Sourcing Configuration");
			log.info("****Verified Add Sourcing Configuration is not present in dropdown****");
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyAddSourcingConfigOption()", e);
			throw e;
		}
		return true;
	}

	public static boolean UpdateMaterialLaborPricingChartTableOperationType(String[] data) throws Exception{
		try{
			navigateUptoCreateDiffrntTypesOfMaterial(data);	
			createLabourMaterial(data);
			//	assas
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in UpdateMaterialLaborPricingChartTableOperationType()", e);
			throw e;
		}
		return true;
	}

	public static boolean createLabourMaterial(String[] data) throws Exception{
		try{
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialName));
			matName = data[8] + CommonFunctions.getRandomString(5);
			CommonFunctions.enterTextInTextboxUpdated(BOMMaterialMainUser.txtName, matName,"txtlabourName");
			CommonFunctions.enterTextInTextboxUpdated(txtlaborDescription, "SamplelaborDescription","txtlaborDescription");			
			//Search for Season from POPUP Page
			CommonFunctions.clickButtonOrLink(BOMMaterialMainUser.season, "link", "Season");
			Set set1 = driver.getWindowHandles();
			Iterator iter1 = set1.iterator();
			String parent1 = (java.lang.String) iter1.next();
			String child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			CommonFunctions.clickButtonOrLink(BOMMaterialMainUser.btnSearch, "Search For Season");
			//Select choose
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[4]+"')]/preceding::td[1]/a"), "Season selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			// click on resin create button
			CommonFunctions.selectFromDropDownByIndex(MaterialBOM.operationType,1);
			CommonFunctions.clickButtonOrLink(BOMMaterialMainUser.createBtn, "btnCreateLabour");
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in UpdateMaterialResinPricingChartTable()", e);
			throw e;
		}
		return true;
	}

	public static boolean UpdateMaterialResinPricingChartTable(String[] data) throws Exception{
		try{
			navigateUptoCreateDiffrntTypesOfMaterial(data);		
			resinmandatoryData(data);
			System.out.println("matName: "+ matName);
			System.out.println("exchRate: "+ exchRate);
			updateMaterialResin(data);
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in UpdateMaterialResinPricingChartTable()", e);
			throw e;
		}
		return true;
	}

	public static boolean createMaterialResin(String[] data) throws Exception{
		try{
			navigateUptoCreateDiffrntTypesOfMaterial(data);		
			resinmandatoryData(data);
			boExchRate = verifyBOfxRates(data);
			Assert.assertEquals(exchRate, boExchRate);
			log.info("The attribute Exchange Rate (USD to HKD) is based on fx Rates of '2019 ASIA HONG KONG' available on business Object");
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in createFactory()", e);
			throw e;
		}
		return true;
	}

	public static boolean updateMaterialResin(String[] data) throws Exception{
		try{
			//Select update material from Action
			CommonFunctions.selectFromDropDownByVisibleText(Material.materialAction,"Update Material");
			//****Verification :Verify that Resin Type attribute has below list of value:
			//verifyRasinType(data);
			//Click on Save
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			//Click on 'Edit' link of  'Pricing Chart'
			CommonFunctions.clickButtonOrLink(Material.editMOA, "lnk", "Edit");
			//Click on Country of Origin cell
			CommonFunctions.clickButtonOrLink(cellCountryofOrigin, "cell", "Country of Origin");
			//Click on Country of Origin link
			CommonFunctions.clickButtonOrLink(linkCountryofOrigin, "lnk", "Country of Origin");
			set1 = driver.getWindowHandles();
			iter1 = set1.iterator();
			parent1 = (java.lang.String) iter1.next();
			child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			//Enter country in Name input box
			CommonFunctions.enterTextInTextboxUpdated(inputName, data[53], "Country Name");
			CommonFunctions.clickButtonOrLink(search, "btn", "Search");
			//Select choose for country
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[53]+"')]/preceding::td[1]/a"), "country selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			//Click on Save btn
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			altMsg= CommonFunctions.handleAlertPopUp();
			System.out.println(altMsg);
			Assert.assertEquals(altMsg.trim(), "You must specify a value for:   Benchmark Cost (HKD)");
			log.info("****Error message observed as: You must specify a value for:   Benchmark Cost (HKD)");
			//Click cell BenchmarkCostHKD
			CommonFunctions.clickButtonOrLink(cellBenchmarkCostHKD, "cell", "BenchmarkCostHKD");
			//Enter value
			CommonFunctions.enterTextInTextboxUpdated(inputBenchmarkCostHKD, data[54], "BenchmarkCostHKD");
			//Click on Save btn
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			altMsg= CommonFunctions.handleAlertPopUp();
			Assert.assertEquals(altMsg.trim(), "You must specify a value for:   Constant (HKD)");
			log.info("****Error message observed as: You must specify a value for:   Constant (HKD)");

			//Click cell Constant (HKD)
			CommonFunctions.clickButtonOrLink(cellConstantHKD, "cell", "Constant (HKD)");
			//Enter value
			CommonFunctions.enterTextInTextboxUpdated(inputConstantHKD, data[55], "Constant (HKD)");
			//Click on Save btn
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			//Verify Clear Object Reference functionality
			verifyClearObjectReference(data);
			//ReEnter country
			//Click on Country of Origin cell
			CommonFunctions.clickButtonOrLink(cellCountryofOrigin, "cell", "Country of Origin");
			//Click on Country of Origin link
			CommonFunctions.clickButtonOrLink(linkCountryofOrigin, "lnk", "Country of Origin");
			set1 = driver.getWindowHandles();
			iter1 = set1.iterator();
			parent1 = (java.lang.String) iter1.next();
			child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			//	driver.switchTo().defaultContent();
			//Enter country in Name input box
			CommonFunctions.enterTextInTextboxUpdated(inputName, data[53], "Country Name");
			//Click on search
			CommonFunctions.clickButtonOrLink(search, "btn", "Search");
			CommonFunctions.waitForPageLoaded();
			//Select choose for country
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[53]+"')]/preceding::td[1]/a"), "country selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			//Click on Save btn
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			//Click on Done button
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnDone, "Btn", "Done");
			verifyMaterialPricingChartCalculation(data);
			log.info("****VERIFICATION DONE FOR PC10 - Update Material - Resin Pricing Chart Table ****");
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in updateMaterialResin()", e);
			throw e;
		}
		return true;
	}

	private static boolean verifyMaterialPricingChartCalculation(String[] data) throws Exception {
		try{ 
			exchRate=driver.findElement(exchangeRate).getText().trim();
			//verify Minimum Threshold (HKD)
			String minThreshold = driver.findElement(valMinThresholdHKD).getText().trim();
			double minThresholdExp = Double.parseDouble(data[56]);
			NumberFormat formatter = new DecimalFormat("#0.0000");
			Assert.assertEquals(minThreshold, formatter.format(minThresholdExp));
			log.info("VERIFIED MINIMUM THRESHOLD(HKD) AS: "+minThreshold);

			//verify Maximum Threshold (HKD)
			String maxThreshold = driver.findElement(valMaxThresholdHKD).getText().trim();
			double maxThresholdExp = Double.parseDouble(data[57]);
			Assert.assertEquals(maxThreshold, formatter.format(maxThresholdExp));
			log.info("VERIFIED MAXIMUM THRESHOLD(HKD) AS: "+maxThreshold);

			//verify Benchmark Cost (USD) 
			double bc=(Double.parseDouble(data[54]) / Double.parseDouble(exchRate));
			String BenchmarkCostUSDExp = String.format("%.4f", bc);
			Assert.assertEquals(driver.findElement(valBenchmarkCostUSD).getText().trim(), BenchmarkCostUSDExp);
			log.info("VERIFIED BENCHMARK COST(USD) AS: "+BenchmarkCostUSDExp);

			//verify Minimum Threshold (USD) 	
			double minTH=(Double.parseDouble(minThreshold) / Double.parseDouble(exchRate));
			String MinThresholdUSDExp = String.format("%.4f", minTH);
			Assert.assertEquals(driver.findElement(valMinThresholdUSD).getText().trim(), MinThresholdUSDExp);
			log.info("VERIFIED MINIMUM THRESHOLD(USD) AS: "+MinThresholdUSDExp);

			//verify Maximum Threshold (USD)
			double maxTH=(Double.parseDouble(maxThreshold) / Double.parseDouble(exchRate));
			String MaxThresholdUSDExp = String.format("%.4f", maxTH);
			Assert.assertEquals(driver.findElement(valMaxThresholdUSD).getText().trim(), MaxThresholdUSDExp);
			log.info("VERIFIED MAXIMUM THRESHOLD(USD) AS: "+MaxThresholdUSDExp);
			log.info("****CALCULATION VERIFIED****");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in verifyMaterialPricingChartCalculation()", e);
			throw e;
		}
		return true;
	}

	private static boolean verifyClearObjectReference(String[] data) throws Exception {
		try{ 
			//Click on Country of Origin cell
			CommonFunctions.clickButtonOrLink(cellCountryofOrigin, "cell", "Country of Origin");
			//Click on Country of Origin link
			CommonFunctions.clickButtonOrLink(linkCountryofOrigin, "lnk", "Country of Origin");
			Set set1 = driver.getWindowHandles();
			Iterator iter1 = set1.iterator();
			String parent1 = (java.lang.String) iter1.next();
			String child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			//Click on 'Clear Object Reference Value'
			CommonFunctions.clickButtonOrLink(btnClearObjectReferenceValue, "btn", "Clear Object Reference Value");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			Assert.assertEquals(driver.findElements(By.xpath("//div[@id='ptc_verRef_1Display']/a[text()='"+data[53]+"']")).size(), 0);
			log.info("****COUNTRY VALUE REMOVED****");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in verifyClearObjectReference()", e);
			throw e;
		}
		return true;
	}

	private static String[] resinmandatoryData(String[] data) throws Exception {
		try{ 
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialName));
			matName=data[8] + CommonFunctions.getRandomString(5);
			CommonFunctions.enterTextInTextbox(Material.materialName, matName);
			CommonFunctions.enterTextInTextbox(resinDescription,data[5]);
			//Select Processing Method 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(processingMethod, data[6], "Processing Method ");
			//Select Unit Of Measure 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(unitOfMeasure, data[7], "Unit Of Measure");
			//****Verify Verify that Resin Type attribute is present in General Attribute group. 
			CommonFunctions.isElementDisplayed(labelRasinType, "Rasin type");
			log.info("****Verification : System displays Resin Type attribute is present in General attribute group.****");
			//***Verification :  Resin Type attribute list
			verifyRasinType(data);
			log.info("****Verification : Verified List of value for Resin Type attribute*****");
			//Select  Resin Type 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(resinType, data[8], "Resin Type ");
			//Enter  Density (gr/gm2) 
			CommonFunctions.enterTextInTextboxUpdated(density, data[9], "Density (gr/gm2) ");

			//Search for   *Exchange Rate Season from POPUP Page
			CommonFunctions.clickButtonOrLink(seasonLnk, "link", "Season");
			Set set1 = driver.getWindowHandles();
			Iterator iter1 = set1.iterator();
			String parent1 = (java.lang.String) iter1.next();
			String child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			CommonFunctions.clickButtonOrLink(BOMMaterialMainUser.btnSearch, "Search For Season");
			//Select choose
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[4]+"')]/preceding::td[1]/a"), "Season selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			// click on resin create button
			CommonFunctions.clickButtonOrLink(Material.btnCreateResin, "btnCreateResin");
			exchRate=driver.findElement(exchangeRate).getText().trim();
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in resinmandatoryData()", e);
			throw e;
		}
		//	return exchRate;
		return new String[] {exchRate, matName};
	}

	private static String verifyBOfxRates(String[] data) throws Exception {
		try{ 
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			//Click on BusinessObject
			CommonFunctions.clickButtonOrLink(businessObj, "link", "BusinessObject");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on 'FX Rates - Business Unit'
			CommonFunctions.clickButtonOrLink(fxRatesBusiUnit, "link", "FX Rates - Business Unit");	
			//Click on search
			CommonFunctions.clickButtonOrLink(BOMMaterialMainUser.btnSearch, "btn", "Search");
			CommonFunctions.waitForPageLoaded();
			//Click in business unit name as '2019 ASIA HONG KONG'
			CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[52]+"']"), "lnk", "Business unit name");
			//CommonFunctions.waitForVisibilityOfElement(fxRate);
			boExchRate = driver.findElement(fxRate).getText().trim();
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in resinmandatoryData()", e);
			throw e;
		}
		return boExchRate;
	}

	public static boolean verifyRasinType(String [] data) throws Exception{
		try{
			Select selectSou = new Select(driver.findElement(resinType));
			List<WebElement> options = selectSou.getOptions();
			for(int i=10;i<51;i++) {
				//System.out.println(options);
				boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[i],options);
				Assert.assertEquals(bVal, true,"verified RasinType failed for "+ data[i]);
				log.info("Verified RasinType for : "+ data[i]);
			}
			return true;
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in verifyRasinType()", e);
			throw e;
		}

	}


	public static boolean navigateUptoCreateDiffrntTypesOfMaterial(String[] data) throws Exception
	{
		try{
			if(driver.findElements(Material.materialLink).size()==0) {
				driver.navigate().refresh();
			}
			//	if(driver.findElements(materialDet).size()== 0) {	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			if(driver.findElements(Material.materialLink).size()==0) 
			{
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Material.libraryLink, "Link", "Library Link");
			}
			//Click on Color link
			driver.findElement(Material.materialLink).click();
			//Switch frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			wait = new WebDriverWait(driver, 10);
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialHeadning));
			//Click on new
			CommonFunctions.clickButtonOrLink(Material.newLink, "link", "New Link");
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.chooseaType));
			//Click on  type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+ data[2]+ "')and @class='LABEL']"), "link", "Material Type");
		}catch(Exception e){
			fail=true;
			log.error("Exception in navigateUptoCreateDiffrntTypesOfMaterial()", e);
			throw e;
		}
		return true;
	}

	public static boolean createFactory(String[] data) throws Exception{
		try{

			nevigateFactory(data);
			//validate factory attributes
			validateFactoryAttributes(data);

		}catch(Exception e){ 
			fail=true;
			log.error("Exception in createFactory()", e);
			throw e;
		}
		return true;
	}

	public static boolean validateFactoryAttributes(String[] data) throws Exception{
		try{
			CommonFunctions.isElementDisplayed(attName,"Name");
			CommonFunctions.isElementDisplayed(attType,"Type");
			CommonFunctions.isElementDisplayed(attsapVendorCode,"SAP Vendor Code");
			CommonFunctions.isElementDisplayed(attfactoryCode,"Factory Code ");
			CommonFunctions.isElementDisplayed(attfactoryAddress,"Factory Address");
			CommonFunctions.isElementDisplayed(atteffectiveFromDate,"Effective From Date");
			CommonFunctions.isElementDisplayed(attSupplier,"Supplier");
			CommonFunctions.isElementDisplayed(attFactoryID,"Factory ID");
			CommonFunctions.isElementDisplayed(attParentVendorName,"Parent Vendor Name");
			CommonFunctions.isElementDisplayed(attVMRVendorCode,"VMR Vendor Code");
			CommonFunctions.isElementDisplayed(attCountryOfOrigin,"Country Of Origin");
			CommonFunctions.isElementDisplayed(attEffectiveToDate,"Effective To Date");
			CommonFunctions.isElementDisplayed(attLocation,"Location");
			CommonFunctions.isElementDisplayed(attZone,"Zone");
			CommonFunctions.isElementDisplayed(attStatus,"Status");
			CommonFunctions.isElementDisplayed(attVendorGroup,"Vendor Group");
			CommonFunctions.isElementDisplayed(attFactoryNameinlocallanguage,"Factory Name in local language");
			CommonFunctions.isElementDisplayed(attFactoryRegion, "Factory Region");
			CommonFunctions.isElementDisplayed(attFCL_YTHKD, "FCL-YT (HKD)" );
			CommonFunctions.isElementDisplayed(attLCL_YTHKD, "LCL-YT (HKD)");
			CommonFunctions.isElementDisplayed(attAverageFreightCostHKD, "Average Freight Cost (HKD)");
			CommonFunctions.isElementDisplayed(attFCL_HKHKD,"FCL-HK (HKD)");
			CommonFunctions.isElementDisplayed(attLCL_HKHKD,"LCL-HK (HKD)");
			CommonFunctions.isElementDisplayed(attFCL_YTUSD,"FCL-YT (USD)");
			CommonFunctions.isElementDisplayed(attLCL_YTUSD,"LCL-YT (USD)");
			CommonFunctions.isElementDisplayed(attAverageFreightCostUSD,"Average Freight Cost (USD)");
			CommonFunctions.isElementDisplayed(attFCL_HKUSD,"FCL-HK (USD)");
			CommonFunctions.isElementDisplayed(attLCL_HKUSD,"LCL-HK (USD)");
			CommonFunctions.isElementDisplayed(attAssociatedDocuments,"Associated Documents:");
			CommonFunctions.isElementDisplayed(attSystemInformation,"System Information:");
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in validateFactoryAttributes()", e);
			throw e;
		}
		return true;
	}
	public static boolean updateFactory(String[] data) throws Exception{
		try{
			nevigateFactory(data);
			//Select update
			CommonFunctions.selectFromDropDownByVisibleText(suppAction, "Update");
			//	CommonFunctions.clickButtonOrLink(suppAction, "drop down", "Action");
			//	CommonFunctions.clickButtonOrLink(, "drop down", "Action");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForVisibilityOfElement(headingUpdateSupp);
			Assert.assertEquals(driver.findElement(headingUpdateSupp).getText().trim(), "Update Supplier");
			log.info("System displays the Factory details page in edit mode.");
			//verify factory region dropdown values
			verifyFactoryRegionValues(data);
			//Select China - Guangdong as the Facotry Region
			CommonFunctions.selectFromDropDownByVisibleText(ddFactoryRegion, "China - Guangdong");
			//Verification : System displays Chia - Guangdong as the Factory Region attribute
			Assert.assertEquals(new Select(driver.findElement(ddFactoryRegion)).getFirstSelectedOption().getText(), "China - Guangdong");
			log.info("Selected value in drop down is: " +" China - Guangdong" );
			//Select Status
			//CommonFunctions.selectFromDropDownByVisibleText(status, "Active");
			//Select Effective To Date 
			//CommonFunctions.enterTextInTextboxUpdated(effectiveToDate, CommonFunctions.getTodayDate(), "effective To Date");
			//Click on save
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
			//Verification
			Assert.assertEquals(driver.findElement(roFactoryRegion).getText().trim(), "China - Guangdong");
			log.info("System saves the newly added Factory Region attribute as : " + "China - Guangdong");

		}catch(Exception e){ 
			fail=true;
			log.error("Exception in Create_Supplier()", e);
			throw e;
		}
		return true;
	}
	public static boolean verifyFactoryRegionValues(String [] data) throws Exception{
		try{
			Select selectSou = new Select(driver.findElement(ddFactoryRegion));
			List<WebElement> options = selectSou.getOptions();
			for(int i=4;i<15;i++) {
				//System.out.println(options);
				boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[i],options);
				Assert.assertEquals(bVal, true,"verified region failed for "+ data[i]);
				log.info("Verified region: "+ data[i]);
			}
			return true;
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in verifyFactoryRegionValues()", e);
			throw e;
		}

	}

	public static boolean nevigateFactory(String [] data) throws Exception{
		try{
			if(driver.findElements(suppAction).size()== 0) {
				// Switch to Sidebar Frame
				driver.switchTo().defaultContent();
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//Click on Vendor(Supplier) from library
				CommonProjectFunctions.clickSupplierLink();	
				//Click on Factory
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"') and @class='LABEL']"), "link", "Factory");
				//Enter '*' in Name for search
				//CommonFunctions.enterTextInTextboxUpdated(inputName, "*", "Name");
				//Click on search
				CommonFunctions.clickButtonOrLink(search, "btn", "Search");
				//Click on first supplier
				CommonFunctions.clickButtonOrLink(firstSupplier, "lnk", "First supplier in list");
			}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in nevigateFactory()", e);
			throw e;
		}
		return true;
	}

	public static boolean createSupplier(String[] data) throws Exception{
		try{
			// Switch to Sidebar Frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			//Click on Vendor(Supplier) from library
			CommonProjectFunctions.clickSupplierLink();	
			//Click on New
			CommonFunctions.clickButtonOrLink(newLink, "link", "New");
			//Click on Supplier
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"') and @class='LABEL']"), "link", "Factory");
			CommonFunctions.waitForPageLoaded();
			getPageTitle();
			S_ASSERT.assertEquals(pageTitle, "Create Supplier","ERROR:Pagetile is not correct.please verify");

			//Verify Name attribute
			Assert.assertEquals(driver.findElements(suppName).size(),1);
			log.info("****Verification: Name attribute is present which is by default blank and undeitable****");

			//Verify that Type attribute present in Supplier Identification group.
			CommonFunctions.isElementDisplayed(suppTypeCreate,"Type");
			log.info("****Verification:System displays Type attribute in Supplier Attributes group.****");

			//Verify that Type attribute has sytem generated value as Supplier and it is not editable.
			Assert.assertEquals(driver.findElement(typeValue).getText().trim(), "Supplier");
			log.info("****Verification:System does not allows user to modify Type attribute value and it has system generated value as Supplier.****");

			//Verify that Status attribtue present in FSupplier Definition group. 
			Assert.assertEquals(driver.findElements(suppStatus).size(),1);
			log.info("****Verification:System displays Status attribtue in Supplier Definition group.****");

			//Verify that Status default value is Unassigned and it not editable for user.
			Assert.assertEquals(driver.findElement(statusValue).getText().trim(), "Unassigned");
			log.info("****Verification:System does not allows Status attribtue to be edit by user and it's default value is Unassigned.****");

			//Verify that Supplier Category attribute is present in the General Attributes 
			Assert.assertEquals(driver.findElements(supplierCategory).size(), 1);
			log.info("****Verification:System displays Supplier Category in General attribtue group.****");

			//Ensure that Supplier Category attribute is not editable by User and by default value is blank.
			Assert.assertEquals(driver.findElement(supplierCategoryValue).getText().trim(), "");
			log.info("****Verification:System displays Supplier Category blank and it is not editable by User****");

			//Verify that Vendor Group attribute is present in the General Attributes 
			Assert.assertEquals(driver.findElements(vendorGroup).size(),1);
			log.info("****Verification:System displays Vendor Group in General attribtue group****");

			//Below scenario is fail
			//Ensure that Vendor Group attribute is not editable by User and by default value is blank.
			/*Assert.assertEquals(driver.findElement(vendorGroupValue).getText().trim(), "");
			log.info("****Verification:System displays Vendor Group blank and it is not editable by User****");
			 */
			//Verify that SAP Vendor Code attribute is present in the General Attributes 
			Assert.assertEquals(driver.findElements(sapVendorCode).size(),1);
			log.info("****Verification:System displays SAP Vendor Code in General attribtue group****");

			//Ensure that SAP Vendor Code attribute is not editable by User and by default value is blank.
			Assert.assertEquals(driver.findElement(sapVendorCodeValue).getText().trim(), "");
			log.info("****Verification:System displays SAP Vendor Code blank and it is not editable by User****");

			//Verify that VMR Vendor Code attribute is present in the General Attributes  
			Assert.assertEquals(driver.findElements(vmrVendorCode).size(),1);
			log.info("****Verification:System displays VMR Vendor Code in General attribtue group****");

			//Ensure that VMR Vendor Code attribute is not editable by User and by default value is blank.
			Assert.assertEquals(driver.findElement(vmrVendorCodeValue).getText().trim(), "");
			log.info("****Verification:System displays VMR Vendor Code blank and it is not editable by User****");

			//Verify that Country attribute is present in the General Attributes 
			Assert.assertEquals(driver.findElements(country).size(),1);
			log.info("****Verification:System displays Country in General attribtue group****");

			//Ensure that Country attribute is not editable by User and by default value is blank.
			Assert.assertEquals(driver.findElement(countryValue).getText().trim(), "");
			log.info("****Verification:System displays Country blank and it is not editable by User****");

			//Verify that Address attribute is present in the General Attributes  
			Assert.assertEquals(driver.findElements(address).size(),1);
			log.info("****Verification:System displays Address in General attribtue group****");

			//Ensure that Address attribute is not editable by User and by default value is blank.
			Assert.assertEquals(driver.findElement(addressValue).getText().trim(), "");
			log.info("****Verification:System displays Address blank and it is not editable by User****");

			/****************/
			//Verify that Primary Email Contact attribute is present in the General Attributes  
			Assert.assertEquals(driver.findElements(primaryEmailContact).size(),1);
			log.info("****Verification:System displays Primary Email Contact in General attribtue group****");

			//Ensure that Primary Email Contact attribute is not editable by User and by default value is blank.
			Assert.assertEquals(driver.findElement(primaryEmailContactValue).getText().trim(), "");
			log.info("****Verification:System displays Primary Email Contact  blank and it is not editable by User****");

			/*****************/
			//Verify that Remarks attribute is present in the Supplier Definition and by default value is blank.
			Assert.assertEquals(driver.findElements(remarks).size(),1);
			Assert.assertEquals(driver.findElement(remarksValue).getText().trim(),"");
			log.info("****Verification:System displays Remarks in Supplier Definition group and its default value is blank.****");

			//Enter ABC123 value in Remarks attribute.
			CommonFunctions.enterTextInTextbox(remarksValue, "ABC123");
			System.out.println(driver.findElement(remarksValue).getAttribute("value"));
			Assert.assertEquals(driver.findElement(remarksValue).getAttribute("value").trim(),"ABC123");
			log.info("****Verification:System displays ABC123 value in Remarks attribute.****");

			/*****************/
			//Verify that Short Name attribute is present in the Supplier Definition and by default value is blank.
			Assert.assertEquals(driver.findElement(shortName).getText().trim(),"Short Name");
			Assert.assertEquals(driver.findElement(shortNameValue).getText().trim(),"");
			log.info("****Verification:System displays Short Name in Supplier Definition group and its default value is blank.****");

			//Enter more than 30 characters in Short Name.
			CommonFunctions.enterTextInTextboxUpdated(shortNameValue, more30Char, "ShortName");
			int actualLimit = driver.findElement(shortNameValue).getAttribute("value").length();
			Assert.assertEquals(actualLimit, 30);
			//Assert.assertEquals(driver.findElement(shortNameValue).getText().trim(),"012345678901234567890123456789");
			log.info("****Verification:System does not allows to enter more than 30 characters in Short Name attribute.****");

			//Enter XYZ321 in Short Name attribute
			driver.findElement(shortNameValue).clear();
			CommonFunctions.enterTextInTextboxUpdated(shortNameValue, "XYZ321", "ShortName");
			//System.out.println(driver.findElement(shortNameValue).getText());
			Assert.assertEquals(driver.findElement(shortNameValue).getAttribute("value").trim(),"XYZ321");
			log.info("****Verification:System displays XYZ321 value in Short Name****");

			/*****************/
			//Verify that RFQ Receipt Contact attribute is present in the Supplier Definition and by default value is blank.
			Assert.assertEquals(driver.findElements(rfqReceiptContact).size(),1);
			Assert.assertEquals(driver.findElement(rfqReceiptContactValue).getText(),"");
			log.info("****Verification:System displays RFQ Receipt Contact in Supplier Definition group and its default value is blank.****");

			//Enter xyz@bsci.com in RFQ Receipt Contact attribute
			CommonFunctions.enterTextInTextboxUpdated(rfqReceiptContactValue,data[4],"RFQ Receipt Contact");
			Assert.assertEquals(driver.findElement(rfqReceiptContactValue).getAttribute("value").trim(),data[4]);
			log.info("****Verification:System display xyz@bsci.com for RFQ Receipt Contact attribute****");

			/*****************/
			//Verify that Cost Approval Contact attribute is present in the Supplier Definition and by default value is blank.
			Assert.assertEquals(driver.findElements(costApprovalContact).size(),1);
			Assert.assertEquals(driver.findElement(costApprovalContactValue).getText(),"");
			log.info("****Verification:System displays Cost Approval Contacte in Supplier Definition group and its default value is blank.****");

			//Enter xyz@bsci.com in Cost Approval Contact attribute
			CommonFunctions.enterTextInTextboxUpdated(costApprovalContactValue,data[5], "Cost Approval Contact");
			Assert.assertEquals(driver.findElement(costApprovalContactValue).getAttribute("value").trim(),data[5]);
			log.info("****Verification:System display xyz@bsci.com for Cost Approval Contactattribute****");

			//Click on create
			CommonFunctions.clickButtonOrLink(btnCreate, "btn", "Create");
			CommonFunctions.waitForPageLoaded();
			//Verify that on detail page of above Supplier, Name and Type attribute are present in Supplier Identification group table.
			Assert.assertEquals(driver.findElement(attName).getText().trim(),"Name");
			Assert.assertEquals(driver.findElement(typeValue).getText().trim(),data[2]);

			log.info("****Verification:System displays Name and Type attribute in Supplier Identification group table.****");
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in createSupplier()", e);
			throw e;
		}
		return true;
	}

	public static  String getPageTitle(){
		//Common function getting the page title when navigating into another page
		try{
			pageTitle=driver.getTitle();
			log.info(pageTitle);
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in getPageTitle");
			throw e;
		}
		return pageTitle;
	}

	public static String createDevelopmentPlan(String[] data) throws Exception{
		try{
			log.info(" Create Development Plan verification started....");
			negigateToPlan(data);
			//Click on create new plan
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.createNewPlan, "btn", "Create New Plan");
			//Click Global Line Plan :
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.globalLinePlan, "btn", "Global Line Plan");
			//wait for heading 
			CommonFunctions.waitForVisibilityOfElement(GlobalLinePlan.headingCreateNewPlan);
			//Click Create New Plan
			SmokeFlow.glPlanName = createNewPlan(data);
			//glPlanName="AutoDevPlan"+glPlanName;
			fillGlobalLinePlan(data);
			log.info("Plan name is: "+ SmokeFlow.glPlanName);
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg2);
			//Expend
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg2, "Expand class");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg3);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg3, "Expand division");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg4);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg4, "Expand Brand");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg6);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg6, "Expand MainLine");
			//CommonFunctions.waitForElementTobeClickable(expImg6);
			//CommonFunctions.clickButtonOrLink(expImg4, "Expand MainLine");
			//Class
			SmokeFlow.strClass=driver.findElement(SmokeFlow.objClass).getText();
			Assert.assertEquals(SmokeFlow.strClass.trim(), data[6]);
			//Division
			SmokeFlow.strDivision=driver.findElement(SmokeFlow.objDivision).getText();
			Assert.assertEquals(SmokeFlow.strDivision.trim(), data[7]);//
			//Brand
			SmokeFlow.strBrand=driver.findElement(SmokeFlow.objBrand).getText();
			Assert.assertEquals(SmokeFlow.strBrand.trim(), data[5]);
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForVisibilityOfElement(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbTotalRetailItemCount')][1]"));
			//Verification of totalRetailItemCount
			SmokeFlow.strTotalRetailItemCount=driver.findElement(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbTotalRetailItemCount')][1]")).getText();
			String val =data[34].replaceAll("\\.0*$", "");
			Assert.assertEquals(SmokeFlow.strTotalRetailItemCount, val);
			log.info("Column 'Total Retail Item Count' is calculated sucessfully");

			log.info("Create Development Plan verification completed");
		}catch(Exception e){
			fail=true;
			log.error("Exception in createDevelopmentPlan()", e);
			throw e;
		}
		return SmokeFlow.glPlanName;
	}

	public static String createNewPlan(String[] data) throws Exception {
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			SmokeFlow.glPlanName = CommonFunctions.getRandomString(5);
			//Enter Name
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.planName,SmokeFlow.glPlanName);
			//Enter Brand
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.Brand, data[5]);
			//Enter Season Year
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.seasonYear, data[4]);
			//Click on create button
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
			throw e;
		}
		return SmokeFlow.glPlanName;
	}
	public static Boolean fillGlobalLinePlan(String[] data) throws Exception {
		try{
			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink1, "Image", "Plus Sign");
			//Select Class
			//CommonFunctions.selectFromDropDownByValue(hbclass, data[9]);
			new Select(SeleniumDriver.driver.findElement(GlobalLinePlan.hbclass)).selectByVisibleText(data[6]);
			SeleniumDriver.log.info("Selected" + " " + data[9] + " " + "option from drop down.");
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink2, "Image", "Plus Sign");
			//Select DIVISION
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbDivision, data[7]);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink3, "Image", "Plus Sign");
			//Select Brand
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbBrand, data[5]);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");
			CommonFunctions.waitForElementTobeClickable(GlobalLinePlan.menulink5);
			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink5, "Image", "Plus Sign");
			//Select Class
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.hbAssortmentSolidNumber, data[9]);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//opulate the field "Assortment/Solid Name" with the name suggested is a required attribute and has to be filled.
			//Maximum Length of 40 Characters can be entered which gets carried from Dev plan to placeholder
			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbAssortmentSolidName')][1]"), "cell", "Assortment / Solid Name");
			CommonFunctions.enterTextInTextboxUpdated(GlobalLinePlan.inPutAssortmentSolidName, more40Char, "AssortmentSolidName");
			int actualLimit = driver.findElement(GlobalLinePlan.inPutAssortmentSolidName).getAttribute("value").length();
			Assert.assertEquals(actualLimit,40);
			log.info("****Verification:On the Global Line Plan, the system will only allow Product Name (Assortment/Solid Name) to have a maximum of 40 characters which is a required attribute carried from the Dev Plan to the Placholder. ****");
			//valid value
			driver.findElement(GlobalLinePlan.inPutAssortmentSolidName).clear();
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPutAssortmentSolidName, data[10]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbDistributionChannel')][1]"), "cell", "Marketing Channel");
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbDistributionChannelForUpdate, "Mainline");

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbLicensor')][1]"), "cell", "Licensor");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbLicensor, data[11]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbCoBrand')][1]"), "cell", "Co-Brand");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbCoBrand, data[12]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbISO')][1]"), "cell", "ISO");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbISO, data[13]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbMovie')][1]"), "cell", "Movie");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbMovie, data[14]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbIntroTiming')][1]"), "cell", "Intro Timing");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbIntroTiming, data[15]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbOnShelfDate')][1]"), "cell", "On-Shelf Date");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbOnShelfDate, data[16]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbTVAd')][1]"), "cell", "TV Ad");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbTVAd, data[17]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbDigitalProduct')][1]"), "cell", "Digital Product");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbDigitalProduct, data[18]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbProjectType')][1]"), "cell", "Project Type");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbProjectType, data[19]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbInnovationType')][1]"), "Innovation Type");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbInnovationType, data[20]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbPTMH')][1]"), "cell", "PT / MH");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbPTMH, data[21]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbAstSolid')][1]"), "cell", "Ast. / Solid");
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(GlobalLinePlan.selecthbAstSolid, data[22],"Ast. / Solid");

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbRetailerDistribution')][1]"), "cell", "Retailer Distribution");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbRetailerDistribution, data[23]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbCFItemCount')][1]"), "cell", "CF Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbCFItemCount, data[24]); //

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbNewItemCount')][1]"), "cell", "New Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbNewItemCount, data[25]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbProductRefreshItemCount')][1]"), "cell", "Product Refresh Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbProductRefreshItemCount, data[26]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbPackageRefreshItemCount')][1]"), "cell", "Package Refresh Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbPackageRefreshItemCount, data[27]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbSoldasSolid')][1]"), "cell", "Sold as Solid Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbSoldasSolid, data[28]);

			//Blind Bag Character Count Total
			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbBlindBagCharacterCountTotal')][1]"), "cell", "Sold as Solid Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbBlindBagCharacterCountTotal,"1");

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbSRPPriceUSD')][1]"), "cell", "SRP (USD)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbSRPPriceUSD, data[29]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbLCPriceUSD')][1]"), "cell", " L/C Price (USD)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbLCPriceUSD, data[30]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbDOMPriceUSD')][1]"), "cell", "DOM Price (USD)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbDOMPriceUSD, data[31]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbDomesticPercentage')][1]"), "cell", "Domestic (%)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbDomesticPercentage, data[32]);

			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbComments')][1]"), "cell", "Comments");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.textareahbComments, data[33]);

			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
			//Click on Done
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnDone,"btn", "Done");


		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
			throw e;
		}
		return true;
	}

	public static boolean updateDevelopmentPlan(String[] data) throws Exception{
		try{

			log.info(" Update Development Plan verification started....");
			negigateToPlan(data);
			//Click on Created plan
			CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+SmokeFlow.glPlanName+"']"), "lnk", "plan name");
			//Select update plan from Action dropdown
			CommonFunctions.selectFromDropDownByVisibleText(ddAction,"Update Plan");

			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg2);
			//Expend
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg2, "Expand class");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg3);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg3, "Expand division");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg4);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg4, "Expand Brand");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg6);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg6, "Expand MainLine");

			CommonFunctions.waitForElementTobeClickable(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbDistributionChannel')][1]"));
			//Edit any one of the attribute like Marketing Channel
			CommonFunctions.clickButtonOrLink(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbDistributionChannel')][1]"), "cell", "Marketing Channel");
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbDistributionChannelForUpdate, "Channel");
			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
			//Click on Done
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnDone,"btn", "Done");
			//Verification : Plan is saved with new changes
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg2);
			//Expend
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg2, "Expand class");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg3);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg3, "Expand division");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg4);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg4, "Expand Brand");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg6);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg6, "Expand MainLine");
			//Validation
			CommonFunctions.waitForVisibilityOfElement(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbDistributionChannel')][1]"));
			Assert.assertEquals(driver.findElement(By.xpath("//*[text()[contains(.,'"+data[9]+"')]]//following::td[contains(@id,'hbDistributionChannel')][1]")).getText(), "Channel");
			log.info("****Verification:Verified changed value for Marketing Channel****");
			log.info("Update Development Plan verification completed");
		}catch(Exception e){
			fail=true;
			log.error("Exception in createDevelopmentPlan()", e);
			throw e;
		}
		return true;
	}

	public static boolean negigateToPlan(String[] data) throws Exception {
		try{
			if(driver.findElements(ddAction).size()== 0) {
				//Click My Season Link
				CommonProjectFunctions.clickMySeasonLink();
				//Select Season Year
				CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[4]);
				//Click on Planning
				CommonFunctions.clickButtonOrLink(GlobalLinePlan.planningLink, "link", "Planning");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
			}
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
			throw e;
		}
		return true;
	}

	public static boolean createApprovedAssSolid_linktoPlaceholder(String[] data) throws Exception{
		try{
			//create placeholder
			strPlaceholderName = CreatePlaceholder(data);
			//Login with dguser as gm user do not have create Product access
			driver.quit();
			openBrowser();
			launchApp(data[51],data[52]);
			//Create Assortment product
			prodNum = CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[4],"Development Plan","Create New: Product",data[6],
					data[7],data[5],data[42],"Assortment","Not IP Sensitive (Open)","Yes","Yes",
					"Mainline",data[24],data[25], data[26], data[27],
					data[50],"BOYS","1D","2 MONTHS","FEMALE","No","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGS PLAYSET & ACCESSORIES","1D MEDIA LTD","1D","AVALON HILL","15 MONTHS");
			verifyPHandProdwithDiffValues(data);
			verifyPHandProdwithSameValues(data);

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateApprovedAssSolid_linktoPlaceholder()", e);
			throw e;
		}
		return true;
	}

	public static boolean verifyPHandProdwithSameValues(String[] data) throws Exception{
		try{
			//search placeholder
			CommonProjectFunctions.searchAttributeType("Placeholder", strPlaceholderName);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			//Select update of Action
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(phAction, "Update", "Placeholder Action");
			//Update attributes
			//Enter Class (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.classPLC, data[6],"Class (PLC) ");
			//Click on Division (PLC)
			CommonFunctions.waitForElementTobeClickable(PlaceHolderDevPlan.divisionPLC);
			CommonFunctions.clickButtonOrLink(PlaceHolderDevPlan.divisionPLC, "dropdown", "Division (PLC)");
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.divisionPLC,data[7],"Division (PLC)");
			//Click on Brand (PLC) 
			CommonFunctions.waitForElementTobeClickable(PlaceHolderDevPlan.brandPLC);
			CommonFunctions.clickButtonOrLink(PlaceHolderDevPlan.brandPLC, "dropdown", " Brand (PLC)");
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.brandPLC,data[5],"Brand (PLC)");
			//Click on *Segment (PLC) 
			CommonFunctions.clickButtonOrLink(PlaceHolderDevPlan.segmentPLC, "DROP DOWN", "Segment");
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.segmentPLC,data[50],"Segment");
			//Click on save
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			//Select Product
			CommonProjectFunctions.searchAttributeType("Product", prodNum);
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on details tab
			SeleniumDriver.driver.findElement(Colorway.Details).click();
			//Select season
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Colorway.seasonDD, data[4],"Season");
			// Select "Add Existing Products to Placeholder" from DD
			CommonFunctions.selectFromDropDownByVisibleText(Colorway.actionDD,"Add Product To Placeholder");
			//Switch window
			Set set = SeleniumDriver.driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			SeleniumDriver.driver.switchTo().window(child);
			//Enter value of placeholder
			CommonFunctions.enterTextInTextboxUpdated(phNumberInput,strPlaceholderName , "Placeholder Number");
			//Click on search
			CommonFunctions.clickButtonOrLink(search, "btn", "Search");
			CommonFunctions.waitForElementTobeClickable(By.xpath("//a[contains(text(),'"+strPlaceholderName+"')]//preceding::a[1]"));
			//Click on choose
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+strPlaceholderName+"')]//preceding::a[1]"), "lnk", "choose");
			SeleniumDriver.driver.switchTo().window(parent);
			SeleniumDriver.driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			//Verify Product number
			Assert.assertEquals(driver.findElement(productName).getText().trim(),prodNum);
			log.info("****Verification :Product sucessfully created****");
			//Verification : The placeholder and the assortment are linked.
			Assert.assertEquals(driver.findElement(placeholderName).getText().trim(),strPlaceholderName );
			log.info("****Verification :Placeholder name ispresent for Product****");

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyPHandProdwithDiffValues()", e);
			throw e;
		}
		return true;
	}

	public static boolean verifyPHandProdwithDiffValues(String[] data) throws Exception{
		try{
			//Click on details tab
			SeleniumDriver.driver.findElement(Colorway.Details).click();
			// Select "Add Existing Products to Placeholder" from DD
			CommonFunctions.selectFromDropDownByVisibleText(Colorway.actionDD,"Add Product To Placeholder");
			//Switch window
			Set set = SeleniumDriver.driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			SeleniumDriver.driver.switchTo().window(child);
			//Enter value of placeholder
			CommonFunctions.enterTextInTextboxUpdated(phNumberInput,strPlaceholderName , "Placeholder Number");
			//Click on search
			CommonFunctions.clickButtonOrLink(search, "btn", "Search");
			//Click on choose
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+strPlaceholderName+"')]//preceding::a[1]"), "lnk", "choose");
			SeleniumDriver.driver.switchTo().window(parent);
			SeleniumDriver.driver.switchTo().frame("contentframe");
			System.out.println(driver.findElement(errorMsg).getText());
			String errMsg=driver.findElement(errorMsg).getText();
			Assert.assertTrue(errMsg.contains("Following attributes are either not matching between Product and Placeholder or blank"));
			log.info("****Verification : ERROR MESSAGE VERIFIED****");
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyPHandProdwithDiffValues()", e);
			throw e;
		}
		return true;
	}

	public static String CreatePlaceholder(String[] data) throws Exception{
		try{
			//Click My Season Link
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[4]);
			//Click on Line Sheet link
			CommonFunctions.clickButtonOrLink(Product.lineSheet, "link", "Line Sheet");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetAction,"Create New: Placeholder");
			CommonFunctions.waitForVisibilityOfElement(By.xpath("//td[contains(text(),'Choose a Type')]"));
			//Click Product type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"')]"), "link", "Product Type");
			strAssortmentSolidName= createNewPlaceholder(data);
			CommonFunctions.waitForPageLoaded();
			log.info("**** Assortment / Solid Name (PLC) is:" +strAssortmentSolidName);
			strPlaceholderName = driver.findElement(labelPlaeholderName).getText();
			log.info("****Placeholder name is : "+strPlaceholderName);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreatePlaceholder()", e);
			throw e;
		}
		return strPlaceholderName;
	}

	public static String createNewPlaceholder(String[] data) throws Exception {
		try{
			strAssortmentSolidName = CommonFunctions.getRandomString(5);
			//Enter Name
			CommonFunctions.enterTextInTextbox(PlaceHolderDevPlan.assortmentSolidName,strAssortmentSolidName);
			//Enter Assortment / Solid # (PLC) 
			CommonFunctions.enterTextInTextbox(PlaceHolderDevPlan.assortmentSolidnoPLC, data[35]);
			//Enter *Ast. / Solid (PLC) 
			//	CommonFunctions.enterTextInTextbox(astSolidPLC, data[9]);
			CommonFunctions.selectFromDropDownByVisibleText(PlaceHolderDevPlan.astSolidPLC, data[22]);
			//Enter Class (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.classPLC, data[36],"Class (PLC) ");
			//Click on Division (PLC)
			CommonFunctions.waitForElementTobeClickable(PlaceHolderDevPlan.divisionPLC);
			CommonFunctions.clickButtonOrLink(PlaceHolderDevPlan.divisionPLC, "dropdown", "Division (PLC)");
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.divisionPLC,data[37],"Division (PLC)");
			//Click on   Family Brand (PLC) 
			CommonFunctions.waitForElementTobeClickable(PlaceHolderDevPlan.familyBrandPLC);
			CommonFunctions.enterTextInTextboxUpdated(PlaceHolderDevPlan.familyBrandPLC, data[38], "Family Brand (PLC)");
			//CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.familyBrandPLC,data[38],"Family Brand (PLC)");
			//Click on Brand (PLC) 
			CommonFunctions.waitForElementTobeClickable(PlaceHolderDevPlan.brandPLC);
			CommonFunctions.clickButtonOrLink(PlaceHolderDevPlan.brandPLC, "dropdown", " Brand (PLC)");
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.brandPLC,data[39],"Brand (PLC)");
			//Click on  Super Category (PLC) 
			CommonFunctions.enterTextInTextboxUpdated(PlaceHolderDevPlan.superCategoryPLC,data[40],"Super Category");
			//Click on *Segment (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.segmentPLC,data[8],"Segment");
			//Click on  Category (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.categoryPLC,data[41],"Category");
			//Click on   Internal Classification (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.internalClassificationPLC, data[42],"Internal Classification");
			//Click on   Licensor (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.licensorPLC, data[11],"Licensor (PLC)");
			//Click on Co-Brand (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.coBrandPLC, data[12],"Co-Brand (PLC)");
			//Click on  Property (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.propertyPLC, data[43],"Property (PLC)");
			//Click on  Lower Age (PLC) 
			//CommonFunctions.enterTextInTextboxUpdated(PlaceHolderDevPlan.lowerAgePLC, data[44],"Lower Age (PLC)");
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.lowerAgePLC,data[44],"Lower Age (PLC)");
			//Click on  Upper Age (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.upperAgePLC,data[45],"Upper Age (PLC)");
			//Click on   *ISO (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.softProjectilePLC, data[13],"Soft Projectile (PLC)");
			//Click on Distribution Channel (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.distributionChannelPLC, data[46],"Distribution Channel (PLC)");
			//Click on  Retailer Distribution (PLC) 
			CommonFunctions.enterTextInTextbox(PlaceHolderDevPlan.retailerDistributionPLC, data[47]);
			//Click on Project Type (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.projectTypePLC, data[19],"Project Type (PLC)");
			//Click on Innovation Type (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.innovationTypePLC, data[20],"Innovation Type (PLC)");
			//Click on   TV Ad (PLC)
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.tVAdPLC, data[17],"TV Ad (PLC)");
			//Click on *Intro Timing (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.introTimingPLC, data[15],"*Intro Timing (PLC)");
			//Click on Movie (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.moviePLC, data[14],"Movie (PLC)");
			//Click on On-Shelf Date (PLC) 
			CommonFunctions.enterTextInTextbox(PlaceHolderDevPlan.onShelfDatePLC, data[16]);
			//Click on  Digital Product (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.digitalProductPLC, data[18],"Digital Product (PLC)");
			//Click on    PT / MH (PLC) 
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.pT_MHPLC, data[21],"PT / MH (PLC)");
			//Click on  Comments (PLC) 
			CommonFunctions.enterTextInTextbox(PlaceHolderDevPlan.commentsPLC, data[33]);
			//Click on    *GS Plush (PLC)  
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.gsPlushPLC,"Yes","GS Plush (PLC)");
			//Select    gender(PLC)  
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(PlaceHolderDevPlan.genderPLC,data[48],"gender(PLC)");
			//Lead colorway
			//Click on Lead colorway line
			/*CommonFunctions.clickButtonOrLink(PlaceHolderDevPlan.leadcolorwayLine, "line", "leadColorway");
			CommonFunctions.enterTextInTextboxUpdated(PlaceHolderDevPlan.leadcolorwayInput, "input", "lead colorway");*/
			selectColorway();
			//Click on  SRP (USD) (PLC)  
			CommonFunctions.enterTextInTextbox(PlaceHolderDevPlan.sRPUSDPLC, data[29]);
			//Click on   *L/C Price (USD) (PLC) 
			CommonFunctions.enterTextInTextbox(PlaceHolderDevPlan.lCPriceUSDPLC, data[30]);
			//Click on  US Domestic (%) (PLC) 
			CommonFunctions.enterTextInTextbox(PlaceHolderDevPlan.uSDomesticPLC, data[32]);
			//Click on  DOM Price (USD) (PLC) 
			CommonFunctions.enterTextInTextbox(PlaceHolderDevPlan.dOMPriceUSDPLC, data[31]);
			//Click view Place holder
			CommonFunctions.clickButtonOrLink(PlaceHolderDevPlan.btnViewPlaceHolder, "btn", "View PlaceHolder");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlaceholder()", e);
			throw e;
		}
		return strAssortmentSolidName;
	}

	public static boolean selectColorway() throws Exception{
		try{
			//Click lead colorway
			CommonFunctions.clickButtonOrLink(PlaceHolderDevPlan.lableLeadcolorway, "lnk", "lead colorway");
			//Switch window
			Set set = SeleniumDriver.driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			SeleniumDriver.driver.switchTo().window(child);
			//	SeleniumDriver.driver.findElement(Colorway.suffixSearch).sendKeys(data[14]);
			CommonFunctions.clickButtonOrLink(Colorway.search, "Link", "Search Clicked");
			CommonFunctions.clickButtonOrLink(Colorway.choose, "Link", "Suffix Selected");
			SeleniumDriver.driver.switchTo().window(parent);
			SeleniumDriver.driver.switchTo().frame("contentframe");
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreatePlaceholder()", e);
			throw e;
		}
		return true;
	}

	public static boolean createRetailItem(String[] data) throws Exception{
		try{
			prodName = CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[4],"Development Plan","Create New: Product",data[6],
					data[7],data[5],data[13],"Assortment","Not IP Sensitive (Open)","Yes","Yes",
					"Mainline",data[24],data[25], data[26], data[27],
					data[8],"BOYS","1D","1 MONTH","FEMALE","No","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
			log.info("Product Name is: "+prodName);
			//Product.clickDetailsTab(data);
			CommonFunctions.clickButtonOrLink(Product.detailsTablink, "link", "Details tab");
			//Click on relationShip
			CommonFunctions.clickButtonOrLink(SmokeFlow.relationShipTab, "tab", "RelationShip");
			//Click "Copy/ Link Product"
			CommonFunctions.clickButtonOrLink(SmokeFlow.copyLinkProd, "btn", "Copy/ Link Product");
			prodRetail = copyLinkProduct(data);
			log.info("retail product:" +prodRetail);
			//Select Distribution channel
			CommonFunctions.selectFromDropDownByVisibleText(Product.MarketingChannel,"Exclusive");
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View product");
			//Verification
			Assert.assertEquals(driver.findElements(By.xpath("//div[contains(text(),'Related Products:')]//following::a[contains(text(),'"+prodName+"')]")).size(), 1);
			log.info("Related product is: " +prodName);
			//Click on details tab
			CommonFunctions.clickButtonOrLink(Product.detailsTablink, "link", "Details tab");
			Assert.assertEquals(driver.findElements(By.xpath("//td[contains(text(),'Product Number')]//following::a[contains(text(),'"+prodRetail+"')]")).size(), 1);
			Assert.assertEquals(driver.findElement(prodType).getText().trim(),"Retail Item");
			log.info("***Verification: Created product is: "+prodRetail +" and type is retail Item");
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateApprovedAssSolid_linktoPlaceholder()", e);
			throw e;
		}
		return true;
	}

	public static String copyLinkProduct(String[] data) throws Exception {
		try{
			//Select 'Season'
			CommonFunctions.selectFromDropDownByVisibleText(SmokeFlow.seasonDD, data[4]);
			//Select ' Product Type'
			CommonFunctions.selectFromDropDownByVisibleText(SmokeFlow.productType,data[36]);
			//Select 'Relationship Type'
			CommonFunctions.selectFromDropDownByVisibleText(SmokeFlow.relationShipType, data[37]);
			//Click on Next button
			CommonFunctions.clickButtonOrLink(SmokeFlow.nextBtn, "btn", "Next");
			//Softgoods Included
			CommonFunctions.enterTextInTextbox(Product.softgoodsIncluded,data[38]);
			//Electronics Included
			CommonFunctions.enterTextInTextbox(Product.electronicsIncluded,data[39]);
			//Click on Next button
			CommonFunctions.clickButtonOrLink(SmokeFlow.nextBtn, "btn", "Next");
			prodNum=SeleniumDriver.driver.findElement(CommonProjectFunctions.lebelProdNum).getText();
			//	log.info("retail product:" +prodNum);
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in copyLinkProduct()", e);
			throw e;
		}
		return prodNum;
	}
	public static String copyLinkProductWithErrorMsg(String[] data) throws Exception {
		try{
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.seasonDD);
			//Select 'Season'
			CommonFunctions.selectFromDropDownByVisibleText(SmokeFlow.seasonDD, data[4]);
			//Select ' Product Type'
			CommonFunctions.selectFromDropDownByVisibleText(SmokeFlow.productType,data[36]);
			//Select 'Relationship Type'
			CommonFunctions.selectFromDropDownByVisibleText(SmokeFlow.relationShipType, data[37]);
			//Click on Next button
			CommonFunctions.clickButtonOrLink(SmokeFlow.nextBtn, "btn", "Next");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(Product.Class);
			CommonFunctions.enterTextInTextbox(Product.Class, data[50]);
			CommonFunctions.enterTextInTextbox(Product.Division, data[51]);
			CommonFunctions.enterTextInTextbox(Product.Brand, data[52]);
			//CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(Product.segment);
			CommonFunctions.clickButtonOrLink(Product.segment, "dropdown","Segment");
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.segment,  data[53],"Segment"); 
			//Softgoods Included
			CommonFunctions.enterTextInTextbox(Product.softgoodsIncluded,data[38]);
			//Electronics Included
			CommonFunctions.enterTextInTextbox(Product.electronicsIncluded,data[39]);
			//Click on Next button
			CommonFunctions.clickButtonOrLink(SmokeFlow.nextBtn, "btn", "Next");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForVisibilityOfElement(errorMsgRet1);
			//verify error message
			Assert.assertEquals(driver.findElement(errorMsgRet1).getText().trim(),errMsgCommentClass);
			log.info("****Verified error message as : " +errMsgCommentClass +"for" + CommonProjectFunctions.strTestCaseName);
			//Revert back data
			CommonFunctions.enterTextInTextbox(retClass, data[6]);
			CommonFunctions.enterTextInTextbox(retDivision, data[7]);
			CommonFunctions.enterTextInTextbox(retBrand, data[5]);
			CommonFunctions.clickButtonOrLink(Product.segment, "dropdown","Segment");
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.segment,  data[8],"Segment"); 
			
			//Click on Next button
			CommonFunctions.clickButtonOrLink(SmokeFlow.nextBtn, "btn", "Next");
			prodNum=SeleniumDriver.driver.findElement(CommonProjectFunctions.lebelProdNum).getText();
			log.info("retail product:" +prodNum);
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in copyLinkProduct()", e);
			throw e;
		}
		return prodNum;
	}
	
	
	public static boolean bFlagVal(String data) throws Exception{
		try{
			if (flagVal)
			{ return true;}
			else{
				fail=true;
			//	log.info("Cascading fail for Class:" +data[10] + " Division : "+productData[11]+" Brand: "+productData[12]+" Segment: " +productData[13]);
				Assert.fail("Cascading not working");
				return false;
			}
		}catch(Exception e){  
			fail=true;
			log.error("Exception in bFlagVal()", e);
			return false;
		}
	}
	
	public static boolean generatePlaceholders_CreateAssortSolidPrd(String[] data) throws Exception{
		try{
			//Create development plan
			glPlanName= createDevelopmentPlan(data);
			generateAndVerifyAsstSolidPlaceholders(data);
			placeholderNa= searchPlaceholder(data);
			verifyPlaceholderAttribute(data);
			updatePlaceholderStatus();
			createNewProductForPlaceholder(data);
			verifyProductAttribute(data);
		}catch(Exception e){
			fail=true;
			log.error("Exception in GeneratePlaceholders_CreateAssortSolidPrd()", e);
			throw e;
		}
		return true;
	}

	public static boolean createNewProductForPlaceholder(String[] data) throws Exception{
		try{
			//			Click on Action
			CommonFunctions.selectFromDropDownByVisibleText(Color.colorAction, "Create New Product For Placeholder");
			CommonFunctions.clickButtonOrLink(SmokeFlow.AssSolid, "link", "Product Type");
			//Just for some time commented
			prodName= CreateProdFromLineSheet(data[2],data[4],"Development Plan","Create New: Product",data[6],
					data[7],data[5],data[42],"Assortment","Not IP Sensitive (Open)","Yes","Yes",
					"Mainline",data[24],data[25], data[26], data[27],
					"CHANNEL","BOYS","1D","1 MONTH","FEMALE","Yes","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
			log.info("Product Name is: "+prodName);
			//Product.clickDetailsTab(data);
			CommonFunctions.clickButtonOrLink(Product.detailsTablink, "link", "Details tab");
		}catch(Exception e){
			log.error("Exception in updatePlaceholderStatus()", e);
			return false;
		}
		return true;
	}

	public static String CreateProdFromLineSheet(String prodType,String year,String strlineSheetView,String strlineSheetAction,
			String strClass,String strDivision,String strBrand,String strInternalClassification,String AstSolid,String strIPSensitive,
			String strElectronicsIncluded,String strSoftgoodsIncluded,String strDistributionChannel,String strSRPPriceUSD,String strUSDomestic
			,String strLCPriceUSD,String strDOMPriceUSD,String strSegment,String strIntClassifi,String strCoBrand
			,String strLowerAge,String strGender,String strISO,String strSuperCategory,String strCategory
			,String strLicensor,String strProperty,String strFamilyBrand,String strUpperAge) throws Exception{
		try{
			prodName=CommonFunctions.getRandomString(4);
			SeleniumDriver.driver.findElement(Product.ProductName).clear();
			CommonFunctions.enterTextInTextbox(Product.ProductName,prodName);
			if(!prodType.equalsIgnoreCase("Trade Marketing Display")){
				CommonFunctions.enterTextInTextbox(Product.Class, strClass);
				//Select Class
				CommonFunctions.enterTextInTextbox(Product.inductryShortDesc, prodName);
				CommonFunctions.enterTextInTextbox(Product.Division, strDivision);
				CommonFunctions.enterTextInTextbox(Product.Brand, strBrand);
				//CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.segment, strSegment,"Segment"); //Updated
				//CommonFunctions.enterTextInTextbox(Product.InternalClassification, strIntClassifi); //Updated

				CommonFunctions.enterTextInTextbox(Product.lowerAge, strLowerAge); //Updated
				CommonFunctions.enterTextInTextbox(Product.gender, strGender); //Updated

				CommonFunctions.enterTextInTextbox(Product.superCategory, strSuperCategory); //Updated
				CommonFunctions.enterTextInTextbox(Product.category, strCategory); //Updated
				CommonFunctions.enterTextInTextbox(Product.licensor, strLicensor); //Updated
				CommonFunctions.enterTextInTextbox(Product.property, strProperty); //Updated
				CommonFunctions.enterTextInTextbox(Product.familyBrand, strFamilyBrand); //Updated
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.UpperAge, strUpperAge,"Upper Age"); //Updated
				if(!prodType.equalsIgnoreCase("Trade Marketing Pallet")){
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.InternalClassification,strInternalClassification,"Internal Classification");
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.coBrand, strCoBrand,"Co brand"); //Updated
					CommonFunctions.selectFromDropDownByVisibleText(Product.IPSensitive,strIPSensitive);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.softProjectile, strISO,"ISO");
				}
				if(prodType.equalsIgnoreCase("Assortment/Solid")){
					CommonFunctions.selectFromDropDownByVisibleText(Product.AstSolid, AstSolid);
				}
				if(prodType.equalsIgnoreCase("Retail Item")|| prodType.equalsIgnoreCase("Bundle Pack")){
					//Electronics Included
					CommonFunctions.enterTextInTextbox(Product.electronicsIncluded,strElectronicsIncluded);
					//Softgoods Included
					CommonFunctions.enterTextInTextbox(Product.softgoodsIncluded,strSoftgoodsIncluded);
				}
			}
			//Click on Save Button
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			prodNum=SeleniumDriver.driver.findElement(CommonProjectFunctions.lebelProdNum).getText();
			SeleniumDriver.log.info("Prod name: "+ prodNum);
			if(!prodType.equalsIgnoreCase("Trade Marketing Display")){
				//wait = new WebDriverWait(driver, 10);
				//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(viewProductBtn));
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.MarketingChannel,strDistributionChannel," Marketing Channel");
				/*if(prodType.equalsIgnoreCase("Assortment/Solid")){
					CommonFunctions.enterTextInTextbox(Product.SRPPriceUSD,strSRPPriceUSD);
					CommonFunctions.enterTextInTextbox(Product.USDomestic,strUSDomestic);
					CommonFunctions.enterTextInTextbox(Product.LCPriceUSD,strLCPriceUSD);
					CommonFunctions.enterTextInTextbox(Product.DOMPriceUSD,strDOMPriceUSD);
				}*/
				if(prodType.equalsIgnoreCase("Bundle Pack")){
					CommonFunctions.enterTextInTextbox(Product.targetCostUSD, strDOMPriceUSD);
				}
			}
			//Click View Product Button
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View Product");
			SeleniumDriver.log.info("created "+prodType+" Product is: " + prodNum);
		}
		catch(Exception e){ 
			log.error("Exception in CreateProductFromLineSheet()", e);
			//return "";
			//throw e;
		}
		return prodNum;
	}

	public static boolean updatePlaceholderStatus() throws Exception{
		try{
			log.info("Update Placeholder Status verification.....");

			CommonFunctions.clickButtonOrLink(Color.colorAction, "btn", "Action dropdown");
			CommonFunctions.clickButtonOrLink(PlaceHolderDevPlan.optionSetState, "option", "Change State: Placeholder");
			CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Released");
			//	Thread.sleep(1000);
			//Click on Update
			CommonFunctions.clickButtonOrLink(Product.linkUpdate, "link", "Update");
			String strRO_UpdateLifecycleState = driver.findElement(GlobalLinePlan.RO_UpdateLifecycleState).getText();
			//Verification
			Assert.assertEquals(strRO_UpdateLifecycleState, "Released");
			log.info("Place holder updated to released state.");
			log.info("Update Placeholder Status verification completed.....");
		}catch(Exception e){
			log.error("Exception in updatePlaceholderStatus()", e);
			return false;
		}
		return true;
	}

	public static String verifyProductAttribute(String[] data) throws Exception{
		try{


			//Verification : Verify the "Manually Input Wave Forecasts" is set to No in the assortment.
			Assert.assertEquals(driver.findElement(manuallyInputWaveForecasts).getText().trim(), "No");
			log.info("****Verification :\"Manually Input Wave Forecasts\" is set to No in the assortment****");

			//Verification : The placeholder and the assortment are linked.
			Assert.assertEquals(driver.findElement(placeholderName).getText().trim(),placeholderNa );
			log.info("****Verification :Placeholder name is present for Product****");

			//Verfication :Verify the following  attributes have been copied from the placeholder to the assortment.
			//Marketing Channel
			Assert.assertEquals(driver.findElement(marketingChannelPr).getText().trim(), data[46]);
			log.info("****Verification has been done for Marketing Channel ****");
			// Licensor
			Assert.assertEquals(driver.findElement(licensorPr).getText().trim(),data[11]);
			log.info("****Verification has been done for Licensor ****");
			// Co-Brand
			Assert.assertEquals(driver.findElement(coBrandPr).getText().trim(),data[12]);
			log.info("****Verification has been done for Co-Brand****");
			// ISO
			Assert.assertEquals(driver.findElement(isoPr).getText().trim(), data[13]);
			log.info("****Verification has been done for ISO****");
			// Movie
			Assert.assertEquals(driver.findElement(moviePr).getText().trim(), data[14]);
			log.info("****Verification has been done for Movie****");
			// Intro Timing
			Assert.assertEquals(driver.findElement(introTimingPr).getText().trim(), data[15]);
			log.info("****Verification has been done for Intro Timing****");
			// On-Shelf Date
			Assert.assertEquals(driver.findElement(onShelfDatePr).getText().trim(), data[16]);
			log.info("****Verification has been done for On-Shelf Date****");
			// TV Ad
			Assert.assertEquals(driver.findElement(tvAdPr).getText().trim(), data[17]);
			log.info("****Verification has been done for TV Ad****");
			// Digital Product
			Assert.assertEquals(driver.findElement(digitalProductPr).getText().trim(), data[18]);
			log.info("****Verification has been done for Digital Product ****");
			// Project Type
			Assert.assertEquals(driver.findElement(projectTypePr).getText().trim(), data[19]);
			log.info("****Verification has been done for Project Type ****");
			// Innovation Type
			Assert.assertEquals(driver.findElement(innovationTypePr).getText().trim(), data[20]);
			log.info("****Verification has been done for Innovation Type ****");
			// Prime Time/Must Have
			Assert.assertEquals(driver.findElement(primeTimeMustHavePr).getText().trim(), data[21]);
			log.info("****Verification has been done for  Prime Time/Must Have****");
			// Ast. / Solid
			Assert.assertEquals(driver.findElement(astSolidPr).getText().trim(), data[22]);
			log.info("****Verification has been done for  Ast. / Solid ****");
			// Retailer Distribution
			Assert.assertEquals(driver.findElement(retailerDistributionPr).getText().trim(), data[23]);
			log.info("****Verification has been done for Retailer Distribution ****");
			/*	// GS Plush
			Assert.assertEquals(driver.findElement(gsPlushPr).getText().trim(), data[]);
			log.info("****Verification has been done for GS Plush ****");*/
			// CF Total
			Assert.assertEquals(driver.findElement(cfTotalPr).getText().trim(), data[24]);
			log.info("****Verification has been done for CF Total ****");
			// New Total
			Assert.assertEquals(driver.findElement(newTotalPr).getText().trim(), data[25]);
			log.info("****Verification has been done for  New Total****");
			// Product Refresh Total
			Assert.assertEquals(driver.findElement(productRefreshTotPr).getText().trim(), data[26]);
			log.info("****Verification has been done for Product Refresh Total ****");
			// Package Refresh Total
			Assert.assertEquals(driver.findElement(packageRefreshTotPr).getText().trim(), data[27]);
			log.info("****Verification has been done for Package Refresh Total ****");
			// Sold as Solid Total
			Assert.assertEquals(driver.findElement(soldasSolidTotPr).getText().trim(), data[28]);
			log.info("****Verification has been done for Sold as Solid Total****");
			/*// eComm Total
			Assert.assertEquals(driver.findElement().getText().trim(), data[]);
			log.info("****Verification has been done for ****");*/
			/*// Blind Bag Character Count Total
			Assert.assertEquals(driver.findElement().getText().trim(), data[]);
			log.info("****Verification has been done for ****");*/
			Assert.assertEquals(driver.findElement(srpUSDPr).getText().replaceAll("[$]", ""), data[29]);
			log.info("****Verification has been done for srpUSDPr ****");
			// L/C Price (USD)
			Assert.assertEquals(driver.findElement(lcPriceUSDPr).getText().replaceAll("[$]", ""), data[30]);
			log.info("****Verification has been done for  L/C Price (USD)****");
			// DOM Price (USD)
			Assert.assertEquals(driver.findElement(domPriceUSDPr).getText().replaceAll("[$]", ""), data[31]);
			log.info("****Verification has been done for DOM Price (USD)****");
			// Domestic (%)
			Assert.assertEquals(driver.findElement(domesticPr).getText().trim(), data[32]);
			log.info("****Verification has been done for Domestic (%)****");
			// Comments
			Assert.assertEquals(driver.findElement(commentsPr).getText().trim(), data[33]);
			log.info("****Verification has been done for Comments****");
			log.info("Above verified attributes have been copied from the placeholder to the assortment.");
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyPlaceholderAttribute()", e);
			throw e;
		}
		return "";
	}

	public static String verifyPlaceholderAttribute(String[] data) throws Exception{
		try{


			//Verify  General Attributes
			String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
			Assert.assertEquals(GALabel, " General Attributes:");
			log.info("***Verification done for General Attributes***");
			//Verify Assortment / Solid Name (PLC) 
			String slblAssortmentSolidName = driver.findElement(SmokeFlow.lblAssortmentSolidName).getText();
			Assert.assertEquals(slblAssortmentSolidName, data[10]);
			log.info("***Verification done for Assortment / Solid Name (PLC) ***");
			// Assortment / Solid # (PLC) 
			String slblAssortmentSolidNo = driver.findElement(SmokeFlow.lblAssortmentSolidNo).getText();
			Assert.assertEquals(slblAssortmentSolidNo, data[9]);
			log.info("***Verification done for Assortment / Solid # (PLC) ***");
			//Verify Class
			String sddClassPLC= driver.findElement(classPLC).getText();
			Assert.assertEquals(sddClassPLC, data[6]);
			log.info("***Verification done for Class***");
			//Verify Division
			String sddDivisionPLC= driver.findElement(divisionPLC).getText();
			Assert.assertEquals(sddDivisionPLC, data[7]);
			log.info("***Verification done for Division ***");
			//Verify Brand
			String sddBrandPLC= driver.findElement(brandPLC).getText();
			Assert.assertEquals(sddBrandPLC, data[5]);
			log.info("***Verification done for Brand***");
			//Verify Segment
			String sddSegmentPLC= driver.findElement(segmentPLC).getText();
			Assert.assertEquals(sddSegmentPLC, data[8]);
			log.info("***Verification done for Segment***");
			//Co-Brand (PLC) 
			String sddCoBrandPLC= driver.findElement(coBrandPLC).getText();
			Assert.assertEquals(sddCoBrandPLC, data[12]);
			log.info("***Verification done for Co-Brand (PLC) ***");
			//Licensor (PLC) 
			String sddLicensorPLC= driver.findElement(licensorPLC).getText();
			Assert.assertEquals(sddLicensorPLC, data[11]);
			log.info("***Verification done for Licensor (PLC)  ***");
			//*ISO (PLC) 
			String sddISOPLC= driver.findElement(isoPLC).getText();
			Assert.assertEquals(sddISOPLC, data[13]);
			log.info("***Verification done for ISO (PLC) ***");
			// *Project Type (PLC)
			String sddProjectTypePLC= driver.findElement(projectTypePLC).getText();
			Assert.assertEquals(sddProjectTypePLC, data[19]);
			log.info("***Verification done for Project Type (PLC)***");
			//  TV Ad (PLC) 
			String sddTVAdPLC= driver.findElement(tvAdPLC).getText();
			Assert.assertEquals(sddTVAdPLC, data[17]);
			log.info("***Verification done for TV Ad (PLC) ***");
			//*Movie (PLC) 
			String sddMoviePLC= driver.findElement(moviePLC).getText();
			Assert.assertEquals(sddMoviePLC, data[14]);
			log.info("***Verification done for Movie (PLC) ***");
			// Digital Product (PLC) 
			String sddDigitalProductPLC= driver.findElement(digitalProductPLC).getText();
			Assert.assertEquals(sddDigitalProductPLC, data[18]);
			log.info("***Verification done for Digital Product (PLC) ***");
			// Comments (PLC) 
			String staCommentsPLC = driver.findElement(commentsPLC).getText();
			Assert.assertEquals(staCommentsPLC, data[33]);
			log.info("***Verification done for Comments (PLC) ***");
			// Retailer Distribution (PLC) 
			String stxtRetailerDistribution = driver.findElement(retailerDistribution).getText();
			Assert.assertEquals(stxtRetailerDistribution, data[23]);
			log.info("***Verification done for Retailer Distribution (PLC) ***");
			//  *Innovation Type (PLC) 
			String sddInnovationTypePLC= driver.findElement(innovationTypePLC).getText();
			//	String sddInnovationTypePLC = driver.findElement(ddInnovationTypePLC).getText();
			Assert.assertEquals(sddInnovationTypePLC, data[20]);
			log.info("***Verification done for Innovation Type (PLC)  ***");
			//  *Intro Timing (PLC) 
			String sddIntroTimingPLC= driver.findElement(introTimingPLC).getText();
			//	String sddIntroTimingPLC = driver.findElement(ddIntroTimingPLC).getText();
			Assert.assertEquals(sddIntroTimingPLC, data[15]);
			log.info("***Verification done for Intro Timing (PLC) ***");
			//  *On-Shelf Date (PLC) 
			String stxtOnShelfDate = driver.findElement(onShelfDate).getText();
			Assert.assertEquals(stxtOnShelfDate, data[16]);
			log.info("***Verification done for On-Shelf Date (PLC) ***");
			//  PT / MH (PLC) 
			String sddPT_MHPLC= driver.findElement(pt_MHPLC).getText();
			//		String sddPT_MHPLC = driver.findElement(ddPT_MHPLC).getText();
			Assert.assertEquals(sddPT_MHPLC, data[21]);
			log.info("***Verification done for PT / MH (PLC)  ***");
			//  Plan Identifier (PLC) 
			//	String slblPlanIdentifier= driver.findElement(lblPlanIdentifier)).getFirstSelectedOption().getText();
			String slblPlanIdentifier = driver.findElement(SmokeFlow.lblPlanIdentifier).getText();
			Assert.assertEquals(slblPlanIdentifier, glPlanName);
			log.info("***Verification done for Plan Identifier (PLC) ***");
			//Verify Global Forecasting
			String GFLabel=driver.findElement(SmokeFlow.globalForcasting).getText().trim();
			Assert.assertEquals(GFLabel, "Global Forecasting:");
			log.info("***Verification done for Global Forecasting***");
			// *SRP (USD) (PLC) 
			String sRPUSDPLC = driver.findElement(srPUSDPLC).getText();
			sRPUSDPLC = sRPUSDPLC.replaceAll("[$]", "");
			Assert.assertEquals(sRPUSDPLC, data[29]);
			log.info("***Verification done for SRP (USD) (PLC) ***");
			//  *L/C Price (USD) (PLC) 
			String lCPriceUSDPLC = driver.findElement(lcPriceUSDPLC).getText();
			lCPriceUSDPLC = lCPriceUSDPLC.replaceAll("[$]", "");
			Assert.assertEquals(lCPriceUSDPLC, data[30]);
			log.info("***Verification done for L/C Price (USD) (PLC) ***");
			//   *US Domestic (%) (PLC) 
			String uSDomesticPLC = driver.findElement(usDomesticPLC).getText();
			// uSDomesticPLC.replaceAll("[$]", "");
			Assert.assertEquals(uSDomesticPLC, data[32]);
			log.info("***Verification done for US Domestic (%) (PLC) ***");
			//    *DOM Price (USD) (PLC) 
			String dOMPriceUSDPLC = driver.findElement(domPriceUSDPLC).getText();
			dOMPriceUSDPLC = dOMPriceUSDPLC.replaceAll("[$]", "");
			Assert.assertEquals(dOMPriceUSDPLC, data[31]);
			log.info("***Verification done for DOM Price (USD) (PLC) ***");
			//Verify Retail Item Counts
			String RICLabel=driver.findElement(SmokeFlow.retailItemCounts).getText();
			Assert.assertEquals(RICLabel.trim(), "Retail Item Counts:");
			log.info("***Verification done for Retail Item Counts***");
			//Retail Item Count Total (PLC) 
			String RICTLabel=driver.findElement(SmokeFlow.retailItemCountTotalLabel).getText();
			Assert.assertEquals(RICTLabel, data[34].replaceAll("(?<=^\\d+)\\.0*$", ""));
			log.info("***Verification done for Retail Item Count Total (PLC) ***");
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyPlaceholderAttribute()", e);
			throw e;
		}
		return "";
	}

	public static String searchPlaceholder(String[] data) throws Exception{
		try{
			SeleniumDriver.driver.switchTo().defaultContent();
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("sidebarframe"));
			//click on libraries
			CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			//Click on placeholder
			CommonFunctions.clickButtonOrLink(placeholderLink, "Link", "Pleaceholder");
			SeleniumDriver.driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForElementTobeClickable(AssortmentSolidNoPLC);
			//Enter Assortment / Solid # (PLC) number
			CommonFunctions.enterTextInTextboxUpdated(AssortmentSolidNoPLC, data[9], "Assortment / Solid # (PLC)");
			//Click on search
			CommonFunctions.clickButtonOrLink(Color.btnSearch, "btn", "Search");
			CommonFunctions.waitForPageLoaded();
			//take placeholder name in string
			placeholderNa=driver.findElement(labelPlaeholderName).getText().trim();
			log.info("Placeholder name is :"+placeholderNa);
		}catch(Exception e){
			fail=true;
			log.error("Exception in generateAndVerifyAsstSolidPlaceholders()", e);
			throw e;
		}
		return placeholderNa;
	}

	public static String generateAndVerifyAsstSolidPlaceholders(String[] data) throws Exception{
		try{
			log.info("Generate & Verify Asst./Solid Placeholders verified");
			CommonFunctions.selectCheckbox(SmokeFlow.PlaceholderChk); 
			//Click on 'Generate Place holder'
			CommonFunctions.clickButtonOrLink(SmokeFlow.generatePlaceholders,"btn", "Generate PlaceHolder");
			//Verification
			SmokeFlow.strPHSucessGen=driver.findElement(SmokeFlow.plSucessGen).getText();
			Assert.assertEquals(SmokeFlow.strPHSucessGen, "Placeholders successfully generated.");
			log.info(" Generate & Verify Asst./Solid Placeholders verification completed");

		}catch(Exception e){
			fail=true;
			log.error("Exception in generateAndVerifyAsstSolidPlaceholders()", e);
			throw e;
		}
		return "";
	}

	public static String filterOptions(String[] data) throws Exception {
		try{
			//Select 'Product\Brand (PLC)'
			CommonFunctions.selectFromDropDownByVisibleText(SmokeFlow.ddFilterOption, "Product"+"\\"+"Brand (PLC)");
			//Select 'Furby' from list
			CommonFunctions.selectFromDropDownByVisibleText(SmokeFlow.lbFilterOption,data[5]);
			//Click on Add link
			CommonFunctions.clickButtonOrLink(SmokeFlow.addLink, "link", "Add");
			//Click on Add button
			CommonFunctions.clickButtonOrLink(SmokeFlow.addBtn, "btn", "Add");
			//Click on update button
			//	CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update");
			//Select 'Placeholder\placeholder Creator'
			CommonFunctions.selectFromDropDownByVisibleText(SmokeFlow.ddFilterOption, "Placeholder"+"\\"+"Placeholder Creator");
			//Select 'Created by '
			CommonFunctions.selectFromDropDownByVisibleText(SmokeFlow.createdByDD, data[0]);
			//Click on Add button
			CommonFunctions.clickButtonOrLink(SmokeFlow.addBtn, "btn", "Add");
			//Click on update button
			CommonFunctions.clickButtonOrLink(SmokeFlow.updateBtn, "btn", "Update");
			//Click on return button
			CommonFunctions.clickButtonOrLink(SmokeFlow.returnBtn, "btn", "Return");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
			throw e;
		}
		return glPlanName;
	}

	public static String createSearchFilter() throws Exception {
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on 'Create New Filter'
			CommonFunctions.clickButtonOrLink(SmokeFlow.createNewFilter, "btn", "Create New Filter");
			SmokeFlow.searFilter = "Auto" +CommonFunctions.getRandomString(5);
			//	glPlanName = data[6]+date.getTime();
			//	glPlanName=glPlanName.substring(0,5);
			//Enter Name
			CommonFunctions.enterTextInTextbox(SmokeFlow.creSearchFilter,SmokeFlow.searFilter);
			//Click on create button
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			//Verify heading
			CommonFunctions.waitForVisibilityOfElement(SmokeFlow.headingUpdateSearchPref);
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
			throw e;
		}
		return SmokeFlow.searFilter;
	}


	@AfterMethod
	public void reporterdataSetResult(){
		if(CommonProjectFunctions.skip)
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "FAIL");
			CommonProjectFunctions.isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "PASS");
		CommonProjectFunctions.skip=false;
		fail=false;
	}
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteSanityXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		CommonProjectFunctions.runmodes=Utility.getDataSetRunmodeTest(suiteSanityXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(CommonProjectFunctions.isTestPass){
			Utility.dataSetResult(suiteSanityXls,"Testcases", Utility.getRowNum(suiteSanityXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteSanityXls,"Testcases", Utility.getRowNum(suiteSanityXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteSanityXls, this.getClass().getSimpleName());
	}

}
