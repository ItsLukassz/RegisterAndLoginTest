import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterandLoginTest {
    public static final String password = "ThisIsATestPassword";
    public static final String email = "itslukass123@gmail.com";
    public static final String keyword = "advanced";

    @BeforeMethod (onlyForGroups = "register")
    public void startregister(){
        System.setProperty("webdriver.chrome.driver", "src/webdriver/chromedriver97.exe");
        Register.OpenChrome();
    }
    @BeforeMethod (onlyForGroups = "login")
    public void startlogin(){
        System.setProperty("webdriver.chrome.driver", "src/webdriver/chromedriver97.exe");
        Login.OpenChrome();
    }
    @BeforeMethod (onlyForGroups = "search")
    public void startsearch(){
        System.setProperty("webdriver.chrome.driver", "src/webdriver/chromedriver97.exe");
        Search.OpenChrome();
    }

    @AfterMethod (onlyForGroups = "register")
    public void closeregister(){
        Register.browserClose();
    }


    @AfterMethod (onlyForGroups = "login")
    public void closelogin(){
        Login.browserClose();
    }
    @AfterMethod (onlyForGroups = "search")
    public void closesearch(){
        Search.browserClose();
    }


    @Test (groups = "register")
    public void registerTestPositive() throws InterruptedException {
        Assert.assertTrue(Register.fullRegister(password));
    }
    @Test (groups = "register")
    public void registerTestNegative() throws InterruptedException {
        Assert.assertNotEquals(Register.fullRegister(password), false);
    }
    @Test(groups = "login")
    public void loginTestPositive() throws InterruptedException {
        Assert.assertTrue(Login.fullLogin(email, password));
    }
    @Test(groups = "login")
    public void loginTestNegative() throws InterruptedException {
        Assert.assertNotEquals(Login.fullLogin(email, password), false);
    }
    @Test(groups = "search")
    public void searchTestPositive() throws InterruptedException {
        Assert.assertTrue(Search.FullSearch(email, password, keyword));
    }
    @Test(groups = "search")
    public void searchTestNegative() throws InterruptedException {
        Assert.assertNotEquals(Search.FullSearch(email, password, keyword), false);
    }
}
