package TestRunner;

import org.json.simple.JSONObject;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PersonalDetailsPage;
import pages.PimPage;
import utils.Utils;

import java.io.IOException;
import java.text.ParseException;

import setup.setup;

public class secondUserTestRunner extends setup {
    DashboardPage dashboardPage;
    LoginPage loginPage;
    PimPage pimPage;
    PersonalDetailsPage personalDetailsPage;

    @Test(priority = 1, description = "User login")
    public void doLoginWithSecondUser() throws IOException, ParseException, InterruptedException, org.json.simple.parser.ParseException {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        pimPage = new PimPage(driver);
        JSONObject userObject = Utils.loadJSONFileArray("./src/test/resources/newuser.json", 1);
        String username = userObject.get("username").toString();
        String password = userObject.get("password").toString();
        loginPage.doLogin(username, password);
        Thread.sleep(5000);
        dashboardPage.menus.get(2).click();
    }

    @Test(priority = 2, description = "select gender")
    public void gender() throws InterruptedException {
        personalDetailsPage = new PersonalDetailsPage(driver);

        personalDetailsPage.selectGender();
        Utils.doScrolling(driver, 400);
        Thread.sleep(9000);
    }

    @Test(priority = 3, description = "update select blood group")
    public void updateBlood() throws InterruptedException {
        personalDetailsPage.selectBloodGroup();
        personalDetailsPage.dropdownBlood.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        personalDetailsPage.dropdownBlood.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        personalDetailsPage.dropdownBlood.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        personalDetailsPage.dropdownBlood.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        personalDetailsPage.dropdownBlood.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        personalDetailsPage.dropdownBlood.get(2).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
    }

    @Test(priority = 4, description = "user update contact details")
    public void updateContactDetails() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
        personalDetailsPage.contactDetailsInput();
        Thread.sleep(2000);
        personalDetailsPage.txtInput.get(1).sendKeys("234 Soho Hill");
        Thread.sleep(2000);
        personalDetailsPage.txtInput.get(2).sendKeys("Indianapolis");
        Thread.sleep(2000);
        personalDetailsPage.txtInput.get(3).sendKeys("Birmingham");
        Thread.sleep(2000);
        personalDetailsPage.txtInput.get(4).sendKeys("West Midlands");
        Thread.sleep(2000);
        personalDetailsPage.txtInput.get(5).sendKeys("1893");
        Thread.sleep(2000);
        personalDetailsPage.selectCountry.click();
        personalDetailsPage.selectCountry.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        personalDetailsPage.selectCountry.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        personalDetailsPage.selectCountry.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        personalDetailsPage.selectCountry.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        personalDetailsPage.selectCountry.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        personalDetailsPage.txtInput.get(7).sendKeys("+18927363895");
        personalDetailsPage.txtInput.get(9).sendKeys("testuser" + Utils.generateRandomNumber(10, 99) + "@gmail.com");
        Thread.sleep(3000);
        personalDetailsPage.saveBtn.click();
    }

    @Test(priority = 5, description = "user log out")
    public void userLogout() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.doLogout();
    }
}
