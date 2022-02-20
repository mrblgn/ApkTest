package steps;

import io.appium.java_client.MobileElement;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.After;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import setups.Capabilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ForeksTest extends Capabilities {
    @Before("@TestQATeam")
    public void setup() throws Exception {
        preparation();
    }

    @Given("^Increasing Values Test$")
    public void increasingValuesTest() throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement el1 = driver.findElementById("foreks.android:id/layoutMainTab_linearLayout_varantTab");
        el1.click();
        Thread.sleep(4000);
//        wait.until(ExpectedConditions.presenceOfElementLocated(
//                MobileBy.className(“android.widget.ListView”)
//        ));
        driver.navigate().back();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("Yükselenler / Düşenler");
        el2.click();
        List<WebElement> list = driver.findElementsById("foreks.android:id/rowWarrantSymbolList_textView_column3");
        List<Double> increase = new ArrayList<>();

//        TouchActions action = new TouchActions((WebDriver) driver);
//        WebElement element = driver.findElementById("foreks.android:id/layoutWarrantSymbolListHolderPage_recyclerView");
//        action.scroll(element, 10, 400);
//        action.perform();

        for (WebElement e : list) {
            String text = e.getText().replace("%", "").replace(",", ".");
            increase.add(Double.valueOf(text));
        }

        List<Double> increaseBase = new ArrayList<>(increase);
        increase.sort(Collections.reverseOrder());

        Assert.assertEquals(increase, increaseBase);
    }

    @Given("^Decreasing Values Test$")
    public void decreasingValuesTest() throws Throwable {
        MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("Düşenler");
        el2.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElementsById("foreks.android:id/rowWarrantSymbolList_textView_column3");
        List<Double> decrease = new ArrayList<>();

        for (WebElement e : list) {
            String text = e.getText().replace("%", "").replace(",", ".");
            decrease.add(Double.valueOf(text));
        }

        List<Double> increaseBase = new ArrayList<>(decrease);
        Collections.sort(decrease);

        Assert.assertEquals(decrease, increaseBase);
    }

    @After("@TestQATeam")
    public void stop(){
        stopServer();
    }
}
