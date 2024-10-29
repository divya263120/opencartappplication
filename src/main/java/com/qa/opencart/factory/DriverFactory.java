package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		
		switch (prop.getProperty("browser").toLowerCase().trim()) {

		case "chrome":
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver());
			break;
		case "firefox":
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
			break;
		case "edge":
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Browser not found " + prop);
			throw new BrowserException(AppError.BROWSER_NOT_FOUND);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));// loginPage
		return getDriver();
	}

	
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	public Properties initProp() {
		FileInputStream ip = null ;
		prop = new Properties();
		String envName = System.getProperty("env");
		System.out.println("environment variable is " +envName );
		
		if(envName==null) {
			envName = "qa";
		}
		try {
			switch (envName.trim().toLowerCase()) {

			case "qa":

				ip = new FileInputStream(AppConstants.CONFIG_QA_FILEPATH);
				break;
			case "dev":

				ip = new FileInputStream(AppConstants.CONFIG_DEV_FILEPATH);
				break;

			case "stage":

				ip = new FileInputStream(AppConstants.CONFIG_STAGE_FILEPATH);
				break;
			case "prod":

				ip = new FileInputStream(AppConstants.CONFIG_PROD_FILEPATH);
				break;

			default:
				System.out.println("wrong environment" +envName );
				throw new FrameException("plz pass the write environment name ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return prop;
	}
	
	
	/*
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {
		
		File srcfile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path  =	System.getProperty("user.dir")+"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcfile, destination);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return path;
	}

}
