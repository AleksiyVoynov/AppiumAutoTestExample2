package configs.devices.simulators;

import configs.AppiumConfig;
import configs.app.App;
import configs.devices.Device;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Android extends Device {

    public AndroidDriver androidDriver;

    public Android(String model, String version, String uDID, App app)  {
        this.app = app;
        this.os = "android";
        this.origin = "simulator";

        this.model = model;
        this.platformVersion = version;
        this.uDID = uDID;

        AppiumConfig appiumConfig = new AppiumConfig();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:platformVersion", platformVersion);
        capabilities.setCapability("appium:deviceName", uDID);
        capabilities.setCapability("appium:browserName", app.name);
        capabilities.setCapability("appium:autoWebviewTimeout", 2000);
        try {
            androidDriver = new AndroidDriver(new URL("http://" + appiumConfig.appiumAndroidIP + ":" + appiumConfig.appiumAndroidPort + "/"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
