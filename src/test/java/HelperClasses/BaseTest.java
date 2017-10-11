package HelperClasses;


import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Sharikov Pavel on 10.10.2017.
 */
public class BaseTest {
    public WebDriver driver;
    public Properties props;
    InputStreamReader isr;
    public static final String PATH_APP_PROPERTIES = System.getProperty("user.dir")
            + "/src/test/resources/application.properties";

    @Before
    public void initTest() throws Exception {
        initProps();
        initWebDriver();
    }

    @After
    public void aftherTest() {
        if (driver.getWindowHandles().size() > 0) {
            driver.quit();
        }
    }

    public void initProps() throws Exception {
        props = new Properties();
        try {
            isr = new InputStreamReader(new FileInputStream(new File(PATH_APP_PROPERTIES)),
                    "UTF8");
            props.load(isr);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Ошибка. Не удалось прочитать файл конфигураций");
        }
    }

    public void initWebDriver() throws Exception {
        String browserName = props.getProperty("browser");
        switch (browserName) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                        props.getProperty("pathToChromeDriver"));
                driver = new ChromeDriver();
                break;
            }
            case "firefox":
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                        props.getProperty("pathToGeckoDriver"));
                driver = new FirefoxDriver();
                break;
            default:
                throw new Exception("Не задан браузер или не верный путь к драйверу");
        }
    }
}