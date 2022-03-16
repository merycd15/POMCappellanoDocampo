package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoriesPage {
    @FindBy (linkText = "Laptops")
    WebElement laptopsCategory;
    WebDriver driver;



    public CategoriesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickLaptop(){
        laptopsCategory.click();
    }


}
