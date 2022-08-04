package tests;

import data.TestDataSource;
import org.testng.annotations.Test;
import pages.LoginPage;

public class SuccessfullLoginTest extends SeleniumBaseTest {

    @Test //test scenerio: 4
    public void shouldLogWithDefaultCredentials(){

        new LoginPage(driver)
                .typeEmail(config.getLogin())
                .typePassword(config.getPassword())
                .submitLogin()
                .assertWelcomeElemetIsShown();
    }

    @Test(dataProvider="goodPasswords",dataProviderClass = TestDataSource.class)
    public void shouldLogAllTheUsers(String login, String password){

        new LoginPage(driver)
                .typeEmail(login)
                .typePassword(password)
                .submitLogin()
                .assertWelcomeElemetIsShown();
    }
}