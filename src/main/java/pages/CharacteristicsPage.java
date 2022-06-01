package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        Assert.assertEquals(url,driver.getCurrentUrl(),"adresy sie nie zgadzają");
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
}
