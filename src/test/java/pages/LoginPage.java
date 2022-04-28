package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtils;

public class LoginPage {

    CommonUtils utility = new CommonUtils();
    WebDriver driver;


    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id="rego")
    WebElement registerBtn;

    @FindBy(id="login")
    WebElement loginBtn;

    public void clickLoginButton(){
        //loginBtn.click();
        utility.clickElement(driver, loginBtn);
    }

    public void clickRegisterButton(){
        registerBtn.click();
    }

    @FindBy(id="worrior_username")
    WebElement username;

    public void enterUserName(String user){
        username.clear();
        username.sendKeys(user);
    }

    @FindBy(id="worrior_pwd")
    WebElement password;

    public void enterPassword(String pwd){
        password.sendKeys(pwd);
    }

    @FindBy(id="warrior")
    WebElement loginWarrior;

    public void clickLoginWarrior(){
        //loginWarrior.click();
        utility.clickElement(driver, loginWarrior);
    }

    @FindBy(id="start")
    WebElement startJourney;

    public void clickStartJourney(){
        //startJourney.click();
        utility.clickElement(driver, startJourney);
    }

    @FindBy(id="close")
    WebElement cancelJourney;

    public void clickCancelJourney(){
        //cancelJourney.click();
        utility.clickElement(driver, cancelJourney);
    }

    @FindBy(id="uname")
    WebElement registerUserName;

    public void enterUserNameForRegistration(String user){
        registerUserName.sendKeys(user);
    }

    @FindBy(id="pwd")
    WebElement registerPassword1;

    @FindBy(id="psw-repeat")
    WebElement registerPassword2;

    public void enterPasswordForRegistration(String pwd){
        registerPassword1.sendKeys(pwd);
        registerPassword2.sendKeys(pwd);
    }

    @FindBy(id="signupbtn")
    WebElement signUpBtn;

    public void clickSignUpButton(){
        utility.clickElement(driver,signUpBtn);
    }

    @FindBy(xpath = "//div[@id='loginmodal']//h1")
    WebElement loginWarriorText;

    public String getLoginBanner(){
        return utility.getTextFromElement(driver, loginWarriorText);
    }

}
