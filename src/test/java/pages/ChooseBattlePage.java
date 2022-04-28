package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtils;

public class ChooseBattlePage {
    CommonUtils utility = new CommonUtils();
    WebDriver driver;


    public ChooseBattlePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id="welcome_text")
    WebElement welcomeText;

    public String getWelcomeText(){
        return utility.getTextFromElement(driver, welcomeText);
    }

    @FindBy(id="office")
    WebElement goToOfficeLink;

    public void clickGoToOfficeLink(){
        utility.clickElement(driver, goToOfficeLink);
    }

    @FindBy(xpath = "//img[@id='officeWorker']/following-sibling::h5")
    WebElement enterOfficeBanner;

    public String getEnterOfficeBanner(){
        return utility.getTextFromElement(driver, enterOfficeBanner);
    }

    @FindBy(xpath = "//button[@id='start']")
    WebElement startOfficeBtn;

    public void clickStartOfficeLink(){
        utility.clickElement(driver, startOfficeBtn);
    }
}
