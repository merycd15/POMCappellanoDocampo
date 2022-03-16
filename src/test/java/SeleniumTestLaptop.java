import Pages.*;
import Utility.DriverFactory;
import Utility.PropertiesFile;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class SeleniumTestLaptop {
    //Seteo variable url con la dirección a la que quiero que navegue
    private String url = PropertiesFile.getProperty("url_base");
    //Seteo de Webdriver
    private WebDriver driver = DriverFactory.getDriver();

    @Test
    public void NavigateToDemoblaze() throws InterruptedException {

        //Variables locales de modelo y precio
        String modeloLaptop;
        String precioLaptop;

        //Instancio las pages
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        LaptopsPage laptopsPage = new LaptopsPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartMenuPage cartMenuPage = new CartMenuPage(driver);
        CartPage cartPage = new CartPage(driver);

        //Dirección a la que quiero que navegue
        driver.navigate().to(url);
        //Para poner el driver maximizado
        driver.manage().window().maximize();

        //Click categoría Laptops
        categoriesPage.clickLaptop();

        //Click primer laptop
        laptopsPage.clickPrimerProducto(10);

        //Obtengo modelo y precio de la laptop
        modeloLaptop = productPage.getLaptop();
        precioLaptop = productPage.getPrice();
        //Imprimo los datos por consola
        System.out.println("El modelo de la laptop es " + modeloLaptop + " y tiene un precio de " + precioLaptop);

        //Agrego al cart el producto
        productPage.clickAddToCart();

        //Espero el alert
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        //Obtengo el mensaje del alert
        String alertmessage = alert.getText();
        //Acepto el alert
        alert.accept();

        //Comparo el mensaje obtenido del alert con el mensaje esperado
        Assert.assertEquals("Product added", alertmessage);

        //Voy al cart
        cartMenuPage.clickCart();

        //Espero a que se muestre el producto cargado y compruebo que se agregó correctamente
        Assert.assertTrue(cartPage.imageProductIsVisible(10));

        //Cierro el navegador
        driver. quit();
    }


}
