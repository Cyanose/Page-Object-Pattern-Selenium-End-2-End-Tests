package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class LoginPage {
    protected WebDriver driver;

    @FindBy(id = "Email")
    private WebElement emailTxt;

    @FindBy(id = "Password")
    private WebElement passwordTxt;

    @FindBy(css = "button[type=submit]")
    private WebElement loginBtn;

    @FindBy(css = ".validation-summary-errors>ul>li")
    public List<WebElement> loginErrors;

    @FindBy(css="a[href*=Register]")
    public WebElement registerLink;

    @FindBy(css = "form>h1")
    public WebElement header;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public LoginPage typeEmail(String email){
        emailTxt.clear();
        emailTxt.sendKeys(email);
        return this;
    }

    public LoginPage typePassword(String password) {
        passwordTxt.clear();
        passwordTxt.sendKeys(password);
        return this;
    }
    public CreateAccountPage goToRegisterPage(){
        registerLink.click();
        return new CreateAccountPage(driver);

    }

    public HomePage submitLogin(){
        loginBtn.click();
        return new HomePage(driver);
    }
    public LoginPage submitLoginWithFailure(){
        loginBtn.click();
        return this;
    }

    public LoginPage assertLoginErrorIsShown(String errorMsg){
        boolean doesErrorExist = false;
        for (int i=0;i<this.loginErrors.size();i++)
            if(this.loginErrors.get(i).getText().equals(errorMsg)) {
                doesErrorExist = true;
                break;
            }
        Assert.assertTrue(doesErrorExist,"Errors do not match");
        return this;
    }
    public LoginPage assertLoginPageHeader(){
        Assert.assertTrue(header.isDisplayed(),"Login Header is not displayed");
        return this;
    }
}