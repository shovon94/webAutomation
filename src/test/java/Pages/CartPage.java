package Pages;

import Test.TestMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    WebDriver driver;
    List<WebElement> item;

    public CartPage(WebDriver driver)
    {
        this.driver=driver;

    }

    By cartIcon = By.xpath("//a[@class='shopping_cart_link']");
    By itemName = By.xpath("//div[@class='inventory_item_name']");
    By itemDescription = By.xpath("//div[@class='inventory_item_desc']");
    By cartPageTitle = By.xpath("//span[@class='title']");
    By continueShopping = By.xpath("//button[@id='continue-shopping']");


    public void openCartPage()
    {
        driver.findElement(cartIcon).click();
    }

    public String validateCartPage()
    {
        return driver.findElement(cartPageTitle).getText();
    }

    public String GetProductName()
    {
        return driver.findElement(itemName).getText();
    }

    public String GetProductDescription()
    {
        return driver.findElement(itemDescription).getText();
    }

    public void ContinueShopping()
    {
        driver.findElement(continueShopping).click();
    }

    public void CartItemDetails()
    {

        String[] itemList = new String[0];
        item= driver.findElements(By.xpath("//div[@class='cart_item_label']"));
        //System.out.println(item.size());
        String itMax= driver.findElements(By.xpath("//div[@class='inventory_item_name']")).get(0).getText();
        String itMin= driver.findElements(By.xpath("//div[@class='inventory_item_name']")).get(1).getText();

        String itDesMax= driver.findElements(By.xpath("//div[@class='inventory_item_desc']")).get(0).getText();
        String itDesMin= driver.findElements(By.xpath("//div[@class='inventory_item_desc']")).get(1).getText();

        System.out.println("-------------------ITEM DETAILS--------------------------\n");

        System.out.println("Maximum Price Item Name : "+itMax);
        System.out.println("Maximum Item Description : "+itDesMax+"\n\n");
        System.out.println("Minimum Price Item Name : "+itMin);
        System.out.println("Minimum Price Item Description : "+itDesMin);

        System.out.println("---------------------------------------------------------\n");

    }

}
