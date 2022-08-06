package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CharacteristicsPage extends HomePage{

    @FindBy(css = ".title_left>h3")
    private WebElement header;
    @FindBy(css = ".btn-success")
    private WebElement addNewCharacteristicBtn;

    private String GENERIC_CHARACTERISTIC_ROW_XPATH = "//td[text()='%s']/..";

    public CharacteristicsPage(WebDriver driver){
        super(driver);
    }
    public CharacteristicsPage assertCharacteristicsHeader(){
        Assert.assertEquals(header.getText(),"Characteristics","Headers do not match");
        return this;
    }
    public CharacteristicsPage assertCharacteristicsUrl(String url){
        Assert.assertEquals(url,driver.getCurrentUrl(),"Adresses are not equal");
        return this;
    }
    public CreateCharacteristicPage addNewCharacteristic(){
        addNewCharacteristicBtn.click();
        return new CreateCharacteristicPage(driver);
    }
    public CharacteristicsPage assertCharacteristic(String expName,String expLowerLimit,String expUpperLimit,String expBinCount){
        String characteristicXpath = String.format(GENERIC_CHARACTERISTIC_ROW_XPATH,expName);
        WebElement characteristicRow = driver.findElement(By.xpath(characteristicXpath));

        String actLowerLimit = characteristicRow.findElement(By.xpath("./td[3]")).getText();
        String actUpperLimit = characteristicRow.findElement(By.xpath("./td[4]")).getText();
        String actBinCount = characteristicRow.findElement(By.xpath("./td[5]")).getText();

        Assert.assertEquals(actLowerLimit,expLowerLimit);
        Assert.assertEquals(actUpperLimit,expUpperLimit);
        Assert.assertEquals(actBinCount,expBinCount);
        return this;
    }
    public ResultsPage goToResultsPage(String characteristicName) {
        GENERIC_CHARACTERISTIC_ROW_XPATH+="//a[contains(@href, 'Results')]";
        String resultBtnXpath = String.format(GENERIC_CHARACTERISTIC_ROW_XPATH,characteristicName);

        WebElement resultBtn = driver.findElement(By.xpath(resultBtnXpath));
        scrollTillYouFindElementAndClick(resultBtn);
        return new ResultsPage(driver);
    }

    public ReportPage gotToReportPage(String characteristicName){
        GENERIC_CHARACTERISTIC_ROW_XPATH+="//a[contains(@href, 'Report')]";
        String reportBtnXpath = String.format(GENERIC_CHARACTERISTIC_ROW_XPATH,characteristicName);

        WebElement reportBtn = driver.findElement(By.xpath(reportBtnXpath));
        scrollTillYouFindElementAndClick(reportBtn);
        return new ReportPage(driver);
    }

    private void scrollTillYouFindElementAndClick(WebElement element){
        try {
            element.click();
        }catch (ElementClickInterceptedException e){
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            element.click();
        }
    }
}
