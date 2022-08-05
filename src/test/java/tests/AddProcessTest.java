package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.SeleniumBaseTest;

import java.util.UUID;

public class AddProcessTest extends SeleniumBaseTest {

    @Test //test scenerio: 6
    private void shouldOccurOnProcessesPage() {
        String processName = UUID.randomUUID().toString().substring(0, 10);
        String processDescription = Faker.instance().chuckNorris().fact();
        String processNotes = Faker.instance().company().bs();

        new LoginPage(driver)
                .typeEmail(config.getLogin())
                .typePassword(config.getPassword())
                .submitLogin()
                .goToProcesses()
                .addNewProcess()
                .typeProcessName(processName)
                .typeProcesDescription(processDescription)
                .typeProcessNotes(processNotes)
                .submitProcessCreation()
                .assertProcessesHeader()
                .assertProcess(processName, processDescription, processNotes);
    }

    @Test //test scenerio: 7
    public void shouldOccurOnDashboardPage() {
        String processName = UUID.randomUUID().toString().substring(0, 10);
        String processDescription = Faker.instance().chuckNorris().fact();
        String processNotes = Faker.instance().company().bs();

        new LoginPage(driver)
                .typeEmail(config.getLogin())
                .typePassword(config.getPassword())
                .submitLogin()
                .goToProcesses()
                .addNewProcess()
                .typeProcessName(processName)
                .typeProcesDescription(processDescription)
                .typeProcessNotes(processNotes)
                .submitProcessCreation()
                .goToDashBoard()
                .assertDashboardUrl("http://localhost:4444/")
                .assertNewProcessWasAdded(processName);
    }

    @Test //test scenerio: 8
    public void shouldNotOccurOnDashboardPage() {
        String tooShortProcessName = "ab";
        new LoginPage(driver)
                .typeEmail(config.getLogin())
                .typePassword(config.getPassword())
                .submitLogin()
                .goToProcesses()
                .addNewProcess()
                .typeProcessName(tooShortProcessName)
                .goBackToProcessesList()
                .assertProcessIsNotShown(tooShortProcessName);
    }
}