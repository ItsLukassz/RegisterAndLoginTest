import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Search {
    public static final String LoginLink = "https://learner.demo.edunext.co/login";
    public static final String password = "ThisIsATestPassword";
    public static final String email = "itslukass123@gmail.com";
    public static final String emailFieldID = "login-email";
    public static final String passwordFieldID = "login-password";
    public static final String SearchFieldClasss = "discovery-input";
    public static final String ResultsFieldID = "discovery-message";
    static ChromeDriver browser;

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/webdriver/chromedriver97.exe");
        OpenChrome();
        login(email,password);
        Thread.sleep(2000);
        System.out.println(search("advanced"));
    }
    public static boolean FullSearch(String Email, String Password, String keyword) throws InterruptedException {
        login(Email,Password);
        Thread.sleep(2000);
        return search(keyword);
    }
    public static void OpenChrome() {
        // opening chrome
        browser = new ChromeDriver();
        browser.get(LoginLink);
    }
    public static void browserClose(){
        browser.close();
    }

    public static void login(String Email, String Password){

        WebElement EmailField = browser.findElement(By.id(emailFieldID));
        EmailField.sendKeys(Email);

        WebElement PasswordField = browser.findElement(By.id(passwordFieldID));
        PasswordField.sendKeys(Password);

        WebElement click = browser.findElement(By.xpath("//*[@id=\"login\"]/button"));
        JavascriptExecutor executor = browser;
        executor.executeScript("arguments[0].click();", click);
    }
    public static boolean search(String keyword){

        WebElement click = browser.findElement(By.xpath("//*[@id=\"dashboard-main\"]/div[1]/div[3]/div/div[1]/div[1]/a"));
        JavascriptExecutor executor = browser;
        executor.executeScript("arguments[0].click();", click);

        WebElement search = browser.findElement(By.className(SearchFieldClasss));
        search.sendKeys(keyword);
        Actions builder = new Actions(browser);
        builder.sendKeys(Keys.ENTER).perform();

        WebElement results = browser.findElement(By.id(ResultsFieldID));
        String Results = results.getText();
        return Results.contains("Viewing");
    }
}
