package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class CreateCharacteristicPage extends HomePage{

    @FindBy(id = "ProjectId")
    private WebElement projectSelect;

    @FindBy(id = "Name")
    private WebElement characteristicName;

    @FindBy(id="LowerSpecificationLimit")
    private WebElement lowerLimit;

    @FindBy(id="UpperSpecificationLimit")
    private WebElement upperLimit;

    @FindBy(id="HistogramBinCount")
    private WebElement histBinCount;

    @FindBy(css = ".btn-success")
    private WebElement createBtn;

    @FindBy(css = ".field-validation-error")
    private List<WebElement> errors;

    public CreateCharacteristicPage(WebDriver driver){
        super(driver);
    }
    public CreateCharacteristicPage typeCharacteristicName(String name){
        characteristicName.clear();
        characteristicName.sendKeys(name);
        return this;
    }

    public CreateCharacteristicPage typeLowerSpecLimit(String number){
        lowerLimit.clear();
        lowerLimit.sendKeys(number);
        return this;
    }
    public CreateCharacteristicPage typeUpperSpecLimit(String number){
        upperLimit.clear();
        upperLimit.sendKeys(number);
        return this;
    }
    public CreateCharacteristicPage typeHistBinCount(String number){
        histBinCount.clear();
        histBinCount.sendKeys(number);
        return this;
    }
    public CharacteristicsPage submitCreate(){
        createBtn.click();
        return new CharacteristicsPage(driver);
    }

    public CreateCharacteristicPage submitCreateWithFailure(){
        createBtn.click();
        return this;
    }

    public CreateCharacteristicPage selectProcess(String processName){
        new Select(projectSelect).selectByVisibleText(processName);
        return this;
    }
    public CreateCharacteristicPage assertCharacteristicError(String expError){
        boolean doesErrorExist = false;
        for (WebElement error:errors) {
            if(error.getText().equals(expError)) {
                doesErrorExist = true;
                break;
            }
        }
        Assert.assertTrue(doesErrorExist,"Errors do not match");
        return this;
    }
}