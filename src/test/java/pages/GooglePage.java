package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GooglePage extends BasePage {

    //********* Variables Definition *********
    private static WebDriver driver;
    private static final String GOOGLE_HOME_URL = "https://www.google.com";
    private static final By BY_SEARCH_FIELD = By.name("q");

    //********* Web Elements - Definition *********
    @FindBy(name = "q")
    private WebElement txtSearchField;

    @FindBy(name = "btnK")
    private WebElement btnGoogleSearch;

    @FindBy(id = "resultStats")
    private WebElement lblResultStats;

    @FindBy(xpath = "//h3/div")
    private WebElement lnkFirstResult;

    @FindBy(xpath = "//div[2]/div/span")
    private WebElement lblFirstSuggestion;

    //********* Constructor *********
    public GooglePage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    public void goToHomePage() {
        getDriver().navigate().to(GOOGLE_HOME_URL);
    }

    public void setTxtSearchField(String strTextToSearch) throws Exception{
        txtSearchField.clear();
        txtSearchField.sendKeys(strTextToSearch);
        Thread.sleep(2000);
    }

    public void clickBtnGoogleSearch(){
        try{
            btnGoogleSearch.click();
        }catch (NoSuchElementException e){
            Actions builder = new Actions(driver);
            WebElement element = btnGoogleSearch;
            builder.moveToElement(element).build().perform();
            element.click();
        }catch (Exception e){
            WebElement element = driver.findElement(By.name("btnK"));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
        }
    }

    public String getLblResultStats(){
        return this.lblResultStats.getText();
    }

    public String getFirstResult(){
        return this.lnkFirstResult.getText();
    }

    public void clickFirstResult(){
        this.lnkFirstResult.click();
    }

    public void clickFirstSuggestion(){
        this.lblFirstSuggestion.click();
    }

    public void getPageResults() {
        assertTrue("Google results was not displayed", lblResultStats.isDisplayed());
    }

    public void validateFirstResult(String strExpected) {
        assertEquals(strExpected, getFirstResult());
    }

    public void goToFirstResult() throws Exception{
        this.clickFirstResult();
        Thread.sleep(1000);
    }

    public void validateExpectedPage(String strExpectedPage){
        assertEquals(strExpectedPage, driver.getTitle());
    }
}
