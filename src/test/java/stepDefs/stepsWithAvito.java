package stepDefs;

import io.cucumber.java.ParameterType;
import io.cucumber.java.ru.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.util.List;





public class stepsWithAvito {
    public static ChromeDriver driver;
    public static WebDriverWait explicitWait;

    @Пусть("открыт ресурс авито")
    public void openSite() {
        driver.get("https://www.avito.ru/");
        Ashot();
    }

    @ParameterType(".*")
    public Categories categories(String category) {
        return Categories.valueOf(category);
    }

    @И("в выпадающем списке категорий выбрана {categories}")
    public void selectCategory(Categories cat) {
        Select category = new Select(driver.findElement(By.xpath("//select[@name = 'category_id']")));
        category.selectByValue(cat.id);
        Ashot();
    }

    @И("в поле поиска введено значение {word}")
    public void enterProductName(String product) {
        WebElement input = driver.findElement(By.xpath("//input[@placeholder = 'Поиск по объявлениям']"));
        input.sendKeys(product);
        Ashot();
    }

    @Тогда("кликнуть по выпадающему списку региона")
    public void regionClick() {
        WebElement city = driver.findElement(By.xpath("//div[@data-marker = 'search-form/region']"));
        city.click();
        Ashot();
    }


    @Тогда("в поле регион введено значение {word}")
    public void enterTheCity(String city) {
        WebElement cityInput = driver.findElement(By.xpath("//input[@data-marker = 'popup-location/region/input']"));
        cityInput.sendKeys(city);
        explicitWait.until(ExpectedConditions.textToBe(By.xpath("//li[@data-marker = 'suggest(0)']//strong"), city));
        Ashot();
    }

    @И("нажата кнопа показать объявления")
    public void showGoods() {
        WebElement suggestedCityButton = driver.findElement(By.xpath("//button[@data-marker = 'popup-location/save-button']"));
        suggestedCityButton.click();
        Ashot();
    }

    @Тогда("открылась страница результаты по запросу {word}")
    public void pageCheck(String product)  {
        if (product.equals("принтер"))
            explicitWait.until(ExpectedConditions.urlContains("%D0%BF%D1%80%D0%B8%D0%BD%D1%82%D0%B5%D1%80"));
        else driver.quit();
        Ashot();
    }

    @И("активирован чекбокс только с фотографией")
    public void checkBoxCheck() {
        WebElement checkBox = driver.findElement(By.xpath("//span[text() = 'только с фото']"));
        if (!checkBox.isSelected())
            checkBox.click();
        Ashot();
    }

    @ParameterType(".*")
    public ShowSelect showSelect(String select) {
        return ShowSelect.valueOf(select);
    }
    @И("в выпадающем списке сотрировка выбрано значение {showSelect}")
    public void showSelectClick(ShowSelect select) {
        Select showSelect = new Select(driver.findElement(By.xpath("//div[@class = 'sort-select-3QxXG select-select-box-3LBfK select-size-s-2gvAy']//select[@class = 'select-select-3CHiM']")));
        showSelect.selectByValue(select.id);
        Ashot();
    }

    @И("в консоль выведено значение названия и цены {int} первых товаров")
    public void showItemList(int howMuchToShow) {
        List<WebElement> itemsList = driver.findElements(By.xpath("//div[@class = 'items-items-38oUm'][1]/div[@data-marker = 'item']"));
        for (int count = 0; count < howMuchToShow; count++) {
            System.out.println(itemsList.get(count).findElement(By.xpath(".//div[@class = 'iva-item-titleStep-2bjuh']")).getText() + " стоит " + itemsList.get(count).findElement(By.xpath(".//div[@class = 'iva-item-priceStep-2qRpg']")).getText());
        }
        Ashot();
    }

    public void Ashot() {
        Allure.addAttachment("Скришот", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

}
