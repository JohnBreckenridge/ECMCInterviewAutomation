import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePOM {
    protected WebDriver driver;

    By contactUsBy = By.xpath("//a[@href='/contact']");
    By waitingBy = By.xpath("//a[@href='/waiting']");

    public HomePOM (WebDriver driver) {
        this.driver = driver;
    }
    public void clickContactUs () {
        driver.findElement(contactUsBy).click();
    }
    public void clickWaiting () {
        driver.findElement(waitingBy).click();
    }

}
