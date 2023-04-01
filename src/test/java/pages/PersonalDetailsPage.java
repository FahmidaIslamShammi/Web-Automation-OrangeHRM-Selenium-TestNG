package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PersonalDetailsPage {
    @FindBy(xpath = "//label[normalize-space()='Male']")
    public WebElement radioBtn;
    @FindBy(tagName = "button")
    public List<WebElement> saveBtn1;
    @FindBy(tagName = "button")
    public List<WebElement> saveBtn2;
    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> dropdownBlood;
    @FindBy(className = "orangehrm-tabs-item")
    public List<WebElement> contactDetails;
    @FindBy(tagName = "input")
    public List<WebElement> txtInput;
    @FindBy(className = "oxd-select-text-input")
    public WebElement selectCountry;
    @FindBy(css = "[type=submit]")
    public WebElement saveBtn;
    WebDriver driver;

    public PersonalDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void selectGender() throws InterruptedException {
        Thread.sleep(5000);
        radioBtn.click();
        Thread.sleep(3500);
        saveBtn1.get(1).click();
    }

    public void selectBloodGroup() throws InterruptedException {
        dropdownBlood.get(2).click();
        Thread.sleep(3500);
        saveBtn2.get(2).click();
    }

    public void contactDetailsInput() throws InterruptedException {
        contactDetails.get(1).click();
    }
}
