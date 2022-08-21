package test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amazon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		
		//Launch browser
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);

		//Search  product samsung
		WebElement searchbox = driver.findElement(By.id("twotabsearchtextbox"));
		searchbox.sendKeys("Samsung");

		WebElement submit = driver.findElement(By.id("nav-search-submit-button"));
		submit.click();

		//Store the search results and price list
		List<WebElement> SearchResult = driver
				.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		List<WebElement> price = driver
				.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));

		//Print the names and price in console
		System.out.println("The total products are " + SearchResult.size());
		for (int i = 0; i < SearchResult.size(); i++) {

			System.out.println(SearchResult.get(i).getText() + " " + price.get(i).getText());

		}
		//Get the parent window id
		String parentWin = driver.getWindowHandle();
		
		//Get the label of the first product
		String expectedValue = SearchResult.get(0).getText();
		System.out.println(expectedValue);

		//Click on the first result
		SearchResult.get(0).click();

		
		Set<String> AllWin = driver.getWindowHandles();

		//Switch to child window
		for (String win : AllWin) {
			System.out.println(win);
			if (!win.equals(parentWin)) {
				driver.switchTo().window(win);
			}
		}

		//Validation of the product name with that on the search result
		WebElement actualVal = driver.findElement(By.xpath("//span[@id='productTitle']"));
		String actual = actualVal.getText();
		if (actual.equals(expectedValue)) {
			System.out.println("Product title is matching");
		} else {
			System.out.println("Product title is not matching");
		}

		driver.quit();
	}
}
