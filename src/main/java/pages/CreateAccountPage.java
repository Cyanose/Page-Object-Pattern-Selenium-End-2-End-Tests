package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CreateAccountPage {
    protected WebDriver driver;

    @FindBy(id = "Email")
    private WebElement emailTxt;

    @FindBy(id = "Password")
    private WebElement passwordTxt;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPassword;

    @FindBy(css = "button[type=submit]")
    private WebElement registerBtn;

    @FindBy(css = ".validation-summary-errors>ul>li")
    public List<WebElement> registerErrors;

    public CreateAccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CreateAccountPage typeEmail(String email){
        emailTxt.clear();
        emailTxt.sendKeys(email);
        return this;
    }

    public CreateAccountPage typePassword(String pass){
        passwordTxt.clear();
        passwordTxt.sendKeys(pass);
        return this;
    }

    public CreateAccountPage typeConfirmPassword(String pass){
        confirmPassword.clear();
        confirmPassword.sendKeys(pass);
        return this;
    }
    public HomePage submitRegister(){
        registerBtn.click();
        return new HomePage(driver);
    }

    public CreateAccountPage submitRegisterWithFailure(){
        registerBtn.click();
        return this;
    }

    public CreateAccountPage assertRegisterErrorIsShown(String errorMsg){
        boolean doesErrorExist = false;
        for (int i=0;i<this.registerErrors.size();i++)
            if(this.registerErrors.get(i).getText().equals(errorMsg)) {
                doesErrorExist = true;
                break;
            }
        Assert.assertTrue(doesErrorExist,"Errors do not match");
        return this;
    }
}