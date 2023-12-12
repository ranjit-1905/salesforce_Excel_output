package AccountScenario;

import org.openqa.selenium.JavascriptExecutor;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Test.screenshot;

public class ShippingAddress {

	WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	By Checkbox = By.xpath("//input[@part='checkbox']");
	By ShipingAddress = By.xpath("//lightning-input[@class='slds-form-element_stacked slds-form-element']//div[1]");
	By Save = By.xpath("//button[text()='Save']");
	By Button = By.xpath("//*[@id=\"brandBand_1\"]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr[1]/td[6]/span/div/a/span/span[1]");
	By Edit =By.xpath("/html[1]/body[1]/div[12]/div[1]/ul[1]/li[1]/a[1]");    //("//li//a[@title='Edit']");
	By AccountName = By.xpath("(//div[@part='input-container'])[2]");
	By Label = By.xpath("(//label[@class='slds-form-element__label slds-no-flex'])");


	public ShippingAddress(WebDriver driver) {
		this.driver = driver;
	}

	public void GotoSrolldown() throws AWTException, Exception {
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
	}

	public void CheckboxClick() throws Exception {
		// TODO Auto-generated method stub
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement m=driver.findElement(Checkbox);
		js.executeScript("arguments[0].click();", m);
	}

	// Check Same as Shipping Address Field
	public void strShipingAddress() throws Exception {
		Thread.sleep(3000);
		List<WebElement> objLinks = driver.findElements(ShipingAddress);
		for (WebElement label : objLinks) {
			String strShipingAddress = label.getText();
			System.out.println("ShipingAddress :" + strShipingAddress);

		}
	}

	@SuppressWarnings("unused")
	public void ShipAddressEnabled() throws InterruptedException {
		Thread.sleep(5000);
		// TODO Auto-generated method stub
		boolean Enabled = driver.findElement(ShipingAddress).isEnabled();

		boolean Displayed = driver.findElement(ShipingAddress).isDisplayed();

		if (Enabled)

			System.out.println("Shipping Address Field Checkbox");
		else {
			System.out.println("Shipping Address Field Checkbox UnAvailable");
			screenshot.captureScreenshot(driver, "Shipping Address Field");

		}
	}
	// Account Click New Account
	public void Save() throws Exception {
		Thread.sleep(1000);
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Save));
		driver.findElement(Save).click();
	}	

	// Account Click New Account
	public void clickButton() throws Exception {
		Thread.sleep(1000);
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Button));
		driver.findElement(Button).click();
	}

	// Account Click Edit 
	public void clickEdit() throws Exception {
		Thread.sleep(3000);
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Edit));
		driver.findElement(Edit).click();
	}	

	// Account Name
	public void AccountnameSF() throws InterruptedException {
		Thread.sleep(2000);
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(AccountName));
		driver.findElement(AccountName).click();
	}
	// New Account Label List
	public void strLinkText() throws Exception {
		Thread.sleep(3000);
		List<WebElement> objLinks = driver.findElements(Label);
		for (WebElement label : objLinks) {
			String strLinkText = label.getText();
			System.out.println("New Account :" + strLinkText);
		}
	}

	public void ShipAddressfield()  throws Exception {

		//Gotodown
		//this.GotoSrolldown();
		//
		this.CheckboxClick();
		//
		this.strShipingAddress();
		//Field Available
		this.ShipAddressEnabled();
		//Field Save
		this.Save();
		//Click button
		this.clickButton();
		// Edit
		this.clickEdit();
		//Account Name
		this.AccountnameSF();




	}	
}





