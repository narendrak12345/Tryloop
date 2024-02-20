package generic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVReader;

import mainBase.baseTest;

public class Helper extends baseTest{


	public Helper() throws IOException {
        super();
	}
	
	public void ClickAndWait(WebElement ele) {
		ele.isDisplayed();
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(ele)).click();
	}
	
	public boolean verifydownloadedfile() {
		File folder=new File(location);
		File[] filelist=folder.listFiles();
		
		boolean isFilepresent=false;
		for(File file:filelist) {
			
			if(file.isFile()) {
				
				String filename=file.getName();
				System.out.println(filename);
				
		if(filename.matches(expected)) {
			
			isFilepresent=true;
		}
	
		}
	}
		return isFilepresent;
	}
	
	public void ClickandWait(WebElement ele) throws InterruptedException {
		ele.isDisplayed();
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).click().perform();
		Thread.sleep(5000);
	}
	
	public static void fetchtada() throws IOException {
		
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
		}
	}
	
	
	
	
	public void ClickAndWait(WebElement ele, String text) {

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(ele)).click();
	}

	public String GetLists(List<WebElement> alloptions) throws InterruptedException {

		List<WebElement> m = alloptions;
		Thread.sleep(2000);
		String text =" ";
		for(int i=0;i<m.size();i++) {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(13));
			
			wait.until(ExpectedConditions.visibilityOfAllElements(m));
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", m);
			text = text +"\n"+ m.get(i).getText();
			
		}
		return text;
	}
	 public void Dropdown(List<WebElement> alloptions,String value) throws InterruptedException {

			List<WebElement> m = alloptions;
			for (WebElement text : m) {
	
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(13));
				
				wait.until(ExpectedConditions.visibilityOfAllElements(text));
	
				//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", text);
				System.out.println(text.getText());
				
				if(text.getText().equals(value))
						{
					text.click();
					break;
						}
		}
	 }
}
