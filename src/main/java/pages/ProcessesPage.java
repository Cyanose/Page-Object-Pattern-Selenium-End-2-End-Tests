package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class ProcessesPage extends HomePage {

    public ProcessesPage(WebDriver driver){
        super(driver);
    }
    @FindBy(css = ".title_left>h3")
    private WebElement header;
    @FindBy(css = ".btn.btn-success")
    private WebElement addNewProcessBtn;

    @FindBy(css = ".table>tbody>tr")//-responsive")
    private List<WebElement> processesTable;

    private String GENERIC_PROCESS_ROW_XPATH="//td[text()='%s']/..";

    public ProcessesPage assertProcessesUrl(String url){
        Assert.assertEquals(driver.getCurrentUrl(),url,"adresses do not match");
        return this;
    }
    public ProcessesPage assertProcessesHeader(){
        Assert.assertEquals(header.getText(),"Processes","Headers do not match");
        return this;
    }

    public ProcessesPage assertProcess(String expName, String expDescription, String expNotes){
        //String GENERIC_PROCESS_ROW_XPATH="//td[text()='%s']/..";

        String processXpath=String.format(GENERIC_PROCESS_ROW_XPATH,expName);
        WebElement processRow = driver.findElement(By.xpath(processXpath));

        String actDescription=processRow.findElement(By.xpath("./td[2]")).getText();
        String actNotes=processRow.findElement(By.xpath("./td[3]")).getText();

        Assert.assertEquals(actDescription,expDescription,"Descriptions don't match");
        Assert.assertEquals(actNotes,expNotes,"Notes don't match");

        return this;
    }

    public CreateProcessPage addNewProcess(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(addNewProcessBtn));
        addNewProcessBtn.click();

        return new CreateProcessPage(driver);
    }
    public ProcessesPage assertProcessIsNotShown(String processName){
        String processXpath=String.format(GENERIC_PROCESS_ROW_XPATH,processName);
        List<WebElement> process = driver.findElements(By.xpath(processXpath));
        Assert.assertEquals(process.size(),0,"There is a process with this name");

        return this;
    }
}
