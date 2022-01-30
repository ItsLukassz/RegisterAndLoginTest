import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Register {
    public static final String RegisterLink = "https://learner.demo.edunext.co/register";
    public static final String FullnameFieldID = "register-name";
    public static final String UsernameFieldID = "register-username";
    public static final String EmailFieldId = "register-email";
    public static final String PasswordFieldId = "register-password";

    static ChromeDriver browser;
    public static void main(String[] args) throws InterruptedException {
        // used for generating fake names
        System.setProperty("webdriver.chrome.driver", "src/webdriver/chromedriver97.exe");
        // opening chrome
        OpenChrome();
        // inputting
        String password = "ThisIsATestPassword123";
        PopulateFields(password);
        // clicking checkboxes
        checkCheckboxes();
        // confirming registration
        confirmRegistration();
        // waiting for 3 seconds for everything to load and finish
        Thread.sleep(3000);
        browserClose();

    }
    public static boolean fullRegister(String password) throws InterruptedException {

        PopulateFields(password);
        checkCheckboxes();
        return confirmRegistration();
    }

    public static void OpenChrome(){
        // opening chrome
        browser = new ChromeDriver();
        browser.get(RegisterLink);
    }
    public static void PopulateFields(String password){
        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();

        WebElement namefield = browser.findElement(By.id(FullnameFieldID));
        namefield.sendKeys(firstname + " " + lastname);

        WebElement usernamefield = browser.findElement(By.id(UsernameFieldID));

        usernamefield.sendKeys(firstname + "_GamerxXx");

        WebElement emailfield = browser.findElement(By.id(EmailFieldId));
        emailfield.sendKeys(firstname+ "."+ lastname +"@gmail.com");

        WebElement passwordfield = browser.findElement(By.id(PasswordFieldId));
        passwordfield.sendKeys(password);
    }
    public static void checkCheckboxes(){
        Actions builder = new Actions(browser);
        builder.sendKeys(Keys.TAB).perform();
        builder.sendKeys(Keys.SPACE).perform();
        builder.sendKeys(Keys.TAB).perform();
        builder.sendKeys(Keys.TAB).perform();
        builder.sendKeys(Keys.SPACE).perform();
    }
    public static boolean confirmRegistration() throws InterruptedException {
        WebElement click = browser.findElement(By.xpath("//button[contains(.,'Create Account')]"));
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
    public static void browserClose(){
        browser.close();
    }
}
