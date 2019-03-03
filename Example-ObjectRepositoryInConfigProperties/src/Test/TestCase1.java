package Test;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


public class TestCase1 {

	
	public static void main(String[] args) throws Exception {
		WebDriver driver;
		String strChromeDriverPath=System.getProperty("user.dir")+"\\src\\Test\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", strChromeDriverPath);
		driver = new ChromeDriver();
		driver.get("http://www.google.com");
		
		WebElement ob=ReadProperty.ReturnValue("search_Value",driver);
		ob.sendKeys("java");
		
		WebElement ob1=ReadProperty.ReturnValue("search_btn",driver);
		ob1.click();
		
		driver.quit();
		
	}

}
