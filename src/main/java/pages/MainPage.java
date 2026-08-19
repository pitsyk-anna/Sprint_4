package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    private final By faq = By.className("Home_FAQ__3uVm4"); // блок FAQ
    private final By questionsLocator = By.className("accordion__button"); // вопросы
    private final By answersLocator = By.className("accordion__panel"); // панели ответов

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // ожидание
    }

    public void openSite() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookie(); //принятие куки
    }

    //скролл до блока FAQ
    public void scrollToFaq() {
        WebElement element = driver.findElement(faq);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // список всех кнопок вопросов
    public List<WebElement>getQuestionButtons() {
        return driver.findElements(questionsLocator);
    }


    // список всех панелей ответов
    public List<WebElement>getAnswers() {
        return driver.findElements(answersLocator);
    }
}