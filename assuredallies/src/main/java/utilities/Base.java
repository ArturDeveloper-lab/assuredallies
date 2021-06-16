package utilities;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;
import pageElements.MainPage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Base {

    private static String sPathFile = "projConfig.xml";
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
    protected static String location;
    static protected String Path = "";
    static protected WebDriver driver;   // = null;
    static protected String baseUrl;
    static protected MainPage mainPage;





    public static void initExecution() throws IOException {
        System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
        if (getData("BrowserType").equals("Chrome")) {
            driver = new ChromeDriver();
            System.out.println("launching chrome browser");
        } else if (getData("BrowserType").equals("FireFox")) {
            System.out.println();
            driver = new FirefoxDriver();
            System.out.println("launching FireFox browser");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(getData("WaitTime")), TimeUnit.SECONDS);
        baseUrl="https://todomvc.com/examples/angularjs/#/";
        mainPage = PageFactory.initElements(driver, MainPage.class);

    }

    public void initTest(String sTestName, String sTestDescription)
    {
        try {
            location = getData("ReportFilePath") + "Execution_" + timeStamp + "/";
            File dir = new File(location);
            dir.mkdir();
            test = extent.startTest(sTestName, sTestDescription);
        }
        catch (Exception e)
        {
            System.out.println("Extent report file creation failed. See details " + e.getMessage());
        }
    }

    public static void initExtentReport()
    {
        extent = extentReportsInstance(timeStamp);
    }

    public void finilizeExtentReportTest()
    {
        extent.endTest(test);
    }

    public static void finalizeExtentReport() {
        extent.flush();
        extent.close();

    }

    public static ExtentReports extentReportsInstance(String timeStamp)
    {
        ExtentReports extent;
        Path = getData("ReportFilePath") + "Execution_" + timeStamp + "/" + getData("ReportFileName");
        extent = new ExtentReports(Path, false, DisplayOrder.OLDEST_FIRST);
        return extent;
    }



    public static String getScreenshot(){
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"
                + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }



    public static String getData(String sNodeName) {
        try {
            File fXmlFile = new File(sPathFile);
            InputStream in = new FileInputStream(fXmlFile);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            return doc.getElementsByTagName(sNodeName).item(0).getTextContent();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return "";
    }

}
