import com.google.common.collect.ImmutableMap;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.*;

import javax.xml.xpath.XPath;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static java.awt.SystemColor.text;
import static org.junit.Assert.assertEquals;

public class StepImplementation extends BaseTest {


    @Step("<time> saniye kadar bekle")
    public static void waitForseconds(int time) throws InterruptedException {
        Thread.sleep(time * 1000);
    }

    @Step("id <id> li elemente tıkla")
    public static void clickByid(String id) {
        appiumDriver.findElement(By.id(id)).click();
    }


    @Step("xpath <xpath> li elemente tıkla")
    public void clickByXpath(String xpath) {
        appiumDriver.findElement(By.xpath(xpath)).click();


    }

    @Step("xpath <xpath> li elementi bul ve <text> ini kontrol et")
    public void textiKontrolEt(String xpath, String text) {

        Assert.assertTrue("Element text değerini içermiyor",
                appiumDriver.findElementByXPath(xpath).getText().contains(text));
    }


    @Step("id <id> li ementi bul ve <text> değerini yaz")
    public void sendkeysByid(String id, String text) {
        appiumDriver.findElement(By.id(id)).sendKeys(text);


    }

    @Step("Android klavyeyi kapat")
    public void closeKeyboardonAndroid() {
        appiumDriver.hideKeyboard();


    }

    @Step("Sayfayı aşağı kaydır")
    public void swipeDown() {
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;
        // init screen variables
        Dimension dims = appiumDriver.manage().window().getSize();
        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
        new TouchAction(appiumDriver)
                .press(pointOptionStart)
                // a bit more reliable when we add small wait
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();

    }

    @Step("id <id> li elementi bul ve <text> alanını kontrol et")
    public void textAreacontrol(String id, String text) {
        Assert.assertTrue("Element text değerini içermiyor", appiumDriver.findElement(By.id(id)).getText().contains(text));
    }

    @Step("Android klavyeden arama tuşuna bas")
    public void clickSearchbuttonOnadnroidKeyboard() {
        ((JavascriptExecutor) appiumDriver).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
    }

    @Step("Ürünler <xpath> arasından rastgele bir tane seç")
    public void clickRandomelement(String xpath) {
        Random random = new Random();
        List<MobileElement> products = appiumDriver.findElements(By.xpath(xpath));
        int index = random.nextInt(products.size());
        products.get(index).click();
    }

/*
    @Step("<xpath> ve <xpath> ile giriş yap")
    public void login(){

        WebElement we=appiumDriver.findElement(By.xpath("//*[@class=\"com.ozdilek.ozdilekteyim:id/etEposta\"]"));
        we.click();
        JavascriptExecutor js = (JavascriptExecutor)appiumDriver;
        js.executeScript("arguments[0].value='ozcan.bukun@hotmail.com';", we);

        WebElement we=appiumDriver.findElement(By.xpath("//*[@class=\"com.ozdilek.ozdilekteyim:id/etPassword\"]"));
        we.click();
        JavascriptExecutor js = (JavascriptExecutor)appiumDriver;
        js.executeScript("arguments[0].value='1q2w3e4r5t';", we);



        appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/etEposta"), "ozcan.bukun@hotmail.com");
        appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/etPassword\n"), "1q2w3e4r5t");

        clickByid("com.ozdilek.ozdilekteyim:id/btnLogin\n");


    }
*/

}
