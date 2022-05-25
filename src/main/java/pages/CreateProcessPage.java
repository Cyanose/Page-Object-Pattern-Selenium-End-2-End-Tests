package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateProcessPage extends HomePage{
    @FindBy(css = "#Name")
    private WebElement processNameBtn;
    @FindBy(css = "#Description")
    private WebElement processDescriptionBtn;
    @FindBy(css = "#Notes")
    private WebElement processNotesBtn;
    @FindBy(css = ".btn.btn-success")
    private WebElement createBtn;
    @FindBy(css = ".btn[type='reset']")
    private WebElement resetBtn;
    @FindBy(css = ".btn:nth-child(1)")
    private WebElement backBtn;

    public CreateProcessPage(WebDriver driver){
        super(driver);
    }

    public CreateProcessPage typeProcessName(String processName){
        processNameBtn.sendKeys(processName);
        return this;
    }
    public CreateProcessPage typeProcesDescription(String description){
        processDescriptionBtn.sendKeys(description);
        return this;
    }
    public CreateProcessPage typeProcessNotes(String notes){
        processNotesBtn.sendKeys(notes);
        return this;
    }
    public ProcessesPage submitProcessCreation(){
        createBtn.click();
        return new ProcessesPage(driver);
    }
    public CreateProcessPage resetProcessCreation(){
        resetBtn.click();
        return this;
    }
    public ProcessesPage goBackToProcessesList(){
        backBtn.click();
        return new ProcessesPage(driver);
    }
}