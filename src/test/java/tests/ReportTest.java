package tests;

import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.UUID;

public class ReportTest extends SeleniumBaseTest {

    @Test //test scenerio: 12
    public void shouldGenerateProperReport() {
        String processName = "DEMO PROJECT";
        String characteristicName = UUID.randomUUID().toString().substring(0, 10);
        String lowerLimit = "8";
        String upperLimit = "10";

        String sampleName="sample";
        String resultValue="5;4;3";

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
                .goToResultsPage(characteristicName)
                .addNewResultSample()
                .typeName(sampleName)
                .typeResults(resultValue)
                .submitCreate()
                .goBackToCharacteristics()
                .gotToReportPage(characteristicName)
                .assertMetric("Mean","4.0000")
                .assertMetric("Standard deviation","1.0000")
                .assertMetric("Performance index","0.3333")
                .assertMetric("Ppl","-1.3333")
                .assertMetric("Ppu","2.0000")
                .assertMetric("Ppk","-1.3333");
    }
}
