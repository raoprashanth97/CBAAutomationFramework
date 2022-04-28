package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pages.LeaderBoardPage;

import java.util.List;

public class LeaderBoardTest extends BaseTest{

    LeaderBoardPage leaderBoardPage;

    @Test(priority=5)
    public void verifyScoreCard(ITestContext context){
        leaderBoardPage = new LeaderBoardPage(driver);
        //List<WebElement> tableRows = leaderBoardPage.returnScoreForUser().findElements(By.xpath("//tr"));
        String score = returnScoreOfUser(leaderBoardPage.returnScoreForUser(),
                context.getAttribute("username").toString());
        Assert.assertTrue(Integer.parseInt(score)>0);
    }

    public String returnScoreOfUser(WebElement table, String user){
        String score = null;

        List<WebElement> rows = table.findElements(By.xpath("//tr"));
        for(int i=0; i<rows.size(); i++){
            List<WebElement> columns = rows.get(i).findElements(By.xpath("//td"));
            if(columns.get(0).getText().contentEquals(user)){
                score = columns.get(1).getText();
                break;
            }
        }
        return score;
    }
}
