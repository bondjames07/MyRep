package com.test.core.hybrid;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

public class GenericKeywords {
	
	public WebDriver driver;
	public Properties prop;
	
	public GenericKeywords() {
		prop = new Properties();
		try {
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test/resources//project.properties");
			prop.load(fs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openBrowser(String browserType){		
		if(browserType.equals("Firefox")) {
			System.setProperty("webdriver.firefox.driver", "D:\\11. Selenium\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browserType.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\11. Selenium\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}		
		else if (browserType.equals("IE")) {
			System.setProperty("webdriver.ie.driver", "D:\\11. Selenium\\drivers\\IEdriver.exe");
			driver = new InternetExplorerDriver();
		}		
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();	
	}
	
	public void navigate(String urlKey){
		System.out.println("Navigating to "+ prop.getProperty(urlKey));
		
		driver.get(prop.getProperty(urlKey));

		
	}
	
	public void click(String locatorKey){
		System.out.println("Clicking on "+ prop.getProperty(locatorKey));
		WebElement e = getElement(locatorKey);
		e.click();
	}
	
	public void input(String locatorKey, String data){
		System.out.println("Typing in "+ prop.getProperty(locatorKey));
		WebElement e = getElement(locatorKey);
		e.sendKeys(data);
	}
	
	public void verifyText(String locatorKey, String expectedText){
		WebElement e = getElement(locatorKey);
		String actualText = e.getText();
		
	}
	
	
	/************************Utility Functions*******************************************************************/
	
	public WebElement getElement(String locatorKey) {
		
		WebElement e = null;
		try {
			if(locatorKey.endsWith("_id")) {
				e = driver.findElement(By.id(prop.getProperty(locatorKey)));
			}
			else if(locatorKey.endsWith("_xpath")) {
				e = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
			}
			else if(locatorKey.endsWith("_name")) {
				e = driver.findElement(By.name(prop.getProperty(locatorKey)));
			}
		}catch(Exception ex) {
			Assert.fail("Failure in Element Extraction - "+locatorKey);
		}
		return e;
	}

}
