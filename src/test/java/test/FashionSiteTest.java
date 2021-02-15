package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FashionSiteTest {

	private static RemoteWebDriver driver;
	private WebElement targ;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());
		driver.manage().window().setSize(new Dimension(1366, 768));

	}

	@Test
	public void test() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php");
		targ = driver.findElement(By.xpath("//*[@id=\"search_query_top\"]"));
		targ.sendKeys("Dress");
		targ = driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button"));
		targ.click();

		assertEquals("7 results have been found.",
				driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[2]")).getText());

		Thread.sleep(10000);
	}

	@After
	public void tearDown() {
		driver.close();
	}

	public static ChromeOptions chromeCfg() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions cOptions = new ChromeOptions();

		// Settings
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);

		// Create ChromeOptions to disable Cookies pop-up
		cOptions.setExperimentalOption("prefs", prefs);

		return cOptions;

	}
}
