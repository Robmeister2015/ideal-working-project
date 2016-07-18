package com.robsmovies.RobsMovies.test.selenium;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\A00226084\\Documents\\Software\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/RobsMovies/index2.html");	
	}
	
	@Test
	public void testPageTitle() {
		assertEquals("Rob's Movie Emporium", driver.getTitle());
		assertTrue(driver.findElements(By.id("myCarousel")).size() > 0);
	}
	
	@Test
	public void testProductsTabActive() {
		WebElement activeTab = driver.findElement(By.className("active"));
		String attribute = activeTab.getAttribute("id");
		assertEquals("homeTab", attribute);
		WebElement productsTabButton = driver.findElement(By.id("productsLink"));
		productsTabButton.click();
		WebElement newActiveTab = driver.findElement(By.className("active"));
		String productTab = newActiveTab.getAttribute("id");
		assertEquals("productsTab", productTab);
		WebElement resetTab = driver.findElement(By.id("homeTab"));
		resetTab.click();
	}
	
	@Test
	public void testCoversTabActive() {
		WebElement activeTab = driver.findElement(By.className("active"));
		String attribute = activeTab.getAttribute("id");
		assertEquals("homeTab", attribute);
		WebElement coversTabButton = driver.findElement(By.id("coversTab"));
		coversTabButton.click();
		WebElement newActiveTab = driver.findElement(By.className("active"));
		String carouselTab = newActiveTab.getAttribute("id");
		assertEquals("carouselTab", carouselTab);
		WebElement resetTab = driver.findElement(By.id("homeTab"));
		resetTab.click();
	}
	
	@Test
	public void testModalDisplayed() throws InterruptedException {
		WebElement activeTab = driver.findElement(By.className("active"));
		String attribute = activeTab.getAttribute("id");
		assertEquals("homeTab", attribute);
		WebElement title2 = driver.findElement(By.id("moreInfoModal"));
		assertFalse(title2.isDisplayed());
		WebElement coversTabButton = driver.findElement(By.id("productsLink"));
		coversTabButton.click();
		WebElement modalButton = driver.findElement(By.id("moviesTable"));
		WebElement modalClick;
		List<WebElement> childs = modalButton.findElements(By.xpath(".//*"));
		for(WebElement e : childs){
			if(e.getAttribute("id").equals("16")){
				modalClick = e;
				modalClick.click();
			}
		}
			driver.switchTo().activeElement();
			Thread.sleep(3000);
			WebElement title = driver.findElement(By.id("moreinfotitle"));
			assertTrue(title.isDisplayed());

	}
	
	@Test
	public void testInformationInModalIsCorrect() throws InterruptedException {
		WebElement activeTab = driver.findElement(By.className("active"));
		String attribute = activeTab.getAttribute("id");
		assertEquals("homeTab", attribute);
		WebElement title2 = driver.findElement(By.id("moreInfoModal"));
		assertFalse(title2.isDisplayed());
		WebElement coversTabButton = driver.findElement(By.id("productsLink"));
		coversTabButton.click();
		WebElement modalButton = driver.findElement(By.id("moviesTable"));
		WebElement modalClick;
		List<WebElement> childs = modalButton.findElements(By.xpath(".//*"));
		for(WebElement e : childs){
			if(e.getAttribute("id").equals("16")){
				modalClick = e;
				modalClick.click();
			}
		}
			driver.switchTo().activeElement();
			Thread.sleep(3000);
			WebElement title = driver.findElement(By.id("moreinfotitle"));
			assertTrue(title.isDisplayed());

			String s = driver.findElement(By.id("moreinfotitle")).getText();
			assertEquals("TestMovie2", s);
	}
	
	@After
	public void tearDown(){
		driver.quit();
}
}
