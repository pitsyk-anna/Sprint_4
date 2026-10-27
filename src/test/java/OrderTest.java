import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.OrderPage;
import pages.RentPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    private HomePage homePage;
    private OrderPage orderPage;
    private RentPage rentPage;

    private final String name;
    private final String lastName;
    private final String address;
    private final String subway;
    private final String phone;
    private final String commentCourier;
    private final String orderButton;


    public OrderTest(String name, String lastName, String address, String subway, String phone, String commentCourier, String orderButton) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.subway = subway;
        this.phone = phone;
        this.commentCourier = commentCourier;
        this.orderButton = orderButton;
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);
        rentPage = new RentPage(driver, wait);
        homePage.acceptCookie(); // принимаем куки
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                // кнопка Заказать вверху страницы
                {"Анна", "Пицык", "Москва, Сиреневый бульвар 4", "Черкизовская", "+70001112113", "Первый заказ", "header"},
                // кнопка Заказать внизу страницы
                {"Лекси", "Кошечка", "Москава, Фиолетовый проспект 15", "Красногвардейская", "+71110001100", "Второй заказ", "down"}
        };
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
     public void testOrder() {
            if (orderButton.equals("header")) {
                homePage.createFromHeader(); // кнопка Заказать вверху страницы
            } else {
                homePage.createFromDown(); // кнопка Заказать внизу страницы
            }

            // заполнение полей на странице Для кого самокат
        orderPage.setUserName(name);
        orderPage.setLastName(lastName);
        orderPage.setAddress(address);
        orderPage.selectSubwayStation(subway);
        orderPage.setPhone(phone);

        orderPage.clickNextButton();

        // заполнение полей на странице Про аренду
        rentPage.setRentalDate("27.08.2026");
        rentPage.selectRentalPeriodDays();
        rentPage.checkGreyScooter();
        rentPage.setComment(commentCourier);
        rentPage.clickOrderButton();

        rentPage.confirmOrderYesInModal();

        String successText = rentPage.getOrderSuccessText();
        assertTrue("Заказ должен быть оформлен", successText.contains("Заказ оформлен"));
    }
}