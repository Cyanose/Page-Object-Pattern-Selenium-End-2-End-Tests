package data;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import utilities.DatabaseUtils;

public class TestDataSource {

    //Data provider for test scenerio: 3 incorrect password
    @DataProvider
    public static Object[][] wrongPasswords(){
        return new Object[][]{
                {"short","The Password must be at least 6 and at max 100 characters long."},
                {"test1!","Passwords must have at least one uppercase ('A'-'Z')."},
                {"Test1a","Passwords must have at least one non alphanumeric character."}
        };
    }

    //Data provider for test scenerio:
    @DataProvider
    public static Object[][] goodPasswords(){

        Object[][] data ;

        DatabaseUtils db = DatabaseUtils.openConnection();
        String query = "SELECT * from Users";
        data = db.executeSelectQuery(query);
        db.closeConnection();

        return data;
    }
    public static String generateValidPassword(){
        /* regex that covers requirement for password:
        6-99 characters, including: number,special char, lower,uppercase letters*/
        return Faker.instance().regexify("[A-Z]{1}[a-z]{5,96}[0-9]{1}([@#!$%^&*])");
    }


}
