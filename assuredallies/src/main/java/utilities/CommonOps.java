package utilities;


import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CommonOps extends Base{

    public static void click(WebElement element, String logtext) {
        try {
            element.click();
            if (!logtext.equals(""))
                test.log(LogStatus.PASS, logtext + "  passed successfully");
        } catch (Throwable e) {
            test.log(LogStatus.FAIL, logtext + "  failed, See error: " + e.getMessage() + test.addScreenCapture(getScreenshot()));
            fail(logtext + "click on element failed, See error: " + e.getMessage());
        }
    }

    public static void sendKey(WebElement element, String value, String Logtext){
        try {
            element.sendKeys(value);
            test.log(LogStatus.PASS, Logtext + "  passed successfully");
        } catch (Throwable e) {
            test.log(LogStatus.FAIL, Logtext + "  failed, See error: " + e.getMessage() + test.addScreenCapture(getScreenshot()));
            fail(Logtext + "sendKey to element failed, See error: " + e.getMessage());
        }
    }

    public static void verifyEquals(String expected, String actual, String Logtext) {
        try {
            assertEquals(expected, actual);
            test.log(LogStatus.PASS, Logtext + " passed successfully");
        } catch (Throwable e) {

            test.log(LogStatus.FAIL, "Verify Equals failed, See error: " + e.getMessage() + test.addScreenCapture(getScreenshot()));
            fail("Verify Equals failed, See error: " + e.getMessage());
        }

    }

    public static String getText(WebElement element) {
        return element.getText();
    }


    public static void sleep(int sleepNumber) {
        try {
            TimeUnit.SECONDS.sleep(sleepNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void clickEnterOnKB(WebElement element,String Logtext){
        try{
            element.sendKeys(Keys.ENTER);
            test.log(LogStatus.PASS, Logtext + "  passed successfully");
        }
        catch (Throwable e) {
            test.log(LogStatus.FAIL, Logtext + "  failed, See error: " + e.getMessage() + test.addScreenCapture(getScreenshot()));
            fail(Logtext + "Click failed to the enter button in the Keyword , See error: " + e.getMessage());
        }

    }
}
