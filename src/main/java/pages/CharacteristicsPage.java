package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CharacteristicsPage extends HomePage{

    @FindBy(css = ".title_left>h3")
    private WebElement header;

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
}
