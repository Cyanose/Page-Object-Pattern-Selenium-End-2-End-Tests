import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class RegisterUserTest extends SeleniumBaseTest{

    @Test //test-scenerio: 1
    public void shouldAddUserSuccessfully(){
        String email = Faker.instance().internet().emailAddress();

        /* regex that covers 6-99 characters, including: number,special char, lower,uppercase letters*/
        String password = Faker.instance().regexify("[A-Z]{1}[a-z]{5,96}[0-9]{1}([@#!$%^&*])");

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

    @Test //test scenerio: 2
    public void shouldFail_passwordsAreNOTtheSame(){
        String email = Faker.instance().internet().emailAddress();
        String password = Faker.instance().regexify("[A-Z]{1}[a-z]{5,96}[0-9]{1}([@#!$%^&*])");

        new LoginPage(driver)
                .goToRegisterPage()
                .typeEmail(email)
                .typePassword(password)
                .typeConfirmPassword("OtherPassword1!")
                .submitRegisterWithFailure()
                .submitRegisterWithFailure() //some unexpected behaviour, one click wasn't enough
                .assertRegisterErrorIsShown("The password and confirmation password do not match.");
    }

    @DataProvider
    public static Object[][] wrongPasswords(){
        return new Object[][]{
                {"short","The Password must be at least 6 and at max 100 characters long."},
                {"test1!","Passwords must have at least one uppercase ('A'-'Z')."},
                {"Test1a","Passwords must have at least one non alphanumeric character."}
        };
    }

    //test scenerio: 3
    @Test(dataProvider = "wrongPasswords")
    public void shouldFail_incorrectPassword(String password, String errMsg){
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