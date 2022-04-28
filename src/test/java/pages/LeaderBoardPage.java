package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtils;

public class LeaderBoardPage {

    CommonUtils utility = new CommonUtils();
    WebDriver driver;


    public LeaderBoardPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//p[@id='showData']//table")
    WebElement scoreTable;

    public WebElement returnScoreForUser(){
        return scoreTable;
    }
}
