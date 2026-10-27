package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

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
    public List<WebElement> getQuestionButtons() {
        return driver.findElements(questionsLocator);
    }


    // список всех панелей ответов
    public List<WebElement> getAnswers() {
        return driver.findElements(answersLocator);
    }

    public void clickQuestionButtons(int index) {
        List<WebElement> questionButtons = getQuestionButtons();
        questionButtons.get(index).click();
    }
    // получение текста ответа по индексу
    public String getAnswerText(int index) {
        List<WebElement> answerPanels = getAnswers();
        wait.until(ExpectedConditions.visibilityOf(answerPanels.get(index)));
        return answerPanels.get(index).getText();
    }
}