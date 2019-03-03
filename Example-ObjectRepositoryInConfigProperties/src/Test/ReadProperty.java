package Test;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class ReadProperty {

	
	public static WebElement ReturnValue(String obj,WebDriver driver) throws Exception{
		Properties prob= null;
		InputStream input= ReadProperty.class.getClassLoader().getResourceAsStream("configfile.properties");
		WebElement element= null;
		prob= new Properties();
		prob.load(input);
		String objdce= null;
		objdce=prob.getProperty(obj);
		
		String[] objpara=objdce.split(",");
		String strtidentitymethod=objpara[0];
		String strtidentitydec=objpara[1];
		
		switch(strtidentitymethod.toLowerCase()){
		case "xpath":
			element= driver.findElement(By.xpath(strtidentitydec));
		break;
		
		case "name":
			element= driver.findElement(By.name(strtidentitydec));
			break;
		case "id":
			element= driver.findElement(By.id(strtidentitydec));
			break;
		}
		
		return element;
	}
	
	
}	
