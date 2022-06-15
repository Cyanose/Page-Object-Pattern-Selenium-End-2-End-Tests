package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateResultsPage extends HomePage{

    @FindBy(id = "Sample")
    private WebElement nameInputField;

    @FindBy(id = "Values")
    private WebElement resultsInputField;

    @FindBy(css="input[type='submit']")
    private WebElement createBtn;

    @FindBy(css = "button.btn-default")
    private WebElement cancelBtn;

    public CreateResultsPage(WebDriver driver){
        super(driver);
    }

    public CreateResultsPage typeName(String sampleName){
        nameInputField.clear();
        nameInputField.sendKeys(sampleName);
        return this;
    }

    public CreateResultsPage typeResults(String results){
        resultsInputField.clear();
        resultsInputField.sendKeys(results);
        return this;
    }

    public ResultsPage submitCreate(){
        createBtn.click();
        return new ResultsPage(driver);
    }

    public ResultsPage cancelResultCreation(){
        cancelBtn.click();
        return new ResultsPage(driver);
    }
}
