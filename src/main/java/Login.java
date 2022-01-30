import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

    public static final String LoginLink = "https://learner.demo.edunext.co/login";
    public static final String password = "ThisIsATestPassword";
    public static final String email = "itslukass123@gmail.com";
    public static final String emailFieldID = "login-email";
    public static final String passwordFieldID = "login-password";

    static ChromeDriver browser;


    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/webdriver/chromedriver97.exe");
        OpenChrome();
        PopulateFields(email,password);
        confirmRegistration();
        Thread.sleep(3000);
        browserClose();

    }
    public static boolean fullLogin(String Email, String Password) throws InterruptedException {
        PopulateFields(Email,Password);
        return confirmRegistration();
    }
    public static void OpenChrome() {
        // opening chrome
        browser = new ChromeDriver();
        browser.get(LoginLink);
    }
    public static void browserClose(){
        browser.close();
    }
    public static void PopulateFields(String Email, String Password){
        WebElement EmailField = browser.findElement(By.id(emailFieldID));
        EmailField.sendKeys(Email);

        WebElement PasswordField = browser.findElement(By.id(passwordFieldID));

        PasswordField.sendKeys(Password);
    }
    public static boolean confirmRegistration() throws InterruptedException {
        WebElement click = browser.findElement(By.xpath("//*[@id=\"login\"]/button"));
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", click);
        boolean success;
        Thread.sleep(5000);
        try {
            browser.findElement(By.className("my-courses"));
            success = true;
        } catch (NoSuchElementException e) {
            success = false;
        }
        System.out.println("Was the registration successfull: " + success);
        return success;
    }
}
