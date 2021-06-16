package pageElements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {


    @FindBy(xpath = "//input[@placeholder='What needs to be done?']")
    public WebElement mainTextField;

    @FindBy(xpath = "//label[@ng-dblclick='editTodo(todo)']")
    public WebElement insertedText;

    @FindBy(xpath = "//span[@class = 'todo-count']/*[@class ='ng-binding']")
    public WebElement countOfNotes;





}
