import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBank1 {
    private WebDriver driver;

    @BeforeAll
    static void setUoAll() {
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
    }


    @BeforeEach
        // перед запуском
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        // driver = new ChromeDriver();

    }

    @AfterEach
        // после запуска
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void submittingValidForm() throws InterruptedException {
// загрузить страницу
// поиск элементов
// взаимодейсвие с элементами
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Антилопа Гну");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79830000000");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
        Thread.sleep(3000);

    }

    @Test
    void submittingNoValidName() throws InterruptedException {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Sheldon Cooper");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79830000000");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String text = driver.findElement(By.cssSelector("[class=input__sub]")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text);
        Thread.sleep(5000);
    }

}
