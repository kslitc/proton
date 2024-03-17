package library;

import controller.ProtonController;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test(groups = {"Proton"})
public class ProtonLibraryTest {
    private static final Logger logger = LogManager.getLogger();
    ProtonController protonController = new ProtonController();
    List<String> defaultPrices = new ArrayList<>();

    /**
     * Constructor to set up the basic config
     */
    public ProtonLibraryTest() {
        protonController.setUp();
    }

    /**
     * Close browser window and end session
     */
    @Test
    @AfterClass(alwaysRun = true)
    @Then("^browser window closed$")
    public void tearDown() {
        protonController.tearDown();
    }

    /**
     * Go to defined url
     *
     * @param url url to get access to
     */
    @Test
    @When("^user goes to \"([^\"]*)\"$")
    public void goToUrl(String url) {
        logger.info("Go to " + url);
        protonController.goToUrl(url);
    }

    /**
     * Click on the specified element
     *
     * @param element element to click on
     */
    @Test
    @When("^user clicks on \"([^\"]*)\"$")
    public void click(String element) {
        logger.info("Click " + element);
        protonController.click(element);
    }

    /**
     * Select the specified element for a specific option
     *
     * @param element element to select
     * @param type    option to be selected
     */
    @Test
    @When("^user selects \"([^\"]*)\" \"([^\"]*)\"$")
    public void select(String element, String type) {
        logger.info("Select " + type + " with " + element);
        protonController.select(element, type);
    }

    /**
     * Get current plan prices
     */
    @Test
    @When("^user writes down default plan prices$")
    public void getCurrentPlanPrices() {
        logger.info("Get current plan prices");
        defaultPrices = protonController.getCurrentPlanPrices();
    }

    /**
     * Get current url and verify that matches with the expected one
     *
     * @param text expected url
     */
    @Test
    @Then("^url is \"([^\"]*)\"$")
    public void assertUrlIs(String text) {
        logger.info("Url is " + text);
        Assert.assertTrue(protonController.getUrl().contains(text), "Url has an unexpected value: " +
                protonController.getUrl());
    }

    /**
     * Scan the webpage to look for the logo and verify that webpage has some content
     *
     * @param logo logo to look for
     */
    @Test
    @Then("^logo \"([^\"]*)\" is visible$")
    public void assertContentIsNotEmpty(String logo) {
        logger.info("Content is not empty");
        Assert.assertTrue(protonController.getLogo(logo), "Url has no content");
    }

    /**
     * Plans have the selected currency
     *
     * @param symbol currency to look for
     * @param plan   selected plan
     */
    @Test
    @Then("^plans are in \"([^\"]*)\" for \"([^\"]*)\"$")
    public void assertPlansInCurrency(String symbol, String plan) {
        logger.info("Plans are in " + symbol + " for " + plan);
        Assert.assertTrue(protonController.getCurrency(symbol, plan) >= 2, "Plans have an unexpected "
                + "currency");
    }

    /**
     * Plan prices are different from the default ones
     */
    @Test
    @Then("^plan prices are different$")
    public void assertPlanPricesDifferent() {
        logger.info("Plan prices are different from the default ones");
        protonController.getCurrentPlanPrices().removeAll(defaultPrices);
        Assert.assertTrue(protonController.getCurrentPlanPrices().size() > 0, "Plans have no "
                + "different price");
    }
}
