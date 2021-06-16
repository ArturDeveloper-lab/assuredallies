package businessScenario;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.CommonOps;

import java.io.IOException;

public class Tests extends  CommonOps {

    @BeforeClass
    public static void setUp(){
        initExtentReport();
    }

    @Before
    public void beforeTest() throws IOException {
        initExecution();
        driver.get(baseUrl);
    }

    @After
    public void finalizingTest()
    {
        finilizeExtentReportTest();
        driver.quit();
    }

    @AfterClass
    public static void tearDown() {
        finalizeExtentReport();
        driver.quit();
    }

    @Rule
    public TestName name =new TestName();

    public static final String longText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book";
    public static final String note1 = "Create automation test";
    public static final String note2 = "The test performed in java";
    public static final String note3 = "I love Ashdod";


    @org.junit.Test
    public void insertLongText_and_verify()  {
        initTest(name.getMethodName()+ " - "+getData("BrowserType"), "Inset long text and verify existing text");
        CommonOps.sendKey(mainPage.mainTextField,longText,"The text insert ");
        CommonOps.clickEnterOnKB(mainPage.mainTextField,"Click on the Enter button ");
        String getTextFromToDo = CommonOps.getText(mainPage.insertedText);
        CommonOps.verifyEquals(getTextFromToDo,longText,"Verification of inserted long text");
    }

    @org.junit.Test
    public void verificationOf_Count_Of_insertedNoteInToDo()  {
        initTest(name.getMethodName()+ " - "+getData("BrowserType"), "Verification of number in count after insert 3 notes ");
        CommonOps.sendKey(mainPage.mainTextField,note1,"The text insert note1 ");
        CommonOps.clickEnterOnKB(mainPage.mainTextField,"Click on the Enter button ");
        CommonOps.sendKey(mainPage.mainTextField,note2,"The text insert note2 ");
        CommonOps.clickEnterOnKB(mainPage.mainTextField,"Click on the Enter button ");
        CommonOps.sendKey(mainPage.mainTextField,note3,"The text insert note3 ");
        CommonOps.clickEnterOnKB(mainPage.mainTextField,"Click on the Enter button ");
        String getCountOfNotes = CommonOps.getText(mainPage.countOfNotes);
        CommonOps.verifyEquals(getCountOfNotes,"3","Verification of count of notes");
    }












}

