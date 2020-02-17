package booking;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;

public class BookingTest {

    int maximumPrice = 1000;
    static WebDriver driver;

    @BeforeEach
    public void initialSetUp() {
        System.setProperty("webdriver.chrome.driver", "D:/installation/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.booking.com");
        driver.manage().window().maximize();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement dates = driver.findElement(By.xpath("//div[@class=\"xp__dates-inner xp__dates__checkin\"]"));
        dates.click();
        WebElement checkIn = driver.findElement(By.xpath("//span[@aria-label=\"19 Февраль 2020\"]"));
        checkIn.click();
        WebElement checkOut = driver.findElement(By.xpath("//span[@aria-label=\"26 Февраль 2020\"]"));
        checkOut.click();
        WebElement citySearch = driver.findElement(By.xpath("//input[@type=\"search\"]"));
        citySearch.sendKeys("Париж");
        citySearch.submit();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement lowestPrice = driver.findElement(By.xpath("//a[contains(text(),'Цена (сначала самая низкая)')]"));
        lowestPrice.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement hotelsAvailability = driver.findElement(By.xpath("//span[contains(text(),'Показать только доступные варианты')]"));
        hotelsAvailability.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void close() {
        driver.quit();
    }

    @Test
    public void hotelsAreAvailable() {
        List<WebElement> listOfHotels = driver.findElements(By.xpath("//a[@class=\"hotel_name_link url\"]"));
        Assert.assertFalse(listOfHotels.isEmpty());
    }

    @Test
    public void priceCheck() {

        String xpath = "//div[@class=\"bui-review-score__badge\"]";
        double rating = 0.0;
        int index = 0;

        for (int i = 0; i < driver.findElements(By.xpath(xpath)).size(); i++) {
            double resultRating = Double.parseDouble(driver.findElements(By.xpath(xpath)).get(i).getText().replace(',', '.'));
            if (resultRating > rating) {
                rating = resultRating;
                index = i;
            }
        }

        driver.findElements(By.xpath(xpath)).get(index).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement hotelPrice = driver.findElement(By.xpath("(//div[@class=\"bui-price-display__value prco-font16-helper\"])[1]"));
        int price = Integer.parseInt(hotelPrice.getText().replaceAll("\\D", ""));
        Assert.assertTrue(maximumPrice >= price);
    }
}
