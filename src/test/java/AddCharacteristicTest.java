import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.UUID;

public class AddCharacteristicTest extends SeleniumBaseTest{
    @Test //test scenerio: 9
    public void shouldAddCharacteristic(){
        String processName = "DEMO PROJECT";
        String characteristicName = UUID.randomUUID().toString().substring(0,10);
        String lowerLimit = "8";
        String upperLimit = "10";

        new LoginPage(driver)
                .typeEmail(config.getLogin())
                .typePassword(config.getPassword())
                .submitLogin()
                .goToCharacteristics()
                .addNewCharacteristic()
                .selectProcess(processName)
                .typeCharacteristicName(characteristicName)
                .typeLowerSpecLimit(lowerLimit)
                .typeUpperSpecLimit(upperLimit)
                .submitCreate()
                .assertCharacteristic(characteristicName, lowerLimit , upperLimit, "");
    }
}
