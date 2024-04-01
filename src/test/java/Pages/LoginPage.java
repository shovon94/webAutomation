package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        this.driver=driver;

    }

    By txtUsername = By.xpath("//input[@id='user-name']");
    By txtPassword = By.xpath("//input[@id='password']");
    By btn_login = By.xpath("//input[@id='login-button']");
    By btnMenu = By.xpath("//button[@id='react-burger-menu-btn']");
    By sideMenuLogout= By.xpath("//a[@id='logout_sidebar_link']");


    public void Login(String user, String pass)
    {
        try
        {
            driver.findElement(txtUsername).sendKeys(user);
            driver.findElement(txtPassword).sendKeys(pass);
            driver.findElement(btn_login).click();
            Thread.sleep(3000);

        }

        catch (Exception e)
        {
            System.out.println("Exception:"+ e.getMessage());
        }

    }

    public String CheckLoginValidation()
    {
        try
        {
            driver.findElement(btnMenu).click();
            Thread.sleep(1000);
            return driver.findElement(sideMenuLogout).getText();
        }
        catch (Exception e)
        {
            System.out.println("Exception:"+ e.getMessage());
        }

        return null;
    }

}
