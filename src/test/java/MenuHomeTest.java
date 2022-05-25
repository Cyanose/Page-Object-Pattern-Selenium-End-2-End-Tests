import org.testng.annotations.Test;
import pages.LoginPage;

public class MenuHomeTest extends SeleniumBaseTest{

    @Test //test scenerio: 5
    public void navigationThroughMenuTest() {
        new LoginPage(driver)
                .typeEmail(config.getLogin())
                .typePassword(config.getPassword())
                .submitLogin()
                    .assertWelcomeElemetIsShown()
                .goToProcesses()
                    .assertProcessesHeader()
                    .assertProcessesUrl("http://localhost:4444/Projects")
                .goToDashBoard()
                    .assertDemoProjectIsShown()
                    .assertDashboardUrl(config.getApplicationUrl())
                .goToCharacteristics()
                    .assertCharacteristicsHeader()
                    .assertCharacteristicsUrl("http://localhost:4444/Characteristics")
                .goToDashBoard() //return to the starting point
                .logout() //logout = go to the login page
                    .assertLoginPageHeader();
    }
}