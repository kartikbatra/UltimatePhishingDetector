package bot;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Seleniumclass extends SearchServlet {
    WebDriver driver;

    public Seleniumclass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    public void call() {
        driver.get(SearchServlet.Link);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(org.openqa.selenium.TimeoutException.class);

        wait.until((Function<WebDriver, Boolean>) webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public String performActions(String username, String password) {
        String[] commonUsernameLocators = {
                "input[type='text']",
                "input[type='email']",
                "input[type='tel']",
                "input[name='username']",
                "input[name='user']",
                "input[name='login']",
                "input[id*='username']",
                "input[id*='user']",
                "input[class*='username']",
                "input[class*='user']",
                "input[data-test*='username']",
                "input[data-test*='user']"
        };

        String[] commonPasswordLocators = {
                "input[type='password']",
                "input[name='password']",
                "input[id*='password']",
                "input[class*='password']",
                "input[data-test*='password']"
        };

        String[] commonLoginButtonLocators = {
                "input[type='submit']",
                "button[type='submit']",
                "button[name='login']",
                "button[id*='login']",
                "button[class*='login']",
                "button[data-test*='login']"
        };

        String usernameLocator = findFieldLocator(driver, commonUsernameLocators);
        String passwordLocator = findFieldLocator(driver, commonPasswordLocators);
        String loginButtonLocator = findButtonLocator(driver, commonLoginButtonLocators);

        if (usernameLocator != null && passwordLocator != null && loginButtonLocator != null) {
            System.out.println("Username field found with locator: " + usernameLocator);
            System.out.println("Password field found with locator: " + passwordLocator);
            System.out.println("Login button found with locator: " + loginButtonLocator);

            sendKeysToField(usernameLocator, username);
            sendKeysToField(passwordLocator, password);

            clickButton(loginButtonLocator);

            try {
                Thread.sleep(2000); // Wait for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains(SearchServlet.Link)) {
                return "Safe.";
            } else {
                return "Unsafe.";
            }
        } else {
            return "Required fields or login button not found on the website.";
        }

    }

    public void sendKeysToField(String locator, String keys) {
        WebElement textField = driver.findElement(By.cssSelector(locator));
        textField.sendKeys(keys);
    }

    public void clickButton(String locator) {
        WebElement button = driver.findElement(By.cssSelector(locator));

        if (button != null) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.pollingEvery(Duration.ofMillis(500));

            try {
                wait.until(ExpectedConditions.elementToBeClickable(button)).click();
            } catch (Exception e) {
                System.out.println("Failed to click the button: " + e.getMessage());
                // Handle the exception accordingly
            }
        } else {
            System.out.println("Button is not found");
            // Handle the situation accordingly
        }
    }


    public String findFieldLocator(WebDriver driver, String[] locators) {
        for (String locator : locators) {
            try {
                WebElement textField = driver.findElement(By.cssSelector(locator));
                if (textField != null) {
                    return locator;
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    public String findButtonLocator(WebDriver driver, String[] locators) 
    {
        for (String locator : locators) {
            try {
                WebElement button = driver.findElement(By.cssSelector(locator));
                if (button != null) {
                    return locator;
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }
}
