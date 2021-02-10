package com.gumtree.utils;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gumtree.utils.PropertiesCache;


public class webGenericLibrary  {

	public static WebDriver driver;
	public enum locatorTypes {
		xpath, css, id, name, link, className, tagName, partialLink
	}
	
	// Open browser for web application
	public static void getBrowser()  {
		
		ChromeOptions options = new ChromeOptions();	
		String driverPath = System.getProperty("user.dir") + "\\browserdrivers\\chromedriver.exe";
		try {
			
			options.setExperimentalOption("w3c", false);
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-notifications");
			options.addArguments("--safebrowsing-disable-extension-blacklist");
			options.addArguments("--safebrowsing-disable-download-protection");
			options.addArguments("ignore-certificate-errors");
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);  
			options.setCapability(ChromeOptions.CAPABILITY, options);
			options.setCapability("applicationCacheEnabled", false);
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
		
		} catch (Throwable exp) {
			System.out.println("Exception thrown opening browser error. " + exp);
		}
	}
	
	public FileInputStream loadPropertiesPath(String fileName) throws FileNotFoundException {
		StringBuilder filenameElementProperties = new StringBuilder();
		filenameElementProperties.append("src").append(File.separator);
		filenameElementProperties.append("test").append(File.separator);
		filenameElementProperties.append("java").append(File.separator);
		filenameElementProperties.append("com").append(File.separator);
		filenameElementProperties.append("gumtree").append(File.separator);
		filenameElementProperties.append("locators").append(File.separator);
		filenameElementProperties.append(fileName);
		FileInputStream fileObject = new FileInputStream(
				System.getProperty("user.dir") + File.separator + filenameElementProperties.toString());
		return fileObject;
	 }
	
	public static void launchUrl(String url) {
		driver.get(url);
	}
	
	public void sendKeys(String element, String fTextToEnter, String pageData) throws InterruptedException {
		WebElement ele = webElementIdentifier(PropertiesCache.getProperty(pageData, element));
		Thread.sleep(1000);
		ele.clear();
		ele.sendKeys(fTextToEnter);
	}
	
	public void click(String objectLocator, String pageData) {
		webElementIdentifier(PropertiesCache.getProperty(pageData, objectLocator)).click();
	}
	
	public void verifyText(String actualResults, String expectedResult, String pageData) {
		actualResults = PropertiesCache.getProperty(pageData, actualResults);
		assertEquals(webElementIdentifier(actualResults).getText().trim(), expectedResult);
	}
	
	public WebElement webElementIdentifier(String objectLocator) {
		locatorTypes locator = locatorTypes.valueOf(objectLocator.substring(objectLocator.lastIndexOf("$") + 1));
		String elementLocator = objectLocator.substring(0, objectLocator.lastIndexOf("$"));
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		switch (locator) {
		case link:
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(elementLocator)));
			element = driver.findElement(By.linkText(elementLocator));
			//element=new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.linkText(elementLocator)));
			break;
		case xpath:
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementLocator)));
			element = driver.findElement(By.xpath(elementLocator));
			break;
		case css:
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementLocator)));
			element = driver.findElement(By.cssSelector(elementLocator));
			break;
		case id:
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementLocator)));
			element = driver.findElement(By.id(elementLocator));
			break;
		case name:
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(elementLocator)));
			element = driver.findElement(By.name(elementLocator));
			break;
		case className:
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(elementLocator)));
			element = driver.findElement(By.className(elementLocator));
			break;
		case tagName:
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(elementLocator)));
			element = driver.findElement(By.tagName(elementLocator));
			break;
		case partialLink:
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(elementLocator)));
			element = driver.findElement(By.partialLinkText(elementLocator));
			break;
		}
		/* hightlight element */
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid green'", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);

		return element;

	}
}