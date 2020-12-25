package mk.finki.ukim.wp.lab.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage extends AbstractPage {
    private WebElement username;
    private WebElement password;
    private WebElement button;

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    public static LoginPage toLogin(WebDriver driver)
    {
        get(driver,"/auth/login");
        return PageFactory.initElements(driver,LoginPage.class);
    }

    public static BalloonPage doLogin(WebDriver driver,LoginPage loginPage, String username, String password) {
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.button.click();
        return PageFactory.initElements(driver, BalloonPage.class);
    }
}