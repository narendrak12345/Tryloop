package tryloop.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvException;

import Pages.tryLoopPage;
import dataDriven.testDatadriven;
import generic.Helper;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import mainBase.baseTest;

public class dataValidation extends baseTest{

	public tryLoopPage page;
	
	public String loc=(System.getProperty("user.dir")+"\\DownloadFolder\\chargebacks_payouts_overview.csv");
	//public testDatadriven testdrive;
	
	public dataValidation() throws IOException {
		super();
	}

	
	public  String[][] expectedTitles(String titleName) throws IOException {
	    String[][] testData = null;
	    String[] data = null;
	    String line = null;

	    BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\DownloadFolder\\chargebacks_payouts_overview.csv"));


	    while ((line = br.readLine()) != null){

	        data = line.split(",");
	       testData= new String[1][data.length];

	        if(data[1].equalsIgnoreCase(titleName))
	        {
	            for(int i =0; i<data.length; i++)

	            {

	            testData[0][i] = data[i];

	            }

	        break;
	        }
	    }
	    return testData;        
	}
	
	@Step("Application has started")
	@BeforeMethod
	public void excutebefore() throws IOException, CsvException {
		init();
	    page=new tryLoopPage();
	    //testdrive=new testDatadriven();
		File directory=	new File(location);
		FileUtils.cleanDirectory(directory);
		
	}
	
	@Test
	@Description("Validating sum of each values of august month equals to total grand value")
	public void Check_aug_month() throws InterruptedException, IOException, ParseException {

		
		page.login(prop.getProperty("email"), prop.getProperty("password"));
		page.closepopup();
		
		float sum_of_all_amount_aug=page.get_each_month_count(2);
		System.out.println("Sum of all added amount of aug" +sum_of_all_amount_aug);
		float grand_total_aug=page.get_grand_total_amount(2);
		System.out.println("Total Grand mount " +grand_total_aug);
		Assert.assertEquals(sum_of_all_amount_aug, grand_total_aug);
	}
		
		@Test
		@Description("Validating sum of each values of sept month equals to total grand value")
		public void Check_sept_month() throws InterruptedException, IOException, ParseException {

			page.login(prop.getProperty("email"), prop.getProperty("password"));
			page.closepopup();
		
		float sum_of_all_amount_sep=page.get_each_month_count(3);
		System.out.println("Sum of all added amount sept" +sum_of_all_amount_sep);
		float grand_total_sep=page.get_grand_total_amount(3);
		System.out.println("Total Grand mount " +grand_total_sep);
		Assert.assertEquals(sum_of_all_amount_sep, grand_total_sep);
		}
		
		@Test
		@Description("Validating sum of each values of oct month equals to total grand value")
		public void Check_oct_month() throws InterruptedException, IOException, ParseException {

			page.login(prop.getProperty("email"), prop.getProperty("password"));
			page.closepopup();

		float sum_of_all_amount_oct=page.get_each_month_count(4);
		System.out.println("Sum of all added amount oct" +sum_of_all_amount_oct);
		float grand_total_oct=page.get_grand_total_amount(4);
		System.out.println("Total Grand mount " +grand_total_oct);
		Assert.assertEquals(sum_of_all_amount_oct, grand_total_oct);
		
		}
		
		@Test
		@Description("Validating sum of each values of nov month equals to total grand value")
		public void Check_nov_month() throws InterruptedException, IOException, ParseException {

			page.login(prop.getProperty("email"), prop.getProperty("password"));
			page.closepopup();
		float sum_of_all_amount_nov=page.get_each_month_count(5);
		System.out.println("Sum of all added amount nov" +sum_of_all_amount_nov);
		float grand_total_nov=page.get_grand_total_amount(5);
		System.out.println("Total Grand mount " +grand_total_nov);
		Assert.assertEquals(sum_of_all_amount_nov, grand_total_nov);
		
		}
		
		@Test
		@Description("Validating sum of each values of dec month equals to total grand value")
		public void Check_dec_month() throws InterruptedException, IOException, ParseException {

			page.login(prop.getProperty("email"), prop.getProperty("password"));
			page.closepopup();
		float sum_of_all_amount_dec=page.get_each_month_count(6);
		System.out.println("Sum of all added amount dec" +sum_of_all_amount_dec);
		float grand_total_dec=page.get_grand_total_amount(6);
		System.out.println("Total Grand mount " +grand_total_dec);
		Assert.assertEquals(sum_of_all_amount_dec, grand_total_dec);
		
		
		}
		@Test
		@Description("Validating sum of each values of jan month equals to total grand value")
		public void Check_jan_month() throws InterruptedException, IOException, ParseException {

			page.login(prop.getProperty("email"), prop.getProperty("password"));
			page.closepopup();
		float sum_of_all_amount_jan=page.get_each_month_count(7);
		System.out.println("Sum of all added amount jan" +sum_of_all_amount_jan);
		float grand_total_jan=page.get_grand_total_amount(7);
		System.out.println("Total Grand mount " +grand_total_jan);
		Assert.assertEquals(sum_of_all_amount_jan, grand_total_jan);
		
		}
		@Test
		@Description("Validating sum of each values of feb month equals to total grand value")
		public void Check_feb_month() throws InterruptedException, IOException, ParseException {

			page.login(prop.getProperty("email"), prop.getProperty("password"));
			page.closepopup();
		float sum_of_all_amount_feb=page.get_each_month_count(8);
		System.out.println("Sum of all added amount feb" +sum_of_all_amount_feb);
		float grand_total_feb=page.get_grand_total_amount(8);
		System.out.println("Total Grand mount " +grand_total_feb);
		Assert.assertEquals(sum_of_all_amount_feb, grand_total_feb);
		
	}
		
		@SuppressWarnings("unlikely-arg-type")
		@Test
		@Description("Data extractation and validation")
		public void Check_extrct_data() throws InterruptedException, IOException, ParseException, CsvException {

			page.login(prop.getProperty("email"), prop.getProperty("password"));
			page.closepopup();
			page.select_locations();
			page.select_marketplaces();
			page.downloadfile();
			tryLoopPage page=new tryLoopPage();
			
			String value=page.get_table_valuess();
			System.out.println("table values are " +value);
			tryLoopPage.fetchtada(value);
			//String str1=page.readcsv(loc);
			//System.out.println(str1);
			//String str=page.get_table_values();
			//
			
			//System.out.println(page.get_table_valuess());
			
			
			
		}
		
	@AfterMethod
	public void teardown() {
		
		driver.quit();
	}
}
