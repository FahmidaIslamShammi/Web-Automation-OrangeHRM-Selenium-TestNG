package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class setup {
    public WebDriver driver;

    @BeforeTest
    public void Setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headed");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-software-rasterizer");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @AfterTest
    public void closeDriver() {

        driver.quit();
    }
}

