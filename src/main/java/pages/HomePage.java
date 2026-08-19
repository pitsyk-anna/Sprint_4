package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    // Заголовок "Яндекс Самокат"
    private final By header = By.className("Header_LogoYandex__3TSOI");

    // Кнопка "Заказать" вверху страницы
    private final By OrderInHeader = By.xpath("//button[contains(text(), 'Заказать')]");

    // Статус заказа
    private final By statusOrder = By.className("Header_Link__1TAG7");

    // Поле "Введите номер заказа"
    private final By inputOrderNumber = By.className("Input_InputContainer__3NykH");

    // Кнопка Go!
    private final By buttonGo = By.xpath(".//button[text()='Go!']");

    // Кнопка "Заказать" внизу страницы
    private final By OrderInDown = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    // Кнопка принятия "Cookie"
    private final By CookieButton = By.id("rcc-confirm-button");

    // Методы
    public void createFromHeader(){
        driver.findElement(OrderInHeader).click(); // кнопка заказать Вверху страницы
    }

    public void  createFromDown(){
        driver.findElement(OrderInDown).click(); // кнопка Заказать внизу страницы
    }

    public void acceptCookie(){
        driver.findElement(CookieButton).click(); // принятие куки
}
}

