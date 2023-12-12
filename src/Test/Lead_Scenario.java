		
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
import java.util.concurrent.TimeUnit;

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

import Login.Navigation;


import Login.Login;
import LeadScenario.Lead;
import LeadScenario.LeadStatus;
import LeadScenario.AnnualRevenue;
import LeadScenario.LeadConversion;
import LeadScenario.Creation;
import LeadScenario.ValidationRule;




public class Lead_Scenario {

	String driverPath = "D:\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver;
	Login objLogin;
	Navigation objNavigation;
	Lead objLead;
	ValidationRule objValidationRule;
	AnnualRevenue objAnnualRevenue;
	LeadStatus objLeadStatus;
	LeadConversion objLeadConversion;
	Creation objCreation;


	Map<String, Object[]> LeadResults;

	//To Calculate test class run time

	long Starttime;
	long Endtime;


	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32 (4)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
	public void Lead_LastName_Commpany () {
		try {
			objLead = new Lead(driver);		
			
			objLead.gotoLead("Mr.","Allen","Zimmers","Corp Moody");  
			// TODO Auto-generated catch block
			LeadResults.put("2",
					new Object[] { "1","1.1",  "Lead - Check required fields in Lead - Last Name & Company","Pass" });


		} catch (Exception e) {

			LeadResults.put("2",
					new Object[] { "1","1.1",  "Lead - Check required fields in Lead - Last Name & Company","Fail" });

			Assert.assertTrue(false);

		}
	}
	@Test (priority = 3)
	public void Validate_the_field() {
		objValidationRule = new ValidationRule(driver);
		try {
			objValidationRule.Validate (driverPath);

			LeadResults.put("3",
					new Object[] { "2","1.2",  "Lead - Validation Rule - Annual Revenue field should be filled","Pass" });


		} catch (Exception e) {

			LeadResults.put("3",
					new Object[] { "2","1.2",  "Lead - Validation Rule - Annual Revenue field should be filled","Fail" });

			Assert.assertTrue(false);

		}
	}
	@Test (priority = 4)// ,enabled=false)
	public void AnnualRevenue() {
		objAnnualRevenue= new AnnualRevenue(driver);
		try {
			objAnnualRevenue.gotoAnnual("25100","123 Main Street Apartment 4B ", "Anytown", "13126","NA", "United States");

			LeadResults.put("4",
					new Object[] { "3","1.3",  "Lead - Validation Rule - Annual Revenue has filled","Pass" });


		} catch (Exception e) {

			LeadResults.put("4",
					new Object[] {"3","1.3",  "Lead - Validation Rule - Annual Revenue has filled","Fail" });

			Assert.assertTrue(false);

		}
	}

	@Test (priority = 5)//,enabled=false)
	public void LeadConversion( ) throws Exception {
		objLeadStatus=new LeadStatus(driver);
		try {
			objLeadStatus.LeadStatusreport();
			LeadResults.put("5",
					new Object[] { "4","1.4",  "Lead Status should be changed to Qualified Lead","Pass" });


		} catch (Exception e) {

			LeadResults.put("5",
					new Object[] {"4","1.4",  "Lead Status should be changed to Qualified Lead","Fail" });

			Assert.assertTrue(false);

		}
	}
	@Test (priority = 6)//,enabled=false)
	public void LeadStauts() throws Exception {
		objLeadConversion=new LeadConversion(driver);
		try {
			objLeadConversion.gotoConversion();
			LeadResults.put("6",
					new Object[] { "5","1.5",  "Check Lead Conversion","Pass" });


		} catch (Exception e) {

			LeadResults.put("6",
					new Object[] {"5","1.5",  "Check Lead Conversion","Fail" });

			Assert.assertTrue(false);


		}
	}

	@Test(priority = 7)//,enabled=false)
	public void Account_OpportunityCreation() throws Exception {
		objCreation = new Creation(driver);		

		try {
			objCreation. ToValidate();

			LeadResults.put("7",
					new Object[] { "6","1.6",  "Check Account/Opportunity Creation", "Pass" });


		} catch (Exception e) {

			LeadResults.put("7",
					new Object[] { "6","1.6",  "Check Account/Opportunity Creation","Fail" });

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

		LeadResults = new LinkedHashMap<String, Object[]>();
		// add test result excel file column header
		// write the header in the first row
		LeadResults.put("1", new Object[] {"Test Cases - Lead Object Scenario"});
	}

	//To save the results in Excel

	@AfterClass
	public void suiteTearDown() throws IOException {

		String excelFilePath = "C:\\Users\\ranjith.raghavan\\Documents\\Scenario_Output.xlsx";

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(inputStream);

		Sheet sheet = workbook.getSheetAt(0);

		Set<String> keyset = LeadResults.keySet();
		int rownum = sheet.getLastRowNum();
		for (String key : keyset) {
			Row row = sheet.createRow(++rownum);
			Object[] objArr = LeadResults.get(key);
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
	public void TotalTime() throws InterruptedException {
		Endtime = System.currentTimeMillis(); 
		long totaltime = Endtime - Starttime;
		System.out.println("Total time take = " + totaltime);

	}

}


