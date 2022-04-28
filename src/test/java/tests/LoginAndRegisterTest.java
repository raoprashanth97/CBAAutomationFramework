package tests;


import org.junit.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginAndRegisterTest extends BaseTest {

    LoginPage loginPage;

    @Test(priority=0)
    public void userCreationWithUserNameAndPassword(ITestContext context) throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();
        String username = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        String password = "praob";
        loginPage.enterUserNameForRegistration(username);
        loginPage.enterPasswordForRegistration(password);
        loginPage.clickSignUpButton();
        String banner = loginPage.getLoginBanner();
        Assert.assertEquals("Login with your warrior name", banner);
        context.setAttribute("username", username);
        context.setAttribute("password", password);
    }

    @Test(dependsOnMethods = {"userCreationWithUserNameAndPassword"}, priority=1)
    public void loginWithUserNameAndPassword(ITestContext context) throws InterruptedException {
        loginPage = new LoginPage(driver);
        //loginPage.clickLoginButton();
        loginPage.enterUserName(context.getAttribute("username").toString());
        loginPage.enterPassword(context.getAttribute("password").toString());
        loginPage.clickLoginWarrior();
        loginPage.clickStartJourney();
        //Thread.sleep(5000);
        System.out.println("Page title: " + driver.getTitle());
        Assert.assertEquals("COVID-19 THE GAME", driver.getTitle());
    }
}
