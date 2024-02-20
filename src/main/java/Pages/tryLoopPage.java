package Pages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import generic.Helper;
import mainBase.baseTest;

public class tryLoopPage extends baseTest{

	
	public tryLoopPage() throws IOException{
		PageFactory.initElements(driver, this);
	}

@FindAll({@FindBy(xpath="//li[@data-testid='optionItem']")})
	
	List<WebElement> list;

	@FindBy(xpath="//input[@type='text']")
	WebElement usname;
	@FindBy(xpath="//input[@type='password']")
	WebElement passwrd;
	@FindBy(xpath="//*[text()='Login']")
	WebElement loginbtn;
	
	@FindBy(xpath="//*[text()='Skip for now']")
	WebElement popup;
	
	@FindBy(xpath="//*[text()='3P Chargebacks']")
	WebElement chargebacks;
	
	@FindBy(xpath="//*[text()='History by Store']")
	WebElement historybystore;
	
	@FindBy(xpath="//button[@data-testid='pagination-next']")
	WebElement nextbuton;
	
	@FindBy(xpath="//*[text()='Transactions']")
	WebElement transactions;

	
	@FindBy(xpath="(//button[@data-testid='selectBtn'])[1]")
	WebElement locations;
	
	
	@FindBy(xpath="//button[text()='Clear']")
	WebElement clear;
	
	@FindBy(xpath="(//button[@data-testid='selectBtn'])[3]")
	WebElement marketplace;
	
	
	@FindBy(xpath="//button[@data-testid='applyBtn']")
	WebElement apply;
	
    @FindAll({@FindBy(xpath="//table/tbody/tr[2]/td/h6")})
	
	List<WebElement> lists;


 @FindBy(xpath="//button[text()='Download']")
 WebElement download;
 
 
 
	
	
 
 
 public void login(String usename,String Password) {
		
		usname.sendKeys(usename);
		passwrd.sendKeys(Password);
		loginbtn.click();
	}
	public void closepopup() throws IOException, InterruptedException {
		Helper help=new Helper();
		help.ClickandWait(popup);
	}

	public float get_each_month_count(int month) throws InterruptedException, IOException {
		
		Helper help=new Helper();
		help.ClickAndWait(chargebacks);
		help.ClickAndWait(historybystore);
		Thread.sleep(15000);
		float count=getcount(month);
		float total=0;
		nextbuton.isEnabled();
		for(int i=0;i<10;i++) {
		if(nextbuton.isEnabled()) {
			help.ClickandWait(nextbuton);
			total=total+getcount(month);
		}
		}
		return total+count;
	}

	public float get_grand_total_amount(int month) {
		
		float number=0;
		List<WebElement> ele=driver.findElements(By.xpath("//table/tbody/tr/td["+month+"]/h6"));
		for(int i=ele.size()-1;i>0;i--) {
			
			String result=ele.get(i).getText();
			String newStr = result.replaceAll("[$,]", "");
			number = Float.parseFloat(newStr);
		break;
	}
		return number;
	}
	public float getcount(int month) {
			List<WebElement> ele=driver.findElements(By.xpath("//table/tbody/tr/td["+month+"]/h6"));
			float sum=0;
			for(int i=0;i<ele.size()-1;i++) {
				
				String result=ele.get(i).getText();
				String newStr = result.replaceAll("[$,]", "");
				float number = Float.parseFloat(newStr);
				sum=sum+number;
	}
			return sum;
	}

	public void select_locations() throws IOException, InterruptedException {
		
		Helper help=new Helper();
		help.ClickAndWait(chargebacks);
		help.ClickAndWait(transactions);
		help.ClickAndWait(locations);
		help.ClickAndWait(clear);
		 help.Dropdown(list, "Blissful Buffet");
		 help.Dropdown(list, "Artisan Alchemy");
		 help.ClickAndWait(apply);
	}

