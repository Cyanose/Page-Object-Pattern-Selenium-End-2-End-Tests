package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultsPage extends HomePage{
    @FindBy(css = ".btn-default")
    private WebElement backToCharacteristicsBtn;

    @FindBy(css=".btn-danger")
    private WebElement deleteResultsSampleBtn;

    @FindBy(css=".btn-success")
    private WebElement addResultsSampleBtn;

    public ResultsPage(WebDriver driver){
        super(driver);
    }

    public CreateResultsPage addNewResultSample(){
        addResultsSampleBtn.click();
        return new CreateResultsPage(driver);
    }

    public CharacteristicsPage goBackToCharacteristics(){
        backToCharacteristicsBtn.click();
        return new CharacteristicsPage(driver);
    }
}
