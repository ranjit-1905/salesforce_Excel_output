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

import Login.Login;
import Login.Navigation;
import OpportunityScenario.AnnualRevenueAutopopulate;
import OpportunityScenario.Closed_lost;
import OpportunityScenario.Closed_won;
import OpportunityScenario.ContactDetails;
import OpportunityScenario.Contract_Creation;
import OpportunityScenario.DataType;
import OpportunityScenario.OpportunityLayouts;
import OpportunityScenario.QualificationTask;
import OpportunityScenario.Quote_PDF;
import OpportunityScenario.Quote_created;





public class Opportunity_Scenario {
	String driverPath = "D:\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver;
	Login objLogin;
	Navigation objNavigation;
	OpportunityLayouts objOpportunityLayouts;
	DataType objDataType;
	AnnualRevenueAutopopulate objAnnualRevenueAutopopulate;
	ContactDetails objContactDetails;
	QualificationTask objQualificationTask;
	Quote_created objQuote_created;
	Closed_won objClosed_won;
	Contract_Creation objContract_Creation;
	Closed_lost objClosed_lost;
	Quote_PDF objQuote_PDF;	


	//Opportunity objOpportunity;

	Map<String, Object[]> OpportunityResults;

	//To Calculate test class run time

	long Starttime;
	long Endtime;


