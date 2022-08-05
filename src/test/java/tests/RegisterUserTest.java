package tests;

import com.github.javafaker.Faker;
import data.TestDataSource;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.DatabaseUtils;

import static data.TestDataSource.*;

public class RegisterUserTest extends SeleniumBaseTest {

    @Test(invocationCount = 3) //test-scenerio: 1
    public void shouldAddUserSuccessfully(){
        String email = Faker.instance().internet().emailAddress();

        String password = generateValidPassword();

        DatabaseUtils db = DatabaseUtils.openConnection();
        db.insertDataToTable(email,password);

        System.out.println("email: "+email);
        System.out.println("password: "+password);
        new LoginPage(driver)
                .goToRegisterPage()
                    .typeEmail(email)
                    .typePassword(password)
                    .typeConfirmPassword(password)
                .submitRegister()
                    .assertWelcomeElemetIsShown();
    }

    @Test //test scenerio: 2 passwords are not the same
    public void shouldFail(){
        String email = Faker.instance().internet().emailAddress();
        String password = generateValidPassword();

        new LoginPage(driver)
                .goToRegisterPage()
                .typeEmail(email)
                .typePassword(password)
                .typeConfirmPassword("OtherPassword1!")
                .submitRegisterWithFailure()
                .submitRegisterWithFailure() //some unexpected behaviour, one click wasn't enough
                .assertRegisterErrorIsShown("The password and confirmation password do not match.");
    }

    //test scenerio: 3 incorrect password
    @Test(dataProvider = "wrongPasswords",dataProviderClass = TestDataSource.class)
    public void shouldFail(String password, String errMsg){
        String email = "test@gmail.com";
        new LoginPage(driver)
                .goToRegisterPage()
                    .typeEmail(email)
                    .typePassword(password)
                    .typeConfirmPassword(password)
                .submitRegisterWithFailure()
                .assertRegisterErrorIsShown(errMsg);
    }
}
