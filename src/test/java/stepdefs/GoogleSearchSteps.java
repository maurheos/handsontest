package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.GooglePage;

public class GoogleSearchSteps {
    private WebDriver driver;
    private GooglePage objGooglePage;

    @Before(value = "@web", order = 1)
    public void initWebDriver() throws Throwable {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @Before(value = "@google", order = 10)
    public void initGooglePage() throws Throwable {
        objGooglePage = new GooglePage(driver);
    }

    @Given("I'm on the homepage")
    public void iMOnTheHomepage() {
        objGooglePage.goToHomePage();
    }

    @When("I type \"([^\"]*)\" into the search field")
    public void iTypeIntoTheSearchField(String strTextToSearch) throws Exception {
        objGooglePage.setTxtSearchField(strTextToSearch);
    }

    @And("I click the Google Search button")
    public void iClickTheGoogleSearchButton() {
        objGooglePage.clickBtnGoogleSearch();
    }

    @Then("I go to the search results page")
    public void iGoToTheSearchResultsPage() {
        objGooglePage.getPageResults();
    }

    @And("the first result is \"([^\"]*)\"")
    public void theFirstResultIs(String strFirstResult) {
        objGooglePage.validateFirstResult(strFirstResult);
    }

    @When("I click on the first result link")
    public void iClickOnTheFirstResultLink() throws Exception {
        objGooglePage.goToFirstResult();
    }

    @Then("I go to the \"([^\"]*)\" page")
    public void iGoToThePage(String strExpectedTitle) {
        objGooglePage.validateExpectedPage(strExpectedTitle);
    }

    @And("the suggestions list is displayed")
    public void theSuggestionsListIsDisplayed() {
    }

    @And("I click on the first suggestion in the list")
    public void iClickOnTheFirstSuggestionInTheList() {
        objGooglePage.clickFirstSuggestion();
    }

    @After(value = "@web")
    public void tearDown() throws Throwable {
        driver.quit();
    }
}
