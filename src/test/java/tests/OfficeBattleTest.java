package tests;

import com.beust.jcommander.Parameter;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pages.ChooseBattlePage;
import pages.OfficeBattleFieldPage;

public class OfficeBattleTest extends BaseTest{

    ChooseBattlePage chooseBattle;
    OfficeBattleFieldPage officeBattle;
    LoginAndRegisterTest loginTest = new LoginAndRegisterTest();

    @Test(priority=2)
    public void chooseOfficeBattleAndPerformChallenge(ITestContext context) throws InterruptedException {
        //loginTest.userCreationWithUserNameAndPassword(context);
        //loginTest.loginWithUserNameAndPassword(context);
        chooseBattle = new ChooseBattlePage(driver);
        chooseBattle.clickGoToOfficeLink();
        chooseBattle.clickStartOfficeLink();

        officeBattle = new OfficeBattleFieldPage(driver);
        String validAnswer = officeBattle.officeAnswerValid.getText().trim();
        Assert.assertEquals("Use your superhero Social Distance, advise your Manager of the risk and that his partner should be seeking medical advice.",
                validAnswer);
        String inValidAnswer = officeBattle.officeAnswerInValid.getText().trim();
        Assert.assertEquals("Comfort your Manager, and assure him it is only natural to have a seasonal cold this time of the year.",
                inValidAnswer);
        officeBattle.clickValidOfficeAnswer();
        officeBattle.clickTryNextBattle();
    }
}
