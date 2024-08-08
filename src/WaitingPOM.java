import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitingPOM {
    protected WebDriver driver;

    By enableShowAlertButtonBy = By.xpath("//button[@data-testid='enableShowAlertButton']");
    By showAlertButtonBy = By.xpath("//button[@data-testid='ShowAlertButton']");
    By accordionItem1By = By.xpath("//div[@class='accordion']/div[1]");
    By accordionItem2By = By.xpath("//div[@class='accordion']/div[2]");
    By accordionItem3By = By.xpath("//div[@class='accordion']/div[3]");
    By accodrion1TextFieldBy = By.xpath("//input[@id='accordion1Text']");
    By accodrion2TextFieldBy = By.xpath("//input[@id='accordion2Text']");
    By accodrion3ButtonBy = By.xpath("//button[@name='accordionMessageButton']");

    public WaitingPOM(WebDriver driver){
        this.driver = driver;
    }

    public void enableAndShowAlert(){
        driver.findElement(enableShowAlertButtonBy).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> driver.findElement(showAlertButtonBy).isEnabled());
        driver.findElement(showAlertButtonBy).click();
    }
    public void enterAccordionText(String accordionText1, String accordionText2){
        driver.findElement(accordionItem1By).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d -> driver.findElement(accodrion1TextFieldBy).isDisplayed());
        driver.findElement(accodrion1TextFieldBy).sendKeys(accordionText1);
        driver.findElement(accordionItem2By).click();
        wait.until(d -> driver.findElement(accodrion2TextFieldBy).isDisplayed());
        driver.findElement(accodrion2TextFieldBy).sendKeys(accordionText2);
    }
    public void clickAccordionButton(){
        driver.findElement(accordionItem3By).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d -> driver.findElement(accodrion3ButtonBy).isDisplayed());
        driver.findElement(accodrion3ButtonBy).click();
    }
}
