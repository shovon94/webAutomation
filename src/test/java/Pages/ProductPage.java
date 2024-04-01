package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v121.systeminfo.SystemInfo;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;

public class ProductPage {

    WebDriver driver;
    String filterName;
    List<WebElement> price;
    String productName;

    public ProductPage(WebDriver driver)
    {
        this.driver=driver;

    }


    By sortBtn = By.xpath("//select[@class='product_sort_container']");

    By sortOptions= By.xpath("//select[@class='product_sort_container']/option");


    public void ApplyFilter(String filterName) throws InterruptedException {
        this.filterName=filterName;
        int attempts = 0;
        while (attempts < 2)
        {
            try {
                driver.findElement(sortBtn).click();
                List<WebElement> filter = driver.findElements(sortOptions);
                for (WebElement f : filter) {
                    if (f.getText().equals(filterName)) {
                        Thread.sleep(5000);
                        f.click();
                    }

                }
            }catch (StaleElementReferenceException e) {
                System.out.println("Retrying...");
            }
            attempts++;
        }


    }

    public boolean PriceSortValidationDesc()
    {
        String[] priceList = new String[0];
        price= driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        for (WebElement p: price)
        {
            System.out.println(p.getText());
            priceList= new String[]{p.getText()};

        }

        boolean isDescending = true;
        for (int i = 0; i < priceList.length - 1; i++)
        {
            if (priceList[i].compareTo(priceList[i + 1]) < 0) {
                // If the current element is less than the next one, array is not in descending order
                isDescending = false;
                break;
            }
        }

        if (isDescending)
        {
            System.out.println("PriceList is in descending order\n");
            return true;
        }
        else
        {
            System.out.println("PriceList is NOT in descending order\n");
            return false;
        }

    }

    public boolean PriceSortValidationAsc()
    {
        String[] priceList = new String[0];
        price= driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        for (WebElement p: price)
        {
            System.out.println(p.getText());
            priceList= new String[]{p.getText()};

        }

        boolean isAscending = true;
        for (int i = 0; i < priceList.length - 1; i++)
        {
            if (priceList[i].compareTo(priceList[i + 1]) > 0) {
                // If the current element is less than the next one, array is not in descending order
                isAscending = false;
                break;
            }
        }

        if (isAscending)
        {
            System.out.println("PriceList is in Ascending order\n");
            return true;
        }
        else
        {
            System.out.println("PriceList is NOT in Ascending order\n");
            return false;
        }

    }

    public String AddToCart()
    {
        productName = driver.findElement(By.xpath(("(//button[@class='btn btn_primary btn_small btn_inventory '])[1]"))).getText();
        driver.findElement(By.xpath(("(//button[@class='btn btn_primary btn_small btn_inventory '])[1]"))).click();
        return productName;
    }
    public String SelectedProductName()
    {
        return driver.findElement(By.xpath(("(//button[@class='btn btn_primary btn_small btn_inventory '])[1]"))).getText();

    }

}
