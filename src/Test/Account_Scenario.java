package Test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import SalesFlows.Utility;
import AccountScenario.AccountLayouts ;
import AccountScenario.AddressCheck;
import AccountScenario.ShippingAddress;
import AccountScenario.dependency;
import Login.Login;
import Login.Navigation;



public class Account_Scenario {
	//String driverPath = "D:\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver;
	Login objLogin;
	Navigation objNavigation;
	AccountLayouts objAccountLayouts;
	ShippingAddress objShippingAddress;
	AddressCheck objAddressCheck; 
	dependency objdependency;
	//Opportunity objOpportunity;

	Map<String, Object[]>  AccountResults;

	//To Calculate test class run time

	long Starttime;
	long Endtime;


	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.silentOutput","true");
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32 (4)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com/");	

	}
	@Test(priority = 0)
	public void Login_Salesforce_page() {
		// Create Login Page object
		objLogin = new Login(driver);
		// login to application
		objLogin.loginToSF("ranjithraghavan@htcinc.com","Welcome$321");		
	}
	@Test (priority = 1)
	public void Homepage() throws Exception {
		objNavigation = new Navigation(driver);
		objNavigation.gotoNavigation();
	}
	@Test (priority = 2)//,enabled=false)
	public void Account_Layouts () {
		try {
			objAccountLayouts = new AccountLayouts(driver);
			objAccountLayouts.gotoAccount();  
			// TODO Auto-generated catch block
			AccountResults.put("2",
					new Object[] { "2", "2.1",  "Account-Check Field availables in Account Layouts","Pass" });
			screenshot.captureScreenshot(driver, "6.Account-Check Field availables in Account Layouts");

		} catch (Exception e) {

			AccountResults.put("2",
					new Object[] { "2", "2.1",  "Account-Check Field availables in Account Layouts","Fail" });
			screenshot.captureScreenshot(driver, "6.Account-Check Field availables in Account Layouts");
			Assert.assertTrue(false);

		}
	}
	@Test (priority = 3)//,enabled=false)
	public void ShippingAddress_checkbox() {
		objShippingAddress = new ShippingAddress(driver);
		try {
			objShippingAddress.ShipAddressfield();

			AccountResults.put("3",
					new Object[] { "2", "2.2",  "Check Same as Shipping Address Field Checkbox","Pass" });
			screenshot.captureScreenshot(driver, "7.Check Same as Shipping Address Field Checkbox");

		} catch (Exception e) {

			AccountResults.put("3",
					new Object[] { "2", "2.2",  "Check Same as Shipping Address Field Checkbox","Fail" });
			screenshot.captureScreenshot(driver, "7.Check Same as Shipping Address Field Checkbox");
			Assert.assertTrue(false);

		}
	}
	@Test (priority = 4)//,enabled=false)
	public void Address_compare() {
		objAddressCheck = new AddressCheck (driver);
		try {
			objAddressCheck .AddressFields();

			AccountResults.put("4",
					new Object[] { "2", "2.3",  "Check Billing and Shipping Address are same","Pass" });
			screenshot.captureScreenshot(driver, "8.Check Billing and Shipping Address are same");

		} catch (Exception e) {

			AccountResults.put("4",
					new Object[] {"2", "2.3",  "Check Billing and Shipping Address are same","Fail" });
			screenshot.captureScreenshot(driver, "8.Check Billing and Shipping Address are same");
			Assert.assertTrue(false);

		}
	}

	@Test (priority = 5)//,enabled=false)
	public void Dependency_Filed( ) throws Exception {
		objdependency=new dependency(driver);
		try {
			objdependency.dependency_picklist("Customer - Direct","Medium");
		
			AccountResults.put("5",
					new Object[] { "2", "2.4",  "Check dependency picklist value in Account","Pass" });
			screenshot.captureScreenshot(driver, "9.Check dependency picklist value in Account");

		} catch (Exception e) {

			AccountResults.put("5",
					new Object[] {"2", "2.4",  "Check dependency picklist value in Account","Fail" });
			screenshot.captureScreenshot(driver, "9.Check dependency picklist value in Account");
			Assert.assertTrue(false);

		}
	
	}
	
	//To prepare report in Excel

	@BeforeSuite(alwaysRun = true)
	public void suiteSetUp() throws IOException {		
		
		String excelFilePath = "D://Output.xlsx";

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(inputStream);

		@SuppressWarnings("unused")
		Sheet sheet = workbook.getSheetAt(0);

		AccountResults = new LinkedHashMap<String, Object[]>();
		// add test result excel file column header
		// write the header in the first row
		AccountResults.put("1", new Object[] {"Test Cases - Account Object Scenario"});
	}

	//To save the results in Excel

	@AfterClass
	public void suiteTearDown() throws IOException {

		String excelFilePath = "C:\\Users\\ranjith.raghavan\\Documents\\Scenario_Output.xlsx";

		
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(inputStream);

		Sheet sheet = workbook.getSheetAt(0);

		Set<String> keyset =  AccountResults.keySet();
		int rownum = sheet.getLastRowNum();
		for (String key : keyset) {
			Row row = sheet.createRow(++rownum);
			Object[] objArr =  AccountResults.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof Date)
					cell.setCellValue((Date) obj);
				else if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				else if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(new File("C:\\\\Users\\\\ranjith.raghavan\\\\Documents\\\\Scenario_Output.xlsx"));
			workbook.write(out);
			out.close();

			System.out.println("Successfully saved Selenium WebDriver Leads result to Excel File!!!");
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Fail");
		}
		driver.close();
	}
	//To Calculate the Runtime of the Class
	@AfterSuite 
	public void TotalTime() {
		Endtime = System.currentTimeMillis();
		long totaltime = Endtime - Starttime;
		System.out.println("Total time take = " + totaltime);
		
	}


}


