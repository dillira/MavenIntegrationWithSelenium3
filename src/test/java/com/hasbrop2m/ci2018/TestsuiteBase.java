package com.hasbrop2m.ci2018;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.hasbrop2m.core.Utility;

import com.hasbrop2m.core.SeleniumDriver;

public class TestsuiteBase extends SeleniumDriver{

	@BeforeSuite
	public void checkSuiteSkipTest() throws Exception{
		initialize();
	//	log.debug("Initiallized");
		//System.out.println(suiteXls);
		if(!Utility.isSuiteRunnable(suiteXls, "ci2018")){
			log.debug("Skipping cascading option as runmode is set to no");
			throw new SkipException("Skipping ci2018 option as runmode is set to no");
		}
		System.out.println("Inside ci2018");
	}
}
