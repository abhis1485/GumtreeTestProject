package com.gumtree.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.gumtree.utils.PropertiesCache;

import com.gumtree.utils.webGenericLibrary;;

public class gumtreeConfigs {

	public static String userDir = System.getProperty("user.dir");
	public static final String PATH_SEPARATOR = File.separator;
	public static List<String> propertyList = new ArrayList<String>();

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "url", "gumtreeFile"})
	public void preClass(String url, String gumtreeMapFile) {
		
		propertyList.add(gumtreeMapFile);	
		
		new PropertiesCache(propertyList);
		webGenericLibrary.getBrowser();
		webGenericLibrary.launchUrl(url);
	}

	@AfterSuite(alwaysRun = true)
	public void postSuite() {
		webGenericLibrary.driver.quit();
	}

}

