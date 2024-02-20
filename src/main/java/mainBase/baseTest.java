package mainBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {

	public static WebDriver driver;
	public static Properties prop;
	public static FileInputStream fis;
	public static FileInputStream fis1;
	public static FileInputStream filePath;
	//public static String location;
	public static String location=System.getProperty("user.dir")+File.separator+"DownloadFolder"+File.separator;
	public String expected="chargebacks_payouts_overview.csv";
	
	
	public baseTest() throws IOException {
		
		prop = new Properties();
		String home = System.getProperty("user.dir");
		fis = new FileInputStream(home + "\\src\\main\\resources\\Properties\\Testdata.properties");
		prop.load(fis);
	}
	
	public void init() {

		//location=System.getProperty("user.dir")+File.separator+"DownloadFolder"+File.separator;
	
		File downloadFolder = new File(location);
        downloadFolder.mkdir();
		WebDriverManager.chromedriver().setup();
		WebDriverManager.chromedriver().clearDriverCache().setup();
		WebDriverManager.chromedriver().clearResolutionCache().setup();
		ChromeOptions options = new ChromeOptions();
		Map<String,Object> pref1=new HashMap<String, Object>();
		pref1.put("download.default_directory",location);
		options.setExperimentalOption("prefs", pref1);
	
		String browser = prop.getProperty("browser");

		if (browser.equals("chrome")) {
			driver=new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));
		driver.get(prop.getProperty("URL"));
	}
}

}
