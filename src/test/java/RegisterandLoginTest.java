import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterandLoginTest {
    public static final String password = "ThisIsATestPassword";
    public static final String email = "itslukass123@gmail.com";

    @BeforeMethod
    public void start(){
        System.setProperty("webdriver.chrome.driver", "src/webdriver/chromedriver97.exe");
    }
    @AfterMethod
    public void close(){

    }
    @Test
    public void registerTestPositive() throws InterruptedException {
        Register.OpenChrome();
        Assert.assertTrue(Register.fullRegister(password));
        Register.browserClose();
    }
    @Test
    public void registerTestNegative() throws InterruptedException {
        Register.OpenChrome();
        Assert.assertNotEquals(Register.fullRegister(password), false);
        Register.browserClose();
    }
    @Test
    public void loginTestPositive() throws InterruptedException {
        Login.OpenChrome();
        Assert.assertTrue(Login.fullLogin(email, password));
        Login.browserClose();
    }
    @Test
    public void loginTestNegative() throws InterruptedException {
        Login.OpenChrome();
        Assert.assertNotEquals(Login.fullLogin(email, password), false);
        Login.browserClose();
    }
}
