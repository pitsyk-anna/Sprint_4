package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private final WebDriver driver;

    private final By inputName = By.xpath("//input[@placeholder ='* Имя']"); // поле Имя
    private final By inputLastName = By.xpath("//input[@placeholder ='* Фамилия']"); // поле Фамилия
    private final By inputAddress = By.xpath("//input[@placeholder ='* Адрес: куда привезти заказ']"); // поле Адрес
    private final By inputSubway = By.xpath("//input[@placeholder ='* Станция метро']"); // поле Метро
    private final By inputPhone = By.xpath("//input[@placeholder ='* Телефон: на него позвонит курьер']"); // поле Номер телефона
    private final By nextButton = By.xpath("//button[contains(text(), 'Далее')]"); // кнопка Далее
    private final By subwayDropdown = By.cssSelector(".select-search__select"); // дропдаун с названиями станций


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUserName(String userName) {
        driver.findElement(inputName).sendKeys(userName); // заполнение поля Имя
    }

    public void setLastName(String lastName) {
        driver.findElement(inputLastName).sendKeys(lastName); // заполнения поля Фамилия
    }

    public void setAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address); // заполнение поля Адрес
    }

    public OrderPage selectSubwayStation(String stationName) {
        driver.findElement(inputSubway).click(); // клик по полю Метро
        driver.findElement(inputSubway).sendKeys(stationName);// ввод станции
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); //ожидание появления списка станций
        wait.until(ExpectedConditions.visibilityOfElementLocated(subwayDropdown));
        By optionLocator = By.xpath("//*[contains(text(), '" + stationName + "')]");
        driver.findElement(optionLocator).click(); // поиск по элементу и клик по нему
        return this;
    }

    public void setPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);// заполнение поля Телефон
    }

    public void clickNextButton() {
    driver.findElement(nextButton).click(); // клик по кнопке Далее
    }

}
