package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtils;

public class PublicBusPage {

    CommonUtils utility = new CommonUtils();
    WebDriver driver;


    public PublicBusPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id="bus_timer_start")
    WebElement busBattleStart;

    public void clickStartBusBattle(){
        utility.clickElement(driver, busBattleStart);
    }

    @FindBy(id="bus_answer_2")
    public WebElement validBusAnswer;

    public void clickValidBusAnswer(){
        utility.clickElement(driver, validBusAnswer);
    }

    @FindBy(id="bus_answer_1")
    public WebElement inValidBusAnswer;

    public void clickInValidBusAnswer(){
        utility.clickElement(driver, inValidBusAnswer);
    }

    @FindBy(id="leaderboard_link")
    WebElement leaderBoardLink;

    public void clickCheckFinalScore(){
        utility.clickElement(driver, leaderBoardLink);
    }
}
