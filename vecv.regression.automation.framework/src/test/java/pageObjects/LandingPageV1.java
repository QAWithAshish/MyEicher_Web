package pageObjects;

import logger.LOG;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.CommonUtils;
import utilities.DriverUtils;

public class LandingPageV1 extends BasePage{

    DriverUtils utility = new DriverUtils(driver);

    @FindBy(xpath = "//input[@type='text']")
    public WebElement searchBar;

    @FindBy(css = "mat-option[tabindex='0'] span")
    public WebElement userOptionsBM;

    @FindBy(xpath = "//div[@class='dropdown']")
    WebElement OurServices;

    @FindBy(xpath = "//a[contains(text(),'Track')]")
    @CacheLookup
    public WebElement track;

    @FindBy(xpath = "//*[text()='Moving']")
    @CacheLookup
    public WebElement movingTab;

    @FindBy(xpath = "//*[text()='Idling']")
    @CacheLookup
    public WebElement idlingTab;

    @FindBy(xpath = "//*[text()='Stopped']")
    @CacheLookup
    public WebElement stoppedTab;

    @FindBy(xpath = "//*[text()='Not Reachable']")
    @CacheLookup
    public WebElement notReachableTab;

    @FindBy(xpath = "(//div[contains(@class,'headerDiv')]/p[@class='count'])[2]")
    public WebElement movingTabCount;

    @FindBy(xpath = "(//div[contains(@class,'headerDiv')]/p[@class='count'])[3]")
    public WebElement idlingTabCount;

    @FindBy(xpath = "(//div[contains(@class,'headerDiv')]/p[@class='count'])[4]")
    public WebElement stoppedTabCount;

    @FindBy(xpath = "(//div[contains(@class,'headerDiv')]/p[@class='count'])[5]")
    public WebElement notReachableTabCount;

    public LandingPageV1(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LandingPageV1 selectUserForFleet(String customer) {
        CommonUtils.sleep(3);
        Assert.assertTrue(iselementVisible(searchBar, 30), "Search bar not Visible");
        utility.clear(searchBar);
        CommonUtils.sleep(1);
        LOG.Reporter("Clear the search bar for user");
        int x = recall(searchBar, customer);
        return this;
    }

    public int recall(WebElement element, String text) {
        Boolean flag = false;
        sendkeysForAutoSuggestions(element, text);
        try {
            CommonUtils.sleep(1);
            flag = utility.iselementVisible(userOptionsBM);

        } catch (Exception e) {

        }
        if (flag) {
            utility.highlightOnly(userOptionsBM, "yellow");
            String customerName = userOptionsBM.getText();
            userOptionsBM.click();
            LOG.Reporter("User is selected : " + customerName);
            return 1;
        } else {
            try {
                utility.clear(element);
                CommonUtils.sleep(1);
                LOG.Reporter("Clear the search bar for user");
            } catch (Exception e) {

            }
        }
        return recall(element, text);
    }

    public LandingPageV1 selectFleetTracking() {
        CommonUtils.sleep(4);
        Assert.assertTrue(utility.iselementVisible(OurServices), "fleetTrack not Visible");
        OurServices.click();
        LOG.Reporter("Clicking on  Fleet Track");
        CommonUtils.sleep(2);
        Assert.assertTrue(iselementVisible(track, second10TimeOut), "Track not Visible");
        track.click();
        LOG.Reporter("Clicking on Track");
        return this;
    }

    public LandingPageV1 verifyAllTabs() {
        CommonUtils.sleep(1);
        utility.iselementVisible(movingTab);
        Assert.assertTrue(iselementVisible(movingTab, second10TimeOut), "Moving tab not Visible");
        LOG.Reporter("Moving tab is displayed and count is : " + movingTabCount.getText());
        CommonUtils.sleep(1);
        utility.iselementVisible(idlingTab);
        Assert.assertTrue(iselementVisible(idlingTab, second10TimeOut), "Idling tab not Visible");
        LOG.Reporter("Idling tab is displayed and count is : " + idlingTabCount.getText());
        CommonUtils.sleep(1);
        utility.iselementVisible(stoppedTab);
        Assert.assertTrue(iselementVisible(stoppedTab, second10TimeOut), "StoppedTab tab not Visible");
        LOG.Reporter("Stopped tab is displayed and count is : " + stoppedTabCount.getText());
        CommonUtils.sleep(1);
        utility.iselementVisible(notReachableTab);
        Assert.assertTrue(iselementVisible(notReachableTab, second10TimeOut), "Not Reachable tab not Visible");
        LOG.Reporter("Not Reachable tab is displayed and count is : " + notReachableTabCount.getText());
        return this;
    }
}



