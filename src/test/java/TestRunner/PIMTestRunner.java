package TestRunner;

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PersonalDetailsPage;
import pages.PimPage;
import utils.Utils;

import java.io.IOException;
import java.text.ParseException;

import setup.setup;

import static utils.Utils.addJsonArray;

public class PIMTestRunner extends setup {
    DashboardPage dashboardPage;
    PimPage pimPage;
    PersonalDetailsPage personalDetailsPage;

    @Test(priority = 1, description = "User login")
    public void doLogin() throws IOException, ParseException, org.json.simple.parser.ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONObject userObject = Utils.loadJSONFile("./src/test/resources/user.json");
        String username = (String) userObject.get("username");
        String password = (String) userObject.get("password");
        loginPage.doLogin(username, password);
    }

    @Test(priority = 2, description = "User can Create employee1")
    public void createEmployee1() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException {
        pimPage = new PimPage(driver);

        Thread.sleep(5000);
        driver.findElements(By.className("oxd-main-menu-item--name")).get(1).click();
        Thread.sleep(3000);
        String username = "Fahmida" + Utils.generateRandomNumber(1000, 9999);
        String password = "P@ssword1234";
        String employeeId = "0" + Utils.generateRandomNumber(100, 999);
        Thread.sleep(5000);
        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        Thread.sleep(5000);
        pimPage.createEmployee(firstname, lastname, username, password, employeeId);
        String header_actual = driver.findElements(By.tagName("h6")).get(1).getText();
        String header_expected = "Add Employee";
        Assert.assertTrue(header_actual.contains(header_expected));
        Thread.sleep(5000);
        String msg_actual = driver.findElements(By.className("oxd-text--h6")).get(2).getText();
        String msg_expected = "Personal Details";
        Assert.assertTrue(msg_actual.contains(msg_expected));
        //Utils.doScorlling(driver,500);
        addJsonArray(firstname, lastname, username, password, employeeId);

    }

    @Test(priority = 3, description = "User can Create employee2")
    public void createEmployee2() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException {
        pimPage = new PimPage(driver);
        Thread.sleep(5000);
        driver.findElements(By.className("oxd-main-menu-item--name")).get(1).click();
        Thread.sleep(2000);
        String username = "Fahmida" + Utils.generateRandomNumber(1000, 9999);//random name generate
        Thread.sleep(2000);
        String password = "P@ssword1234";
        String employeeId = "0" + Utils.generateRandomNumber(100, 999);


        Thread.sleep(5000);

        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        Thread.sleep(5000);

        pimPage.createEmployee(firstname, lastname, username, password, employeeId);
        String header_actual = driver.findElements(By.tagName("h6")).get(1).getText();
        String header_expected = "Add Employee";
        Assert.assertTrue(header_actual.contains(header_expected));
        Thread.sleep(5000);

        String msg_actual = driver.findElements(By.className("oxd-text--h6")).get(2).getText();
        String msg_expected = "Personal Details";
        Assert.assertTrue(msg_actual.contains(msg_expected));
        //Utils.doScorlling(driver,500);
        addJsonArray(firstname, lastname, username, password, employeeId);
    }

    @Test(priority = 4, description = "user can search employee")
    public void searchEmployeeInfo() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        Thread.sleep(3000);
        dashboardPage.menus.get(1).click();
        Thread.sleep(3000);
        String isUserFound = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        Assert.assertTrue(isUserFound.contains("Records Found"));

        dashboardPage.employeeName.get(1).click();

        dashboardPage.employeeName.get(1).sendKeys("Alice");
        driver.findElement(By.cssSelector("[type=submit]")).click();
        Thread.sleep(2000);
        //String employeeFound=driver.findElements(By.className("oxd-text--span")).get(11).getText();

        SoftAssert softAssert = new SoftAssert();
        String empFound_actual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        String empFound_expected = "Record found";
        softAssert.assertTrue(empFound_actual.contains(empFound_expected));

        Utils.doScrolling(driver, 200);

    }

    @Test(priority = 5, description = "search emp by valid name")
    public void searchEmployeeByName() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        PimPage pimPage = new PimPage(driver);
        Thread.sleep(3000);
        dashboardPage.menus.get(1).click();
        Thread.sleep(3000);
        String isUserFound = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        Assert.assertTrue(isUserFound.contains("Records Found"));


        Thread.sleep(4000);
        JSONObject userObject = Utils.loadJSONFileArray("./src/test/resources/newuser.json", 1);
        String employeeName = userObject.get("firstname").toString();
        pimPage.SearchEmployeeByValidName(employeeName);

    }

    @Test(priority = 6, description = "search emp by invalid name")
    public void searchEmployeeByInvalidName() throws InterruptedException, IOException, ParseException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.menus.get(1).click();
        dashboardPage.employeeName.get(1).click();
        dashboardPage.employeeName.get(1).sendKeys("ghdfkjhgd");
        driver.findElement(By.cssSelector("[type=submit]")).click();
        Thread.sleep(2000);

        SoftAssert softAssert = new SoftAssert();
        String empFound_actual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        String empFound_expected = "No Records found";
        softAssert.assertTrue(empFound_actual.contains(empFound_expected));

        Utils.doScrolling(driver, 200);
    }

    @Test(priority = 7, description = "user can update user ID")
    public void updateEmpId() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        PimPage pimPage = new PimPage(driver);
        Thread.sleep(3000);
        dashboardPage.menus.get(1).click();//click on PIM

        Thread.sleep(7000);
        JSONObject userObject = Utils.loadJSONFileArray("./src/test/resources/newuser.json", 0);
        String employeeId = userObject.get("employeeId").toString();
        pimPage.SearchEmployeeByNewId(employeeId);

        Utils.doScrolling(driver, 200);

        pimPage.searchBtn.click();
        Thread.sleep(3000);
        pimPage.editUser.click();
        Thread.sleep(5000);
        pimPage.txtEmployeeId.get(5).click();

        Thread.sleep(3000);
        pimPage.txtEmployeeId.get(5).sendKeys(Keys.CONTROL + "a");
        Thread.sleep(3000);
        pimPage.txtEmployeeId.get(5).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(3000);
        pimPage.txtEmployeeId.get(5).sendKeys("0" + Utils.generateRandomNumber(100, 999));
        driver.findElements(By.tagName("button")).get(1).click();


    }

    @Test(priority = 8, description = "user can search New user by valid emp ID")
    public void searchNewUserByValidID() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        PimPage pimPage = new PimPage(driver);
        Thread.sleep(3000);
        dashboardPage.menus.get(1).click();
        Utils.doScrolling(driver, 300);
        Thread.sleep(3000);
        String isUserFound = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        Assert.assertTrue(isUserFound.contains("Records Found"));

        Thread.sleep(4000);
        JSONObject userObject = Utils.loadJSONFileArray("./src/test/resources/newuser.json", 1);
        String employeeId = userObject.get("employeeId").toString();
        pimPage.SearchEmployeeByNewId(employeeId);
    }

    @Test(priority = 9, description = "user can search by invalid emp ID")
    public void searchUserByInvalidEmpId() throws InterruptedException, IOException, ParseException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        PimPage pimPage = new PimPage(driver);
        Thread.sleep(3000);
        dashboardPage.menus.get(1).click();
        Thread.sleep(3000);
        dashboardPage.employeeName.get(2).click();
        dashboardPage.employeeName.get(2).sendKeys("WrongId123");
        driver.findElement(By.cssSelector("[type=submit]")).click();
        Utils.doScrolling(driver, 300);
        Thread.sleep(2000);
        String isUserFound = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        Assert.assertTrue(isUserFound.contains("No Records Found"));
    }

    @Test(priority = 10, description = "user log out")
    public void userLogout() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.doLogout();
    }
}


