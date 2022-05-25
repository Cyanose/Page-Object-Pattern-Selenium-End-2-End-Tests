package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {
    protected WebDriver driver;

    @FindBy(css = ".sidebar-footer>a[href*=Logout]")
    private WebElement bottomLogOutBtn;
    @FindBy(css = ".profile_info>h2")
    private WebElement welcomeElm;

    @FindBy(css=".menu-workspace")
    private WebElement workspaceNav;

    @FindBy(css =".menu-home")
    private WebElement menuHome;

    @FindBy(css="a[href$=Projects]")
    private WebElement processesMenu;

    @FindBy(linkText = "Dashboard")
    private WebElement dashboardBtn;

    @FindBy(css="a[href$=Characteristics]")
    private WebElement characteristicsBtn;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public HomePage assertWelcomeElemetIsShown(){
        Assert.assertTrue(this.welcomeElm.isDisplayed(), "welcome element is not shown.");
        Assert.assertTrue(this.welcomeElm.getText().contains("Welcome"), "welcome element text: '" +
                this.welcomeElm.getText() + "' does not contain word 'welcome'");
        return this;
    }

    public boolean isParentExpanded(WebElement menuLink){
        WebElement parent = menuLink.findElement(By.xpath("./.."));

        return parent.getAttribute("class").contains("active");
    }

    public ProcessesPage goToProcesses(){
        if(!isParentExpanded(workspaceNav))
            workspaceNav.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(processesMenu));

        processesMenu.click();

        return new ProcessesPage(driver);
    }

    public CharacteristicsPage goToCharacteristics(){
        if(!isParentExpanded(workspaceNav))
            workspaceNav.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(characteristicsBtn));

        characteristicsBtn.click();

        return new CharacteristicsPage(driver);
    }
    public DashBoardPage goToDashBoard(){
        if(!isParentExpanded(menuHome))
            menuHome.click();

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait.until(ExpectedConditions.elementToBeClickable(dashboardBtn));
        dashboardBtn.click();
        return new DashBoardPage(driver);
    }
    public LoginPage logout(){
        if(!isParentExpanded(menuHome))
            menuHome.click();
        bottomLogOutBtn.click();
        return new LoginPage(driver);
    }
    /*
    public DashBoardPage addProcess(){

    }

     */



}
