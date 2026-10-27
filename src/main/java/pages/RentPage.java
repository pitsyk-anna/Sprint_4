package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RentPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By rentalDate = By.xpath("//input[@placeholder='* Когда привезти самокат']"); // поле Когда привезти самокат
    private final By rentalTime = By.cssSelector(".Dropdown-control"); // поле Срок аренды
    private final By rentalDay = By.xpath("//div[contains(@class, 'Dropdown-menu')]//div[text()='сутки']");// список выбора срока аренды
    private final By checkBoxBlackScooter = By.id("black"); // черный цвет самоката
    private final By checkBoxGreyScooter = By.id("grey"); // серый цвет самоката
    private final By commentCourier = By.xpath("//input[@placeholder='Комментарий для курьера']"); // комментарий для курьера
    private final By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']"); // кнопка Заказать
    private final By modalOrder = By.xpath("//div[contains(@class, 'Order_Modal__YZ-d3')]"); // модальное окно Хотите оформить заказ
    private final By noButton = By.xpath("//button[contains(text(), 'Нет')]"); // кнопка Нет в модальном окне
    private final By yesButton = By.xpath("//button[contains(text(), 'Да')]"); // кнопка Да в модальном окне
    private final By modalWindow = By.xpath("//div[contains(@class, 'Order_ModalHeader')]"); // модальное окно Заказ оформлен
    private final By statusButton = By.xpath("//button[contains(text(), 'Посмотреть статус')]"); // кнопка Посмотреть статус

    public RentPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    // ввести дату заказа Когда привезти самокат
    public RentPage setRentalDate(String date) {
        driver.findElement(rentalDate).sendKeys(date);
        driver.findElement(rentalDate).sendKeys(Keys.ENTER);
        return this;
    }

    // выбрать срок аренды
    public RentPage selectRentalPeriodDays() {
        driver.findElement(rentalTime).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rentalDay));
        driver.findElement(rentalDay).click();
        return this;
        }
    //выбрать черный цвет самоката
    public RentPage checkBlackScooter() {
        driver.findElement(checkBoxBlackScooter).click();
        return this;
    }
    // выбрать серый цвет самоката
    public RentPage checkGreyScooter() {
        driver.findElement(checkBoxGreyScooter).click();
        return this;
    }
    // ввести комментрий для курьера
    public RentPage setComment(String comment) {
        driver.findElement(commentCourier).sendKeys(comment);
        return this;
    }
    // нажать на кнопку Заказать
    public RentPage clickOrderButton() {
        driver.findElement(orderButton).click();
        return this;
    }
    // нажать на кнопку Нет
    public RentPage confirmOrderNoInModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalOrder));
        wait.until(ExpectedConditions.elementToBeClickable(noButton)).click();
        return this;
    }
    // нажать на кнопку Да
    public RentPage confirmOrderYesInModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalOrder));
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
        return this;
    }
    // появление окна об успешном создании заказа
    public String getOrderSuccessText() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modalWindow));
        return modal.getText();
    }
}

