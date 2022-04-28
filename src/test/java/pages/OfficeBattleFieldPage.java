package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtils;

public class OfficeBattleFieldPage {
    CommonUtils utility = new CommonUtils();
    WebDriver driver;


    public OfficeBattleFieldPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id="office_answer_2")
    public WebElement officeAnswerValid;

    @FindBy(id="office_answer_1")
    public WebElement officeAnswerInValid;

    public void clickValidOfficeAnswer(){
        utility.clickElement(driver, officeAnswerValid);
    }

    public void clickInValidOfficeAnswer(){
        utility.clickElement(driver, officeAnswerInValid);
    }

    @FindBy(id="close_modal_btn_1")
    WebElement tryNextBattle;

    public void clickTryNextBattle(){
        utility.clickElement(driver, tryNextBattle);
    }
}