	@BeforeTest
	public void setup() {

		//System.setProperty("webdriver.chrome.silentOutput","true");
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
	public void  Opportunity_Layouts () {

		objOpportunityLayouts = new OpportunityLayouts(driver); 
		try {
			objOpportunityLayouts.gotoOpportunity(); 
			// TODO Auto-generated catch block
			OpportunityResults.put("3",
					new Object[] { "1","3.1",  "opportunity -Check Field availables in Opportunity Layouts ","Pass" });

		} catch (Exception e) {

			OpportunityResults.put("3",
					new Object[] { "1","3.1",  "opportunity -Check Field availables in Opportunity Layouts","Fail" });

			Assert.assertTrue(false);
		}
	}
	@Test (priority = 3)//,enabled=false)
	public void Opportunity_Date_Field_Type() {

		objDataType = new DataType(driver); 
		try {
			objDataType.toGetFieldtype();

			OpportunityResults.put("4",
					new Object[] { "2","3.2",  "Check Field Data Type","Pass" });


		} catch (Exception e) {

			OpportunityResults.put("4",
					new Object[] { "2","3.2",  "Check Field Data Type","Fail" });

			Assert.assertTrue(false);

		}
	}
	@Test (priority = 4)// ,enabled=false)
	public void AutopopulateAnnualRevenue()  {

		objAnnualRevenueAutopopulate = new AnnualRevenueAutopopulate(driver);
		try {
			objAnnualRevenueAutopopulate.AnnualRevenue_Autopopulate();

			OpportunityResults.put("5",
					new Object[] { "3","3.3",  "Opportunity - Annual Revenue field should be autopopulate from Account","Pass" });


		} catch (Exception e) {

			OpportunityResults.put("5",
					new Object[] {"3","3.3",  "Opportunity - Annual Revenue field should be autopopulate from Account","Fail" });

			Assert.assertTrue(false);

		}
	}

	@Test (priority = 5)//,enabled=false)
	public void Qualification_NewTask()  throws Exception {

		objQualificationTask = new QualificationTask(driver);
		try {
			objQualificationTask.Qualification_Task();
			OpportunityResults.put("6",
					new Object[] { "4","3.4",  "Qualification - Task has been created","Pass" });


		} catch (Exception e) {

			OpportunityResults.put("6",
					new Object[] {"4","3.4",  "Qualification - Task has been created","Fail" });

			Assert.assertTrue(false);

		}
	}
	@Test (priority = 6)//,enabled=false)
	public void AutoPopulate_Contact() throws Exception {

		objContactDetails = new ContactDetails(driver);
		try {
			objContactDetails.Contact_Details();
			OpportunityResults.put("7",
					new Object[] { "5","3.5",  " Opportunity-autopopulate from Contact","Pass" });


		} catch (Exception e) {

			OpportunityResults.put("7",
					new Object[] {"5","3.5",  " Opportunity-autopopulate from Contact","Fail" });

			Assert.assertTrue(false);
		}
	}

	@Test(priority = 7)//,enabled=false)
	public void Quote_created_tab() throws Exception {

		objQuote_created = new Quote_created(driver);
		try {
			objQuote_created.goto_Quotes_tab("5","5");

			OpportunityResults.put("8",
					new Object[] { "6","3.6",  " Quote has been created", "Pass" });

		} catch (Exception e) {

			OpportunityResults.put("8",
					new Object[] { "6","3.6",  " Quote has been created","Fail" });

			Assert.assertTrue(false);
		}
	}
	@Test(priority = 8)//,enabled=false)
	public void Quote_created_PDF() throws Exception {

		objQuote_PDF = new Quote_PDF(driver);
		try {
			objQuote_PDF.goto_Quotes_PDF();
			OpportunityResults.put("9",
					new Object[] { "7","3.7",  "Check Product availability & generate Quote PDF", "Pass" });


		} catch (Exception e) {

			OpportunityResults.put("9",
					new Object[] { "7","3.7",  " Check Product availability & generate Quote PDF","Fail" });

			Assert.assertTrue(false);

		}
	}
	@Test(priority = 9)//,enabled=false)
	public void  Closed_won() throws Exception {

		objClosed_won = new Closed_won(driver);
		try {
			objClosed_won.Closed_Won_task("Closed Won");
			OpportunityResults.put("10",
					new Object[] { "8","3.8",  "Check Quote Accepted and Check Contract Creation", "Pass" });


		} catch (Exception e) {

			OpportunityResults.put("10",
					new Object[] { "8","3.8",  "Check Quote Accepted and Check Contract Creation","Fail" });

			Assert.assertTrue(false);

		}
	}
	@Test(priority = 10)//,enabled=false)
	public void  Contract_Created() throws Exception {

		objContract_Creation = new Contract_Creation(driver);
		try {
			objContract_Creation.Contract_Create("All Orders");
			OpportunityResults.put("11",
					new Object[] { "9","3.9",  " Activated Contract and Order Creation", "Pass" });


		} catch (Exception e) {

			OpportunityResults.put("10",
					new Object[] { "9","3.9",  " Activated Contract and Order Creation","Fail" });

			Assert.assertTrue(false);

		}
	}

	@Test(priority = 11)//,enabled=false)
	public void  Contract_Lost()  throws Exception {

		objClosed_lost = new Closed_lost(driver);
		try {
			objClosed_lost.Lost_Closed("Closed Lost");
			OpportunityResults.put("12",
					new Object[] { "10","3.10",  "Closed Lost/Check Quote Rejected", "Pass" });

		} catch (Exception e) {

			OpportunityResults.put("12",
					new Object[] { "10","3.10",  "Closed Lost/Check Quote Rejected","Fail" });
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

		OpportunityResults = new LinkedHashMap<String, Object[]>();
		// add test result excel file column header
		// write the header in the first row
		OpportunityResults.put("1", new Object[] {"Test Cases - Opportunities Object Scenario"});
	}

	//To save the results in Excel

	@AfterClass
	public void suiteTearDown() throws IOException {

		String excelFilePath = "C:\\Users\\ranjith.raghavan\\Documents\\Scenario_Output.xlsx";
				
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(inputStream);

		Sheet sheet = workbook.getSheetAt(0);

		Set<String> keyset = OpportunityResults.keySet();
		int rownum = sheet.getLastRowNum();
		for (String key : keyset) {
			Row row = sheet.createRow(++rownum);
			Object[] objArr = OpportunityResults.get(key);
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

			System.out.println("Successfully saved Selenium WebDriver Opportunity result to Excel File!!!");

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
		Thread.sleep(5000);
		Endtime = System.currentTimeMillis(); 
		long totaltime = Endtime - Starttime;
		System.out.println("Total time take = " + totaltime);
				

	}
}


