package booking;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

public class BookingTest2 {

    static WebDriver driver;
    int minimumPrice = 6726;
    int price;

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
        WebElement checkIn = driver.findElement(By.xpath("//span[@aria-label=\"20 Февраль 2020\"]"));
        checkIn.click();
        WebElement checkOut = driver.findElement(By.xpath("//span[@aria-label=\"27 Февраль 2020\"]"));
        checkOut.click();
        WebElement guestsInput = driver.findElement(By.xpath("//label[@for=\"xp__guests__input\"]"));
        guestsInput.click();
        WebElement numberOfAdults = driver.findElement(By.xpath("//button[@aria-label=\"Взрослых: увеличить количество\"]"));
        numberOfAdults.click();
        numberOfAdults.click();
        WebElement numberOfRooms = driver.findElement(By.xpath("//button[@aria-label=\"Номера: увеличить количество\"]"));
        numberOfRooms.click();
        WebElement citySearch = driver.findElement(By.xpath("//input[@type=\"search\"]"));
        citySearch.sendKeys("Париж");
        citySearch.submit();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement highestPrice = driver.findElement(By.xpath("//div[@id=\"filter_price\"]//span[contains(text(),'952 + за ночь')]"));
        highestPrice.click();

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

        List<WebElement> hotelPrices = driver.findElements(By.xpath("//div[@class=\"bui-price-display__value prco-inline-block-maker-helper\"]"));
        price = Integer.parseInt(hotelPrices.get(0).getText().replaceAll("\\D", ""));
        int index = 0;

        for (int i = 0; i < hotelPrices.size(); i++) {
            int currentPrice = Integer.parseInt(hotelPrices.get(i).getText().replaceAll("\\D", ""));
            if (currentPrice < price) {
                price = currentPrice;
                index = i;
            }
        }
        driver.findElements(By.xpath("//span[contains(text(),'Выбрать номер')]")).get(index).click();
    }

    @AfterEach
    public void close() {
        driver.quit();
    }

    @Test
    public void checkPrice() {
        Assert.assertTrue(price >= minimumPrice);
    }

    @Test
    public void cardCheck() {
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement makeBooking1 = driver.findElement(By.xpath("//span[contains(text(),'Забронировать эти варианты')]"));
        makeBooking1.click();
        WebElement makeBooking2 = driver.findElement(By.xpath("//div[@class=\"hprt-reservation-cta\"]"));
        makeBooking2.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement secondName = driver.findElement(By.id("lastname"));
        secondName.sendKeys("Dubrovin");
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("yauhen_dubrovin@yahoo.co.uk");
        WebElement emailConfirm = driver.findElement(By.id("email_confirm"));
        emailConfirm.sendKeys("yauhen_dubrovin@yahoo.co.uk");
        emailConfirm.submit();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement phone = driver.findElement(By.id("phone"));
        phone.sendKeys("291000000");
        WebElement card = driver.findElement(By.id("cc_type"));
        Select selectCard = new Select(card);
        selectCard.selectByValue("Visa");
        WebElement cardNumber = driver.findElement(By.id("cc_number"));
        cardNumber.sendKeys("4242 4242 4242 4242");
        WebElement year = driver.findElement(By.id("ccYear"));
        Select selectYear = new Select(year);
        selectYear.selectByValue("2022");
        WebElement cvc = driver.findElement((By.id("cc_cvc")));
        cvc.sendKeys("111");
        cvc.submit();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement alert = driver.findElement(By.xpath("//div[@data-component=\"bp/payment-details-form/cc-number\"]//p[@role=\"alert\"]"));
        Assert.assertTrue(alert.getText().equals("Введите номер действительной кредитной карты"));
    }
}
