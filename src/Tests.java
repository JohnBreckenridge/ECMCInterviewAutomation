import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Tests {
    WebDriver driver;
    @Before
    public void initialize(){
        driver = new ChromeDriver();
        driver.get("https://interview-app-plum.vercel.app");
    }
    @After
    public void close() {
        driver.quit();
    }
    @Test
    public void submitContactRequest() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2)); //max duration of wait is 2 seconds
        ContactPOM contactPom = new ContactPOM(driver);
        HomePOM homePom = new HomePOM(driver);
        homePom.clickContactUs();

        wait.until(d -> driver.findElement(contactPom.firstNameBy).isDisplayed()); //ensures page is loaded before entering info

        contactPom.enterName("test", "testman");
        contactPom.enterCity("testCity");
        contactPom.enterState(23); //uses integer value of position of state in alphabetical order, Minnesota in this case
        contactPom.enterZip("12345");
        contactPom.enterMessage("Test Message");
        contactPom.agreeTerms();
        contactPom.clickSubmit();

        wait.until(d -> driver.findElement(contactPom.thanksBy).isDisplayed()); // success message is displayed on new page, ensure message loads before checking
        assertEquals("https://interview-app-plum.vercel.app/contact/test/success", driver.getCurrentUrl()); //verify we moved to new page
        assertTrue(contactPom.isUsernameInThanksMessage("test")); //verify user first name is in test message
    }
    @Test
    public void submitContactError() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2)); //max duration of wait is 2 seconds
        ContactPOM contactPom = new ContactPOM(driver);
        HomePOM homePom = new HomePOM(driver);
        homePom.clickContactUs();

        wait.until(d -> driver.findElement(contactPom.firstNameBy).isDisplayed()); //ensures page is loaded before entering info

        contactPom.enterName("test", "testman");
        contactPom.clickSubmit();

        assertTrue(driver.findElement(contactPom.firstNameValidResponseBy).isDisplayed());
        //assertTrue(driver.findElement(contactPom.lastNameValidResponseBy).isDisplayed());
        assertTrue(driver.findElement(contactPom.termsInvalidResponseBy).isDisplayed());
    }
    @Test
    public void waitPageAlert(){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //max duration of wait is 10 seconds
        WaitingPOM waitingPOM = new WaitingPOM(driver);//do I need to pass in driver on these? should test if I can remove it
        HomePOM homePom = new HomePOM(driver);
        homePom.clickWaiting();

        wait.until(d -> driver.findElement(waitingPOM.enableShowAlertButtonBy).isEnabled());

        waitingPOM.enableAndShowAlert();
        String alertText = driver.switchTo().alert().getText();
        assertEquals("False alarm!", alertText);
        driver.switchTo().alert().accept();

        waitingPOM.enterAccordionText("test","text");
        waitingPOM.clickAccordionButton();
        String accordionText = driver.switchTo().alert().getText();
        assertEquals("test text", accordionText);
        driver.switchTo().alert().accept();

    }
}
