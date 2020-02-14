package lesson8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;

public class Lesson8 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:/installation/chromedriver.exe");//
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.booking.com");
        WebElement citySearch = driver.findElement(By.xpath("//input[@type=\"search\"]"));
        citySearch.sendKeys("Москва");
        citySearch.submit();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> listOfHotels = driver.findElements(By.xpath("//a[@class=\"hotel_name_link url\"]"));
        String hotelsAvailability = listOfHotels.isEmpty()? "There are no hotels in Moscow on the default date" :
                "There are hotels in Moscow on the default date";
        System.out.println(hotelsAvailability);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement checkBoxWonderful = driver.findElement(By.xpath("//a[@data-id=\"review_score-90\"]"));
        checkBoxWonderful.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement firstHotel = driver.findElement(By.xpath("(//a[@class=\"hotel_name_link url\"])[1]"));
        firstHotel.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle: windowHandles) {
            driver.switchTo().window(handle);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement ratingBadge = driver.findElement(By.xpath("(//div[@class=\"bui-review-score__badge\"])[1]"));
        double rating = Double.parseDouble(ratingBadge.getText().replaceAll(",","."));
        String ratingCheck = rating >= 9.0? "The rating of the first hotel on the list is greater than or equal to 9" :
                "The rating of the first hotel on the list is less than 9";
        System.out.println(ratingCheck);
    }
}
