import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactPOM {

    protected WebDriver driver;
    By firstNameBy = By.id("firstName");
    By lastNameBy = By.id("lastName");
    By stateBy = By.id("addressState");
    By zipBy = By.id("addressZip");
    By messageBy = By.id("messageBox");
    By cityBy = By.xpath("//input[@placeholder='City']");
    By termsBy = By.xpath("//input[@type='checkbox']");
    By submitBy = By.xpath("//button[@type='submit']");
    By thanksBy = By.xpath("//h2[contains(text(),'Thank')]");
    By firstNameValidResponseBy = By.xpath("//*[@id='firstName']/../div[@class='valid-feedback']");
    By termsInvalidResponseBy = By.xpath("//input[@type='checkbox']/../div[@class='invalid-feedback']");

    public ContactPOM(WebDriver driver){
        this.driver = driver;
    }

    public void enterName(String firstName, String lastName){
        driver.findElement(firstNameBy).sendKeys(firstName);
        driver.findElement(lastNameBy).sendKeys(lastName);
    }
    public void enterCity(String city){
        driver.findElement(cityBy).sendKeys(city);
    }
    public void enterState(Integer state){
        Select dropdown = new Select(driver.findElement(stateBy));
        dropdown.selectByIndex(state);
    }
    public void enterZip(String zip){
        driver.findElement(zipBy).sendKeys(zip);
    }
    public void enterMessage(String message){
        driver.findElement(messageBy).sendKeys(message);
    }
    public void agreeTerms(){
        driver.findElement(termsBy).click();
    }
    public void clickSubmit(){
        driver.findElement(submitBy).click();
    }
    public boolean isUsernameInThanksMessage(String username){
        try {
            String usernameXpath = "//*[text()='"+username+"']";
            driver.findElement(By.xpath(usernameXpath)).isDisplayed();
        }
        catch(Exception e){
            throw e;
        }
        return true;
    }


}
