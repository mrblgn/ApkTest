package setups;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Capabilities extends DesiredCapabilities {
    protected AndroidDriver driver;
    private AppiumDriverLocalService service;

    protected void preparation() throws Exception {
        File app = new File("/Users/Q1/Desktop/MyPc/projeler/QA/foreskTrader.apk.zip");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
//        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Espresso");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability("appium:appPackage", "foreks.android");
        capabilities.setCapability("appium:appActivity", "foreks.android.SplashActivity");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
//        capabilities.setCapability("wdaStartupRetries", "8");
//        capabilities.setCapability("wdaStartupRetryInterval", "20000");

        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        String service_url = service.getUrl().toString();
        System.out.println("Appium Service Address: " + service_url);

        driver = new AndroidDriver<>(
                new URL(service_url),
                capabilities);
//        driver = new AndroidDriver(new URL(service_url), capabilities);
    }

    public void stopServer() {
        service.stop();
    }
}