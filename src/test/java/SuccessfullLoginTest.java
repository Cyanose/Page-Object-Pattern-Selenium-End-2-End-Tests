import org.testng.annotations.Test;
import pages.LoginPage;

public class SuccessfullLoginTest extends SeleniumBaseTest {

    @Test //test scenerio: 4
    public void shouldLog(){

        new LoginPage(driver)
                .typeEmail(config.getLogin())
                .typePassword(config.getPassword())
                .submitLogin()
                .assertWelcomeElemetIsShown();
    }
}
