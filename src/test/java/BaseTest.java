import configs.Config;
import configs.devices.simulators.Android;
import configs.devices.simulators.IOS;
import configs.platformConfig.android.AndroidConfig;
import configs.platformConfig.ios.IOSConfig;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;

@ExtendWith(AllureJunit5.class)
public class BaseTest extends Config {
    protected static AppiumDriver appiumDriver;

    @BeforeAll
    @Step("Setting up Appium driver")
    protected static void setUp() throws Exception {
        if (device instanceof Android) {
            AndroidConfig androidConfig = new AndroidConfig();
            androidConfig.initDriver(Config.device.capabilities);
            appiumDriver = androidConfig.android;
        } else if (device instanceof IOS) {
            IOSConfig iosConfig = new IOSConfig();
            iosConfig.initDriver(Config.device.capabilities);
            appiumDriver = iosConfig.ios;
        } else {
            Assertions.fail("unknown device platform");
        }
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @AfterAll
    @Step("Tearing down Appium driver")
    protected static void tearDown() {
        appiumDriver.quit();
    }
}
