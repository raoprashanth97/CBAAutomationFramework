package tests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pages.ChooseBattlePage;
import pages.OfficeBattleFieldPage;
import pages.PublicBusPage;

public class PublicBusTest extends BaseTest{

    ChooseBattlePage chooseBattle;
    PublicBusPage publicBusPage;


    @Test(priority=2)
    public void choosePublicBusAndPerformChallenge(ITestContext context) throws InterruptedException {
        //loginTest.userCreationWithUserNameAndPassword(context);
        //loginTest.loginWithUserNameAndPassword(context);
        publicBusPage = new PublicBusPage(driver);
        //publicBusPage.clickGoToOfficeLink();
        publicBusPage.clickStartBusBattle();

        String validAnswer = publicBusPage.validBusAnswer.getText().trim();
        Assert.assertEquals("Move to another seat immediately and report it to the driver!",
                validAnswer);
        String inValidAnswer = publicBusPage.inValidBusAnswer.getText().trim();
        Assert.assertEquals("Use your superhero punch power and hit them",
                inValidAnswer);
        publicBusPage.clickInValidBusAnswer();
    }


}
