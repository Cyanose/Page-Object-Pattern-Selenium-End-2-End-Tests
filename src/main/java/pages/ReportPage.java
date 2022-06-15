package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ReportPage extends HomePage{

    /*
    @FindBy(xpath = "//td[text()='Mean (x)']//../td[2]")
    private WebElement meanCell;

    @FindBy(xpath = "//td[text()='Standard deviation (s)']//../td[2]")
    private WebElement standardDeviationCell;

    @FindBy(xpath = "//td[text()='Performance Index (Pp)']//../td[2]")
    private WebElement performanceIndexCell;

    @FindBy(xpath = "//td[text()='Lower process performance index (Ppl)']//../td[2]")
    private WebElement lowerProcessPerformanceIndexCell;

    @FindBy(xpath = "//td[text()='Upper process performance index (Ppu)']//../td[2]")
    private WebElement upperProcessPerformanceIndexCell;

    @FindBy(xpath = "//td[text()='Process performance index (Ppk)']//../td[2]")
    private WebElement processPerformanceIndexCell;

     */

    private String GENERIC_METRIC_VALUE_XPATH="//tr/td[contains(text(),'%s')]//../td[2]";

    public ReportPage(WebDriver driver){
        super(driver);
    }

    public ReportPage assertMetric(String metricName, String expResult){
        String actResultXpath = String.format(GENERIC_METRIC_VALUE_XPATH,metricName);
        String actResult= driver.findElement(By.xpath(actResultXpath)).getText();
        Assert.assertEquals(actResult,expResult,"Values of metric: "+metricName+" does not match "+expResult);
        return this;
    }

    /*
    public ReportPage assertMean(String expMean){
        Assert.assertEquals(meanCell.getText(),expMean,"Means are not the same");
        return this;
    }

    public ReportPage assertStandardDeviation(String expDeviation){
        Assert.assertEquals(standardDeviationCell.getText(),expDeviation,"Standard deviations are not the same");
        return this;
    }

    public ReportPage assertPerformanceIndex(String expDeviation){
        Assert.assertEquals(standardDeviationCell.getText(),expDeviation,"Standard deviations are not the same");
        return this;
    }

     */

}
