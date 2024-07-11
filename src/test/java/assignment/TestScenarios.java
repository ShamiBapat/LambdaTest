package assignment;

import java.awt.Point;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestScenarios {
	RemoteWebDriver driver;
	
	@Parameters({"browsername","version","platform"})
	@BeforeTest
	public void setUp(String browsername,String version, String platform) {
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName(platform);
		
		browserOptions.setCapability("browserName", browsername);
		browserOptions.setBrowserVersion(version);
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "chaituvaidya");
		ltOptions.put("accessKey", "pLOgNjI4Fq6d5Cfu79plya2Fu8o7sXo1vgf4zG6gKP65YWKGKT");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("network", true);
		ltOptions.put("build", "Assignment");
		ltOptions.put("project", "Assignment");
		ltOptions.put("name", "Test");
		ltOptions.put("console", "true");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		try {
			driver = new RemoteWebDriver(new URL("https://chaituvaidya:pLOgNjI4Fq6d5Cfu79plya2Fu8o7sXo1vgf4zG6gKP65YWKGKT@hub.lambdatest.com/wd/hub"), browserOptions);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void ScenarioOne() {
		
		driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
		driver.manage().window().maximize();
		String actualURL = driver.getCurrentUrl();
		System.out.println("Title="+driver.getTitle());	
		String expected = "simple-form-demo";
		Assert.assertTrue(actualURL.contains(expected));
		
		String inputValue = "Welcome to LambdaTest";
		driver.findElement(By.xpath("//input[@id='user-message']")).sendKeys(inputValue);
		driver.findElement(By.xpath("//button[@id='showInput']")).click();
		String actualMsg = driver.findElement(By.id("message")).getText();
		Assert.assertTrue(actualMsg.equalsIgnoreCase(inputValue));
	}
	
	@Test
	public void ScenarioTwo() {
		driver.get("https://www.lambdatest.com/selenium-playground/drag-drop-range-sliders-demo");
		WebElement slider =driver.findElement(By.xpath("//input[@value='15']"));
		WebElement sliderValue = driver.findElement(By.id("rangeSuccess"));

        // Create an Actions object to perform the drag and drop action
        Actions actions = new Actions(driver);
        
        org.openqa.selenium.Point location = slider.getLocation();
        int x = location.getX();
        int y = location.getY();
        System.out.println(x);
        System.out.println(y);
        
        actions.dragAndDropBy(slider, 215, 0).build().perform();
        
        String value = sliderValue.getText();
        if ("95".equals(value)) {
            System.out.println("Slider successfully moved to 95.");
        } else {
            System.out.println("Failed to move the slider to 95. Current value: " + value);
		
	}

	}
	
	@Test
	public void ScenarioThree() {	
				driver.get("https://www.lambdatest.com/selenium-playground/input-form-demo");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
				WebElement Name = driver.findElement(By.id("name"));
				String error = Name.getAttribute("validationMessage");
				System.out.println(error);
				String tooltip = "Please fill out this field.";
				Assert.assertEquals(tooltip, error);
				
				driver.findElement(By.name("name")).sendKeys("Shami Bapat");
				driver.findElement(By.id("inputEmail4")).sendKeys("abc@gmail.com");
				driver.findElement(By.id("inputPassword4")).sendKeys("password");
				driver.findElement(By.xpath("//input[@placeholder='Company']")).sendKeys("Lambda Test");
				driver.findElement(By.id("websitename")).sendKeys("www.lambdatest.com");
				WebElement sel = driver.findElement(By.name("country"));
				Select select1 = new Select((WebElement) sel);
				select1.selectByVisibleText("United States");
				driver.findElement(By.name("city")).sendKeys("Troy");
				driver.findElement(By.id("inputAddress1")).sendKeys("1234 golden dr");
				driver.findElement(By.id("inputAddress2")).sendKeys("abcd ct ");
				driver.findElement(By.xpath("(//label[text()='State*']/following::input)[1]")).sendKeys("New Jersey");
				driver.findElement(By.xpath("//input[@placeholder='Zip code']")).sendKeys("543234");
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				String sucessMsg = "Thanks for contacting us, we will get back to you shortly.";
				String actualMsg = driver.findElement(By.xpath("//p[@class='success-msg hidden']")).getText();
				Assert.assertTrue(sucessMsg.equalsIgnoreCase(actualMsg));
		
	}
		
	
	
	@AfterTest
	public void TearDown() {
		driver.quit();
	}

}
