package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.LandingPageV1;
import pageObjects.LoginPage;

import static reporter.ExtentTestManager.startTest;

public class VehicleTrackTestV1 extends SuiteSetup {


    LoginPage page;
    LandingPageV1 landing;

    @Test(priority = 1)
    @Parameters({"customer", "user", "password"})
    public void testToVerifyAllTabs(String customer, String user, String password) {
        startTest("Validate All Tabs",
                "Track landing page should have 4 tabs i.e. Moving, Idling, Stopped & Not Reachable.");
        page = new LoginPage(getDriver());
        page.isLandingScreenAppear().navigateToOrganizationSignIn().userLogin(user, password);
        landing = new LandingPageV1(getDriver());
        landing.selectUserForFleet(customer).selectFleetTracking().verifyAllTabs();
    }
}


