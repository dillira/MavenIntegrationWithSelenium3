package com.hasbrop2m.sanity;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.hasbrop2m.core.Utility;
import com.hasbrop2m.core.SeleniumDriver;

public class TestsuiteBase extends SeleniumDriver{

	@BeforeSuite
	public void checkSuiteSkipTest() throws Exception{
		initialize();
		log.debug("Initiallized");
	//	System.out.println(suiteXls);
		if(!Utility.isSuiteRunnable(suiteXls,"sanity")){
			log.debug("Skipping option as runmode is set to no");
			throw new SkipException("Skipping option as runmode is set to no");
		}
		System.out.println("Doing Smoke Testing");
	}
}
