package finalProject.finalProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppTest {

	WebDriver driver = new ChromeDriver();
	String URL = "https://www.automationteststore.com/";
	String LoginOrRegisterPageURL = "https://automationteststore.com/index.php?rt=account/login";
	String MenPageURL = "https://automationteststore.com/index.php?rt=product/category&path=58";
	String MakeUpPageURL = "https://automationteststore.com/index.php?rt=product/category&path=36";
	String SkinCarePageURL = "https://automationteststore.com/index.php?rt=product/category&path=43";

	String[] firstnames = { "John", "Emma", "Liam", "Olivia", "Noah" };
	String[] lastnames = { "Smith", "Johnson", "Williams", "Brown", "Davis" };

	String logoutPage = "https://automationteststore.com/index.php?rt=account/logout";

	Random rand = new Random();

	String PublicUserName = "";

	@BeforeTest
	public void mySetup() {
		driver.get(URL);

		driver.manage().window().maximize();
	}

	@Test(priority = 1, enabled = true)

	public void SignUp() throws InterruptedException {

		// driver.get(LoginOrRegisterPageURL);
		// driver.findElement(By.linkText("Login or register")).click();;
		driver.findElement(By.partialLinkText("Login or")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@title='Continue']")).click();
		int randomIndex = rand.nextInt(5);

		WebElement Firstname = driver.findElement(By.id("AccountFrm_firstname"));

		String RandomFirstName = firstnames[randomIndex];
		Firstname.sendKeys(RandomFirstName);

		WebElement LastName = driver.findElement(By.id("AccountFrm_lastname"));
		String RandomLastName = lastnames[randomIndex];
		LastName.sendKeys(RandomLastName);

		WebElement Email = driver.findElement(By.id("AccountFrm_email"));
		int RandomIndexForTheEmail = rand.nextInt(54871);

		String username = RandomFirstName + RandomLastName + RandomIndexForTheEmail;

		PublicUserName = username;
		Email.sendKeys(username + "@gmail.com");

		WebElement Address = driver.findElement(By.id("AccountFrm_address_1"));

		Address.sendKeys("random address farah");

		WebElement City = driver.findElement(By.id("AccountFrm_city"));

		City.sendKeys("Amman");

		WebElement countrySelectTag = driver.findElement(By.id("AccountFrm_country_id"));

		Select mySelect = new Select(countrySelectTag);

		mySelect.selectByVisibleText("Jordan");
		// mySelect.selectByValue("88");

		Thread.sleep(1000);
		WebElement mySelectForRegion = driver.findElement(By.id("AccountFrm_zone_id"));

		Select mySelect2 = new Select(mySelectForRegion);

		mySelect2.selectByValue("1704");

		driver.findElement(By.id("AccountFrm_postcode")).sendKeys("199310");

		driver.findElement(By.id("AccountFrm_loginname")).sendKeys(username);

		driver.findElement(By.id("AccountFrm_password")).sendKeys("P@ssw0rd");

		driver.findElement(By.id("AccountFrm_confirm")).sendKeys("P@ssw0rd");

		driver.findElement(By.id("AccountFrm_agree")).click();

		driver.findElement(By.xpath("//button[@title='Continue']")).click();

		Thread.sleep(2000);

		driver.get(logoutPage);

	}

	@Test(priority = 2, enabled = true)
	public void invalidLogin() {
		driver.get(LoginOrRegisterPageURL);

		driver.findElement(By.id("loginFrm_loginname")).sendKeys("wronguser");
		driver.findElement(By.id("loginFrm_password")).sendKeys("wrongpass");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		WebElement error = driver.findElement(By.cssSelector(".alert.alert-error"));
		Assert.assertTrue(error.isDisplayed(), "Error message not displayed");
		Assert.assertTrue(error.getText().toLowerCase().contains("error"), "Error message text not found");
	}

	@Test(priority = 3, enabled = true)

	public void LoginTest() throws InterruptedException {
		driver.get(LoginOrRegisterPageURL);

		WebElement username = driver.findElement(By.id("loginFrm_loginname"));
		WebElement password = driver.findElement(By.id("loginFrm_password"));

		username.sendKeys(PublicUserName);
		password.sendKeys("P@ssw0rd");

		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@title='Login']")).click();

	}

	@Test(priority = 4, enabled = true)
	public void ValidProductSearch() {
		WebElement searchBar = driver.findElement(By.id("filter_keyword"));

		searchBar.sendKeys("Shampoo");

		searchBar.submit();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		java.util.List<WebElement> products = driver.findElements(By.cssSelector(".fixed_wrapper"));

		Assert.assertTrue(products.size() > 0, "No products found for the search keyword");
	}

	@Test(priority = 5, enabled = true)
	public void BrandNavigation() {
		
		WebElement homeButton = driver.findElement(By.cssSelector("a.menu_home"));
		homeButton.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement gucciLogo = driver.findElement(By.cssSelector("img[alt='Gucci']"));

		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				gucciLogo);

		gucciLogo.click();

		WebElement title = driver.findElement(By.cssSelector("div#maincontainer h1"));
		String pageTitle = title.getText().trim().toLowerCase();
		Assert.assertTrue(pageTitle.contains("gucci"), "Not on Gucci brand page");

		System.out.println("Successfully navigated to Gucci brand page");
	}

	@Test(priority = 6, enabled = true)
	public void AddRandomItem() throws InterruptedException {

	    WebElement skincareButton = driver.findElement(By.cssSelector("a[href*='product/category'][href*='path=43']"));
	    skincareButton.click();

	    Thread.sleep(2000);

	    WebElement eyesCategory = driver.findElement(By.cssSelector("a[href*='path=43_47']"));
	    eyesCategory.click();

	    Thread.sleep(2000);

	    List<WebElement> items = driver.findElements(By.cssSelector("a.productname"));

	    if (items.isEmpty()) {
	        System.out.println("No products found in Eyes section.");
	    } else {
	        int randomIndex = rand.nextInt(items.size());
	        WebElement selectedItem = items.get(randomIndex);
	        System.out.println("Selected product: " + selectedItem.getText());
	        selectedItem.click(); 

	        Thread.sleep(2000);
	        WebElement addToCartButton = driver.findElement(By.cssSelector("a.cart"));
	        addToCartButton.click();

	        System.out.println("Product added to cart successfully from Eyes category.");
	    }
	}


	@Test(priority = 7, enabled = true)
	public void changeQuantity() {

		List<WebElement> Items2 = driver.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12"));// اختيار منتج
																										// عشوائي من
																										// الموقع
		int RandomItem2 = rand.nextInt(Items2.size());

		Items2.get(RandomItem2).click();

		driver.findElement(By.xpath("//a[contains(@class,'cart')]")).click();

		int randomQuantity = rand.nextInt(9) + 2;

		WebElement quantityInput = driver.findElement(By.cssSelector(".form-control.short"));

		quantityInput.clear();
		quantityInput.sendKeys(String.valueOf(randomQuantity)); 

	}

	@Test(priority = 8, enabled = true)
	public void checkout() {
		driver.findElement(By.id("cart_checkout1")).click();
		driver.findElement(By.id("checkout_btn")).click();

	}

	@Test(priority = 9, enabled = true)
	public void InvalidSearch() throws InterruptedException {
		WebElement homeButton = driver.findElement(By.cssSelector("a.menu_home"));
		homeButton.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement searchBar = driver.findElement(By.id("filter_keyword"));
		searchBar.clear();
		searchBar.sendKeys("XYZ1234");
		searchBar.submit();

		Thread.sleep(2000);

		WebElement noResultsMessage = driver.findElement(By.cssSelector(".contentpanel"));
		String messageText = noResultsMessage.getText().toLowerCase();

		Assert.assertTrue(messageText.contains("there is no product"), "Expected message for no results not found");
	}

	@Test(priority = 10, enabled = true)
	public void verifySocialLink() throws InterruptedException {
		driver.get(URL);

		String originalWindow = driver.getWindowHandle();

		WebElement facebookLink = driver.findElement(By.cssSelector("a.facebook"));
		facebookLink.click();

		Thread.sleep(2000);

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("facebook.com"), "Facebook link did not open the correct URL");

		driver.close();
		driver.switchTo().window(originalWindow);
	}

}
