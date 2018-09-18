package com.hasbrop2m.sanity;

import java.util.ArrayList;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//import java.util.concurrent.TimeUnit;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.apache.xmlbeans.SchemaTypeSystem;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.hasbrop2m.ci2018.CI253;
import com.hasbrop2m.ci2018.CI296;
import com.hasbrop2m.security.BOMMaterialMainUser;
import com.hasbrop2m.security.Color;
import com.hasbrop2m.security.Colorway;
import com.hasbrop2m.security.CostsheetInternal;
import com.hasbrop2m.security.CostsheetTooling;
import com.hasbrop2m.security.ExternalBOM;
import com.hasbrop2m.security.GlobalLinePlan;
import com.hasbrop2m.security.InternalBOMSoftG;
import com.hasbrop2m.security.Material;
import com.hasbrop2m.security.PlaceHolderDevPlan;
import com.hasbrop2m.security.Product;
import com.hasbrop2m.security.Sample;
import com.hasbrop2m.security.SourcingConfig;
import com.hasbrop2m.security.Specifications;
import com.hasbrop2m.security.Supplier;
import com.hasbrop2m.security.CostingApprovalWF;
import com.hasbrop2m.sanity.SmokeFlow;
import com.hasbrop2m.security.Specifications_old;

import com.hasbrop2m.core.SeleniumDriver;
import com.hasbrop2m.mostCommonFunctions.CommonFunctions;
import com.hasbrop2m.mostCommonFunctions.CommonProjectFunctions;
import com.hasbrop2m.core.ErrorUtil;
import com.hasbrop2m.core.Utility;

/**
 * @author anjali.gupta
 *
 */


public class SanitySuite2 extends TestsuiteBase{
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	String loginVal;
	Boolean flaglogin=false;
	int y=0;
	static String Prodname; 
	
	
	public static String prodNumber;
	public static By SeasonDropDown                = By.xpath("//select[@id='splId']");
	public static By productDetailstab             = By.xpath("//a[contains(text(),'Specifications')]//preceding::a[contains(text(),'Details')]");
	public static By CostingTab                    = By.xpath("//a[contains(text(),'Costing')]");
	public static By SourcingTab                   = By.xpath("//a[contains(text(),'Sourcing')]");
	public static By SourceDropDown                = By.xpath("//select[@id='sourcingConfigId']");
	public static By SpecificationsDropDown        = By.xpath("//select[@id='contextSpecId']");
	public static By tabCostsheet                  = By.xpath("//a[text()='Cost Sheet List']");
	public static By lstCostSheetAction            = By.xpath(".//*[@id='costSheetResults']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/select[@onchange='evalList(this)']");
	public static By QuoteCurrencyDropDown         = By.xpath("//select[@id='ptc_str_2']");
	public static By btnSave                       = By.xpath("//a[text()='Save']"); 
	public static By BOMDropDown                   = By.xpath("//select[@id='ptc_str_20']");
	public static By specificationtab              = By.xpath("//a[contains(text(),'Specifications')]");
	public static By costSheetType                 = By.linkText("Internal");
	public static By costSheetTypeVendor           = By.linkText("Vendor");
	
	
	
	//To create BOM
	public static By selectSource                 = By.id("sourcingConfigId");
	public static By selectSpecification          = By.xpath(".//*[@id='contextSpecId']");
	public static By addNewBOMTab                 = By.xpath("//a[contains(text(),'Add New BOM')]");
	public static By BOMTypeId                    = By.id("bomTypeId");
	public static By initializeBOM                = By.xpath("//a[contains(text(),'Initialize BOM')]");
	public static By compOrLoca                   = By.id("r1_partName");
	public static By inputCompOrLoca              = By.xpath("//div[@id='partNameSourceDiv']/input");
	public static By btnSaveAndCheckIn            = By.xpath("//a[contains(text(),'Save and Check In')]");
	//public static By headerAttributes             = By.xpath("//div[@id='genDetails_plus']/a[2]");
	public static By headerAttributes             = By.xpath(".//*[@id='genDetails_plus']/a[2]");
	
	
	public static By strHeaderAttributes          = By.xpath("//a[contains(text(),'Header Attributes')]");
	
	public static By BOMId                        = By.id("bomId");
	static String strBOMInWorkRetail;
	public static By headingCreateBOM            = By.xpath("//td[contains(text(),'Create BOM')]");
	public static By name                        = By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By colorway                    = By.xpath("//td[contains(text(),'Colorway')]//following::select[1]");
	public static By wave                        = By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By currency                    = By.xpath("//td[contains(text(),'Currency')]//following::select[1]");
	public static By PlasticUnitPrice            = By.xpath(".//*[@id='r1_hbUnitPrice']");
	
	
	static String strCW;
	static String BOMnameRetail;
	public static Actions action;
	public static String BOMname;
	static String BOMnameInWorkRetail1;
	
	//Create Product
	public static By mySeasonYear                = By.id("seasonSelectList");
	public static By lebelProdNum                = By.xpath("//td[contains(text(),'Product Number')]//following::a[1]");

	public static String prodName;
	public static String prodNum;
	//Create Cost sheet
	
	
	public static By lnkInternal                 = By.xpath("//a[contains(text(),'Internal')]");
	public static By btnCancel                   = By.xpath("//a[text()='Cancel']");
	public static By csCostSheetList             = By.xpath("//a[@title='Cost Sheet List']");
	public static By csProductNumber             = By.xpath("//td[contains(text(),'Product Number')]//following::a[1]");
	public static By CostSheetType               = By.xpath(".//*[@id='VR:com.ptc.core.meta.type.mgmt.server.impl.WTTypeDefinition:13468587_link']");
	
	public	static String strCostSheetName;
	public	static String cstCreateCS;
	public	static String vendorName ="FUNSKOOL";
	
	//Create BOM
	public static By SpecificationLink            = By.xpath("//a[contains(text(),'Create New Specification')]");
	public static By strWave                      = By.xpath("//select[@id='ptc_str_5']");
	public static By materialstab                 = By.xpath("//a[contains(text(),'Materials')]");
	//PC56
	public static By cellUnitPriceIE                = By.xpath("//td[@id='r1_hbInternalEstimateUnitPrice']");
	public static By inputUnitPriceIE               = By.xpath("//div[@id='hbInternalEstimateUnitPriceSourceDiv']/input");
	public static By cellUnitPrice                  = By.xpath("//td[@id='r1_hbUnitPrice']");
	public static By inputUnitPrice                 = By.xpath("//div[@id='hbUnitPriceSourceDiv']/input");
	public static By AddRowsButton                  = By.xpath("//img[@id='menuImage1']");
	
	public static By plasticCellMarkup            = By.xpath("//td[@id='r1_hbMarkup']");
	public static By plasticInputMarkup           = By.xpath("//div[@id='hbMarkupSourceDiv']/input");
	
	public static By chemicalCellUnitPriceIE       = By.xpath("//td[@id='r2_hbInternalEstimateUnitPrice']");
	public static By chemicalInputUnitPriceIE       = By.xpath("//div[@id='hbInternalEstimateUnitPriceSourceDiv']/input");
	
    public static By chemicalCellUsagePerK        = By.xpath("//td[@id='r2_hbUsagePerK']");
	public static By chemicalInputUsagePerK       = By.xpath("//div[@id='hbUsagePerKSourceDiv']/input");
	
	public static By chemicalCellUnitPrice        = By.xpath("//td[@id='r2_hbUnitPrice']");
	public static By chemicalInputUnitPrice       = By.xpath("//div[@id='hbUnitPriceSourceDiv']/input");
   
	
	public static By chemicalCellMarkup           = By.xpath("//td[@id='r2_hbMarkup']");
	public static By chemicalInputMarkup          = By.xpath("//div[@id='hbMarkupSourceDiv']/input");
	
    public static By purchasedCellUnitPrice       = By.xpath("//td[@id='r3_hbUnitPrice']");
	public static By purchasedInputUnitPrice      = By.xpath("//div[@id='hbUnitPriceSourceDiv']/input");
	
    public static By purchasedCellUsagePerK       = By.xpath("//td[@id='r3_hbUsagePerK']");
    public static By purchasedInputUsagePerK      = By.xpath("//div[@id='hbUsagePerKSourceDiv']/input");
    
    public static By purchasedCellUnitPriceIE       = By.xpath("//td[@id='r3_hbInternalEstimateUnitPrice']");
	public static By purchasedInputUnitPriceIE      = By.xpath("//div[@id='hbInternalEstimateUnitPriceSourceDiv']/input");
  
    
    public static By purchasedCellMarkup          = By.xpath("//td[@id='r3_hbMarkup']");
	public static By purchasedInputMarkup         = By.xpath("//div[@id='hbMarkupSourceDiv']/input");
    
    public static By electCellUnitPrice           = By.xpath("//td[@id='r4_hbUnitPrice']");
    public static By electInputUnitPrice          = By.xpath("//div[@id='hbUnitPriceSourceDiv']/input");
    public static By electCellUnitPriceIE           = By.xpath("//td[@id='r4_hbInternalEstimateUnitPrice']");
    public static By electInputUnitPriceIE          = By.xpath("//div[@id='hbInternalEstimateUnitPriceSourceDiv']/input");
    public static By electCellUsagePerK           = By.xpath("//td[@id='r4_hbUsagePerK']");
	public static By electInputUsagePerK          = By.xpath("//div[@id='hbUsagePerKSourceDiv']/input");
    public static By electCellMarkup              = By.xpath("//td[@id='r4_hbMarkup']");
	public static By electInputMarkup             = By.xpath("//div[@id='hbMarkupSourceDiv']/input");
	
    public static By sGCellUnitPrice              = By.xpath("//td[@id='r5_hbUnitPrice']");
	public static By sGInputUnitPrice             = By.xpath("//div[@id='hbUnitPriceSourceDiv']/input");
	public static By sGCellUnitPriceIE              = By.xpath("//td[@id='r5_hbInternalEstimateUnitPrice']");
	public static By sGInputUnitPriceIE             = By.xpath("//div[@id='hbInternalEstimateUnitPriceSourceDiv']/input");
    public static By sGCellUsagePerK              = By.xpath("//td[@id='r5_hbUsagePerK']");
    public static By sGInputUsagePerK             = By.xpath("//div[@id='hbUsagePerKSourceDiv']/input");
    public static By sGCellMarkup                 = By.xpath("//td[@id='r5_hbMarkup']");
	public static By sGInputMarkup                = By.xpath("//div[@id='hbMarkupSourceDiv']/input");
    
	public static By packagingCellUnitPrice       = By.xpath("//td[@id='r6_hbUnitPrice']");
	public static By packagingInputUnitPrice      = By.xpath("//div[@id='hbUnitPriceSourceDiv']/input");
	public static By packagingCellUnitPriceIE       = By.xpath("//td[@id='r6_hbInternalEstimateUnitPrice']");
	public static By packagingInputUnitPriceIE     = By.xpath("//div[@id='hbInternalEstimateUnitPriceSourceDiv']/input");
    public static By packagingCellUsagePerK       = By.xpath("//td[@id='r6_hbUsagePerK']");
	public static By packagingInputUsagePerK      = By.xpath("//div[@id='hbUsagePerKSourceDiv']/input");
	public static By packagingCellMarkup          = By.xpath("//td[@id='r6_hbMarkup']");
	public static By packagingInputMarkup         = By.xpath("//div[@id='hbMarkupSourceDiv']/input");
	
    public static By generalLabourCellUnitPrice   = By.xpath("//td[@id='r7_hbUnitPrice']");
	public static By generalLabourInputUnitPrice  = By.xpath("//div[@id='hbUnitPriceSourceDiv']/input");
	public static By generalLabourCellUnitPriceIE   = By.xpath("//td[@id='r7_hbInternalEstimateUnitPrice']");
	public static By generalLabourInputUnitPriceIE  = By.xpath("//div[@id='hbInternalEstimateUnitPriceSourceDiv']/input");
    public static By generalLabourCellUsagePerK   = By.xpath("//td[@id='r7_hbUsagePerK']");
	public static By generalLabourInputUsagePerK  = By.xpath("//div[@id='hbUsagePerKSourceDiv']/input");
	public static By generalCellMarkup            = By.xpath("//td[@id='r7_hbMarkup']");
	public static By generalInputMarkup           = By.xpath("//div[@id='hbMarkupSourceDiv']/input");
	
	
    public static By moldingCellUnitPrice         = By.xpath("//td[@id='r8_hbUnitPrice']");
	public static By moldingInputUnitPrice        = By.xpath("//div[@id='hbUnitPriceSourceDiv']/input");
	public static By moldingCellUnitPriceIE       = By.xpath("//td[@id='r8_hbInternalEstimateUnitPrice']");
	public static By moldingInputUnitPriceIE      = By.xpath("//div[@id='hbInternalEstimateUnitPriceSourceDiv']/input");
    public static By moldingCellUsagePerK         = By.xpath("//td[@id='r8_hbUsagePerK']");
	public static By moldingInputUsagePerK        = By.xpath("//div[@id='hbUsagePerKSourceDiv']/input");
	public static By moldingCellMarkup            = By.xpath("//td[@id='r8_hbMarkup']");
	public static By moldingInputMarkup           = By.xpath("//div[@id='hbMarkupSourceDiv']/input");
	public static By moldingInputMarkupPC58       = By.xpath(".//*[@id='FLEXBOMLINK_ptc_dbl_17Input']");
	public static By moldingCellMarkupPC56        = By.xpath("//td[@id='hbMarkUp']");
	public static By moldingCycleTime             = By.xpath("//td[@id='r8_hbInternalEstimateCycleTime']");
	public static By moldingCycleTimeV             = By.xpath("//td[@id='r8_hbCycleTime']");
	public static By moldingInputCycleTime        = By.xpath("//div[@id='hbInternalEstimateCycleTimeSourceDiv']/input");
	public static By moldingInputCycleTimeV        = By.xpath("//div[@id='hbCycleTimeSourceDiv']/input");
	public static By moldingUp                    = By.xpath("//td[@id='r8_hbUp']");
	public static By moldingInputUp               = By.xpath("//div[@id='hbUpSourceDiv']/input");
	
	public static By moldingCostPerHr             = By.xpath("//td[@id='r8_hbInternalEstimateCostPerHr']");
	public static By moldingInputCostPerHr        = By.xpath("//div[@id='hbInternalEstimateCostPerHrSourceDiv']/input");
	public static By moldingCostPerHrV             = By.xpath("//td[@id='r8_hbCostPerHr']");
	public static By moldingInputCostPerHrV        = By.xpath("//div[@id='hbCostPerHrSourceDiv']/input");
	
	
    public static String ActualValue;

	//Validations
   
    public static By strPlasticValue              = By.xpath("//td[@id='hBPlasticMaterial']");
    public static By strChemicalValue             = By.xpath("//td[@id='hbChemicalMaterial']");
    public static By strPurchasedValue            = By.xpath("//td[@id='hBPurchasedMaterial']");
    public static By strElectronicValue           = By.xpath("//td[@id='hBElectronicMaterial']");
    public static By strSGValue                   = By.xpath("//td[@id='hBSoftgoodsMaterial']");
    public static By strPackagingValue            = By.xpath("//td[@id='hBPackagingMaterial']");
    public static By strGenDecLabValue            = By.xpath("//td[@id='hbGeneralDecoLaborTotalCost']");
    public static By strMoldingLabValue           = By.xpath("//td[@id='hbMoldingLaborTotalCost']");
    public static By strRetailItemCostValue       = By.xpath("//td[contains(text(),'Retail Item Cost')]//following::td[2]");
    public static By strProductMarkup             = By.xpath("//td[@id='hbMarkUp']");
    public static By strProductMarkupCost             = By.xpath("//td[@id='hBOverheadMarkups']");
    //public static By strRetailItemCostValue       = By.xpath("//td[contains(text(),'Retail Item Cost')]//following::input[1]");
    public static By strBOMName                   = By.xpath("//td[@id='hbBOM']"); 
    public static By strBOMIteration              = By.xpath("//td[@id='hbBOMIteration']");
    public static By strQuoteCurrency             = By.xpath("//td[@id='hBCurrency']");
    public static By strCurrencyConversionRate    = By.xpath("//td[@id='hbCurrencyConversionRate']");
    public static String totalBOMCostInBOM;
    public static String totalPlasticCostInBOM;
	public static String totalChemicalCostInBOM;
	public static String totalPurchasedCostInBOM;
	public static String totalElectronicCostInBOM;
	public static String totalSGCostInBOM;
	public static String totalPackagingCostInBOM;
	public static String totalGDLaborCostInBOM;
	public static String totalMoldingLaborCostInBOM;
	public static String totalMarkUpCostInBOM;
 
    public static String strCurrency;
    
    //For PC49
    public static By imgViewMaster                = By.xpath(".//*[@id='hbMasterCartonPackagingTabEditorTableDiv_plus']/a[2]//following::img[12]");
    public static By imgViewLabour                = By.xpath(".//*[@id='hbLaborTabEditorTableDiv_plus']/a[2]//following::img[12]");
    public static By imgViewMiscellaneous         = By.xpath(".//*[@id='hbMiscellaneousTabEditorTableDiv_plus']/a[2]//following::img[12]");
    //public static By BOMTablesView                = By.linkText("Costing: Full");
    public static By BOMTablesView                = By.linkText("Internal: Full Plastic");
    public static By BOMTablesViewI                = By.linkText("Internal: Full");
    public static By BOMTablesViewMold            = By.linkText("Internal: Full Molding Labor");
    public static By BOMTablesViewMoldVendor      = By.linkText("Vendor: Full Molding Labor");
    public static By VendorBOMTablesView          = By.linkText("Vendor: Full");
    public static By strShowAll                   = By.xpath(".//*[@id='chooserResultsDiv']/table/tbody/tr[3]/td/span/a[3]");
    
    
    //Master Carton Packaging:
    
    //Unit Price
    public static By mCPackagingCellUnitPrice     = By.xpath("//td[@id='r1_hbUnitPrice']");
    public static By mCPackagingInputUnitPrice    = By.xpath("//div[@id='hbUnitPriceSourceDiv']/input");
    
    
    public static By mCPackagingCellUnitPrice_IE    = By.xpath("//td[@id='r1_hbInternalEstimateUnitPrice']");
    public static By mCPackagingInputUnitPrice_IE   = By.xpath("//div[@id='hbInternalEstimateUnitPriceSourceDiv']/input");
    
    //Usage Per K
    public static By mCPackagingCellUsagePerK     = By.xpath("//td[@id='r1_hbUsagePerK']");
    public static By mCPackagingInputUsagePerK    = By.xpath("//div[@id='hbUsagePerKSourceDiv']/input");
    //MarkUp
    public static By mCPackagingCellMarkUp        = By.xpath("//td[@id='r1_hbMarkup']");
    public static By mCPackagingInputMarkUp       = By.xpath("//div[@id='hbMarkupSourceDiv']/input");

    	

    //Labor:
    
    //Unit Price
    public static By laborCellUnitPrice          = By.xpath("//td[@id='r2_hbUnitPrice']");
    public static By laborInputUnitPrice         = By.xpath("//div[@id='hbUnitPriceSourceDiv']/input");
    
    public static By laborCellUnitPrice_IE         = By.xpath("//td[@id='r2_hbInternalEstimateUnitPrice']");
    public static By laborInputUnitPrice_IE        = By.xpath("//div[@id='hbInternalEstimateUnitPriceSourceDiv']/input");
    
    //Usage Per K
    public static By laborCellUsagePerK          = By.xpath("//td[@id='r2_hbUsagePerK']");
    public static By laborInputUsagePerK         = By.xpath("//div[@id='hbUsagePerKSourceDiv']/input");
    //MarkUp
    public static By laborCellMarkUp             = By.xpath("//td[@id='r2_hbMarkup']");
    public static By laborInputMarkUp            = By.xpath("//div[@id='hbMarkupSourceDiv']/input");
    
 
   //Miscellaneous:
    
    //Unit Price
    public static By miscellaneousCellUnitPrice               = By.xpath("//td[@id='r3_hbUnitPrice']");
    public static By miscellaneousInputUnitPrice              = By.xpath("//div[@id='hbUnitPriceSourceDiv']/input");
    
    public static By miscellaneousCellUnitPrice_IE            = By.xpath("//td[@id='r3_hbInternalEstimateUnitPrice']");
    public static By miscellaneousInputUnitPrice_IE           = By.xpath("//div[@id='hbInternalEstimateUnitPriceSourceDiv']/input");
    
    //Usage Per K
    public static By miscellaneousCellUsagePerK               = By.xpath("//td[@id='r3_hbUsagePerK']");
    public static By miscellaneousInputUsagePerK              = By.xpath("//div[@id='hbUsagePerKSourceDiv']/input");
    //MarkUp
    public static By miscellaneousCellMarkUp                  = By.xpath("//td[@id='r3_hbMarkup']");
    public static By miscellaneousInputMarkUp                 = By.xpath("//div[@id='hbMarkupSourceDiv']/input");
    //Cost sheet Type
    public static By strcostSheetType                         = By.linkText("Vendor");
    public static By currencyConversionRate                   = By.xpath("//td[@id='hbCurrencyConversionRate']");
    public static By quoteCurrency                            = By.xpath("//td[@id='hbCurrency']");
    public static By cSBOMName                                = By.xpath("//td[@id='hbBOM']");
    public static By cSBOMIteration                           = By.xpath("//td[@id='hbBOMIteration']");
    public static By masterCartonPackagingMaterialUSDValue    = By.xpath("//td[@id='hbMasterCartonPackagingMaterial']");
    public static By masterCartonLaborCostUSD                 = By.xpath("//td[@id='hbMasterCartonPackagingLaborCost']");
    public static By miscelleneousMaterialValue               = By.xpath("//td[@id='hbMiscellaneousMaterial']");
	public static By productMarkUpValue                       = By.xpath("//td[@id='hbProductMarkup']");
	public static By strColorwayName                          = By.xpath("//td[contains(text(),'Colorway Name')]//following::select[1]");
    public static By MasterCartonEditButton                   = By.xpath("//img[@id='menuImage1']");
	
	public static String strColorway;
	
	 //For PC50
	/*public static By imgViewPlastics                         = By.xpath(".//*[@id='hbPlasticsTabEditorTableDiv_plus']/a[2]//following::img[12]");
	public static By imgViewChemical                         = By.xpath(".//*[@id='hbChemicalsTabEditorTableDiv_plus']/a[2]//following::img[12]");
	public static By imgViewPurchased                        = By.xpath(".//*[@id='hbPurchasedPartsTabEditorTableDiv_plus']/a[2]//following::img[12]");
	public static By imgViewElectronics                      = By.xpath(".//*[@id='hbElectronicsTabEditorTableDiv_plus']/a[2]//following::img[12]");
	public static By imgViewSG                               = By.xpath(".//*[@id='hbSoftGoodsTabEditorTableDiv_plus']/a[2]//following::img[12]");
	public static By imgViewPackaging                        = By.xpath(".//*[@id='hbPackagingTabEditorTableDiv_plus']/a[2]//following::img[12]");
	public static By imgViewGenDecLabor                      = By.xpath(".//*[@id='hbGeneralOrDecoLaborTabEditorTableDiv_plus']/a[2]//following::img[12]");
	public static By imgViewMoldingLabor                     = By.xpath(".//*[@id='hbMoldingLaborTabEditorTableDiv_plus']/a[2]//following::img[12]");
    */		
	
	//PC48
	/*public static By imgViewPlastics                         = By.id("hbPlasticsTabEditorTableDiv");
	public static By imgViewChemical                         = By.id("hbChemicalsTabEditorTableDiv");
	public static By imgViewPurchased                        = By.id("hbPurchasedPartsTabEditorTableDiv");
	public static By imgViewElectronics                      = By.id("hbElectronicsTabEditorTableDiv");
	public static By imgViewSG                               = By.id("hbSoftGoodsTabEditorTableDiv");
	public static By imgViewPackaging                        = By.id("hbPackagingTabEditorTableDiv");
	public static By imgViewGenDecLabor                      = By.id("hbGeneralOrDecoLaborTabEditorTableDiv");
	public static By imgViewMoldingLabor                     = By.id("hbMoldingLaborTabEditorTableDiv");*/
	public static By imgViewPlastics                         = By.xpath(".//*[@id='hbPlasticsTabEditorTableDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a/img");
	public static By imgViewChemical                         = By.xpath(".//*[@id='hbChemicalsTabEditorTableDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a/img");
	public static By imgViewPurchased                        = By.xpath(".//*[@id='hbPurchasedPartsTabEditorTableDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a/img");
	public static By imgViewElectronics                      = By.xpath(".//*[@id='hbElectronicsTabEditorTableDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a/img");
	public static By imgViewSG                               = By.xpath(".//*[@id='hbSoftGoodsTabEditorTableDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a/img");
	public static By imgViewPackaging                        = By.xpath(".//*[@id='hbPackagingTabEditorTableDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a/img");
	public static By imgViewGenDecLabor                      = By.xpath(".//*[@id='hbGeneralOrDecoLaborTabEditorTableDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a/img");
	public static By imgViewMoldingLabor                     = By.xpath(".//*[@id='hbMoldingLaborTabEditorTableDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a/img");
    
	//PC48_Costsheet Validations:
	public static By strTotalBOMCostValueCS                 = By.xpath("//td[@id='hbExFactorySAPBasePriceUSDConversion']");
	public static By strPlasticValueCS                      = By.xpath("//td[@id='hbPlasticMaterialUSDConversion']");
    public static By strChemicalValueCS                     = By.xpath("//td[@id='hbChemicalMaterialUSDConversion']");
    public static By strPurchasedValueCS                    = By.xpath("//td[@id='hbPurchasedMaterialUSDConversion']");
    public static By strElectronicValueCS                   = By.xpath("//td[@id='hbElectronicMaterialUSDConversion']");
    public static By strSGValueCS                           = By.xpath("//td[@id='hbSoftgoodsMaterialUSDConversion']");
    public static By strPackagingValueCS                    = By.xpath("//td[@id='hbRetailPackagingMaterialUSDConversion']");
    public static By strGenDecLabValueCS                    = By.xpath("//td[@id='hbContentsGeneralDecoLaborTotalUSD']");
    public static By strMoldingLabValueCS                   = By.xpath("//td[@id='hbContentsMoldingLaborCostUSD']");
    //public static By strRetailItemCostValueBOM       = By.xpath("//td[contains(text(),'Retail Item Cost')]//following::td[2]");
    public static By strProductMarkupCS                     = By.xpath("//td[@id='hbMarkUpUSDConversion']");
    public static By strProductOverHeadMarkupCS             = By.xpath("//td[@id='hbOverheadMarkupsUSDConversion']");
    
    
    
    //Remove BOM From Vendor Retail Item Costsheet
    public static By updateCSActions                         = By.xpath(".//*[@id='costSheetResults']//following::select[1]");
    public static By updatePlasticMaterial                   = By.xpath("//td[contains(text(),'Plastic Material')]//following::input[1]");
    public static By updateChemicalMaterial                  = By.xpath("//td[contains(text(),'Chemical Material')]//following::input[1]");
    public static By updatePurchasedMaterial                 = By.xpath("//td[contains(text(),'Purchased Material')]//following::input[1]");
    public static By updateElectronicMaterial                = By.xpath("//td[contains(text(),'Electronic Material')]//following::input[1]"); 
    public static By updateSGsMaterial                       = By.xpath("//td[contains(text(),'Soft Goods Material')]//following::input[1]");
    public static By updatePackagingMaterial                 = By.xpath("//td[contains(text(),'Packaging Material')]//following::input[1]");   
    public static By updateGenDecLaborMaterial               = By.xpath("//td[contains(text(),'General / Deco Labor Cost')]//following::input[1]");     
    public static By updateMoldingLaborCost                  = By.xpath("//td[contains(text(),'Molding Labor Cost')]//following::input[1]");   
    public static By updateMarkup                            = By.xpath("//td[contains(text(),'Overhead & Markup')]//following::input[1]");
    public static By validateOverheadMarkups                 = By.xpath("//td[@id='hBOverheadMarkups']");
    
    //PC22
    public static By strBOMType                              = By.xpath("//td[contains(text(),'Type')]//following::td[1]");
    public static By strGeneralAttributes                    = By.xpath("//td[contains(text(),'General Attributes:')]");
    public static By strVendorBOMCostSummary                 = By.xpath("//td[contains(text(),'Vendor BOM Cost Summary:')]");
    public static By strGeneralAttributesFactory             = By.xpath("//td[contains(text(),'General Attributes:')]//following::td[contains(text(),'Factory Region')]//preceding::a[2][text()='Factory:']");
    public static By strSupplierFromSearchResults            = By.xpath("//td[contains(text(),'AEQUS ENGINEERED PLASTICS PRIV')]");
    //public static By strSupplierFromSearchResultsTM            = By.xpath("//td[contains(text(),'WORLD TOYMAKER 1')]");
    public static By strSupplierFromSearchResultsTM            = By.xpath("//td[contains(text(),'AEQUS ENGINEERED PLASTICS PRIV')]");
    public static By strGeneralAttributesCOO                 = By.xpath("//td[contains(text(),'General Attributes:')]//following::a[contains(text(),'Factory:')]//preceding::td[contains(text(),'COO')]");
    public static By strGeneralAttributesFactoryRegion       = By.xpath("//td[contains(text(),'General Attributes:')]//following::td[contains(text(),'Vendor BOM Status:')]//preceding::td[contains(text(),'Factory Region')]//following::td[1]");
    public static By strFactoryRegion                        = By.xpath("//td[contains(text(),'COO')]//following::td[1])");
    public static By strCOO                                  = By.xpath("//td[contains(text(),'COO')]//following::td[1])");
    public static By BOMTablesViewVendor                     = By.linkText("Vendor: Full");
    public static By BOMTablesViewVendorLabor                = By.linkText("Vendor: Full Labor");
    public static String actSeq;
    /*public static By BOMTablesViewMaster                     = By.xpath(".//*[@id='hbMiscellaneousTabEditorTableDiv']//following::b[contains(text(),'View: Vendor: Full')]");
    public static By BOMTablesViewLabor                      = By.xpath(".//*[@id='hbMiscellaneousTabEditorTableDiv']//following::b[contains(text(),'View: Vendor: Full Labor')]");
    public static By BOMTablesViewMiscellaneous              = By.xpath(".//*[@id='hbMiscellaneousTabEditorTableDiv']//following::b[contains(text(),'View: Vendor: Full')]");*/
    
    /*public static By imgViewMasterVendor                     = By.xpath(".//*[@id='hbMasterCartonPackagingTabEditorTableDiv']/a[2]//following::img[12]");
    public static By imgViewLabourVendor                     = By.xpath(".//*[@id='hbLaborTabEditorTableDiv']/a[2]//following::img[12]");
    public static By imgViewMiscellaneousVendor              = By.xpath(".//*[@id='hbMiscellaneousTabEditorTableDiv_plus']/a[2]//following::img[12]");*/
		
    public static By BOMTablesViewMaster                     = By.xpath(".//*[@id='hbMasterCartonPackagingTabEditorTableDiv']//following::b[contains(text(),'View: Vendor: Full')]");
    public static By BOMTablesViewLabor                      = By.xpath(".//*[@id='hbLaborTabEditorTableDiv']//following::b[contains(text(),'View: Vendor: Full')]");
    public static By BOMTablesViewMiscellaneous              = By.xpath(".//*[@id='hbMiscellaneousTabEditorTableDiv']//following::b[contains(text(),'View: Vendor: Full')]");
    
    //For COO and Factory Region field values
    
	public static By strCOOHearderAttr                       = By.xpath("//td[contains(text(),'COO')]//following::a[1]");
	public static By strFactoryHearderAttr                   = By.xpath("//td[contains(text(),'Factory Region')]//following::td[1]");
	public static String strBOMNameDynamic;
    public static String strVendorShortName                  = "AEQUS";
    public static String strVendorShortNameTM                = "WORLD TOYS 1";
	public static By strColorwayDynamic                      = By.xpath("//td[contains(text(),'Colorway')]//following::td[1]/a[1]");
	public static By strWaveDynamic                          = By.xpath("//td[contains(text(),'Wave')]//following::td[1]");
	public static String strColorwayVal;
	public static String strWaveVal;
	public static By strBOMTableOrderMaster                  = By.xpath("//a[contains(text(),'Master Carton Packaging')]");
	public static By strBOMTableOrderLabor                   = By.xpath("//a[contains(text(),'Master Carton Packaging')]//following::a[contains(text(),'Labor')]");
	public static By strBOMTableOrderMisce                   = By.xpath("//a[contains(text(),'Master Carton Packaging')]//following::a[contains(text(),'Labor')]//following::a[contains(text(),'Miscellaneous')]");
	public static By ProductType;
	public static By ProductNumberSearch                     = By.xpath("//td[contains(text(),'Product Number')]//following::input[1]"); 
	public static By SearchProductButton                     = By.xpath("//a[text()='Search']"); 
	public static By strAddNewBOM                            = By.xpath("//a[contains(text(),'Add New BOM')]");
	public static By strBOMNameFromEditBOMPage               = By.xpath("//a[contains(text(),'Header Attributes')]//following::td[contains(text(),'BOM Identification')]//following::td[contains(text(),'Name')]//following::td[1]");
	
	
	//pc56
	
	//public static By strAddNewBOM                          = By.xpath("//a[contains(text(),'Add New BOM')]");
	public static By strUpdateBOM                            = By.xpath("//a[contains(text(),'Update')]");
	public static String strPrdNum;
	public static String strPrdNo;
	public static String seq;
	
	//PC05
	public static By strUpdateSourceActions                  = By.xpath("//td[contains(text(),'Actions')]//following::select[1]");
	public static By strUpdateSourceStatus                   = By.xpath("//td[contains(text(),'Sourcing Status')]//following::select[1]");
	public static By sourceActions                           = By.xpath(".//*[@id='prodseasonActions']");
	public static By selectSourceCheckbox1                   = By.xpath("//td[contains(text(),'Sourcing Configs')]//following::input[2]");
	public static By selectSourceCheckbox2                   = By.xpath("//td[contains(text(),'Sourcing Configs')]//following::input[3]");
	public static By selectSourceCheckbox3                   = By.xpath("//td[contains(text(),'Sourcing Configs')]//following::input[4]");
	public static By strSelect                               = By.xpath("//a[contains(text(),'Select')]");
	public static By strSourcingConfigurationTask            = By.xpath(".//*[@id='upcoming|~*~|com.lcs.wc.sourcing.LCSSourcingConfigContentIcon']");
	public static By strSourcingConfigurationTaskLast        = By.xpath(".//*[@id='upcoming|~*~|com.lcs.wc.sourcing.LCSSourcingConfigContent']/div[1]/a");
	public static By strSourcingConfigApproverTask           = By.xpath("//td[contains(text(),'Confirm Sourcing Configuration')]//following::a[contains(text(),'007 : FUNSKOOL (E6687)')]");
	//public static By strCreateBOMPageName                  = By.xpath("//td[contains(text(),'Create New BOM')]//following::td[contains(text(),'General Attributes:')]//preceding::td[contains(text(),'Name')]");
	public static By strCreateBOMPageName                    = By.xpath("//td[contains(text(),'Name')]//following::td[1]");
	
	public static By strVerifyFactory                        = By.xpath("//a[contains(text(),'Aequs Engineered Plastics Priv')]");
	public static By strBOMTableOrderPlastic                 = By.xpath("//a[contains(text(),'Plastics')]");
	public static By strBOMTableOrderChemical                = By.xpath("//a[contains(text(),'Plastics')]//following::a[contains(text(),'Chemicals')]");
    public static By strBOMTableOrderPurchasedParts          = By.xpath("//a[contains(text(),'Plastics')]//following::a[contains(text(),'Chemicals')]//following::a[contains(text(),'Purchased Parts')]");
    public static By strBOMTableOrderElectronics             = By.xpath("//a[contains(text(),'Plastics')]//following::a[contains(text(),'Chemicals')]//following::a[contains(text(),'Purchased Parts')]//following::a[contains(text(),'Electronics')]");
    public static By strBOMTableOrderSoftgoods               = By.xpath("//a[contains(text(),'Plastics')]//following::a[contains(text(),'Chemicals')]//following::a[contains(text(),'Purchased Parts')]//following::a[contains(text(),'Electronics')]//following::a[contains(text(),'Soft Goods')]");
    public static By strBOMTableOrderPackaging               = By.xpath("//a[contains(text(),'Plastics')]//following::a[contains(text(),'Chemicals')]//following::a[contains(text(),'Purchased Parts')]//following::a[contains(text(),'Electronics')]//following::a[contains(text(),'Soft Goods')]//following::a[contains(text(),'Packaging')]");
    public static By strBOMTableOrderGenDecoLabor            = By.xpath("//a[contains(text(),'Plastics')]//following::a[contains(text(),'Chemicals')]//following::a[contains(text(),'Purchased Parts')]//following::a[contains(text(),'Electronics')]//following::a[contains(text(),'Soft Goods')]//following::a[contains(text(),'Packaging')]//following::a[contains(text(),'General / Deco Labor')]");
    public static By strBOMTableOrderMoldingLabor            = By.xpath("//a[contains(text(),'Plastics')]//following::a[contains(text(),'Chemicals')]//following::a[contains(text(),'Purchased Parts')]//following::a[contains(text(),'Electronics')]//following::a[contains(text(),'Soft Goods')]//following::a[contains(text(),'Packaging')]//following::a[contains(text(),'General / Deco Labor')]//following::a[contains(text(),'Molding Labor')]");
    public static By strGeneralAttributesFactoryRegionVal    = By.xpath("//td[contains(text(),'General Attributes:')]//following::td[contains(text(),'Vendor BOM Status:')]//preceding::td[contains(text(),'Factory Region')]//following::td[1]");
    public static By strSouceState                           = By.xpath("//td[contains(text(),'Sourcing Status')]//following::td[1]");
    public static By strSeasonDropdown1                      = By.xpath("//td[contains(text(),'Season')]");
    public static By strVerifyFactoryPC06                    = By.xpath(".//*[@id='4838566']/td[11]");
    
    //PC07
    public static By strSouceLifecycleState                  = By.xpath("//td[contains(text(),'Source Lifecycle State')]//following::td[1]");
    public static By electronicsIncluded                     = By.xpath("//td[contains(text(),'Electronics Included')]");
	public static By softgoodsIncluded                       = By.xpath("//td[contains(text(),'Softgoods Included')]");
	//public static By strExpandSource                         = By.xpath(".//*[@id='sourcesOptionsdiv_plus']/a[1]/img");
	public static By strSelectSource                        = By.xpath(".//*[@id='sourcesOptionsdiv_plus']/input");
	//public static By strSelectSource2                     = By.xpath(".//*[@id='source2Optionsdiv_plus']/input");
	public static By strVerifyFactoryPC07                   = By.xpath("//a[contains(text(),'choose')]//following::td[10]");
	
	//PC04
	public static By VendorLink                             = By.linkText("Vendor (Supplier)");
	public static By VendorOrSupplierSearch                 = By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By VendorType;
	public static By strSupplierStatusInactive              = By.xpath("//td[contains(text(),'Status')]//following::td[1]");
	public static By strClickSupplier                       = By.xpath("//a[contains(text(),'Name')]//following::a[21]");
	//public static String sourcename1                        = "PACIFIC";
	//public static String sourcename2                        = "PALS PLUSH";
	
	public static String sourcename1                          = "ADVENT";
	public static String sourcename2                          = "AEQUS";
	public static String sourcename3                          = "ALLIED";
	//public static String sourcename3                        = "ADVENT";
	public static By strSortOptions                           = By.xpath("//a[contains(text(),'Sort Options')]");
	
	public static By strselectsourcenameAApprover1          = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[6]");
	public static By strselectsourcenameAApprover2          = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[7]");
	public static By strselectsourcenameBApprover1          = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[10]");
	public static By strselectsourcenameBApprover2          = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[11]");
	public static By strselectsourcenameCApprover1          = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[2]");
	public static By strselectsourcenameCApprover2          = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[3]");
	public static By strselectsourcenameAName               = By.xpath("//td[contains(text(),'Strategic Sourcing Sub Category')]//following::input[3]");
	public static By strselectsourcenameBName               = By.xpath("//td[contains(text(),'Strategic Sourcing Sub Category')]//following::input[10]");
	public static By strselectsourcenameCName               = By.xpath("//td[contains(text(),'Strategic Sourcing Sub Category')]//following::input[17]");
	public static By strCloseButton                         = By.xpath(".//*[@id='divHeader']/div/div[2]/div/table/tbody/tr/td[2]/img");
	//public static By mySiteTab                              = By.id("siteNavLink");
	public static By mySiteTab                              = By.xpath(".//*[@id='siteNavLink']/a");
	
	public static By strMultipleSourcingConfigurationTaskLast= By.xpath(".//*[@id='upcoming|~*~|com.lcs.wc.sourcing.LCSSourcingConfigContent']/div/a");
    
	 //PC57
	public static By strQuoteCurrencyBOM                         = By.xpath("//td[@id='hbBOMCurrency']");
	public static By masterCartonPackagingMaterialUSDValueBOM    = By.xpath("//td[@id='hbTotalMasterCartonPkgCost']");
    public static By masterCartonLaborCostUSDBOM                 = By.xpath("//td[@id='hbTotalLaborCost']");
    public static By miscelleneousMaterialValueBOM               = By.xpath("//td[@id='hbTotalMiscellaneousCost']");
	public static By productMarkUpValueBOM                       = By.xpath("//td[@id='hbTotalMarkUpCost']");
	
	//PC58
	public static By strTotalBOMCostValueBOM              = By.xpath("//td[@id='hbTotalBOMCost']");
	public static By strPlasticValueBOM              = By.xpath("//td[@id='hbTotalPlasticCost']");
    public static By strChemicalValueBOM            = By.xpath("//td[@id='hbTotalChemicalsCost']");
    public static By strPurchasedValueBOM            = By.xpath("//td[@id='hbTotalPurchasedPartsCost']");
    public static By strElectronicValueBOM           = By.xpath("//td[@id='hbTotalElectronicsCost']");
    public static By strSGValueBOM                   = By.xpath("//td[@id='hbTotalSoftGoodsCost']");
    public static By strPackagingValueBOM            = By.xpath("//td[@id='hbTotalPackagingCost']");
    public static By strGenDecLabValueBOM            = By.xpath("//td[@id='hbTotalGenDecoLaborCost']");
    public static By strMoldingLabValueBOM           = By.xpath("//td[@id='hbTotalMoldingLaborCost']");
    //public static By strRetailItemCostValueBOM       = By.xpath("//td[contains(text(),'Retail Item Cost')]//following::td[2]");
    public static By strProductMarkupBOM             = By.xpath("//td[@id='hbTotalMarkUpCost']");
    public static By imgViewMoldingLaborFull         = By.xpath(".//*[@id='hbTotalMoldingLaborCost']/a[2]//following::img[12]");
    public static By BOMTablesViewMoldingFullView    = By.linkText("Vendor: Full Molding Labor");
    
    public static By moldingCellMoldNumber           = By.xpath("//td[@id='r8_hbMoldNo']");
    public static By moldingCellMoldNumberDuplicate  = By.xpath("//td[@id='r8_hbDuplicateMoldNo']");
    public static By moldingCellMoldUp               = By.xpath("//td[@id='r8_hbUp']");
    public static By moldingCellTotalCar             = By.xpath("//td[@id='r8_hbTotalCav']");
    public static By moldingCellCostPerHr            = By.xpath("//td[@id='r8_hbCostPerHr']");
    public static By moldingCellEfficiency            = By.xpath("//td[@id='r8_hbEfficiencyPercent']");
    public static By moldingCellCycleTime            = By.xpath("//td[@id='r8_hbCycleTime']");
    public static By moldingCellWeeklyCap            = By.xpath("//td[@id='r8_hbWeeklyCapK']");
    public static By moldingCellMachineSize           = By.xpath("//td[@id='r8_hbMachineSizeTON']");
    
    public static By moldingCellMoldNumberInpu             = By.xpath("//div[@id='hbMoldNoSourceDiv']/input");
    
    //public static By moldingCellMoldNumberInpu             = By.xpath(".//*[@id='FLEXBOMLINK_ptc_str_18']");
    
    //public static By miscellaneousInputUnitPrice              = By.xpath("//div[@id='hbUnitPriceSourceDiv']/input");
    //public static By moldingCellMoldNumberDuplicateInput   = By.xpath("//div[@id='hbDuplicateMoldNoSourceDiv']/input");
    public static By moldingCellMoldNumberDuplicateInput   = By.xpath(".//*[@id='FLEXBOMLINK_ptc_str_13']");
    
    //public static By moldingCellMoldUpInput                = By.xpath("//div[@id='hbUpSourceDiv']/input");
    public static By moldingCellMoldUpInput                = By.xpath(".//*[@id='FLEXBOMLINK_ptc_dbl_22Input']");
    
    //public static By moldingCellTotalCarInput            = By.xpath("//div[@id='hbTotalCavSourceDiv']/input");
    public static By moldingCellTotalCarInput            = By.xpath(".//*[@id='FLEXBOMLINK_ptc_dbl_23Input']");
    
    //public static By moldingCellCostPerHrInput            = By.xpath("//div[@id='hbCostPerHrSourceDiv']/input");
    public static By moldingCellCostPerHrInput            = By.xpath(".//*[@id='FLEXBOMLINK_ptc_dbl_21Input']");
    
    //public static By moldingCellEfficiencyInput            = By.xpath("//div[@id='hbEfficiencyPercentSourceDiv']/input");
    public static By moldingCellEfficiencyInput            = By.xpath(".//*[@id='FLEXBOMLINK_ptc_dbl_29Input']");
    
    //public static By moldingCellCycleTimeInput            = By.xpath("//div[@id='hbCycleTimeSourceDiv']/input");
    public static By moldingCellCycleTimeInput            = By.xpath(".//*[@id='FLEXBOMLINK_ptc_dbl_27Input']");
    
    //public static By moldingCellWeeklyCapInput            = By.xpath("//div[@id='hbWeeklyCapKSourceDiv']/input");
    public static By moldingCellWeeklyCapInput            = By.xpath(".//*[@id='FLEXBOMLINK_ptc_dbl_20Input']");
    
    //public static By moldingCellMachineSizeInput            = By.xpath("//div[@id='hbMachineSizeTONSourceDiv']/input");
    public static By moldingCellMachineSizeInput            = By.xpath(".//*[@id='FLEXBOMLINK_ptc_dbl_32Input']");
	
    //PC11
    static String matName;
    public static By resinDescription                        = By.xpath("//td[contains(text(),'*Material Description')]//following::input[1]");
    public static By processingMethod                        = By.xpath("//td[contains(text(),'Processing Method')]//following::select[1]"); 
    public static By unitOfMeasure                           = By.xpath("//td[contains(text(),'Unit Of Measure')]//following::select[1]");
    public static By operationType                           = By.xpath("//td[contains(text(),'Operation Type')]//following::select[1]");
    public static By seasonLnk                               = By.xpath("//a[contains(text(),'Season:')]");
    static String exchRate;
    public static By exchangeRate                            = By.id("hbExchangeRate");
    public static By businessObj                             = By.linkText("Business Object");
    public static By fxRatesBusiUnit                         = By.linkText("FX Rates - Business Unit");
    public static By fxRate                                  = By.id("hbFXRate");
    static String boExchRate;
    public static By pricingTypeEdit                         = By.xpath("//td[contains(text(),'Pricing Chart')]//following::a[1]");
    public static By cycleTimeChartEdit                      = By.xpath("//td[contains(text(),'Cycle Time Chart')]//following::a[1]");
    public static By cellBenchmarkCost                       = By.xpath("//td[@id='r1_hbBenchmarkCostHKD']");
	public static By inputBenchmarkCost                      = By.xpath("//div[@id='hbBenchmarkCostHKDSourceDiv']/input");
    public static By cellCountryOfOrigin                     = By.xpath("//td[@id='r1_hbCountryofOrigin']");
	//public static By inputCountryOfOrigin                    = By.xpath("//div[@id='hbCountryofOriginSourceDiv']/input");
    public static By inputCountryOfOrigin                    = By.xpath("//a[@id='ptc_verRef_1Link']");
    
	public static By cellDeviationAttribute                  = By.xpath("//td[@id='r1_hbDeviation']");
	public static By inputDeviationAttribute                 = By.xpath("//div[@id='hbDeviationSourceDiv']/input");
	public static By factoryRegionValue                      = By.xpath("//td[@id='r1_hbFactoryRegion']");
	public static By strMaximumThresholdValueHKD             = By.xpath("//td[@id='r1_hbMaximumThresholdHKD']");
	public static By strMinimumThresholdValueHKD             = By.xpath("//td[@id='r1_hbMinimumThresholdHKD']");
	public static By strMaximumThresholdValueUSD             = By.xpath("//td[@id='r1_hbMaximumThresholdUSD']");
	public static By strMinimumThresholdValueUSD             = By.xpath("//td[@id='r1_hbMinimumThresholdUSD']");
	
	static String benchMarkCostHKD;
	static String benchMarkCostUSD;
	public static By cellFactoryRegion                      = By.xpath("//td[@id='r1_hbFactoryRegion']");
	//public static By factoryRegion                          = By.xpath("//div[@id='hbFactoryRegionSourceDiv']/select");
	//public static By factoryRegion                          = By.xpath("//div[@id='ptc_str_11']");
	
	public static By factoryRegion                          = By.xpath("//td[contains(text(),'Factory Region')]//following::select[1]");
	public static By productCategory                        = By.xpath("//td[contains(text(),'Product Category')]//following::select[1]");
	public static By cellProductCategory                    = By.xpath(".//*[@id='r1_hbCTCProductCategory']");
	public static By inputProductCategory                   = By.xpath("//div[@id='hbCTCProductCategorySourceDiv']/select");
	
	
	
	public static By retailPackageStyle                     = By.xpath("//td[contains(text(),'Retail Package Style')]//following::select[1]");
	public static By cellRetailPackageStyle                  = By.xpath("//td[@id='r1_hbCTCRetailPackageStyle']");
	public static By strProductCategory                     = By.xpath("//td[@id='r1_hbCTCProductCategory']");
	public static By inputRetailPackageStyle                   = By.xpath("//div[@id='hbCTCRetailPackageStyleSourceDiv']/select");
	
	
	public static By strMachineSizeTON                     = By.xpath("//td[@id='r1_hbCTCMachineSize']");
	public static By inputMachineSizeTON                   = By.xpath("//div[@id='hbCTCMachineSizeSourceDiv']/input");
	public static By strRetailPackageInCycleTimeChart       = By.xpath("//td[contains(text(),'Cycle Time Chart')]//following::td[contains(text(),'Retail Package Style')]");
	public static By strRetailPackageStyle                  = By.xpath("//td[@id='r1_hbCTCRetailPackageStyle']");
	public static By benchMarkCycleTime                     = By.xpath("//td[@id='r1_hbCTCBenchmark']");
	public static By inputBenchMarkCycleTime                = By.xpath("//div[@id='hbCTCBenchmarkSourceDiv']/input");
	public static By cellDeviationAttributeCTC              = By.xpath("//td[@id='r1_hbCTCDeviation']");
	public static By inputDeviationAttributeCTC                = By.xpath("//div[@id='hbCTCDeviationSourceDiv']/input");
	public static By cellMinimumThresholdCTC                 = By.xpath("//td[@id='r1_hbCTCMinimumThreshold']");
	//public static By inputMinimumThresholdCTC                = By.xpath("//div[@id='hbCTCMinimumThresholdSourceDiv']/input");
	public static By inputMinimumThresholdCTC                = By.xpath("//a[contains(text(),'Cycle Time Chart')]//following::td[8]");
	public static By cellMaximumThresholdCTC                 = By.xpath("//td[@id='r1_hbCTCMaximumThreshold']");
	//public static By inputMaximumThresholdCTC                = By.xpath("//div[@id='hbCTCMaximumThresholdSourceDiv']/input");
	public static By inputMaximumThresholdCTC                = By.xpath("//a[contains(text(),'Cycle Time Chart')]//following::td[9]");
	public static By strMinimumThresholdValueCTC             = By.xpath("//td[@id='r1_hbCTCMinimumThreshold']");
	
	public static By strMaximumThresholdValueCTC             = By.xpath("//td[@id='r1_hbCTCMaximumThreshold']");
	
	//PC80
	public static By strCostSheetA                            = By.xpath("//a[contains(text(),'TestA')]");
	public static By strCostSheetB                            = By.xpath("//a[contains(text(),'CostA')]");
	public static By strCostSheetRetA                         = By.xpath("//a[contains(text(),'IntRetItem-001')]");
	public static By strCostSheetRetB                         = By.xpath("//a[contains(text(),'IntRetItem-002')]");
	//public static By actionDD                                 = By.xpath(".//*[@id='costSheetResults']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/select");
	public static By actionDD                                 = By.xpath("//td[contains(text(),'Cost Sheet Details:')]//following::select[1]");
	public static By oldIteration                             = By.xpath("//a[contains(text(),'A.1')]");
	public static By actionDDOldIter                          = By.xpath(".//*[@id='costSheetResults']/table/tbody/tr[1]/td/table/tbody/tr/td[2]");
	public static By selectNewIterationCheckbox               = By.xpath(".//*[@id='VR:com.lcs.wc.sourcing.LCSProductCostSheet:84739881']");
	public static By selectOldIterationCheckbox               = By.xpath(".//*[@id='OR:com.lcs.wc.sourcing.LCSProductCostSheet:84739882']");
	public static By selectNewIterationCheckboxCSB            = By.xpath(".//*[@id='VR:com.lcs.wc.sourcing.LCSProductCostSheet:84739835']");
	public static By selectOldIterationCheckboxCSB            = By.xpath(".//*[@id='OR:com.lcs.wc.sourcing.LCSProductCostSheet:84739836']");
	public static By strCompareIterations                     = By.xpath("//a[contains(text(),'Compare Iterations')]");
	public static By strCURRENTIterations                     = By.xpath("//a[contains(text(),'Current Iteration')]");
	public static By strBackButton                            = By.xpath("//a[contains(text(),'Back')]");
	public static By selectNewIterationCheckboxRetCSB1         = By.xpath("//td[contains(text(),'Created On')]//following::input[1]");
	public static By selectOldIterationCheckboxRetCSB2         = By.xpath("//td[contains(text(),'Created On')]//following::input[2]");
	public static By verifyOldIteration                        = By.xpath("//td[contains(text(),'Cost Sheet Details:')]//following::td[1]");
	//td[contains(text(),'Cost Sheet Details:')]//following::select[1]
	public static String strOldIteration;
	
	//SC1
	//public static By lnkSourConfig = By.xpath("//div[@id='workListDiv']/div[2]/a[contains(text(),'Sourcing Configuration (4)')]");
	public static By lnkSourConfig = By.xpath("//div[@id='workListDiv']/div[2]/a");
	public static By lnkSourConfigSC1 = By.xpath("//div[@id='workListDiv']/div[4]/a");
	public static By lnkSourConfigSC2 = By.xpath("//div[@id='workListDiv']/div[2]/a");
	public static By lnkSourConfigAP1 = By.xpath("//div[@id='workListDiv']/div[2]/a");
	public static By lnkConfirmSourConfig = By.xpath("//a[contains(text(),'Set Proposed Sourcing Status')]");
	static int i;
	public static By lnkConfirmSourConfigSCHSC1 = By.xpath("//div[@id='workListDiv']/div[4]/a//following::a[1]");
	public static By lnkConfirmSourConfigSCH = By.xpath("//div[@id='workListDiv']/div[2]/a//following::a[1]");
	
	public static By lnkConfirmSourConfigSC1 = By.xpath("//div[@id='workListDiv']/div[2]/a//following::a[4]");
	public static By lnkConfirmSourConfigSC2 = By.xpath("//div[@id='workListDiv']/div[2]/a//following::a[1]");
	public static By approveRadioButton = By.xpath(".//*[@id='allSpace']/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]/table/tbody/tr[3]/td[1]/input");
	public static By rejectRadioButton = By.xpath(".//*[@id='allSpace']/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]/table/tbody/tr[3]/td[1]/input");
	//Copy/Link Product
	public static By SourcingDropDown        = By.xpath("//td[contains(text(),'Source')]//following::select[1]");
	public static By RFQPageActionDropDown   = By.xpath("//select[@id='prodseasonActions']");
	public static By ProductTypeInCopyLink   = By.xpath("//select[@id='productTypedata']");
	public static By RelationShipType        = By.xpath("//select[@id='copyModedata']");
	public static By ColorwayCheckBox        = By.xpath("//div[@id='colorwayOptionsdiv_plus']/input");
	public static By CopyLinkNextButton      = By.xpath("//a[text()='Next']");
	public static By SoftGoodsIncluded       = By.xpath("//td[contains(text(),'*Softgoods Included')]//following::select[1]");
	public static By ElectronicsIncluded     = By.xpath("//td[contains(text(),'*Electronics Included')]//following::select[1]");
	public static By ViewProductButton       = By.xpath("//a[text()='View Product.']");
	public static By RetailItemIdentification = By.xpath("//td[@id='contextBar']/table/tbody/tr/td[1]/a[2]");
	public static String CopyLinkRetailItem1;
	public static By ProductNumberSolid = By.xpath("//a[contains(text(),'Sort Options')]//following::a[67]");
	public static By ProductNumberRetail = By.xpath("//a[contains(text(),'Sort Options')]//following::a[64]");
	

	
	//SC3
	public static By updateSource = By.xpath(".//*[@id='sourcingActions']//following::select[1]");
	
	//Webelements for PD23:
	public static By productNumberFromSummaryPage             = By.xpath("//td[@id='contextBar']/table/tbody/tr/td[1]/a[1]");
	public static String prodNum1;
	public static String prodNum2;
	public static String prodNum3;
	public static String prodNum4;
	public static String prodNum5;
	public static String prodNum6;
	public static String prodNum7;
	public static String CopyLinkRetailItem;
	public static String strDistributionChannel;
    public static By SourceCheckBox                           = By.xpath("//div[@id='sourcesOptionsdiv_plus']/input");
    public static By strDetailedWaveHDMEdit                   = By.xpath("//td[contains(text(),'Detailed Wave - HDM:')]//following::a[1]");
    public static By colorwayTextBox1                          = By.xpath("//td[@id='r1_retailItemOrWave']");
    public static By colorwayTextBox2                          = By.xpath("//td[@id='r2_retailItemOrWave']");
    public static By colorwayTextBox3                          = By.xpath("//td[@id='r3_retailItemOrWave']");
    public static By colorwayTextBox4                          = By.xpath("//td[@id='r4_retailItemOrWave']");
    public static By colorwayTextBox5                          = By.xpath("//td[@id='r5_retailItemOrWave']");
	public static By colorwayLink                              = By.xpath("//a[@id='ptc_verRef_2Link']");
	public static By RetailItemLink                            = By.linkText("Retail Item");
	public static By BundlePackLink                            = By.linkText("Bundle Pack");
	public static By TradeMarketPalletLink                     = By.linkText("Trade Marketing Pallet");
	public static By AOrSProductLink                           = By.linkText("Assortment/Solid");
	public static By strProductNumber                         = By.xpath("//td[contains(text(),'Product Number')]//following::input[1]");
	public static By strSelectProduct                         = By.xpath("//a[contains(text(),'Sort Options')]//following::a[62]");
	public static By strSelectProductBP                       = By.xpath("//a[contains(text(),'Sort Options')]//following::a[64]");
	public static By strSelectProductTMP                      = By.xpath("//a[contains(text(),'Sort Options')]//following::a[50]");
	
	public static By strInsertRow                             = By.xpath("//a[contains(text(),'Insert')]");
	static String strSpec;
	
	//PC55 WebElements:
	public static By ProductLink                               = By.linkText("Product");
	public static By Details                                   = By.linkText("Details");
	public static By ClickAgainProduct                         = By.xpath("//td[@id='contextBar']/table/tbody/tr/td[1]/a[2]");
	public static By CostingHyperLink                          = By.xpath("//a[text()='Costing']");
	public static By SourcingHyperLink                         = By.xpath("//a[text()='Sourcing']");
	public static By SortOption                                = By.xpath("//a[contains(text(),'Hide/Show columns')]//following::a[1]");
	public static By CostSheetDropDown                         = By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/select");
	public static By CloseCostSheet                            = By.xpath("//li[@id='firstTab']//following::img[1]");
	public static By WhatIfCostSheetCheckBox                   = By.xpath("//input[@id='includeWhatIfBoxbox']");
	public static By FirstSortDropDown                         = By.xpath("//td[contains(text(),'Sort By')]/select[1]");
	public static By SecondSortDropDown                        = By.xpath("//td[contains(text(),'Sort By')]//following::select[contains(@id,'firstSortByOrder')]");
	public static By SortButton                                = By.xpath("//a[@id='multiSortButton']");
	public static By WhatIfCostSheetSelectionCostingUser       = By.xpath("//a[contains(text(),'Sourcing Configuration')]//following::td[4]/a[1]");
	public static By QuoteCurrencyInCostSheet                  = By.xpath("//td[@id='hBCurrency']");
	public static By TotalMasterCartonPackagingCostInCostSheet = By.xpath("//td[@id='hbMasterCartonPackagingMaterial']");
	public static By TotalMasterCartonLaborCostInCostSheet     = By.xpath("//td[@id='hbMasterCartonPackagingLaborCost']");
	public static By TotalMiscellaneousCostInCostSheet         = By.xpath("//td[@id='hbMiscellaneousMaterial']");
	public static By TotalProductMarkUpInCostSheet             = By.xpath("//td[@id='hbProductMarkup']");
	public static By MaterialTabThroughSideBar                 = By.linkText("Materials");
	public static By CostingTabThroughSideBar                 = By.linkText("Costing");
	public static By SpecificationsTabThroughSideBar           = By.linkText("Specifications");
	public static By BOMid                                     = By.xpath("//select[@id='bomId']");
    public static By BOMUpdateButton                           = By.xpath("//a[text()='Update']");
    public static By HeaderAttributes                          = By.xpath("//a[text()='Header Attributes']");
    public static By BOMCurrencyDropDown                       = By.xpath("//td[contains(text(),'*Currency')]/following::select[1]");
    public static By HeaderAttributesPlusIcon                  = By.xpath("//a[text()='Header Attributes']//preceding::img[1]");
    public static By TotalMasterCartonPackagingCostInBOM       = By.xpath("//td[@id='hbTotalMasterCartonPkgCost']");
	public static By TotalMiscellaneousCostInBOM               = By.xpath("//td[@id='hbTotalMiscellaneousCost']");
	public static By TotalMasterCartonLaborCostInBOM           = By.xpath("//td[@id='hbTotalLaborCost']");
	public static By TotalMarkUpCostInBOM                      = By.xpath("//td[@id='hbTotalMarkUpCost']");
	public static By BOMIterationInCostSheet                   = By.xpath("//td[@id='hbBOMIteration']");
	public static By CurrencyConversionRateInCostSheet         = By.xpath("//td[@id='hbCurrencyConversionRate']");
	public static By CurrencyInBOM                             = By.xpath("//td[@id='hbBOMCurrency']");
	public static By SideBarCosting                            = By.xpath("//a[text()='Sourcing']//following::a[1][text()='Costing']");
	public static String totalMasterCartonPackagingCostInBOM;
	public static String totalMiscellaneousCostInBOM;
	public static String totalMasterCartonLaborCost;
	public static String totalMarkUpCost;
	public static String QuoteCurrencyInBOM;
	public static String CostSheetQuoteCurrency;
	public static By RunButton                                 = By.xpath("//a[text()='Run']");
	
	
    
    
    //Master carton  text Box In BOM
	
	public static By MasterCarton_MOQPer_K                                   = By.xpath("//td[@id='r1_hbMOQPerK']");
	public static By MasterCarton_MOQPer_K_InnerTextBox                      = By.xpath("//td[@id='r1_hbMOQPerK']/div/input[1]");
	public static By MasterCarton_LeadTime                                   = By.xpath("//td[@id='r1_hbLeadTimeDays']");
	public static By MasterCarton_LeadTme_InnerTextBox                       = By.xpath("//td[@id='r1_hbLeadTimeDays']/div/input[1]");
	public static By MasterCarton_UnitPrice                                  = By.xpath("//td[@id='r1_hbUnitPrice']");
	public static By MasterCarton_UnitPrice_InnerTextBox                     = By.xpath("//td[@id='r1_hbUnitPrice']/div/input[1]");
	public static By MasterCarton_UsagePerK                                  = By.xpath("//td[@id='r1_hbUsagePerK']");
	public static By MasterCarton_UsagePerK_InnerTextBox                     = By.xpath("//td[@id='r1_hbUsagePerK']/div/input[1]");
	public static By MasterCarton_MarkUp                                     = By.xpath("//td[@id='r1_hbMarkup']");
	public static By Mastercarton_MarkUP_InnerTextBox                        = By.xpath("//td[@id='r1_hbMarkup']/div/input[1]");
	public static By MasterCarton_Unit                                       = By.xpath("//td[@id='r1_hbUnit']");
	public static By MasterCarton_Unit_InnerDropDown                         = By.xpath("//td[@id='r1_hbUnit']/div/select[1]");
	
	
	//labor  text Box In BOM
	
	public static By Labor_MOQPer_K                                          = By.xpath("//td[@id='r2_hbMOQPerK']");
	public static By Labor_MOQPer_K_InnerTextBox                             = By.xpath("//td[@id='r2_hbMOQPerK']/div/input[1]");
	public static By Labor_LeadTime                                          = By.xpath("//td[@id='r2_hbLeadTimeDays']");
	public static By Labor_LeadTme_InnerTextBox                              = By.xpath("//td[@id='r2_hbLeadTimeDays']/div/input[1]");
	public static By Labor_UnitPrice                                         = By.xpath("//td[@id='r2_hbUnitPrice']");
	public static By Labor_UnitPrice_InnerTextBox                            = By.xpath("//td[@id='r2_hbUnitPrice']/div/input[1]");
	public static By Labor_UsagePerK                                         = By.xpath("//td[@id='r2_hbUsagePerK']");
	public static By Labor_UsagePerK_InnerTextBox                            = By.xpath("//td[@id='r2_hbUsagePerK']/div/input[1]");
	public static By Labor_MarkUp                                            = By.xpath("//td[@id='r2_hbMarkup']");
	public static By Labor_MarkUP_InnerTextBox                               = By.xpath("//td[@id='r2_hbMarkup']/div/input[1]");
	public static By Labor_Unit                                              = By.xpath("//td[@id='r2_hbUnit']");
	public static By Labor_Unit_InnerDropDown                                = By.xpath("//td[@id='r2_hbUnit']/div/select[1]");
	
	
	//Miscellaneous  text Box In BOM
	
	public static By Miscellaneous_MOQPer_K                                   = By.xpath("//td[@id='r3_hbMOQPerK']");
	public static By Miscellaneous_MOQPer_K_InnerTextBox                      = By.xpath("//td[@id='r3_hbMOQPerK']/div/input[1]");
	public static By Miscellaneous_LeadTime                                   = By.xpath("//td[@id='r3_hbLeadTimeDays']");
	public static By Miscellaneous_LeadTme_InnerTextBox                       = By.xpath("//td[@id='r3_hbLeadTimeDays']/div/input[1]");
	public static By Miscellaneous_UnitPrice                                  = By.xpath("//td[@id='r3_hbUnitPrice']");
	public static By Miscellaneous_UnitPrice_InnerTextBox                     = By.xpath("//td[@id='r3_hbUnitPrice']/div/input[1]");
	public static By Miscellaneous_UsagePerK                                  = By.xpath("//td[@id='r3_hbUsagePerK']");
	public static By Miscellaneous_UsagePerK_InnerTextBox                     = By.xpath("//td[@id='r3_hbUsagePerK']/div/input[1]");
	public static By Miscellaneous_MarkUp                                     = By.xpath("//td[@id='r3_hbMarkup']");
	public static By Miscellaneous_MarkUP_InnerTextBox                        = By.xpath("//td[@id='r3_hbMarkup']/div/input[1]");
	public static By Miscellaneous_Unit                                       = By.xpath("//td[@id='r3_hbUnit']");
	public static By Miscellaneous_Unit_InnerDropDown                         = By.xpath("//td[@id='r3_hbUnit']/div/select[1]");
	
	//Fourth Row Values
	public static By FourthRow_MOQPer_K                                       = By.xpath("//td[@id='r4_hbMOQPerK']");
	public static By FourthRow_MOQPer_K_InnerTextBox                          = By.xpath("//td[@id='r4_hbMOQPerK']/div/input[1]");
	public static By FourthRow_LeadTime                                       = By.xpath("//td[@id='r4_hbLeadTimeDays']");
	public static By FourthRow_LeadTme_InnerTextBox                           = By.xpath("//td[@id='r4_hbLeadTimeDays']/div/input[1]");
	public static By FourthRow_UnitPrice                                      = By.xpath("//td[@id='r4_hbUnitPrice']");
	public static By FourthRow_UnitPrice_InnerTextBox                         = By.xpath("//td[@id='r4_hbUnitPrice']/div/input[1]");
	public static By FourthRow_UsagePerK                                      = By.xpath("//td[@id='r4_hbUsagePerK']");
	public static By FourthRow_UsagePerK_InnerTextBox                         = By.xpath("//td[@id='r4_hbUsagePerK']/div/input[1]");
	public static By FourthRow_MarkUp                                         = By.xpath("//td[@id='r4_hbMarkup']");
	public static By FourthRow_MarkUP_InnerTextBox                            = By.xpath("//td[@id='r4_hbMarkup']/div/input[1]");
	public static By FourthRow_Unit                                           = By.xpath("//td[@id='r4_hbUnit']");
	public static By FourthRow_Unit_InnerDropDown                             = By.xpath("//td[@id='r4_hbUnit']/div/select[1]");
	
	//Fifth Row Values
    public static By FifthRow_MOQPer_K                                        = By.xpath("//td[@id='r5_hbMOQPerK']");
	public static By FifthRow_MOQPer_K_InnerTextBox                           = By.xpath("//td[@id='r5_hbMOQPerK']/div/input[1]");
	public static By FifthRow_LeadTime                                        = By.xpath("//td[@id='r5_hbLeadTimeDays']");
	public static By FifthRow_LeadTme_InnerTextBox                            = By.xpath("//td[@id='r5_hbLeadTimeDays']/div/input[1]");
	public static By FifthRow_UnitPrice                                       = By.xpath("//td[@id='r5_hbUnitPrice']");
	public static By FifthRow_UnitPrice_InnerTextBox                          = By.xpath("//td[@id='r5_hbUnitPrice']/div/input[1]");
	public static By FifthRow_UsagePerK                                       = By.xpath("//td[@id='r5_hbUsagePerK']");
	public static By FifthRow_UsagePerK_InnerTextBox                          = By.xpath("//td[@id='r5_hbUsagePerK']/div/input[1]");
	public static By FifthRow_MarkUp                                          = By.xpath("//td[@id='r5_hbMarkup']");
	public static By FifthRow_MarkUP_InnerTextBox                             = By.xpath("//td[@id='r5_hbMarkup']/div/input[1]");
	public static By FifthRow_Unit                                            = By.xpath("//td[@id='r5_hbUnit']");
	public static By FifthRow_Unit_InnerDropDown                              = By.xpath("//td[@id='r5_hbUnit']/div/select[1]");
	
	//Sixth Row Values
    public static By SixthRow_MOQPer_K                                        = By.xpath("//td[@id='r6_hbMOQPerK']");
	public static By SixthRow_MOQPer_K_InnerTextBox                           = By.xpath("//td[@id='r6_hbMOQPerK']/div/input[1]");
	public static By SixthRow_LeadTime                                        = By.xpath("//td[@id='r6_hbLeadTimeDays']");
	public static By SixthRow_LeadTme_InnerTextBox                            = By.xpath("//td[@id='r6_hbLeadTimeDays']/div/input[1]");
	public static By SixthRow_UnitPrice                                       = By.xpath("//td[@id='r6_hbUnitPrice']");
	public static By SixthRow_UnitPrice_InnerTextBox                          = By.xpath("//td[@id='r6_hbUnitPrice']/div/input[1]");
	public static By SixthRow_UsagePerK                                       = By.xpath("//td[@id='r6_hbUsagePerK']");
	public static By SixthRow_UsagePerK_InnerTextBox                          = By.xpath("//td[@id='r6_hbUsagePerK']/div/input[1]");
	public static By SixthRow_MarkUp                                          = By.xpath("//td[@id='r6_hbMarkup']");
	public static By SixthRow_MarkUP_InnerTextBox                             = By.xpath("//td[@id='r6_hbMarkup']/div/input[1]");
	public static By SixthRow_Unit                                            = By.xpath("//td[@id='r6_hbUnit']");
	public static By SixthRow_Unit_InnerDropDown                              = By.xpath("//td[@id='r6_hbUnit']/div/select[1]");
	
	
	//Seventh Row Values
    public static By SeventhRow_MOQPer_K                                       = By.xpath("//td[@id='r7_hbMOQPerK']");
	public static By SeventhRow_MOQPer_K_InnerTextBox                          = By.xpath("//td[@id='r7_hbMOQPerK']/div/input[1]");
	public static By SeventhRow_LeadTime                                       = By.xpath("//td[@id='r7_hbLeadTimeDays']");
	public static By SeventhRow_LeadTme_InnerTextBox                           = By.xpath("//td[@id='r7_hbLeadTimeDays']/div/input[1]");
	public static By SeventhRow_UnitPrice                                      = By.xpath("//td[@id='r7_hbUnitPrice']");
	public static By SeventhRow_UnitPrice_InnerTextBox                         = By.xpath("//td[@id='r7_hbUnitPrice']/div/input[1]");
	public static By SeventhRow_UsagePerK                                      = By.xpath("//td[@id='r7_hbUsagePerK']");
	public static By SeventhRow_UsagePerK_InnerTextBox                         = By.xpath("//td[@id='r7_hbUsagePerK']/div/input[1]");
	public static By SeventhRow_MarkUp                                         = By.xpath("//td[@id='r7_hbMarkup']");
	public static By SeventhRow_MarkUP_InnerTextBox                            = By.xpath("//td[@id='r7_hbMarkup']/div/input[1]");
	public static By SeventhRow_Unit                                           = By.xpath("//td[@id='r7_hbUnit']");
	public static By SeventhRow_Unit_InnerDropDown                             = By.xpath("//td[@id='r7_hbUnit']/div/select[1]");
	
	//Eighth Row Values
    public static By EighthRow_MOQPer_K                                        = By.xpath("//td[@id='r8_hbMOQPerK']");
	public static By EighthRow_MOQPer_K_InnerTextBox                           = By.xpath("//td[@id='r8_hbMOQPerK']/div/input[1]");
	public static By EighthRow_LeadTime                                        = By.xpath("//td[@id='r8_hbLeadTimeDays']");
	public static By EighthRow_LeadTme_InnerTextBox                            = By.xpath("//td[@id='r8_hbLeadTimeDays']/div/input[1]");
	public static By EighthRow_UnitPrice                                       = By.xpath("//td[@id='r8_hbUnitPrice']");
	public static By EighthRow_UnitPrice_InnerTextBox                          = By.xpath("//td[@id='r8_hbUnitPrice']/div/input[1]");
	public static By EighthRow_UsagePerK                                       = By.xpath("//td[@id='r8_hbUsagePerK']");
	public static By EighthRow_UsagePerK_InnerTextBox                          = By.xpath("//td[@id='r8_hbUsagePerK']/div/input[1]");
	public static By EighthRow_MarkUp                                          = By.xpath("//td[@id='r8_hbMarkup']");
	public static By EighthRow_MarkUP_InnerTextBox                             = By.xpath("//td[@id='r8_hbMarkup']/div/input[1]");
	public static By EighthRow_Unit                                            = By.xpath("//td[@id='r8_hbUnit']");
	public static By EighthRow_Unit_InnerDropDown                              = By.xpath("//td[@id='r8_hbUnit']/div/select[1]");
	public static By VendorActionDropDown                                      = By.xpath("//select[@id='sourcingActions']");
	
	//PC38
	public static By CostSheetName                                            = By.xpath("//td[contains(text(),'Cost Sheet Identification')]//following::td[3]");
	public static String strCostSheetNameA;
	public static String strCostSheetNameB;
	public static String strCostSheetNameC;
	public static String strCostSheetNameD;
	public static String strCostSheetNameE;
	public static String strCostSheetNameF;
	public static String strCostSheetNameG;
	public static String strCostSheetNameH;
	public static String strCostSheetNameI;
	public static String strCostSheetNameJ;
	public static String CostSheetNameA;
	public static String CostSheetNameB;
	public static String CostSheetNameC;
	public static String CostSheetNameD;
	public static By CostSheetIdentification1_Internal                       = By.xpath("//a[contains(text(),'Sourcing Configuration')]//following::a[10]");
	public static By CostSheetIdentification2_Internal                       = By.xpath("//a[contains(text(),'Sourcing Configuration')]//following::a[24]");
	public static By CostSheetIdentification3_Internal                       = By.xpath("//a[contains(text(),'Sourcing Configuration')]//following::a[38]");
	public static By CostSheetIdentification4_Internal                       = By.xpath("//a[contains(text(),'Sourcing Configuration')]//following::a[52]");
	public static By CostSheetUpdateButton                                   = By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/select");
	public static By ChoosedColorwayOption                                   = By.xpath("//select[@id='colorwayGroupChosen']/option[1]");
	public static By PrimaryCostSheetCheckBox                                = By.xpath("//td[contains(text(),'Primary Cost Sheet')]//following::input[2]");
	public static By UpdateCostSheetSaveButton                               = By.xpath("//td[contains(text(),'Update Product Cost Sheet')]//following::a[1]");
	public static String pageTitle;
	public static By csUpdateAction                                          = By.xpath("//a[contains(text(),'Cost Sheet List')]//following::select[1]");
	public static By ErrorMessageForPrimaryCostSheetUpdation                 = By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/table/tbody/tr/td");
	
	
	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSCFunctional(String[] data) throws Exception{
		count++;
		System.out.println(runmodes[count]);
		if(!runmodes[count].equalsIgnoreCase("y")){
			skip=true;
			log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
			throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
		}
		try{
			log.debug("Inside testcase for Sanity suit");
			// User Name, Password and Action
			log.info("col0 :" + data[0]); 
			log.info("col1 :" + data[1]);
			log.info("Testcase is :" + data[2]);
			log.info("Testcase no is :" + data[3]);
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
			switch (data[3]) {
			/******************/
			case "Create Product":
				log.info("In side :-> " + data[3]);	
				CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[5],data[9],data[8],data[10],
						data[11],data[12],data[13],data[14],data[16],data[6],data[7],
						data[17],data[18],data[19], data[20], data[21],
						"CHANNEL","BOYS","1D","1 MONTH","FEMALE","Yes","ACTION FIGURES & ACCESSORIES",
						"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
				log.info("Out side :-> " + data[3]);
				break;
			case "Create Multiple Colorway":
				log.info("In side :-> " + data[3]);	
				createMultipleCW(data);
				log.info("Out side :-> " + data[3]);
				break;	
			case "Create Colorway":
				log.info("In side :-> " + data[3]);	
				createCW(data);
				log.info("Out side :-> " + data[3]);
				break;	
			/*case "Create Development Plan":
				log.info("In side :-> " + data[3]);	
				createDevelopmentPlan(data);
				log.info("Out side :-> " + data[3]);
				break;*/
			case "TC01_PC48_AssociateBOMToInternalRetailItemCS":
				log.info("In side :-> " + data[3]);	
				TC01_PC48_AssociateBOMToInternalRetailItemCS(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "TC02_PC49_AssociateBOMToVendorProductCS":
				log.info("In side :-> " + data[3]);	
				TC02_pc49associateBOMToVendorPrdCS(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "TC03_PC50_AssociateBOMToVendorRetailItemCS":
				log.info("In side :-> " + data[3]);	
				TC03_pc50associateBOMToVendorRetailItemCS(data);
				log.info("Out side :-> " + data[3]);
				break;
				
			case "TC04_PC54_RemoveBOMFromVendorRetailItemCS":
				log.info("In side :-> " + data[3]);	
				TC04_pc54RemoveBOMFromVendorRetailItemCS(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "TC05_PC22_CreateVendorProductBOM":
				log.info("In side :-> " + data[3]);	
				TC05_pc22CreateVendorProductBOM(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "TC06_PC56_ValidateBOMChangesOnInternalRetailItemCostSheet":
				log.info("In side :-> " + data[3]);	
				TC06_pc56CreateValidateBOMOnIntRetCS(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "TC07_PC05_CreateSourcingConfigCarryForward":
				log.info("In side :-> " + data[3]);	
				TC07_PC05CreateSourcingConfigCarryForward(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "TC08_PC06_CreateSourcingConfigFromAddExisting":
				log.info("In side :-> " + data[3]);	
				TC08_PC06_CreateSourcingConfigFromAddExisting(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "TC09_PC07_CreateSourcingConfigUsingCopyOrLink":
				log.info("In side :-> " + data[3]);	
				TC09_PC07_CreateSourcingConfigUsingCopyOrLink(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "TC10_PC04_CreateMultipleSourcingConfigs":
				log.info("In side :-> " + data[3]);	
				TC10_PC04_CreateMultipleSourcingConfigs(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PC57_ValidateBOMChangesOnVendorProductCostSheet":
				log.info("In side :-> " + data[3]);	
				PC57_ValidateBOMChangesOnVendorProductCostSheet(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PC58_ValidateBOMChangesOnVendorRetailItemCostSheet":
				log.info("In side :-> " + data[3]);	
				PC58_ValidateBOMChangesOnVendorRetailItemCostSheet(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "TC11_PC11_CreateMaterialLabor":
				log.info("In side :-> " + data[3]);	
				TC11_PC11_CreateMaterialLabor(data);
				log.info("Out side :-> " + data[3]);
				break;
				
			case "TC13_PC80_ViewCostSheetIterationHistory":
				log.info("In side :-> " + data[3]);	
				TC13_PC80_ViewCostSheetIterationHistory(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "TC12_PC08_CreateMultipleSourcingConfigsRetailItem":
				log.info("In side :-> " + data[3]);	
				TC12_PC08_CreateMultipleSourcingConfigsRetailItem(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "SC1_CreateAndApproveSCForAssortment":
				log.info("In side :-> " + data[3]);	
				SC1_CreateAndApproveSCForAssortment(data);
				log.info("Out side :-> " + data[3]);
				break;
				
			case "SC2_UpdateSCForAssortment":
				log.info("In side :-> " + data[3]);	
				SC2_UpdateSCForAssortment(data);
				log.info("Out side :-> " + data[3]);
				break;
				
			case "SC3_CreateAndApproveSCForRetail":
				log.info("In side :-> " + data[3]);	
				SC3_CreateAndApproveSCForRetail(data);
				log.info("Out side :-> " + data[3]);
				break;
				
			case "TC18_PD23_BundlePack":
				log.info("In side :-> " + data[3]);	
				TC18_PD23_BundlePack(data);
				log.info("Out side :-> " + data[3]);
				break;
				
			case "TC19_PC55_ValidateBOMChangesOnInternalProductCostSheet":
				log.info("In side :-> " + data[3]);	
				TC19_PC55_ValidateBOMChangesOnInternalProductCostSheet(data);
				log.info("Out side :-> " + data[3]);
				break;
				
			case "TC20_PC38_UpdatePrimaryFlagForVendorRetailItemCS":
				log.info("In side :-> " + data[3]);	
				TC20_PC38_UpdatePrimaryFlagForVendorRetailItemCS(data);
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
	
	
	
	/*public static String createDevelopmentPlan(String[] data) throws Exception{
		try{
			log.info(" Create Development Plan verification started....");
			//Click My Season Link
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[5]);
			//Click on Planning
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.planningLink, "link", "Planning");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on create new plan
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.createNewPlan, "btn", "Create New Plan");
			//Click Global Line Plan :
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.globalLinePlan, "btn", "Global Line Plan");
			//wait for heading 
			CommonFunctions.waitForVisibilityOfElement(GlobalLinePlan.headingCreateNewPlan);
			//Click Create New Plan
			SmokeFlow.glPlanName = createNewPlan(data);
			//glPlanName="AutoDevPlan"+glPlanName;
			SmokeFlow.assortmentSolidName= fillGlobalLinePlan(data);
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg2);
			//Expend
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg2, "Expand class");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg3);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg3, "Expand division");
			//
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg4);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg4, "Expand Brand");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg6);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg6, "Expand MainLine");
			//CommonFunctions.waitForElementTobeClickable(expImg6);
			//CommonFunctions.clickButtonOrLink(expImg4, "Expand MainLine");
			//Class
			SmokeFlow.strClass=driver.findElement(SmokeFlow.objClass).getText();
			Assert.assertEquals(SmokeFlow.strClass.trim(), data[7]);
			//Division
			SmokeFlow.strDivision=driver.findElement(SmokeFlow.objDivision).getText();
			Assert.assertEquals(SmokeFlow.strDivision.trim(), data[8]);//
			//Brand
			SmokeFlow.strBrand=driver.findElement(SmokeFlow.objBrand).getText();
			Assert.assertEquals(SmokeFlow.strBrand.trim(), data[5]);
			//Verification of totalRetailItemCount
			SmokeFlow.strTotalRetailItemCount=driver.findElement(SmokeFlow.totalRetailItemCount).getText();
			String val =data[35].replaceAll("\\.0*$", "");
			Assert.assertEquals(SmokeFlow.strTotalRetailItemCount, val);
			log.info("Plan name is: "+ SmokeFlow.glPlanName);
			log.info("Create Development Plan verification completed");
		}catch(Exception e){
			fail=true;
			log.error("Exception in createDevelopmentPlan()", e);
			throw e;
		}
		return SmokeFlow.glPlanName;
	}
	public static String createNewPlan(String[] data) {
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			SmokeFlow.glPlanName = CommonFunctions.getRandomString(5);
			SmokeFlow.glPlanName ="AutoDevP"+SmokeFlow.glPlanName;
			//	glPlanName = data[6]+date.getTime();
			//	glPlanName=glPlanName.substring(0,5);
			//Enter Name
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.planName,SmokeFlow.glPlanName);
			//Enter Brand
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.Brand, data[12]);
			//Enter Season Year
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.seasonYear, data[5]);
			//Click on create button
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
		return SmokeFlow.glPlanName;
	}
	
	public static String fillGlobalLinePlan(String[] data) throws Exception {
		try{
			//Add Assortment/Solid name
			SmokeFlow.assSoName= data[4]+CommonFunctions.getRandomString(3);
			CommonFunctions.enterTextInTextbox(SmokeFlow.assSolidName, SmokeFlow.assSoName);
			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink1, "Image", "Plus Sign");
			//Select Class
			//CommonFunctions.selectFromDropDownByValue(hbclass, data[5]);
			new Select(SeleniumDriver.driver.findElement(GlobalLinePlan.hbclass)).selectByVisibleText(data[10]);
			SeleniumDriver.log.info("Selected" + " " + data[7] + " " + "option from drop down.");
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink2, "Image", "Plus Sign");
			//Select DIVISION
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbDivision, data[11]);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink3, "Image", "Plus Sign");
			//Select Brand
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbBrand, data[12]);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			//	CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink5, "Image", "Plus Sign");
			//Enter Assortment Number
			//	CommonFunctions.enterTextInTextbox(GlobalLinePlan.hbAssortmentSolidNumber, data[10]);
			//Click on Add
			//	CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink6, "Image", "Plus Sign");
			//Enter Assortment Number
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.hbAssortmentSolidNumber, SmokeFlow.assSoName);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbAssortmentSolidName, "cell", "Assortment / Solid Name");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPutAssortmentSolidName, SmokeFlow.assSoName);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbDistributionChannel, "cell", "Distribution Channel");
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbDistributionChannelForUpdate, "Mainline");



			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbLicensor, "cell", "Licensor");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbLicensor, data[13]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbCoBrand, "cell", "Co-Brand");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbCoBrand, data[14]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.r6_hbISO, "cell", "ISO");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbISO, data[8]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbMovie, "cell", "Movie");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbMovie, data[9]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbIntroTiming, "cell", "Intro Timing");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbIntroTiming, data[16]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbOnShelfDate, "cell", "On-Shelf Date");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbOnShelfDate, data[17]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbTVAd, "cell", "TV Ad");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbTVAd, data[18]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbDigitalProduct, "cell", "Digital Product");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbDigitalProduct, data[19]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbProjectType, "cell", "Project Type");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbProjectType, data[20]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbInnovationType, "cell", "Innovation Type");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbInnovationType, data[21]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbPTMH, "cell", "PT / MH");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbPTMH, data[22]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbAstSolid, "cell", "Ast. / Solid");
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.selecthbAstSolid, data[23]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbRetailerDistribution, "cell", "Retailer Distribution");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbRetailerDistribution, data[24]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbCFItemCount, "cell", "CF Total");
			System.out.println(data[25]);
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbCFItemCount, data[25]); //

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbNewItemCount, "cell", "New Total");
			System.out.println(data[26]);
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbNewItemCount, data[26]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbProductRefreshItemCount, "cell", "Product Refresh Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbProductRefreshItemCount, data[27]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbPackageRefreshItemCount, "cell", "Package Refresh Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbPackageRefreshItemCount, data[28]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbSoldasSolid, "cell", "Sold as Solid Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbSoldasSolid, data[29]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbSRPPriceUSD, "cell", "SRP (USD)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbSRPPriceUSD, data[30]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbLCPriceUSD, "cell", " L/C Price (USD)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbLCPriceUSD, data[31]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbDOMPriceUSD, "cell", "DOM Price (USD)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbDOMPriceUSD, data[32]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbDomesticPercentage, "cell", "Domestic (%)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbDomesticPercentage, data[33]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbComments, "cell", "Comments");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.textareahbComments, data[34]);

			//Click on Save
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
			CommonFunctions.waitForElementTobeClickable(GlobalLinePlan.btnDone);
			Thread.sleep(2000);
			//Click on Done
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnDone,"btn", "Done");


		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
			throw e;
		}
		return SmokeFlow.assSoName;
	}
*/
	public static String createMultipleCW(String[] data) throws Exception {
		try{
			//Create Product
			Prodname=CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[5],data[9],data[8],data[10],
					data[11],data[12],data[13],data[14],data[16],data[6],data[7],
					data[17],data[18],data[19], data[20], data[21],
					"CHANNEL","BOYS","1D","1 MONTH","FEMALE","Yes","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
			log.info("Assortment Product: "+Prodname);
			//Create multiple colorway
			CommonProjectFunctions.CreateMultiple_Colorway();
		}catch(Exception e){
			fail=true;
			log.error("Exception in createMultipleCW()", e);
			throw e;
		}
		return Prodname;
	}

	public static String createCW(String[] data) throws Exception{
		try{
			//Create Product
			/*Prodname=CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[5],data[9],data[8],data[10],
					data[11],data[12],data[13],data[14],data[16],data[6],data[7],
					data[17],data[18],data[19], data[20], data[21],
					"CHANNEL","BOYS","1D","1 MONTH","FEMALE","Yes","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");*/
		//	log.info("Assortment Product: "+Prodname);
			//Create colorway
			CommonProjectFunctions.Create_Colorway(Prodname,data[5],data[4],data[23],data[24]);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createCW()", e);
			throw e;
		}
		return Prodname;
	}
	
	public static boolean enterTextInTextbox(By by, String inputValue) throws Exception{
		//This function is to enter the the value in waveRequirement table 5 Since the value are passing with decimal value .to avoid that this function made. 
		boolean result=false;
		Thread.sleep(100);
	
			if(CommonFunctions.isElementPresentWithoutLog(by)){
				SeleniumDriver.driver.findElement(by).clear();
				SeleniumDriver.driver.findElement(by).sendKeys((inputValue.substring(0,2)));
				SeleniumDriver.log.info("Entered" + " " + inputValue + " " + "in the text field.");
				result=true;
			}
			return result;
			}

	//Create Internal Retail Item BOM
	public static String[] pc52createRetailIntBOM(String[] data) throws Exception{
		try{
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[38]);
			//CommonFunctions.waitForPageLoaded();
			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			CommonFunctions.waitForPageLoaded();
			BOMnameRetail="InWork"+CommonFunctions.getRandomString(4);

			//Create BOM page
			BOMnameInWorkRetail1 = fillCreateBOM(data);
			CommonFunctions.waitForPageLoaded();
			Thread.sleep(1000);
			
			if(data[38].contains("BOM\\Materials\\Product\\Retail Item\\Internal"))
				//if(data[38].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[38].contains("BOM\\Materials\\Product\\Product\\Internal")))
			{ 
				//Plastics-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewPlastics);
				   CommonFunctions.clickButtonOrLink(imgViewPlastics, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View");
			   //Enter unit Price-Plastics
			   wait.until(ExpectedConditions.visibilityOfElementLocated(cellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(cellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(inputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(inputUnitPriceIE, data[40],"Unit Price Value for Plastics");
			   //Enter usage per K-Plastics
			   CommonFunctions.waitForElementTobeClickable(CI253.cellUsagePerK);
			   CommonFunctions.clickButtonOrLink(CI253.cellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(CI253.inputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(CI253.inputUsagePerK, data[41],"Usage Per K Value for plastics");
			   //Enter Product Markup-Plastics
			   CommonFunctions.waitForElementTobeClickable(plasticCellMarkup);
			   CommonFunctions.clickButtonOrLink(plasticCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(plasticInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(plasticInputMarkup, data[79],"Markup Value for plastics");
			
			   
			   
				 /*//Chemical-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewChemical);
				   CommonFunctions.clickButtonOrLink(imgViewChemical, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); */
			   //Enter unit Price-Chemicals
			   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(chemicalCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(chemicalInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUnitPriceIE, data[42],"Unit Price Value for Chemicals");
			   CommonFunctions.waitForPageLoaded();
			   //Enter usage per K-Chemicals
			   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(chemicalCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(chemicalInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUsagePerK, data[43],"Usage Per K Value for Chemicals");
			   //Enter Product Markup-Chemicals
			   CommonFunctions.waitForElementTobeClickable(chemicalCellMarkup);
			   CommonFunctions.clickButtonOrLink(chemicalCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(chemicalInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(chemicalInputMarkup, data[80],"Markup Value for Chemicals");

			   
			   
				 //Purchased-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewPurchased);
				   CommonFunctions.clickButtonOrLink(imgViewPlastics, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
			   //Enter unit Price-Purchased Parts
			   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(purchasedCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(purchasedInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUnitPriceIE, data[44],"Unit Price Value for Purchased Parts");
			   //Enter usage per K-Purchased Parts
			   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(purchasedCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(purchasedInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUsagePerK, data[45],"Usage Per K Value for Purchased Parts");
			   //Enter Product Markup-Purchased Parts
			   CommonFunctions.waitForElementTobeClickable(purchasedCellMarkup);
			   CommonFunctions.clickButtonOrLink(purchasedCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(purchasedInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(purchasedInputMarkup, data[81],"Markup Value for Purchased");

			   
			   
				 //Electronics-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewElectronics);
				   CommonFunctions.clickButtonOrLink(imgViewElectronics, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
			   //Enter unit Price-Electronics
			   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(electCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(electInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(electInputUnitPriceIE, data[46],"Unit Price Value for Electronics");
			   //Enter usage per K-Electronics
			   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(electCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(electInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(electInputUsagePerK, data[47],"Usage Per K Value for Electronics");
			   //Enter Product Markup-Electronics
			   CommonFunctions.waitForElementTobeClickable(electCellMarkup);
			   CommonFunctions.clickButtonOrLink(electCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(electInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(electInputMarkup, data[82],"Markup Value for Electronics");

			   
			   
			  //Soft Goods-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewSG);
				   CommonFunctions.clickButtonOrLink(imgViewSG, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
			   //Enter unit Soft Goods
			   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(sGCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(sGInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(sGInputUnitPriceIE, data[48],"Unit Price Value for Soft Goods");
			   //Enter usage per K-Soft Goods
			   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(sGCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(sGInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(sGInputUsagePerK, data[49],"Usage Per K Value for Soft Goods");
			   //Enter Product Markup-Soft Goods
			   CommonFunctions.waitForElementTobeClickable(sGCellMarkup);
			   CommonFunctions.clickButtonOrLink(sGCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(sGInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(sGInputMarkup, data[83],"Markup Value for Soft Goods");
               
			   
			   
			 //Pacakaging-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewPackaging);
				   CommonFunctions.clickButtonOrLink(imgViewPackaging, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
			   //Enter unit Pacakaging
			   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(packagingCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(packagingInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(packagingInputUnitPriceIE, data[50],"Unit Price Value for Pacakaging");
			   //Enter usage per Packaging
			   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(packagingCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(packagingInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(packagingInputUsagePerK, data[51],"Usage Per K Value for Pacakaging");
			   //Enter Product Markup-Soft Goods
			   CommonFunctions.waitForElementTobeClickable(packagingCellMarkup);
			   CommonFunctions.clickButtonOrLink(packagingCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(packagingInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(packagingInputMarkup, data[84],"Markup Value for Pacakaging");
               
			   
			   
				 //General Decor Labour-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewGenDecLabor);
				   CommonFunctions.clickButtonOrLink(imgViewGenDecLabor, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
			   //Enter unit General Decor Labour
			   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(generalLabourCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(generalLabourInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUnitPriceIE, data[52],"Unit Price Value for General/Deco Labor");
			   //Enter usage per General Decor Labour
			   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(generalLabourCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(generalLabourInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUsagePerK, data[53],"Usage Per K Value for General/Deco Labor");
			   //Enter Product Markup-General Decor Labour
			   CommonFunctions.waitForElementTobeClickable(generalCellMarkup);
			   CommonFunctions.clickButtonOrLink(generalCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(generalInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(generalInputMarkup, data[85],"Markup Value for General/Deco Labor");

			   
			   
			  //Molding Labour-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewMoldingLabor);
				   CommonFunctions.clickButtonOrLink(imgViewMoldingLabor, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
			   //Enter unit Molding Labour
			   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(moldingCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(moldingInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputUnitPriceIE, data[54],"Unit Price Value for Molding Labor");
			   //Enter usage per Molding Labour
			   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(moldingCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(moldingInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputUsagePerK, data[55],"Usage Per K Value for Molding Labor");
			   //Enter Product Markup-Molding Labour
			   CommonFunctions.waitForElementTobeClickable(moldingCellMarkup);
			   CommonFunctions.clickButtonOrLink(moldingCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(moldingInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputMarkup, data[86],"Markup Value for Molding");
			}
			
			else if((data[38].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")))
			{
				//CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);
				   
				  //Enter unit Price-Plastics
				
				   /*//Plastics-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewPlastics);
				   CommonFunctions.clickButtonOrLink(imgViewPlastics, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View");*/
				   
				   wait.until(ExpectedConditions.visibilityOfElementLocated(CI253.cellUnitPrice));
				   CommonFunctions.clickButtonOrLink(CI253.cellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(CI253.inputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(CI253.inputUnitPrice, data[40],"Unit Price Value for Plastics");
				
				   //Enter usage per K-Plastics
				   CommonFunctions.waitForElementTobeClickable(CI253.cellUsagePerK);
				   CommonFunctions.clickButtonOrLink(CI253.cellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(CI253.inputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(CI253.inputUsagePerK, data[41],"Usage Per K Value for plastics");
				   
				   //Enter Product Markup-Plastics
				   CommonFunctions.waitForElementTobeClickable(plasticCellMarkup);
				   CommonFunctions.clickButtonOrLink(plasticCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(plasticInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(plasticInputMarkup, data[88],"Markup Value for plastics");
				
				   /*//Chemicals-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewChemical);
				   CommonFunctions.clickButtonOrLink(imgViewChemical, "image", "View for Chemicals");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Chemicals Costing Full View");*/
				   
				   //Enter unit Price-Chemicals
				   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(chemicalCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(chemicalInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUnitPrice, data[42],"Unit Price Value for Chemicals");
				   CommonFunctions.waitForPageLoaded();
				   //Enter usage per K-Chemicals
				   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(chemicalCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(chemicalInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUsagePerK, data[43],"Usage Per K Value for Chemicals");
				   //Enter Product Markup-Chemicals
				   CommonFunctions.waitForElementTobeClickable(chemicalCellMarkup);
				   CommonFunctions.clickButtonOrLink(chemicalCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(chemicalInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(chemicalInputMarkup, data[89],"Markup Value for Chemicals");
				   
				   /*//Purchased Parts-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewPurchased);
				   CommonFunctions.clickButtonOrLink(imgViewPurchased, "image", "View for Purchased Parts");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Purchased Parts Costing Full View");*/

				   //Enter unit Price-Purchased Parts
				   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(purchasedCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(purchasedInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUnitPrice, data[44],"Unit Price Value for Purchased Parts");
				   //Enter usage per K-Purchased Parts
				   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(purchasedCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(purchasedInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUsagePerK, data[45],"Usage Per K Value for Purchased Parts");
				   //Enter Product Markup-Purchased Parts
				   CommonFunctions.waitForElementTobeClickable(purchasedCellMarkup);
				   CommonFunctions.clickButtonOrLink(purchasedCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(purchasedInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(purchasedInputMarkup, data[90],"Markup Value for Purchased");
				   
				   /*//Electronics-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewElectronics);
				   CommonFunctions.clickButtonOrLink(imgViewElectronics, "image", "View for Electronics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Electronics Costing Full View");*/

				   //Enter unit Price-Electronics
				   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(electCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(electInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(electInputUnitPrice, data[46],"Unit Price Value for Electronics");
				   //Enter usage per K-Electronics
				   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(electCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(electInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(electInputUsagePerK, data[47],"Usage Per K Value for Electronics");
				   //Enter Product Markup-Electronics
				   CommonFunctions.waitForElementTobeClickable(electCellMarkup);
				   CommonFunctions.clickButtonOrLink(electCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(electInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(electInputMarkup, data[91],"Markup Value for Electronics");
				   
				   /*//Soft Goods-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewSG);
				   CommonFunctions.clickButtonOrLink(imgViewSG, "image", "View for Electronics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Soft Goods Costing Full View");*/

				   //Enter unit Soft Goods
				   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(sGCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(sGInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(sGInputUnitPrice, data[48],"Unit Price Value for Soft Goods");
				   //Enter usage per K-Soft Goods
				   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(sGCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(sGInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(sGInputUsagePerK, data[49],"Usage Per K Value for Soft Goods");
				   //Enter Product Markup-Soft Goods
				   CommonFunctions.waitForElementTobeClickable(sGCellMarkup);
				   CommonFunctions.clickButtonOrLink(sGCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(sGInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(sGInputMarkup, data[92],"Markup Value for Soft Goods");
				   
				   
				   //Pacakaging-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewPackaging);
				   CommonFunctions.clickButtonOrLink(imgViewPackaging, "image", "View for Pacakaging");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Pacakaging Costing Full View");
				   //Enter unit Pacakaging
				   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(packagingCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(packagingInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(packagingInputUnitPrice, data[50],"Unit Price Value for Pacakaging");
				   //Enter usage per Packaging
				   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(packagingCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(packagingInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(packagingInputUsagePerK, data[51],"Usage Per K Value for Pacakaging");
				   //Enter Product Markup-Soft Goods
				   CommonFunctions.waitForElementTobeClickable(packagingCellMarkup);
				   CommonFunctions.clickButtonOrLink(packagingCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(packagingInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(packagingInputMarkup, data[93],"Markup Value for Pacakaging");

				   /*//Gen/Deco Labor-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewGenDecLabor);
				   CommonFunctions.clickButtonOrLink(imgViewGenDecLabor, "image", "View for Gen/Deco Labor");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Gen/Deco Labor Costing Full View");*/
				   //Enter unit General Decor Labour
				   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(generalLabourCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(generalLabourInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUnitPrice, data[52],"Unit Price Value for General/Deco Labor");
				   //Enter usage per General Decor Labour
				   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(generalLabourCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(generalLabourInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUsagePerK, data[53],"Usage Per K Value for General/Deco Labor");
				   //Enter Product Markup-General Decor Labour
				   CommonFunctions.waitForElementTobeClickable(generalCellMarkup);
				   CommonFunctions.clickButtonOrLink(generalCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(generalInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(generalInputMarkup, data[94],"Markup Value for General/Deco Labor");
                   
				   /*//Molding Labor-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewMoldingLabor);
				   CommonFunctions.clickButtonOrLink(imgViewMoldingLabor, "image", "View for Molding Labor");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Molding Labor Costing Full View");*/
				   //Enter unit Molding Labour
				   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(moldingCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(moldingInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(moldingInputUnitPrice, data[54],"Unit Price Value for Molding Labor");
				   //Enter usage per Molding Labour
				   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(moldingCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(moldingInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(moldingInputUsagePerK, data[55],"Usage Per K Value for Molding Labor");
				   //Enter Product Markup-Molding Labour
				   CommonFunctions.waitForElementTobeClickable(moldingCellMarkup);
				   CommonFunctions.clickButtonOrLink(moldingCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(moldingInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(moldingInputMarkup, data[95],"Markup Value for Molding");
			}
			
			//else if((data[38].contains("BOM\\Materials\\Product\\Product\\Vendor") || (data[48].contains("BOM\\Materials\\Product\\Product\\Internal")))
			//else if	((data[38].contains("BOM\\Materials\\Product\\Product\\Vendor") || (data[48].contains("BOM\\Materials\\Product\\Product\\Internal"))
			else if	((data[38].contains("BOM\\Materials\\Product\\Product\\Internal")))
			{
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);

				//Master Carton Packaging-Coasting(full) view
				CommonFunctions.waitForElementTobeClickable(imgViewMaster);
				CommonFunctions.clickButtonOrLink(imgViewMaster, "image", "View for Master Carton");
				CommonFunctions.waitForElementTobeClickable(BOMTablesViewI);
				CommonFunctions.clickButtonOrLink(BOMTablesViewI, "link", "Packaging Costing Full View");
				
				//Enter unit Price -Master Carton Packaging
				wait.until(ExpectedConditions.visibilityOfElementLocated(mCPackagingCellUnitPrice_IE));
				CommonFunctions.clickButtonOrLink(mCPackagingCellUnitPrice_IE, "table cell", "unitPrice");
				CommonFunctions.waitForElementTobeClickable(mCPackagingInputUnitPrice_IE);
				CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputUnitPrice_IE, data[39],"Unit Price Value for Plastics");

				//Enter Usage per K-Master Carton Packaging
				CommonFunctions.waitForElementTobeClickable(mCPackagingCellUsagePerK);
				CommonFunctions.clickButtonOrLink(mCPackagingCellUsagePerK, "table cell", "unitPerK");
				CommonFunctions.waitForElementTobeClickable(mCPackagingInputUsagePerK);
				CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputUsagePerK, data[52],"Usage Per K Value for plastics");

				//Enter Markup - Master Carton Packaging
				wait.until(ExpectedConditions.visibilityOfElementLocated(mCPackagingCellMarkUp));
				CommonFunctions.clickButtonOrLink(mCPackagingCellMarkUp, "table cell", "Product Markup for Master Carton table");
				CommonFunctions.waitForElementTobeClickable(mCPackagingInputMarkUp);
				CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputMarkUp, data[53],"Product Markup for Master Carton table");
				
				//Labour-Costing(full) view
				CommonFunctions.waitForElementTobeClickable(imgViewLabour);
				CommonFunctions.clickButtonOrLink(imgViewLabour, "image", "View for Labour");
				CommonFunctions.waitForElementTobeClickable(BOMTablesViewI);
				CommonFunctions.clickButtonOrLink(BOMTablesViewI, "link", " Labour Costing Full View");
				
				//Enter unit Price -Labor
				wait.until(ExpectedConditions.visibilityOfElementLocated(laborCellUnitPrice_IE));
				CommonFunctions.clickButtonOrLink(laborCellUnitPrice_IE, "table cell", "unitPrice");
				CommonFunctions.waitForElementTobeClickable(laborInputUnitPrice_IE);
				CommonFunctions.enterTextInTextboxUpdated(laborInputUnitPrice_IE, data[54],"Unit Price Value for Plastics");

				//Enter Usage per K-Labor
				CommonFunctions.waitForElementTobeClickable(laborCellUsagePerK);
				CommonFunctions.clickButtonOrLink(laborCellUsagePerK, "table cell", "unitPerK");
				CommonFunctions.waitForElementTobeClickable(laborInputUsagePerK);
				CommonFunctions.enterTextInTextboxUpdated(laborInputUsagePerK, data[55],"Usage Per K Value for plastics");

				//Enter Markup - Labor
				wait.until(ExpectedConditions.visibilityOfElementLocated(laborCellMarkUp));
				CommonFunctions.clickButtonOrLink(laborCellMarkUp, "table cell", "Product Markup for Labor table");
				CommonFunctions.waitForElementTobeClickable(laborInputMarkUp);
				CommonFunctions.enterTextInTextboxUpdated(laborInputMarkUp, data[56],"Product Markup for Labor table");
				
				
				
				// Miscellaneous-Costing(full) view
				CommonFunctions.waitForElementTobeClickable(imgViewMiscellaneous);
				CommonFunctions.clickButtonOrLink(imgViewMiscellaneous, "image", "View for  Miscellaneous");
				CommonFunctions.waitForElementTobeClickable(BOMTablesViewI);
				CommonFunctions.clickButtonOrLink(BOMTablesViewI, "link", "  Miscellaneous Costing Full View");
				
				
				//Enter unit Price -Miscellaneous
				wait.until(ExpectedConditions.visibilityOfElementLocated(miscellaneousCellUnitPrice_IE));
				CommonFunctions.clickButtonOrLink(miscellaneousCellUnitPrice_IE, "table cell", "unitPrice");
				CommonFunctions.waitForElementTobeClickable(miscellaneousInputUnitPrice_IE);
				CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputUnitPrice_IE, data[57],"Unit Price Value for Miscellaneous");
				 
				//Enter Usage Per K- Miscellaneous
				CommonFunctions.waitForElementTobeClickable(miscellaneousCellUsagePerK);
				CommonFunctions.clickButtonOrLink(miscellaneousCellUsagePerK, "table cell", "unitPerK");
				CommonFunctions.waitForElementTobeClickable(miscellaneousInputUsagePerK);
				CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputUsagePerK, data[58],"Usage Per K Value for Miscellaneous");

				//Enter Markup - Miscellaneous
				wait.until(ExpectedConditions.visibilityOfElementLocated(miscellaneousCellMarkUp));
				CommonFunctions.clickButtonOrLink(miscellaneousCellMarkUp, "table cell", "Product Markup for Miscellaneous table");
				CommonFunctions.waitForElementTobeClickable(miscellaneousInputMarkUp);
				CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputMarkUp, data[59],"Product Markup for Miscellaneous table");
				
				   
				   
			}
			
			else if	((data[38].contains("BOM\\Materials\\Product\\Product\\Vendor")))
			{
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);

			
				
				//Enter unit Price -Master Carton Packaging
				wait.until(ExpectedConditions.visibilityOfElementLocated(mCPackagingCellUnitPrice));
				CommonFunctions.clickButtonOrLink(mCPackagingCellUnitPrice, "table cell", "unitPrice");
				CommonFunctions.waitForElementTobeClickable(mCPackagingInputUnitPrice);
				CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputUnitPrice, data[39],"Unit Price Value for Plastics");

				//Enter Usage per K-Master Carton Packaging
				CommonFunctions.waitForElementTobeClickable(mCPackagingCellUsagePerK);
				CommonFunctions.clickButtonOrLink(mCPackagingCellUsagePerK, "table cell", "unitPerK");
				CommonFunctions.waitForElementTobeClickable(mCPackagingInputUsagePerK);
				CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputUsagePerK, data[52],"Usage Per K Value for plastics");

				//Enter Markup - Master Carton Packaging
				wait.until(ExpectedConditions.visibilityOfElementLocated(mCPackagingCellMarkUp));
				CommonFunctions.clickButtonOrLink(mCPackagingCellMarkUp, "table cell", "Product Markup for Master Carton table");
				CommonFunctions.waitForElementTobeClickable(mCPackagingInputMarkUp);
				CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputMarkUp, data[53],"Product Markup for Master Carton table");
				
				
				
				//Enter unit Price -Labor
				wait.until(ExpectedConditions.visibilityOfElementLocated(laborCellUnitPrice));
				CommonFunctions.clickButtonOrLink(laborCellUnitPrice, "table cell", "unitPrice");
				CommonFunctions.waitForElementTobeClickable(laborInputUnitPrice);
				CommonFunctions.enterTextInTextboxUpdated(laborInputUnitPrice, data[54],"Unit Price Value for Plastics");

				//Enter Usage per K-Labor
				CommonFunctions.waitForElementTobeClickable(laborCellUsagePerK);
				CommonFunctions.clickButtonOrLink(laborCellUsagePerK, "table cell", "unitPerK");
				CommonFunctions.waitForElementTobeClickable(laborInputUsagePerK);
				CommonFunctions.enterTextInTextboxUpdated(laborInputUsagePerK, data[55],"Usage Per K Value for plastics");

				//Enter Markup - Labor
				wait.until(ExpectedConditions.visibilityOfElementLocated(laborCellMarkUp));
				CommonFunctions.clickButtonOrLink(laborCellMarkUp, "table cell", "Product Markup for Labor table");
				CommonFunctions.waitForElementTobeClickable(laborInputMarkUp);
				CommonFunctions.enterTextInTextboxUpdated(laborInputMarkUp, data[56],"Product Markup for Labor table");
				
				
				
				//Enter unit Price -Miscellaneous
				wait.until(ExpectedConditions.visibilityOfElementLocated(miscellaneousCellUnitPrice));
				CommonFunctions.clickButtonOrLink(miscellaneousCellUnitPrice, "table cell", "unitPrice");
				CommonFunctions.waitForElementTobeClickable(miscellaneousInputUnitPrice);
				CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputUnitPrice, data[57],"Unit Price Value for Miscellaneous");
				 
				//Enter Usage Per K- Miscellaneous
				CommonFunctions.waitForElementTobeClickable(miscellaneousCellUsagePerK);
				CommonFunctions.clickButtonOrLink(miscellaneousCellUsagePerK, "table cell", "unitPerK");
				CommonFunctions.waitForElementTobeClickable(miscellaneousInputUsagePerK);
				CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputUsagePerK, data[58],"Usage Per K Value for Miscellaneous");

				//Enter Markup - Miscellaneous
				wait.until(ExpectedConditions.visibilityOfElementLocated(miscellaneousCellMarkUp));
				CommonFunctions.clickButtonOrLink(miscellaneousCellMarkUp, "table cell", "Product Markup for Miscellaneous table");
				CommonFunctions.waitForElementTobeClickable(miscellaneousInputMarkUp);
				CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputMarkUp, data[59],"Product Markup for Miscellaneous table");
				
				   
				   
			}
			
			CommonFunctions.waitForPageLoaded();
			Thread.sleep(1000);
			//Click button btnSaveAndCheckIn
			CommonFunctions.waitForElementTobeClickable(ExternalBOM.btnSaveAndCheckIn);
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to default frame 	 	
			driver.switchTo().defaultContent();
			
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
	        CommonFunctions.waitForPageLoaded();

			CommonFunctions.waitForVisibilityOfElement(headerAttributes);
			//	String strBOM=driver.findElement(BOMId).getText();
			String strBOMInWork1=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
			strBOMInWorkRetail= strBOMInWork1.trim();
			System.out.println("BOM name in detail page after check in: " + strBOMInWorkRetail);

		}catch(Exception e){

			fail=true;
			log.error("Exception in pc52createRetailIntBOM()", e);
			throw e;
				}
		return new String[] {strBOMInWorkRetail, BOMnameInWorkRetail1};
	}
	
	//Create Internal Retail Item BOM
		public static String[] TC06_pc52createRetailIntBOM(String[] data) throws Exception{
			try{
				
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.enterTextInTextbox(BOMTypeId, data[38]);
				//CommonFunctions.waitForPageLoaded();
				//Click Initialize BOM
				CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
				CommonFunctions.waitForPageLoaded();
				BOMnameRetail="InWork"+CommonFunctions.getRandomString(4);

				//Create BOM page
				BOMnameInWorkRetail1 = fillCreateBOM(data);
				CommonFunctions.waitForPageLoaded();
				Thread.sleep(1000);
				
				if(data[38].contains("BOM\\Materials\\Product\\Retail Item\\Internal"))
					//if(data[38].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[38].contains("BOM\\Materials\\Product\\Product\\Internal")))
				{ 
					//Plastics-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewPlastics);
					   CommonFunctions.clickButtonOrLink(imgViewPlastics, "image", "View for Plastics");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View");
				   //Enter unit Price-Plastics
				   wait.until(ExpectedConditions.visibilityOfElementLocated(cellUnitPriceIE));
				   CommonFunctions.clickButtonOrLink(cellUnitPriceIE, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(inputUnitPriceIE);
				   CommonFunctions.enterTextInTextboxUpdated(inputUnitPriceIE, data[39],"Unit Price Value for Plastics");
				   //Enter usage per K-Plastics
				   CommonFunctions.waitForElementTobeClickable(CI253.cellUsagePerK);
				   CommonFunctions.clickButtonOrLink(CI253.cellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(CI253.inputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(CI253.inputUsagePerK, data[39],"Usage Per K Value for plastics");
				   //Enter Product Markup-Plastics
				   CommonFunctions.waitForElementTobeClickable(plasticCellMarkup);
				   CommonFunctions.clickButtonOrLink(plasticCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(plasticInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(plasticInputMarkup, data[39],"Markup Value for plastics");
				
				   
				   
					 //Chemical-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewChemical);
					   CommonFunctions.clickButtonOrLink(imgViewChemical, "image", "View for Plastics");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
				   //Enter unit Price-Chemicals
				   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUnitPriceIE));
				   CommonFunctions.clickButtonOrLink(chemicalCellUnitPriceIE, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(chemicalInputUnitPriceIE);
				   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUnitPriceIE, data[39],"Unit Price Value for Chemicals");
				   CommonFunctions.waitForPageLoaded();
				   //Enter usage per K-Chemicals
				   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(chemicalCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(chemicalInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUsagePerK, data[39],"Usage Per K Value for Chemicals");
				   //Enter Product Markup-Chemicals
				   CommonFunctions.waitForElementTobeClickable(chemicalCellMarkup);
				   CommonFunctions.clickButtonOrLink(chemicalCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(chemicalInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(chemicalInputMarkup, data[39],"Markup Value for Chemicals");

				   
				   
					 //Purchased-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewPurchased);
					   CommonFunctions.clickButtonOrLink(imgViewPurchased, "image", "View for Purchased");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
				   //Enter unit Price-Purchased Parts
				   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUnitPriceIE));
				   CommonFunctions.clickButtonOrLink(purchasedCellUnitPriceIE, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(purchasedInputUnitPriceIE);
				   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUnitPriceIE, data[39],"Unit Price Value for Purchased Parts");
				   //Enter usage per K-Purchased Parts
				   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(purchasedCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(purchasedInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUsagePerK, data[39],"Usage Per K Value for Purchased Parts");
				   //Enter Product Markup-Purchased Parts
				   CommonFunctions.waitForElementTobeClickable(purchasedCellMarkup);
				   CommonFunctions.clickButtonOrLink(purchasedCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(purchasedInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(purchasedInputMarkup, data[39],"Markup Value for Purchased");

				   
				   
					 //Electronics-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewElectronics);
					   CommonFunctions.clickButtonOrLink(imgViewElectronics, "image", "View for Plastics");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
				   //Enter unit Price-Electronics
				   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUnitPriceIE));
				   CommonFunctions.clickButtonOrLink(electCellUnitPriceIE, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(electInputUnitPriceIE);
				   CommonFunctions.enterTextInTextboxUpdated(electInputUnitPriceIE, data[39],"Unit Price Value for Electronics");
				   //Enter usage per K-Electronics
				   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(electCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(electInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(electInputUsagePerK, data[39],"Usage Per K Value for Electronics");
				   //Enter Product Markup-Electronics
				   CommonFunctions.waitForElementTobeClickable(electCellMarkup);
				   CommonFunctions.clickButtonOrLink(electCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(electInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(electInputMarkup, data[39],"Markup Value for Electronics");

				   
				   
				  //Soft Goods-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewSG);
					   CommonFunctions.clickButtonOrLink(imgViewSG, "image", "View for Plastics");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
				   //Enter unit Soft Goods
				   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUnitPriceIE));
				   CommonFunctions.clickButtonOrLink(sGCellUnitPriceIE, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(sGInputUnitPriceIE);
				   CommonFunctions.enterTextInTextboxUpdated(sGInputUnitPriceIE, data[39],"Unit Price Value for Soft Goods");
				   //Enter usage per K-Soft Goods
				   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(sGCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(sGInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(sGInputUsagePerK, data[39],"Usage Per K Value for Soft Goods");
				   //Enter Product Markup-Soft Goods
				   CommonFunctions.waitForElementTobeClickable(sGCellMarkup);
				   CommonFunctions.clickButtonOrLink(sGCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(sGInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(sGInputMarkup, data[39],"Markup Value for Soft Goods");
	               
				   
				   
				 //Pacakaging-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewPackaging);
					   CommonFunctions.clickButtonOrLink(imgViewPackaging, "image", "View for Plastics");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
				   //Enter unit Pacakaging
				   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUnitPriceIE));
				   CommonFunctions.clickButtonOrLink(packagingCellUnitPriceIE, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(packagingInputUnitPriceIE);
				   CommonFunctions.enterTextInTextboxUpdated(packagingInputUnitPriceIE, data[39],"Unit Price Value for Pacakaging");
				   //Enter usage per Packaging
				   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(packagingCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(packagingInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(packagingInputUsagePerK, data[39],"Usage Per K Value for Pacakaging");
				   //Enter Product Markup-Soft Goods
				   CommonFunctions.waitForElementTobeClickable(packagingCellMarkup);
				   CommonFunctions.clickButtonOrLink(packagingCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(packagingInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(packagingInputMarkup, data[39],"Markup Value for Pacakaging");
	               
				   
				   
					 //General Decor Labour-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewGenDecLabor);
					   CommonFunctions.clickButtonOrLink(imgViewGenDecLabor, "image", "View for Plastics");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
				   //Enter unit General Decor Labour
				   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUnitPriceIE));
				   CommonFunctions.clickButtonOrLink(generalLabourCellUnitPriceIE, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(generalLabourInputUnitPriceIE);
				   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUnitPriceIE, data[39],"Unit Price Value for General/Deco Labor");
				   //Enter usage per General Decor Labour
				   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(generalLabourCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(generalLabourInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUsagePerK, data[39],"Usage Per K Value for General/Deco Labor");
				   //Enter Product Markup-General Decor Labour
				   CommonFunctions.waitForElementTobeClickable(generalCellMarkup);
				   CommonFunctions.clickButtonOrLink(generalCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(generalInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(generalInputMarkup, data[39],"Markup Value for General/Deco Labor");

				   
				   
				  //Molding Labour-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewMoldingLabor);
					   CommonFunctions.clickButtonOrLink(imgViewMoldingLabor, "image", "View for Plastics");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
				   //Enter unit Molding Labour
				   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUnitPriceIE));
				   CommonFunctions.clickButtonOrLink(moldingCellUnitPriceIE, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(moldingInputUnitPriceIE);
				   CommonFunctions.enterTextInTextboxUpdated(moldingInputUnitPriceIE, data[39],"Unit Price Value for Molding Labor");
				   //Enter usage per Molding Labour
				   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(moldingCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(moldingInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(moldingInputUsagePerK, data[39],"Usage Per K Value for Molding Labor");
				   //Enter Product Markup-Molding Labour
				   CommonFunctions.waitForElementTobeClickable(moldingCellMarkup);
				   CommonFunctions.clickButtonOrLink(moldingCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(moldingInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(moldingInputMarkup, data[39],"Markup Value for Molding");
				}
				
		
				
			
				
				CommonFunctions.waitForPageLoaded();
				Thread.sleep(1000);
				//Click button btnSaveAndCheckIn
				CommonFunctions.waitForElementTobeClickable(ExternalBOM.btnSaveAndCheckIn);
				CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to default frame 	 	
				driver.switchTo().defaultContent();
				
				
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");
		        CommonFunctions.waitForPageLoaded();

				CommonFunctions.waitForVisibilityOfElement(headerAttributes);
				//	String strBOM=driver.findElement(BOMId).getText();
				String strBOMInWork1=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
				strBOMInWorkRetail= strBOMInWork1.trim();
				System.out.println("BOM name in detail page after check in: " + strBOMInWorkRetail);

			}catch(Exception e){

				fail=true;
				log.error("Exception in TC06_pc52createRetailIntBOM()", e);
				throw e;
					}
			return new String[] {strBOMInWorkRetail, BOMnameInWorkRetail1};
		}
	
	//Fill BOM
	
	public static String fillCreateBOM(String[] data) throws Exception{
		try{
			InternalBOMSoftG.BOMname="InWork"+CommonFunctions.getRandomString(4);
			System.out.println("BOM Name entered is: " + InternalBOMSoftG.BOMname);
			CommonFunctions.waitForVisibilityOfElement(headingCreateBOM);
			if(data[38].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[38].contains("BOM\\Materials\\Product\\Product\\Internal")))
			{
				BOMname = "InWork"+CommonFunctions.getRandomString(4);
				CommonFunctions.enterTextInTextbox(name,BOMname);
				//CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 2);
			CommonFunctions.waitForPageLoaded();
			//Select Wave
			//CommonFunctions.selectFromDropDownByVisibleText(wave, data[34]);
			CommonFunctions.selectFromDropDownByIndex(wave, 1);
			CommonFunctions.waitForPageLoaded();
			//Select Currency
			//CommonFunctions.selectFromDropDownByVisibleText(currency, data[39]);
			CommonFunctions.selectFromDropDownByIndex(currency, 1);
			CommonFunctions.waitForPageLoaded();
			/*if(data[38].contains("BOM\\Materials\\Product\\Product\\Vendor")){
			//Wait for Factory
			CommonFunctions.waitForElementTobeClickable(SourcingConfig.factory);
			//Click on Factory
			CommonFunctions.clickButtonOrLink(SourcingConfig.factory, "link", "Factory field from Create BOM page");
			CommonFunctions.waitForPageLoaded();
			Set set = SeleniumDriver.driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			SeleniumDriver.driver.switchTo().window(child);
			CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'(choose)')]"), "Supplier selected");
			}
			SeleniumDriver.driver.switchTo().window(parent);*/

			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");
			//click on Create
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame")); 
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(CI253.AddRowsButton));
			
		
			}
			else if((data[38].contains("BOM\\Materials\\Product\\Retail Item\\Vendor"))){
				//Select colorway
				CommonFunctions.selectFromDropDownByIndex(colorway, 1);
				CommonFunctions.waitForPageLoaded();
				//Select Currency
				//CommonFunctions.selectFromDropDownByVisibleText(currency, data[39]);
				CommonFunctions.selectFromDropDownByIndex(currency, 1);
				CommonFunctions.waitForPageLoaded();
				//Wait for Factory
				CommonFunctions.waitForElementTobeClickable(SourcingConfig.factory);
				//Click on Factory
				CommonFunctions.clickButtonOrLink(SourcingConfig.factory, "link", "Factory field from Create BOM page");
				CommonFunctions.waitForPageLoaded();
				Set set = SeleniumDriver.driver.getWindowHandles();
				Iterator iter = set.iterator();
				String parent = (java.lang.String) iter.next();
				String child = (java.lang.String) iter.next();
				SeleniumDriver.driver.switchTo().window(child);
				CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'(choose)')]"), "Supplier selected");
				SeleniumDriver.driver.switchTo().window(parent);

				SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");
				//click on Create
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");	
				//CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				//driver.switchTo().frame("mainFrame");
				CommonFunctions.waitForPageLoaded();
				
			}
			
			else if((data[38].contains("BOM\\Materials\\Product\\Product\\Vendor"))){
				//Select colorway
				CommonFunctions.selectFromDropDownByIndex(colorway, 1);
				CommonFunctions.waitForPageLoaded();
				//Select Wave
				CommonFunctions.selectFromDropDownByIndex(wave, 1);
				CommonFunctions.waitForPageLoaded();
				//CommonFunctions.selectFromDropDownByVisibleText(wave, data[34]);
				//Select Currency
				CommonFunctions.selectFromDropDownByIndex(currency, 1);
				CommonFunctions.waitForPageLoaded();
				//CommonFunctions.selectFromDropDownByVisibleText(currency, data[39]);
				//Wait for Factory
				CommonFunctions.waitForElementTobeClickable(SourcingConfig.factory);
				
				//Click on Factory
				CommonFunctions.clickButtonOrLink(SourcingConfig.factory, "link", "Factory field from Create BOM page");
				CommonFunctions.waitForPageLoaded();
				Set set = SeleniumDriver.driver.getWindowHandles();
				Iterator iter = set.iterator();
				String parent = (java.lang.String) iter.next();
				String child = (java.lang.String) iter.next();
				SeleniumDriver.driver.switchTo().window(child);
				CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'(choose)')]"), "Supplier selected");
				SeleniumDriver.driver.switchTo().window(parent);

				SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");
				//click on Create
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");	
				//CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				//driver.switchTo().frame("mainFrame");
				CommonFunctions.waitForPageLoaded();
				
				
			
				
			}
		}

		catch(Exception e){
			fail=true;
			log.error("Exception in fillCreateBOM()", e);
			//return "";
			throw e;
		}
		return BOMname;
	}
	
	public static Boolean createCS(String[] data) throws Exception{
		try{
		
			String strPrdNo=driver.findElement(csProductNumber).getText();
			CommonProjectFunctions.searchProduct(strPrdNo);
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			
		    //Wait for 	Sourcing Tab
			wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
			CommonFunctions.waitForPageLoaded();
			//Click on Sourcing tab
			CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				
			//Wait for 	Costing Tab
			wait.until(ExpectedConditions.visibilityOfElementLocated(CostingTab));
			CommonFunctions.waitForPageLoaded();
			//Click on Costing tab
			CommonFunctions.clickButtonOrLink(CostingTab, "link", "CostingTab");
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
			CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
			CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
			CommonFunctions.waitForPageLoaded();
		    //Wait for Source dropdown
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
			//Click on Source dropdown
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 1);
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
			//Select Specification
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
			Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded();
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
			CommonFunctions.waitForPageLoaded();
			//}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in createCS()", e);
			//return false;
			throw e;
			
		}
		return true;
	}
	
	
	public static Boolean createCS_TC01(String[] data) throws Exception{
		try{
		
			String strPrdNo=driver.findElement(csProductNumber).getText();
			CommonProjectFunctions.searchProduct(strPrdNo);
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			
		    //Wait for 	Sourcing Tab
			wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
			CommonFunctions.waitForPageLoaded();
			//Click on Sourcing tab
			CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				
			//Wait for 	Costing Tab
			wait.until(ExpectedConditions.visibilityOfElementLocated(CostingTab));
			CommonFunctions.waitForPageLoaded();
			//Click on Costing tab
			CommonFunctions.clickButtonOrLink(CostingTab, "link", "CostingTab");
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
			CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
			CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
			CommonFunctions.waitForPageLoaded();
		    /*//Wait for Source dropdown
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
			//Click on Source dropdown
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 1);
			CommonFunctions.waitForPageLoaded();
			*/
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
			//Select Specification
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
			Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded();
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
			CommonFunctions.waitForPageLoaded();
			//}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in createCS_TC01()", e);
			//return false;
			throw e;
			
		}
		return true;
	}
	public static Boolean createCS_TC02(String[] data) throws Exception{
		try{
		
			String strPrdNo=driver.findElement(csProductNumber).getText();
			CommonProjectFunctions.searchProduct(strPrdNo);
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			
		    //Wait for 	Sourcing Tab
			wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
			CommonFunctions.waitForPageLoaded();
			//Click on Sourcing tab
			CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				
			//Wait for 	Costing Tab
			wait.until(ExpectedConditions.visibilityOfElementLocated(CostingTab));
			CommonFunctions.waitForPageLoaded();
			//Click on Costing tab
			CommonFunctions.clickButtonOrLink(CostingTab, "link", "CostingTab");
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
			CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
			CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
			CommonFunctions.waitForPageLoaded();
		    /*//Wait for Source dropdown
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
			//Click on Source dropdown
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 1);
			CommonFunctions.waitForPageLoaded();
			*/
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
			//Select Specification
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
			Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded();
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
			CommonFunctions.waitForPageLoaded();
			//}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in createCS_TC02()", e);
			//return false;
			throw e;
			
		}
		return true;
	}

	//Create Internal Retail Item Costsheet
	public static String CreateInternalRetailCS(String[] data) throws Exception{
		try{
			    createCS(data);
				CostsheetTooling.clickOnCostSheetListTab();
				CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.costAction);
				CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForVisibilityOfElement(costSheetType);
				CommonFunctions.clickButtonOrLink(costSheetType, "link", "Costsheet Type-Internal Retail CS");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForVisibilityOfElement(CostsheetInternal.csName);
				driver.findElement(CostsheetInternal.csName).clear();
				CommonFunctions.enterTextInTextbox(CostsheetInternal.csName, data[41]);
				//Wait for Wave Dropdown
				CommonFunctions.waitForElementTobeClickable(wave);
				//Click on Wave Dropdown
				//CommonFunctions.selectFromDropDownByVisibleTextUpdated(wave, data[34], "Wave DropDown");
				CommonFunctions.selectFromDropDownByIndex(wave, 1);
				//Wait for Quote Currency Dropdown
				CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
				//Click on Quote Currency Dropdown
				CommonFunctions.selectFromDropDownByIndex(QuoteCurrencyDropDown, 2);
				//Wait for BOM Dropdown
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(BOMDropDown);
				//Select Specification
				CommonFunctions.selectFromDropDownByIndex(BOMDropDown, 1);
				Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded();
				// click on Save button
				CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
				CommonFunctions.waitForPageLoaded();
				
			}
		catch(Exception e){
			fail=true;
			log.error("Exception in CreateInternalRetailCS()", e);
			//return "";
			throw e;
		}
		return cstCreateCS;
		
	}
	//Create Internal Retail Item Costsheet
		public static String TC06_CreateInternalRetailCS(String[] data) throws Exception{
			try{
				    createCS(data);
					CostsheetTooling.clickOnCostSheetListTab();
					CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.costAction);
					CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet");
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForVisibilityOfElement(costSheetType);
					CommonFunctions.clickButtonOrLink(costSheetType, "link", "Costsheet Type-Internal Retail CS");
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForVisibilityOfElement(CostsheetInternal.csName);
					driver.findElement(CostsheetInternal.csName).clear();
					CommonFunctions.enterTextInTextbox(CostsheetInternal.csName, data[41]);
					//Wait for Wave Dropdown
					//CommonFunctions.waitForElementTobeClickable(wave);
					//Click on Wave Dropdown
					//CommonFunctions.selectFromDropDownByVisibleTextUpdated(wave, data[34], "Wave DropDown");
					//CommonFunctions.selectFromDropDownByIndex(wave, 1);
					//Wait for Quote Currency Dropdown
					CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
					//Click on Quote Currency Dropdown
					CommonFunctions.selectFromDropDownByIndex(QuoteCurrencyDropDown, 2);
					//Wait for BOM Dropdown
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(BOMDropDown);
					//Select Specification
					CommonFunctions.selectFromDropDownByIndex(BOMDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					// click on Save button
					CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
					CommonFunctions.waitForPageLoaded();
					
				}
			catch(Exception e){
				fail=true;
				log.error("Exception in TC06_CreateInternalRetailCS()", e);
				//return "";
				throw e;
			}
			return cstCreateCS;
			
		}
	//Create Internal Retail Item Costsheet
			public static String CreateInternalRetailCS_TC01(String[] data) throws Exception{
				try{
					    createCS_TC01(data);
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
						CommonFunctions.enterTextInTextbox(CostsheetInternal.csName, data[73]);
						//Wait for Wave Dropdown
						//CommonFunctions.waitForElementTobeClickable(wave);
						//Click on Wave Dropdown
						//CommonFunctions.selectFromDropDownByVisibleTextUpdated(wave, data[34], "Wave DropDown");
						//CommonFunctions.selectFromDropDownByIndex(wave, 1);
						//Wait for Quote Currency Dropdown
						CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
						//Click on Quote Currency Dropdown
						CommonFunctions.selectFromDropDownByIndex(QuoteCurrencyDropDown, 2);
						//Wait for BOM Dropdown
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(BOMDropDown);
						//Select Specification
						CommonFunctions.selectFromDropDownByIndex(BOMDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						// click on Save button
						CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
						CommonFunctions.waitForPageLoaded();
						
					}
				catch(Exception e){
					fail=true;
					log.error("Exception in CreateInternalRetailCS_TC01()", e);
					//return "";
					throw e;
				}
				return cstCreateCS;
				
			}
			//Create Internal Retail Item Costsheet
			public static String CreateInternalRetailCS_TC03(String[] data) throws Exception{
				try{
					    createCS_TC01(data);
						CostsheetTooling.clickOnCostSheetListTab();
						CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.costAction);
						CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet");
						CommonFunctions.waitForPageLoaded();
						driver.switchTo().defaultContent();
						driver.switchTo().frame("contentframe");
						
						
						if(data[3].equalsIgnoreCase("TC02_PC49_AssociateBOMToVendorProductCS")){
							
						
						CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
						//Click on Quote Currency Dropdown
						CommonFunctions.selectFromDropDownByIndex(QuoteCurrencyDropDown, 2);
						//Wait for BOM Dropdown
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(BOMDropDown);
						//Select Specification
						CommonFunctions.selectFromDropDownByIndex(BOMDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						// click on Save button
						CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
						CommonFunctions.waitForPageLoaded();
						
						}
						/*CommonFunctions.waitForVisibilityOfElement(CostsheetInternal.csName);
						driver.findElement(CostsheetInternal.csName).clear();
						CommonFunctions.enterTextInTextbox(CostsheetInternal.csName, data[73]);*/
						//Wait for Wave Dropdown
						//CommonFunctions.waitForElementTobeClickable(wave);
						//Click on Wave Dropdown
						//CommonFunctions.selectFromDropDownByVisibleTextUpdated(wave, data[34], "Wave DropDown");
						//CommonFunctions.selectFromDropDownByIndex(wave, 1);
						else{
						CommonFunctions.waitForVisibilityOfElement(costSheetTypeVendor);
						CommonFunctions.clickButtonOrLink(costSheetTypeVendor, "link", "Costsheet Type-Internal Retail CS");
						CommonFunctions.waitForPageLoaded();
						//Wait for Quote Currency Dropdown
						CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
						//Click on Quote Currency Dropdown
						CommonFunctions.selectFromDropDownByIndex(QuoteCurrencyDropDown, 2);
						//Wait for BOM Dropdown
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(BOMDropDown);
						//Select Specification
						CommonFunctions.selectFromDropDownByIndex(BOMDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						// click on Save button
						CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
						CommonFunctions.waitForPageLoaded();
						}
						
					}
				catch(Exception e){
					fail=true;
					log.error("Exception in CreateInternalRetailCS_TC03()", e);
					//return "";
					throw e;
				}
				return cstCreateCS;
				
			}
	//Create Internal Retail Item Costsheet
		public static String CreateInternalRetailCS_TC02(String[] data) throws Exception{
			try{
				    createCS_TC02(data);
					CostsheetTooling.clickOnCostSheetListTab();
					CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.costAction);
					CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet");
					CommonFunctions.waitForPageLoaded();
					//CommonFunctions.waitForVisibilityOfElement(costSheetType);
					//CommonFunctions.clickButtonOrLink(costSheetType, "link", "Costsheet Type-Internal Retail CS");
					CommonFunctions.waitForPageLoaded();
					//CommonFunctions.waitForVisibilityOfElement(CostsheetInternal.csName);
					//driver.findElement(CostsheetInternal.csName).clear();
					//CommonFunctions.enterTextInTextbox(CostsheetInternal.csName, data[41]);
					//Wait for Wave Dropdown
					//CommonFunctions.waitForElementTobeClickable(wave);
					//Click on Wave Dropdown
					//CommonFunctions.selectFromDropDownByVisibleTextUpdated(wave, data[34], "Wave DropDown");
					//CommonFunctions.selectFromDropDownByIndex(wave, 1);
					//Wait for Quote Currency Dropdown
					CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
					//Click on Quote Currency Dropdown
					CommonFunctions.selectFromDropDownByIndex(QuoteCurrencyDropDown, 2);
					//Wait for BOM Dropdown
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(BOMDropDown);
					//Select Specification
					CommonFunctions.selectFromDropDownByIndex(BOMDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					// click on Save button
					CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
					CommonFunctions.waitForPageLoaded();
					
				}
			catch(Exception e){
				fail=true;
				log.error("Exception in CreateInternalRetailCS_TC02()", e);
				//return "";
				throw e;
			}
			return cstCreateCS;
			
		}
	//Create Vendor Product Costsheet
		public static String CreateVendorProductCS(String[] data) throws Exception{
			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
			    //Wait for 	Sourcing Tab
		         CommonFunctions.waitForPageLoaded();
				//Click on Sourcing tab
				CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				CommonFunctions.waitForPageLoaded();	
				//Wait for 	Costing Tab
				wait.until(ExpectedConditions.visibilityOfElementLocated(CostingTab));;
				CommonFunctions.waitForPageLoaded();
				//Click on Costing tab
				CommonFunctions.clickButtonOrLink(CostingTab, "link", "CostingTab");
				CommonFunctions.waitForPageLoaded();

		        //Wait for Season dropdown 
		        //CommonFunctions.waitForPageLoaded();
			    CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
			    CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
				
				//Wait for Specification
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
				//Select Specification
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
				//Wait for Colorway
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
				//Select colorway
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
				
				CommonFunctions.waitForPageLoaded();
				CostsheetTooling.clickOnCostSheetListTab();
				CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.costAction);
				CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet");
				CommonFunctions.waitForPageLoaded();
				
				
				//Wait for Quote Currency Dropdown
				CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
				//Click on Quote Currency Dropdown
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(QuoteCurrencyDropDown, data[57], "QuoteCurrency DropDown");
				
				CommonFunctions.waitForPageLoaded();
				//Wait for BOM Dropdown
				CommonFunctions.waitForElementTobeClickable(BOMDropDown);
				//Select Specification
				CommonFunctions.selectFromDropDownByIndex(BOMDropDown, 1);
				Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded();
				// click on Save button
				CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
				CommonFunctions.waitForPageLoaded();
				
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in CreateVendorProductCS()", e);
				//return "";
				throw e;
			}
			return cstCreateCS;
		}
		
		//Create Vendor Product Costsheet
				public static String CreateVendorRetailCS(String[] data) throws Exception{
					try{
						wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					    //Wait for 	Sourcing Tab
				         CommonFunctions.waitForPageLoaded();
						//Click on Sourcing tab
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
							
						//Wait for 	Costing Tab
						wait.until(ExpectedConditions.visibilityOfElementLocated(CostingTab));
						CommonFunctions.waitForPageLoaded();
						//Click on Costing tab
						CommonFunctions.clickButtonOrLink(CostingTab, "link", "CostingTab");
						wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
						CommonFunctions.waitForPageLoaded();

				        //Wait for Season dropdown 
				        //CommonFunctions.waitForPageLoaded();
					    CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					    CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
						
						//Wait for Specification
						CommonFunctions.waitForPageLoaded();
						wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.selectSpecification));
						CommonFunctions.waitForPageLoaded();
						//CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
						//Select Specification
						CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
						//Wait for Colorway
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
						//Select colorway
						CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
						//wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.costAction));
						CommonFunctions.waitForPageLoaded();
						Thread.sleep(1000);
						wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.tabCostsheet));
						CommonFunctions.clickButtonOrLink(CostsheetTooling.tabCostsheet, "Tab", "Cost Sheet Tab");
						//CostsheetTooling.clickOnCostSheetListTab();
						wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.costAction));
						CommonFunctions.waitForPageLoaded();
						//CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.costAction);
						CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet");
						CommonFunctions.waitForPageLoaded();
						wait.until(ExpectedConditions.visibilityOfElementLocated(strcostSheetType));
						//CommonFunctions.waitForVisibilityOfElement(strcostSheetType);
						//Click on Costing tab
						CommonFunctions.clickButtonOrLink(strcostSheetType, "link", "Cost Sheet Type");
						CommonFunctions.waitForPageLoaded();
						
						//Wait for Quote Currency Dropdown
						CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
						//Click on Quote Currency Dropdown
						CommonFunctions.selectFromDropDownByIndex(QuoteCurrencyDropDown, 1);
						
						CommonFunctions.waitForPageLoaded();
						//Commented for TC20
						/*//Wait for BOM Dropdown
						CommonFunctions.waitForElementTobeClickable(BOMDropDown);
						//Select Specification
						CommonFunctions.selectFromDropDownByIndex(BOMDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();*/
						// click on Save button
						CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
						CommonFunctions.waitForPageLoaded();
						
					}
					catch(Exception e){
						fail=true;
						log.error("Exception in CreateVendorProductCS()", e);
						//return "";
						throw e;
					}
					return cstCreateCS;
				}
				
				//Create Vendor Product What IF Costsheet
				public static String CreateVendorWhatIfCS(String[] data) throws Exception{
					try{
						wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					    //Wait for 	Sourcing Tab
				         CommonFunctions.waitForPageLoaded();
						//Click on Sourcing tab
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
							
						//Wait for 	Costing Tab
						wait.until(ExpectedConditions.visibilityOfElementLocated(CostingTab));
						CommonFunctions.waitForPageLoaded();
						//Click on Costing tab
						CommonFunctions.clickButtonOrLink(CostingTab, "link", "CostingTab");
						wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
						CommonFunctions.waitForPageLoaded();

				        //Wait for Season dropdown 
				        //CommonFunctions.waitForPageLoaded();
					    CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					    CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
						
						//Wait for Specification
						CommonFunctions.waitForPageLoaded();
						wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.selectSpecification));
						CommonFunctions.waitForPageLoaded();
						//CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
						//Select Specification
						CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
						//Wait for Colorway
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
						//Select colorway
						CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
						//wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.costAction));
						CommonFunctions.waitForPageLoaded();
						Thread.sleep(1000);
						wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.tabCostsheet));
						CommonFunctions.clickButtonOrLink(CostsheetTooling.tabCostsheet, "Tab", "Cost Sheet Tab");
						//CostsheetTooling.clickOnCostSheetListTab();
						wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.costAction));
						CommonFunctions.waitForPageLoaded();
						//CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.costAction);
						CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create New What If");
						CommonFunctions.waitForPageLoaded();
						wait.until(ExpectedConditions.visibilityOfElementLocated(strcostSheetType));
						//CommonFunctions.waitForVisibilityOfElement(strcostSheetType);
						//Click on Costing tab
						CommonFunctions.clickButtonOrLink(strcostSheetType, "link", "Cost Sheet Type");
						CommonFunctions.waitForPageLoaded();
						
						//Wait for Quote Currency Dropdown
						CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
						//Click on Quote Currency Dropdown
						CommonFunctions.selectFromDropDownByIndex(QuoteCurrencyDropDown, 1);
						// Click on Save button
						CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
						CommonFunctions.waitForPageLoaded();
						
					}
					catch(Exception e){
						fail=true;
						log.error("Exception in CreateVendorWhatIfCS()", e);
						//return "";
						throw e;
					}
					return cstCreateCS;
				}
			
	//Get attribute value
	public static String GettingAttribute(By by){
		
		try{
			wait.until(ExpectedConditions.elementToBeClickable(by));
			ActualValue=SeleniumDriver.driver.findElement(by).getAttribute("value");
			
			}
		catch(Exception e){
			fail=true;
			log.error("Exception in waitForElementTobeClicable()", e);
			throw e;
			
		}
		return ActualValue;
		
		}
	//Get text value
    public static String GettingText(By by){
    try{
	wait.until(ExpectedConditions.elementToBeClickable(by));
	ActualValue=SeleniumDriver.driver.findElement(by).getText();
	System.out.println("The ActualValue is "+ActualValue);
	}
    catch(Exception e){
	fail=true;
	log.error("Exception in waitForElementTobeClicable()", e);
	throw e;
	}
    return ActualValue;
    } 

				
    //Verify Currency Conversion Rate
    public static boolean verifyCurrencyConversionRate(String[] data) throws Exception
    {
    	try{


    		strCurrency=GettingText(strQuoteCurrency);
    		System.out.println("The currency value is coming as "+strCurrency);
    		GettingText(strCurrencyConversionRate);
    		log.info(ActualValue);
    		if(strCurrency.equalsIgnoreCase("USD")){
    			Assert.assertEquals(ActualValue, data[69]);
    		}
    		else{
    			Assert.assertEquals(ActualValue, data[70]);	
    		}
    		log.info("Verified Quote Currency");
    		//return true;
    	}catch(Exception e){
    		fail=true;
    		log.error("Exception in verifyCurrencyConversionRate", e);
    		return false;
    	}
    	return true;

    }

    //Verify BOM Name
    public static boolean verifyBOMName(String[] data) throws Exception
    {
    	try{

    		GettingText(strBOMName);
    		log.info(ActualValue);
    		//String name = data[59]+BOMname;
    		Assert.assertEquals(ActualValue, data[59]);
    		log.info("Verified BOM Name");
    		//return true;
    	}
    	catch(Exception e){
    		fail=true;
    		log.error("Exception in verifyBOMName", e);
    		return false;
    	}
    	return true;

    }

    //PC48 Validations

    public static boolean pc48assertionVerfication(String [] data) throws Exception{
    	try{
    		//Assertion Verification of BOM Name
    		verifyBOMName(data);
    		//Assertion Verification of BOM Iteration
    		CommonFunctions.waitForElementTobeClickable(strBOMIteration);
    		GettingText(strBOMIteration);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[60], "Actual And Expected Cost Sheet BOM Iteration Are Not Matched.Assertion Failed.Please check");
    		log.info("********Verified BOM Iteration********");
    		//Assertion Verification of Quote Currency
    		CommonFunctions.waitForElementTobeClickable(strQuoteCurrency);
    		GettingText(strQuoteCurrency);
    		log.info("********Verified Quote Currency********");
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[39], "Actual And Expected Cost Sheet Quote Currency Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of CurrencyConversionRate of USD
    		verifyCurrencyConversionRate(data);
    		//Assertion Verification of Retail Item Cost
    		//CommonFunctions.waitForElementTobeClickable(strRetailItemCostValue);
    		GettingText(strRetailItemCostValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[71], "Actual And Expected Cost Sheet Retail Item Cost Value Are Not Matched.Assertion Failed.Please check");
    		log.info("********Verified Retail Item Cost********");
    		//Assertion Verification of  Plastic material
    		CommonFunctions.waitForElementTobeClickable(strPlasticValue);
    		GettingText(strPlasticValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[58], "Actual And Expected Cost Sheet Plastic material Are Not Matched.Assertion Failed.Please check");
    		log.info("********Verified Plastic Value********");
    		//Assertion Verification of  Purchased Material
    		CommonFunctions.waitForElementTobeClickable(strPurchasedValue);
    		GettingText(strPurchasedValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[62], "Actual And Expected Cost Sheet Purchased Material Not Matched.Assertion Failed.Please check");
    		log.info("********Verified Purchased Value********");
    		//Assertion Verification of  Soft Goods Material
    		CommonFunctions.waitForElementTobeClickable(strSGValue);
    		GettingText(strSGValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[64], "Actual And Expected Cost Sheet Soft Goods Material Are Not Matched.Assertion Failed.Please check");
    		log.info("********Verified Soft Goods Material Value********");
    		//Assertion Verification of  Chemical Material
    		CommonFunctions.waitForElementTobeClickable(strChemicalValue);
    		GettingText(strChemicalValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[61], "Actual And Expected Cost Sheet Chemical Material Are Not Matched.Assertion Failed.Please check");
    		log.info("********Verified Chemical Material Value********");
    		//Assertion Verification of  General / Deco Labor Cost
    		CommonFunctions.waitForElementTobeClickable(strGenDecLabValue);
    		GettingText(strGenDecLabValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[66], "Actual And Expected Cost Sheet General / Deco Labor Cost Are Not Matched.Assertion Failed.Please check");
    		log.info("********Verified General / Deco Labor Cost Value********");
    		//Assertion Verification of  Molding Labor Cost
    		CommonFunctions.waitForElementTobeClickable(strMoldingLabValue);
    		GettingText(strMoldingLabValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[67], "Actual And Expected Cost Sheet Molding Labor Cost Are Not Matched.Assertion Failed.Please check");
    		log.info("********Verified Molding Labor Cost Value********");
    		//Assertion Verification of  Electronic Material
    		CommonFunctions.waitForElementTobeClickable(strElectronicValue);
    		GettingText(strElectronicValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[63], "Actual And Expected Cost Sheet Electronic Material Are Not Matched.Assertion Failed.Please check");
    		log.info("********Verified Electronic Material Value********");
    		//Assertion Verification of  Packaging Material
    		CommonFunctions.waitForElementTobeClickable(strPackagingValue);
    		GettingText(strPackagingValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[65], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
    		log.info("********Verified Packaging Material Value********");
    		//Assertion Verification of  Product Markup Cost
    		CommonFunctions.waitForElementTobeClickable(strProductMarkup);
    		GettingText(strProductMarkup);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[87], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
    		log.info("********Verified Product Markup Value********");
    	}
    	catch(Exception e){

    		fail=true;
    		log.error("Exception in pc48assertionVerfication"+e);
    		throw e;
    	}
    	return true;
    }
    //Fill BOM Details
    public static String fillCreateBOMpc48(String[] data) throws Exception{
		try{
			InternalBOMSoftG.BOMname="InWork"+CommonFunctions.getRandomString(4);
			System.out.println("BOM Name entered is: " + InternalBOMSoftG.BOMname);
			CommonFunctions.waitForVisibilityOfElement(headingCreateBOM);
			if(data[38].contains("BOM\\Materials\\Product\\Retail Item\\Internal"))
			{
				BOMname = "InWork"+CommonFunctions.getRandomString(4);
				CommonFunctions.enterTextInTextbox(name,BOMname);
			
				//CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
			CommonFunctions.waitForPageLoaded();
			//Select Wave
			//CommonFunctions.selectFromDropDownByVisibleText(wave, data[34]);
			CommonFunctions.selectFromDropDownByIndex(wave, 1);
			CommonFunctions.waitForPageLoaded();
			//Select Currency
			//CommonFunctions.selectFromDropDownByVisibleText(currency, data[39]);
			CommonFunctions.selectFromDropDownByIndex(currency, 2);
			CommonFunctions.waitForPageLoaded();
		    /*SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");*/
			//click on Create
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame")); 
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(CI253.AddRowsButton));
			}
			else if (data[38].contains("BOM\\Materials\\Product\\Retail Item\\Vendor"))
			{
			//CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
			CommonFunctions.waitForPageLoaded();
			//Select Wave
			//CommonFunctions.selectFromDropDownByVisibleText(wave, data[34]);
			CommonFunctions.selectFromDropDownByIndex(wave, 1);
			CommonFunctions.waitForPageLoaded();
			//Select Currency
			//CommonFunctions.selectFromDropDownByVisibleText(currency, data[39]);
			CommonFunctions.selectFromDropDownByIndex(currency, 2);
			CommonFunctions.waitForPageLoaded();
				//Wait for Factory
				CommonFunctions.waitForElementTobeClickable(SourcingConfig.factory);
				//Click on Factory
				CommonFunctions.clickButtonOrLink(SourcingConfig.factory, "link", "Factory field from Create BOM page");
				CommonFunctions.waitForPageLoaded();
				Set set = SeleniumDriver.driver.getWindowHandles();
				Iterator iter = set.iterator();
				String parent = (java.lang.String) iter.next();
				String child = (java.lang.String) iter.next();
				SeleniumDriver.driver.switchTo().window(child);
				CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'(choose)')]"), "Supplier selected");
				SeleniumDriver.driver.switchTo().window(parent);

				SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame")); 
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.visibilityOfElementLocated(CI253.AddRowsButton));
			}
		
			
		}

		catch(Exception e){
			fail=true;
			log.error("Exception in fillCreateBOMpc48()", e);
			//return "";
			throw e;
		}
		return BOMname;
	}
    
    //Create Internal Retail Item BOM
    public static String[] pc48createRetailIntBOM(String[] data) throws Exception{
		try{
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[38]);
			//CommonFunctions.waitForPageLoaded();
			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			CommonFunctions.waitForPageLoaded();
			BOMnameRetail="InWork"+CommonFunctions.getRandomString(4);

			//Create BOM page
			BOMnameInWorkRetail1 = fillCreateBOMpc48(data);
			CommonFunctions.waitForPageLoaded();
			Thread.sleep(1000);
			
			if(data[38].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[38].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")))
				
			{ 
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);
				//Plastics-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewPlastics);
				   CommonFunctions.clickButtonOrLink(imgViewPlastics, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View");
			   //Enter unit Price-Plastics
			   wait.until(ExpectedConditions.visibilityOfElementLocated(cellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(cellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(inputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(inputUnitPriceIE, data[40],"Unit Price Value for Plastics");
			   //Enter usage per K-Plastics
			   CommonFunctions.waitForElementTobeClickable(CI253.cellUsagePerK);
			   CommonFunctions.clickButtonOrLink(CI253.cellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(CI253.inputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(CI253.inputUsagePerK, data[41],"Usage Per K Value for plastics");
			   //Enter Product Markup-Plastics
			   CommonFunctions.waitForElementTobeClickable(plasticCellMarkup);
			   CommonFunctions.clickButtonOrLink(plasticCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(plasticInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(plasticInputMarkup, data[79],"Markup Value for plastics");
			
			   
			   
				 //Chemical-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewChemical);
				   CommonFunctions.clickButtonOrLink(imgViewChemical, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); 
			   //Enter unit Price-Chemicals
			   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(chemicalCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(chemicalInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUnitPriceIE, data[42],"Unit Price Value for Chemicals");
			   CommonFunctions.waitForPageLoaded();
			   //Enter usage per K-Chemicals
			   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(chemicalCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(chemicalInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUsagePerK, data[43],"Usage Per K Value for Chemicals");
			   //Enter Product Markup-Chemicals
			   CommonFunctions.waitForElementTobeClickable(chemicalCellMarkup);
			   CommonFunctions.clickButtonOrLink(chemicalCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(chemicalInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(chemicalInputMarkup, data[80],"Markup Value for Chemicals");

			   
			   
				 //Purchased-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewPurchased);
				   CommonFunctions.clickButtonOrLink(imgViewPurchased, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); 
			   //Enter unit Price-Purchased Parts
			   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(purchasedCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(purchasedInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUnitPriceIE, data[44],"Unit Price Value for Purchased Parts");
			   //Enter usage per K-Purchased Parts
			   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(purchasedCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(purchasedInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUsagePerK, data[45],"Usage Per K Value for Purchased Parts");
			   //Enter Product Markup-Purchased Parts
			   CommonFunctions.waitForElementTobeClickable(purchasedCellMarkup);
			   CommonFunctions.clickButtonOrLink(purchasedCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(purchasedInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(purchasedInputMarkup, data[81],"Markup Value for Purchased");

			   
			   
				 //Electronics-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewElectronics);
				   CommonFunctions.clickButtonOrLink(imgViewElectronics, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); 
			   //Enter unit Price-Electronics
			   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(electCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(electInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(electInputUnitPriceIE, data[46],"Unit Price Value for Electronics");
			   //Enter usage per K-Electronics
			   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(electCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(electInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(electInputUsagePerK, data[47],"Usage Per K Value for Electronics");
			   //Enter Product Markup-Electronics
			   CommonFunctions.waitForElementTobeClickable(electCellMarkup);
			   CommonFunctions.clickButtonOrLink(electCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(electInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(electInputMarkup, data[82],"Markup Value for Electronics");

			   
			   
			  //Soft Goods-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewSG);
				   CommonFunctions.clickButtonOrLink(imgViewSG, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); 
			   //Enter unit Soft Goods
			   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(sGCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(sGInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(sGInputUnitPriceIE, data[48],"Unit Price Value for Soft Goods");
			   //Enter usage per K-Soft Goods
			   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(sGCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(sGInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(sGInputUsagePerK, data[49],"Usage Per K Value for Soft Goods");
			   //Enter Product Markup-Soft Goods
			   CommonFunctions.waitForElementTobeClickable(sGCellMarkup);
			   CommonFunctions.clickButtonOrLink(sGCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(sGInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(sGInputMarkup, data[83],"Markup Value for Soft Goods");
               
			   
			   
			  //Pacakaging-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewPackaging);
				   CommonFunctions.clickButtonOrLink(imgViewPackaging, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); 
			   //Enter unit Pacakaging
			   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(packagingCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(packagingInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(packagingInputUnitPriceIE, data[50],"Unit Price Value for Pacakaging");
			   //Enter usage per Packaging
			   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(packagingCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(packagingInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(packagingInputUsagePerK, data[51],"Usage Per K Value for Pacakaging");
			   //Enter Product Markup-Soft Goods
			   CommonFunctions.waitForElementTobeClickable(packagingCellMarkup);
			   CommonFunctions.clickButtonOrLink(packagingCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(packagingInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(packagingInputMarkup, data[84],"Markup Value for Pacakaging");
               
			   
			   
				 //General Decor Labour-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewGenDecLabor);
				   CommonFunctions.clickButtonOrLink(imgViewGenDecLabor, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); 
			   //Enter unit General Decor Labour
			   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(generalLabourCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(generalLabourInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUnitPriceIE, data[52],"Unit Price Value for General/Deco Labor");
			   //Enter usage per General Decor Labour
			   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(generalLabourCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(generalLabourInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUsagePerK, data[53],"Usage Per K Value for General/Deco Labor");
			   //Enter Product Markup-General Decor Labour
			   CommonFunctions.waitForElementTobeClickable(generalCellMarkup);
			   CommonFunctions.clickButtonOrLink(generalCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(generalInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(generalInputMarkup, data[85],"Markup Value for General/Deco Labor");

			   
			   
			  //Molding Labour-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewMoldingLabor);
				   CommonFunctions.clickButtonOrLink(imgViewMoldingLabor, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); 
			   //Enter unit Molding Labour
			   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUnitPriceIE));
			   CommonFunctions.clickButtonOrLink(moldingCellUnitPriceIE, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(moldingInputUnitPriceIE);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputUnitPriceIE, data[54],"Unit Price Value for Molding Labor");
			   //Enter usage per Molding Labour
			   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(moldingCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(moldingInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputUsagePerK, data[55],"Usage Per K Value for Molding Labor");
			   //Enter Product Markup-Molding Labour
			   CommonFunctions.waitForElementTobeClickable(moldingCellMarkup);
			   CommonFunctions.clickButtonOrLink(moldingCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(moldingInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputMarkup, data[86],"Markup Value for Molding");
			   //Molding Labour-Full Molding Labor View
			   CommonFunctions.waitForElementTobeClickable(imgViewMoldingLabor);
			   CommonFunctions.clickButtonOrLink(imgViewMoldingLabor, "image", "View for Plastics");
			   CommonFunctions.waitForElementTobeClickable(BOMTablesViewMold);
			   CommonFunctions.clickButtonOrLink(BOMTablesViewMold, "link", "Internal Full Molding Labor View");
			   //Enter Cycle Time for Molding Labor Section
			   CommonFunctions.waitForElementTobeClickable(moldingCycleTime);
			   CommonFunctions.clickButtonOrLink(moldingCycleTime, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(moldingInputCycleTime);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputCycleTime, data[88],"Cycle Time for Molding");
			   //Enter Up for Molding Labor Section
			   CommonFunctions.waitForElementTobeClickable(moldingUp);
			   CommonFunctions.clickButtonOrLink(moldingUp, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(moldingInputUp);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputUp, data[89],"UP for Molding");
			   //Enter Cost Per Hr for Molding Labor Section
			   CommonFunctions.waitForElementTobeClickable(moldingCostPerHr);
			   CommonFunctions.clickButtonOrLink(moldingCostPerHr, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(moldingInputCostPerHr);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputCostPerHr, data[90],"Cost Per Hr for Molding");
			}
			
			CommonFunctions.waitForPageLoaded();
			Thread.sleep(1000);
			//Click button btnSaveAndCheckIn
			CommonFunctions.waitForElementTobeClickable(ExternalBOM.btnSaveAndCheckIn);
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to default frame 	 	
			driver.switchTo().defaultContent();
			
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
	        CommonFunctions.waitForPageLoaded();

			CommonFunctions.waitForVisibilityOfElement(headerAttributes);
			//	String strBOM=driver.findElement(BOMId).getText();
			String strBOMInWork1=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
			strBOMInWorkRetail= strBOMInWork1.trim();
			System.out.println("BOM name in detail page after check in: " + strBOMInWorkRetail);

		}catch(Exception e){

			fail=true;
			log.error("Exception in pc48createRetailIntBOM()", e);
			throw e;
				}
		return new String[] {strBOMInWorkRetail, BOMnameInWorkRetail1};
	}
    //Create Vendor Retail Item BOM
  //Create Internal Retail Item BOM
    public static String[] createRetailVendorBOM(String[] data) throws Exception{
		try{
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[38]);
			//CommonFunctions.waitForPageLoaded();
			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			CommonFunctions.waitForPageLoaded();
			BOMnameRetail="InWork"+CommonFunctions.getRandomString(4);

			//Create BOM page
			BOMnameInWorkRetail1 = fillCreateBOMpc48(data);
			CommonFunctions.waitForPageLoaded();
			Thread.sleep(1000);
			
			if(data[38].contains("BOM\\Materials\\Product\\Retail Item\\Vendor"))
				
			{ 
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);
				/*//Plastics-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewPlastics);
				   CommonFunctions.clickButtonOrLink(imgViewPlastics, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View");*/
			   //Enter unit Price-Plastics
			   wait.until(ExpectedConditions.visibilityOfElementLocated(cellUnitPrice));
			   CommonFunctions.clickButtonOrLink(cellUnitPrice, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(inputUnitPrice);
			   CommonFunctions.enterTextInTextboxUpdated(inputUnitPrice, data[40],"Unit Price Value for Plastics");
			   //Enter usage per K-Plastics
			   CommonFunctions.waitForElementTobeClickable(CI253.cellUsagePerK);
			   CommonFunctions.clickButtonOrLink(CI253.cellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(CI253.inputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(CI253.inputUsagePerK, data[40],"Usage Per K Value for plastics");
			   //Enter Product Markup-Plastics
			   CommonFunctions.waitForElementTobeClickable(plasticCellMarkup);
			   CommonFunctions.clickButtonOrLink(plasticCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(plasticInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(plasticInputMarkup, data[40],"Markup Value for plastics");
			
			   
			   
				 /*//Chemical-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewChemical);
				   CommonFunctions.clickButtonOrLink(imgViewChemical, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); */
			   //Enter unit Price-Chemicals
			   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUnitPrice));
			   CommonFunctions.clickButtonOrLink(chemicalCellUnitPrice, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(chemicalInputUnitPrice);
			   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUnitPrice, data[40],"Unit Price Value for Chemicals");
			   CommonFunctions.waitForPageLoaded();
			   //Enter usage per K-Chemicals
			   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(chemicalCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(chemicalInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUsagePerK, data[40],"Usage Per K Value for Chemicals");
			   //Enter Product Markup-Chemicals
			   CommonFunctions.waitForElementTobeClickable(chemicalCellMarkup);
			   CommonFunctions.clickButtonOrLink(chemicalCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(chemicalInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(chemicalInputMarkup, data[40],"Markup Value for Chemicals");

			   
			   
				 /*//Purchased-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewPurchased);
				   CommonFunctions.clickButtonOrLink(imgViewPurchased, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); */
			   //Enter unit Price-Purchased Parts
			   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUnitPrice));
			   CommonFunctions.clickButtonOrLink(purchasedCellUnitPrice, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(purchasedInputUnitPrice);
			   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUnitPrice, data[40],"Unit Price Value for Purchased Parts");
			   //Enter usage per K-Purchased Parts
			   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(purchasedCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(purchasedInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUsagePerK, data[40],"Usage Per K Value for Purchased Parts");
			   //Enter Product Markup-Purchased Parts
			   CommonFunctions.waitForElementTobeClickable(purchasedCellMarkup);
			   CommonFunctions.clickButtonOrLink(purchasedCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(purchasedInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(purchasedInputMarkup, data[40],"Markup Value for Purchased");

			   
			   
				 /*//Electronics-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewElectronics);
				   CommonFunctions.clickButtonOrLink(imgViewElectronics, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); */
			   //Enter unit Price-Electronics
			   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUnitPrice));
			   CommonFunctions.clickButtonOrLink(electCellUnitPrice, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(electInputUnitPrice);
			   CommonFunctions.enterTextInTextboxUpdated(electInputUnitPrice, data[40],"Unit Price Value for Electronics");
			   //Enter usage per K-Electronics
			   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(electCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(electInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(electInputUsagePerK, data[40],"Usage Per K Value for Electronics");
			   //Enter Product Markup-Electronics
			   CommonFunctions.waitForElementTobeClickable(electCellMarkup);
			   CommonFunctions.clickButtonOrLink(electCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(electInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(electInputMarkup, data[40],"Markup Value for Electronics");

			   
			   
			 /* //Soft Goods-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewSG);
				   CommonFunctions.clickButtonOrLink(imgViewSG, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); */
			   //Enter unit Soft Goods
			   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUnitPrice));
			   CommonFunctions.clickButtonOrLink(sGCellUnitPrice, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(sGInputUnitPrice);
			   CommonFunctions.enterTextInTextboxUpdated(sGInputUnitPrice, data[40],"Unit Price Value for Soft Goods");
			   //Enter usage per K-Soft Goods
			   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(sGCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(sGInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(sGInputUsagePerK, data[40],"Usage Per K Value for Soft Goods");
			   //Enter Product Markup-Soft Goods
			   CommonFunctions.waitForElementTobeClickable(sGCellMarkup);
			   CommonFunctions.clickButtonOrLink(sGCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(sGInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(sGInputMarkup, data[40],"Markup Value for Soft Goods");
               
			   
			   
			  /*//Pacakaging-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewPackaging);
				   CommonFunctions.clickButtonOrLink(imgViewPackaging, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); */
			   //Enter unit Pacakaging
			   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUnitPrice));
			   CommonFunctions.clickButtonOrLink(packagingCellUnitPrice, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(packagingInputUnitPrice);
			   CommonFunctions.enterTextInTextboxUpdated(packagingInputUnitPrice, data[40],"Unit Price Value for Pacakaging");
			   //Enter usage per Packaging
			   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(packagingCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(packagingInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(packagingInputUsagePerK, data[40],"Usage Per K Value for Pacakaging");
			   //Enter Product Markup-Soft Goods
			   CommonFunctions.waitForElementTobeClickable(packagingCellMarkup);
			   CommonFunctions.clickButtonOrLink(packagingCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(packagingInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(packagingInputMarkup, data[40],"Markup Value for Pacakaging");
               
			   
			   
				/* //General Decor Labour-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewGenDecLabor);
				   CommonFunctions.clickButtonOrLink(imgViewGenDecLabor, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); */
			   //Enter unit General Decor Labour
			   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUnitPrice));
			   CommonFunctions.clickButtonOrLink(generalLabourCellUnitPrice, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(generalLabourInputUnitPrice);
			   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUnitPrice, data[40],"Unit Price Value for General/Deco Labor");
			   //Enter usage per General Decor Labour
			   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(generalLabourCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(generalLabourInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUsagePerK, data[40],"Usage Per K Value for General/Deco Labor");
			   //Enter Product Markup-General Decor Labour
			   CommonFunctions.waitForElementTobeClickable(generalCellMarkup);
			   CommonFunctions.clickButtonOrLink(generalCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(generalInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(generalInputMarkup, data[40],"Markup Value for General/Deco Labor");

			   
			   
			  /*//Molding Labour-Coasting(full) view
				   CommonFunctions.waitForElementTobeClickable(imgViewMoldingLabor);
				   CommonFunctions.clickButtonOrLink(imgViewMoldingLabor, "image", "View for Plastics");
				   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
				   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Internal Full View"); */
			   //Enter unit Molding Labour
			   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUnitPrice));
			   CommonFunctions.clickButtonOrLink(moldingCellUnitPrice, "table cell", "unitPrice");
			   CommonFunctions.waitForElementTobeClickable(moldingInputUnitPrice);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputUnitPrice, data[40],"Unit Price Value for Molding Labor");
			   //Enter usage per Molding Labour
			   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUsagePerK));
			   CommonFunctions.clickButtonOrLink(moldingCellUsagePerK, "table cell", "unitPerK");
			   CommonFunctions.waitForElementTobeClickable(moldingInputUsagePerK);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputUsagePerK, data[40],"Usage Per K Value for Molding Labor");
			   //Enter Product Markup-Molding Labour
			   CommonFunctions.waitForElementTobeClickable(moldingCellMarkup);
			   CommonFunctions.clickButtonOrLink(moldingCellMarkup, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(moldingInputMarkup);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputMarkup, data[40],"Markup Value for Molding");
			   
			   //Molding Labour-Full Molding Labor View
			   CommonFunctions.waitForElementTobeClickable(imgViewMoldingLabor);
			   CommonFunctions.clickButtonOrLink(imgViewMoldingLabor, "image", "View for Plastics");
			   CommonFunctions.waitForElementTobeClickable(BOMTablesViewMoldVendor);
			   CommonFunctions.clickButtonOrLink(BOMTablesViewMoldVendor, "link", "Vendor Full Molding Labor View");
			   CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);
			   
			   //Enter Up for Molding Labor Section
			   CommonFunctions.waitForElementTobeClickable(moldingUp);
			   CommonFunctions.clickButtonOrLink(moldingUp, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(moldingInputUp);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputUp, data[89],"UP for Molding");
			   //Enter Cost Per Hr for Molding Labor Section
			   CommonFunctions.waitForElementTobeClickable(moldingCostPerHrV);
			   CommonFunctions.clickButtonOrLink(moldingCostPerHrV, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(moldingInputCostPerHrV);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputCostPerHrV, data[90],"Cost Per Hr for Molding");
			 //Enter Cycle Time for Molding Labor Section
			   CommonFunctions.waitForElementTobeClickable(moldingCycleTimeV);
			   CommonFunctions.clickButtonOrLink(moldingCycleTimeV, "table cell", "Markup");
			   CommonFunctions.waitForElementTobeClickable(moldingInputCycleTimeV);
			   CommonFunctions.enterTextInTextboxUpdated(moldingInputCycleTimeV, data[88],"Cycle Time for Molding");
			}
			
			CommonFunctions.waitForPageLoaded();
			Thread.sleep(1000);
			//Click button btnSaveAndCheckIn
			CommonFunctions.waitForElementTobeClickable(ExternalBOM.btnSaveAndCheckIn);
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to default frame 	 	
			driver.switchTo().defaultContent();
			
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
	        CommonFunctions.waitForPageLoaded();

			CommonFunctions.waitForVisibilityOfElement(headerAttributes);
			//	String strBOM=driver.findElement(BOMId).getText();
			String strBOMInWork1=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
			strBOMInWorkRetail= strBOMInWork1.trim();
			System.out.println("BOM name in detail page after check in: " + strBOMInWorkRetail);

		}catch(Exception e){

			fail=true;
			log.error("Exception in pc48createRetailIntBOM()", e);
			throw e;
				}
		return new String[] {strBOMInWorkRetail, BOMnameInWorkRetail1};
	}
    //Get BOM Values:
  //Update BOM values
	public static boolean getBOMValuesInternalRetail(String [] data) throws Exception{
		try{
			 //Clicking on Header Attribute + Icon
		    wait.until(ExpectedConditions.visibilityOfElementLocated(HeaderAttributesPlusIcon));
            CommonFunctions.clickButtonOrLink(HeaderAttributesPlusIcon, "Image", "HeaderAttributesPlusIcon");
            //getting Total BOM Cost in BOM to Compare
            wait.until(ExpectedConditions.visibilityOfElementLocated(strTotalBOMCostValueBOM));
            totalBOMCostInBOM =driver.findElement(strTotalBOMCostValueBOM).getText();
            log.info("totalBOMCostInBOM is "+totalBOMCostInBOM);
            //getting Plastic in BOM to Compare
            wait.until(ExpectedConditions.visibilityOfElementLocated(strPlasticValueBOM));
            totalPlasticCostInBOM =driver.findElement(strPlasticValueBOM).getText();
            log.info("totalPlasticCostInBOM is "+totalPlasticCostInBOM);
            //getting Chemical in BOM to Compare
            wait.until(ExpectedConditions.visibilityOfElementLocated(strChemicalValueBOM));
            totalChemicalCostInBOM =driver.findElement(strChemicalValueBOM).getText();
            log.info("totalChemicalCostInBOM is "+totalChemicalCostInBOM);
            //getting Purchased in BOM to Compare
            wait.until(ExpectedConditions.visibilityOfElementLocated(strPurchasedValueBOM));
            totalPurchasedCostInBOM =driver.findElement(strPurchasedValueBOM).getText();
            log.info("totalPurchasedCostInBOM is "+totalPurchasedCostInBOM);
            //getting Electronics in BOM to Compare
            wait.until(ExpectedConditions.visibilityOfElementLocated(strElectronicValueBOM));
            totalElectronicCostInBOM =driver.findElement(strElectronicValueBOM).getText();
            log.info("totalElectronicCostInBOM is "+totalElectronicCostInBOM);
            //getting Soft Goods in BOM to Compare
            wait.until(ExpectedConditions.visibilityOfElementLocated(strSGValueBOM));
            totalSGCostInBOM =driver.findElement(strSGValueBOM).getText();
            log.info("totalSGCostInBOM is "+totalSGCostInBOM);
            //getting Packaging in BOM to Compare
            wait.until(ExpectedConditions.visibilityOfElementLocated(strPackagingValueBOM));
            totalPackagingCostInBOM =driver.findElement(strPackagingValueBOM).getText();
            log.info("totalPackagingCostInBOM is "+totalPackagingCostInBOM);
            //getting G/D Labor in BOM to Compare
            wait.until(ExpectedConditions.visibilityOfElementLocated(strGenDecLabValueBOM));
            totalGDLaborCostInBOM =driver.findElement(strGenDecLabValueBOM).getText();
            log.info("totalGDLaborCostInBOM is "+totalGDLaborCostInBOM);
            //getting Molding Labor Cost in BOM to Compare
            wait.until(ExpectedConditions.visibilityOfElementLocated(strMoldingLabValueBOM));
            totalMoldingLaborCostInBOM =driver.findElement(strMoldingLabValueBOM).getText();
            log.info("totalMoldingLaborCostInBOM is "+totalMoldingLaborCostInBOM);
            //getting totalMarkUpCost in BOM to Compare
            wait.until(ExpectedConditions.visibilityOfElementLocated(strProductMarkupBOM));
            totalMarkUpCostInBOM=driver.findElement(strProductMarkupBOM).getText();
            log.info("totalMarkUpCostInBOM is "+totalMarkUpCostInBOM);
            }
		catch(Exception e){
			fail=true;
			log.error("Exception in SelectBOM"+e);
			throw e;
		}
		return true;
	}
    	     
	//Associate BOM to Internal Retail Item
	public static boolean TC01_PC48_AssociateBOMToInternalRetailItemCS(String [] data) throws Exception{
		try{
			/***
			 * Prerequisites
			 * 1.Create Product : Retail Item
			 * 2.Create Internal Retail Item BOM
			 * 3.Create Internal Retail Item CS
			 */
			
			//********Create Product : Retail Item*********
			CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[6],data[7],data[8],data[9],data[10],data[11],
			data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
			data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
			//Navigate to Details Tab
			NavigateToDetailsTab(data);
			prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
			log.info("*******The Retail PRODUCT NUMBER Created is********"+prodNum1);
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("contentframe");
		   
		    //Wait for Season dropdown 
		    wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
		    CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
		    CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
			CommonFunctions.waitForPageLoaded();
			//Wait for Specification tab
			wait.until(ExpectedConditions.visibilityOfElementLocated(specificationtab));
			//Click on Specification tab
			CommonFunctions.clickButtonOrLink(specificationtab, "Specification tab");
			//Wait for Source dropdown
			CommonFunctions.waitForElementTobeClickable(SourceDropDown);
			//Click on Source dropdown
			CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
			CommonFunctions.waitForPageLoaded();
		    
			//Create Specification
			CommonProjectFunctions.Create_Specifications(data[34],data[35]);
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
			//Select Specification
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
			Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded();
			InternalBOMSoftG.Create_Colorway(data);
			
			//Navigating to product page  
			navigateToProduct(data);
			//Search Product
            CommonProjectFunctions.searchProduct(prodNum1);
			CommonFunctions.waitForPageLoaded();
			//Navigate To materials tab
			navigateToMaterialTabThroughSideBar(data);
			//Select Season
			CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
			CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
			CommonFunctions.waitForPageLoaded();
			//Select Specification
			CommonFunctions.waitForElementTobeClickable(Specifications_old.specificationdropdown);
			CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
			CommonFunctions.waitForPageLoaded();
			//Select Colorway
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
			CommonFunctions.waitForPageLoaded();
			//Create Vendor Product BOM
			pc48createRetailIntBOM(data);
			//Getting a BOM Values
			getBOMValuesInternalRetail(data);
			//Navigating to Costing Tab
			NavigateToCostingTabpc48(data);
			//Create Retail Item Internal Cost sheet
			CreateInternalRetailCS_TC01(data);
			//get Vendor Cost Sheet Details
			getInternalRetailCostSheetDetails(data);
			
	       
		}
		catch(Exception e){
					fail=true;
					log.error("Exception in pc48AssociateBOMToIntRetailCS "+e);
					throw e;
					}
				return true;
	}
	//PC49 Validations
	 public static boolean pc49assertionVerficationAfterUpdation(String [] data) throws Exception{
	    	try{
	
			
			//Assertion Verification of BOM naming format
			CommonFunctions.waitForElementTobeClickable(cSBOMName);
			GettingText(cSBOMName);
			CommonFunctions.AssertEqualsVerification(ActualValue, data[159], "Actual And Expected Cost Sheet BOM Name Are Not Matched.Assertion Failed.Please check");
			log.info("*****Verified BOM Name*****");
			//Assertion Verification of BOM Iteration
			CommonFunctions.waitForElementTobeClickable(cSBOMIteration);
			GettingText(cSBOMIteration);
			CommonFunctions.AssertEqualsVerification(ActualValue, data[60], "Actual And Expected Cost Sheet BOM Iteration Are Not Matched.Assertion Failed.Please check");
			log.info("*****Verified BOM Iteration*****");
			//Assertion Verification of CurrencyConversionRate of HKD
			CommonFunctions.waitForElementTobeClickable(currencyConversionRate);
			GettingText(currencyConversionRate);
			CommonFunctions.AssertEqualsVerification(ActualValue, data[69], "Actual And Expected Cost Sheet CurrencyConversionRate Are Not Matched.Assertion Failed.Please check");
			log.info("*****Verified Currency Conversion Rate*****");
			//Assertion Verification of MasterCartonPackagingMaterialUSDValue of HKD
			GettingText(masterCartonPackagingMaterialUSDValue);
			CommonFunctions.AssertEqualsVerification(ActualValue, data[73], "Actual And Expected Cost Sheet MasterCartonPackagingMaterialUSDValue Are Not Matched.Assertion Failed.Please check");
			log.info("*****Verified Master Carton Packaging Material*****");
			//Assertion Verification of  MasterCartonLaborCostUSDr of HKD
			CommonFunctions.waitForElementTobeClickable(masterCartonLaborCostUSD);
			GettingText(masterCartonLaborCostUSD);
			log.info("*****Verified Master Carton Labor Cost*****");
			CommonFunctions.AssertEqualsVerification(ActualValue, data[74], "Actual And Expected Cost Sheet MasterCartonLaborCostUSD Are Not Matched.Assertion Failed.Please check");
			//Assertion Verification of  Miscelleneous Material
			CommonFunctions.waitForElementTobeClickable(miscelleneousMaterialValue);
			GettingText(miscelleneousMaterialValue);
			CommonFunctions.AssertEqualsVerification(ActualValue, data[75], "Actual And Expected Cost Sheet MiscelleneousMaterialValueAre Not Matched.Assertion Failed.Please check");
			log.info("*****Verified Miscelleneous*****");
			//Assertion Verification of  ProductMarkUpValue of HKD
			CommonFunctions.waitForElementTobeClickable(productMarkUpValue);
			GettingText(productMarkUpValue);
			CommonFunctions.AssertEqualsVerification(ActualValue, data[87], "Actual And Expected Cost Sheet ProductMarkUpValue Are Not Matched.Assertion Failed.Please check");
			log.info("*****Verified Product MarkUp Value*****");
		}
	    	catch(Exception e){

	    		fail=true;
	    		log.error("Exception in pc49assertionVerficationAfterUpdation"+e);
	    		throw e;
	    	}
	    	return true;
	}
	//PC50 Validations
	 public static boolean pc50assertionVerficationAfterUpdation(String [] data) throws Exception{
	    	try{
	
			
			//Assertion Verification of BOM naming format
			CommonFunctions.waitForElementTobeClickable(cSBOMName);
			GettingText(cSBOMName);
			CommonFunctions.AssertEqualsVerification(ActualValue, data[159], "Actual And Expected Cost Sheet BOM Name Are Not Matched.Assertion Failed.Please check");
			log.info("****Verified BOM Name****");
			//Assertion Verification of BOM Iteration
			CommonFunctions.waitForElementTobeClickable(cSBOMIteration);
			GettingText(cSBOMIteration);
			CommonFunctions.AssertEqualsVerification(ActualValue, data[60], "Actual And Expected Cost Sheet BOM Iteration Are Not Matched.Assertion Failed.Please check");
			log.info("****Verified BOM Iteration****");
			//Assertion Verification of Quote Currency
    		CommonFunctions.waitForElementTobeClickable(strQuoteCurrency);
    		GettingText(strQuoteCurrency);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[39], "Actual And Expected Cost Sheet Quote Currency Are Not Matched.Assertion Failed.Please check");
    		log.info("****Verified Quote Currency****");
    		//Assertion Verification of CurrencyConversionRate of HKD
			CommonFunctions.waitForElementTobeClickable(currencyConversionRate);
			GettingText(currencyConversionRate);
			CommonFunctions.AssertEqualsVerification(ActualValue, data[69], "Actual And Expected Cost Sheet CurrencyConversionRate Are Not Matched.Assertion Failed.Please check");
			log.info("****Verified Currency Conversion Rate****");
			//Assertion Verification of Retail Item Cost
    		CommonFunctions.waitForElementTobeClickable(strRetailItemCostValue);
    		GettingText(strRetailItemCostValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[68], "Actual And Expected Cost Sheet Retail Item Cost Value Are Not Matched.Assertion Failed.Please check");
    		log.info("****Verified Retail Item Cost****");
    		//Assertion Verification of  Plastic material
    		CommonFunctions.waitForElementTobeClickable(strPlasticValue);
    		GettingText(strPlasticValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[58], "Actual And Expected Cost Sheet Plastic material Are Not Matched.Assertion Failed.Please check");
    		log.info("****Verified Plastic material****");
    		//Assertion Verification of  Purchased Material
    		CommonFunctions.waitForElementTobeClickable(strPurchasedValue);
    		GettingText(strPurchasedValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[62], "Actual And Expected Cost Sheet Purchased Material Not Matched.Assertion Failed.Please check");
    		log.info("****Verified Purchased Material****");
    		//Assertion Verification of  Soft Goods Material
    		CommonFunctions.waitForElementTobeClickable(strSGValue);
    		GettingText(strSGValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[64], "Actual And Expected Cost Sheet Soft Goods Material Are Not Matched.Assertion Failed.Please check");
    		log.info("****Verified Soft Goods Material****");
    		//Assertion Verification of  Chemical Material
    		CommonFunctions.waitForElementTobeClickable(strChemicalValue);
    		GettingText(strChemicalValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[61], "Actual And Expected Cost Sheet Chemical Material Are Not Matched.Assertion Failed.Please check");
    		log.info("****Verified Chemical Material****");
    		//Assertion Verification of  General / Deco Labor Cost
    		CommonFunctions.waitForElementTobeClickable(strGenDecLabValue);
    		GettingText(strGenDecLabValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[66], "Actual And Expected Cost Sheet General / Deco Labor Cost Are Not Matched.Assertion Failed.Please check");
    		log.info("****Verified General / Deco Labor****");
    		/*//Assertion Verification of  Molding Labor Cost
    		CommonFunctions.waitForElementTobeClickable(strMoldingLabValue);
    		GettingText(strMoldingLabValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[67], "Actual And Expected Cost Sheet Molding Labor Cost Are Not Matched.Assertion Failed.Please check");
    		*/
    		//Assertion Verification of  Electronic Material
    		CommonFunctions.waitForElementTobeClickable(strElectronicValue);
    		GettingText(strElectronicValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[63], "Actual And Expected Cost Sheet Electronic Material Are Not Matched.Assertion Failed.Please check");
    		log.info("****Verified Electronic Material****");
    		//Assertion Verification of  Packaging Material
    		CommonFunctions.waitForElementTobeClickable(strPackagingValue);
    		GettingText(strPackagingValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[65], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
    		log.info("****Verified Packaging Material****");
    		//Assertion Verification of  ProductMarkUpValue of HKD
			CommonFunctions.waitForElementTobeClickable(strProductMarkupCost);
			GettingText(strProductMarkupCost);
			CommonFunctions.AssertEqualsVerification(ActualValue, data[87], "Actual And Expected Cost Sheet ProductMarkUpValue Are Not Matched.Assertion Failed.Please check");
			log.info("****Verified Product MarkUp Value****");
		}
	    	catch(Exception e){

	    		fail=true;
	    		log.error("Exception in pc50assertionVerficationAfterUpdation"+e);
	    		throw e;
	    	}
	    	return true;
	}
	
	//Create Vendor Product BOM:PC49
	//Create Internal Retail Item BOM
		public static String[] pc49createRetailIntBOM(String[] data) throws Exception{
			try{
				
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
				//Click on ADD NEW BOM
				CommonFunctions.waitForElementTobeClickable(strAddNewBOM);
				CommonFunctions.clickButtonOrLink(strAddNewBOM,"btn", "ADD NEW BOM");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.enterTextInTextbox(BOMTypeId, data[38]);
				CommonFunctions.waitForPageLoaded();
				//Click Initialize BOM
				CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
				CommonFunctions.waitForPageLoaded();
				BOMnameRetail="InWork"+CommonFunctions.getRandomString(4);

				//Create BOM page
				BOMnameInWorkRetail1 = fillCreateBOM(data);
				CommonFunctions.waitForPageLoaded();
				Thread.sleep(1000);
				
				if(data[38].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[38].contains("BOM\\Materials\\Product\\Product\\Internal")))
				{ 
					/*CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
					CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);*/
				 //Enter unit Price-Plastics
				   wait.until(ExpectedConditions.visibilityOfElementLocated(cellUnitPrice));
				   CommonFunctions.clickButtonOrLink(cellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(inputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(inputUnitPrice, data[40],"Unit Price Value for Plastics");
				
				   //Enter usage per K-Plastics
				   CommonFunctions.waitForElementTobeClickable(CI253.cellUsagePerK);
				   CommonFunctions.clickButtonOrLink(CI253.cellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(CI253.inputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(CI253.inputUsagePerK, data[41],"Usage Per K Value for plastics");
				   
				 //Enter Product Markup-Plastics
				   CommonFunctions.waitForElementTobeClickable(plasticCellMarkup);
				   CommonFunctions.clickButtonOrLink(plasticCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(plasticInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(plasticInputMarkup, data[79],"Markup Value for plastics");
				
				   //Enter unit Price-Chemicals
				   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(chemicalCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(chemicalInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUnitPrice, data[42],"Unit Price Value for Chemicals");
				   CommonFunctions.waitForPageLoaded();
				   //Enter usage per K-Chemicals
				   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(chemicalCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(chemicalInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUsagePerK, data[43],"Usage Per K Value for Chemicals");
				   //Enter Product Markup-Chemicals
				   CommonFunctions.waitForElementTobeClickable(chemicalCellMarkup);
				   CommonFunctions.clickButtonOrLink(chemicalCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(chemicalInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(chemicalInputMarkup, data[80],"Markup Value for Chemicals");

				   //Enter unit Price-Purchased Parts
				   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(purchasedCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(purchasedInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUnitPrice, data[44],"Unit Price Value for Purchased Parts");
				   //Enter usage per K-Purchased Parts
				   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(purchasedCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(purchasedInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUsagePerK, data[45],"Usage Per K Value for Purchased Parts");
				   //Enter Product Markup-Purchased Parts
				   CommonFunctions.waitForElementTobeClickable(purchasedCellMarkup);
				   CommonFunctions.clickButtonOrLink(purchasedCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(purchasedInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(purchasedInputMarkup, data[81],"Markup Value for Purchased");

				   //Enter unit Price-Electronics
				   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(electCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(electInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(electInputUnitPrice, data[46],"Unit Price Value for Electronics");
				   //Enter usage per K-Electronics
				   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(electCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(electInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(electInputUsagePerK, data[47],"Usage Per K Value for Electronics");
				   //Enter Product Markup-Electronics
				   CommonFunctions.waitForElementTobeClickable(electCellMarkup);
				   CommonFunctions.clickButtonOrLink(electCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(electInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(electInputMarkup, data[82],"Markup Value for Electronics");

				   //Enter unit Soft Goods
				   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(sGCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(sGInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(sGInputUnitPrice, data[48],"Unit Price Value for Soft Goods");
				   //Enter usage per K-Soft Goods
				   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(sGCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(sGInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(sGInputUsagePerK, data[49],"Usage Per K Value for Soft Goods");
				   //Enter Product Markup-Soft Goods
				   CommonFunctions.waitForElementTobeClickable(sGCellMarkup);
				   CommonFunctions.clickButtonOrLink(sGCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(sGInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(sGInputMarkup, data[83],"Markup Value for Soft Goods");

				   //Enter unit Pacakaging
				   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(packagingCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(packagingInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(packagingInputUnitPrice, data[50],"Unit Price Value for Pacakaging");
				   //Enter usage per Packaging
				   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(packagingCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(packagingInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(packagingInputUsagePerK, data[51],"Usage Per K Value for Pacakaging");
				   //Enter Product Markup-Soft Goods
				   CommonFunctions.waitForElementTobeClickable(packagingCellMarkup);
				   CommonFunctions.clickButtonOrLink(packagingCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(packagingInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(packagingInputMarkup, data[84],"Markup Value for Pacakaging");

				   //Enter unit General Decor Labour
				   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(generalLabourCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(generalLabourInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUnitPrice, data[52],"Unit Price Value for General/Deco Labor");
				   //Enter usage per General Decor Labour
				   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(generalLabourCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(generalLabourInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUsagePerK, data[53],"Usage Per K Value for General/Deco Labor");
				   //Enter Product Markup-General Decor Labour
				   CommonFunctions.waitForElementTobeClickable(generalCellMarkup);
				   CommonFunctions.clickButtonOrLink(generalCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(generalInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(generalInputMarkup, data[85],"Markup Value for General/Deco Labor");

				   //Enter unit Molding Labour
				   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUnitPrice));
				   CommonFunctions.clickButtonOrLink(moldingCellUnitPrice, "table cell", "unitPrice");
				   CommonFunctions.waitForElementTobeClickable(moldingInputUnitPrice);
				   CommonFunctions.enterTextInTextboxUpdated(moldingInputUnitPrice, data[54],"Unit Price Value for Molding Labor");
				   //Enter usage per Molding Labour
				   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUsagePerK));
				   CommonFunctions.clickButtonOrLink(moldingCellUsagePerK, "table cell", "unitPerK");
				   CommonFunctions.waitForElementTobeClickable(moldingInputUsagePerK);
				   CommonFunctions.enterTextInTextboxUpdated(moldingInputUsagePerK, data[55],"Usage Per K Value for Molding Labor");
				   //Enter Product Markup-Molding Labour
				   CommonFunctions.waitForElementTobeClickable(moldingCellMarkup);
				   CommonFunctions.clickButtonOrLink(moldingCellMarkup, "table cell", "Markup");
				   CommonFunctions.waitForElementTobeClickable(moldingInputMarkup);
				   CommonFunctions.enterTextInTextboxUpdated(moldingInputMarkup, data[86],"Markup Value for Molding");
				}
				
				else if((data[38].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")))
				{
					//CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
					CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);
					   
					  //Enter unit Price-Plastics
					
					   //Plastics-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewPlastics);
					   CommonFunctions.clickButtonOrLink(imgViewPlastics, "image", "View for Plastics");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View");
					   
					   wait.until(ExpectedConditions.visibilityOfElementLocated(CI253.cellUnitPrice));
					   CommonFunctions.clickButtonOrLink(CI253.cellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(CI253.inputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(CI253.inputUnitPrice, data[40],"Unit Price Value for Plastics");
					
					   //Enter usage per K-Plastics
					   CommonFunctions.waitForElementTobeClickable(CI253.cellUsagePerK);
					   CommonFunctions.clickButtonOrLink(CI253.cellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(CI253.inputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(CI253.inputUsagePerK, data[41],"Usage Per K Value for plastics");
					   
					   //Enter Product Markup-Plastics
					   CommonFunctions.waitForElementTobeClickable(plasticCellMarkup);
					   CommonFunctions.clickButtonOrLink(plasticCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(plasticInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(plasticInputMarkup, data[88],"Markup Value for plastics");
					
					   //Chemicals-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewChemical);
					   CommonFunctions.clickButtonOrLink(imgViewChemical, "image", "View for Chemicals");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Chemicals Costing Full View");
					   
					   //Enter unit Price-Chemicals
					   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(chemicalCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(chemicalInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUnitPrice, data[42],"Unit Price Value for Chemicals");
					   CommonFunctions.waitForPageLoaded();
					   //Enter usage per K-Chemicals
					   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(chemicalCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(chemicalInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUsagePerK, data[43],"Usage Per K Value for Chemicals");
					   //Enter Product Markup-Chemicals
					   CommonFunctions.waitForElementTobeClickable(chemicalCellMarkup);
					   CommonFunctions.clickButtonOrLink(chemicalCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(chemicalInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(chemicalInputMarkup, data[89],"Markup Value for Chemicals");
					   
					   //Purchased Parts-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewPurchased);
					   CommonFunctions.clickButtonOrLink(imgViewPurchased, "image", "View for Purchased Parts");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Purchased Parts Costing Full View");

					   //Enter unit Price-Purchased Parts
					   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(purchasedCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(purchasedInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUnitPrice, data[44],"Unit Price Value for Purchased Parts");
					   //Enter usage per K-Purchased Parts
					   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(purchasedCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(purchasedInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUsagePerK, data[45],"Usage Per K Value for Purchased Parts");
					   //Enter Product Markup-Purchased Parts
					   CommonFunctions.waitForElementTobeClickable(purchasedCellMarkup);
					   CommonFunctions.clickButtonOrLink(purchasedCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(purchasedInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(purchasedInputMarkup, data[90],"Markup Value for Purchased");
					   
					   //Electronics-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewElectronics);
					   CommonFunctions.clickButtonOrLink(imgViewElectronics, "image", "View for Electronics");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Electronics Costing Full View");

					   //Enter unit Price-Electronics
					   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(electCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(electInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(electInputUnitPrice, data[46],"Unit Price Value for Electronics");
					   //Enter usage per K-Electronics
					   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(electCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(electInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(electInputUsagePerK, data[47],"Usage Per K Value for Electronics");
					   //Enter Product Markup-Electronics
					   CommonFunctions.waitForElementTobeClickable(electCellMarkup);
					   CommonFunctions.clickButtonOrLink(electCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(electInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(electInputMarkup, data[91],"Markup Value for Electronics");
					   
					   //Soft Goods-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewSG);
					   CommonFunctions.clickButtonOrLink(imgViewSG, "image", "View for Electronics");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Soft Goods Costing Full View");

					   //Enter unit Soft Goods
					   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(sGCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(sGInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(sGInputUnitPrice, data[48],"Unit Price Value for Soft Goods");
					   //Enter usage per K-Soft Goods
					   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(sGCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(sGInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(sGInputUsagePerK, data[49],"Usage Per K Value for Soft Goods");
					   //Enter Product Markup-Soft Goods
					   CommonFunctions.waitForElementTobeClickable(sGCellMarkup);
					   CommonFunctions.clickButtonOrLink(sGCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(sGInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(sGInputMarkup, data[92],"Markup Value for Soft Goods");
					   
					   
					   //Pacakaging-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewPackaging);
					   CommonFunctions.clickButtonOrLink(imgViewPackaging, "image", "View for Pacakaging");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Pacakaging Costing Full View");
					   //Enter unit Pacakaging
					   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(packagingCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(packagingInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(packagingInputUnitPrice, data[50],"Unit Price Value for Pacakaging");
					   //Enter usage per Packaging
					   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(packagingCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(packagingInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(packagingInputUsagePerK, data[51],"Usage Per K Value for Pacakaging");
					   //Enter Product Markup-Soft Goods
					   CommonFunctions.waitForElementTobeClickable(packagingCellMarkup);
					   CommonFunctions.clickButtonOrLink(packagingCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(packagingInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(packagingInputMarkup, data[93],"Markup Value for Pacakaging");

					   //Gen/Deco Labor-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewGenDecLabor);
					   CommonFunctions.clickButtonOrLink(imgViewGenDecLabor, "image", "View for Gen/Deco Labor");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Gen/Deco Labor Costing Full View");
					   //Enter unit General Decor Labour
					   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(generalLabourCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(generalLabourInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUnitPrice, data[52],"Unit Price Value for General/Deco Labor");
					   //Enter usage per General Decor Labour
					   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(generalLabourCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(generalLabourInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUsagePerK, data[53],"Usage Per K Value for General/Deco Labor");
					   //Enter Product Markup-General Decor Labour
					   CommonFunctions.waitForElementTobeClickable(generalCellMarkup);
					   CommonFunctions.clickButtonOrLink(generalCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(generalInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(generalInputMarkup, data[94],"Markup Value for General/Deco Labor");
	                   
					   //Molding Labor-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewMoldingLabor);
					   CommonFunctions.clickButtonOrLink(imgViewMoldingLabor, "image", "View for Molding Labor");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Molding Labor Costing Full View");
					   //Enter unit Molding Labour
					   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(moldingCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(moldingInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(moldingInputUnitPrice, data[54],"Unit Price Value for Molding Labor");
					   //Enter usage per Molding Labour
					   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(moldingCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(moldingInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(moldingInputUsagePerK, data[55],"Usage Per K Value for Molding Labor");
					   //Enter Product Markup-Molding Labour
					   CommonFunctions.waitForElementTobeClickable(moldingCellMarkup);
					   CommonFunctions.clickButtonOrLink(moldingCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(moldingInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(moldingInputMarkup, data[95],"Markup Value for Molding");
				}
				
				else if((data[38].contains("BOM\\Materials\\Product\\Product\\Vendor")))
				{
					CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
					CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);

					//Master Carton Packaging-Coasting(full) view
					CommonFunctions.waitForElementTobeClickable(imgViewMaster);
					CommonFunctions.clickButtonOrLink(imgViewMaster, "image", "View for Master Carton");
					CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Packaging Costing Full View");
					
					//Enter unit Price -Master Carton Packaging
					wait.until(ExpectedConditions.visibilityOfElementLocated(mCPackagingCellUnitPrice));
					CommonFunctions.clickButtonOrLink(mCPackagingCellUnitPrice, "table cell", "unitPrice");
					CommonFunctions.waitForElementTobeClickable(mCPackagingInputUnitPrice);
					CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputUnitPrice, data[40],"Unit Price Value for Plastics");

					//Enter Usage per K-Master Carton Packaging
					CommonFunctions.waitForElementTobeClickable(mCPackagingCellUsagePerK);
					CommonFunctions.clickButtonOrLink(mCPackagingCellUsagePerK, "table cell", "unitPerK");
					CommonFunctions.waitForElementTobeClickable(mCPackagingInputUsagePerK);
					CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputUsagePerK, data[41],"Usage Per K Value for plastics");

					//Enter Markup - Master Carton Packaging
					wait.until(ExpectedConditions.visibilityOfElementLocated(mCPackagingCellMarkUp));
					CommonFunctions.clickButtonOrLink(mCPackagingCellMarkUp, "table cell", "Product Markup for Master Carton table");
					CommonFunctions.waitForElementTobeClickable(mCPackagingInputMarkUp);
					CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputMarkUp, data[76],"Product Markup for Master Carton table");
					
					//Labour-Costing(full) view
					CommonFunctions.waitForElementTobeClickable(imgViewLabour);
					CommonFunctions.clickButtonOrLink(imgViewLabour, "image", "View for Labour");
					CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					CommonFunctions.clickButtonOrLink(BOMTablesView, "link", " Labour Costing Full View");
					
					//Enter unit Price -Labor
					wait.until(ExpectedConditions.visibilityOfElementLocated(laborCellUnitPrice));
					CommonFunctions.clickButtonOrLink(laborCellUnitPrice, "table cell", "unitPrice");
					CommonFunctions.waitForElementTobeClickable(laborInputUnitPrice);
					CommonFunctions.enterTextInTextboxUpdated(laborInputUnitPrice, data[42],"Unit Price Value for Plastics");

					//Enter Usage per K-Labor
					CommonFunctions.waitForElementTobeClickable(laborCellUsagePerK);
					CommonFunctions.clickButtonOrLink(laborCellUsagePerK, "table cell", "unitPerK");
					CommonFunctions.waitForElementTobeClickable(laborInputUsagePerK);
					CommonFunctions.enterTextInTextboxUpdated(laborInputUsagePerK, data[43],"Usage Per K Value for plastics");

					//Enter Markup - Labor
					wait.until(ExpectedConditions.visibilityOfElementLocated(laborCellMarkUp));
					CommonFunctions.clickButtonOrLink(laborCellMarkUp, "table cell", "Product Markup for Labor table");
					CommonFunctions.waitForElementTobeClickable(laborInputMarkUp);
					CommonFunctions.enterTextInTextboxUpdated(laborInputMarkUp, data[77],"Product Markup for Labor table");
					
					
					
					// Miscellaneous-Costing(full) view
					CommonFunctions.waitForElementTobeClickable(imgViewMiscellaneous);
					CommonFunctions.clickButtonOrLink(imgViewMiscellaneous, "image", "View for  Miscellaneous");
					CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "  Miscellaneous Costing Full View");
					
					
					//Enter unit Price -Miscellaneous
					wait.until(ExpectedConditions.visibilityOfElementLocated(miscellaneousCellUnitPrice));
					CommonFunctions.clickButtonOrLink(miscellaneousCellUnitPrice, "table cell", "unitPrice");
					CommonFunctions.waitForElementTobeClickable(miscellaneousInputUnitPrice);
					CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputUnitPrice, data[42],"Unit Price Value for Miscellaneous");
					 
					//Enter Usage Per K- Miscellaneous
					CommonFunctions.waitForElementTobeClickable(miscellaneousCellUsagePerK);
					CommonFunctions.clickButtonOrLink(miscellaneousCellUsagePerK, "table cell", "unitPerK");
					CommonFunctions.waitForElementTobeClickable(miscellaneousInputUsagePerK);
					CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputUsagePerK, data[45],"Usage Per K Value for Miscellaneous");

					//Enter Markup - Miscellaneous
					wait.until(ExpectedConditions.visibilityOfElementLocated(miscellaneousCellMarkUp));
					CommonFunctions.clickButtonOrLink(miscellaneousCellMarkUp, "table cell", "Product Markup for Miscellaneous table");
					CommonFunctions.waitForElementTobeClickable(miscellaneousInputMarkUp);
					CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputMarkUp, data[78],"Product Markup for Miscellaneous table");
					
					   
					   
				}
				
				CommonFunctions.waitForPageLoaded();
				Thread.sleep(1000);
				//Click button btnSaveAndCheckIn
				CommonFunctions.waitForElementTobeClickable(ExternalBOM.btnSaveAndCheckIn);
				CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to default frame 	 	
				driver.switchTo().defaultContent();
				
				
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();

				CommonFunctions.waitForVisibilityOfElement(headerAttributes);
				//	String strBOM=driver.findElement(BOMId).getText();
				String strBOMInWork1=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
				strBOMInWorkRetail= strBOMInWork1.trim();
				System.out.println("BOM name in detail page after check in: " + strBOMInWorkRetail);

			}catch(Exception e){

				fail=true;
				log.error("Exception in pc49createRetailIntBOM()", e);
				throw e;
					}
			return new String[] {strBOMInWorkRetail, BOMnameInWorkRetail1};
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
		//Prerequisites for any type of product to be visible for vendor user
		public static boolean VendorProductPrerequisites(String [] data) throws Exception{
			try{
		//Navigate to Details Tab
		NavigateToDetailsTab(data);
		prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
		log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum1);
		//if(data[3].contains("TC09_PC07_CreateSourcingConfigUsingCopyOrLink")){
		//Add Source
		CommonProjectFunctions.AddSource("AEQUS",data[4]);
		//}
		/*else{
			CommonProjectFunctions.AddSource("WORLD TOYMAKER 1",data[4]);
		}*/
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
		CostingApprovalWF.updateSpecStatus(data);
		//Create Multiple Colorway
		CreateMultiple_Colorway();
		//Select Colorway
		CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
		CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
		CommonFunctions.waitForPageLoaded();
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in pc49associateBOMToVendorPrdCS "+e);
				throw e;
				}
			return true;
	}
		
		//Prerequisites for any type of product to be visible for vendor user
				public static boolean VendorProductPrerequisitesTC10(String [] data) throws Exception{
					try{
				//Navigate to Details Tab
				NavigateToDetailsTab(data);
				prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
				log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum1);
				//Add Source
				CommonProjectFunctions.AddSource("ADVENT",data[4]);
			    //Selecting Season and Source
				ClickSeasonAndSource(data);
				
				//Update the source to Approved state
				updateSource(data,"Conditionally Approved");
				//Navigate To specifications tab
				navigateToSpecificationTabThroughSideBar(data);
				//Adding a Specification
				AddSpecification(data);
				CommonFunctions.waitForPageLoaded();
		       //Update the Specification Status
				CostingApprovalWF.updateSpecStatus(data);
				//Create Multiple Colorway
				CreateMultiple_Colorway();
				//Select Colorway
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
				CommonFunctions.waitForPageLoaded();
					}
					catch(Exception e){
						fail=true;
						log.error("Exception in pc49associateBOMToVendorPrdCS "+e);
						throw e;
						}
					return true;
			}
		/*public static Boolean AddSourcingConfiguration(String sourcename) throws Exception{
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
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+sourcename+"')]/preceding::td[1]/a"), "Supplier selected");
				SeleniumDriver.driver.switchTo().window(parent);

				SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");

				//Sourcing Lead 
				CommonFunctions.selectFromDropDownByIndex(CommonProjectFunctions.sourcingLead, 7);
				//*Sourcing Head 
				CommonFunctions.selectFromDropDownByIndex(CommonProjectFunctions.sourcingHead, 7);
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
		}*/
		
		/*public static String AddSource(String sourcename,String source) throws Exception{
			try{
				CommonFunctions.waitForPageLoaded();
				//Add Source
				Select dropDownSource = new Select(SeleniumDriver.driver.findElement(selectSource));
				List<WebElement> elementCountSource = dropDownSource.getOptions();
				int countSource =elementCountSource.size();
				if(countSource>2)
				{
					CommonFunctions.selectFromDropDownByVisibleText(selectSource, source);
					CommonProjectFunctions.strSource=new Select(SeleniumDriver.driver.findElement(selectSource)).getFirstSelectedOption().getText();
				}
				else
				{
					AddSourcingConfiguration(sourcename);
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(selectSource);
					CommonFunctions.selectFromDropDownByVisibleText(selectSource, source);
					CommonFunctions.waitForPageLoaded();
					CommonProjectFunctions.strSource=new Select(SeleniumDriver.driver.findElement(selectSource)).getFirstSelectedOption().getText();
				}
				SeleniumDriver.log.info("Source is: "+CommonProjectFunctions.strSource);
			}catch(Exception e){
				fail=true;
				log.error("Exception in AddSource()", e);
				throw e;
			}
			return CommonProjectFunctions.strSource;
		}
		*/
	//Associate BOM To Vendor Product Costsheet
		public static boolean TC02_pc49associateBOMToVendorPrdCS(String [] data) throws Exception{
			try{
		   //******Create Product : Assortment********
			CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
		    data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
		    data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
			//Prerequisites for any type of product to be visible for vendor user
	        VendorProductPrerequisites(data);
			CommonProjectFunctions.logOut();
			driver.quit();
			openBrowser();
			launchApp(data[46],data[47]);	
			//Navigating to product page  
			navigateToProduct(data);
			//Search Product
            CommonProjectFunctions.searchProduct(prodNum1);
			CommonFunctions.waitForPageLoaded();
			
			//Navigate To materials tab
			navigateToMaterialTabThroughSideBar(data);
			//Select Season
			CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
			CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
			CommonFunctions.waitForPageLoaded();
			/*//Select Source
			CommonFunctions.waitForElementTobeClickable(SourceDropDown);
			CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
			Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded();*/
			//Select Specification
			CommonFunctions.waitForElementTobeClickable(Specifications_old.specificationdropdown);
			CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
			CommonFunctions.waitForPageLoaded();
			//Select Colorway
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
			CommonFunctions.waitForPageLoaded();
			//Create Vendor Product BOM
			pc52createRetailIntBOM(data);
			//Getting a BOM Values
			getBOMValues(data);
			//Navigating to Costing Tab
			NavigateToCostingTab(data);
			//Create Product Vendor Cost sheet
			CreateInternalRetailCS_TC03(data);
			//get Vendor Cost Sheet Details
			getVendorCostSheetDetails(data);
			
			
			
			
		
			
			
		}
			catch(Exception e){
				fail=true;
				log.error("Exception in pc49associateBOMToVendorPrdCS "+e);
				throw e;
				}
			return true;
	}
		     
	//Associate BOM to Vendor Retail Item CS
		public static boolean TC03_pc50associateBOMToVendorRetailItemCS(String [] data) throws Exception{
			try{
		
				/***
				 * Prerequisites
				 * 1.Create Product : Retail Item
				 * 2.Create Internal Retail Item BOM
				 * 3.Create Internal Retail Item CS
				 */
				
				//********Create Product : Retail Item*********
				CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
				data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
				data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
				
				NavigateToDetailsTab(data);
				prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
				log.info("*******The Retail Item PRODUCT NUMBER Created is********"+prodNum1);
				//Prerequisites for any type of product to be visible for vendor user
		        VendorProductPrerequisites(data);
		        CommonProjectFunctions.logOut();
				driver.quit();
				openBrowser();
				launchApp(data[79],data[80]);
		        //Navigating to product page  
				navigateToProductforPC50andPC54(data);
				//Search Product
	            CommonProjectFunctions.searchProduct(prodNum1);
				CommonFunctions.waitForPageLoaded();
				//Navigate To Costing tab
				navigateToMaterialTabThroughSideBar(data);
				//Select Season
				CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
				CommonFunctions.waitForPageLoaded();
				
				//Select Specification
				CommonFunctions.waitForElementTobeClickable(Specifications_old.specificationdropdown);
				CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
				CommonFunctions.waitForPageLoaded();
				//Select Colorway
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
				CommonFunctions.waitForPageLoaded();
				//Create Vendor Product BOM
				createRetailVendorBOM(data);
				//Getting a BOM Values
				getBOMValuesInternalRetail(data);
				//Navigating to Costing Tab
				NavigateToCostingTabpc48(data);
				//Create Retail Item Internal Cost sheet
				CreateInternalRetailCS_TC03(data);
				//get Vendor Cost Sheet Details
				getInternalRetailCostSheetDetails(data);
			
			
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in TC03_pc50associateBOMToVendorRetailItemCS "+e);
				throw e;
				}
			return true;
	}
	
	//PC56 Validations-Before Updation

    public static boolean pc56assertionVerficationBeforeUpdation(String [] data) throws Exception{
    	try{
    		//Assertion Verification of Quote Currency
    		CommonFunctions.waitForElementTobeClickable(strQuoteCurrency);
    		GettingText(strQuoteCurrency);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[39], "Actual And Expected Cost Sheet Quote Currency Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of Retail Item Cost
    		CommonFunctions.waitForElementTobeClickable(strRetailItemCostValue);
    		GettingText(strRetailItemCostValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[68], "Actual And Expected Cost Sheet Retail Item Cost Value Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Plastic material
    		CommonFunctions.waitForElementTobeClickable(strPlasticValue);
    		GettingText(strPlasticValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[58], "Actual And Expected Cost Sheet Plastic material Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Purchased Material
    		CommonFunctions.waitForElementTobeClickable(strPurchasedValue);
    		GettingText(strPurchasedValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[62], "Actual And Expected Cost Sheet Purchased Material Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Soft Goods Material
    		CommonFunctions.waitForElementTobeClickable(strSGValue);
    		GettingText(strSGValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[64], "Actual And Expected Cost Sheet Soft Goods Material Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Chemical Material
    		CommonFunctions.waitForElementTobeClickable(strChemicalValue);
    		GettingText(strChemicalValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[61], "Actual And Expected Cost Sheet Chemical Material Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  General / Deco Labor Cost
    		CommonFunctions.waitForElementTobeClickable(strGenDecLabValue);
    		GettingText(strGenDecLabValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[66], "Actual And Expected Cost Sheet General / Deco Labor Cost Are Not Matched.Assertion Failed.Please check");
    		/*//Assertion Verification of  Molding Labor Cost
    		CommonFunctions.waitForElementTobeClickable(strMoldingLabValue);
    		GettingText(strMoldingLabValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[67], "Actual And Expected Cost Sheet Molding Labor Cost Are Not Matched.Assertion Failed.Please check");
    		*/
    		//Assertion Verification of  Electronic Material
    		CommonFunctions.waitForElementTobeClickable(strElectronicValue);
    		GettingText(strElectronicValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[63], "Actual And Expected Cost Sheet Electronic Material Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Packaging Material
    		CommonFunctions.waitForElementTobeClickable(strPackagingValue);
    		GettingText(strPackagingValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[65], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Product Markup Cost
    		CommonFunctions.waitForElementTobeClickable(strProductMarkup);
    		GettingText(strProductMarkup);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[87], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
    	}
    	catch(Exception e){

			fail=true;
			log.error("Exception in pc56assertionVerficationBeforeUpdation"+e);
			throw e;
		}
		return true;
	}
    
  //PC56 Validations-Before Updation

    public static boolean pc56assertionVerficationAfterUpdation(String [] data) throws Exception{
    	try{
    		//Assertion Verification of Quote Currency
    		CommonFunctions.waitForElementTobeClickable(strQuoteCurrency);
    		GettingText(strQuoteCurrency);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[39], "Actual And Expected Cost Sheet Quote Currency Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of Retail Item Cost
    		CommonFunctions.waitForElementTobeClickable(strRetailItemCostValue);
    		GettingText(strRetailItemCostValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[144], "Actual And Expected Cost Sheet Retail Item Cost Value Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Plastic material
    		CommonFunctions.waitForElementTobeClickable(strPlasticValue);
    		GettingText(strPlasticValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[145], "Actual And Expected Cost Sheet Plastic material Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Purchased Material
    		CommonFunctions.waitForElementTobeClickable(strPurchasedValue);
    		GettingText(strPurchasedValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[146], "Actual And Expected Cost Sheet Purchased Material Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Soft Goods Material
    		CommonFunctions.waitForElementTobeClickable(strSGValue);
    		GettingText(strSGValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[147], "Actual And Expected Cost Sheet Soft Goods Material Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Chemical Material
    		CommonFunctions.waitForElementTobeClickable(strChemicalValue);
    		GettingText(strChemicalValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[148], "Actual And Expected Cost Sheet Chemical Material Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  General / Deco Labor Cost
    		CommonFunctions.waitForElementTobeClickable(strGenDecLabValue);
    		GettingText(strGenDecLabValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[149], "Actual And Expected Cost Sheet General / Deco Labor Cost Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Molding Labor Cost
    		CommonFunctions.waitForElementTobeClickable(strMoldingLabValue);
    		GettingText(strMoldingLabValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[150], "Actual And Expected Cost Sheet Molding Labor Cost Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Electronic Material
    		CommonFunctions.waitForElementTobeClickable(strElectronicValue);
    		GettingText(strElectronicValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[151], "Actual And Expected Cost Sheet Electronic Material Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Packaging Material
    		CommonFunctions.waitForElementTobeClickable(strPackagingValue);
    		GettingText(strPackagingValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[152], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
    		//Assertion Verification of  Product Markup Cost
    		CommonFunctions.waitForElementTobeClickable(strProductMarkup);
    		GettingText(strProductMarkup);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[153], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
    	}
    	catch(Exception e){

			fail=true;
			log.error("Exception in pc56assertionVerficationAfterUpdation"+e);
			throw e;
		}
		return true;
	}
	//Associate BOM to Vendor Retail Item CS
		public static boolean TC06_pc56CreateValidateBOMOnIntRetCS(String [] data) throws Exception{
			try{
				
				//******Create Product : Retail Item********
				
			      CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
				  data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
				  data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
				
			    NavigateToDetailsTab(data);
				prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
				log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum1);
				//Navigating to details Tab
				ClickSeasonAndSource(data);
				//Selecting Season and Source
				AddSpecification(data);
				CommonFunctions.waitForPageLoaded();
				//Adding a Specification
				CreateMultiple_Colorway();
				//Create Multiple Colorway
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
				CommonFunctions.waitForPageLoaded();
				//Select Colorway
				navigateToMaterialTabThroughSideBar(data);
				//Navigate To materials tab
				TC06_pc52createRetailIntBOM(data);
				//Create Internal Product BOM
				NavigateToCostingTab(data);
				//Navigating to Costing Tab
				TC06_CreateInternalRetailCS(data);
				//Create Product Internal Cost sheet
				getInternalProductCostSheetDetails(data);
				//get Vendor Cost Sheet Details
				navigateToMaterialTabThroughSideBar(data);
				//Navigate To materials tab
				UpdateBOMValueInternalRetailItem(data);
				//Updating the BOM value
				//getBOMValues(data);
				//Getting a BOM Values
				NavigateToCostSheetThroughSideBar(data);
				//Navigate to Cost Sheet
				TC06_AssertionVerficationOfVendorCostSheetValues(data);
				//Assertion Verification Of Cost values
				
			
				
				
				}
			catch(Exception e){
				fail=true;
				log.error("Exception in pc56CreateValidateBOMOnIntRetCS"+e);
				throw e;
			}
			 return true;
		}
		
	
//PC54 Validations
	
	public static boolean pc54assertionVerficationAfterUpdation(String [] data) throws Exception{
		try{
			
			//Assertion Verification of Quote Currency
    		CommonFunctions.waitForElementTobeClickable(strQuoteCurrency);
    		GettingText(strQuoteCurrency);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[39], "Actual And Expected Cost Sheet Quote Currency Are Not Matched.Assertion Failed.Please check");
			log.info("*****Verified Quote Currency*****");
    		//Assertion Verification of CurrencyConversionRate of HKD
			CommonFunctions.waitForElementTobeClickable(currencyConversionRate);
			GettingText(currencyConversionRate);
			CommonFunctions.AssertEqualsVerification(ActualValue, data[69], "Actual And Expected Cost Sheet CurrencyConversionRate Are Not Matched.Assertion Failed.Please check");
			log.info("*****Verified Currency Conversion Rate*****");
			//Assertion Verification of Retail Item Cost
    		CommonFunctions.waitForElementTobeClickable(strRetailItemCostValue);
    		GettingText(strRetailItemCostValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[68], "Actual And Expected Cost Sheet Retail Item Cost Value Are Not Matched.Assertion Failed.Please check");
    		log.info("*****Verified Retail Item Cost*****");
    		//Assertion Verification of  Plastic material
    		CommonFunctions.waitForElementTobeClickable(strPlasticValue);
    		GettingText(strPlasticValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[58], "Actual And Expected Cost Sheet Plastic material Are Not Matched.Assertion Failed.Please check");
    		log.info("*****Verified Plastic material*****");
    		//Assertion Verification of  Purchased Material
    		CommonFunctions.waitForElementTobeClickable(strPurchasedValue);
    		GettingText(strPurchasedValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[62], "Actual And Expected Cost Sheet Purchased Material Not Matched.Assertion Failed.Please check");
    		log.info("*****Verified Purchased material*****");
    		//Assertion Verification of  Soft Goods Material
    		CommonFunctions.waitForElementTobeClickable(strSGValue);
    		GettingText(strSGValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[64], "Actual And Expected Cost Sheet Soft Goods Material Are Not Matched.Assertion Failed.Please check");
    		log.info("*****Verified Soft Goods Material*****");
    		//Assertion Verification of  Chemical Material
    		CommonFunctions.waitForElementTobeClickable(strChemicalValue);
    		GettingText(strChemicalValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[61], "Actual And Expected Cost Sheet Chemical Material Are Not Matched.Assertion Failed.Please check");
    		log.info("*****Verified Chemical Material*****");
    		//Assertion Verification of  General / Deco Labor Cost
    		CommonFunctions.waitForElementTobeClickable(strGenDecLabValue);
    		GettingText(strGenDecLabValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[66], "Actual And Expected Cost Sheet General / Deco Labor Cost Are Not Matched.Assertion Failed.Please check");
    		log.info("*****Verified General / Deco Labor*****");
    		//Assertion Verification of  Molding Labor Cost
    		CommonFunctions.waitForElementTobeClickable(strMoldingLabValue);
    		GettingText(strMoldingLabValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[67], "Actual And Expected Cost Sheet Molding Labor Cost Are Not Matched.Assertion Failed.Please check");
    		log.info("*****Verified Molding Labor Cost*****");
    		//Assertion Verification of  Electronic Material
    		CommonFunctions.waitForElementTobeClickable(strElectronicValue);
    		GettingText(strElectronicValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[63], "Actual And Expected Cost Sheet Electronic Material Are Not Matched.Assertion Failed.Please check");
    		log.info("*****Verified Electronic Material*****");
    		//Assertion Verification of  Packaging Material
    		CommonFunctions.waitForElementTobeClickable(strPackagingValue);
    		GettingText(strPackagingValue);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[65], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
    		log.info("*****Verified Packaging Material*****");
    		//Assertion Verification of  ProductMarkUpValue of HKD
			CommonFunctions.waitForElementTobeClickable(validateOverheadMarkups);
			GettingText(validateOverheadMarkups);
			CommonFunctions.AssertEqualsVerification(ActualValue, data[87], "Actual And Expected Cost Sheet ProductMarkUpValue Are Not Matched.Assertion Failed.Please check");
			log.info("*****Verified ProductMarkUpValue*****");
		}
		catch(Exception e){

			fail=true;
			log.error("Exception in pc54assertionVerficationAfterUpdation"+e);
			throw e;
		}
		return true;
	}
	//Associate BOM to Vendor Retail Item CS
		public static boolean TC04_pc54RemoveBOMFromVendorRetailItemCS(String [] data) throws Exception{
			try{
				
			
				
				//********Create Product : Retail Item*********
				CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
				data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
				data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
				
				NavigateToDetailsTab(data);
				prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
				log.info("*******The Retail Item PRODUCT NUMBER Created is********"+prodNum1);
				//Prerequisites for any type of product to be visible for vendor user
		        VendorProductPrerequisites(data);
		        CommonProjectFunctions.logOut();
				driver.quit();
				openBrowser();
				launchApp(data[79],data[80]);
				//Navigating to product page  
				navigateToProductforPC50andPC54(data);
				/*//Search Product
			    CommonProjectFunctions.searchProduct(prodNum1);*/
			    
			    //Search Product
	            CommonProjectFunctions.searchProduct(prodNum1);
				CommonFunctions.waitForPageLoaded();
				//Navigate To Costing tab
				navigateToMaterialTabThroughSideBar(data);
				//Select Season
				CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
				CommonFunctions.waitForPageLoaded();
				
				//Select Specification
				CommonFunctions.waitForElementTobeClickable(Specifications_old.specificationdropdown);
				CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
				CommonFunctions.waitForPageLoaded();
				//Select Colorway
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
				CommonFunctions.waitForPageLoaded();
				//Create Vendor Product BOM
				createRetailVendorBOM(data);
				//Navigating to Costing Tab
				NavigateToCostingTabpc48(data);
				//Create Retail Item Internal Cost sheet
				CreateInternalRetailCS_TC03(data);
				//get Vendor Cost Sheet Details
				getInternalRetailCostSheetDetails(data);
				
			   
				//Wait for Update-Actions menu
				CommonFunctions.waitForElementTobeClickable(updateCSActions);
				//Select Update CS
				CommonFunctions.selectFromDropDownByIndex(updateCSActions, 1);
				CommonFunctions.waitForPageLoaded();
				//Wait for Quote Currency Dropdown
				CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
				//Click on Quote Currency Dropdown
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(QuoteCurrencyDropDown, data[105], "QuoteCurrency DropDown");
				//Wait for BOM Dropdown
				CommonFunctions.waitForElementTobeClickable(BOMDropDown);
				//Remove BOM
				CommonFunctions.selectFromDropDownByIndex(BOMDropDown, 0);
				Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded();
				
				//Plastic Material
				CommonFunctions.clearTextBox(updatePlasticMaterial, "Plastic Material");
				CommonFunctions.waitForElementTobeClickable(updatePlasticMaterial);
	            CommonFunctions.enterTextInTextboxUpdated(updatePlasticMaterial, data[96], "PlasticMaterialTextBox");
	            //Chemical Material
	            CommonFunctions.clearTextBox(updateChemicalMaterial, "Chemical Material");
				CommonFunctions.waitForElementTobeClickable(updateChemicalMaterial);
	            CommonFunctions.enterTextInTextboxUpdated(updateChemicalMaterial, data[97], "ChemicalMaterialTextBox");
	            //Purchased Material
	            CommonFunctions.clearTextBox(updatePurchasedMaterial, "Purchased Material");
				CommonFunctions.waitForElementTobeClickable(updatePurchasedMaterial);
	            CommonFunctions.enterTextInTextboxUpdated(updatePurchasedMaterial, data[98], "PurchasedMaterialTextBox");
	            //Electronic Material
	            CommonFunctions.clearTextBox(updateElectronicMaterial, "Electronic Material");
				CommonFunctions.waitForElementTobeClickable(updateElectronicMaterial);
	            CommonFunctions.enterTextInTextboxUpdated(updateElectronicMaterial, data[99], "ElectronicMaterialTextBox");
	            //Soft Goods Material
	            CommonFunctions.clearTextBox(updateSGsMaterial, "Soft Goods Material");
				CommonFunctions.waitForElementTobeClickable(updateSGsMaterial);
	            CommonFunctions.enterTextInTextboxUpdated(updateSGsMaterial, data[100], "SGsMaterialTextBox");
	            //Packaging Material
	            CommonFunctions.clearTextBox(updatePackagingMaterial, "Packaging Material");
				CommonFunctions.waitForElementTobeClickable(updatePackagingMaterial);
	            CommonFunctions.enterTextInTextboxUpdated(updatePackagingMaterial, data[101], "PackagingMaterialTextBox");
	            //Gen/Dec Labor Material
	            CommonFunctions.clearTextBox(updateGenDecLaborMaterial, "Gen/Dec Labor Material");
				CommonFunctions.waitForElementTobeClickable(updateGenDecLaborMaterial);
	            CommonFunctions.enterTextInTextboxUpdated(updateGenDecLaborMaterial, data[102], "GenDecLaborMaterialTextBox");
	            //Molding Labor Cost
	            CommonFunctions.clearTextBox(updateMoldingLaborCost, "Molding Labor Cost");
				CommonFunctions.waitForElementTobeClickable(updateMoldingLaborCost);
	            CommonFunctions.enterTextInTextboxUpdated(updateMoldingLaborCost, data[103], "MoldingLaborCostTextBox");
	            //Markup
	            CommonFunctions.clearTextBox(updateMarkup, "Markup Cost");
				CommonFunctions.waitForElementTobeClickable(updateMarkup);
	            CommonFunctions.enterTextInTextboxUpdated(updateMarkup, data[104], "MarkupTextBox");
	            //Click on Save button
				CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
				CommonFunctions.waitForPageLoaded();
				//PC54 Validations
				pc54assertionVerficationAfterUpdation(data);
				
				}
			catch(Exception e){
				fail=true;
				log.error("Exception in pc54RemoveBOMFromVendorRetailItemCS"+e);
				throw e;
			}
			 return true;
		}
		
		
		//Create and Validate Vendor Product BOM for PC22
				public static String[] pc22creatVendorPrdBOM(String [] data) throws Exception{
					try{
						
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForPageLoaded();
					//Click on ADD NEW BOM
					CommonFunctions.waitForElementTobeClickable(strAddNewBOM);
					CommonFunctions.clickButtonOrLink(strAddNewBOM,"btn", "ADD NEW BOM");
					//Click Initialize BOM
					CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
					CommonFunctions.waitForPageLoaded();
					//Assertion verification to check if Create BOM page contains 'General Attributes', attribute section.
					CommonFunctions.waitForElementTobeClickable(strGeneralAttributes);
					CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strGeneralAttributes, "General Attributes section in Create BOM page"), "General Attributes section in Create BOM page Not dispalyed.Assertion failed.please check");
					log.info("**** Verified that Create BOM Page has General Attributes Section ****");
					//Assertion verification to check if Create BOM page contains 'BOM Cost Summary' attribute section.
					CommonFunctions.waitForElementTobeClickable(strVendorBOMCostSummary);
					CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strVendorBOMCostSummary, "Vendor BOM Cost Summary attribute section in Create BOM page"), "Vendor BOM Cost Summary section in Create BOM page Not dispalyed.Assertion failed.please check");
					log.info("**** Verified that Create BOM Page has BOM Cost Summary ****");
					//Validate BOM Type
					CommonFunctions.waitForElementTobeClickable(strBOMType);
					GettingText(strBOMType);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[37], "Actual And Expected BOM Type Are Not Matched.Assertion Failed.Please check");
					log.info("**** Verified that BOM Type is Vendor Product BOM ****");
					//Click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
				    //Verify the Error Text:Colorway
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.AssertEqualsVerification(CommonFunctions.alertmessage.trim(), data[106], "Actual and Expected values are not matched.Assertion falied.Please check.");
					log.info("**** Verified Error Message ststing fields are missing****");
					//Wait for Colorway
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
					//Select Colorway
					CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
					//Click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
					//Verify the Error Text:Currency
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.AssertEqualsVerification(CommonFunctions.alertmessage.trim(), data[107], "Actual and Expected values are not matched.Assertion falied.Please check.");
					log.info("**** Verified Error Message ststing fields are missing****");
					//Wait for Quote Currency Dropdown
					CommonFunctions.waitForElementTobeClickable(currency);
					//Click on Quote Currency Dropdown
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(currency, data[57], "QuoteCurrency DropDown");
					//Click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
					//Verify the Error Text:Factory
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.AssertEqualsVerification(CommonFunctions.alertmessage.trim(), data[108], "Actual and Expected values are not matched.Assertion falied.Please check.");
					log.info("**** Verified Error Message ststing fields are missing****");
					//Verify 'Factory' is present under 'General Attributes'
					CommonFunctions.waitForElementTobeClickable(strGeneralAttributesFactory);
					CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strGeneralAttributesFactory, "Fatory is presnet under GeneralAttributes"), "Fatory is Not dispalyed under GeneralAttributes.Assertion failed.please check");
					log.info("**** Verified 'Factory' is present under 'General Attributes'****");
					//Click on Factory
					CommonFunctions.clickButtonOrLink(SourcingConfig.factory, "link", "Factory field from Create BOM page");
					CommonFunctions.waitForPageLoaded();
					Set set = SeleniumDriver.driver.getWindowHandles();
					Iterator iter = set.iterator();
					String parent = (java.lang.String) iter.next();
					String child = (java.lang.String) iter.next();
					SeleniumDriver.driver.switchTo().window(child);
					CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
					CommonFunctions.waitForPageLoaded();
					//Verify Factory search results is same as Factory Load File(As we can't automate the factory load file, Factory load file for "Vendor Product BOM" contains "AEQUS")
					CommonFunctions.waitForElementTobeClickable(strSupplierFromSearchResultsTM);
		    		GettingText(strSupplierFromSearchResultsTM);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[110], "Actual And Expected Cost Sheet Quote Currency Are Not Matched.Assertion Failed.Please check");
		    		log.info("**** Verified Factory search results is same as Factory Load File****");
		    		//CommonFunctions.AssertEqualsVerification(strSupplierFromSearchResults, data[4], "Actual and Expected values are not matched.Assertion falied.Please check.");
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'(choose)')]"), "Supplier selected");
					SeleniumDriver.driver.switchTo().window(parent);
                    SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");
					//Verify 'COO' is present under 'General Attributes'
					CommonFunctions.waitForElementTobeClickable(strGeneralAttributesCOO);
					CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strGeneralAttributesCOO, "COO is present under GeneralAttributes"), "COO is Not dispalyed under GeneralAttributes.Assertion failed.please check");
					log.info("**** Verified 'COO' is present under 'General Attributes'****");
					//Verify 'Factory Region' is present under 'General Attributes'
					CommonFunctions.waitForElementTobeClickable(strGeneralAttributesFactoryRegion);
					CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strGeneralAttributesFactoryRegion, "Factory Region is present under GeneralAttributes"), "Factory Region is Not displayed under GeneralAttributes.Assertion failed.please check");
					log.info("**** Verified 'Factory Region' is present under 'General Attributes'****");
					//Verify  if 'COO' id editable or not
					CommonFunctions.waitForElementTobeClickable(strGeneralAttributes);
					CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strGeneralAttributes, "General Attributes section in Create BOM page"), "General Attributes section in Create BOM page Not dispalyed.Assertion failed.please check");
					Assert.assertEquals(driver.findElements(strGeneralAttributesCOO).size(),1);
					log.info("****COO field is not editable field****");
					//Verify  if 'Factory Region' id editable or not
					CommonFunctions.waitForElementTobeClickable(strGeneralAttributes);
					CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strGeneralAttributes, "General Attributes section in Create BOM page"), "General Attributes section in Create BOM page Not dispalyed.Assertion failed.please check");
					Assert.assertEquals(driver.findElements(strGeneralAttributesFactoryRegion).size(),1);
					log.info("****Factory Region field is not editable field");
					//Verify if 'Factory Region' is editable or not
					Assert.assertEquals(driver.findElements(strGeneralAttributesFactoryRegion).size(),1);
					//Click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
					//Verify the Error Text:Wave
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.AssertEqualsVerification(CommonFunctions.alertmessage.trim(), data[109], "Actual and Expected values are not matched.Assertion falied.Please check.");
					log.info("****Verified error message stating that fields(wave) are missing");
					//Wait for Wave Dropdown
					CommonFunctions.waitForElementTobeClickable(wave);
					//Click on Wave Dropdown
					//CommonFunctions.selectFromDropDownByVisibleTextUpdated(wave, data[34], "Wave DropDown");
					CommonFunctions.selectFromDropDownByIndex(wave, 1);
					//Click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForPageLoaded();
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);
					//Verify Order-- Master Carton Packaging
					CommonFunctions.waitForElementTobeClickable(strBOMTableOrderMaster);
					CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderMaster, "Verify Order-- Master Carton Packaging is displayed first"), "Master Carton Packaging is Not dispalyed in correct order.Assertion failed.please check");
					log.info("****Verified Master Carton Packaging****");
					//Verify Order-- Labor
					CommonFunctions.waitForElementTobeClickable(strBOMTableOrderLabor);
					CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderLabor, "Verify Order-- Labor is displayed after Master Carton Packaging- "), "Labor-Not dispalyed in correct order i.e  after Master.Assertion failed.please check");
					log.info("****Verified Order-- Labor****");
					//Verify Order-- Miscellaneous
					CommonFunctions.waitForElementTobeClickable(strBOMTableOrderMisce);
					CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderMisce, "Verify Order-- Miscellaneous is displayed after Labor"), "Miscellaneous Not dispalyed in correct order i.e after Labor.Assertion failed.please check");
					log.info("****Verified Order-- Miscellaneous****");
					CommonFunctions.waitForPageLoaded();
					Thread.sleep(1000);
			        //Verification for Tables View for Master Carton,Labor,Miscellaneous
					CommonFunctions.waitForElementTobeClickable(BOMTablesViewMaster);
					GettingText(BOMTablesViewMaster);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[111], "Actual and Expected values of Master Table View are not matched.Assertion falied.Please check.");
					CommonFunctions.waitForElementTobeClickable(BOMTablesViewLabor);
					GettingText(BOMTablesViewLabor);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[112], "Actual and Expected values of Labor Table View are not matched.Assertion falied.Please check.");
					CommonFunctions.waitForElementTobeClickable(BOMTablesViewMiscellaneous);
					GettingText(BOMTablesViewMiscellaneous);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[113], "Actual and Expected values of Miscellaneous are not matched.Assertion falied.Please check.");
					//Click button btnSaveAndCheckIn
					CommonFunctions.waitForElementTobeClickable(ExternalBOM.btnSaveAndCheckIn);
					CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
					CommonFunctions.handleAlertPopUp();
					//Verify that User gets a pop up with a message "Save Changes and Check in BOM ?"
					CommonFunctions.AssertEqualsVerification(CommonFunctions.alertmessage.trim(), data[114], "Actual and Expected values of Checkin ERROR message are not matched.Assertion falied.Please check.");
					log.info("****Verified pop up with a message Save Changes and Check in BOM ?****");
					//Switch to default frame 	 	
					driver.switchTo().defaultContent();
					//Switch to contentFrame
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForPageLoaded();
                    //Verify Header Attributes
					CommonFunctions.waitForVisibilityOfElement(headerAttributes);
					CommonFunctions.clickButtonOrLink(headerAttributes,"link", "headerAttributes");
					//	String strBOM=driver.findElement(BOMId).getText();
					String strBOMInWork1=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
					strBOMInWorkRetail= strBOMInWork1.trim();
					System.out.println("BOM name in detail page after check in: " + strBOMInWorkRetail);
					//Verify BOM Name (OOTB) Colorway + Wave + Supplier Short Name
					strColorwayVal=GettingText(strColorwayDynamic);
					strWaveVal=GettingText(strWaveDynamic);
					//CommonFunctions.waitForElementTobeClickable(BOMTablesViewLabor);
					seq=GettingText(strBOMNameFromEditBOMPage);
					System.out.println("The seq value is "+seq);
					actSeq = seq.substring(0,3);
					System.out.println("The actSeq value is "+actSeq);
					strBOMNameDynamic = actSeq+" : "+strColorwayVal+"-"+strWaveVal+"-"+strVendorShortName;
					CommonFunctions.AssertEqualsVerification(strBOMNameDynamic.trim(), strBOMInWorkRetail, "Actual and Expected values of BOM NAME are not matched.Assertion falied.Please check.");
					CommonFunctions.waitForElementTobeClickable(strCOOHearderAttr);
					Assert.assertEquals(driver.findElements(strCOOHearderAttr).size(),1);
					log.info("*****Country of Origin attribute value are populated in Header attributes*****");
					//Verify that the Factory Region attribute value are populated in Header attributes
					CommonFunctions.waitForElementTobeClickable(strFactoryHearderAttr);
					Assert.assertEquals(driver.findElements(strFactoryHearderAttr).size(),1);
				    log.info("*****Factory Region attribute value are populated in Header attributes*****");
			}
					catch(Exception e){
						fail=true;
						log.error("Exception in pc05createRetailTemProduct"+e);
						throw e;
					}
					//return true;
					return new String[] {strBOMInWorkRetail, BOMnameInWorkRetail1};

				}
				//SearchProduct
				
			      public static boolean SearchProduct(String[] data) throws Exception
			      {
			            try{
			                  ProductType=By.linkText(data[5]);
			              CommonFunctions.waitForElementTobeClickable(ProductType);
			                  CommonFunctions.clickButtonOrLink(ProductType, "HyperLink", "AssortMen/Solid");
			                  //Choosing the product type as Retail Item
			                  CommonFunctions.waitForPageLoaded();
			            CommonFunctions.waitForElementTobeClickable(ProductNumberSearch);
			            CommonFunctions.enterTextInTextboxUpdated(ProductNumberSearch, prodNum1, "product number");
			                  //Entering the Product Number
			            CommonFunctions.clickButtonOrLink(SearchProductButton, "Button", "Search");
			                  //wait.until(ExpectedConditions.titleIs(data[4]));
			                  //waiting for the expected Pagetitle to appear
			                  CommonFunctions.waitForPageLoaded();
			                  }
			            catch(Exception e){
			                  fail=true;
			                  log.error("Exception in SearchProduct()", e);
			                  throw e;
			            }
			            return true;
			      }
			      
			      
			      


		//Create Vendor Product BOM
		
		
				public static boolean TC05_pc22CreateVendorProductBOM(String[] data) throws Exception{
					
					try{
				
			    
				
						
						
						   //******Create Product : Assortment********
						CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
					    data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
					    data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
						//Navigate to Details Tab
						NavigateToDetailsTab(data);
						prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
						log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum1);
						//Add Source
					    CommonProjectFunctions.AddSource("AEQUS",data[4]);
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
						CostingApprovalWF.updateSpecStatus(data);
						//Create Multiple Colorway
						CreateMultiple_Colorway();
						//Select Colorway
						CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
						CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
						CommonFunctions.waitForPageLoaded();
						CommonProjectFunctions.logOut();
					driver.quit();
					openBrowser();
					launchApp(data[39],data[40]);
					//Navigating to product page  
					CI296.navigateToProduct(data);
					//navigateToProduct(data);
					//Search Product
					CommonProjectFunctions.searchProduct(prodNum1);
					CommonFunctions.waitForPageLoaded();
					//Switch to default frame
					//driver.switchTo().defaultContent();
					//Switch to content frame
					//driver.switchTo().frame("contentframe");
					//SearchProduct(data);
					//Navigate To materials tab
					navigateToMaterialTabThroughSideBar(data);
				/*//Click on Specification tab
			    Thread.sleep(1000);
			    CommonFunctions.waitForPageLoaded();
			    SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(specificationtab));
	            CommonFunctions.waitForVisibilityOfElement(specificationtab);
	            CommonFunctions.clickButtonOrLink(specificationtab, "Specification tab");
	            CommonFunctions.waitForPageLoaded();
		        //Click on Material tab	   
		        CommonFunctions.waitForVisibilityOfElement(materialstab);
	            CommonFunctions.clickButtonOrLink(materialstab, "Material tab");*/
	            //CommonFunctions.waitForPageLoaded();
	            //CommonFunctions.handleAlertPopUp();
	            
			    //Select Season
	            SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
			    //CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
	            CommonFunctions.waitForPageLoaded();
		        CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
			    CommonFunctions.waitForPageLoaded();
			    //Select Specification
			    SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.selectSpecification));
			    CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
	            CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
	            Thread.sleep(1000);
	            CommonFunctions.waitForPageLoaded();
			    //Select Colorway
	            SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.colorway));
			    CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
	            CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
	            CommonFunctions.waitForPageLoaded();
				//Create BOM
				pc22creatVendorPrdBOM(data);
				//Logout of flexPLM
				CommonProjectFunctions.logOut();
				
				
			}
					catch(Exception e){
						fail=true;
						log.error("Exception in pc22CreateVendorProductBOM"+e);
						throw e;
					}
					return true;
		}
		
		
		//Create Retail Item product with all prerequisites:-PC05
		public static boolean pc05createRetailTemProduct(String[] data) throws Exception{
			
		try{
		//Create Product
		CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
				data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
				data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
		//Switch to default frame
		driver.switchTo().defaultContent();
		//Switch to content frame
		driver.switchTo().frame("contentframe");
		CommonFunctions.waitForPageLoaded();
		Thread.sleep(1000);
		SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(specificationtab));
		//Wait for Specification tab
		CommonFunctions.waitForVisibilityOfElement(specificationtab);
		//Click on Specification tab
		CommonFunctions.clickButtonOrLink(specificationtab, "Specification tab");
		Thread.sleep(1000);
		SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(sourceActions));
		//Create Source
	    CommonProjectFunctions.AddSourcingConfiguration("ADVENT");
	    SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(sourceActions));
	    CommonProjectFunctions.AddSourcingConfiguration("AEQUS");
	    SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(sourceActions));
	    CommonProjectFunctions.AddSourcingConfiguration("FUNSKOOL");
	    //Wait for Source dropdown
		CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
		//Click on Source dropdown
		CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 1);
		CommonFunctions.waitForPageLoaded();
	  
		//Create Specification
		CommonProjectFunctions.Create_Specifications(data[34],data[35]);
		CommonFunctions.waitForPageLoaded();
		CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
		//Select Specification
		CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
		Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded();
		InternalBOMSoftG.Create_Colorway(data);
		CommonFunctions.waitForPageLoaded();
		SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(csProductNumber));
		String strPrdNo=driver.findElement(csProductNumber).getText();
		CommonProjectFunctions.searchProduct(strPrdNo);
		CommonFunctions.waitForPageLoaded();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//Wait for 	Sourcing Tab
		CommonFunctions.waitForVisibilityOfElement(SourcingTab);
		//Click on Sourcing tab
		CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
		CommonFunctions.waitForPageLoaded();
		
		//Select Source-Source A
		CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
		CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 1);
		//Wait for 	Actions-Update Source-A
		CommonFunctions.waitForVisibilityOfElement(strUpdateSourceActions);
		//Click on Upadte Source menu
		CommonFunctions.selectFromDropDownByIndex(strUpdateSourceActions, 1);
		CommonFunctions.waitForPageLoaded();
		CommonFunctions.waitForVisibilityOfElement(strUpdateSourceStatus);
		CommonFunctions.selectFromDropDownByIndex(strUpdateSourceStatus, 2);
		CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
		CommonFunctions.waitForPageLoaded();
		CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
		CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 3);
		
		//Wait for 	Actions-Update Source-C
		CommonFunctions.waitForPageLoaded();
		CommonFunctions.waitForVisibilityOfElement(strUpdateSourceActions);
		CommonFunctions.selectFromDropDownByIndex(strUpdateSourceActions, 1);
		CommonFunctions.waitForPageLoaded();
		CommonFunctions.waitForVisibilityOfElement(strUpdateSourceStatus);
		CommonFunctions.selectFromDropDownByIndex(strUpdateSourceStatus, 3);
		CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
		CommonFunctions.waitForPageLoaded();
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in pc05createRetailTemProduct"+e);
			throw e;
		}
		return true;
	}
		
		//Create and Validate Vendor Product BOM for PC05
		public static String[] PC05AndPC06createVendorRetailItemBOM(String [] data) throws Exception{
			try{
			
			driver.switchTo().defaultContent();
		    driver.switchTo().frame("contentframe");
		    //Navigating to product search Page
			CI296.navigateToProduct(data);
			CommonFunctions.waitForPageLoaded();
		    //Search Product
		    CommonProjectFunctions.searchProduct(prodNum1);
		    CommonFunctions.waitForPageLoaded();
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("contentframe");
		    
		    //Click on Specification tab
		    Thread.sleep(1000);
		    CommonFunctions.waitForPageLoaded();
		    SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(specificationtab));
            CommonFunctions.waitForVisibilityOfElement(specificationtab);
            CommonFunctions.clickButtonOrLink(specificationtab, "Specification tab");
            CommonFunctions.waitForPageLoaded();
            //Select Season
		    CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
	        CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
		    CommonFunctions.waitForPageLoaded();
		    //Select Source-A
		    CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
		    CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 3);
            CommonFunctions.waitForPageLoaded();
            //Navigate To specifications tab
    		//navigateToSpecificationTabThroughSideBar(data);
    		//Adding a Specification
    		AddSpecification(data);
    		CommonFunctions.waitForPageLoaded();
    		//Select Specification
		    CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
            CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
            Thread.sleep(1000);
            CommonFunctions.waitForPageLoaded();
            //Create Multiple Colorway
    		CreateMultiple_Colorway();
    		//Select Colorway
    		CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
    		CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
    		CommonFunctions.waitForPageLoaded();
    		
    		CommonProjectFunctions.logOut();
			driver.quit();
			openBrowser();
			launchApp(data[39],data[40]);
			CI296.navigateToProduct(data);
			//Navigating to product search Page
			CommonProjectFunctions.searchProduct(prodNum1);
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			
			//Click on Sourcing tab
			CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
			CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
			
	       /* //Click on Material tab	   
	        CommonFunctions.waitForVisibilityOfElement(materialstab);
            CommonFunctions.clickButtonOrLink(materialstab, "Material tab");
            CommonFunctions.waitForPageLoaded();
            CommonFunctions.handleAlertPopUp();*/
			navigateToMaterialTabThroughSideBar(data);
            
		    //Select Season
		    CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
	        CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
		    CommonFunctions.waitForPageLoaded();
		    //Select Source-A
		    CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
		    CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 3);
            CommonFunctions.waitForPageLoaded();
		    //Select Specification
		    CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
            CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
            Thread.sleep(1000);
            CommonFunctions.waitForPageLoaded();
		    //Select Colorway
		    CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
            CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
            CommonFunctions.waitForPageLoaded();
            //Click on ADD NEW BOM
			CommonFunctions.waitForElementTobeClickable(strAddNewBOM);
			CommonFunctions.clickButtonOrLink(strAddNewBOM,"btn", "ADD NEW BOM");
			SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(BOMTypeId));
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[38]);
			
			CommonFunctions.waitForPageLoaded();
			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			CommonFunctions.waitForPageLoaded();
			//Validate if Name field is Editable or not
			Assert.assertEquals(driver.findElements(strCreateBOMPageName).size(),1);
			log.info("****Name field from Create BOM page is not editable field****");
			//Validate BOM Type
			CommonFunctions.waitForElementTobeClickable(strBOMType);
			GettingText(strBOMType);
			CommonFunctions.AssertEqualsVerification(ActualValue, data[37], "Actual And Expected BOM Type Are Not Matched.Assertion Failed.Please check");
			//Wait for Colorway
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
			//Select Colorway
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
			//Wait for Quote Currency Dropdown
			CommonFunctions.waitForElementTobeClickable(currency);
			//Click on Quote Currency Dropdown
			CommonFunctions.selectFromDropDownByIndex(currency, 2);
			//Wait for Wave Dropdown
			CommonFunctions.waitForElementTobeClickable(wave);
			//Click on Wave Dropdown
			CommonFunctions.selectFromDropDownByIndex(wave, 11);
			CommonFunctions.waitForPageLoaded();
			//Click on Factory
			CommonFunctions.clickButtonOrLink(SourcingConfig.factory, "link", "Factory field from Create BOM page");
			CommonFunctions.waitForPageLoaded();
			Set set = SeleniumDriver.driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			SeleniumDriver.driver.switchTo().window(child);
			CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
			CommonFunctions.waitForPageLoaded();
			if((data[38].contains("BOM\\Materials\\Product\\Retail Item\\Vendor"))){
				//Verify Factory search results is same as Factory Load File(As we can't automate the factory load file, Factory load file for "Vendor Product BOM" contains "ADVENT")
				CommonFunctions.waitForElementTobeClickable(strVerifyFactory);
	    		GettingText(strVerifyFactory);
	    		CommonFunctions.AssertEqualsVerification(ActualValue, data[110], "Actual And Expected BOM Factory Are Not Matched.Assertion Failed.Please check");
			}
			else if((data[38].contains("BOM\\Materials\\Product\\Product\\Vendor"))&&(data[2].contains("VendorProductBOM"))){
			//Verify Factory search results is same as Factory Load File(As we can't automate the factory load file, Factory load file for "Vendor Product BOM" contains "FUNSKOOL")
			CommonFunctions.waitForElementTobeClickable(strVerifyFactoryPC06);
    		GettingText(strVerifyFactoryPC06);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[110], "Actual And Expected BOM Factory Are Not Matched.Assertion Failed.Please check");
			}
			else if((data[38].contains("BOM\\Materials\\Product\\Product\\Vendor"))&&(data[2].contains("Create Sourcing Config using Copy/Link")))
					{
				//Verify Factory search results is same as Factory Load File(As we can't automate the factory load file, Factory load file for "Vendor Product BOM" contains "FUNSKOOL")
				CommonFunctions.waitForElementTobeClickable(strVerifyFactoryPC07);
	    		GettingText(strVerifyFactoryPC07);
	    		CommonFunctions.AssertEqualsVerification(ActualValue, data[172], "Actual And Expected BOM Factory Are Not Matched.Assertion Failed.Please check");
				}
			//CommonFunctions.AssertEqualsVerification(strSupplierFromSearchResults, data[4], "Actual and Expected values are not matched.Assertion falied.Please check.");
			//CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'(choose)')]"), "Supplier selected");
			/*SeleniumDriver.driver.switchTo().window(child);
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");*/
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'(choose)')]"), "Supplier selected");
			//CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'choose')]"), "Supplier selected");
			SeleniumDriver.driver.switchTo().window(parent);
            SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");
			//Click on Create
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame")); 
			CommonFunctions.waitForPageLoaded();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(CI253.AddRowsButton));
			if((data[38].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")))
			{
			//Verify Order-- Plastics
			CommonFunctions.waitForElementTobeClickable(strBOMTableOrderPlastic);
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderPlastic, "Verify Order-- Plastics is displayed first"), "Plastics is Not dispalyed in correct order.Assertion failed.please check");
			log.info("*** Verify Order-- Plastics - validated ***");
			//Verify Order-- Chemicals
			CommonFunctions.waitForElementTobeClickable(strBOMTableOrderChemical);
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderChemical, "Verify Order-- Chemicals is displayed after Plastics- "), "Chemicals-Not dispalyed in correct order i.e  after Master.Assertion failed.please check");
			log.info("*** Verify Order-- Chemicals - validated ***");
            //Verify Order-- Purchased Parts
			CommonFunctions.waitForElementTobeClickable(strBOMTableOrderPurchasedParts);
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderPurchasedParts, "Verify Order-- Purchased Parts is displayed after Chemicals"), "Purchased Parts Not dispalyed in correct order i.e after Labor.Assertion failed.please check");
			log.info("*** Verify Order-- Purchased Parts - validated ***");
			//Verify Order-- Electronics
			CommonFunctions.waitForElementTobeClickable(strBOMTableOrderElectronics);
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderElectronics, "Verify Order-- Electronics is displayed after Purchased Parts"), "Electronics Not dispalyed in correct order i.e after Labor.Assertion failed.please check");
			log.info("*** Verify Order-- Electronics - validated ***");
			//Verify Order-- Soft Goods
			CommonFunctions.waitForElementTobeClickable(strBOMTableOrderSoftgoods);
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderSoftgoods, "Verify Order-- Soft Goods is displayed after Electronics"), "Soft Goods Not dispalyed in correct order i.e after Labor.Assertion failed.please check");
			log.info("*** Verify Order-- Soft Goods - validated ***");
			//Verify Order-- Packaging
			CommonFunctions.waitForElementTobeClickable(strBOMTableOrderPackaging);
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderPackaging, "Verify Order-- Packaging is displayed after Soft Goods"), "Packaging Not dispalyed in correct order i.e after Labor.Assertion failed.please check");
			log.info("*** Verify Order-- Packaging - validated ***");
			//Verify Order-- General Deco/Labor
			CommonFunctions.waitForElementTobeClickable(strBOMTableOrderGenDecoLabor);
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderGenDecoLabor, "Verify Order-- General Deco/Labor is displayed after Packaging"), "General Deco/Labor Not dispalyed in correct order i.e after Labor.Assertion failed.please check");
			log.info("*** Verify Order-- General Deco/Labor - validated ***");
			//Verify Order-- Molding Labor
			CommonFunctions.waitForElementTobeClickable(strBOMTableOrderMoldingLabor);
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderMoldingLabor, "Verify Order-- Molding Labor is displayed after General Deco/Labor"), "Molding Labor Not dispalyed in correct order i.e after Labor.Assertion failed.please check");
			log.info("*** Verify Order-- Molding Labor - validated ***");
			}
			else if((data[38].contains("BOM\\Materials\\Product\\Product\\Vendor"))){
				
				//Verify Order-- Master Carton Packaging
				CommonFunctions.waitForElementTobeClickable(strBOMTableOrderMaster);
				
				CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderMaster, "Verify Order-- Master Carton Packaging is displayed first"), "Master Carton Packaging is Not dispalyed in correct order.Assertion failed.please check");
				log.info("*** Verify Order-- Master Carton Packaging - validated ***");
				//Verify Order-- Labor
				CommonFunctions.waitForElementTobeClickable(strBOMTableOrderLabor);
				CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderLabor, "Verify Order-- Labor is displayed after Master Carton Packaging- "), "Labor-Not dispalyed in correct order i.e  after Master.Assertion failed.please check");
				log.info("*** Verify Order-- Labor - validated ***");
				//Verify Order-- Miscellaneous
				CommonFunctions.waitForElementTobeClickable(strBOMTableOrderMisce);
				CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strBOMTableOrderMisce, "Verify Order-- Miscellaneous is displayed after Labor"), "Miscellaneous Not dispalyed in correct order i.e after Labor.Assertion failed.please check");
				log.info("*** Verify Order-- Miscellaneous - validated ***");
				CommonFunctions.waitForPageLoaded();
				Thread.sleep(1000);
				
			}
			CommonFunctions.waitForPageLoaded();
			Thread.sleep(1000);
			
			//Click button btnSaveAndCheckIn
			CommonFunctions.waitForElementTobeClickable(ExternalBOM.btnSaveAndCheckIn);
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to default frame 	 	
			driver.switchTo().defaultContent();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
            //Verify Header Attributes
			CommonFunctions.waitForVisibilityOfElement(headerAttributes);
			CommonFunctions.clickButtonOrLink(headerAttributes,"link", "headerAttributes");
			
			//Verify 'Factory Region' is present under 'General Attributes'
			CommonFunctions.waitForElementTobeClickable(strGeneralAttributesFactoryRegionVal);
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strGeneralAttributesFactoryRegionVal, "Factory Region is present under GeneralAttributes"), "Factory Region is Not displayed under GeneralAttributes.Assertion failed.please check");
			if((data[38].contains("BOM\\Materials\\Product\\Product\\Vendor"))&&(data[2].contains("VendorProductBOM"))){
			//Verify Factory Region
			CommonFunctions.waitForElementTobeClickable(strGeneralAttributesFactoryRegion);
    		String val = GettingText(strGeneralAttributesFactoryRegion);
    		System.out.println("********val********"+val);
    		CommonFunctions.AssertEqualsVerification(ActualValue, data[161], "Actual And Expected BOM Factory Region Are Not Matched.Assertion Failed.Please check");
			}
			else if((data[38].contains("BOM\\Materials\\Product\\Product\\Vendor"))&&(data[2].contains("Create Sourcing Config using Copy/Link"))){
				//Verify Factory Region
				CommonFunctions.waitForElementTobeClickable(strGeneralAttributesFactoryRegion);
	    		String val = GettingText(strGeneralAttributesFactoryRegion);
	    		System.out.println("********val********"+val);
	    		CommonFunctions.AssertEqualsVerification(ActualValue, data[161], "Actual And Expected BOM Factory Region Are Not Matched.Assertion Failed.Please check");
			}
		}
			catch(Exception e){
				fail=true;
				log.error("Exception in PC05AndPC06createVendorRetailItemBOM"+e);
				throw e;
			}
			return new String[] {strBOMInWorkRetail, BOMnameInWorkRetail1};

		}
	
		//Verify Approve rTask
	    public static boolean verifyApproverTask(String[] data) throws Exception
	    {
	    	try{
	    		Assert.assertEquals(driver.findElements(strSourcingConfigApproverTask).size(),1);
	    		GettingText(strSourcingConfigApproverTask);
	    		log.info(ActualValue);
	    		Assert.assertNotEquals(ActualValue, data[157]);
	    		log.info("Verified that No workflow was sent to Approver");
	    		//return true;
	    	}
	    	catch(Exception e){
	    		fail=true;
	    		log.error("Exception in verifyApproverTask"+e);
	    		return false;
	    	}
	    	return true;

	    }
		
		
		//PC05: Create Sourcing Config through Carry Forward
		
		
			public static boolean TC07_PC05CreateSourcingConfigCarryForward(String[] data) throws Exception{
			
			try{
				//******Create Product : Retail Item********
				
			      CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
				  data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
				  data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
				
			    NavigateToDetailsTab(data);
				prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
				log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum1);
				
				
			
				//Click on Sourcing tab
				CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
				//Add Source
				CommonProjectFunctions.AddSourcingConfiguration("ADVENT");
				CommonProjectFunctions.AddSourcingConfiguration("FUNSKOOL");
				//in sandbox
				//CommonProjectFunctions.AddSourcingConfiguration("FUNSKOOL");
				CommonProjectFunctions.AddSourcingConfiguration("AEQUS");
				
			    //CommonProjectFunctions.AddSource("WORLD TOYMAKER 1",data[4]);
			    //CommonProjectFunctions.AddSource("AEQUS",data[39]);
			    //CommonProjectFunctions.AddSource("FUNSKOOL",data[40]);
			    
				Thread.sleep(1000);
				/*SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(specificationtab));
				//Wait for Specification tab
				CommonFunctions.waitForVisibilityOfElement(specificationtab);
				CommonFunctions.waitForPageLoaded();
				//Wait for 	Sourcing Tab
				SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
				CommonFunctions.waitForVisibilityOfElement(SourcingTab);
				//Click on Sourcing tab
				CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
				Thread.sleep(1000);*/
			    CommonFunctions.gettingParentWindow();
				CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.actionDD);
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.actionDD, "Add Existing Sources");
				CommonFunctions.waitForPageLoaded();
				//CommonFunctions.switchToChildWindow();
				/*Set set = SeleniumDriver.driver.getWindowHandles();
				Iterator iter = set.iterator();
				String parent = (java.lang.String) iter.next();
				String child = (java.lang.String) iter.next();
				SeleniumDriver.driver.switchTo().window(child);*/
				CommonFunctions.switchingChildWindow();
				CommonFunctions.clickButtonOrLink(selectSourceCheckbox1, "Select Source-1");
				CommonFunctions.clickButtonOrLink(selectSourceCheckbox2, "Select Source-2");
				CommonFunctions.clickButtonOrLink(selectSourceCheckbox3, "Select Source-3");
				CommonFunctions.clickButtonOrLink(strSelect, "Select btn");
				CommonFunctions.switchParentWindow();
                SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
				SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
				//CommonFunctions.waitForPageLoaded();
			    /*SeleniumDriver.driver.switchTo().window(parent);
                SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");*/
				
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 3);
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 2);
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 1);
				/*CommonProjectFunctions.logOut();
				driver.quit();
				openBrowser();
				launchApp(data[154],data[155]);
				//Click on mywork
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				//driver.switchTo().frame("contentframe");
				driver.switchTo().frame("sidebarframe");
				CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
				//Click on placeholder icon
				CommonFunctions.waitForElementTobeClickable(strSourcingConfigurationTask);
				CommonFunctions.clickButtonOrLink(strSourcingConfigurationTask, "plus icon", "Sourcing Configuration Task");
				CommonFunctions.waitForElementTobeClickable(strSourcingConfigurationTaskLast);
				CommonFunctions.clickButtonOrLink(strSourcingConfigurationTaskLast, "plus icon", "Sourcing Configuration Task Last");
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				verifyApproverTask(data);
				CommonFunctions.waitForPageLoaded();
				CommonProjectFunctions.logOut();
				driver.quit();
				openBrowser();
			    launchApp(data[0],data[1]);*/
				PC05AndPC06createVendorRetailItemBOM(data);
				
				
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in PC05CreateSourcingConfigCarryForward "+e);
				throw e;
				
			}
			return true;
		}
		
		public static boolean TC08_PC06_CreateSourcingConfigFromAddExisting(String[] data) throws Exception{
			try{
				
				//******Create Product : Solid Product********
				
			      CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
				  data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
				  data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
				
			    NavigateToDetailsTab(data);
				prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
				log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum1);
				//Prerequisites for any type of product to be visible for vendor user
		        VendorProductPrerequisites(data);
				CommonProjectFunctions.logOut();
				driver.quit();
				openBrowser();
				launchApp(data[39],data[40]);
				/*
				 * For Source-A
				 */
				
				 //Navigating to product search Page
				CI296.navigateToProduct(data);
				CommonFunctions.waitForPageLoaded();
			    //Search Product
			    CommonProjectFunctions.searchProduct(prodNum1);
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
				Thread.sleep(1000);
				SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(specificationtab));
				//Wait for Specification tab
				CommonFunctions.waitForVisibilityOfElement(specificationtab);
				CommonFunctions.waitForPageLoaded();
				
				//Wait for 	Sourcing Tab
				SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
				CommonFunctions.waitForVisibilityOfElement(SourcingTab);
				//Click on Sourcing tab
				CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
				Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 1);
		        CommonFunctions.waitForPageLoaded();
		        //Assertion Verification of BOM Iteration
	    		CommonFunctions.waitForElementTobeClickable(strSouceState);
	    		GettingText(strSouceState);
	    		CommonFunctions.AssertEqualsVerification(ActualValue, data[158], "Actual And Expected Cost Sheet BOM Iteration Are Not Matched.Assertion Failed.Please check");
	    		/*CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 2);
		        CommonFunctions.waitForPageLoaded();
		        SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
				CommonFunctions.waitForVisibilityOfElement(SourcingTab);
				CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.gettingParentWindow();
				CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.actionDD);
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.actionDD, "Add Existing Sources");
			    //CommonFunctions.switchToChildWindow();
			    CommonFunctions.switchingChildWindow();
				CommonFunctions.clickButtonOrLink(selectSourceCheckbox1, "Select Source-1");
				CommonFunctions.clickButtonOrLink(strSelect, "Select btn");
				//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(strSeasonDropdown1));
				//CommonFunctions.waitForPageLoaded();
			    CommonFunctions.switchParentWindow();
                SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 1);
				//Assertion Verification of BOM Iteration
	    		CommonFunctions.waitForElementTobeClickable(strSouceState);
	    		GettingText(strSouceState);
	    		CommonFunctions.AssertEqualsVerification(ActualValue, data[158], "Actual And Expected Cost Sheet BOM Iteration Are Not Matched.Assertion Failed.Please check");
			
			
			   //Approver-1 Login
	    		CommonProjectFunctions.logOut();
				driver.quit();
				openBrowser();
				launchApp(data[154],data[155]);
				//Click on mywork
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				//driver.switchTo().frame("contentframe");
				driver.switchTo().frame("sidebarframe");
				CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
				//Click on placeholder icon
				CommonFunctions.waitForElementTobeClickable(strSourcingConfigurationTask);
				CommonFunctions.clickButtonOrLink(strSourcingConfigurationTask, "plus icon", "Sourcing Configuration Task");
				CommonFunctions.waitForElementTobeClickable(strSourcingConfigurationTaskLast);
				CommonFunctions.clickButtonOrLink(strSourcingConfigurationTaskLast, "plus icon", "Sourcing Configuration Task Last");
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				verifyApproverTask(data);
				CommonFunctions.waitForPageLoaded();
				CommonProjectFunctions.logOut();
				driver.quit();
				openBrowser();
				launchApp(data[0],data[1]);
				
				
				
				 * For Source-B
				 
				
				CI296.navigateToProduct(data);
				//Navigating to product search Page
				CommonProjectFunctions.searchProduct(prodNum1);
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
				Thread.sleep(1000);
				SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(specificationtab));
				//Wait for Specification tab
				CommonFunctions.waitForVisibilityOfElement(specificationtab);
				CommonFunctions.waitForPageLoaded();
				
				//Wait for 	Sourcing Tab
				SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
				CommonFunctions.waitForVisibilityOfElement(SourcingTab);
				//Click on Sourcing tab
				CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
				Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 2);
		        CommonFunctions.waitForPageLoaded();
		        //Assertion Verification of Source state
	    		CommonFunctions.waitForElementTobeClickable(strSouceState);
	    		GettingText(strSouceState);
	    		CommonFunctions.AssertEqualsVerification(ActualValue, data[159], "Actual And Expected Cost Sheet Source-A state Are Not Matched.Assertion Failed.Please check");
	    		CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 2);
		        CommonFunctions.waitForPageLoaded();
		        SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
				CommonFunctions.waitForVisibilityOfElement(SourcingTab);
				CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.gettingParentWindow();
				CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.actionDD);
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.actionDD, "Add Existing Sources");
			    //CommonFunctions.switchToChildWindow();
			    CommonFunctions.switchingChildWindow();
				CommonFunctions.clickButtonOrLink(selectSourceCheckbox2, "Select Source-2");
				CommonFunctions.clickButtonOrLink(strSelect, "Select btn");
				//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(strSeasonDropdown1));
				//CommonFunctions.waitForPageLoaded();
			    CommonFunctions.switchParentWindow();
                SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 2);
				//Assertion Verification of Source state
	    		CommonFunctions.waitForElementTobeClickable(strSouceState);
	    		GettingText(strSouceState);
	    		CommonFunctions.AssertEqualsVerification(ActualValue, data[159], "Actual And Expected Cost Sheet Source-A state Are Not Matched.Assertion Failed.Please check");
			
			
			 
	    		
				 * For Source-C
				 
				
	    		CI296.navigateToProduct(data);
				//Navigating to product search Page
				CommonProjectFunctions.searchProduct(prodNum1);
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
				Thread.sleep(1000);
				SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(specificationtab));
				//Wait for Specification tab
				CommonFunctions.waitForVisibilityOfElement(specificationtab);
				CommonFunctions.waitForPageLoaded();
				
				//Wait for 	Sourcing Tab
				SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
				CommonFunctions.waitForVisibilityOfElement(SourcingTab);
				//Click on Sourcing tab
				CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
				Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 3);
		        CommonFunctions.waitForPageLoaded();
		        //Assertion Verification of Source state
	    		CommonFunctions.waitForElementTobeClickable(strSouceState);
	    		GettingText(strSouceState);
	    		CommonFunctions.AssertEqualsVerification(ActualValue, data[160], "Actual And Expected Cost Sheet Source-B state Are Not Matched.Assertion Failed.Please check");
	    		CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 2);
		        CommonFunctions.waitForPageLoaded();
		        SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
				CommonFunctions.waitForVisibilityOfElement(SourcingTab);
				CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.gettingParentWindow();
				CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.actionDD);
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.actionDD, "Add Existing Sources");
			    //CommonFunctions.switchToChildWindow();
			    CommonFunctions.switchingChildWindow();
				CommonFunctions.clickButtonOrLink(selectSourceCheckbox3, "Select Source-3");
				CommonFunctions.clickButtonOrLink(strSelect, "Select btn");
				//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(strSeasonDropdown1));
				//CommonFunctions.waitForPageLoaded();
			    CommonFunctions.switchParentWindow();
                SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 3);
				//Assertion Verification of Source state
	    		CommonFunctions.waitForElementTobeClickable(strSouceState);
	    		GettingText(strSouceState);
	    		CommonFunctions.AssertEqualsVerification(ActualValue, data[160], "Actual And Expected Cost Sheet Source-B state Are Not Matched.Assertion Failed.Please check");
	    		//openBrowser();
			    //launchApp(data[0],data[1]);
	    		//Create BOM
	    		PC05AndPC06createVendorRetailItemBOM(data);
	    		*/
			}
			
			catch(Exception e){
				fail=true;
				log.error("Exception in TC08_PC06_CreateSourcingConfigFromAddExisting "+e);
				throw e;
				
			}
			return true;
		}
		//Copy/Link Assortment to Retail
		public static String copyLinkProduct(String[] data) {
			try{
				CommonFunctions.waitForPageLoaded();
				//Select 'Season'
				CommonFunctions.selectFromDropDownByVisibleText(SmokeFlow.seasonDD, data[6]);
				//Select ' Product Type'
				CommonFunctions.selectFromDropDownByVisibleText(SmokeFlow.productType,data[163]);
				//Select 'Relationship Type'
				CommonFunctions.selectFromDropDownByVisibleText(SmokeFlow.relationShipType, data[164]);
				
                CommonFunctions.waitForElementTobeClickable(strSelectSource);
                CommonFunctions.clickButtonOrLink(strSelectSource, "Select Source");
                
				//Click on Next button
				CommonFunctions.clickButtonOrLink(SmokeFlow.nextBtn, "btn", "Next");
				SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(Product.softgoodsIncluded));
				//Softgoods Included
				CommonFunctions.selectFromDropDownByVisibleText(Product.softgoodsIncluded,data[165]);
				//Electronics Included
				CommonFunctions.selectFromDropDownByVisibleText(Product.electronicsIncluded,data[166]);
				//Family Brand
			//	CommonFunctions.enterTextInTextbox(Product.familyBrand,data[70] );
				//Lower age
			//	CommonFunctions.enterTextInTextbox(Product.lowerAge,data[71]);
				//Upper age
			//	CommonFunctions.selectFromDropDownByIndex(Product.UpperAge,10);
				//Click on Next button
				CommonFunctions.clickButtonOrLink(SmokeFlow.nextBtn, "btn", "Next");
				CommonFunctions.waitForPageLoaded();
				//Click on View Product Button
				//CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View Product");
				SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(csProductNumber));
				prodNum=SeleniumDriver.driver.findElement(csProductNumber).getText();
			//	log.info("retail product:" +prodNum);
			}catch(Exception e){
				fail=true;
				SeleniumDriver.log.error("Exception in copyLinkProduct()", e);
			}
			return prodNum;
		}
		
		//Create Sourcing Config using Copy/Link
		
		public static boolean TC09_PC07_CreateSourcingConfigUsingCopyOrLink(String[] data) throws Exception{
			try{
				//******Create Product : Solid Product********
				
			      CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
				  data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
				  data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
				
			    NavigateToDetailsTab(data);
				prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
				log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum1);
				//Prerequisites for any type of product to be visible for vendor user
		        VendorProductPrerequisites(data);
				CommonProjectFunctions.logOut();
				driver.quit();
				openBrowser();
				launchApp(data[39],data[40]);
				
				CI296.navigateToProduct(data);
				//Navigating to product search Page
				CommonProjectFunctions.searchProduct(prodNum1);
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
				Thread.sleep(1000);
				SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SmokeFlow.relationShipTab));
				//Click on relationShip tab
				CommonFunctions.clickButtonOrLink(SmokeFlow.relationShipTab, "tab", "RelationShip");
				CommonFunctions.waitForPageLoaded();
				//Select Season
			    CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
		        CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
			    CommonFunctions.waitForPageLoaded();
				//Click "Copy/ Link Product"
				CommonFunctions.clickButtonOrLink(SmokeFlow.copyLinkProd, "btn", "Copy/ Link Product");
				SmokeFlow.prodNumRetail1 = copyLinkProduct(data);
				log.info("First retail product:" +SmokeFlow.prodNumRetail1);
				//Select Distribution channel
				SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(Product.distributionChannel));
				CommonFunctions.selectFromDropDownByVisibleText(Product.distributionChannel,"Mainline");
				CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View product");
				CommonFunctions.waitForPageLoaded();
		        SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
				CommonFunctions.waitForVisibilityOfElement(SourcingTab);
				CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 1);
				/*//Verify Lifecycle State-Source 1
			    CommonFunctions.waitForElementTobeClickable(strSouceLifecycleState);
	    		GettingText(strSouceLifecycleState);
	    		CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 2);
	    		CommonFunctions.AssertEqualsVerification(ActualValue, data[167], "Actual And Expected Cost Sheet Source-B state Are Not Matched.Assertion Failed.Please check");*/
	    		//Assertion Verification of Source-1 state
	    		CommonFunctions.waitForElementTobeClickable(strSouceState);
	    		GettingText(strSouceState);
	    		CommonFunctions.AssertEqualsVerification(ActualValue, data[169], "Actual And Expected Cost Sheet Source-B state Are Not Matched.Assertion Failed.Please check");
	    		log.info("*** Verification of Source-1 state(Approved) - validated ***");
	    		/*//Verify Lifecycle State-Source 2
			    CommonFunctions.waitForElementTobeClickable(strSouceLifecycleState);
	    		GettingText(strSouceLifecycleState);
	    		CommonFunctions.AssertEqualsVerification(ActualValue, data[168], "Actual And Expected Cost Sheet Source-B state Are Not Matched.Assertion Failed.Please check");
	    		log.info("*** Verification of Source-2 state(In Review) - validated ***");*/
	    		
	    		/*//Approver-1 Login
	    		CommonProjectFunctions.logOut();
				driver.quit();
				openBrowser();
				launchApp(data[154],data[155]);
				//Click on mywork
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				//driver.switchTo().frame("contentframe");
				driver.switchTo().frame("sidebarframe");
				CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
				//Click on placeholder icon
				CommonFunctions.waitForElementTobeClickable(strSourcingConfigurationTask);
				CommonFunctions.clickButtonOrLink(strSourcingConfigurationTask, "plus icon", "Sourcing Configuration Task");
				CommonFunctions.waitForElementTobeClickable(strSourcingConfigurationTaskLast);
				CommonFunctions.clickButtonOrLink(strSourcingConfigurationTaskLast, "plus icon", "Sourcing Configuration Task Last");
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				verifyApproverTask(data);
				CommonFunctions.waitForPageLoaded();*/
				/*CommonProjectFunctions.logOut();
				driver.quit();
				openBrowser();
				launchApp(data[170],data[171]);
				//Create BOM
	    		PC05AndPC06createVendorRetailItemBOM(data);*/
				
			}
			
			catch(Exception e){
				fail=true;
				log.error("Exception in TC09_PC07_CreateSourcingConfigUsingCopyOrLink "+e);
				throw e;
				
			}
			return true;
		}
		//Navigate to Vendor(Supplier)
		public static boolean navigateToVendor(String[] data) throws Exception
		{
			try{
				//Added refresh code below as to create another material BOM require this as we need to close library + sign
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("sidebarframe");
				if(driver.findElements(VendorLink).size()==0) 
				{
					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Material.libraryLink, "Link", "Library Link");
				}
				//Click on Color link
				driver.findElement(VendorLink).click();
				//Switch frame
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
				/*getPageTitle();
				CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[3],"ERROR: The Expected page is not correct.please verify");
				log.info(pageTitle +" "+"Page Appears");*/
			
	          }catch(Exception e){
				fail=true;
				log.error("Exception in navigateToVendor()", e);
				throw e;
			}
			return true;
		}
		//Search Vendor(Supplier)-Inactive
	
		public static boolean SearchVendorInactive(String[] data) throws Exception
	      {
	            try{
	            	VendorType=By.linkText(data[176]);
	                  //Choosing the Vendor(Supplier) as Supplier
	              CommonFunctions.waitForElementTobeClickable(VendorType);
	              CommonFunctions.clickButtonOrLink(VendorType, "HyperLink", "Supplier");
	              CommonFunctions.waitForPageLoaded();
	              //Entering the Vendor(Supplier) Name
	              CommonFunctions.waitForElementTobeClickable(VendorOrSupplierSearch);
	              CommonFunctions.enterTextInTextboxUpdated(VendorOrSupplierSearch, data[173], "Vendor Name in Inactive state");
	              CommonFunctions.clickButtonOrLink(SearchProductButton, "Button", "Search");
	              //wait.until(ExpectedConditions.titleIs(data[4]));
	                  //waiting for the expected Pagetitle to appear
	                  CommonFunctions.waitForPageLoaded();
	                  }
	            catch(Exception e){
	                  fail=true;
	                  log.error("Exception in SearchVendorInactive()", e);
	                  throw e;
	            }
	            return true;
	      }
		//Search Vendor(Supplier)-Unassinged
		
			public static boolean SearchVendorUnassigned(String[] data) throws Exception
		      {
		            try{
		            	VendorType=By.linkText(data[176]);
		                  //Choosing the Vendor(Supplier) as Supplier
		              CommonFunctions.waitForElementTobeClickable(VendorType);
		              CommonFunctions.clickButtonOrLink(VendorType, "HyperLink", "Supplier");
		              CommonFunctions.waitForPageLoaded();
		              //Entering the Vendor(Supplier) Name
		              CommonFunctions.waitForElementTobeClickable(VendorOrSupplierSearch);
		              CommonFunctions.enterTextInTextboxUpdated(VendorOrSupplierSearch, data[174], "Vendor Name in Inactive state");
		              CommonFunctions.clickButtonOrLink(SearchProductButton, "Button", "Search");
		              //wait.until(ExpectedConditions.titleIs(data[4]));
		                  //waiting for the expected Pagetitle to appear
		                  CommonFunctions.waitForPageLoaded();
		                  }
		            catch(Exception e){
		                  fail=true;
		                  log.error("Exception in SearchVendorUnassigned()", e);
		                  throw e;
		            }
		            return true;
		      }
		
			//Search Vendor(Supplier)-Conditionally Approved
			
			public static boolean SearchVendorConditionallyApproved(String[] data) throws Exception
		      {
		            try{
		            	VendorType=By.linkText(data[176]);
		                  //Choosing the Vendor(Supplier) as Supplier
		              CommonFunctions.waitForElementTobeClickable(VendorType);
		              CommonFunctions.clickButtonOrLink(VendorType, "HyperLink", "Supplier");
		              CommonFunctions.waitForPageLoaded();
		              //Entering the Vendor(Supplier) Name
		              CommonFunctions.waitForElementTobeClickable(VendorOrSupplierSearch);
		              CommonFunctions.enterTextInTextboxUpdated(VendorOrSupplierSearch, data[175], "Vendor Name in Inactive state");
		              CommonFunctions.clickButtonOrLink(SearchProductButton, "Button", "Search");
		              //wait.until(ExpectedConditions.titleIs(data[4]));
		                  //waiting for the expected Pagetitle to appear
		                  CommonFunctions.waitForPageLoaded();
		                  }
		            catch(Exception e){
		                  fail=true;
		                  log.error("Exception in SearchVendorConditionallyApproved()", e);
		                  throw e;
		            }
		            return true;
		      }
		
			
			//Verify Approve1 rTask
		    public static boolean verifyApprover1Task(String[] data) throws Exception
		    {
		    	try{
		    		Assert.assertEquals(driver.findElements(strSourcingConfigApproverTask).size(),1);
		    		GettingText(strSourcingConfigApproverTask);
		    		log.info(ActualValue);
		    		Assert.assertEquals(ActualValue, data[190]);
		    		log.info("Verified that workflow was sent to Approver");
		    		
		    		//return true;
		    	}
		    	catch(Exception e){
		    		fail=true;
		    		log.error("Exception in verifyApproverTask"+e);
		    		return false;
		    	}
		    	return true;

		    }
		    
		  /*//Verify Approve2 rTask
		    public static boolean verifyApprover2Task(String[] data) throws Exception
		    {
		    	try{
		    		Assert.assertEquals(driver.findElements(strSourcingConfigApproverTask).size(),1);
		    		GettingText(strSourcingConfigApproverTask);
		    		log.info(ActualValue);
		    		Assert.assertEquals(ActualValue, data[191]);
		    		log.info("Verified that workflow was sent to Approver");
		    		//return true;
		    	}
		    	catch(Exception e){
		    		fail=true;
		    		log.error("Exception in verifyApproverTask"+e);
		    		return false;
		    	}
		    	return true;

		    }*/
		      
		  //Create Multiple Sourcing Config : Retail
			
		    public static boolean TC12_PC08_CreateMultipleSourcingConfigsRetailItem(String[] data) throws Exception{
				try{	
					
					//******Create Product : Solid Product********
					
				      CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
					  data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
					  data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
					
				    NavigateToDetailsTab(data);
					prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
					log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum1);
					//Prerequisites for any type of product to be visible for vendor user
			        //VendorProductPrerequisitesTC10(data);
			        
			        CommonProjectFunctions.logOut();
				    //****Sourcing Lead****//
					openBrowser();
					Thread.sleep(2000);
					launchApp(data[39],data[40]);
					
					
					
					
		    
						//Navigating to product search Page
			    		CI296.navigateToProduct(data);
						//Search for Product
						CommonProjectFunctions.searchProduct(prodNum1);
						CommonFunctions.waitForPageLoaded();
						driver.switchTo().defaultContent();
						driver.switchTo().frame("contentframe");
						CommonFunctions.waitForPageLoaded();
						Thread.sleep(1000);
					    //Wait for 	Sourcing Tab
						SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						CommonFunctions.waitForVisibilityOfElement(SourcingTab);
						//Click on Sourcing tab
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
						CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						//CommonFunctions.gettingParentWindow();
						CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.actionDD);
						CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.actionDD, "Add Multiple Sourcing Configurations");
					    //CommonFunctions.switchingChildWindow();
					    CommonFunctions.waitForPageLoaded();
					    SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingConfig.search));
					    CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
					    CommonFunctions.waitForPageLoaded();
					    SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(strSortOptions));
					    CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+sourcename1+"')]/preceding::input[1]"), "Supplier selected");
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+sourcename2+"')]/preceding::input[1]"), "Supplier selected");
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+sourcename3+"')]/preceding::input[1]"), "Supplier selected");
						//CommonFunctions.clickButtonOrLink(selectSourceCheckbox2, "Select Source-2");
						CommonFunctions.clickButtonOrLink(strSelect, "Select btn");
						CommonFunctions.waitForPageLoaded();
						//Source-1:Approver levels
						SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(strselectsourcenameAApprover1));
						CommonFunctions.waitForVisibilityOfElement(strselectsourcenameAApprover1);
						CommonFunctions.selectFromDropDownByIndex(strselectsourcenameAApprover1, 10);
						CommonFunctions.waitForVisibilityOfElement(strselectsourcenameAApprover2);
						CommonFunctions.selectFromDropDownByIndex(strselectsourcenameAApprover2,11);
						//Source-2:Approver Levels
						CommonFunctions.waitForVisibilityOfElement(strselectsourcenameBApprover1);
						CommonFunctions.selectFromDropDownByIndex(strselectsourcenameBApprover1, 10);
						CommonFunctions.waitForVisibilityOfElement(strselectsourcenameBApprover2);
						CommonFunctions.selectFromDropDownByIndex(strselectsourcenameBApprover2, 11);
						//Source-3:Approver Levels
						CommonFunctions.waitForVisibilityOfElement(strselectsourcenameCApprover1);
						CommonFunctions.selectFromDropDownByIndex(strselectsourcenameCApprover1, 10);
						CommonFunctions.waitForVisibilityOfElement(strselectsourcenameCApprover2);
						CommonFunctions.selectFromDropDownByIndex(strselectsourcenameCApprover2,11);
						//Enter value for Name:Supplier1 field
						CommonFunctions.waitForElementTobeClickable(strselectsourcenameAName);
			            CommonFunctions.enterTextInTextboxUpdated(strselectsourcenameAName, data[186], "Supplier-A Name");
			            //Enter value for Name:Supplier2 field
						CommonFunctions.waitForElementTobeClickable(strselectsourcenameBName);
			            CommonFunctions.enterTextInTextboxUpdated(strselectsourcenameBName, data[183], "Supplier-B Name");
			            //Enter value for Name:Supplier3 field
						CommonFunctions.waitForElementTobeClickable(strselectsourcenameCName);
			            CommonFunctions.enterTextInTextboxUpdated(strselectsourcenameCName, data[184], "Supplier-B Name");
			            //Click on Save
						CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
						CommonFunctions.clickButtonOrLink(strCloseButton, "img", "Close");
						
						//Verify the Supplier State :
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
						CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 1);
				        CommonFunctions.waitForPageLoaded();
				        //Assertion Verification of Supplier Status
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[185], "Actual And Expected Source state Are Not Matched.Assertion Failed.Please check");
			    		log.info("*** Verification of Supplier-1 Status In Review - validated ***");
			    		CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
						CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 2);
				        CommonFunctions.waitForPageLoaded();
				        //Assertion Verification of Supplier Status
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[185], "Actual And Expected Source state Are Not Matched.Assertion Failed.Please check");
			    		log.info("*** Verification of Supplier-2 Status In Review - validated ***");
			    		//Verify the Supplier State :
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
						CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 3);
				        CommonFunctions.waitForPageLoaded();
				        //Assertion Verification of Supplier Status
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[185], "Actual And Expected Source state Are Not Matched.Assertion Failed.Please check");
			    		log.info("*** Verification of Supplier -3 Status In Review - validated ***");
						
			    		}
					
					
				catch(Exception e){
					fail=true;
					log.error("Exception in TC12_PC08_CreateMultipleSourcingConfigsRetailItem "+e);
					throw e;
					
				}
				return true;
				}
		//Create Multiple Sourcing Config : Assortment
		
		    public static boolean TC10_PC04_CreateMultipleSourcingConfigs(String[] data) throws Exception{
				try{	
		    
						/*Inactive
						navigateToVendor(data);
						SearchVendorInactive(data);
						//Validate Vendor Status-Inactive
						CommonFunctions.waitForElementTobeClickable(strSupplierStatusInactive);
			    		GettingText(strSupplierStatusInactive);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[177], "Actual And Expected Vendor(Inactive) state Are Not Matched.Assertion Failed.Please check");
			    		Unassigned
			    		navigateToVendor(data);
			    		SearchVendorUnassigned(data);
						//Validate Vendor Status-Unassigned
						CommonFunctions.waitForElementTobeClickable(strSupplierStatusInactive);
			    		GettingText(strSupplierStatusInactive);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[178], "Actual And Expected Vendor(Unassigned) state Are Not Matched.Assertion Failed.Please check");
			    		*/
			    		
			    		
			    		//******Create Product : Solid Product********
						
					      CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
						  data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
						  data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
						
					    NavigateToDetailsTab(data);
						prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
						log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum1);
						//Prerequisites for any type of product to be visible for vendor user
				        //VendorProductPrerequisitesTC10(data);
				        
				        CommonProjectFunctions.logOut();
					    //****Sourcing Lead****//
						openBrowser();
						Thread.sleep(2000);
						launchApp(data[39],data[40]);
				        
			    		//Navigating to product search Page
			    		CI296.navigateToProduct(data);
						//Search for Product
						CommonProjectFunctions.searchProduct(prodNum1);
						CommonFunctions.waitForPageLoaded();
						driver.switchTo().defaultContent();
						driver.switchTo().frame("contentframe");
						CommonFunctions.waitForPageLoaded();
						Thread.sleep(1000);
					    //Wait for 	Sourcing Tab
						SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						CommonFunctions.waitForVisibilityOfElement(SourcingTab);
						//Click on Sourcing tab
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
						CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						//CommonFunctions.gettingParentWindow();
						CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.actionDD);
						CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.actionDD, "Add Multiple Sourcing Configurations");
					    //CommonFunctions.switchingChildWindow();
					    CommonFunctions.waitForPageLoaded();
					    SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingConfig.search));
					    CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
					    CommonFunctions.waitForPageLoaded();
					    SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(strSortOptions));
					    CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+sourcename1+"')]/preceding::input[1]"), "Supplier selected");
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+sourcename2+"')]/preceding::input[1]"), "Supplier selected");
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+sourcename3+"')]/preceding::input[1]"), "Supplier selected");
						//CommonFunctions.clickButtonOrLink(selectSourceCheckbox2, "Select Source-2");
						CommonFunctions.clickButtonOrLink(strSelect, "Select btn");
						CommonFunctions.waitForPageLoaded();
						//Source-1:Approver levels
						SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(strselectsourcenameAApprover1));
						CommonFunctions.waitForVisibilityOfElement(strselectsourcenameAApprover1);
						CommonFunctions.selectFromDropDownByIndex(strselectsourcenameAApprover1, 10);
						CommonFunctions.waitForVisibilityOfElement(strselectsourcenameAApprover2);
						CommonFunctions.selectFromDropDownByIndex(strselectsourcenameAApprover2, 11);
						//Source-2:Approver Levels
						CommonFunctions.waitForVisibilityOfElement(strselectsourcenameBApprover1);
						CommonFunctions.selectFromDropDownByIndex(strselectsourcenameBApprover1, 10);
						CommonFunctions.waitForVisibilityOfElement(strselectsourcenameBApprover2);
						CommonFunctions.selectFromDropDownByIndex(strselectsourcenameBApprover2, 11);
						//Source-3:Approver Levels
						CommonFunctions.waitForVisibilityOfElement(strselectsourcenameCApprover1);
						CommonFunctions.selectFromDropDownByIndex(strselectsourcenameCApprover1, 10);
						CommonFunctions.waitForVisibilityOfElement(strselectsourcenameCApprover2);
						CommonFunctions.selectFromDropDownByIndex(strselectsourcenameCApprover2, 11);
						//Enter value for Name:Supplier1 field
						CommonFunctions.waitForElementTobeClickable(strselectsourcenameAName);
			            CommonFunctions.enterTextInTextboxUpdated(strselectsourcenameAName, data[186], "Supplier-A Name");
			            //Enter value for Name:Supplier2 field
						CommonFunctions.waitForElementTobeClickable(strselectsourcenameBName);
			            CommonFunctions.enterTextInTextboxUpdated(strselectsourcenameBName, data[183], "Supplier-B Name");
			            
			           //Enter value for Name:Supplier3 field
						CommonFunctions.waitForElementTobeClickable(strselectsourcenameCName);
			            CommonFunctions.enterTextInTextboxUpdated(strselectsourcenameCName, data[184], "Supplier-C Name");
			            
			            //Click on Save
						CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
						CommonFunctions.clickButtonOrLink(strCloseButton, "img", "Close");
						//Verify the Supplier State :
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
						CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 1);
				        CommonFunctions.waitForPageLoaded();
				        //Assertion Verification of Supplier Status
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[185], "Actual And Expected Source state Are Not Matched.Assertion Failed.Please check");
			    		log.info("*** Verification of Supplier-1 Status In Review - validated ***");
			    		CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
						CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 2);
				        CommonFunctions.waitForPageLoaded();
				        //Assertion Verification of Supplier Status
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[185], "Actual And Expected Source state Are Not Matched.Assertion Failed.Please check");
			    		log.info("*** Verification of Supplier-2 Status In Review - validated ***");
			    		//Verify the Supplier State :
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
						CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 3);
				        CommonFunctions.waitForPageLoaded();
				        //Assertion Verification of Supplier Status
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[185], "Actual And Expected Source state Are Not Matched.Assertion Failed.Please check");
			    		log.info("*** Verification of Supplier -3 Status In Review - validated ***");
			    		}
					
					
				catch(Exception e){
					fail=true;
					log.error("Exception in TC10_PC04_CreateMultipleSourcingConfigs "+e);
					throw e;
					
				}
				return true;
				}
		    
		    
		    
		    //PC57 : Validate BOM Changes on Vendor Product Cost Sheet
		    
					public static boolean PC57_ValidateBOMChangesOnVendorProductCostSheet(String [] data) throws Exception{
						try{
					CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				    //Search Product
				    CommonProjectFunctions.searchProduct(data[191]);
				    CommonFunctions.waitForPageLoaded();
					//Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					
					//Navigate to Sourcing Tab
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Navigate to Costing Tab
					CommonFunctions.waitForVisibilityOfElement(CostingTab);
				    CommonFunctions.clickButtonOrLink(CostingTab, "link", "CostingTab");
					CommonFunctions.waitForPageLoaded();
					//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.costAction));
					//Note Master Carton Packaging Material value
					GettingText(masterCartonPackagingMaterialUSDValue);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[192], "Actual And Expected Cost Sheet MasterCartonPackagingMaterialUSDValue Are Not Matched.Assertion Failed.Please check");
					//Note Master Carton Packaging Labor Cost value
					CommonFunctions.waitForElementTobeClickable(masterCartonLaborCostUSD);
					GettingText(masterCartonLaborCostUSD);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[193], "Actual And Expected Cost Sheet MasterCartonLaborCostUSD Are Not Matched.Assertion Failed.Please check");
					//Note Miscellaneous Material value
					CommonFunctions.waitForElementTobeClickable(miscelleneousMaterialValue);
					GettingText(miscelleneousMaterialValue);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[194], "Actual And Expected Cost Sheet MiscelleneousMaterialValueAre Not Matched.Assertion Failed.Please check");
					//Note Product Markup value
					CommonFunctions.waitForElementTobeClickable(productMarkUpValue);
					GettingText(productMarkUpValue);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[195], "Actual And Expected Cost Sheet ProductMarkUpValue Are Not Matched.Assertion Failed.Please check");
				    CommonProjectFunctions.searchProduct(data[191]);
					CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					
					Thread.sleep(1000);
					//Navigate to Specifications tab
					wait.until(ExpectedConditions.visibilityOfElementLocated(specificationtab));
					CommonFunctions.waitForPageLoaded();
					//Wait for Specification tab
					CommonFunctions.waitForVisibilityOfElement(specificationtab);
					CommonFunctions.clickButtonOrLink(specificationtab, "Specification tab");
					CommonFunctions.waitForPageLoaded();
					//Navigate to Materials sub-tab
					CommonFunctions.waitForVisibilityOfElement(materialstab);
					CommonFunctions.clickButtonOrLink(materialstab, "Material tab");
					CommonFunctions.waitForPageLoaded();
					//Select Season -  
					wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
					//Wait for Season dropdown 
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					//Click on Season dropdown value
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					/*CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSource);
					CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 1);
			        CommonFunctions.waitForPageLoaded();*/
			        //Select Specifications
			        CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
					CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
					CommonFunctions.waitForPageLoaded();
					//Update BOM
					CommonFunctions.waitForElementTobeClickable(strUpdateBOM);
					CommonFunctions.clickButtonOrLink(strUpdateBOM, "Update BOM");
				
					
					//****Update costs in Master Carton Packaging Material section****//
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);

					//Master Carton Packaging-Coasting(full) view
					CommonFunctions.waitForElementTobeClickable(imgViewMaster);
					CommonFunctions.clickButtonOrLink(imgViewMaster, "image", "View for Master Carton");
					CommonFunctions.waitForElementTobeClickable(VendorBOMTablesView);
					CommonFunctions.clickButtonOrLink(VendorBOMTablesView, "link", "Packaging Costing Full View");
					
					//Enter unit Price -Master Carton Packaging
					wait.until(ExpectedConditions.visibilityOfElementLocated(mCPackagingCellUnitPrice));
					CommonFunctions.clickButtonOrLink(mCPackagingCellUnitPrice, "table cell", "unitPrice");
					CommonFunctions.waitForElementTobeClickable(mCPackagingInputUnitPrice);
					CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputUnitPrice, data[197],"Unit Price Value for Plastics");

					//Enter Usage per K-Master Carton Packaging
					CommonFunctions.waitForElementTobeClickable(mCPackagingCellUsagePerK);
					CommonFunctions.clickButtonOrLink(mCPackagingCellUsagePerK, "table cell", "unitPerK");
					CommonFunctions.waitForElementTobeClickable(mCPackagingInputUsagePerK);
					CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputUsagePerK, data[198],"Usage Per K Value for plastics");

					//Enter Markup - Master Carton Packaging
					wait.until(ExpectedConditions.visibilityOfElementLocated(mCPackagingCellMarkUp));
					CommonFunctions.clickButtonOrLink(mCPackagingCellMarkUp, "table cell", "Product Markup for Master Carton table");
					CommonFunctions.waitForElementTobeClickable(mCPackagingInputMarkUp);
					CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputMarkUp, data[199],"Product Markup for Master Carton table");
					
					
					//*****Update costs in Master Carton Packaging Labor section****//
					//Labour-Costing(full) view
					CommonFunctions.waitForElementTobeClickable(imgViewLabour);
					CommonFunctions.clickButtonOrLink(imgViewLabour, "image", "View for Labour");
					CommonFunctions.waitForElementTobeClickable(VendorBOMTablesView);
					CommonFunctions.clickButtonOrLink(VendorBOMTablesView, "link", " Labour Costing Full View");
					
					//Enter unit Price -Labor
					wait.until(ExpectedConditions.visibilityOfElementLocated(laborCellUnitPrice));
					CommonFunctions.clickButtonOrLink(laborCellUnitPrice, "table cell", "unitPrice");
					CommonFunctions.waitForElementTobeClickable(laborInputUnitPrice);
					CommonFunctions.enterTextInTextboxUpdated(laborInputUnitPrice, data[200],"Unit Price Value for Plastics");

					//Enter Usage per K-Labor
					CommonFunctions.waitForElementTobeClickable(laborCellUsagePerK);
					CommonFunctions.clickButtonOrLink(laborCellUsagePerK, "table cell", "unitPerK");
					CommonFunctions.waitForElementTobeClickable(laborInputUsagePerK);
					CommonFunctions.enterTextInTextboxUpdated(laborInputUsagePerK, data[201],"Usage Per K Value for plastics");

					//Enter Markup - Labor
					wait.until(ExpectedConditions.visibilityOfElementLocated(laborCellMarkUp));
					CommonFunctions.clickButtonOrLink(laborCellMarkUp, "table cell", "Product Markup for Labor table");
					CommonFunctions.waitForElementTobeClickable(laborInputMarkUp);
					CommonFunctions.enterTextInTextboxUpdated(laborInputMarkUp, data[202],"Product Markup for Labor table");
					
					
					//****Update costs in Miscellaneous Material section****//
					// Miscellaneous-Costing(full) view
					CommonFunctions.waitForElementTobeClickable(imgViewMiscellaneous);
					CommonFunctions.clickButtonOrLink(imgViewMiscellaneous, "image", "View for  Miscellaneous");
					CommonFunctions.waitForElementTobeClickable(VendorBOMTablesView);
					CommonFunctions.clickButtonOrLink(VendorBOMTablesView, "link", "  Miscellaneous Costing Full View");
					
					
					//Enter unit Price -Miscellaneous
					wait.until(ExpectedConditions.visibilityOfElementLocated(miscellaneousCellUnitPrice));
					CommonFunctions.clickButtonOrLink(miscellaneousCellUnitPrice, "table cell", "unitPrice");
					CommonFunctions.waitForElementTobeClickable(miscellaneousInputUnitPrice);
					CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputUnitPrice, data[203],"Unit Price Value for Miscellaneous");
					 
					//Enter Usage Per K- Miscellaneous
					CommonFunctions.waitForElementTobeClickable(miscellaneousCellUsagePerK);
					CommonFunctions.clickButtonOrLink(miscellaneousCellUsagePerK, "table cell", "unitPerK");
					CommonFunctions.waitForElementTobeClickable(miscellaneousInputUsagePerK);
					CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputUsagePerK, data[204],"Usage Per K Value for Miscellaneous");

					//Enter Markup - Miscellaneous
					wait.until(ExpectedConditions.visibilityOfElementLocated(miscellaneousCellMarkUp));
					CommonFunctions.clickButtonOrLink(miscellaneousCellMarkUp, "table cell", "Product Markup for Miscellaneous table");
					CommonFunctions.waitForElementTobeClickable(miscellaneousInputMarkUp);
					CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputMarkUp, data[205],"Product Markup for Miscellaneous table");
					//Click on Header Attributes
					CommonFunctions.waitForVisibilityOfElement(strHeaderAttributes);
					CommonFunctions.clickButtonOrLink(strHeaderAttributes,"link", "headerAttributes");
					//Select Quote Currency - USD
					CommonFunctions.selectFromDropDownByVisibleText(currency, data[196]);
					//Click on Save and Check In
					CommonFunctions.waitForElementTobeClickable(ExternalBOM.btnSaveAndCheckIn);
					CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.waitForPageLoaded();
					//Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForPageLoaded()	;
					/*CommonFunctions.waitForVisibilityOfElement(headerAttributes);
					CommonFunctions.clickButtonOrLink(headerAttributes,"link", "headerAttributes");*/
					//Click on Header Attributes
					CommonFunctions.waitForVisibilityOfElement(headerAttributes);
					CommonFunctions.clickButtonOrLink(headerAttributes,"link", "headerAttributes");
					//Note Quote Currency
					CommonFunctions.waitForElementTobeClickable(strQuoteCurrencyBOM);
		    		GettingText(strQuoteCurrencyBOM);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[196], "Actual And Expected Cost Sheet Quote Currency Are Not Matched.Assertion Failed.Please check");
		    		log.info("***Verified Quote Currency****");
		    		//Note Master Carton Packaging Material value
		    		CommonFunctions.waitForElementTobeClickable(masterCartonPackagingMaterialUSDValueBOM);
					GettingText(masterCartonPackagingMaterialUSDValueBOM);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[206], "Actual And Expected Cost Sheet MasterCartonPackagingMaterialUSDValue Are Not Matched.Assertion Failed.Please check");
					log.info("***Verified Master Carton Packaging Material****");
					//Note Master Carton Packaging Labor Material value
					CommonFunctions.waitForElementTobeClickable(masterCartonLaborCostUSDBOM);
					GettingText(masterCartonLaborCostUSDBOM);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[207], "Actual And Expected Cost Sheet MasterCartonLaborCostUSD Are Not Matched.Assertion Failed.Please check");
					log.info("***Verified Master Carton Packaging Labor Cost****");
					//Note Miscellaneous Material value
					CommonFunctions.waitForElementTobeClickable(miscelleneousMaterialValueBOM);
					GettingText(miscelleneousMaterialValueBOM);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[208], "Actual And Expected Cost Sheet MiscelleneousMaterialValueAre Not Matched.Assertion Failed.Please check");
					log.info("***Verified Miscellaneous Material****");
					//Note Product Markup Material value
					CommonFunctions.waitForElementTobeClickable(productMarkUpValueBOM);
					GettingText(productMarkUpValueBOM);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[209], "Actual And Expected Cost Sheet ProductMarkUpValue Are Not Matched.Assertion Failed.Please check");
					log.info("***Verified Product Markup****");
					CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				    //Search Product
				    CommonProjectFunctions.searchProduct(data[191]);
				    CommonFunctions.waitForPageLoaded();
					//Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForPageLoaded()	;
					
					//Navigate to Sourcing tab
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Navigate to Costing sub-tab
					CommonFunctions.waitForVisibilityOfElement(CostingTab);
				    CommonFunctions.clickButtonOrLink(CostingTab, "link", "CostingTab");
					CommonFunctions.waitForPageLoaded();
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.costAction));
					//Validate Quote Currency
					CommonFunctions.waitForElementTobeClickable(strQuoteCurrency);
		    		GettingText(strQuoteCurrency);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[196], "Actual And Expected Cost Sheet Quote Currency Are Not Matched.Assertion Failed.Please check");
					log.info("***Verified Quote Currency****");
		    		//Validate Master Carton Packaging Material
		    		CommonFunctions.waitForElementTobeClickable(masterCartonPackagingMaterialUSDValue);
		    		GettingText(masterCartonPackagingMaterialUSDValue);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[206], "Actual And Expected Cost Sheet MasterCartonPackagingMaterialUSDValue Are Not Matched.Assertion Failed.Please check");
					log.info("***Verified Master Carton Packaging Material****");
					//Validate Master Carton Packaging Labor Cost
					CommonFunctions.waitForElementTobeClickable(masterCartonLaborCostUSD);
					GettingText(masterCartonLaborCostUSD);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[207], "Actual And Expected Cost Sheet MasterCartonLaborCostUSD Are Not Matched.Assertion Failed.Please check");
					log.info("***Verified Master Carton Packaging Labor Cost****");
					//Validate Miscellaneous Material
					CommonFunctions.waitForElementTobeClickable(miscelleneousMaterialValue);
					GettingText(miscelleneousMaterialValue);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[208], "Actual And Expected Cost Sheet MiscelleneousMaterialValueAre Not Matched.Assertion Failed.Please check");
					log.info("***Verified Miscellaneous Material****");
					//Validate Product Markup
					CommonFunctions.waitForElementTobeClickable(productMarkUpValue);
					GettingText(productMarkUpValue);
					CommonFunctions.AssertEqualsVerification(ActualValue, data[209], "Actual And Expected Cost Sheet ProductMarkUpValue Are Not Matched.Assertion Failed.Please check");
					log.info("***Verified Product Markup****");
						}
						catch(Exception e){
							fail=true;
							log.error("Exception in PC57_ValidateBOMChangesOnVendorProductCostSheet"+e);
							throw e;
						}
						 return true;
			}
		    
		    
		  //PC58 - Validate BOM Changes on Vendor Retail Item Cost Sheet
			public static boolean PC58_ValidateBOMChangesOnVendorRetailItemCostSheet(String [] data) throws Exception{
				try{
					CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				    //Search Product
				    CommonProjectFunctions.searchProduct(data[191]);
				    CommonFunctions.waitForPageLoaded();
					//Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForPageLoaded()	;
					//Navigate to Sourcing Tab
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Navigate to Costing Tab
					CommonFunctions.waitForVisibilityOfElement(CostingTab);
				    CommonFunctions.clickButtonOrLink(CostingTab, "link", "CostingTab");
					CommonFunctions.waitForPageLoaded();
					//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.costAction));
					//Note Quote Currency
		    		CommonFunctions.waitForElementTobeClickable(strQuoteCurrency);
		    		GettingText(strQuoteCurrency);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[196], "Actual And Expected Cost Sheet Quote Currency Are Not Matched.Assertion Failed.Please check");
		    		//Note Plastic Material
		    		CommonFunctions.waitForElementTobeClickable(strPlasticValue);
		    		GettingText(strPlasticValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[210], "Actual And Expected Cost Sheet Plastic material Are Not Matched.Assertion Failed.Please check");
		    		//Note Purchased Material
		    		CommonFunctions.waitForElementTobeClickable(strPurchasedValue);
		    		GettingText(strPurchasedValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[211], "Actual And Expected Cost Sheet Purchased Material Not Matched.Assertion Failed.Please check");
				    //Note Soft Goods Material
		    		CommonFunctions.waitForElementTobeClickable(strSGValue);
		    		GettingText(strSGValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[212], "Actual And Expected Cost Sheet Soft Goods Material Are Not Matched.Assertion Failed.Please check");
		    		//Note Chemical Material
		    		CommonFunctions.waitForElementTobeClickable(strChemicalValue);
		    		GettingText(strChemicalValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[213], "Actual And Expected Cost Sheet Chemical Material Are Not Matched.Assertion Failed.Please check");
		    		//Note General / Deco Labor Cost
		    		CommonFunctions.waitForElementTobeClickable(strGenDecLabValue);
		    		GettingText(strGenDecLabValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[214], "Actual And Expected Cost Sheet General / Deco Labor Cost Are Not Matched.Assertion Failed.Please check");
		    		//Note Molding Labor Cost
		    		CommonFunctions.waitForElementTobeClickable(strMoldingLabValue);
		    		GettingText(strMoldingLabValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[215], "Actual And Expected Cost Sheet Molding Labor Cost Are Not Matched.Assertion Failed.Please check");
		    		//Note Electronic Material
		    		CommonFunctions.waitForElementTobeClickable(strElectronicValue);
		    		GettingText(strElectronicValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[216], "Actual And Expected Cost Sheet Electronic Material Are Not Matched.Assertion Failed.Please check");
		    		//Note Packaging Material
		    		CommonFunctions.waitForElementTobeClickable(strPackagingValue);
		    		GettingText(strPackagingValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[217], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
		    		//Note Markup
		    		CommonFunctions.waitForElementTobeClickable(strProductMarkupCost);
		    		GettingText(strProductMarkupCost);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[218], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
		    		CommonFunctions.waitForPageLoaded();
		    		CommonProjectFunctions.searchProduct(data[191]);
					CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForPageLoaded();
					Thread.sleep(1000);
					
					//Navigate to Specifications tab
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(specificationtab));
					//Wait for Specification tab
					CommonFunctions.waitForVisibilityOfElement(specificationtab);
					CommonFunctions.clickButtonOrLink(specificationtab, "Specification tab");
					CommonFunctions.waitForPageLoaded();
					//Navigate to Materials sub-tab
					CommonFunctions.waitForVisibilityOfElement(materialstab);
					CommonFunctions.clickButtonOrLink(materialstab, "Material tab");
					CommonFunctions.waitForPageLoaded();
					//Select Season -  
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
					//Wait for Season dropdown 
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					//Click on Season dropdown value
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					
			        //Select Specifications
			        CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
					CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
					CommonFunctions.waitForPageLoaded();
					//Update BOM
					CommonFunctions.waitForElementTobeClickable(strUpdateBOM);
					CommonFunctions.clickButtonOrLink(strUpdateBOM, "Update BOM");
				    Thread.sleep(1000);
					
					//****Update costs in Plastics****//
					CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
					CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);

					
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
					CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);
					   
					  //Enter unit Price-Plastics
					
					   /*//Plastics-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewPlastics);
					   CommonFunctions.clickButtonOrLink(imgViewPlastics, "image", "View for Plastics");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View");*/
					   
					   wait.until(ExpectedConditions.visibilityOfElementLocated(CI253.cellUnitPrice));
					   CommonFunctions.clickButtonOrLink(CI253.cellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(CI253.inputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(CI253.inputUnitPrice, data[220],"Unit Price Value for Plastics");
					
					   //Enter usage per K-Plastics
					   CommonFunctions.waitForElementTobeClickable(CI253.cellUsagePerK);
					   CommonFunctions.clickButtonOrLink(CI253.cellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(CI253.inputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(CI253.inputUsagePerK, data[221],"Usage Per K Value for plastics");
					   
					   //Enter Product Markup-Plastics
					   CommonFunctions.waitForElementTobeClickable(plasticCellMarkup);
					   CommonFunctions.clickButtonOrLink(plasticCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(plasticInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(plasticInputMarkup, data[222],"Markup Value for plastics");
					
					  /* //Chemicals-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewChemical);
					   CommonFunctions.clickButtonOrLink(imgViewChemical, "image", "View for Chemicals");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Chemicals Costing Full View");*/
					   
					   //Enter unit Price-Chemicals
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(chemicalCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(chemicalInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUnitPrice, data[223],"Unit Price Value for Chemicals");
					   CommonFunctions.waitForPageLoaded();
					   //Enter usage per K-Chemicals
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(chemicalCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(chemicalInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUsagePerK, data[224],"Usage Per K Value for Chemicals");
					   //Enter Product Markup-Chemicals
					   CommonFunctions.waitForElementTobeClickable(chemicalCellMarkup);
					   CommonFunctions.clickButtonOrLink(chemicalCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(chemicalInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(chemicalInputMarkup, data[225],"Markup Value for Chemicals");
					   
					   /*/Purchased Parts-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewPurchased);
					   CommonFunctions.clickButtonOrLink(imgViewPurchased, "image", "View for Purchased Parts");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Purchased Parts Costing Full View");*/

					   //Enter unit Price-Purchased Parts
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(purchasedCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(purchasedInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUnitPrice, data[226],"Unit Price Value for Purchased Parts");
					   //Enter usage per K-Purchased Parts
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(purchasedCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(purchasedInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUsagePerK, data[227],"Usage Per K Value for Purchased Parts");
					   //Enter Product Markup-Purchased Parts
					   CommonFunctions.waitForElementTobeClickable(purchasedCellMarkup);
					   CommonFunctions.clickButtonOrLink(purchasedCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(purchasedInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(purchasedInputMarkup, data[228],"Markup Value for Purchased");
					   
					   /*//Electronics-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewElectronics);
					   CommonFunctions.clickButtonOrLink(imgViewElectronics, "image", "View for Electronics");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Electronics Costing Full View");*/

					   //Enter unit Price-Electronics
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(electCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(electInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(electInputUnitPrice, data[229],"Unit Price Value for Electronics");
					   //Enter usage per K-Electronics
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(electCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(electInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(electInputUsagePerK, data[230],"Usage Per K Value for Electronics");
					   //Enter Product Markup-Electronics
					   CommonFunctions.waitForElementTobeClickable(electCellMarkup);
					   CommonFunctions.clickButtonOrLink(electCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(electInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(electInputMarkup, data[231],"Markup Value for Electronics");
					   
					 /*  //Soft Goods-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewSG);
					   CommonFunctions.clickButtonOrLink(imgViewSG, "image", "View for Electronics");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Soft Goods Costing Full View");*/

					   //Enter unit Soft Goods
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(sGCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(sGInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(sGInputUnitPrice, data[232],"Unit Price Value for Soft Goods");
					   //Enter usage per K-Soft Goods
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(sGCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(sGInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(sGInputUsagePerK, data[233],"Usage Per K Value for Soft Goods");
					   //Enter Product Markup-Soft Goods
					   CommonFunctions.waitForElementTobeClickable(sGCellMarkup);
					   CommonFunctions.clickButtonOrLink(sGCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(sGInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(sGInputMarkup, data[234],"Markup Value for Soft Goods");
					   
					   
					   /*//Pacakaging-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewPackaging);
					   CommonFunctions.clickButtonOrLink(imgViewPackaging, "image", "View for Pacakaging");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Pacakaging Costing Full View");*/
					   //Enter unit Pacakaging
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(packagingCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(packagingInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(packagingInputUnitPrice, data[235],"Unit Price Value for Pacakaging");
					   //Enter usage per Packaging
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(packagingCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(packagingInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(packagingInputUsagePerK, data[236],"Usage Per K Value for Pacakaging");
					   //Enter Product Markup-Soft Goods
					   CommonFunctions.waitForElementTobeClickable(packagingCellMarkup);
					   CommonFunctions.clickButtonOrLink(packagingCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(packagingInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(packagingInputMarkup, data[237],"Markup Value for Pacakaging");

					   /*//Gen/Deco Labor-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewGenDecLabor);
					   CommonFunctions.clickButtonOrLink(imgViewGenDecLabor, "image", "View for Gen/Deco Labor");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
					   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Gen/Deco Labor Costing Full View");*/
					   //Enter unit General Decor Labour
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(generalLabourCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(generalLabourInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUnitPrice, data[238],"Unit Price Value for General/Deco Labor");
					   //Enter usage per General Decor Labour
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(generalLabourCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(generalLabourInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUsagePerK, data[239],"Usage Per K Value for General/Deco Labor");
					   //Enter Product Markup-General Decor Labour
					   CommonFunctions.waitForElementTobeClickable(generalCellMarkup);
					   CommonFunctions.clickButtonOrLink(generalCellMarkup, "table cell", "Markup");
					   CommonFunctions.waitForElementTobeClickable(generalInputMarkup);
					   CommonFunctions.enterTextInTextboxUpdated(generalInputMarkup, data[240],"Markup Value for General/Deco Labor");
	                   
					   /*//Molding Labor-Coasting(full) view
					   CommonFunctions.waitForElementTobeClickable(imgViewMoldingLabor);
					   CommonFunctions.clickButtonOrLink(imgViewMoldingLabor, "image", "View for Molding Labor");
					   CommonFunctions.waitForElementTobeClickable(BOMTablesViewMoldingFullView);
					   CommonFunctions.clickButtonOrLink(BOMTablesViewMoldingFullView, "link", "Molding Labor Vendor Full Molding Labor View");*/
					   //Enter unit Molding Labour
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUnitPrice));
					   CommonFunctions.clickButtonOrLink(moldingCellUnitPrice, "table cell", "unitPrice");
					   CommonFunctions.waitForElementTobeClickable(moldingInputUnitPrice);
					   CommonFunctions.enterTextInTextboxUpdated(moldingInputUnitPrice, data[241],"Unit Price Value for Molding Labor");
					   //Enter usage per Molding Labour
					   //wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUsagePerK));
					   CommonFunctions.clickButtonOrLink(moldingCellUsagePerK, "table cell", "unitPerK");
					   CommonFunctions.waitForElementTobeClickable(moldingInputUsagePerK);
					   CommonFunctions.enterTextInTextboxUpdated(moldingInputUsagePerK, data[242],"Usage Per K Value for Molding Labor");
					  

					   //Enter Product Markup-Molding Labour
					   CommonFunctions.waitForElementTobeClickable(moldingCellMarkup);
					   CommonFunctions.clickButtonOrLink(moldingCellMarkup, "table cell", "Markup");
					   //CommonFunctions.clearTextBox(moldingCellMarkup, "Markup Clear");
					   CommonFunctions.waitForElementTobeClickable(moldingInputMarkupPC58);
					   CommonFunctions.enterTextInTextboxUpdated(moldingInputMarkupPC58, data[243],"Markup Value for Molding");
					   
					   
					 
					//Click on Header Attributes
					CommonFunctions.waitForVisibilityOfElement(strHeaderAttributes);
					CommonFunctions.clickButtonOrLink(strHeaderAttributes,"link", "headerAttributes");
					//Select Quote Currency - USD
					CommonFunctions.selectFromDropDownByVisibleText(currency, data[196]);
					//Click on Save and Check In
					CommonFunctions.waitForElementTobeClickable(ExternalBOM.btnSaveAndCheckIn);
					CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.waitForPageLoaded();
					//Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForPageLoaded()	;
					/*CommonFunctions.waitForVisibilityOfElement(headerAttributes);
					CommonFunctions.clickButtonOrLink(headerAttributes,"link", "headerAttributes");*/
					//Click on Header Attributes
					CommonFunctions.waitForVisibilityOfElement(headerAttributes);
					CommonFunctions.clickButtonOrLink(headerAttributes,"link", "headerAttributes");
					
					//Note Quote Currency
					CommonFunctions.waitForElementTobeClickable(strQuoteCurrencyBOM);
		    		GettingText(strQuoteCurrencyBOM);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[196], "Actual And Expected Cost Sheet Quote Currency Are Not Matched.Assertion Failed.Please check");
				
		    		//Note Plastic Material
		    		CommonFunctions.waitForElementTobeClickable(strPlasticValueBOM);
		    		GettingText(strPlasticValueBOM);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[261], "Actual And Expected Cost Sheet Plastic material Are Not Matched.Assertion Failed.Please check");
		    		//Note Purchased Material
		    		CommonFunctions.waitForElementTobeClickable(strPurchasedValueBOM);
		    		GettingText(strPurchasedValueBOM);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[262], "Actual And Expected Cost Sheet Purchased Material Not Matched.Assertion Failed.Please check");
				    //Note Soft Goods Material
		    		CommonFunctions.waitForElementTobeClickable(strSGValueBOM);
		    		GettingText(strSGValueBOM);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[263], "Actual And Expected Cost Sheet Soft Goods Material Are Not Matched.Assertion Failed.Please check");
		    		//Note Chemical Material
		    		CommonFunctions.waitForElementTobeClickable(strChemicalValueBOM);
		    		GettingText(strChemicalValueBOM);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[264], "Actual And Expected Cost Sheet Chemical Material Are Not Matched.Assertion Failed.Please check");
		    		//Note General / Deco Labor Cost
		    		CommonFunctions.waitForElementTobeClickable(strGenDecLabValueBOM);
		    		GettingText(strGenDecLabValueBOM);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[265], "Actual And Expected Cost Sheet General / Deco Labor Cost Are Not Matched.Assertion Failed.Please check");
		    		//Note Molding Labor Cost
		    		CommonFunctions.waitForElementTobeClickable(strMoldingLabValueBOM);
		    		GettingText(strMoldingLabValueBOM);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[266], "Actual And Expected Cost Sheet Molding Labor Cost Are Not Matched.Assertion Failed.Please check");
		    		//Note Electronic Material
		    		CommonFunctions.waitForElementTobeClickable(strElectronicValueBOM);
		    		GettingText(strElectronicValueBOM);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[267], "Actual And Expected Cost Sheet Electronic Material Are Not Matched.Assertion Failed.Please check");
		    		//Note Packaging Material
		    		CommonFunctions.waitForElementTobeClickable(strPackagingValueBOM);
		    		GettingText(strPackagingValueBOM);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[268], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
		    		//Note Markup
		    		CommonFunctions.waitForElementTobeClickable(strProductMarkupBOM);
		    		GettingText(strProductMarkupBOM);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[269], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
					
					CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				    //Search Product
				    CommonProjectFunctions.searchProduct(data[191]);
				    CommonFunctions.waitForPageLoaded();
					//Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForPageLoaded()	;
					
					//Navigate to Sourcing tab
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Navigate to Costing sub-tab
					CommonFunctions.waitForVisibilityOfElement(CostingTab);
				    CommonFunctions.clickButtonOrLink(CostingTab, "link", "CostingTab");
					CommonFunctions.waitForPageLoaded();
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.costAction));
					//Note Quote Currency
		    		CommonFunctions.waitForElementTobeClickable(strQuoteCurrency);
		    		GettingText(strQuoteCurrency);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[196], "Actual And Expected Cost Sheet Quote Currency Are Not Matched.Assertion Failed.Please check");
		    		
		    		//Note Plastic Material
		    		CommonFunctions.waitForElementTobeClickable(strPlasticValue);
		    		GettingText(strPlasticValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[261], "Actual And Expected Cost Sheet Plastic material Are Not Matched.Assertion Failed.Please check");
		    		//Note Purchased Material
		    		CommonFunctions.waitForElementTobeClickable(strPurchasedValue);
		    		GettingText(strPurchasedValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[262], "Actual And Expected Cost Sheet Purchased Material Not Matched.Assertion Failed.Please check");
				    //Note Soft Goods Material
		    		CommonFunctions.waitForElementTobeClickable(strSGValue);
		    		GettingText(strSGValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[263], "Actual And Expected Cost Sheet Soft Goods Material Are Not Matched.Assertion Failed.Please check");
		    		//Note Chemical Material
		    		CommonFunctions.waitForElementTobeClickable(strChemicalValue);
		    		GettingText(strChemicalValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[264], "Actual And Expected Cost Sheet Chemical Material Are Not Matched.Assertion Failed.Please check");
		    		//Note General / Deco Labor Cost
		    		CommonFunctions.waitForElementTobeClickable(strGenDecLabValue);
		    		GettingText(strGenDecLabValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[265], "Actual And Expected Cost Sheet General / Deco Labor Cost Are Not Matched.Assertion Failed.Please check");
		    		//Note Molding Labor Cost
		    		CommonFunctions.waitForElementTobeClickable(strMoldingLabValue);
		    		GettingText(strMoldingLabValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[266], "Actual And Expected Cost Sheet Molding Labor Cost Are Not Matched.Assertion Failed.Please check");
		    		//Note Electronic Material
		    		CommonFunctions.waitForElementTobeClickable(strElectronicValue);
		    		GettingText(strElectronicValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[267], "Actual And Expected Cost Sheet Electronic Material Are Not Matched.Assertion Failed.Please check");
		    		//Note Packaging Material
		    		CommonFunctions.waitForElementTobeClickable(strPackagingValue);
		    		GettingText(strPackagingValue);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[268], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
		    		//Note Markup
		    		CommonFunctions.waitForElementTobeClickable(strProductMarkupCost);
		    		GettingText(strProductMarkupCost);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[269], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
				}
				catch(Exception e){
					fail=true;
					log.error("Exception in PC58_ValidateBOMChangesOnVendorRetailItemCostSheet"+e);
					throw e;
				}
				return true;

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
			
			
			private static String[] labormandatoryData(String[] data) throws Exception {
				try{ 
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialName));
					matName=data[8]+CommonFunctions.getRandomString(5);
					CommonFunctions.enterTextInTextbox(Material.materialName, matName);
					// click on resin create button
					CommonFunctions.clickButtonOrLink(Material.btnCreateResin, "btnCreateResin");
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.AssertEqualsVerification(CommonFunctions.alertmessage.trim(), data[11], "Actual and Expected values are not matched.Assertion falied.Please check.");
					log.info("****Verified Error message ***You must choose a value for:  Exchange Rate Season*** is present");
					CommonFunctions.enterTextInTextbox(resinDescription,data[7]);
					// click on resin create button
					CommonFunctions.clickButtonOrLink(Material.btnCreateResin, "btnCreateResin");
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.AssertEqualsVerification(CommonFunctions.alertmessage.trim(), data[12], "Actual and Expected values are not matched.Assertion falied.Please check.");
					log.info("****Verified Error message ***You must choose a value for Operation Type*** is present");
					//Select Unit Of Measure 
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(unitOfMeasure, data[9], "Unit Of Measure");
					//Search for   *Exchange Rate Season from POPUP Page
					CommonFunctions.clickButtonOrLink(seasonLnk, "link", "Season");
					Set set1 = driver.getWindowHandles();
					Iterator iter1 = set1.iterator();
					String parent1 = (java.lang.String) iter1.next();
					String child1 = (java.lang.String) iter1.next();
					driver.switchTo().window(child1);
					CommonFunctions.clickButtonOrLink(BOMMaterialMainUser.btnSearch, "Search For Season");
					//Select choose
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[6]+"')]/preceding::td[1]/a"), "Season selected");
					driver.switchTo().window(parent1);
					driver.switchTo().frame("contentframe");
					//Enter Operation Type
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(operationType, data[10], "Operation Type");
					// click on resin create button
					CommonFunctions.clickButtonOrLink(Material.btnCreateResin, "btnCreateResin");
					exchRate=driver.findElement(exchangeRate).getText().trim();
				}
				catch(Exception e){
					fail=true;
					log.error("Exception in labormandatoryData()", e);
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
					CommonFunctions.waitForPageLoaded();
					Thread.sleep(1000);
					//Click on 'FX Rates - Business Unit'
					CommonFunctions.clickButtonOrLink(fxRatesBusiUnit, "link", "FX Rates - Business Unit");
					CommonFunctions.waitForPageLoaded();
					//Click on search
					CommonFunctions.clickButtonOrLink(BOMMaterialMainUser.btnSearch, "btn", "Search");
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Click in business unit name as '2019 ASIA HONG KONG'
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[22]+"']"), "lnk", "Business unit name");
					CommonFunctions.waitForVisibilityOfElement(fxRate);
					boExchRate = driver.findElement(fxRate).getText().trim();
				}
				catch(Exception e){
					fail=true;
					log.error("Exception in resinmandatoryData()", e);
					throw e;
				}
				return boExchRate;
			}
			//Verify List of Values for Factory Region
			
			public static boolean verifyFactoryRegionLOV(String [] data) throws Exception{
				try{
					Select selectSou = new Select(driver.findElement(factoryRegion));
					List<WebElement> options = selectSou.getOptions();
					for(int i=24;i<38;i++) {
						//System.out.println(options);
						boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[i],options);
						Assert.assertEquals(bVal, true,"verified FactoryRegion failed for "+ data[i]);
						log.info("Verified FactoryRegio for : "+ data[i]);
					}
					return true;
				}
				catch(Exception e){
					fail=true;
					log.error("Exception in verifyFactoryRegion "+e);
					throw e;
					}
				
			}
			public static boolean editPricingChartMOATable(String [] data) throws Exception{
				try{
					wait.until(ExpectedConditions.visibilityOfElementLocated(pricingTypeEdit));
					CommonFunctions.clickButtonOrLink(pricingTypeEdit, "link", "Edit link");
					CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
					CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);
					wait.until(ExpectedConditions.visibilityOfElementLocated(cellBenchmarkCost));
					CommonFunctions.clickButtonOrLink(cellBenchmarkCost, "table cell", "BenchMarkCost");
					CommonFunctions.waitForElementTobeClickable(inputBenchmarkCost);
					CommonFunctions.enterTextInTextboxUpdated(inputBenchmarkCost, data[13],"BenchMarkCost");
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.AssertEqualsVerification(CommonFunctions.alertmessage.trim(), data[14], "Actual and Expected values are not matched.Assertion falied.Please check.");
					log.info("****Message ***You must specify a value for:   Country of Origin*** is present");
					//Clear BenchMark Cost
					CommonFunctions.clickButtonOrLink(cellBenchmarkCost, "table cell", "BenchMarkCost");
					CommonFunctions.waitForElementTobeClickable(inputBenchmarkCost);
					CommonFunctions.clearTextBox(inputBenchmarkCost, "Clear Benchmark Cost");
					//Enter Country of Origin
					CommonFunctions.clickButtonOrLink(cellCountryOfOrigin, "table cell", "CountryOfOrigin");
					CommonFunctions.waitForElementTobeClickable(inputCountryOfOrigin);
					CommonFunctions.clickButtonOrLink(inputCountryOfOrigin, "table cell","CountryOfOrigin");
					Set set1 = driver.getWindowHandles();
					Iterator iter1 = set1.iterator();
					String parent1 = (java.lang.String) iter1.next();
					String child1 = (java.lang.String) iter1.next();
					driver.switchTo().window(child1);
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(BOMMaterialMainUser.btnSearch));
					CommonFunctions.clickButtonOrLink(BOMMaterialMainUser.btnSearch, "Search For Season");
					//Select choose
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[15]+"')]/preceding::td[1]/a"), "Season selected");
					driver.switchTo().window(parent1);
					driver.switchTo().frame("contentframe");
					//Enter Deviation Attribute
					CommonFunctions.clickButtonOrLink(cellDeviationAttribute, "table cell", "DeviationAttribute");
					CommonFunctions.waitForElementTobeClickable(inputDeviationAttribute);
					CommonFunctions.enterTextInTextboxUpdated(inputDeviationAttribute, data[16],"DeviationAttribute");
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.AssertEqualsVerification(CommonFunctions.alertmessage.trim(), data[17], "Actual and Expected values are not matched.Assertion falied.Please check.");
					log.info("****Message ***You must specify a value for:   Benchmark Cost (HKD)*** is present");
					CommonFunctions.clickButtonOrLink(cellBenchmarkCost, "table cell", "BenchMarkCost");
					CommonFunctions.waitForElementTobeClickable(inputBenchmarkCost);
					CommonFunctions.enterTextInTextboxUpdated(inputBenchmarkCost, data[13],"BenchMarkCost");
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
					CommonFunctions.waitForPageLoaded();
					wait.until(ExpectedConditions.visibilityOfElementLocated(MasterCartonEditButton));
					//Verify that Factory Region attribute is present and by default it is blank.
					Assert.assertEquals(driver.findElement(factoryRegionValue).getText().trim(), "");
					log.info("****Verification:System displays Factory Region is blank and it is not editable by User****");
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
					wait.until(ExpectedConditions.visibilityOfElementLocated(MasterCartonEditButton));
					//Verify Maximum Threshold (HKD) column = Benchmark Cost (HKD) * (100%+Deviation)
					CommonFunctions.waitForElementTobeClickable(strMaximumThresholdValueHKD);
		    		GettingText(strMaximumThresholdValueHKD);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[18], "Actual And Expected Maximum Threshold Value HKD Are Not Matched.Assertion Failed.Please check");
		    		log.info("****Verification:System displays Maximum Threshold (HKD) column = Benchmark Cost (HKD) * (100%+Deviation)****");
		    		//Verify Minimum Threshold (HKD) column = Benchmark Cost (HKD) * (100%-Deviation)
		    		CommonFunctions.waitForElementTobeClickable(strMinimumThresholdValueHKD);
		    		GettingText(strMinimumThresholdValueHKD);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[19], "Actual And Expected Minimum Threshold Value HKD Are Not Matched.Assertion Failed.Please check");
		    		log.info("****Verification:System displays Minimum Threshold (HKD) column = Benchmark Cost (HKD) * (100%-Deviation)****");
		    		//Verify that the system calculates Maximum Threshold  Cost (USD) = Maximum Threshold Cost (HKD) * 1/(FX HKD Rate) displayed***
		    		CommonFunctions.waitForElementTobeClickable(strMaximumThresholdValueUSD);
		    		GettingText(strMaximumThresholdValueUSD);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[20], "Actual And Expected Maximum Threshold Value USD Are Not Matched.Assertion Failed.Please check");
		    		log.info("****Verification:System displays Maximmum Threshold  Cost (USD) = Maximum Threshold Cost (HKD) * 1/(FX HKD Rate)****");
		    		//Verify that the system calculates Minimum Threshold  Cost (USD) = Minimum Threshold Cost (HKD) * 1/(FX HKD Rate) displayed
		    		CommonFunctions.waitForElementTobeClickable(strMinimumThresholdValueUSD);
		    		GettingText(strMinimumThresholdValueUSD);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[21], "Actual And Expected Minimum Threshold Value USD Are Not Matched.Assertion Failed.Please check");
		    		log.info("****Verification:System displays Minimum Threshold  Cost (USD) = Minimum Threshold Cost (HKD) * 1/(FX HKD Rate)****");
		    		//Click on Save
		    		Thread.sleep(1000);
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
					CommonFunctions.waitForPageLoaded();
					wait.until(ExpectedConditions.visibilityOfElementLocated(MasterCartonEditButton));
					CommonFunctions.waitForElementTobeClickable(GlobalLinePlan.btnDone);
					Thread.sleep(2000);
					//Click on Done
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnDone,"btn", "Done");
					CommonFunctions.waitForPageLoaded();
					//Edit Pricing Chart MOA Table
					wait.until(ExpectedConditions.visibilityOfElementLocated(pricingTypeEdit));
					CommonFunctions.clickButtonOrLink(pricingTypeEdit, "link", "Edit link");
					CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);
					CommonFunctions.clickButtonOrLink(cellFactoryRegion, "table cell", "Factory Region Cell");
					
                    //Verify that Factory Region attribute list of values:
					verifyFactoryRegionLOV(data);
					log.info("****Verification : Verified List of value for Factory Region attribute*****");
					//Click on Save and Done
					Thread.sleep(1000);
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
					CommonFunctions.waitForPageLoaded();
					wait.until(ExpectedConditions.visibilityOfElementLocated(MasterCartonEditButton));
					CommonFunctions.waitForElementTobeClickable(GlobalLinePlan.btnDone);
					Thread.sleep(2000);
					//Click on Done
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnDone,"btn", "Done");
					CommonFunctions.waitForPageLoaded();
				}
				catch(Exception e){
					fail=true;
					log.error("Exception in editPricingChartMOATable "+e);
					throw e;
					}
				return true;
			}
			
			//Verify Retail Package Style List Of Values
			public static boolean verifyRetailPackageStyleLOV(String [] data) throws Exception{
				try{
					Select selectSou = new Select(driver.findElement(inputRetailPackageStyle));
					List<WebElement> options = selectSou.getOptions();
					for(int i=52;i<81;i++) {
						//System.out.println(options);
						boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[i],options);
						Assert.assertEquals(bVal, true,"verified Retail Package Style failed for "+ data[i]);
						log.info("Verified RetailPackageStyle for : "+ data[i]);
					}
					return true;
				}
				catch(Exception e){
					fail=true;
					log.error("Exception in verifyRetailPackageStyleLOV "+e);
					throw e;
					}
				
			}
			
			//Verify Product Category LOV
			public static boolean verifyProductCategoryLOV(String [] data) throws Exception{
				try{
					Select selectSou = new Select(driver.findElement(inputProductCategory));
					List<WebElement> options = selectSou.getOptions();
					for(int i=39;i<52;i++) {
						//System.out.println(options);
						boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[i],options);
						Assert.assertEquals(bVal, true,"verified ProductCategory failed for "+ data[i]);
						log.info("Verified ProductCategory for : "+ data[i]);
					}
					return true;
				}
				catch(Exception e){
					fail=true;
					log.error("Exception in verifyProductCategoryLOV "+e);
					throw e;
					}
				
			}
			
			
			public static boolean editCycleTimeChartMOATable(String [] data) throws Exception{
				try{
					wait.until(ExpectedConditions.visibilityOfElementLocated(cycleTimeChartEdit));
					CommonFunctions.clickButtonOrLink(cycleTimeChartEdit, "link", "Edit link");
					CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
					CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);
					//Verify that a non-required attribute Product Category exist in Cycle Time Chart
					CommonFunctions.waitForElementTobeClickable(strProductCategory);
		    		GettingText(strProductCategory);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[38], "Actual And Expected Product Category Value HKD Are Not Matched.Assertion Failed.Please check");
		    		log.info("****Verification:System displays Product Category and its default value is N/A****");
		    		CommonFunctions.waitForElementTobeClickable(strProductCategory);
		    		CommonFunctions.clickButtonOrLink(strProductCategory, "table cell", "Product Category");
		    		//Verify that Product Category attribute list of values:
					verifyProductCategoryLOV(data);
					log.info("****Verification : Verified List of value for Product Category attribute*****");
					//Select NERF value from drop down for Product Category.
					CommonFunctions.clickButtonOrLink(strProductCategory, "table cell", "Product Category");
					CommonFunctions.waitForElementTobeClickable(productCategory);
					CommonFunctions.selectFromDropDownByIndex(productCategory, 1);
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.AssertEqualsVerification(CommonFunctions.alertmessage.trim(), data[14], "Actual and Expected values are not matched.Assertion falied.Please check.");
					log.info("****Message ***You must specify a value for:   Country of Origin*** is present");
					//Verify that Country of Origin is by default blank.
					Assert.assertEquals(driver.findElement(cellCountryOfOrigin).getText().trim(), "");
					log.info("****Verification:System displays Country of Origin is blank****");
					//Enter Country of Origin
					CommonFunctions.clickButtonOrLink(cellCountryOfOrigin, "table cell", "CountryOfOrigin");
					CommonFunctions.waitForElementTobeClickable(inputCountryOfOrigin);
					CommonFunctions.clickButtonOrLink(inputCountryOfOrigin, "table cell","CountryOfOrigin");
					Set set1 = driver.getWindowHandles();
					Iterator iter1 = set1.iterator();
					String parent1 = (java.lang.String) iter1.next();
					String child1 = (java.lang.String) iter1.next();
					driver.switchTo().window(child1);
					CommonFunctions.clickButtonOrLink(BOMMaterialMainUser.btnSearch, "Search For Season");
					//Select choose
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[15]+"')]/preceding::td[1]/a"), "Season selected");
					driver.switchTo().window(parent1);
					driver.switchTo().frame("contentframe");
					//CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
					//wait.until(ExpectedConditions.visibilityOfElementLocated(MasterCartonEditButton));
					//CommonFunctions.handleAlertPopUp();
					//Verify for the presence of Retail Package Style attribute in Cycle Time Chart table
					CommonFunctions.waitForElementTobeClickable(strRetailPackageInCycleTimeChart);
					CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(strRetailPackageInCycleTimeChart, "Retail Package Style is present in cycle time chart table"), "Retail Package Style is present in cycle time chart table.Assertion failed.please check");
					log.info("****Retail Package Style is present in cycle time chart table****");
				
					CommonFunctions.waitForElementTobeClickable(cellRetailPackageStyle);
		    		CommonFunctions.clickButtonOrLink(cellRetailPackageStyle, "table cell", "Retail Package Style");
					//Verify List of Values for Retail Package Style
		    		verifyRetailPackageStyleLOV(data);
					//Select BAG value for Retail Package Style attribute
		    		CommonFunctions.clickButtonOrLink(strRetailPackageStyle, "table cell", "Retail Package Style");
					CommonFunctions.waitForElementTobeClickable(retailPackageStyle);
					CommonFunctions.selectFromDropDownByIndex(retailPackageStyle, 1);
					
					//Verify that Machine Size (TON) non-required attribute is present in Cycle chart table
					CommonFunctions.waitForElementTobeClickable(strMachineSizeTON);
					CommonFunctions.clickButtonOrLink(strMachineSizeTON, "table cell", "Machine Size TON");
					//CommonFunctions.waitForElementTobeClickable(inputMachineSizeTON);
					//CommonFunctions.clickButtonOrLink(inputMachineSizeTON, "Input", "Machine Size TON");
					Assert.assertEquals(driver.findElements(inputMachineSizeTON).size(),1);
					Assert.assertEquals(driver.findElement(inputMachineSizeTON).getText(),"");
					log.info("****Verification:System displays Machine Size TON in cycle time chart table and its default value is blank.****");
					//Enter 150 for Machine Size TON
					CommonFunctions.clickButtonOrLink(strMachineSizeTON, "table cell", "strMachineSizeTON");
					CommonFunctions.waitForElementTobeClickable(inputMachineSizeTON);
					CommonFunctions.enterTextInTextboxUpdated(inputMachineSizeTON, data[82],"Machine Size TON");
					//Verify that Bench Mark Cycle Time attribute is present in Cycle chart table
					CommonFunctions.waitForElementTobeClickable(benchMarkCycleTime);
					CommonFunctions.clickButtonOrLink(benchMarkCycleTime, "table cell", "Machine Size TON");
					Assert.assertEquals(driver.findElements(inputBenchMarkCycleTime).size(),1);
					Assert.assertEquals(driver.findElement(inputBenchMarkCycleTime).getText(),"");
					log.info("****Verification:System displays Bench Mark Cycle Time in cycle time chart table and its default value is blank.****");
					//Enter 15.1234 in Benchmark Cycle Time.
					CommonFunctions.clickButtonOrLink(benchMarkCycleTime, "table cell", "benchMarkCycleTime");
					CommonFunctions.waitForElementTobeClickable(inputBenchMarkCycleTime);
					CommonFunctions.enterTextInTextboxUpdated(inputBenchMarkCycleTime, data[83],"Bench Mark Cycle Time");
					//Verify that Deviation attribute is present in Cycle chart table
					CommonFunctions.waitForElementTobeClickable(cellDeviationAttributeCTC);
					CommonFunctions.clickButtonOrLink(cellDeviationAttributeCTC, "table cell", "Deviation Attribute");
					Assert.assertEquals(driver.findElements(inputDeviationAttributeCTC).size(),1);
					Assert.assertEquals(driver.findElement(inputDeviationAttributeCTC).getText(),"");
					log.info("****Verification:System displays Deviation attribute in cycle time chart table and its default value is blank.****");
					//Enter 20.1234 in Deviation attribute
					CommonFunctions.clickButtonOrLink(cellDeviationAttributeCTC, "table cell", "Deviation Attribute IN Cycle Time Chart");
					CommonFunctions.waitForElementTobeClickable(inputDeviationAttributeCTC);
					CommonFunctions.enterTextInTextboxUpdated(inputDeviationAttributeCTC, data[84],"Deviation Attribute IN Cycle Time Chart");
					
					
					
					//Click on Save and Done
					Thread.sleep(1000);
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
					CommonFunctions.waitForPageLoaded();
					wait.until(ExpectedConditions.visibilityOfElementLocated(MasterCartonEditButton));
					CommonFunctions.waitForElementTobeClickable(GlobalLinePlan.btnDone);
					Thread.sleep(2000);
					//Click on Done
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnDone,"btn", "Done");
					CommonFunctions.waitForPageLoaded();
			
		    		
					//Verify Minimum Threshold Value
					//CommonFunctions.waitForElementTobeClickable(strMinimumThresholdValueCTC);
					//CommonFunctions.clickButtonOrLink(strMinimumThresholdValueCTC, "table cell", "Minimum Threshold CTC");
					//CommonFunctions.waitForElementTobeClickable(inputMinimumThresholdCTC);
					CommonFunctions.clickButtonOrLink(inputMinimumThresholdCTC, "table cell", "Minimum Threshold CTC");
		    		GettingText(inputMinimumThresholdCTC);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[85], "Actual And Expected Maximum Threshold Value USD Are Not Matched.Assertion Failed.Please check");
		    		log.info("****Verification:System displays  Minimum Threshold = Benchmark Cycle Timet * (100% - Deviation)****");
					//Verify Maximum Threshold Value
		    		//CommonFunctions.waitForElementTobeClickable(strMaximumThresholdValueCTC);
					//CommonFunctions.clickButtonOrLink(strMaximumThresholdValueCTC, "table cell", "Maximum Threshold CTC");
					//CommonFunctions.waitForElementTobeClickable(inputMinimumThresholdCTC);
		    		//CommonFunctions.waitForElementTobeClickable(strMaximumThresholdValueCTC);
		    		CommonFunctions.clickButtonOrLink(inputMaximumThresholdCTC, "table cell", "Maximum Threshold CTC");
		    		GettingText(inputMaximumThresholdCTC);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[86], "Actual And Expected Maximum Threshold Value USD Are Not Matched.Assertion Failed.Please check");
		    		log.info("****Verification:System displays  Maximum Threshold= Benchmark Cycle Time * (100% + Deviation)****");
		    		//CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
		    		//CommonFunctions.waitForPageLoaded();
				}
				catch(Exception e){
					fail=true;
					log.error("Exception in editCycleTimeChartMOATable "+e);
					throw e;
					}
				return true;
			}

			
			
			//Create Material - Labor
			public static boolean TC11_PC11_CreateMaterialLabor(String [] data) throws Exception{
				try{
			
			
					navigateUptoCreateDiffrntTypesOfMaterial(data);		
					labormandatoryData(data);
					CommonFunctions.waitForPageLoaded();
					editPricingChartMOATable(data);
					editCycleTimeChartMOATable(data);	
					//***Exchange Rates***//
		    		boExchRate = verifyBOfxRates(data);
					//CommonFunctions.assertEquals(exchRate, boExchRate);
					CommonFunctions.AssertEqualsVerification(boExchRate, data[23], "Actual and Expected values are not matched.Assertion falied.Please check.");
					log.info("The attribute Exchange Rate (USD to HKD) is based on fx Rates of '2019 ASIA HONG KONG' available on business Object");
				}
				catch(Exception e){
					fail=true;
					log.error("Exception in TC11_PC11_CreateMaterialLabor "+e);
					throw e;
					}
				return true;
			}
			//Create Internal Product Cost sheet-A
			public static boolean createInternalProductCostSheetA(String [] data) throws Exception{
			try{
			CostsheetTooling.clickOnCostSheetListTab();
			CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.costAction);
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet");
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForVisibilityOfElement(costSheetType);
			CommonFunctions.clickButtonOrLink(costSheetType, "link", "Costsheet Type-Internal Product CS");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForVisibilityOfElement(CostsheetInternal.csName);
			driver.findElement(CostsheetInternal.csName).clear();
			CommonFunctions.enterTextInTextbox(CostsheetInternal.csName, data[37]);
			//Wait for Wave Dropdown
			CommonFunctions.waitForElementTobeClickable(wave);
			//Click on Wave Dropdown
			//CommonFunctions.selectFromDropDownByVisibleTextUpdated(wave, data[34], "Wave DropDown");
			CommonFunctions.selectFromDropDownByIndex(wave, 1);
			//Wait for Quote Currency Dropdown
			CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
			//Click on Quote Currency Dropdown
			CommonFunctions.selectFromDropDownByIndex(QuoteCurrencyDropDown, 2);
			/*//Wait for BOM Dropdown
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(BOMDropDown);
			//Select Specification
			CommonFunctions.selectFromDropDownByIndex(BOMDropDown, 1);
			Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded();*/
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
			CommonFunctions.waitForPageLoaded();
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in createInternalProductCostSheetA() "+e);
				throw e;
				}
			return true;
		}
			//Create Internal Product Cost sheet-A
			public static boolean createInternalProductCostSheetB(String [] data) throws Exception{
			try{
			SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(tabCostsheet));
			CostsheetTooling.clickOnCostSheetListTab();
			CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.costAction);
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet");
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForVisibilityOfElement(costSheetType);
			CommonFunctions.clickButtonOrLink(costSheetType, "link", "Costsheet Type-Internal Product CS");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForVisibilityOfElement(CostsheetInternal.csName);
			driver.findElement(CostsheetInternal.csName).clear();
			CommonFunctions.enterTextInTextbox(CostsheetInternal.csName, data[38]);
			//Wait for Wave Dropdown
			//CommonFunctions.waitForElementTobeClickable(wave);
			//Click on Wave Dropdown
			//CommonFunctions.selectFromDropDownByVisibleTextUpdated(wave, data[34], "Wave DropDown");
			//CommonFunctions.selectFromDropDownByIndex(wave, 2);
			//Wait for Quote Currency Dropdown
			CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
			//Click on Quote Currency Dropdown
			CommonFunctions.selectFromDropDownByIndex(QuoteCurrencyDropDown, 1);
			/*//Wait for BOM Dropdown
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(BOMDropDown);
			//Select Specification
			CommonFunctions.selectFromDropDownByIndex(BOMDropDown, 1);
			Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded();*/
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
			CommonFunctions.waitForPageLoaded();
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in createInternalProductCostSheetB() "+e);
				throw e;
				}
			return true;
		}
			//PC80: View CostSheet Iteration History
			public static boolean TC13_PC80_ViewCostSheetIterationHistory(String [] data) throws Exception{
				try{
					//******Create Product : Solid Product********
					
				      CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
					  data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
					  data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
					
				    NavigateToDetailsTab(data);
					prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
					log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum1);
					//Compare Iterations for Assortment/Solid Product
					CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				    //Search Product
				    CommonProjectFunctions.searchProduct(prodNum1);
				    CommonFunctions.waitForPageLoaded();
					//Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForPageLoaded()	;
					//Navigate to Sourcing Tab
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Navigate to Costing Tab
					CommonFunctions.waitForVisibilityOfElement(CostingTab);
				    CommonFunctions.clickButtonOrLink(CostingTab, "link", "CostingTab");
					CommonFunctions.waitForPageLoaded();
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//Select Source
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SourceDropDown, data[4], "Source");
					CommonFunctions.waitForPageLoaded();
					/*//Select Specification
					CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
					CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
					CommonFunctions.waitForPageLoaded();*/
					//Create Cost Sheet -A
					createInternalProductCostSheetA(data);
					//Create Cost Sheet -B
					//createInternalProductCostSheetB(data);
					Thread.sleep(1000);
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(tabCostsheet));
					CostsheetTooling.clickOnCostSheetListTab();
					//Click on Cost sheet A
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(strCostSheetA));
					//CommonFunctions.waitForElementTobeClickable(strCostSheetA);
					CommonFunctions.clickButtonOrLink(strCostSheetA, "Cost sheet A");
					//CommonFunctions.waitForPageLoaded();
					
					/**
					 * Update Cost sheet A
					 */
					
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(strCostSheetA));
					//Wait for Update-Actions menu
					CommonFunctions.waitForElementTobeClickable(updateCSActions);
					//Select Update CS
					CommonFunctions.selectFromDropDownByIndex(updateCSActions, 1);
					CommonFunctions.waitForPageLoaded();
		            //Clicking on Update Button
		            //CommonFunctions.waitForPageLoaded();
					//driver.switchTo().defaultContent();
					//driver.switchTo().frame("contentframe");
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(wave));
					//Wait for Wave Dropdown
					CommonFunctions.waitForElementTobeClickable(wave);
					//Click on Wave Dropdown
					//CommonFunctions.selectFromDropDownByVisibleTextUpdated(wave, data[34], "Wave DropDown");
					CommonFunctions.selectFromDropDownByIndex(wave, 5);
					 //Click on Save button
					CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
					CommonFunctions.waitForPageLoaded();
					
					//Click on Iteration history
					//CostsheetTooling.clickOnCostSheetListTab();
					//CommonFunctions.waitForVisibilityOfElement(actionDD);
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(actionDD));
					CommonFunctions.selectFromDropDownByVisibleText(actionDD, "Iteration History");
					CommonFunctions.waitForPageLoaded();
					//Validate Old Iteration
					CommonFunctions.waitForElementTobeClickable(oldIteration);
					CommonFunctions.clickButtonOrLink(oldIteration, "Old Iteration");
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(verifyOldIteration);
					GettingText(verifyOldIteration);
		    		//strOldIteration.trim();
					//Iterator itr = strOldIteration.iterator();
					//glPlanName.substring(0,5);
		    		CommonFunctions.AssertEqualsVerification(ActualValue.substring(0,25), data[36], "Actual And Expected Cost Sheet BOM Iteration Are Not Matched.Assertion Failed.Please check");
		    		log.info(" Old Iteration verification completed for Assortment/Solid Product");
		    		
		    		
		    		//******Create Product : Retail Product********
					
				      CommonProjectFunctions.CreateProdFromLineSheet(data[39],data[6],data[7],data[8],data[9],data[10],data[11],
					  data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
					  data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
					
				    NavigateToDetailsTab(data);
					prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
					log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum1);
					//Compare Iterations for Assortment/Solid Product
					CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				    //Search Product
				    CommonProjectFunctions.searchProduct(prodNum1);
				    CommonFunctions.waitForPageLoaded();
					//Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForPageLoaded()	;
					//Navigate to Sourcing Tab
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Navigate to Costing Tab
					CommonFunctions.waitForVisibilityOfElement(CostingTab);
				    CommonFunctions.clickButtonOrLink(CostingTab, "link", "CostingTab");
					CommonFunctions.waitForPageLoaded();
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//Select Source
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SourceDropDown, data[4], "Source");
					CommonFunctions.waitForPageLoaded();
					/*//Select Specification
					CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
					CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
					CommonFunctions.waitForPageLoaded();*/
					//Create Cost Sheet -A
					createInternalProductCostSheetB(data);
					//Create Cost Sheet -B
					//createInternalProductCostSheetB(data);
					Thread.sleep(1000);
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(tabCostsheet));
					CostsheetTooling.clickOnCostSheetListTab();
					//Click on Cost sheet A
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(strCostSheetB));
					//CommonFunctions.waitForElementTobeClickable(strCostSheetA);
					CommonFunctions.clickButtonOrLink(strCostSheetB, "Cost sheet B");
					//CommonFunctions.waitForPageLoaded();
					
					/**
					 * Update Cost sheet B
					 */
					
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(strCostSheetB));
					//Wait for Update-Actions menu
					CommonFunctions.waitForElementTobeClickable(updateCSActions);
					//Select Update CS
					CommonFunctions.selectFromDropDownByIndex(updateCSActions, 1);
					CommonFunctions.waitForPageLoaded();
		            //Clicking on Update Button
		            //CommonFunctions.waitForPageLoaded();
					//driver.switchTo().defaultContent();
					//driver.switchTo().frame("contentframe");
					//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(wave));
					//Wait for Wave Dropdown
					//CommonFunctions.waitForElementTobeClickable(wave);
					//Click on Wave Dropdown
					//CommonFunctions.selectFromDropDownByVisibleTextUpdated(wave, data[34], "Wave DropDown");
					//CommonFunctions.selectFromDropDownByIndex(wave, 8);
					//Wait for Quote Currency Dropdown
					CommonFunctions.waitForElementTobeClickable(QuoteCurrencyDropDown);
					//Click on Quote Currency Dropdown
					CommonFunctions.selectFromDropDownByIndex(QuoteCurrencyDropDown, 2);
					 //Click on Save button
					CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
					CommonFunctions.waitForPageLoaded();
					
					//Click on Iteration history
					//CostsheetTooling.clickOnCostSheetListTab();
					//CommonFunctions.waitForVisibilityOfElement(actionDD);
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(actionDD));
					CommonFunctions.selectFromDropDownByVisibleText(actionDD, "Iteration History");
					CommonFunctions.waitForPageLoaded();
					//Validate Old Iteration
					CommonFunctions.waitForElementTobeClickable(oldIteration);
					CommonFunctions.clickButtonOrLink(oldIteration, "Old Iteration");
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(verifyOldIteration);
					GettingText(verifyOldIteration);
		    		//strOldIteration.trim();
					//Iterator itr = strOldIteration.iterator();
					//glPlanName.substring(0,5);
		    		CommonFunctions.AssertEqualsVerification(ActualValue.substring(0,25), data[36], "Actual And Expected Cost Sheet BOM Iteration Are Not Matched.Assertion Failed.Please check");
		    		log.info(" Old Iteration verification completed for Retail Product");
		    		
		    	
					
				}
				
			catch(Exception e){
				fail=true;
				log.error("Exception in TC13_PC80_ViewCostSheetIterationHistory "+e);
				throw e;
				}
			return true;
		}
			//Add Sourcing Config
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
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+sourcename+"')]/preceding::td[1]/a"), "Supplier selected");
					SeleniumDriver.driver.switchTo().window(parent);

					SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");

					//Sourcing Lead 
					CommonFunctions.selectFromDropDownByIndex(CommonProjectFunctions.sourcingLead, 13);
					//*Sourcing Head 
					CommonFunctions.selectFromDropDownByIndex(CommonProjectFunctions.sourcingHead, 3);
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
			//Add Sourcing Config
			public static Boolean AddSourcingConfigurationSC1(String sourcename) throws Exception{
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
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingConfig.search));
					CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+sourcename+"')]/preceding::td[1]/a"), "Supplier selected");
					SeleniumDriver.driver.switchTo().window(parent);

					SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");

					//Sourcing Lead 
					CommonFunctions.selectFromDropDownByIndex(CommonProjectFunctions.sourcingLead, 5);
					//*Sourcing Head 
					CommonFunctions.selectFromDropDownByIndex(CommonProjectFunctions.sourcingHead, 5);
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
			//Approve Sourcing Config work flow: first time
			public static boolean approveTask1(String[] data,String status) throws Exception{
				try{
					
					
					//Click on mywork
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					if(data[2].equalsIgnoreCase("Rejected Flow2")&&(i!=0)) 
					{
						//CommonFunctions.waitForInvisibilityOfElement(lnkSourConfig);
						Assert.assertEquals(driver.findElements(lnkSourConfig).size(),0);
						log.info("Sourcing config link is not present as Lead rejected request");
					}
					else {
						//Click on Sourcing Config
						CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
						CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
						CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfig);
						//Click Set Proposed Sourcing Status
						CommonFunctions.clickButtonOrLink(lnkConfirmSourConfig, "lnk", "Confirm Sourcing Configuration");
						driver.switchTo().defaultContent();
						driver.switchTo().frame("contentframe");
						CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
						//Select check box
						CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']/preceding::input[1]"));  //selectCheckBox
						//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
						//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", status);
						
						//Click on + sign
					//	CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
						System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"));
						CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"), "Img", "Plus sign");
						
						//Enter comment
						CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::textarea[1]"), data[9]);
						
						//Click on Complete
						CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					}	
						Thread.sleep(3000);
						/*CommonProjectFunctions.logOut();
						log.info("Task verification completed.....");
						i++;
						System.out.println("end: "+i); */
						
					
					}catch(Exception e){
						fail=true;
						log.error("Exception in approveTask1()", e);
						throw e;
						//return false;
					}
					return true;
				}

			//Approve Sourcing Config work flow: second time
			public static boolean approveTask2(String[] data,String status) throws Exception{
				try{
					
					
					
					/*//Click on mywork
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");*/
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					//Click on Sourcing Config
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
					CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfig);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfig, "lnk", "Confirm Sourcing Configuration");
					log.info("***Set proposed sourcing status workitem list displayed for product-Verified***");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//Click on Approve Radio button
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::td[contains(text(),'"+data[8]+"')]/preceding::td[1]/input"), "radio", status);
					CommonFunctions.clickButtonOrLink(approveRadioButton, "radio button","approveRadioButton");
					//Click on + sign
				    //CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					
						
						
					
					}catch(Exception e){
						fail=true;
						log.error("Exception in approveTask2()", e);
						//return false;
						throw e;
					}
					return true;
				}
			
			public static boolean approveTask1S(String[] data,String status) throws Exception{
				try{
					
					
					//Click on mywork
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					if(data[2].equalsIgnoreCase("Rejected Flow2")&&(i!=0)) 
					{
						//CommonFunctions.waitForInvisibilityOfElement(lnkSourConfig);
						Assert.assertEquals(driver.findElements(lnkSourConfig).size(),0);
						log.info("Sourcing config link is not present as Lead rejected request");
					}
					else {
						//Click on Sourcing Config
						CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
						CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
						CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfig);
						//Click Set Proposed Sourcing Status
						CommonFunctions.clickButtonOrLink(lnkConfirmSourConfig, "lnk", "Confirm Sourcing Configuration");
						driver.switchTo().defaultContent();
						driver.switchTo().frame("contentframe");
						CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
						//Select check box
						CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']/preceding::input[1]"));  //selectCheckBox
						//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
						//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", status);
						
						//Click on + sign
					//	CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
						System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"));
						CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"), "Img", "Plus sign");
						
						//Enter comment
						CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::textarea[1]"), data[9]);
						
						//Click on Complete
						CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					}	
						Thread.sleep(3000);
						/*CommonProjectFunctions.logOut();
						log.info("Task verification completed.....");
						i++;
						System.out.println("end: "+i); */
						
					
					}catch(Exception e){
						fail=true;
						log.error("Exception in approveTask1()", e);
						//return false;
						throw e;
					}
					return true;
				}

			//Approve Sourcing Config work flow: second time
			public static boolean approveTask2S(String[] data,String status) throws Exception{
				try{
					
					
					
					/*//Click on mywork
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");*/
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					//Click on Sourcing Config
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
					CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfig);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfig, "lnk", "Confirm Sourcing Configuration");
					log.info("***Set proposed sourcing status workitem list displayed for product-Verified***");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//Click on Approve Radio button
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::td[contains(text(),'"+data[8]+"')]/preceding::td[1]/input"), "radio", status);
					CommonFunctions.clickButtonOrLink(approveRadioButton, "radio button","approveRadioButton");
					//Click on + sign
				    //CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					
						
						
					
					}catch(Exception e){
						fail=true;
						log.error("Exception in approveTask2()", e);
						//return false;
						throw e;
					}
					return true;
				}
			
			//Approve Task:Source Head
			
			public static boolean ApproveTaskSourceLead(String [] data) throws Exception{
				try{
                    //Search Product
				    CommonProjectFunctions.searchProduct(data[7]);
				    CommonFunctions.waitForPageLoaded();
				  //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					//Navigate to Sourcing Tab
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Inreview--Verified****"); 
				    //Navigate to myWork
				    driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
					CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfigSCH);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfigSCH, "lnk", "Confirm Sourcing Configuration");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", status);
					
					//Click on + sign
				//	CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					//Navigate to Product
					   //Search Product
					    CommonProjectFunctions.searchProduct(data[7]);
					    CommonFunctions.waitForPageLoaded();
					  //Switch to default frame
						driver.switchTo().defaultContent();
						//Switch to content frame
						driver.switchTo().frame("contentframe");
						//Navigate to Sourcing Tab
						SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						CommonFunctions.waitForVisibilityOfElement(SourcingTab);
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
						CommonFunctions.waitForPageLoaded();
						//Select Season
						CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
						CommonFunctions.waitForPageLoaded();
						//select Source
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(SourceDropDown);
						CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						//Assertion Verification of Source state
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
					    log.info("****Sourcing Status is showing IN REVIEW--Verified****"); 
						}
						
				catch(Exception e){
				fail=true;
				log.error("Exception in ApproveTaskSourceLead"+e);
				throw e;
			}
			 return true;
			 }
			
			
			
			
//Approve Task:Source Head
			
			public static boolean ApproveTaskSourceHead(String [] data) throws Exception{
				try{
                    //Search Product
				    CommonProjectFunctions.searchProduct(data[7]);
				    CommonFunctions.waitForPageLoaded();
				  //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					//Navigate to Sourcing Tab
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Inreview--Verified****"); 
				    //Navigate to myWork
				    driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigSC2);
					CommonFunctions.clickButtonOrLink(lnkSourConfigSC2, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfigSCH);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfigSCH, "lnk", "Confirm Sourcing Configuration");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", status);
					
					//Click on + sign
				//	CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					//Navigate to Product
					   //Search Product
					    CommonProjectFunctions.searchProduct(data[7]);
					    CommonFunctions.waitForPageLoaded();
					  //Switch to default frame
						driver.switchTo().defaultContent();
						//Switch to content frame
						driver.switchTo().frame("contentframe");
						//Navigate to Sourcing Tab
						SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						CommonFunctions.waitForVisibilityOfElement(SourcingTab);
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
						CommonFunctions.waitForPageLoaded();
						//Select Season
						CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
						CommonFunctions.waitForPageLoaded();
						//select Source
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(SourceDropDown);
						CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						//Assertion Verification of Source state
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[8], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
					    log.info("****Sourcing Status is showing Approved--Verified****"); 
						}
						
				catch(Exception e){
				fail=true;
				log.error("Exception in ApproveTaskSourceHead"+e);
				throw e;
			}
			 return true;
			}
			

			//Approve Task:Source Head
			
			public static boolean ApproveTaskSourceLeadS(String [] data) throws Exception{
				try{
                    //Search Product
				    CommonProjectFunctions.searchProduct(data[17]);
				    CommonFunctions.waitForPageLoaded();
				  //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
					//Navigate to Sourcing Tab
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Inreview--Verified****"); 
				    //Navigate to myWork
				    driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
					CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfigSCH);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfigSCH, "lnk", "Confirm Sourcing Configuration");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", status);
					
					//Click on + sign
				//	CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					//Navigate to Product
					   //Search Product
					    CommonProjectFunctions.searchProduct(data[17]);
					    CommonFunctions.waitForPageLoaded();
					  //Switch to default frame
						driver.switchTo().defaultContent();
						//Switch to content frame
						driver.switchTo().frame("contentframe");
						CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
						//Navigate to Sourcing Tab
						SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						CommonFunctions.waitForVisibilityOfElement(SourcingTab);
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
						CommonFunctions.waitForPageLoaded();
						//Select Season
						CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
						CommonFunctions.waitForPageLoaded();
						//select Source
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(SourceDropDown);
						CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						//Assertion Verification of Source state
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
					    log.info("****Sourcing Status is showing IN REVIEW--Verified****"); 
						}
						
				catch(Exception e){
				fail=true;
				log.error("Exception in ApproveTaskSourceLeadS"+e);
				throw e;
			}
			 return true;
			 }
			
			
			
			
//Approve Task:Source Head
			
			public static boolean ApproveTaskSourceHeadS(String [] data) throws Exception{
				try{
                    //Search Product
				    CommonProjectFunctions.searchProduct(data[17]);
				    CommonFunctions.waitForPageLoaded();
				  //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
					//Navigate to Sourcing Tab
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Inreview--Verified****"); 
				    //Navigate to myWork
				    driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					CommonFunctions.waitForElementTobeClickable(lnkSourConfig);
					CommonFunctions.clickButtonOrLink(lnkSourConfig, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfigSCH);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfigSCH, "lnk", "Confirm Sourcing Configuration");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", status);
					
					//Click on + sign
				//	CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					//Navigate to Product
					   //Search Product
					    CommonProjectFunctions.searchProduct(data[17]);
					    CommonFunctions.waitForPageLoaded();
					  //Switch to default frame
						driver.switchTo().defaultContent();
						//Switch to content frame
						driver.switchTo().frame("contentframe");
						CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
						//Navigate to Sourcing Tab
						SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						CommonFunctions.waitForVisibilityOfElement(SourcingTab);
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
						CommonFunctions.waitForPageLoaded();
						//Select Season
						CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
						CommonFunctions.waitForPageLoaded();
						//select Source
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(SourceDropDown);
						CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						//Assertion Verification of Source state
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[8], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
					    log.info("****Sourcing Status is showing Approved--Verified****"); 
						}
						
				catch(Exception e){
				fail=true;
				log.error("Exception in ApproveTaskSourceHeadS"+e);
				throw e;
			}
			 return true;
			}
			
			
			public static boolean ApproveTaskSourceLeadSC2R(String [] data) throws Exception{
				try{
                    //Search Product
				    CommonProjectFunctions.searchProduct(data[17]);
				    CommonFunctions.waitForPageLoaded();
				  //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
					//Navigate to Sourcing Tab
					//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					//CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Inreview--Verified****"); 
				    //Navigate to myWork
				    driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
					CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfigSC2);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfigSC2, "lnk", "Confirm Sourcing Configuration");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", status);
					
					//Click on + sign
				//	CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Click on Radio Button
					CommonFunctions.clickButtonOrLink(approveRadioButton, "radio button","approveRadioButton");
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::textarea[1]"), data[18]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					//Navigate to Product
					   //Search Product
					    CommonProjectFunctions.searchProduct(data[17]);
					    CommonFunctions.waitForPageLoaded();
					  //Switch to default frame
						driver.switchTo().defaultContent();
						//Switch to content frame
						driver.switchTo().frame("contentframe");
						CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
						//Navigate to Sourcing Tab
						//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						//CommonFunctions.waitForVisibilityOfElement(SourcingTab);
						wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
						CommonFunctions.waitForPageLoaded();
						//Select Season
						CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
						CommonFunctions.waitForPageLoaded();
						//select Source
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(SourceDropDown);
						CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						//Assertion Verification of Source state
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
					    log.info("****Sourcing Status is showing IN REVIEW--Verified****"); 
						}
						
				catch(Exception e){
				fail=true;
				log.error("Exception in ApproveTaskSourceLeadSC2R"+e);
				throw e;
			}
			 return true;
			 }
			
			public static boolean ApproveTaskSourceHeadSC2R(String [] data) throws Exception{
				try{
                    //Search Product
				    CommonProjectFunctions.searchProduct(data[17]);
				    CommonFunctions.waitForPageLoaded();
				  //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
					//Navigate to Sourcing Tab
					//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					//CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing InReview--Verified****"); 
				    //Navigate to myWork
				    driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigSC2);
					CommonFunctions.clickButtonOrLink(lnkSourConfigSC2, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfigSC2);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfigSC2, "lnk", "Confirm Sourcing Configuration");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", status);
					
					//Click on + sign
				//	CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"), "Img", "Plus sign");
					//Click on Radio button
					CommonFunctions.clickButtonOrLink(approveRadioButton, "radio button","approveRadioButton");
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::textarea[1]"), data[18]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					//Navigate to Product
					   //Search Product
					    CommonProjectFunctions.searchProduct(data[17]);
					    CommonFunctions.waitForPageLoaded();
					  //Switch to default frame
						driver.switchTo().defaultContent();
						//Switch to content frame
						driver.switchTo().frame("contentframe");
						CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
						//Navigate to Sourcing Tab
						//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						//CommonFunctions.waitForVisibilityOfElement(SourcingTab);
						wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
						CommonFunctions.waitForPageLoaded();
						//Select Season
						CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
						CommonFunctions.waitForPageLoaded();
						//select Source
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(SourceDropDown);
						CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						//Assertion Verification of Source state
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[18], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
					    log.info("****Sourcing Status is showing Rejected--Verified for Solid Product****"); 
						}
						
				catch(Exception e){
				fail=true;
				log.error("Exception in ApproveTaskSourceHeadSC2R"+e);
				throw e;
			}
			 return true;
			}
			
			public static boolean ApproveTaskSourceLeadSC2(String [] data) throws Exception{
				try{
                    //Search Product
				    CommonProjectFunctions.searchProduct(data[7]);
				    CommonFunctions.waitForPageLoaded();
				  //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					//Navigate to Sourcing Tab
					//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					//CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Inreview--Verified****"); 
				    //Navigate to myWork
				    driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
					CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfigSC2);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfigSC2, "lnk", "Confirm Sourcing Configuration");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", status);
					
					//Click on + sign
				    CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"));
					
					
					//Click on Radio Button
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"), "Img", "Plus sign");
					CommonFunctions.clickButtonOrLink(approveRadioButton, "radio button","approveRadioButton");
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					//Navigate to Product
					   //Search Product
					    CommonProjectFunctions.searchProduct(data[7]);
					    CommonFunctions.waitForPageLoaded();
					  //Switch to default frame
						driver.switchTo().defaultContent();
						//Switch to content frame
						driver.switchTo().frame("contentframe");
						//Navigate to Sourcing Tab
						//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						//CommonFunctions.waitForVisibilityOfElement(SourcingTab);
						wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
						CommonFunctions.waitForPageLoaded();
						//Select Season
						CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
						CommonFunctions.waitForPageLoaded();
						//select Source
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(SourceDropDown);
						CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						//Assertion Verification of Source state
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
					    log.info("****Sourcing Status is showing IN REVIEW--Verified****"); 
						}
						
				catch(Exception e){
				fail=true;
				log.error("Exception in ApproveTaskSourceLeadSC2"+e);
				throw e;
			}
			 return true;
			 }
			
			public static boolean ApproveTaskSourceHeadSC2(String [] data) throws Exception{
				try{
                    //Search Product
				    CommonProjectFunctions.searchProduct(data[7]);
				    CommonFunctions.waitForPageLoaded();
				  //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					//Navigate to Sourcing Tab
					//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					//CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Inreview--Verified****"); 
				    //Navigate to myWork
				    driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigSC2);
					CommonFunctions.clickButtonOrLink(lnkSourConfigSC2, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfigSC2);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfigSC2, "lnk", "Confirm Sourcing Configuration");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", status);
					
					//Click on + sign
				//	CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"), "Img", "Plus sign");
					//Click on Radio button
					CommonFunctions.clickButtonOrLink(approveRadioButton, "radio button","approveRadioButton");
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					//Navigate to Product
					   //Search Product
					    CommonProjectFunctions.searchProduct(data[7]);
					    CommonFunctions.waitForPageLoaded();
					  //Switch to default frame
						driver.switchTo().defaultContent();
						//Switch to content frame
						driver.switchTo().frame("contentframe");
						//Navigate to Sourcing Tab
						//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						//CommonFunctions.waitForVisibilityOfElement(SourcingTab);
						wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
						CommonFunctions.waitForPageLoaded();
						//Select Season
						CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
						CommonFunctions.waitForPageLoaded();
						//select Source
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(SourceDropDown);
						CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						//Assertion Verification of Source state
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[10], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
					    log.info("****Sourcing Status is showing Approved For Bidding--Verified****"); 
						}
						
				catch(Exception e){
				fail=true;
				log.error("Exception in ApproveTaskSourceHeadSC2"+e);
				throw e;
			}
			 return true;
			}
			
			
			public static boolean ApproveTaskSourceLeadSC2S(String [] data) throws Exception{
				try{
                    //Search Product
				    CommonProjectFunctions.searchProduct(data[17]);
				    CommonFunctions.waitForPageLoaded();
				  //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
					//Navigate to Sourcing Tab
					//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					//CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Inreview--Verified****"); 
				    //Navigate to myWork
				    driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
					CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfigSC2);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfigSC2, "lnk", "Confirm Sourcing Configuration");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", status);
					
					//Click on + sign
				//	CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					//Navigate to Product
					   //Search Product
					    CommonProjectFunctions.searchProduct(data[17]);
					    CommonFunctions.waitForPageLoaded();
					  //Switch to default frame
						driver.switchTo().defaultContent();
						//Switch to content frame
						driver.switchTo().frame("contentframe");
						CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
						//Navigate to Sourcing Tab
						//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						//CommonFunctions.waitForVisibilityOfElement(SourcingTab);
						wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
						CommonFunctions.waitForPageLoaded();
						//Select Season
						CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
						CommonFunctions.waitForPageLoaded();
						//select Source
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(SourceDropDown);
						CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						//Assertion Verification of Source state
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
					    log.info("****Sourcing Status is showing IN REVIEW--Verified****"); 
						}
						
				catch(Exception e){
				fail=true;
				log.error("Exception in ApproveTaskSourceLeadSC2S"+e);
				throw e;
			}
			 return true;
			 }
			
			public static boolean ApproveTaskSourceHeadSC2S(String [] data) throws Exception{
				try{
                    //Search Product
				    CommonProjectFunctions.searchProduct(data[17]);
				    CommonFunctions.waitForPageLoaded();
				  //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
					//Navigate to Sourcing Tab
					//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					//CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Inreview--Verified****"); 
				    //Navigate to myWork
				    driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigSC2);
					CommonFunctions.clickButtonOrLink(lnkSourConfigSC2, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfigSC2);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfigSC2, "lnk", "Confirm Sourcing Configuration");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", status);
					
					//Click on + sign
				//	CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					//Navigate to Product
					   //Search Product
					    CommonProjectFunctions.searchProduct(data[17]);
					    CommonFunctions.waitForPageLoaded();
					  //Switch to default frame
						driver.switchTo().defaultContent();
						//Switch to content frame
						driver.switchTo().frame("contentframe");
						CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
						//Navigate to Sourcing Tab
						//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						//CommonFunctions.waitForVisibilityOfElement(SourcingTab);
						wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
						CommonFunctions.waitForPageLoaded();
						//Select Season
						CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
						CommonFunctions.waitForPageLoaded();
						//select Source
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(SourceDropDown);
						CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
						Thread.sleep(1000);
						CommonFunctions.waitForPageLoaded();
						//Assertion Verification of Source state
			    		CommonFunctions.waitForElementTobeClickable(strSouceState);
			    		GettingText(strSouceState);
			    		CommonFunctions.AssertEqualsVerification(ActualValue, data[10], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
					    log.info("****Sourcing Status is showing Approved For Bidding--Verified****"); 
						}
						
				catch(Exception e){
				fail=true;
				log.error("Exception in ApproveTaskSourceHeadSC2S"+e);
				throw e;
			}
			 return true;
			}
			
			public static boolean UnSelectSource(String [] data) throws Exception{
				try{
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingDropDown));
		            //CommonFunctions.waitForElementTobeClickable(SourcingDropDown);
		            Select dropdDown = new Select(driver.findElement(By.xpath("//td[contains(text(),'Source')]//following::select[1]")));
				     List<WebElement> allOptions=dropdDown.getOptions();
		            for(int i=0;i<allOptions.size();i++){
		   		     String RequiredValue=allOptions.get(i).getText();
		   		    // System.out.println(RequiredValue);
		   		     if(RequiredValue.contains(data[146]))
		   		     {
		   		    	 CommonFunctions.selectFromDropDownByVisibleTextUpdated(SourcingDropDown, RequiredValue, "Source DropDown Selection");
		   		    	 wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingDropDown));
		   		    	 CommonFunctions.waitForPageLoaded();
		   		    	 break;
		   		     }
		   		    
		            }
				}
				catch(Exception e){
					fail=true;
					log.error("Exception in UnSelectSource"+e);
					throw e;
				}
				return true;
			}
			//Copy/ Link Assortment/Solid to Retail 
			public static String CopyLinkProductRetailItem1(String [] data) throws Exception{
				try{
					UnSelectSource(data);
					CommonFunctions.waitForElementTobeClickable(RFQPageActionDropDown);
					Select dropdDown = new Select(driver.findElement(By.xpath("//select[@id='prodseasonActions']")));
				     List<WebElement> allOptions=dropdDown.getOptions();
			      for(int i=0;i<allOptions.size();i++){
				     String RequiredValue=allOptions.get(i).getText();
				    // System.out.println(RequiredValue);
				     if(RequiredValue.contains(data[141]))
				     {
				     CommonFunctions.selectFromDropDownByVisibleTextUpdated(RFQPageActionDropDown, RequiredValue, "Source DropDown Selection");
				     wait.until(ExpectedConditions.titleIs(data[222]));
				    CommonFunctions.waitForPageLoaded();
				     break;
				     }
			      }
			     CommonFunctions.waitForElementTobeClickable(ProductTypeInCopyLink);
			     Select dropdDown1 = new Select(driver.findElement(By.xpath("//select[@id='productTypedata']")));
			     List<WebElement> allOptions1=dropdDown1.getOptions();
		         for(int i=0;i<allOptions1.size();i++){
			     String RequiredValue=allOptions1.get(i).getText();
			     System.out.println(RequiredValue);
			     if(RequiredValue.contains(data[322]))
			     {
			     CommonFunctions.selectFromDropDownByVisibleTextUpdated(ProductTypeInCopyLink, data[322], "product Type");
			     break;
			     }
		         }
			     //Selecting the Product Type
			     CommonFunctions.waitForElementTobeClickable(RelationShipType);
			     Select dropdDown2 = new Select(driver.findElement(By.xpath("//select[@id='copyModedata']")));
			     List<WebElement> allOptions2=dropdDown2.getOptions();
		         for(int i=0;i<allOptions2.size();i++){
			     String RequiredValue=allOptions2.get(i).getText();
			     System.out.println(RequiredValue);
			     if(RequiredValue.contains(data[323]))
			     {
			      CommonFunctions.selectFromDropDownByVisibleTextUpdated(RelationShipType, data[323], "RelationShipType");
				   //Selecting a RelationShip Type
			     break;
			     }
		         }
			
			     CommonFunctions.waitForElementTobeClickable(ColorwayCheckBox);
			     CommonFunctions.clickButtonOrLink(ColorwayCheckBox, "Check Box", "ColorWay Check Box");
			     //Selecting the Colorway Check box
			     CommonFunctions.waitForElementTobeClickable(CopyLinkNextButton);
			     CommonFunctions.clickButtonOrLink(CopyLinkNextButton, "Button", "Next");
			     //Clicking Next Button
			     wait.until(ExpectedConditions.visibilityOfElementLocated(SoftGoodsIncluded));
			     CommonFunctions.waitForPageLoaded();
			     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SoftGoodsIncluded, data[324], "SoftGoodIncluded");
			     //Selecting the SoftGoods Value
			     wait.until(ExpectedConditions.visibilityOfElementLocated(ElectronicsIncluded));
			     CommonFunctions.selectFromDropDownByVisibleTextUpdated(ElectronicsIncluded, data[325], "ElectronicsIncluded");
			     //Selecting the Electronics Included Value
			     CommonFunctions.waitForElementTobeClickable(CopyLinkNextButton);
			     CommonFunctions.clickButtonOrLink(CopyLinkNextButton, "Button", "Next");
			     //Clicking Next Button
			     wait.until(ExpectedConditions.titleIs(data[144]));
			     CommonFunctions.waitForPageLoaded();
			     wait.until(ExpectedConditions.visibilityOfElementLocated(ViewProductButton));
			     CommonFunctions.clickButtonOrLink(ViewProductButton, "Button", "view Product");
			    CommonFunctions.waitForPageLoaded();
			     wait.until(ExpectedConditions.visibilityOfElementLocated(RetailItemIdentification));
			     CopyLinkRetailItem1=driver.findElement(RetailItemIdentification).getText();
			     log.info("Created Copy/Link Product RetailItem Value is "+CopyLinkRetailItem1);
		           
			}
				catch(Exception e){
					fail=true;
					log.error("Exception in CopyLinkProductRetailItem1"+e);
					throw e;
				}
			 return CopyLinkRetailItem1;
			}
			
			//SC1:-Create and Approve Sourcing Config for Assortment
			public static boolean SC1_CreateAndApproveSCForAssortment(String [] data) throws Exception{
				try{
					
					//***Assortment Product***//
					 
				    CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				    //Search Product
				    CommonProjectFunctions.searchProduct(data[7]);
				    CommonFunctions.waitForPageLoaded();
					//Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					//CommonFunctions.waitForPageLoaded()	;
					//Navigate to Sourcing Tab
					//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(sourceActions));
					AddSourcingConfigurationSC1("AEQUS");
				    //Approve Set Proposed Sourcing Status:
				    approveTask1(data,data[8]);
				    approveTask2(data,data[8]);
				    //Navigate to Product
				    CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				   //Search Product
				    CommonProjectFunctions.searchProduct(data[7]);
				    CommonFunctions.waitForPageLoaded();
				  //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
				  
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing IN REVIEW--Verified****"); 
				    CommonProjectFunctions.logOut();
				    //****Sourcing Lead****//
					openBrowser();
					Thread.sleep(2000);
					launchApp(data[12],data[13]);
					ApproveTaskSourceLead(data);
					CommonProjectFunctions.logOut();
				    //****Sourcing Head****//
					openBrowser();
					Thread.sleep(2000);
					launchApp(data[14],data[15]);
					ApproveTaskSourceHead(data);
					CommonProjectFunctions.logOut();
					openBrowser();
					Thread.sleep(2000);
					//lofin as engineer user
					launchApp(data[0],data[1]);
					//Navigate to Product
				    CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				   //Search Product
				    CommonProjectFunctions.searchProduct(data[7]);
				    CommonFunctions.waitForPageLoaded();
				  //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[8], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Approved--Verified****"); 
				    //Navigate to Retail Item associated to Assortment Product
				    CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				   //Search Product
				    CommonProjectFunctions.searchProduct(data[16]);
				    CommonFunctions.waitForPageLoaded();
				  //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[8], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Approved--Verified---For Retail Item****");
		
				    
				    
				    
				    //***Product Solid***//
				    CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				    //Search Product
				    CommonProjectFunctions.searchProduct(data[17]);
				    CommonFunctions.waitForPageLoaded();
				    //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					//Click on Solid Product Number
				    CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
					//CommonFunctions.waitForPageLoaded()	;
					//Navigate to Sourcing Tab
					//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForVisibilityOfElement(SourcingTab);
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(sourceActions));
					AddSourcingConfigurationSC1("AEQUS");
				    //Approve Set Proposed Sourcing Status:
				    approveTask1S(data,data[8]);
				    approveTask2S(data,data[8]);
				    //Navigate to Product
				    CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				   //Search Product
				    CommonProjectFunctions.searchProduct(data[17]);
				    CommonFunctions.waitForPageLoaded();
				    //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing IN REVIEW for Solid Product--Verified-Solid Product****"); 
				    CommonProjectFunctions.logOut();
				    //****Sourcing Lead****//
					openBrowser();
					Thread.sleep(2000);
					launchApp(data[12],data[13]);
					ApproveTaskSourceLeadS(data);
					CommonProjectFunctions.logOut();
				    //****Sourcing Head****//
					openBrowser();
					Thread.sleep(2000);
					launchApp(data[14],data[15]);
					ApproveTaskSourceHeadS(data);
					CommonProjectFunctions.logOut();
					openBrowser();
					Thread.sleep(2000);
					//lofin as engineer user
					launchApp(data[0],data[1]);
					//Navigate to Product
				    CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				   //Search Product
				    CommonProjectFunctions.searchProduct(data[17]);
				    CommonFunctions.waitForPageLoaded();
				    //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					//Click on Retail Item Product Number
					CommonFunctions.clickButtonOrLink(ProductNumberRetail, "link", "Product - Retail Item");
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[8], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Approved--Verified for Solid Retail Product****"); 
				    
				    
				}
				catch(Exception e){
					fail=true;
					log.error("Exception in SC1_CreateAndApproveSCForAssortment"+e);
					throw e;
				}
				 return true;
			}
			//Approve Task by Engineer for SC2
			public static boolean approveTaskEngineerSC2(String[] data,String status) throws Exception{
				try{
					
					
					
				/*	//Click on mywork
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.waitForVisibilityOfElement(mySiteTab);
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					//driver.switchTo().defaultContent();
					//driver.switchTo().frame("sidebarframe");
					//Click on Sourcing Config
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
					CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfig);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfig, "lnk", "Confirm Sourcing Configuration");
					log.info("***Set proposed sourcing status workitem list displayed for product-Verified***");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//Click on Approve Radio button
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::td[contains(text(),'"+data[8]+"')]/preceding::td[1]/input"), "radio", status);
					//CommonFunctions.clickButtonOrLink(rejectRadioButton, "radio button","rejectRadioButton");
					//Click on + sign
				    //CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");*/
					
					
					//Click on mywork
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.waitForVisibilityOfElement(mySiteTab);
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForVisibilityOfElement(SmokeFlow.myWork);
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					//driver.switchTo().defaultContent();
					//driver.switchTo().frame("sidebarframe");
					//Click on Sourcing Config
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
					CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfig);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfig, "lnk", "Confirm Sourcing Configuration");
					log.info("***Set proposed sourcing status workitem list displayed for product-Verified***");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//Click on Approve Radio button
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::td[contains(text(),'"+data[8]+"')]/preceding::td[1]/input"), "radio", status);
					//CommonFunctions.clickButtonOrLink(rejectRadioButton, "radio button","rejectRadioButton");
					//Click on + sign
				    //CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");

					
						
						
					
					}catch(Exception e){
						fail=true;
						log.error("Exception in approveTaskEngineerSC2()", e);
						//return false;
						throw e;
					}
					return true;
				}
			
			//Approve Task by Engineer for SC2
			public static boolean approveTaskEngineerSC2R(String[] data,String status) throws Exception{
				try{
					
					
					
					//Click on mywork
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					//driver.switchTo().defaultContent();
					//driver.switchTo().frame("sidebarframe");
					//Click on Sourcing Config
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
					CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfig);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfig, "lnk", "Confirm Sourcing Configuration");
					log.info("***Set proposed sourcing status workitem list displayed for product-Verified***");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//Click on Approve Radio button
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::td[contains(text(),'"+data[18]+"')]/preceding::td[1]/input"), "radio", status);
					//CommonFunctions.clickButtonOrLink(rejectRadioButton, "radio button","rejectRadioButton");
					//Click on + sign
				    //CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					
					
					/*//Click on mywork
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					//driver.switchTo().defaultContent();
					//driver.switchTo().frame("sidebarframe");
					//Click on Sourcing Config
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
					CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfig);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfig, "lnk", "Confirm Sourcing Configuration");
					log.info("***Set proposed sourcing status workitem list displayed for product-Verified***");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//Click on Approve Radio button
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::td[contains(text(),'"+data[8]+"')]/preceding::td[1]/input"), "radio", status);
					CommonFunctions.clickButtonOrLink(rejectRadioButton, "radio button","rejectRadioButton");
					//Click on + sign
				    //CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[5]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[7]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");*/

					
						
						
					
					}catch(Exception e){
						fail=true;
						log.error("Exception in approveTaskEngineerSC2R()", e);
						//return false;
						throw e;
					}
					return true;
				}
			
			
			//Approve Task by Engineer for SC2
			public static boolean approveTaskEngineerSC2S(String[] data,String status) throws Exception{
				try{
					
					/*//Click on mywork
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					//driver.switchTo().defaultContent();
					//driver.switchTo().frame("sidebarframe");
					//Click on Sourcing Config
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
					CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfig);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfig, "lnk", "Confirm Sourcing Configuration");
					log.info("***Set proposed sourcing status workitem list displayed for product-Verified***");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//Click on Approve Radio button
					//CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::td[contains(text(),'"+data[8]+"')]/preceding::td[1]/input"), "radio", status);
					//CommonFunctions.clickButtonOrLink(approveRadioButton, "radio button","approveRadioButton");
					//Click on + sign
				    //CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");*/
					
					
					//Click on mywork
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
					CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
					//driver.switchTo().defaultContent();
					//driver.switchTo().frame("sidebarframe");
					//Click on Sourcing Config
					CommonFunctions.waitForElementTobeClickable(lnkSourConfigAP1);
					CommonFunctions.clickButtonOrLink(lnkSourConfigAP1, "lnk", "Sourcing Config");
					CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfig);
					//Click Set Proposed Sourcing Status
					CommonFunctions.clickButtonOrLink(lnkConfirmSourConfig, "lnk", "Confirm Sourcing Configuration");
					log.info("***Set proposed sourcing status workitem list displayed for product-Verified***");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
					//Select check box
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']/preceding::input[1]"));  //selectCheckBox
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
					//Click on Approve Radio button
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::td[contains(text(),'"+data[8]+"')]/preceding::td[1]/input"), "radio", status);
					//CommonFunctions.clickButtonOrLink(approveRadioButton, "radio button","approveRadioButton");
					//Click on + sign
				    //CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
					//System.out.println(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::img[1]"), "Img", "Plus sign");
					
					//Enter comment
					CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : AEQUS ("+data[17]+")']//following::textarea[1]"), data[10]);
					
					//Click on Complete
					CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
					
						
						
					
					}catch(Exception e){
						fail=true;
						log.error("Exception in approveTaskEngineerSC2S()", e);
						//return false;
						throw e;
					}
					return true;
				}
			//SC2 - Update Sourcing Status of assortment sourcing configuration
			public static boolean SC2_UpdateSCForAssortment(String [] data) throws Exception{
				try{
					/***
					 * ASSORTMENT PRODUCT
					 */
					//Navigate to Product
				    CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				    //Search Product
				    CommonProjectFunctions.searchProduct(data[7]);
				    CommonFunctions.waitForPageLoaded();
				    //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					
					//Update Source
					CommonFunctions.waitForPageLoaded();
					//Click on Upadte Source menu
					CommonFunctions.selectFromDropDownByIndex(strUpdateSourceActions, 1);
					CommonFunctions.waitForPageLoaded();
					//Update Sourcing status
					CommonFunctions.selectFromDropDownByIndex(strUpdateSourceStatus, 1);
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
					CommonFunctions.waitForPageLoaded();
					//driver.switchTo().defaultContent();
					//driver.switchTo().frame("sidebarframe");
					//CommonFunctions.clickButtonOrLink(mySiteTab, "Site Tab");
					//CommonFunctions.waitForVisibilityOfElement(mySiteTab);
					//Set proposed sourcing status workitem
					approveTaskEngineerSC2(data,data[8]);
					CommonProjectFunctions.logOut();
					//Source Lead
					openBrowser();
					Thread.sleep(2000);
					launchApp(data[12],data[13]);
					ApproveTaskSourceLeadSC2(data);
					CommonProjectFunctions.logOut();
					
				    //Source Head
					openBrowser();
					Thread.sleep(2000);
					launchApp(data[14],data[15]);
					ApproveTaskSourceHeadSC2(data);
					CommonProjectFunctions.logOut();
					openBrowser();
					Thread.sleep(2000);
					/***
					 * ASSORTMENT: RETAIL ITEM
					 */
					//Login with Engineer user
					launchApp(data[0],data[1]);
					//Navigate to Product
				    CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				   //Search Product
				    CommonProjectFunctions.searchProduct(data[16]);
				    CommonFunctions.waitForPageLoaded();
				    //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[10], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Approved for Bidding--Verified---For Retail Item****"); 
				    
				    
				    /***
				     * SOLID PRODUCT
				     */
				  //Navigate to Product
				    CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				    //Search Product
				    CommonProjectFunctions.searchProduct(data[17]);
				    CommonFunctions.waitForPageLoaded();
				    //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					
					//Update Source
					CommonFunctions.waitForPageLoaded();
					//Click on Upadte Source menu
					CommonFunctions.selectFromDropDownByIndex(strUpdateSourceActions, 1);
					CommonFunctions.waitForPageLoaded();
					//Update Sourcing status
					CommonFunctions.selectFromDropDownByIndex(strUpdateSourceStatus, 1);
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
					CommonFunctions.waitForPageLoaded();
					
					//Set proposed sourcing status workitem
					approveTaskEngineerSC2S(data,data[8]);
					CommonProjectFunctions.logOut();
					
					//Source Lead
					openBrowser();
					Thread.sleep(2000);
					launchApp(data[12],data[13]);
					ApproveTaskSourceLeadSC2S(data);
					CommonProjectFunctions.logOut();
					
				    //Source Head
					openBrowser();
					Thread.sleep(2000);
					launchApp(data[14],data[15]);
					ApproveTaskSourceHeadSC2S(data);
					CommonProjectFunctions.logOut();
					openBrowser();
					Thread.sleep(2000);
					
					
					/***
					 * RETAIL ITEM SOLID PRODUCT
					 */
					//Login with Engineer user
					launchApp(data[0],data[1]);
					//Navigate to Product
				    CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				   //Search Product
				    CommonProjectFunctions.searchProduct(data[17]);
				    CommonFunctions.waitForPageLoaded();
				    //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(ProductNumberRetail, "link", "RETAIL ITEM SOLID PRODUCT");
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[10], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Approved for Bidding--Verified---For Retail Item****"); 
				    
				    /**
				     * Rejected : Sourcing Status
				     */
				    
				    /***
					 * Product Solid 
					 */
					//Navigate to Product
				    CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				    //Search Product
				    CommonProjectFunctions.searchProduct(data[17]);
				    CommonFunctions.waitForPageLoaded();
				    //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(ProductNumberSolid, "link", "Product Assortment/Solid");
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					
					//Update Source
					CommonFunctions.waitForPageLoaded();
					//Click on Upadte Source menu
					CommonFunctions.selectFromDropDownByIndex(strUpdateSourceActions, 1);
					CommonFunctions.waitForPageLoaded();
					//Update Sourcing status
					CommonFunctions.selectFromDropDownByIndex(strUpdateSourceStatus, 1);
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
					CommonFunctions.waitForPageLoaded();
					
					//Set proposed sourcing status workitem
					approveTaskEngineerSC2R(data,data[8]);
					
					//Source Lead
					openBrowser();
					Thread.sleep(2000);
					launchApp(data[12],data[13]);
					ApproveTaskSourceLeadSC2R(data);
					CommonProjectFunctions.logOut();
					
				    //Source Head
					openBrowser();
					Thread.sleep(2000);
					launchApp(data[14],data[15]);
					ApproveTaskSourceHeadSC2R(data);
					CommonProjectFunctions.logOut();
					openBrowser();
					Thread.sleep(2000);
					
                    /***
                     * Solid: RETAIL ITEM
                     */
					//Login with Engineer user
					launchApp(data[0],data[1]);
					//Navigate to Product
				    CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
				   //Search Product
				    CommonProjectFunctions.searchProduct(data[17]);
				    //CommonFunctions.waitForPageLoaded();
				    
				    CommonFunctions.waitForPageLoaded();
				    //Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(ProductNumberRetail, "link", "Product Retail");
					CommonFunctions.waitForPageLoaded();
					wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Assertion Verification of Source state
		    		CommonFunctions.waitForElementTobeClickable(strSouceState);
		    		GettingText(strSouceState);
		    		CommonFunctions.AssertEqualsVerification(ActualValue, data[10], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
				    log.info("****Sourcing Status is showing Approved for bidding and not Rejected--Verified---For Retail Item****"); 
					
				    
				}
				catch(Exception e){
							fail=true;
							log.error("Exception in SC2_UpdateSCForAssortment "+e);
							throw e;
							}
						return true;
			}
			
			//Create and Approve Sourcing Config Status for Retail Item
			
			
					//SC3:-Create and Approve Sourcing Config for Retail Item
					public static boolean SC3_CreateAndApproveSCForRetail(String [] data) throws Exception{
						try{
							CI296.navigateToProduct(data);
							CommonFunctions.waitForPageLoaded();
						    //Search Product
						    CommonProjectFunctions.searchProduct(data[7]);
						    CommonFunctions.waitForPageLoaded();
							//Switch to default frame
							driver.switchTo().defaultContent();
							//Switch to content frame
							driver.switchTo().frame("contentframe");
							//CommonFunctions.waitForPageLoaded()	;
							//Navigate to Sourcing Tab
							//SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
							CommonFunctions.waitForVisibilityOfElement(SourcingTab);
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
							CommonFunctions.waitForPageLoaded();
							//Select Season
							CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
						    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
							CommonFunctions.waitForPageLoaded();
							SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(sourceActions));
						    AddSourcingConfiguration("AEQUS");
						    //Approve Set Proposed Sourcing Status:
						    approveTask1(data,data[8]);
						    approveTask2(data,data[8]);
						    //Navigate to Product
						    CI296.navigateToProduct(data);
							CommonFunctions.waitForPageLoaded();
						   //Search Product
						    CommonProjectFunctions.searchProduct(data[7]);
						    CommonFunctions.waitForPageLoaded();
						  //Switch to default frame
							driver.switchTo().defaultContent();
							//Switch to content frame
							driver.switchTo().frame("contentframe");
						  
							wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
							CommonFunctions.waitForPageLoaded();
							//Select Season
							CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
						    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
							CommonFunctions.waitForPageLoaded();
							//select Source
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.waitForElementTobeClickable(SourceDropDown);
							CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
							Thread.sleep(1000);
							CommonFunctions.waitForPageLoaded();
							//Assertion Verification of Source state
				    		CommonFunctions.waitForElementTobeClickable(strSouceState);
				    		GettingText(strSouceState);
				    		CommonFunctions.AssertEqualsVerification(ActualValue, data[11], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
						    log.info("****Sourcing Status is showing IN REVIEW--Verified****"); 
						    CommonProjectFunctions.logOut();
						    //****Sourcing Lead****//
							openBrowser();
							Thread.sleep(2000);
							launchApp(data[12],data[13]);
							ApproveTaskSourceLead(data);
							CommonProjectFunctions.logOut();
						    //****Sourcing Head****//
							openBrowser();
							Thread.sleep(2000);
							launchApp(data[14],data[15]);
							ApproveTaskSourceHead(data);
							CommonProjectFunctions.logOut();
							openBrowser();
							Thread.sleep(2000);
							//lofin as engineer user
							launchApp(data[0],data[1]);
							//Navigate to Product
						    CI296.navigateToProduct(data);
							CommonFunctions.waitForPageLoaded();
						   //Search Product
						    CommonProjectFunctions.searchProduct(data[7]);
						    CommonFunctions.waitForPageLoaded();
						  //Switch to default frame
							driver.switchTo().defaultContent();
							//Switch to content frame
							driver.switchTo().frame("contentframe");
							wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
							CommonFunctions.waitForPageLoaded();
							//Select Season
							CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
						    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
							CommonFunctions.waitForPageLoaded();
							//select Source
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.waitForElementTobeClickable(SourceDropDown);
							CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
							Thread.sleep(1000);
							CommonFunctions.waitForPageLoaded();
							//Assertion Verification of Source state
				    		CommonFunctions.waitForElementTobeClickable(strSouceState);
				    		GettingText(strSouceState);
				    		CommonFunctions.AssertEqualsVerification(ActualValue, data[8], "Actual And Expected Cost Sheet Sourcing Status state Are Not Matched.Assertion Failed.Please check");
						    log.info("****Sourcing Status is showing Approved--Verified****");   
                            //Approve the sourcing status as 'Approved for Bidding' engineer user
						  //Navigate to Product
						    CI296.navigateToProduct(data);
							CommonFunctions.waitForPageLoaded();
						   //Search Product
						    CommonProjectFunctions.searchProduct(data[7]);
						    CommonFunctions.waitForPageLoaded();
						  //Switch to default frame
							driver.switchTo().defaultContent();
							//Switch to content frame
							driver.switchTo().frame("contentframe");
							wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
							CommonFunctions.waitForPageLoaded();
							//Select Season
							CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
						    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
							CommonFunctions.waitForPageLoaded();
							//select Source
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.waitForElementTobeClickable(SourceDropDown);
							CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
							Thread.sleep(1000);
							CommonFunctions.waitForPageLoaded();
							//Click on Upadte Source menu
							CommonFunctions.selectFromDropDownByIndex(strUpdateSourceActions, 1);
							CommonFunctions.waitForPageLoaded();
							//Update Sourcing status
							CommonFunctions.selectFromDropDownByIndex(strUpdateSourceStatus, 1);
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
							CommonFunctions.waitForPageLoaded();
							//*******Login as admin user and update the status to Approved*******
							CommonProjectFunctions.logOut();
							openBrowser();
							Thread.sleep(2000);
							//lofin as engineer user
							launchApp(data[16],data[17]);
							//Navigate to Product
						    CI296.navigateToProduct(data);
							CommonFunctions.waitForPageLoaded();
						   //Search Product
						    CommonProjectFunctions.searchProduct(data[7]);
						    CommonFunctions.waitForPageLoaded();
						  //Switch to default frame
							driver.switchTo().defaultContent();
							//Switch to content frame
							driver.switchTo().frame("contentframe");
							wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
							CommonFunctions.waitForPageLoaded();
							//Select Season
							CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
						    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
							CommonFunctions.waitForPageLoaded();
							//select Source
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.waitForElementTobeClickable(SourceDropDown);
							CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
							Thread.sleep(1000);
							CommonFunctions.waitForPageLoaded();
							//Click on Upadte Source menu
							CommonFunctions.selectFromDropDownByIndex(strUpdateSourceActions, 1);
							CommonFunctions.waitForPageLoaded();
							//Update Sourcing status
							CommonFunctions.selectFromDropDownByIndex(strUpdateSourceStatus, 2);
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
							CommonFunctions.waitForPageLoaded();
							//*******Login as admin user and update the status to Approved*******
							CommonProjectFunctions.logOut();
							openBrowser();
							Thread.sleep(2000);
							//lofin as engineer user
							launchApp(data[0],data[1]);
							//Navigate to Product
						    CI296.navigateToProduct(data);
							CommonFunctions.waitForPageLoaded();
						   //Search Product
						    CommonProjectFunctions.searchProduct(data[7]);
						    CommonFunctions.waitForPageLoaded();
						  //Switch to default frame
							driver.switchTo().defaultContent();
							//Switch to content frame
							driver.switchTo().frame("contentframe");
							wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
							CommonFunctions.waitForPageLoaded();
							//Select Season
							CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
						    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
							CommonFunctions.waitForPageLoaded();
							//select Source
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.waitForElementTobeClickable(SourceDropDown);
							CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
							Thread.sleep(1000);
							CommonFunctions.waitForPageLoaded();
							//Click on Upadte Source menu
							CommonFunctions.selectFromDropDownByIndex(strUpdateSourceActions, 1);
							CommonFunctions.waitForPageLoaded();
							//Update Sourcing status
							CommonFunctions.selectFromDropDownByIndex(strUpdateSourceStatus, 3);
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
							CommonFunctions.waitForPageLoaded();
							//*******Login as admin user and update the status to Approved*******
							CommonProjectFunctions.logOut();
							openBrowser();
							Thread.sleep(2000);
							//lofin as engineer user
							launchApp(data[16],data[17]);
							//Navigate to Product
						    CI296.navigateToProduct(data);
							CommonFunctions.waitForPageLoaded();
						   //Search Product
						    CommonProjectFunctions.searchProduct(data[7]);
						    CommonFunctions.waitForPageLoaded();
						  //Switch to default frame
							driver.switchTo().defaultContent();
							//Switch to content frame
							driver.switchTo().frame("contentframe");
							wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingTab));
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(SourcingTab, "link", "SourcingTab");
							CommonFunctions.waitForPageLoaded();
							//Select Season
							CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
						    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, data[6], "Saeson Year");
							CommonFunctions.waitForPageLoaded();
							//select Source
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.waitForElementTobeClickable(SourceDropDown);
							CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
							Thread.sleep(1000);
							CommonFunctions.waitForPageLoaded();
							//Click on Upadte Source menu
							CommonFunctions.selectFromDropDownByIndex(strUpdateSourceActions, 1);
							CommonFunctions.waitForPageLoaded();
							//Update Sourcing status
							CommonFunctions.selectFromDropDownByIndex(strUpdateSourceStatus, 2);
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
							CommonFunctions.waitForPageLoaded();
						    
						}
						catch(Exception e){
							fail=true;
							log.error("Exception in SC3_CreateAndApproveSCForRetail"+e);
							throw e;
						}
						 return true;
					}
					 //Copy/Link Product to Retail Item
					public static String CopyLinkProductRetailItem(String [] data) throws Exception{
						try{
							//UnSelectSource(data);
							CommonFunctions.waitForElementTobeClickable(RFQPageActionDropDown);
							Select dropdDown = new Select(driver.findElement(By.xpath("//select[@id='prodseasonActions']")));
						     List<WebElement> allOptions=dropdDown.getOptions();
					         for(int i=0;i<allOptions.size();i++){
						     String RequiredValue=allOptions.get(i).getText();
						    // System.out.println(RequiredValue);
						     if(RequiredValue.contains(data[35]))
						     {
						     CommonFunctions.selectFromDropDownByVisibleTextUpdated(RFQPageActionDropDown, RequiredValue, "Source DropDown Selection");
						     wait.until(ExpectedConditions.titleIs(data[36]));
						    CommonFunctions.waitForPageLoaded();
						     break;
						     }
					      }
					     CommonFunctions.waitForElementTobeClickable(ProductTypeInCopyLink);
					     Select dropdDown1 = new Select(driver.findElement(By.xpath("//select[@id='productTypedata']")));
					     List<WebElement> allOptions1=dropdDown1.getOptions();
				         for(int i=0;i<allOptions1.size();i++){
					     String RequiredValue=allOptions1.get(i).getText();
					     System.out.println(RequiredValue);
					     if(RequiredValue.contains(data[37]))
					     {
					     CommonFunctions.selectFromDropDownByVisibleTextUpdated(ProductTypeInCopyLink, data[37], "product Type");
					     break;
					     }
				         }
					     //Selecting the Product Type
					     CommonFunctions.waitForElementTobeClickable(RelationShipType);
					     Select dropdDown2 = new Select(driver.findElement(By.xpath("//select[@id='copyModedata']")));
					     List<WebElement> allOptions2=dropdDown2.getOptions();
				         for(int i=0;i<allOptions2.size();i++){
					     String RequiredValue=allOptions2.get(i).getText();
					     System.out.println(RequiredValue);
					     if(RequiredValue.contains(data[38]))
					     {
					      CommonFunctions.selectFromDropDownByVisibleTextUpdated(RelationShipType, data[38], "RelationShipType");
						   //Selecting a RelationShip Type
					     break;
					     }
				         }
					
					     CommonFunctions.waitForElementTobeClickable(ColorwayCheckBox);
					     CommonFunctions.clickButtonOrLink(ColorwayCheckBox, "Check Box", "ColorWay Check Box");
					     //Selecting the Colorway Check box
					     CommonFunctions.waitForElementTobeClickable(SourceCheckBox);
					     CommonFunctions.clickButtonOrLink(SourceCheckBox, "Check Box", "Source Check Box");
					     //Selecting the Source Check box
					     CommonFunctions.waitForElementTobeClickable(CopyLinkNextButton);
					     CommonFunctions.clickButtonOrLink(CopyLinkNextButton, "Button", "Next");
					     //Clicking Next Button
					     wait.until(ExpectedConditions.visibilityOfElementLocated(SoftGoodsIncluded));
					     CommonFunctions.waitForPageLoaded();
					     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SoftGoodsIncluded, data[39], "SoftGoodIncluded");
					     //Selecting the SoftGoods Value
					     wait.until(ExpectedConditions.visibilityOfElementLocated(ElectronicsIncluded));
					     CommonFunctions.selectFromDropDownByVisibleTextUpdated(ElectronicsIncluded, data[39], "ElectronicsIncluded");
					     //Selecting the Electronics Included Value
					     CommonFunctions.waitForElementTobeClickable(CopyLinkNextButton);
					     CommonFunctions.clickButtonOrLink(CopyLinkNextButton, "Button", "Next");
					     //Clicking Next Button
					     wait.until(ExpectedConditions.titleIs(data[40]));
					     CommonFunctions.waitForPageLoaded();
					     //Select Marketing Channel
					 	SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(Product.distributionChannel));
						//CommonFunctions.enterTextInTextbox(Product.distributionChannel,strDistributionChannel);
						CommonFunctions.selectFromDropDownByIndex(Product.distributionChannel, 3);
					     wait.until(ExpectedConditions.visibilityOfElementLocated(ViewProductButton));
					     CommonFunctions.clickButtonOrLink(ViewProductButton, "Button", "view Product");
					     CommonFunctions.waitForPageLoaded();
					     wait.until(ExpectedConditions.visibilityOfElementLocated(RetailItemIdentification));
					     CopyLinkRetailItem=driver.findElement(RetailItemIdentification).getText();
					     log.info("Created Copy/Link Product RetailItem Value is "+CopyLinkRetailItem);
				           
					}
						catch(Exception e){
							fail=true;
							log.error("Exception in CopyLinkProductRetailItem"+e);
							throw e;
						}
					 return CopyLinkRetailItem;
					}
					public static String addSpecification(String[] data) throws Exception{
						try{

							//Click back to product
							//CommonProjectFunctions.searchProduct("E4948");
							//Click on Specification
							CommonProjectFunctions.clickSpecificationTab(data[6]);
							
							//Select source
							//CommonFunctions.selectFromDropDownByVisibleText(CommonProjectFunctions.selectSource, data[23]);
							
							//select Source
							CommonFunctions.waitForElementTobeClickable(SourceDropDown);
							CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
							Thread.sleep(1000);
							CommonFunctions.waitForPageLoaded();
							
							//Add Specification
							Select dropDownSpec = new Select(SeleniumDriver.driver.findElement(Specifications_old.specificationdropdown));
							List<WebElement> elementCount = dropDownSpec.getOptions();
							int count =elementCount.size();
							if(count>=2)
							{
								//Select first Specification
								CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
								strSpec=new Select(driver.findElement(Specifications_old.specificationdropdown)).getFirstSelectedOption().getText();
							}
							else
							{
								//Get the name of specification created
								strSpec= CommonProjectFunctions.Create_Specifications(data[45],data[46]);
								//Select specification first
								CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
								//Get the name of the specification created
								strSpec=new Select(driver.findElement(Specifications_old.specificationdropdown)).getFirstSelectedOption().getText();
							}
							log.info("Specification is: "+strSpec);
						}catch(Exception e){
							fail=true;
							log.error("Exception in fillCreatebom()", e);
							return "";
						}
						return strSpec;
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
					//Navigate to Details Page
					public static boolean NavigateToDetails(String [] data) throws Exception{
						try{
					wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailstab));
					//Wait for details tab
				    CommonFunctions.waitForPageLoaded();
				    //Click on Details tab
				    CommonFunctions.clickButtonOrLink(productDetailstab, "Product Details tab");
				    //CommonFunctions.clickButtonOrLink(productDetailstab, "Product Details tab");
				    CommonFunctions.waitForPageLoaded();
				    //select Source
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Select Season
					wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
					//Wait for Season dropdown 
				    CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
					// Add Specification
				    CommonProjectFunctions.clickSpecificationTab(data[6]);
				    CommonProjectFunctions.Create_Specifications(data[45],data[46]);
					//addSpecification(data);
				    //CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
				    //select Source
					CommonFunctions.waitForElementTobeClickable(Specifications_old.specificationdropdown);
					CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Create multiple colorway
					CreateMultiple_Colorway();
					CommonFunctions.waitForPageLoaded();
					wait.until(ExpectedConditions.visibilityOfElementLocated(csProductNumber));
						}
						catch(Exception e){
							fail=true;
							log.error("Exception in CopyLinkProductRetailItem"+e);
							throw e;
						}
						return true;
					}
					
					public static boolean NavigateToDetails_RetWithAst(String [] data) throws Exception{
						try{
					wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailstab));
					//Wait for details tab
				    CommonFunctions.waitForPageLoaded();
				    //Click on Details tab
				    CommonFunctions.clickButtonOrLink(productDetailstab, "Product Details tab");
				    //CommonFunctions.clickButtonOrLink(productDetailstab, "Product Details tab");
				    CommonFunctions.waitForPageLoaded();
				    //select Source
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Select Season
					wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
					//Wait for Season dropdown 
				    CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				    CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
					// Add Specification
				    CommonProjectFunctions.clickSpecificationTab(data[6]);
				    CommonProjectFunctions.Create_Specifications(data[45],data[46]);
					//addSpecification(data);
				    //CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
				    //select Source
					CommonFunctions.waitForElementTobeClickable(Specifications_old.specificationdropdown);
					CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Create multiple colorway
					//CreateMultiple_Colorway();
					CommonFunctions.waitForPageLoaded();
					wait.until(ExpectedConditions.visibilityOfElementLocated(csProductNumber));
						}
						catch(Exception e){
							fail=true;
							log.error("Exception in CopyLinkProductRetailItem"+e);
							throw e;
						}
						return true;
					}
					//R2.5-PD23 - Bundle Pack R2.5 Test
					public static boolean TC18_PD23_BundlePack(String [] data) throws Exception{
						try{
							/***Prerequisites: 
							 * 1. Assortment is created
							 * 2. Two Bundle Packs are created
							 * 3. Retail Item is created with AST reference(E6424)
							 * 4. Retail Item is created without AST reference(E6406)
							 * 5. Solid is created(E6407)
							 * 6. Assortment number 2 is created(E6408)
							 * 7. Trade Marketing Pallet is created(E6410)
							 */
							
			//******Create Product : Assortment********
							
		      CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
			  data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
			  data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
			  NavigateToDetails(data);
		      prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
			  log.info("******The  ASSORTMENT PRODUCT NUMBER created is********"+prodNum1);
							
			//******Create Product : RetailItem with Assortment Reference********  
		    //******Copy/Link Product: Retail Item with AST reference******
			  CopyLinkProductRetailItem(data);
			  NavigateToDetails_RetWithAst(data);
			  prodNum2 = SeleniumDriver.driver.findElement(csProductNumber).getText();
			  log.info("*******The RETAIL PRODUCT NUMBER WITH ASSORTMENT REFERENCE********"+prodNum2);
								
					
			  
			  //*******Create Product : Bundle Pack 1*********
			  CommonProjectFunctions.CreateProdFromLineSheet(data[34],data[6],data[7],data[8],data[9],data[10],data[11],
			  data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
			  data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
			  wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailstab));
			  NavigateToDetails(data);
			  prodNum3 = SeleniumDriver.driver.findElement(csProductNumber).getText();
			  log.info("*******The BUNDLE PACK PRODUCT NUMBER 1 is********"+prodNum3);
									
			 //********Create Product : Bundle Pack 2*********
			  CommonProjectFunctions.CreateProdFromLineSheet(data[34],data[6],data[7],data[8],data[9],data[10],data[11],
			  data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
			  data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
			  NavigateToDetails(data);
			  prodNum4 = SeleniumDriver.driver.findElement(csProductNumber).getText();
			  log.info("*******The BUNDLE PACK PRODUCT NUMBER 2 is********"+prodNum4);
							
										
			  //********Create Product : Trade Marketing Pallet*********
				CommonProjectFunctions.CreateProdFromLineSheet(data[41],data[6],data[7],data[8],data[9],data[10],data[11],
				data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
				data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
				NavigateToDetails(data);
				prodNum5 = SeleniumDriver.driver.findElement(csProductNumber).getText();
				log.info("*******The TRADE MARKETTING PALLET PRODUCT NUMBER is ********"+prodNum5);
										
										
				//********Create Product : Soild Product*********
				   CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
				   data[12],data[43],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
				   data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
										
				   NavigateToDetails(data);
				   prodNum6 = SeleniumDriver.driver.findElement(csProductNumber).getText();
				   log.info("*******The SOLID PRODUCT NUMBER is ********"+prodNum6);
										
										
				//********Create Product : Retail Item*********
					CommonProjectFunctions.CreateProdFromLineSheet(data[42],data[6],data[7],data[8],data[9],data[10],data[11],
					data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
					data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
					NavigateToDetails(data);
					prodNum7 = SeleniumDriver.driver.findElement(csProductNumber).getText();
					log.info("*******The RETAIL ITEM PRODUCT NUMBER is********"+prodNum7);
					CommonProjectFunctions.logOut();
					driver.quit();
					openBrowser();
					launchApp(data[47],data[48]);				
										
				    //Navigate to Assortment Product
					CI296.navigateToProduct(data);
					CommonFunctions.waitForPageLoaded();
					//Search Product
					CommonProjectFunctions.searchProduct(prodNum1);
					CommonFunctions.waitForPageLoaded();
					//Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					CommonFunctions.waitForPageLoaded()	;
					//Click on Details tab
					wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailstab));
					CommonFunctions.clickButtonOrLink(productDetailstab, "Product Details tab");
					//CommonFunctions.clickButtonOrLink(productDetailstab, "Product Details tab");
					CommonFunctions.waitForPageLoaded();
					//Select Season
					wait.until(ExpectedConditions.visibilityOfElementLocated(SeasonDropDown));
					//Wait for Season dropdown 
					CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
					CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
					CommonFunctions.waitForPageLoaded();
					//select Source
					CommonFunctions.waitForElementTobeClickable(SourceDropDown);
					CommonFunctions.selectFromDropDownByIndex(SourceDropDown, 1);
					Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded();
					//Create multiple colorway
					//CreateMultiple_Colorway();
					//Select Colorway
					wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.colorway));
					//Wait for Colorway dropdown 
					CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
					CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
					CommonFunctions.waitForPageLoaded();
					//Click on Edit Menu of 'Detailed Wave HDM Table'
					wait.until(ExpectedConditions.visibilityOfElementLocated(strDetailedWaveHDMEdit));
					CommonFunctions.clickButtonOrLink(strDetailedWaveHDMEdit, "Detailed Wave HDM Table Edit button");
					wait.until(ExpectedConditions.titleIs(data[44]));
					CommonFunctions.waitForPageLoaded();
					//Switch to default frame
					driver.switchTo().defaultContent();
					//Switch to content frame
					driver.switchTo().frame("contentframe");
					//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainframe"));
					
					
					//Click on Colorway : Retail Item with Assortment Reference
					//wait.until(ExpectedConditions.visibilityOfElementLocated(colorwayTextBox));
					CommonFunctions.clickButtonOrLink(colorwayTextBox1, "table cell", "colorwayTextBox");
					CommonFunctions.waitForElementTobeClickable(colorwayLink);
					CommonFunctions.clickButtonOrLink(colorwayLink, "table cell","colorwayLink");
					CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					Set set = SeleniumDriver.driver.getWindowHandles();
					Iterator iter = set.iterator();
					String parent = (java.lang.String) iter.next();
					String child = (java.lang.String) iter.next();
					SeleniumDriver.driver.switchTo().window(child);
					//wait.until(ExpectedConditions.visibilityOfElementLocated(RetailItemLink));
					CommonFunctions.clickButtonOrLink(RetailItemLink, "Retail Item Link");
					wait.until(ExpectedConditions.visibilityOfElementLocated(RetailItemLink));
					CommonFunctions.enterTextInTextbox(strProductNumber, prodNum2);
					CommonFunctions.clickButtonOrLink(SearchProductButton, "Button", "Search");
					CommonFunctions.clickButtonOrLink(strSelectProduct, "Link", "Select Product");
					//CommonFunctions.waitForPageLoaded();
					SeleniumDriver.driver.switchTo().window(parent);
	                SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(strInsertRow, "Button", "Insert Row");
					log.info("**** Retail Item with Assortment Reference is added successfully to Detailed Wave HDM table");
					
					//Click on Colorway : Bundle Pack 1
					//wait.until(ExpectedConditions.visibilityOfElementLocated(colorwayTextBox));
					CommonFunctions.clickButtonOrLink(colorwayTextBox2, "table cell", "colorwayTextBox");
					CommonFunctions.waitForElementTobeClickable(colorwayLink);
					CommonFunctions.clickButtonOrLink(colorwayLink, "table cell","colorwayLink");
					CommonFunctions.waitForPageLoaded();
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					Set set1 = SeleniumDriver.driver.getWindowHandles();
					Iterator iter1 = set1.iterator();
					String parent1 = (java.lang.String) iter1.next();
					String child1 = (java.lang.String) iter1.next();
					SeleniumDriver.driver.switchTo().window(child1);
					//wait.until(ExpectedConditions.visibilityOfElementLocated(BundlePackLink));
					CommonFunctions.clickButtonOrLink(BundlePackLink, "Bundle Pack Link");
					wait.until(ExpectedConditions.visibilityOfElementLocated(BundlePackLink));
					CommonFunctions.enterTextInTextbox(strProductNumber, prodNum3);
					CommonFunctions.clickButtonOrLink(SearchProductButton, "Button", "Search");
					CommonFunctions.clickButtonOrLink(strSelectProductBP, "Link", "Select Product");
					//CommonFunctions.waitForPageLoaded();
					SeleniumDriver.driver.switchTo().window(parent1);
	                SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(strInsertRow, "Button", "Insert Row");
					log.info("****Bundle Pack 1 Reference is added successfully to Detailed Wave HDM table");
					
					
					//Click on Colorway : Bundle Pack 2
					//wait.until(ExpectedConditions.visibilityOfElementLocated(colorwayTextBox));
					CommonFunctions.clickButtonOrLink(colorwayTextBox3, "table cell", "colorwayTextBox");
					CommonFunctions.waitForElementTobeClickable(colorwayLink);
					
					CommonFunctions.clickButtonOrLink(colorwayLink, "table cell","colorwayLink");
					CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					Set set2 = SeleniumDriver.driver.getWindowHandles();
					Iterator iter2 = set2.iterator();
					String parent2 = (java.lang.String) iter2.next();
					String child2 = (java.lang.String) iter2.next();
					SeleniumDriver.driver.switchTo().window(child2);
					//wait.until(ExpectedConditions.visibilityOfElementLocated(BundlePackLink));
					CommonFunctions.clickButtonOrLink(BundlePackLink, "Bundle Pack Link");
					wait.until(ExpectedConditions.visibilityOfElementLocated(BundlePackLink));
					CommonFunctions.enterTextInTextbox(strProductNumber, prodNum4);
					CommonFunctions.clickButtonOrLink(SearchProductButton, "Button", "Search");
					CommonFunctions.clickButtonOrLink(strSelectProductBP, "Link", "Select Product");
					//CommonFunctions.waitForPageLoaded();
					SeleniumDriver.driver.switchTo().window(parent2);
	                SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(strInsertRow, "Button", "Insert Row");
					log.info("****Bundle Pack 2 is added successfully to Detailed Wave HDM table");
					
					
					//Click on Colorway : Trade Marketing Pallet
					//wait.until(ExpectedConditions.visibilityOfElementLocated(colorwayTextBox));
					CommonFunctions.clickButtonOrLink(colorwayTextBox4, "table cell", "colorwayTextBox");
					CommonFunctions.waitForElementTobeClickable(colorwayLink);
					CommonFunctions.clickButtonOrLink(colorwayLink, "table cell","colorwayLink");
					CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					Set set3 = SeleniumDriver.driver.getWindowHandles();
					Iterator iter3 = set3.iterator();
					String parent3 = (java.lang.String) iter3.next();
					String child3 = (java.lang.String) iter3.next();
					SeleniumDriver.driver.switchTo().window(child3);
					//wait.until(ExpectedConditions.visibilityOfElementLocated(TradeMarketPalletLink));
					CommonFunctions.clickButtonOrLink(TradeMarketPalletLink, "Trade Marketing Pallet Link");
					wait.until(ExpectedConditions.visibilityOfElementLocated(TradeMarketPalletLink));
					CommonFunctions.enterTextInTextbox(strProductNumber, prodNum5);
					CommonFunctions.clickButtonOrLink(SearchProductButton, "Button", "Search");
					CommonFunctions.clickButtonOrLink(strSelectProductTMP, "Link", "Select Product");
					//CommonFunctions.waitForPageLoaded();
					SeleniumDriver.driver.switchTo().window(parent3);
	                SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");
					CommonFunctions.clickButtonOrLink(strInsertRow, "Button", "Insert Row");
					log.info("****Trade Marketing Pallet is added successfully to Detailed Wave HDM table");
					
					//Click on Colorway :Solid Product
					//wait.until(ExpectedConditions.visibilityOfElementLocated(colorwayTextBox));
					CommonFunctions.clickButtonOrLink(colorwayTextBox5, "table cell", "colorwayTextBox");
					CommonFunctions.waitForElementTobeClickable(colorwayLink);
					CommonFunctions.clickButtonOrLink(colorwayLink, "table cell","colorwayLink");
					CommonFunctions.waitForPageLoaded();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
					Set set4 = SeleniumDriver.driver.getWindowHandles();
					Iterator iter4 = set4.iterator();
					String parent4 = (java.lang.String) iter4.next();
					String child4 = (java.lang.String) iter4.next();
					SeleniumDriver.driver.switchTo().window(child4);
					//wait.until(ExpectedConditions.visibilityOfElementLocated(AOrSProductLink));
					CommonFunctions.clickButtonOrLink(AOrSProductLink, "Solid Product Link");
					wait.until(ExpectedConditions.visibilityOfElementLocated(AOrSProductLink));
					CommonFunctions.enterTextInTextbox(strProductNumber, prodNum6);
					CommonFunctions.clickButtonOrLink(SearchProductButton, "Button", "Search");
					CommonFunctions.clickButtonOrLink(strSelectProduct, "Link", "Select Product");
					//CommonFunctions.waitForPageLoaded();
					SeleniumDriver.driver.switchTo().window(parent4);
	                SeleniumDriver.driver.switchTo().defaultContent();
					SeleniumDriver.driver.switchTo().frame("contentframe");
					log.info("****Solid Product is added successfully to Detailed Wave HDM table");
					///CommonFunctions.clickButtonOrLink(strInsertRow, "Button", "Insert Row");
					
					//Click on Save
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
					CommonFunctions.waitForElementTobeClickable(GlobalLinePlan.btnDone);
					Thread.sleep(2000);
					//Click on Done
					CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnDone,"btn", "Done");
					
										
						}
						catch(Exception e){
									fail=true;
									log.error("Exception in TC18_PD23_BundlePack "+e);
									throw e;
									}
								return true;
					}
					//Navigate to Details Tab
					public static boolean NavigateToDetailsTab(String[] data) throws Exception{
						try{
							 wait.until(ExpectedConditions.visibilityOfElementLocated(Details));
							 CommonFunctions.waitForPageLoaded();
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
					public static boolean navigateToProductforPC50andPC54(String[] data) throws Exception
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
							//Switch frame
							driver.switchTo().defaultContent();
							driver.switchTo().frame("contentframe");
							CommonFunctions.waitForPageLoaded();
							wait.until(ExpectedConditions.titleIs(data[81]));
						
				          }catch(Exception e){
							fail=true;
							log.error("Exception in navigateToProductforPC50andPC54()"+e);
							throw e;
						}
						return true;
					}
					//Select Source and Season
					public static boolean ClickSeasonAndSource(String [] data) throws Exception{
						try{
							 wait.until(ExpectedConditions.visibilityOfElementLocated(Product.detailPageSeasonDD));
							 CommonFunctions.waitForPageLoaded();
				             CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.detailPageSeasonDD, data[6],"Season year");
				             //CommonFunctions.handleAlertPopUp1();
				             CommonFunctions.waitForPageLoaded();
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
					
					//Navigate to Costing Tab
					public static boolean NavigateToCostingTab(String [] data) throws Exception{
						try{
							wait.until(ExpectedConditions.visibilityOfElementLocated(ClickAgainProduct));
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(ClickAgainProduct, "HyperLink", "Product HyperLink");
							//Clicking on Product HyperLink
							CommonFunctions.waitForPageLoaded();
							wait.until(ExpectedConditions.titleIs(data[40]));
							wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingHyperLink));
							CommonFunctions.clickButtonOrLink(SourcingHyperLink, "SourcingHyperLink","Sourcing");
							//Clicking on Sourcing Tab
						
							wait.until(ExpectedConditions.visibilityOfElementLocated(CostingHyperLink));
							//CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(CostingHyperLink, "HyperLink", "Costing");
							//Clicking on Costing Link
						    wait.until(ExpectedConditions.visibilityOfElementLocated(SortOption));
						    CommonFunctions.waitForPageLoaded();
							
						}
						catch(Exception e){
							fail=true;
							log.error("Exception in NavigateToCostingTab"+e);
							throw e;
						}
						return true;
					}
					
					//Navigate to Costing Tab
					public static boolean NavigateToCostingTabpc48(String [] data) throws Exception{
						try{
							wait.until(ExpectedConditions.visibilityOfElementLocated(ClickAgainProduct));
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(ClickAgainProduct, "HyperLink", "Product HyperLink");
							//Clicking on Product HyperLink
							CommonFunctions.waitForPageLoaded();
							wait.until(ExpectedConditions.titleIs(data[72]));
							wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingHyperLink));
							CommonFunctions.clickButtonOrLink(SourcingHyperLink, "SourcingHyperLink","Sourcing");
							//Clicking on Sourcing Tab
						
							wait.until(ExpectedConditions.visibilityOfElementLocated(CostingHyperLink));
							//CommonFunctions.waitForPageLoaded();
							CommonFunctions.clickButtonOrLink(CostingHyperLink, "HyperLink", "Costing");
							//Clicking on Costing Link
						    wait.until(ExpectedConditions.visibilityOfElementLocated(SortOption));
						    CommonFunctions.waitForPageLoaded();
							
						}
						catch(Exception e){
							fail=true;
							log.error("Exception in NavigateToCostingTabpc48"+e);
							throw e;
						}
						return true;
					}
					//Close and Open Existing CS
					public static boolean CloseOpenedExistingCostSheet() throws Exception{
						try{
						   WebDriverWait wait1 = new WebDriverWait(driver,7);
						   wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CostSheetDropDown));
						   CommonFunctions.waitForPageLoaded();
							try{
							wait.until(ExpectedConditions.visibilityOfElementLocated(CloseCostSheet));
							CommonFunctions.clickButtonOrLink(CloseCostSheet, "Image", "Closing Opened Cost Sheet");
							//Closing the Opened Cost Sheet
							}
							catch(Exception e){
								fail=true;
								log.error("Exception in CloseOpenedExistingCostSheet"+e);
								throw e;
							}
						}
						
					catch(Exception e){
							log.info("Cost Sheet Have More than One in the List");
						}
						return true;
						
						}
					//Search the Cost Sheet
					public static boolean SearchTheCostSheet(String [] data) throws Exception{
						try{
							
							
						   CommonFunctions.waitForElementTobeClickable(WhatIfCostSheetCheckBox);
						   CommonFunctions.clickButtonOrLink(WhatIfCostSheetCheckBox, "CheckBox","WhatIfCostSheetCheckBox");
						   //Clicking on the CheckBox
						   CommonFunctions.waitForElementTobeClickable(SortOption);
							 CommonFunctions.clickButtonOrLink(SortOption, "Button", "SortOption");
							 //Clicking on Sort Button
							 
							 CommonFunctions.waitForPageLoaded();
							 //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SortTable));
							 CommonFunctions.waitForElementTobeClickable(FirstSortDropDown);
							 CommonFunctions.selectFromDropDownByVisibleTextUpdated(FirstSortDropDown, data[37], "First Sort By DropDown");
							 //Clicking the Value On SortBy Drop down
							 CommonFunctions.waitForElementTobeClickable(SecondSortDropDown);
							 CommonFunctions.selectFromDropDownByVisibleTextUpdated(SecondSortDropDown, data[38], "Second Sort By DropDown");
							 //Clicking order on sort
							 CommonFunctions.waitForElementTobeClickable(SortButton);
							 CommonFunctions.clickButtonOrLink(SortButton, "Button", "Sort Button");
							 //Clicking Sort Button
							 CommonFunctions.waitForElementTobeClickable(SortOption);
							 wait.until(ExpectedConditions.titleIs(data[39]));
							 CommonFunctions.waitForPageLoaded();
							 CloseOpenedExistingCostSheet();
							 JavascriptExecutor jse = (JavascriptExecutor)driver;
						    jse.executeScript("window.scrollBy(0,200)", "");
							wait.until(ExpectedConditions.visibilityOfElementLocated(WhatIfCostSheetSelectionCostingUser));
							CommonFunctions.waitForElementTobeClickable(WhatIfCostSheetSelectionCostingUser);
							    CommonFunctions.clickButtonOrLink(WhatIfCostSheetSelectionCostingUser, "HyperLink", "Cost Sheet HyperLink");
							    //Clicking on CostSheet HyperLink
							    CommonFunctions.waitForPageLoaded();
						   }
						catch(Exception e){
							fail=true;
							log.error("Exception in SearchTheCostSheet"+e);
							throw e;
						}
						return true;
					}
					
					//Get Vendor Cost Sheet Details
					public static boolean getInternalRetailCostSheetDetails(String [] data) throws Exception{
						try{
						//Getting Quote Currency Value
						CommonFunctions.waitForElementTobeClickable(QuoteCurrencyInCostSheet);
						String ActualQuoteCurrency = driver.findElement(QuoteCurrencyInCostSheet).getText();
						log.info("The Actual Value of Quote currency from CS  is "+ActualQuoteCurrency);
						
						//getting Total BOM Cost in COSTSHEET to Compare
						CommonFunctions.waitForElementTobeClickable(strTotalBOMCostValueCS);
						String ActualStrTotalBOMCostValueCS =driver.findElement(strTotalBOMCostValueCS).getText();
						log.info("The Value of Total BOM Cost In Cost Sheet is "+ActualStrTotalBOMCostValueCS);
			            //getting Plastic in COSTSHEET to Compare
						CommonFunctions.waitForElementTobeClickable(strPlasticValueCS);
						String ActualStrPlasticValueCS =driver.findElement(strPlasticValueCS).getText();
						log.info("The Value of Total Plastic Cost In Cost Sheet is "+ActualStrPlasticValueCS);
			            //getting Chemical in COSTSHEET to Compare
						CommonFunctions.waitForElementTobeClickable(strChemicalValueCS);
						String ActualStrChemicalValueCS =driver.findElement(strChemicalValueCS).getText();
						log.info("The Value of Total Chemical Cost In Cost Sheet is "+ActualStrChemicalValueCS);
			            //getting Purchased in COSTSHEET to Compare
						CommonFunctions.waitForElementTobeClickable(strPurchasedValueCS);
						String ActualStrPurchasedValueCS =driver.findElement(strPurchasedValueCS).getText();
						log.info("The Value of Total Purchased Cost In Cost Sheet is "+ActualStrPurchasedValueCS);
			            //getting Electronics in COSTSHEET to Compare
						CommonFunctions.waitForElementTobeClickable(strElectronicValueCS);
						String ActualStrElectronicValueCS =driver.findElement(strElectronicValueCS).getText();
						log.info("The Value of Total Electronics Cost In Cost Sheet is "+ActualStrElectronicValueCS);
			            //getting Soft Goods in COSTSHEET to Compare
						CommonFunctions.waitForElementTobeClickable(strSGValueCS);
						String ActualStrSGValueCS =driver.findElement(strSGValueCS).getText();
						log.info("The Value of Total Soft Goods Cost In Cost Sheet is "+ActualStrSGValueCS);
			            //getting Packaging in COSTSHEET to Compare
						CommonFunctions.waitForElementTobeClickable(strPackagingValueCS);
						String ActualStrPackagingValueCS =driver.findElement(strPackagingValueCS).getText();
						log.info("The Value of Total Packaging Cost In Cost Sheet is "+ActualStrPackagingValueCS);
			            //getting G/D Labor in COSTSHEET to Compare
						CommonFunctions.waitForElementTobeClickable(strGenDecLabValueCS);
						String ActualStrGenDecLabValueCS =driver.findElement(strGenDecLabValueCS).getText();
						log.info("The Value of Total G/D Labor Cost In Cost Sheet is "+ActualStrGenDecLabValueCS);
			            //getting Molding Labor Cost in COSTSHEET to Compare
						CommonFunctions.waitForElementTobeClickable(strMoldingLabValueCS);
						String ActualStrMoldingLabValueCS =driver.findElement(strMoldingLabValueCS).getText();
						log.info("The Value of Total Molding Labor Cost In Cost Sheet is "+ActualStrMoldingLabValueCS);
						if(data[3].contains("TC01_PC48_AssociateBOMToInternalRetailItemCS")){
							CommonFunctions.waitForElementTobeClickable(strProductMarkupCS);
							String ActualStrProductMarkupCS =driver.findElement(strProductMarkupCS).getText();
							log.info("The Value of Total  MarkUp Labor Cost In Cost Sheet is "+ActualStrProductMarkupCS);
						}
						else{
			            //getting totalMarkUpCost in COSTSHEET to Compare
						CommonFunctions.waitForElementTobeClickable(strProductOverHeadMarkupCS);
						String ActualStrProductMarkupCS =driver.findElement(strProductOverHeadMarkupCS).getText();
						log.info("The Value of Total OverHead MarkUp Labor Cost In Cost Sheet is "+ActualStrProductMarkupCS);
						}
					}
					catch(Exception e){
						fail=true;
						log.error("Exception in getInternalRetailCostSheetDetails"+e);
						throw e;
					}
					return true;
				}
					//Get Vendor Product Cost Sheet
					public static boolean getVendorCostSheetDetails(String [] data) throws Exception{
						try{
						CommonFunctions.waitForElementTobeClickable(QuoteCurrencyInCostSheet);
						String ActualQuoteCurrency = driver.findElement(QuoteCurrencyInCostSheet).getText();
						log.info("The Value of Quote currency is "+ActualQuoteCurrency);
						//Getting Quote Currency Value
						CommonFunctions.waitForElementTobeClickable(TotalMasterCartonPackagingCostInCostSheet);
						String ActualTotalMasterCartonPackagingCostInCostSheet =driver.findElement(TotalMasterCartonPackagingCostInCostSheet).getText();
						log.info("The Value of Total Master Carton Packaging Cost In Cost Sheet is "+ActualTotalMasterCartonPackagingCostInCostSheet);
						//Getting Total Master Carton Packaging Cost
						CommonFunctions.waitForElementTobeClickable(TotalMasterCartonLaborCostInCostSheet);
						String ActualTotalMasterCartonLaborCostInCostSheet =driver.findElement(TotalMasterCartonLaborCostInCostSheet).getText();
						log.info("The Value of Total Master Carton Labor Cost In Cost Sheet is "+ActualTotalMasterCartonLaborCostInCostSheet);
					    //Assertion Verification of totalMasterCartonLaborCost
						CommonFunctions.waitForElementTobeClickable(TotalMiscellaneousCostInCostSheet);
						String ActualTotalMiscellaneousCostInCostSheet =driver.findElement(TotalMiscellaneousCostInCostSheet).getText();
						log.info("The Value of Total Miscellaneous Cost In Cost Sheet In Cost Sheet is "+ActualTotalMiscellaneousCostInCostSheet);
						//Assertion Verification of totalMiscellaneousCostInBOM
						CommonFunctions.waitForElementTobeClickable(TotalProductMarkUpInCostSheet);
						String ActualTotalProductMarkUpInCostSheet =driver.findElement(TotalProductMarkUpInCostSheet).getText();
						//Assertion Verification of totalMarkUpCost
						log.info("The Value of Total Product Mark up In Cost Sheet In Cost Sheet is "+ActualTotalProductMarkUpInCostSheet);
						
					}
					catch(Exception e){
						fail=true;
						log.error("Exception in getVendorCostSheetDetails"+e);
						throw e;
					}
					return true;
				}
					//Get Vendor  Retail Cost Sheet Details
					public static boolean getInternalProductCostSheetDetails(String [] data) throws Exception{
						try{
						CommonFunctions.waitForElementTobeClickable(QuoteCurrencyInCostSheet);
						String ActualQuoteCurrency = driver.findElement(QuoteCurrencyInCostSheet).getText();
						log.info("The Value of Quote currency is "+ActualQuoteCurrency);
						//Getting Quote Currency Value
						/*CommonFunctions.waitForElementTobeClickable(TotalMasterCartonPackagingCostInCostSheet);
						String ActualTotalMasterCartonPackagingCostInCostSheet =driver.findElement(TotalMasterCartonPackagingCostInCostSheet).getText();
						log.info("The Value of Total Master Carton Packaging Cost In Cost Sheet is "+ActualTotalMasterCartonPackagingCostInCostSheet);
						*/
						//Getting Total Plastic Cost
						CommonFunctions.waitForElementTobeClickable(strPlasticValue);
						String ActualPlasticValueInCostSheet =driver.findElement(strPlasticValue).getText();
						log.info("The Value of Total PLastic Cost In Cost Sheet is "+ActualPlasticValueInCostSheet);
					    //Assertion Verification of Purchased Cost
						CommonFunctions.waitForElementTobeClickable(strPurchasedValue);
						String ActualTotalPlasticCostInCostSheet =driver.findElement(strPurchasedValue).getText();
						log.info("The Value of Total PLastic Cost In Cost Sheet In Cost Sheet is "+ActualTotalPlasticCostInCostSheet);
						//Assertion Verification of Soft Goods
						CommonFunctions.waitForElementTobeClickable(strSGValue);
						String ActualstrSGValueInCostSheet =driver.findElement(strSGValue).getText();
						//Assertion Verification of totalMarkUpCost
						log.info("The Value of Total Soft Goods Cost In Cost Sheet In Cost Sheet is "+ActualstrSGValueInCostSheet);
						
						//Assertion Verification of Chemical
						CommonFunctions.waitForElementTobeClickable(strChemicalValue);
						String ActualstrChemicalValueInCostSheet =driver.findElement(strChemicalValue).getText();
						//Assertion Verification of totalChemicalCost
						log.info("The Value of Total Chemical Cost In Cost Sheet In Cost Sheet is "+ActualstrChemicalValueInCostSheet);
						
						//Assertion Verification of Gen Dec Labor
						CommonFunctions.waitForElementTobeClickable(strGenDecLabValue);
						String ActualstrGenDecLabValueInCostSheet =driver.findElement(strGenDecLabValue).getText();
						//Assertion Verification of Gen Deco Labor
						log.info("The Value of Total Gen Deco Labor Cost In Cost Sheet In Cost Sheet is "+ActualstrGenDecLabValueInCostSheet);
						
						//Assertion Verification of Molding Labor
						CommonFunctions.waitForElementTobeClickable(strMoldingLabValue);
						String ActualstrMoldingLabValueInCostSheet =driver.findElement(strMoldingLabValue).getText();
						//Assertion Verification of Molding Labor
						log.info("The Value of Total Molding Labor Cost In Cost Sheet In Cost Sheet is "+ActualstrMoldingLabValueInCostSheet);
						
						//Assertion Verification of Electronics
						CommonFunctions.waitForElementTobeClickable(strElectronicValue);
						String ActualstrElectronicValueInCostSheet =driver.findElement(strElectronicValue).getText();
						//Assertion Verification of totalMarkUpCost
						log.info("The Value of Total Electronics Cost In Cost Sheet In Cost Sheet is "+ActualstrElectronicValueInCostSheet);
						
						//Assertion Verification of Packaging
						CommonFunctions.waitForElementTobeClickable(strPackagingValue);
						String ActualstrPackagingValueInCostSheet =driver.findElement(strPackagingValue).getText();
						//Assertion Verification of Total Packaging Cost
						log.info("The Value of Total Packaging Cost In Cost Sheet In Cost Sheet is "+ActualstrPackagingValueInCostSheet);
						
						
						//Assertion Verification of MarkUp
						CommonFunctions.waitForElementTobeClickable(moldingCellMarkupPC56);
						String ActualmoldingCellMarkupPC56InCostSheet =driver.findElement(moldingCellMarkupPC56).getText();
						//Assertion Verification of Total Packaging Cost
						log.info("The Value of Total MarkUp Cost In Cost Sheet In Cost Sheet is "+ActualmoldingCellMarkupPC56InCostSheet);
						
						
						
					}
					catch(Exception e){
						fail=true;
						log.error("Exception in getInternalProductCostSheetDetails"+e);
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
					//Navigate to Materials Tab
					public static boolean navigateToMaterialTabThroughSideBar(String [] data) throws Exception{
						try{
							driver.switchTo().defaultContent();
							driver.switchTo().frame("sidebarframe");
						    CommonFunctions.waitForElementTobeClickable(MaterialTabThroughSideBar);
						    CommonFunctions.clickButtonOrLink(MaterialTabThroughSideBar, "Hyper-Link", "MaterialsTab");
						    driver.switchTo().defaultContent();
							driver.switchTo().frame("contentframe");
							CommonFunctions.waitForPageLoaded();
							return true;
						}
						catch(Exception e){
							fail=true;
							log.error("Exception in navigateToMaterialTab()", e);
							throw e;
						}
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
					//Update BOM Value
					public static boolean UpdateBOMValue(String [] data) throws Exception{
						try{
				            CommonFunctions.waitForElementTobeClickable(BOMUpdateButton);
				            CommonFunctions.clickButtonOrLink(BOMUpdateButton, "Button", "Update Button");
				            //Clicking on Update Button
				            CommonFunctions.waitForPageLoaded();
							driver.switchTo().defaultContent();
							driver.switchTo().frame("contentframe");
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
							CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);

							//Enter unit Price -Master Carton Packaging
							wait.until(ExpectedConditions.visibilityOfElementLocated(mCPackagingCellUnitPrice_IE));
							CommonFunctions.clickButtonOrLink(mCPackagingCellUnitPrice_IE, "table cell", "unitPrice");
							CommonFunctions.waitForElementTobeClickable(mCPackagingInputUnitPrice_IE);
							CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputUnitPrice_IE, data[42],"Unit Price Value for Plastics");

							//Enter Usage per K-Master Carton Packaging
							CommonFunctions.waitForElementTobeClickable(mCPackagingCellUsagePerK);
							CommonFunctions.clickButtonOrLink(mCPackagingCellUsagePerK, "table cell", "unitPerK");
							CommonFunctions.waitForElementTobeClickable(mCPackagingInputUsagePerK);
							CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputUsagePerK, data[43],"Usage Per K Value for plastics");

							//Enter Markup - Master Carton Packaging
							wait.until(ExpectedConditions.visibilityOfElementLocated(mCPackagingCellMarkUp));
							CommonFunctions.clickButtonOrLink(mCPackagingCellMarkUp, "table cell", "Product Markup for Master Carton table");
							CommonFunctions.waitForElementTobeClickable(mCPackagingInputMarkUp);
							CommonFunctions.enterTextInTextboxUpdated(mCPackagingInputMarkUp, data[44],"Product Markup for Master Carton table");
							
							/*//Labour-Costing(full) view
							CommonFunctions.waitForElementTobeClickable(imgViewLabour);
							CommonFunctions.clickButtonOrLink(imgViewLabour, "image", "View for Labour");
							CommonFunctions.waitForElementTobeClickable(BOMTablesView);
							CommonFunctions.clickButtonOrLink(BOMTablesView, "link", " Labour Costing Full View");*/
							
							//Enter unit Price -Labor
							wait.until(ExpectedConditions.visibilityOfElementLocated(laborCellUnitPrice_IE));
							CommonFunctions.clickButtonOrLink(laborCellUnitPrice_IE, "table cell", "unitPrice");
							CommonFunctions.waitForElementTobeClickable(laborInputUnitPrice_IE);
							CommonFunctions.enterTextInTextboxUpdated(laborInputUnitPrice_IE, data[45],"Unit Price Value for Plastics");

							//Enter Usage per K-Labor
							CommonFunctions.waitForElementTobeClickable(laborCellUsagePerK);
							CommonFunctions.clickButtonOrLink(laborCellUsagePerK, "table cell", "unitPerK");
							CommonFunctions.waitForElementTobeClickable(laborInputUsagePerK);
							CommonFunctions.enterTextInTextboxUpdated(laborInputUsagePerK, data[46],"Usage Per K Value for plastics");

							//Enter Markup - Labor
							wait.until(ExpectedConditions.visibilityOfElementLocated(laborCellMarkUp));
							CommonFunctions.clickButtonOrLink(laborCellMarkUp, "table cell", "Product Markup for Labor table");
							CommonFunctions.waitForElementTobeClickable(laborInputMarkUp);
							CommonFunctions.enterTextInTextboxUpdated(laborInputMarkUp, data[47],"Product Markup for Labor table");
							
							
							//Enter unit Price -Miscellaneous
							wait.until(ExpectedConditions.visibilityOfElementLocated(miscellaneousCellUnitPrice_IE));
							CommonFunctions.clickButtonOrLink(miscellaneousCellUnitPrice_IE, "table cell", "unitPrice");
							CommonFunctions.waitForElementTobeClickable(miscellaneousInputUnitPrice_IE);
							CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputUnitPrice_IE, data[48],"Unit Price Value for Miscellaneous");
							 
							//Enter Usage Per K- Miscellaneous
							CommonFunctions.waitForElementTobeClickable(miscellaneousCellUsagePerK);
							CommonFunctions.clickButtonOrLink(miscellaneousCellUsagePerK, "table cell", "unitPerK");
							CommonFunctions.waitForElementTobeClickable(miscellaneousInputUsagePerK);
							CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputUsagePerK, data[49],"Usage Per K Value for Miscellaneous");

							//Enter Markup - Miscellaneous
							wait.until(ExpectedConditions.visibilityOfElementLocated(miscellaneousCellMarkUp));
							CommonFunctions.clickButtonOrLink(miscellaneousCellMarkUp, "table cell", "Product Markup for Miscellaneous table");
							CommonFunctions.waitForElementTobeClickable(miscellaneousInputMarkUp);
							CommonFunctions.enterTextInTextboxUpdated(miscellaneousInputMarkUp, data[50],"Product Markup for Miscellaneous table");
					        CommonFunctions.waitForElementTobeClickable(HeaderAttributes);
				            CommonFunctions.clickButtonOrLink(HeaderAttributes, "Button", "HeaderAttributes");
				            //Clicking Header Attributes
				            wait.until(ExpectedConditions.visibilityOfElementLocated(BOMCurrencyDropDown));
				            CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMCurrencyDropDown, data[51], "BOMCurrencyDropDown");
				            //Selecting the Currency Value
				            CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.btnSaveAndCheckIn);
							CommonFunctions.clickButtonOrLink(InternalBOMSoftG.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
							//Clicking on Save And Check In Button
							CommonFunctions.handleAlertPopUp1();
							driver.switchTo().defaultContent();
							driver.switchTo().frame("contentframe");
							//wait.until(ExpectedConditions.titleIs(data[47]));
							CommonFunctions.waitForPageLoaded();
						
						}
						catch(Exception e){
							fail=true;
							log.error("Exception in UpdateBOMValue "+e);
							throw e;
						}
						return true;
					}
					//Update BOM Value for Internal Retail Item
					public static boolean UpdateBOMValueInternalRetailItem(String [] data) throws Exception{
						try{
				            CommonFunctions.waitForElementTobeClickable(BOMUpdateButton);
				            CommonFunctions.clickButtonOrLink(BOMUpdateButton, "Button", "Update Button");
				            //Clicking on Update Button
				            CommonFunctions.waitForPageLoaded();
							driver.switchTo().defaultContent();
							driver.switchTo().frame("contentframe");
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
							CommonFunctions.waitForElementTobeClickable(MasterCartonEditButton);

							//Plastics-Coasting(full) view
							   CommonFunctions.waitForElementTobeClickable(imgViewPlastics);
							   CommonFunctions.clickButtonOrLink(imgViewPlastics, "image", "View for Plastics");
							   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
							   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View");
						   //Enter unit Price-Plastics
						   wait.until(ExpectedConditions.visibilityOfElementLocated(cellUnitPriceIE));
						   CommonFunctions.clickButtonOrLink(cellUnitPriceIE, "table cell", "unitPrice");
						   CommonFunctions.waitForElementTobeClickable(inputUnitPriceIE);
						   CommonFunctions.enterTextInTextboxUpdated(inputUnitPriceIE, data[60],"Unit Price Value for Plastics");
						   //Enter usage per K-Plastics
						   CommonFunctions.waitForElementTobeClickable(CI253.cellUsagePerK);
						   CommonFunctions.clickButtonOrLink(CI253.cellUsagePerK, "table cell", "unitPerK");
						   CommonFunctions.waitForElementTobeClickable(CI253.inputUsagePerK);
						   CommonFunctions.enterTextInTextboxUpdated(CI253.inputUsagePerK, data[60],"Usage Per K Value for plastics");
						   //Enter Product Markup-Plastics
						   CommonFunctions.waitForElementTobeClickable(plasticCellMarkup);
						   CommonFunctions.clickButtonOrLink(plasticCellMarkup, "table cell", "Markup");
						   CommonFunctions.waitForElementTobeClickable(plasticInputMarkup);
						   CommonFunctions.enterTextInTextboxUpdated(plasticInputMarkup, data[60],"Markup Value for plastics");
						
						   
						   
							 //Chemical-Coasting(full) view
							   CommonFunctions.waitForElementTobeClickable(imgViewChemical);
							   CommonFunctions.clickButtonOrLink(imgViewChemical, "image", "View for Plastics");
							   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
							   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
						   //Enter unit Price-Chemicals
						   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUnitPriceIE));
						   CommonFunctions.clickButtonOrLink(chemicalCellUnitPriceIE, "table cell", "unitPrice");
						   CommonFunctions.waitForElementTobeClickable(chemicalInputUnitPriceIE);
						   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUnitPriceIE, data[60],"Unit Price Value for Chemicals");
						   CommonFunctions.waitForPageLoaded();
						   //Enter usage per K-Chemicals
						   wait.until(ExpectedConditions.visibilityOfElementLocated(chemicalCellUsagePerK));
						   CommonFunctions.clickButtonOrLink(chemicalCellUsagePerK, "table cell", "unitPerK");
						   CommonFunctions.waitForElementTobeClickable(chemicalInputUsagePerK);
						   CommonFunctions.enterTextInTextboxUpdated(chemicalInputUsagePerK, data[60],"Usage Per K Value for Chemicals");
						   //Enter Product Markup-Chemicals
						   CommonFunctions.waitForElementTobeClickable(chemicalCellMarkup);
						   CommonFunctions.clickButtonOrLink(chemicalCellMarkup, "table cell", "Markup");
						   CommonFunctions.waitForElementTobeClickable(chemicalInputMarkup);
						   CommonFunctions.enterTextInTextboxUpdated(chemicalInputMarkup, data[60],"Markup Value for Chemicals");

						   
						   
							 //Purchased-Coasting(full) view
							   CommonFunctions.waitForElementTobeClickable(imgViewPurchased);
							   CommonFunctions.clickButtonOrLink(imgViewPurchased, "image", "View for Purchased");
							   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
							   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
						   //Enter unit Price-Purchased Parts
						   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUnitPriceIE));
						   CommonFunctions.clickButtonOrLink(purchasedCellUnitPriceIE, "table cell", "unitPrice");
						   CommonFunctions.waitForElementTobeClickable(purchasedInputUnitPriceIE);
						   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUnitPriceIE, data[60],"Unit Price Value for Purchased Parts");
						   //Enter usage per K-Purchased Parts
						   wait.until(ExpectedConditions.visibilityOfElementLocated(purchasedCellUsagePerK));
						   CommonFunctions.clickButtonOrLink(purchasedCellUsagePerK, "table cell", "unitPerK");
						   CommonFunctions.waitForElementTobeClickable(purchasedInputUsagePerK);
						   CommonFunctions.enterTextInTextboxUpdated(purchasedInputUsagePerK, data[60],"Usage Per K Value for Purchased Parts");
						   //Enter Product Markup-Purchased Parts
						   CommonFunctions.waitForElementTobeClickable(purchasedCellMarkup);
						   CommonFunctions.clickButtonOrLink(purchasedCellMarkup, "table cell", "Markup");
						   CommonFunctions.waitForElementTobeClickable(purchasedInputMarkup);
						   CommonFunctions.enterTextInTextboxUpdated(purchasedInputMarkup, data[60],"Markup Value for Purchased");

						   
						   
							 //Electronics-Coasting(full) view
							   CommonFunctions.waitForElementTobeClickable(imgViewElectronics);
							   CommonFunctions.clickButtonOrLink(imgViewElectronics, "image", "View for Plastics");
							   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
							   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
						   //Enter unit Price-Electronics
						   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUnitPriceIE));
						   CommonFunctions.clickButtonOrLink(electCellUnitPriceIE, "table cell", "unitPrice");
						   CommonFunctions.waitForElementTobeClickable(electInputUnitPriceIE);
						   CommonFunctions.enterTextInTextboxUpdated(electInputUnitPriceIE, data[60],"Unit Price Value for Electronics");
						   //Enter usage per K-Electronics
						   wait.until(ExpectedConditions.visibilityOfElementLocated(electCellUsagePerK));
						   CommonFunctions.clickButtonOrLink(electCellUsagePerK, "table cell", "unitPerK");
						   CommonFunctions.waitForElementTobeClickable(electInputUsagePerK);
						   CommonFunctions.enterTextInTextboxUpdated(electInputUsagePerK, data[60],"Usage Per K Value for Electronics");
						   //Enter Product Markup-Electronics
						   CommonFunctions.waitForElementTobeClickable(electCellMarkup);
						   CommonFunctions.clickButtonOrLink(electCellMarkup, "table cell", "Markup");
						   CommonFunctions.waitForElementTobeClickable(electInputMarkup);
						   CommonFunctions.enterTextInTextboxUpdated(electInputMarkup, data[60],"Markup Value for Electronics");

						   
						   
						  //Soft Goods-Coasting(full) view
							   CommonFunctions.waitForElementTobeClickable(imgViewSG);
							   CommonFunctions.clickButtonOrLink(imgViewSG, "image", "View for Plastics");
							   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
							   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
						   //Enter unit Soft Goods
						   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUnitPriceIE));
						   CommonFunctions.clickButtonOrLink(sGCellUnitPriceIE, "table cell", "unitPrice");
						   CommonFunctions.waitForElementTobeClickable(sGInputUnitPriceIE);
						   CommonFunctions.enterTextInTextboxUpdated(sGInputUnitPriceIE, data[60],"Unit Price Value for Soft Goods");
						   //Enter usage per K-Soft Goods
						   wait.until(ExpectedConditions.visibilityOfElementLocated(sGCellUsagePerK));
						   CommonFunctions.clickButtonOrLink(sGCellUsagePerK, "table cell", "unitPerK");
						   CommonFunctions.waitForElementTobeClickable(sGInputUsagePerK);
						   CommonFunctions.enterTextInTextboxUpdated(sGInputUsagePerK, data[60],"Usage Per K Value for Soft Goods");
						   //Enter Product Markup-Soft Goods
						   CommonFunctions.waitForElementTobeClickable(sGCellMarkup);
						   CommonFunctions.clickButtonOrLink(sGCellMarkup, "table cell", "Markup");
						   CommonFunctions.waitForElementTobeClickable(sGInputMarkup);
						   CommonFunctions.enterTextInTextboxUpdated(sGInputMarkup, data[60],"Markup Value for Soft Goods");
			               
						   
						   
						 //Pacakaging-Coasting(full) view
							   CommonFunctions.waitForElementTobeClickable(imgViewPackaging);
							   CommonFunctions.clickButtonOrLink(imgViewPackaging, "image", "View for Plastics");
							   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
							   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
						   //Enter unit Pacakaging
						   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUnitPriceIE));
						   CommonFunctions.clickButtonOrLink(packagingCellUnitPriceIE, "table cell", "unitPrice");
						   CommonFunctions.waitForElementTobeClickable(packagingInputUnitPriceIE);
						   CommonFunctions.enterTextInTextboxUpdated(packagingInputUnitPriceIE, data[60],"Unit Price Value for Pacakaging");
						   //Enter usage per Packaging
						   wait.until(ExpectedConditions.visibilityOfElementLocated(packagingCellUsagePerK));
						   CommonFunctions.clickButtonOrLink(packagingCellUsagePerK, "table cell", "unitPerK");
						   CommonFunctions.waitForElementTobeClickable(packagingInputUsagePerK);
						   CommonFunctions.enterTextInTextboxUpdated(packagingInputUsagePerK, data[60],"Usage Per K Value for Pacakaging");
						   //Enter Product Markup-Soft Goods
						   CommonFunctions.waitForElementTobeClickable(packagingCellMarkup);
						   CommonFunctions.clickButtonOrLink(packagingCellMarkup, "table cell", "Markup");
						   CommonFunctions.waitForElementTobeClickable(packagingInputMarkup);
						   CommonFunctions.enterTextInTextboxUpdated(packagingInputMarkup, data[60],"Markup Value for Pacakaging");
			               
						   
						   
							 //General Decor Labour-Coasting(full) view
							   CommonFunctions.waitForElementTobeClickable(imgViewGenDecLabor);
							   CommonFunctions.clickButtonOrLink(imgViewGenDecLabor, "image", "View for Plastics");
							   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
							   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
						   //Enter unit General Decor Labour
						   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUnitPriceIE));
						   CommonFunctions.clickButtonOrLink(generalLabourCellUnitPriceIE, "table cell", "unitPrice");
						   CommonFunctions.waitForElementTobeClickable(generalLabourInputUnitPriceIE);
						   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUnitPriceIE, data[60],"Unit Price Value for General/Deco Labor");
						   //Enter usage per General Decor Labour
						   wait.until(ExpectedConditions.visibilityOfElementLocated(generalLabourCellUsagePerK));
						   CommonFunctions.clickButtonOrLink(generalLabourCellUsagePerK, "table cell", "unitPerK");
						   CommonFunctions.waitForElementTobeClickable(generalLabourInputUsagePerK);
						   CommonFunctions.enterTextInTextboxUpdated(generalLabourInputUsagePerK, data[60],"Usage Per K Value for General/Deco Labor");
						   //Enter Product Markup-General Decor Labour
						   CommonFunctions.waitForElementTobeClickable(generalCellMarkup);
						   CommonFunctions.clickButtonOrLink(generalCellMarkup, "table cell", "Markup");
						   CommonFunctions.waitForElementTobeClickable(generalInputMarkup);
						   CommonFunctions.enterTextInTextboxUpdated(generalInputMarkup, data[60],"Markup Value for General/Deco Labor");

						   
						   
						  //Molding Labour-Coasting(full) view
							   CommonFunctions.waitForElementTobeClickable(imgViewMoldingLabor);
							   CommonFunctions.clickButtonOrLink(imgViewMoldingLabor, "image", "View for Plastics");
							   CommonFunctions.waitForElementTobeClickable(BOMTablesView);
							   CommonFunctions.clickButtonOrLink(BOMTablesView, "link", "Plastics Costing Full View"); 
						   //Enter unit Molding Labour
						   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUnitPriceIE));
						   CommonFunctions.clickButtonOrLink(moldingCellUnitPriceIE, "table cell", "unitPrice");
						   CommonFunctions.waitForElementTobeClickable(moldingInputUnitPriceIE);
						   CommonFunctions.enterTextInTextboxUpdated(moldingInputUnitPriceIE, data[60],"Unit Price Value for Molding Labor");
						   //Enter usage per Molding Labour
						   wait.until(ExpectedConditions.visibilityOfElementLocated(moldingCellUsagePerK));
						   CommonFunctions.clickButtonOrLink(moldingCellUsagePerK, "table cell", "unitPerK");
						   CommonFunctions.waitForElementTobeClickable(moldingInputUsagePerK);
						   CommonFunctions.enterTextInTextboxUpdated(moldingInputUsagePerK, data[60],"Usage Per K Value for Molding Labor");
						   //Enter Product Markup-Molding Labour
						   CommonFunctions.waitForElementTobeClickable(moldingCellMarkup);
						   CommonFunctions.clickButtonOrLink(moldingCellMarkup, "table cell", "Markup");
						   CommonFunctions.waitForElementTobeClickable(moldingInputMarkup);
						   CommonFunctions.enterTextInTextboxUpdated(moldingInputMarkup, data[60],"Markup Value for Molding");
					        CommonFunctions.waitForElementTobeClickable(HeaderAttributes);
				            CommonFunctions.clickButtonOrLink(HeaderAttributes, "Button", "HeaderAttributes");
				            //Clicking Header Attributes
				            wait.until(ExpectedConditions.visibilityOfElementLocated(BOMCurrencyDropDown));
				            CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMCurrencyDropDown, data[51], "BOMCurrencyDropDown");
				            //Selecting the Currency Value
				            CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.btnSaveAndCheckIn);
							CommonFunctions.clickButtonOrLink(InternalBOMSoftG.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
							//Clicking on Save And Check In Button
							CommonFunctions.handleAlertPopUp1();
							driver.switchTo().defaultContent();
							driver.switchTo().frame("contentframe");
							//wait.until(ExpectedConditions.titleIs(data[47]));
							CommonFunctions.waitForPageLoaded();
						
						}
						catch(Exception e){
							fail=true;
							log.error("Exception in UpdateBOMValueInternalRetailItem "+e);
							throw e;
						}
						return true;
					}
					//Update BOM values
					public static boolean getBOMValues(String [] data) throws Exception{
						try{
						    wait.until(ExpectedConditions.visibilityOfElementLocated(HeaderAttributesPlusIcon));
				            CommonFunctions.clickButtonOrLink(HeaderAttributesPlusIcon, "Image", "HeaderAttributesPlusIcon");
				            //Clicking on Header Attribute + Icon
				            wait.until(ExpectedConditions.visibilityOfElementLocated(TotalMasterCartonPackagingCostInBOM));
				            totalMasterCartonPackagingCostInBOM =driver.findElement(TotalMasterCartonPackagingCostInBOM).getText();
				            log.info("totalMasterCartonPackagingCostInBOM is "+totalMasterCartonPackagingCostInBOM);
				            //getting totalMasterCartonPackagingCostInBOM in BOM to Compare
				            wait.until(ExpectedConditions.visibilityOfElementLocated(TotalMiscellaneousCostInBOM));
				            totalMiscellaneousCostInBOM =driver.findElement(TotalMiscellaneousCostInBOM).getText();
				            log.info("totalMiscellaneousCostInBOM is "+totalMiscellaneousCostInBOM);
				            //getting totalMiscellaneousCostInBOM in BOM to Compare
				            wait.until(ExpectedConditions.visibilityOfElementLocated(TotalMasterCartonLaborCostInBOM));
				            totalMasterCartonLaborCost =driver.findElement(TotalMasterCartonLaborCostInBOM).getText();
				            log.info("totalMasterCartonLaborCost is "+totalMasterCartonLaborCost);
				            //getting totalMasterCartonLaborCost in BOM to Compare
				            wait.until(ExpectedConditions.visibilityOfElementLocated(TotalMarkUpCostInBOM));
				            totalMarkUpCost =driver.findElement(TotalMarkUpCostInBOM).getText();
				            log.info("TotalMarkUpCost is "+totalMarkUpCost);
				            //getting totalMarkUpCost in BOM to Compare
				            wait.until(ExpectedConditions.visibilityOfElementLocated(CurrencyInBOM));
				            QuoteCurrencyInBOM=driver.findElement(CurrencyInBOM).getText();
				            log.info("QuoteCurrencyInBOM is "+QuoteCurrencyInBOM);
				            }
						catch(Exception e){
							fail=true;
							log.error("Exception in SelectBOM"+e);
							throw e;
						}
						return true;
					}
					//Navigate to Cost Sheet through SideBar
					public static boolean NavigateToCostSheetThroughSideBar(String [] data) throws Exception{
						try{
							CommonFunctions.waitForPageLoaded(); 
							driver.switchTo().defaultContent();
							driver.switchTo().frame("sidebarframe");
							CommonFunctions.waitForElementTobeClickable(SideBarCosting);
							CommonFunctions.clickButtonOrLink(SideBarCosting, "HyperLink", "Costing HyperLink");
							driver.switchTo().defaultContent();
							driver.switchTo().frame("contentframe");
							CommonFunctions.waitForPageLoaded();
						}
						catch(Exception e){
							fail=true;
							log.error("Exception in NavigateToRFQThroughSideBar"+e);
							throw e;
						}
						return true;
						}
					
					public static boolean  TC06_AssertionVerficationOfVendorCostSheetValues(String [] data) throws Exception{
						try{
							//Note Quote Currency
				    		CommonFunctions.waitForElementTobeClickable(strQuoteCurrency);
				    		GettingText(strQuoteCurrency);
				    		CommonFunctions.AssertEqualsVerification(ActualValue, data[196], "Actual And Expected Cost Sheet Quote Currency Are Not Matched.Assertion Failed.Please check");
				    		log.info("*****Verified  Quote Currency*****");
				    		//Note Plastic Material
				    		CommonFunctions.waitForElementTobeClickable(strPlasticValue);
				    		GettingText(strPlasticValue);
				    		CommonFunctions.AssertEqualsVerification(ActualValue, data[261], "Actual And Expected Cost Sheet Plastic material Are Not Matched.Assertion Failed.Please check");
				    		log.info("*****Verified  Plastic Material*****");
				    		//Note Purchased Material
				    		CommonFunctions.waitForElementTobeClickable(strPurchasedValue);
				    		GettingText(strPurchasedValue);
				    		CommonFunctions.AssertEqualsVerification(ActualValue, data[261], "Actual And Expected Cost Sheet Purchased Material Not Matched.Assertion Failed.Please check");
				    		log.info("*****Verified  Purchased Material*****");
				    		//Note Soft Goods Material
				    		CommonFunctions.waitForElementTobeClickable(strSGValue);
				    		GettingText(strSGValue);
				    		CommonFunctions.AssertEqualsVerification(ActualValue, data[261], "Actual And Expected Cost Sheet Soft Goods Material Are Not Matched.Assertion Failed.Please check");
				    		log.info("*****Verified  Soft Goods*****");
				    		//Note Chemical Material
				    		CommonFunctions.waitForElementTobeClickable(strChemicalValue);
				    		GettingText(strChemicalValue);
				    		CommonFunctions.AssertEqualsVerification(ActualValue, data[261], "Actual And Expected Cost Sheet Chemical Material Are Not Matched.Assertion Failed.Please check");
				    		log.info("*****Verified  Chemical Material*****");
				    		//Note General / Deco Labor Cost
				    		CommonFunctions.waitForElementTobeClickable(strGenDecLabValue);
				    		GettingText(strGenDecLabValue);
				    		CommonFunctions.AssertEqualsVerification(ActualValue, data[261], "Actual And Expected Cost Sheet General / Deco Labor Cost Are Not Matched.Assertion Failed.Please check");
				    		log.info("*****Verified  General / Deco Labo*****");
				    		//Note Molding Labor Cost
				    		CommonFunctions.waitForElementTobeClickable(strMoldingLabValue);
				    		GettingText(strMoldingLabValue);
				    		CommonFunctions.AssertEqualsVerification(ActualValue, data[263], "Actual And Expected Cost Sheet Molding Labor Cost Are Not Matched.Assertion Failed.Please check");
				    		log.info("*****Verified  Molding Labor*****");
				    		//Note Electronic Material
				    		CommonFunctions.waitForElementTobeClickable(strElectronicValue);
				    		GettingText(strElectronicValue);
				    		CommonFunctions.AssertEqualsVerification(ActualValue, data[261], "Actual And Expected Cost Sheet Electronic Material Are Not Matched.Assertion Failed.Please check");
				    		log.info("*****Verified  Electronic Material Value*****");
				    		//Note Packaging Material
				    		CommonFunctions.waitForElementTobeClickable(strPackagingValue);
				    		GettingText(strPackagingValue);
				    		CommonFunctions.AssertEqualsVerification(ActualValue, data[261], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
				    		log.info("*****Verified  Packaging Material Value*****");
				    		//Note Markup
				    		CommonFunctions.waitForElementTobeClickable(moldingCellMarkupPC56);
				    		GettingText(moldingCellMarkupPC56);
				    		CommonFunctions.AssertEqualsVerification(ActualValue, data[262], "Actual And Expected Cost Sheet Packaging Material Are Not Matched.Assertion Failed.Please check");
				    		log.info("*****Verified  Markup Value*****");
							}
						catch(Exception e){
							fail=true;
							log.error("Exception in TC06_AssertionVerficationOfVendorCostSheetValues"+e);
							throw e;
						}
						return true;
					}
					
					//Verification
					public static boolean  AssertionVerficationOfVendorCostSheetValues(String [] data) throws Exception{
						try{
							CommonFunctions.waitForElementTobeClickable(QuoteCurrencyInCostSheet);
							String ActualQuoteCurrency = driver.findElement(QuoteCurrencyInCostSheet).getText();
							CommonFunctions.AssertEqualsVerification(ActualQuoteCurrency, QuoteCurrencyInBOM, "Actual and Expected Quote currencied are Not Matched.Assertion failed.Please Check");
							//Verifying the Quote Currency in BOM
							log.info("*** Validate Quote Currency -validated ***");
							CommonFunctions.waitForElementTobeClickable(TotalMasterCartonPackagingCostInCostSheet);
							String ActualTotalMasterCartonPackagingCostInCostSheet =driver.findElement(TotalMasterCartonPackagingCostInCostSheet).getText();
							CommonFunctions.AssertEqualsVerification(ActualTotalMasterCartonPackagingCostInCostSheet, totalMasterCartonPackagingCostInBOM, "Actual and Expected TotalMasterCartonPackagingCostInCostSheet are not Mactched.Assertion failed.Please Verify");
							log.info("*** Validate Master Carton Packaging Material - validated ***");
							//Assertion Verification of TotalMasterCartonPackagingCostInCostSheet
							CommonFunctions.waitForElementTobeClickable(TotalMasterCartonLaborCostInCostSheet);
							String ActualTotalMasterCartonLaborCostInCostSheet =driver.findElement(TotalMasterCartonLaborCostInCostSheet).getText();
							CommonFunctions.AssertEqualsVerification(ActualTotalMasterCartonLaborCostInCostSheet, totalMasterCartonLaborCost, "Actual and Expected TotalMasterCartonLaborCostInCostSheet are not Mactched.Assertion failed.Please Verify");
							log.info("*** Validate Master Carton Packaging Labor Cost - validated ***");
							//Assertion Verification of totalMasterCartonLaborCost
							CommonFunctions.waitForElementTobeClickable(TotalMiscellaneousCostInCostSheet);
							String ActualTotalMiscellaneousCostInCostSheet =driver.findElement(TotalMiscellaneousCostInCostSheet).getText();
							CommonFunctions.AssertEqualsVerification(ActualTotalMiscellaneousCostInCostSheet, totalMiscellaneousCostInBOM, "Actual and Expected TotalMiscellaneousCostInCostSheet are not Mactched.Assertion failed.Please Verify");
							log.info("*** Validate Miscellaneous Material - validated ***");
							//Assertion Verification of totalMiscellaneousCostInBOM
							CommonFunctions.waitForElementTobeClickable(TotalProductMarkUpInCostSheet);
							String ActualTotalProductMarkUpInCostSheet =driver.findElement(TotalProductMarkUpInCostSheet).getText();
							CommonFunctions.AssertEqualsVerification(ActualTotalProductMarkUpInCostSheet, totalMarkUpCost, "Actual and Expected TotalProductMarkUpInCostSheet are not Mactched.Assertion failed.Please Verify");
							log.info("*** Validate Product Markup - validated ***");
							//Assertion Verification of totalMarkUpCost
							}
						catch(Exception e){
							fail=true;
							log.error("Exception in AssertionVerficationOfVendorCostSheetValues"+e);
							throw e;
						}
						return true;
					}
					
					//Validate BOM Changes on Internal Product Cost Sheet :PC55
					
					public static boolean TC19_PC55_ValidateBOMChangesOnInternalProductCostSheet(String [] data) throws Exception{
						try{
							//******Create Product : Assortment********
							
						      CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
							  data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
							  data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
							
						    NavigateToDetailsTab(data);
							prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
							log.info("*******The Assortment PRODUCT NUMBER Created is********"+prodNum1);
							//Navigating to details Tab
							ClickSeasonAndSource(data);
							//Selecting Season and Source
							AddSpecification(data);
							CommonFunctions.waitForPageLoaded();
							//Adding a Specification
							CreateMultiple_Colorway();
							//Create Multiple Colorway
							CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
							CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
							CommonFunctions.waitForPageLoaded();
							//Select Colorway
							navigateToMaterialTabThroughSideBar(data);
							//Navigate To materials tab
							pc52createRetailIntBOM(data);
							//Create Internal Product BOM
							NavigateToCostingTab(data);
							//Navigating to Costing Tab
							CreateInternalRetailCS(data);
							//Create Product Internal Cost sheet
							getInternalProductCostSheetDetails(data);
							//get Vendor Cost Sheet Details
							navigateToMaterialTabThroughSideBar(data);
							//Navigate To materials tab
							UpdateBOMValue(data);
							//Updating the BOM value
							getBOMValues(data);
							//Getting a BOM Values
							NavigateToCostSheetThroughSideBar(data);
							//Navigate to Cost Sheet
							AssertionVerficationOfVendorCostSheetValues(data);
							//Assertion Verification Of Cost values
							
						}
						catch(Exception e){
							fail=true;
							log.error("Exception in TC19_PC55_ValidateBOMChangesOnInternalProductCostSheet "+e);
							throw e;
							}
						return true;
			}
			//Select Cost Sheet A
					public static boolean SelectCostSheetA(String [] data) throws Exception{
						try{
							for(int i=10;i<=1200;i++)
							{
							
							JavascriptExecutor jse = (JavascriptExecutor)driver;
						    jse.executeScript("window.scrollBy(0,100)", "");
							CostSheetNameA="//a[contains(text(),'Sourcing Configuration')]//following::a["+i+"]";
							CostSheetIdentification1_Internal=By.xpath(CostSheetNameA);
							wait.until(ExpectedConditions.visibilityOfElementLocated(CostSheetIdentification1_Internal));
							GettingText(CostSheetIdentification1_Internal);
							System.out.println(ActualValue);
							if(ActualValue.equals(strCostSheetNameA))
							{
							CommonFunctions.clickButtonOrLink(CostSheetIdentification1_Internal, "First Cost Sheet");
							CommonFunctions.waitForPageLoaded();
							break;
						   }
							i=i+13;
							}
						}
						catch(Exception e){
							fail=true;
							log.error("Exception in SelectCostSheetA"+e);
							throw e;
						}
						return true;
					}

					
					//Update the Cost Sheet:
					public static boolean UpdationOfCostSheet(String [] data) throws Exception{
						try{
							wait.until(ExpectedConditions.visibilityOfElementLocated(CostSheetUpdateButton));
							CommonFunctions.selectFromDropDownByVisibleTextUpdated(CostSheetUpdateButton, data[36],"Cost Sheet Update Drop down");
							wait.until(ExpectedConditions.titleIs(data[37]));
							CommonFunctions.waitForPageLoaded();
							CommonFunctions.waitForElementTobeClickable(ChoosedColorwayOption);
							GettingText(ChoosedColorwayOption);
							System.out.println(ActualValue);
							if(ActualValue.equals(data[160])){
							CommonFunctions.waitForElementTobeClickable(PrimaryCostSheetCheckBox);
						    CommonFunctions.clickButtonOrLink(PrimaryCostSheetCheckBox, "Check Box", "Primary Cost Sheet Check Box");
							//Clicking on Primary Cost Sheet Check Box
							CommonFunctions.waitForElementTobeClickable(UpdateCostSheetSaveButton);
							CommonFunctions.clickButtonOrLink(UpdateCostSheetSaveButton, "UpdateCostSheetSaveButton", "UpdateCostSheetSaveButton");
							//Clicking on Save Button
							
						}
							else{
								log.warn("The Specified Colorway needs to be selected for all the Validations.else Validations would be Failed");
							}
						}
						
						catch(Exception e){
							fail=true;
							log.error("Exception in SelectCostSheet2"+e);
							throw e;
						}
						return true;
					}
					
					//Get Page Title
					 
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
					//Verification: Cost Sheet A
					public static boolean AssertionVerificationOnSuccessfulVerification(String [] data) throws Exception{
						try{
							wait.until(ExpectedConditions.titleIs(data[144]));
							CommonFunctions.waitForPageLoaded();
							wait.until(ExpectedConditions.visibilityOfElementLocated(CostSheetUpdateButton));
							CommonFunctions.clickButtonOrLink(CostSheetUpdateButton, "Cost Sheet Update Drop Down");
							log.info(CostSheetNameA+" Successfully updated as Primary");
							CloseOpenedExistingCostSheet();
							getPageTitle();
							CommonFunctions.AssertEqualsVerification(pageTitle, data[144], "Actual And Expected Page name are not matched.Assertion failed.Please Verify");
							}
						catch(Exception e){
							fail=true;
							log.error("Exception in SuccessfulCostSheetUPdation");
							throw e;
						}
						return true;
					}
					
					//Assertion verification for Cost sheet A :PC38
					public static boolean AssertionVerificationOfErrorForCostSheetB(String [] data) throws Exception{
						try{
							wait.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageForPrimaryCostSheetUpdation));
							GettingText(ErrorMessageForPrimaryCostSheetUpdation);
							CommonFunctions.AssertEqualsVerification(ActualValue.trim(),"The Cost Sheet "+strCostSheetNameA+" is already set as Primary", "Actual and Expected Error Messages are Not Matched.Assertion Failed.Please Verify");
							}
						catch(Exception e){
							fail=true;
							log.error("Exception in AssertionVerificationOfErrorForCostSheetA");
							throw e;
						}
						return true;
					}
					
					
					//Assertion verification for Cost sheet J :PC38
					public static boolean AssertionVerificationOfErrorForCostSheetJ(String [] data) throws Exception{
						try{
							wait.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageForPrimaryCostSheetUpdation));
							GettingText(ErrorMessageForPrimaryCostSheetUpdation);
							CommonFunctions.AssertEqualsVerification(ActualValue.trim(),data[39], "Actual and Expected Error Messages are Not Matched.Assertion Failed.Please Verify");
							}
						catch(Exception e){
							fail=true;
							log.error("Exception in AssertionVerificationOfErrorForCostSheetA");
							throw e;
						}
						return true;
					}
					/***
					 * COST SHEET A
					 */
					public static boolean SelectCostSheetAAndUpdate(String [] data) throws Exception{
					try
					{
						
					//Select and click on Cost Sheet A:
					wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.tabCostsheet));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+strCostSheetNameA+"')]"), "Cost Sheet Selected");
					//Click on Update: Cost Sheet A
					wait.until(ExpectedConditions.visibilityOfElementLocated(CostSheetUpdateButton));
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(CostSheetUpdateButton, data[36],"Cost Sheet Update Drop down");
					wait.until(ExpectedConditions.titleIs(data[37]));
					CommonFunctions.waitForPageLoaded();
					
					/*wait.until(ExpectedConditions.visibilityOfElementLocated(csUpdateAction));
					CommonFunctions.selectFromDropDownByVisibleText(csUpdateAction,"Update Menu from Actions");*/
					
				    //Clicking on Primary Cost Sheet Check Box for the second time:Uncheck 
				    //CommonFunctions.waitForElementTobeClickable(PrimaryCostSheetCheckBox);
				    wait.until(ExpectedConditions.visibilityOfElementLocated(PrimaryCostSheetCheckBox));
				    CommonFunctions.clickButtonOrLink(PrimaryCostSheetCheckBox, "Check Box", "Primary Cost Sheet Check Box");
				    //Click on Save button
					CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
					CommonFunctions.waitForPageLoaded();
					
}
					catch(Exception e){
						fail=true;
						log.error("Exception in SelectCostSheetAAndUpdate");
						throw e;
					}
					return true;
				}
					//Select Cost Sheet B and Update
					public static boolean SelectCostSheetBAndUpdate(String [] data) throws Exception{
						try
						{
							
					wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.tabCostsheet));
					CommonFunctions.clickButtonOrLink(CostsheetTooling.tabCostsheet, "Tab", "Cost Sheet Tab");
					//CostSheetB:
					//Select and click on Cost Sheet B:
					wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.tabCostsheet));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+strCostSheetNameB+"')]"), "Cost Sheet Selected");
					//Click on Update: Cost Sheet B
					wait.until(ExpectedConditions.visibilityOfElementLocated(CostSheetUpdateButton));
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(CostSheetUpdateButton, data[36],"Cost Sheet Update Drop down");
					wait.until(ExpectedConditions.titleIs(data[37]));
					CommonFunctions.waitForPageLoaded();
					//Clicking on Primary Cost Sheet Check Box
				    //CommonFunctions.waitForElementTobeClickable(PrimaryCostSheetCheckBox);
					 wait.until(ExpectedConditions.visibilityOfElementLocated(PrimaryCostSheetCheckBox));
				    CommonFunctions.clickButtonOrLink(PrimaryCostSheetCheckBox, "Check Box", "Primary Cost Sheet Check Box");
				    //Click on Save button
					CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
					CommonFunctions.waitForPageLoaded();
			        AssertionVerificationOfErrorForCostSheetB(data);
				    //Click on Cancel button
					CommonFunctions.clickButtonOrLink(btnCancel,"btn","btnCancel");
					CommonFunctions.waitForPageLoaded();
					wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.tabCostsheet));
					CommonFunctions.clickButtonOrLink(CostsheetTooling.tabCostsheet, "Tab", "Cost Sheet Tab");
						}
					catch(Exception e){
						fail=true;
						log.error("Exception in SelectCostSheetBAndUpdate");
						throw e;
					}
					return true;
				}
					//Select Cost Sheet B and Update
					public static boolean SelectCostSheetBAndUpdateWithOutError(String [] data) throws Exception{
						try
						{
							
							wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.tabCostsheet));
							CommonFunctions.clickButtonOrLink(CostsheetTooling.tabCostsheet, "Tab", "Cost Sheet Tab");
							//CostSheetB:
							//Select and click on Cost Sheet B:
							//wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.tabCostsheet));
							CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+strCostSheetNameB+"')]"), "Cost Sheet Selected");
							//Click on Update: Cost Sheet B
							wait.until(ExpectedConditions.visibilityOfElementLocated(CostSheetUpdateButton));
							CommonFunctions.selectFromDropDownByVisibleTextUpdated(CostSheetUpdateButton, data[36],"Cost Sheet Update Drop down");
							wait.until(ExpectedConditions.titleIs(data[37]));
							CommonFunctions.waitForPageLoaded();
							//Clicking on Primary Cost Sheet Check Box
						    //CommonFunctions.waitForElementTobeClickable(PrimaryCostSheetCheckBox);
							 wait.until(ExpectedConditions.visibilityOfElementLocated(PrimaryCostSheetCheckBox));
						    CommonFunctions.clickButtonOrLink(PrimaryCostSheetCheckBox, "Check Box", "Primary Cost Sheet Check Box");
						    //Click on Save button
							CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
							CommonFunctions.waitForPageLoaded();
					        /*AssertionVerificationOfErrorForCostSheetB(data);
						    //Click on Cancel button
							CommonFunctions.clickButtonOrLink(btnCancel,"btn","btnCancel");
							CommonFunctions.waitForPageLoaded();*/
							wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.tabCostsheet));
							CommonFunctions.clickButtonOrLink(CostsheetTooling.tabCostsheet, "Tab", "Cost Sheet Tab");
						}
					catch(Exception e){
						fail=true;
						log.error("Exception in SelectCostSheetBAndUpdateWithOutError");
						throw e;
					}
					return true;
				}
					//Select Cost Sheet J and Update
					public static boolean SelectCostSheetJAndUpdate(String [] data) throws Exception{
						try
						{
							wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.tabCostsheet));
							CommonFunctions.clickButtonOrLink(CostsheetTooling.tabCostsheet, "Tab", "Cost Sheet Tab");
					        //CostSheetJ:
							wait.until(ExpectedConditions.visibilityOfElementLocated(WhatIfCostSheetCheckBox));
							CommonFunctions.clickButtonOrLink(WhatIfCostSheetCheckBox, "What If Cost Sheet Check Box");
							//Clicking What If cost SHeet Check Box
							CommonFunctions.waitForElementTobeClickable(RunButton);
							CommonFunctions.clickButtonOrLink(RunButton, "Run Button");
							//Clicking on Run button
							wait.until(ExpectedConditions.titleIs(data[38]));
							CommonFunctions.waitForPageLoaded();
							
							
							//Select and click on Cost Sheet J:
							wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.tabCostsheet));
							CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+strCostSheetNameJ+"')]"), "Cost Sheet Selected");
							//Click on Update: Cost Sheet J
							wait.until(ExpectedConditions.visibilityOfElementLocated(CostSheetUpdateButton));
							CommonFunctions.selectFromDropDownByVisibleTextUpdated(CostSheetUpdateButton, data[36],"Cost Sheet Update Drop down");
							wait.until(ExpectedConditions.titleIs(data[37]));
							CommonFunctions.waitForPageLoaded();
							//Clicking on Primary Cost Sheet Check Box
						    //CommonFunctions.waitForElementTobeClickable(PrimaryCostSheetCheckBox);
							 wait.until(ExpectedConditions.visibilityOfElementLocated(PrimaryCostSheetCheckBox));
						    CommonFunctions.clickButtonOrLink(PrimaryCostSheetCheckBox, "Check Box", "Primary Cost Sheet Check Box");
							CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
							CommonFunctions.waitForPageLoaded();
					        AssertionVerificationOfErrorForCostSheetJ(data);
						    /*//Click on Cancel button
							CommonFunctions.clickButtonOrLink(btnCancel,"btn","btnCancel");
							CommonFunctions.waitForPageLoaded();
							wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.tabCostsheet));
							CommonFunctions.clickButtonOrLink(CostsheetTooling.tabCostsheet, "Tab", "Cost Sheet Tab");*/
						}
					catch(Exception e){
						fail=true;
						log.error("Exception in SelectCostSheetJAndUpdate");
						throw e;
					}
					return true;
				}
					
			//PC38: Update Primary Flag for Vendor Retail Item Cost Sheet
					
					/***Prerequisites: 
					 * 1. Retail Product is created
					 * 2. 2 Cost Sheets are created
					 * 3. 1 What If Cost Sheet is created
					 *
					 */
					
					public static boolean TC20_PC38_UpdatePrimaryFlagForVendorRetailItemCS(String [] data) throws Exception{
						
						try{
							//********Create Product : Retail Item--->Prerequisite*********
							CommonProjectFunctions.CreateProdFromLineSheet(data[5],data[6],data[7],data[8],data[9],data[10],data[11],
							data[12],data[13],data[14],data[15],data[16],data[17],data[18],data[19],data[20],data[21],data[22],
							data[23],data[24],data[25],data[26],data[27],data[28],data[29],data[30],data[31],data[32],data[33]);
							NavigateToDetailsTab(data);
							prodNum1 = SeleniumDriver.driver.findElement(csProductNumber).getText();
							log.info("*******The Retail Item PRODUCT NUMBER Created is********"+prodNum1);
							//Prerequisites for any type of product to be visible for vendor user
					        VendorProductPrerequisites(data);
					        //Navigating to product page  
							navigateToProduct(data);
							//Search Product
				            CommonProjectFunctions.searchProduct(prodNum1);
							CommonFunctions.waitForPageLoaded();
							//Navigate To Costing tab
							navigateToCostingTabThroughSideBar(data);
							//Select Season
							CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
							CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
							CommonFunctions.waitForPageLoaded();
							
							//Select Specification
							CommonFunctions.waitForElementTobeClickable(Specifications_old.specificationdropdown);
							CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
							CommonFunctions.waitForPageLoaded();
							//Select Colorway
							CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
							CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
							CommonFunctions.waitForPageLoaded();
							
					        //Create Costsheet A : Vendor Retail Item
					        CreateVendorRetailCS(data);
					        wait.until(ExpectedConditions.visibilityOfElementLocated(CostSheetName));
					        strCostSheetNameA = SeleniumDriver.driver.findElement(CostSheetName).getText();
					        log.info("*****The Costsheet A created is*****"+strCostSheetNameA);
					        
					        //Create Costsheet B : Vendor Retail Item
					        CreateVendorRetailCS(data);
					        wait.until(ExpectedConditions.visibilityOfElementLocated(CostSheetName));
					        strCostSheetNameB = SeleniumDriver.driver.findElement(CostSheetName).getText();
					        log.info("*****The Costsheet B created is*****"+strCostSheetNameB);
					        
					
					        
					        //Create Costsheet J : What If Cost Sheet
					        CreateVendorWhatIfCS(data);
					        wait.until(ExpectedConditions.visibilityOfElementLocated(CostSheetName));
					        strCostSheetNameJ = SeleniumDriver.driver.findElement(CostSheetName).getText();
					        log.info("*****The WhatIf Costsheet J created is*****"+strCostSheetNameJ);
					        
							CommonProjectFunctions.logOut();
							driver.quit();
							openBrowser();
							launchApp(data[34],data[35]);
							
							//Navigating to product page
							navigateToProduct(data);
							//Selecting Retail Item Product
							CommonProjectFunctions.searchProduct(prodNum1);
							CommonFunctions.waitForPageLoaded();
							//Navigate to Costing Tab through Costing
							navigateToCostingTabThroughSideBar(data);
							//Select Season
							CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
							CommonFunctions.selectFromDropDownByIndex(SeasonDropDown, 1);
							CommonFunctions.waitForPageLoaded();
							//Select Specification
							CommonFunctions.waitForElementTobeClickable(Specifications_old.specificationdropdown);
							CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
							CommonFunctions.waitForPageLoaded();
							//Select Colorway
							CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
							CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
							CommonFunctions.waitForPageLoaded();
							
							//Cost Sheet A
							SelectCostSheetAAndUpdate(data);
							CloseOpenedExistingCostSheet();
							//Cost Sheet B
							SelectCostSheetBAndUpdate(data);
							CloseOpenedExistingCostSheet();
							//Cost Sheet A
							SelectCostSheetAAndUpdate(data);
							CloseOpenedExistingCostSheet();
							//Cost Sheet B
							SelectCostSheetBAndUpdateWithOutError(data);
							CloseOpenedExistingCostSheet();
							
					        //Cost Sheet J
							SelectCostSheetJAndUpdate(data);
							
							
							
							}
						catch(Exception e){
							fail=true;
							log.error("Exception in TC20_PC38_UpdatePrimaryFlagForVendorRetailItemCS "+e);
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
