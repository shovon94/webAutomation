package Test;
import Pages.CartPage;
import Pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.*;

public class TestMethods {

    WebDriver driver;
    LoginPage lp;
    ProductPage pp;
    CartPage ct;
    public static String maxPrice;
    public static String minPrice;

    @BeforeClass
    public void LaunchBrowser()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        lp= new LoginPage(driver);
        pp= new ProductPage(driver);
        ct=new CartPage(driver);
    }

    @Test(priority = 0)
    public void OpenSite()
    {
        driver.get("https://www.saucedemo.com/");
        assertEquals(driver.getTitle(),"Swag Labs");
    }

    @Test(dependsOnMethods = "OpenSite")
    public void LoginAction()
    {
        lp.Login("standard_user","secret_sauce");
    }
    @Test(dependsOnMethods = "LoginAction")
    public void CheckLoginValidation()
    {
        assertEquals(lp.CheckLoginValidation(),"Logout");
    }

    @Test(dependsOnMethods = "CheckLoginValidation")
    public void ProductSortDesc() throws InterruptedException {
        pp.ApplyFilter("Price (high to low)");
       //pp.ApplyFilter("Price (low to high)");
    }

    @Test(dependsOnMethods = "ProductSortDesc")
    public void ValidateFilterDesc()
    {
        assertTrue(pp.PriceSortValidationDesc());
    }

    @Test(dependsOnMethods = "ValidateFilterDesc")
    public void ProductAddToCart()
    {
        maxPrice =pp.AddToCart();

    }
    @Test(dependsOnMethods = "ProductAddToCart")
    public void OpenCart()
    {
        ct.openCartPage();

    }

    @Test(dependsOnMethods = "OpenCart")
    public void ValidateCartPage()
    {
        assertEquals(ct.validateCartPage(),"Your Cart");
        ct.ContinueShopping();

    }


    @Test(dependsOnMethods = "ValidateCartPage")
    public void ProductSortAsc() throws InterruptedException
    {
           Thread.sleep(2000);
           driver.navigate().refresh();
           pp.ApplyFilter("Price (low to high)");
    }



    @Test(dependsOnMethods = "ProductSortAsc")
    public void ValidateFilterAsc()
    {
        assertTrue(pp.PriceSortValidationAsc());
    }

    @Test(dependsOnMethods = "ValidateFilterAsc")
    public void ProductAddToCartMin() throws InterruptedException {
        minPrice=pp.AddToCart();
        Thread.sleep(2000);

    }
    @Test(dependsOnMethods = "ProductAddToCartMin")
    public void OpenCartforFinal()
    {
        ct.openCartPage();

    }
    @Test(dependsOnMethods = "OpenCartforFinal")
    public void PrintName()
    {
        ct.CartItemDetails();

    }


    @AfterClass
    public void afterTest() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }


}
