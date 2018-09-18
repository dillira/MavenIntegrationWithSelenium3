package com.hasbrop2m.security;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.hasbrop2m.core.SeleniumDriver;
import com.hasbrop2m.core.Utility;



public class TestsuiteBase extends SeleniumDriver{

	@BeforeSuite
	public void checkSuiteSkipTest() throws Exception{
		initialize();
		log.debug("Initiallized");
		//System.out.println(suiteXls);
		if(!Utility.isSuiteRunnable(suiteXls, "Security")){
			log.debug("Skipping Security option as runmode is set to no");
			throw new SkipException("Skipping Security option as runmode is set to no");
		}
		System.out.println("Inside security");
	}
}