	public String get_table_values() throws IOException, InterruptedException {
		
		Helper help=new Helper();
		String Firsttext= help.GetLists(lists);
		Clicknextbutton();
		String secondtext= help.GetLists(lists);
		return Firsttext+secondtext;
	}

	
	public String get_table_valuess() {
		
		int rowcount=driver.findElements(By.xpath("//table[@class='MuiTable-root css-15i8i05-MuiTable-root']//tr")).size();
		//int colcount=driver.findElements(By.xpath("//table[@class='MuiTable-root css-15i8i05-MuiTable-root']//th")).size();
		
		String text1="";
		int colcount=5;
		boolean flag=false;
		
		for(int i=1;i<rowcount;i++) {
			
			for(int j=1;j<=colcount;j++) {
				
				 String text=driver.findElement(By.xpath("//table[@class='MuiTable-root css-15i8i05-MuiTable-root']//tbody/tr["+i+"]/td["+j+"]")).getText();
				
				 text1= text+text1;
				}
			}
		return text1;
	}
	
	public void downloadfile() throws IOException, InterruptedException {
		
		Helper help=new Helper();
		
		help.ClickandWait(download);
		Thread.sleep(7000);
		Assert.assertTrue(help.verifydownloadedfile());
		Thread.sleep(5000);
	}
	
	
public static void fetchtada(String value) throws IOException {
		
		BufferedReader br=null;
		String str=null;
		String header[]=null;
		String colvol[]=null;
		int rowcount=0;
		int count=0;
		
		HashMap<String,String> testdata=null;
		
		br=new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\DownloadFolder\\chargebacks_payouts_overview.csv"));	
		
		while((str=br.readLine())!=null) {
			
			if(rowcount==0) {
				
				header=str.split(",");
			}else {
				colvol=str.split(",");
			}
			rowcount++;
		}
		testdata=new HashMap<String,String>();
		while(count<header.length) {
			
			testdata.put(header[count], colvol[count]);
			count++;
		}
		
		for(String s:testdata.keySet()) {
			
			System.out.println("col is  "+ s +" its value " +testdata.get(s));
			if(testdata.get(s).equalsIgnoreCase(value)) {
				
				System.out.println("value present in the csv");
				break;
			}
		}
		}
	
	
	public void Clicknextbutton() {
		
		nextbuton.isDisplayed();
		nextbuton.click();
	}
	public static String[] read(String server) throws CsvValidationException, IOException {
		
		//String[] serverdetails=new String[11];
		String[] csvlinedata;
		String[] serverdetails = null;
		int count=0;
		
		//serverdetails=
		
		
		
		CSVReader reader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\DownloadFolder\\chargebacks_payouts_overview.csv"));
		
		csvlinedata=null;
		
		while(csvlinedata!=null) {
			
			for(int i=0;i<csvlinedata.length;i++) {
				    
			        if(csvlinedata[0].equalsIgnoreCase(server)&&serverdetails!=null) {
				
				serverdetails[i]=csvlinedata[i+1];
				count++;
				reader.close();
				break;
			}
			}
			csvlinedata=reader.readNext();
		}
		if(count==0) {
			
			System.out.println("No data found");
		}
		return serverdetails;
		}

	public void select_marketplaces() throws IOException, InterruptedException {
		 Helper help=new Helper();
		 help.ClickAndWait(marketplace);
		 help.ClickAndWait(clear);
		 help.Dropdown(list, "Grubhub");
		 help.ClickAndWait(apply);
		 Thread.sleep(2000);
			}
	
	@SuppressWarnings("resource")
	public String readcsv(String path) throws IOException {
		
		BufferedReader reader=null;
		String line=null;
		String index2="",index1="";
		
		reader=new BufferedReader(new FileReader(path));
		
		
		while((line=reader.readLine())!=null) {
			
			String[] row=line.split(",");
			
			for(String index:row) {
				
				index1=index2+index;
			}
			System.out.println();
		}
		return index1;
		
		
	}

	public void validate_CSV_with_Web_table_values(String text1,String text2) {
		
		
		if(text1.equalsIgnoreCase(text2)) {
			
			System.out.println("Csv file values are same as web table values");
		}
		
	}
	}