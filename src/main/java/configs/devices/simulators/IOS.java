package configs.devices.simulators;

import configs.AppiumConfig;
import configs.app.App;
import configs.devices.Device;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class IOS extends Device {

    public IOSDriver ios;

    public IOS(String model, String version, String uDID, String xcodeOrgId, App app) {
        this.app = app;
        this.os = "android";
        this.origin = "simulator";
        this.model = model;
        this.platformVersion = version;
        this.uDID = uDID;

        AppiumConfig appiumConfig = new AppiumConfig();

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("appium:platformVersion", platformVersion);
        cap.setCapability("appium:udid", uDID);
        cap.setCapability("appium:xcodeSigningId", "iPhone Developer");
        cap.setCapability("appium:forceSimulatorSoftwareKeyboardPresence", true);
        cap.setCapability("appium:xcodeOrgId", xcodeOrgId);
        cap.setCapability("appium:browserName", app.name);
        cap.setCapability("appium:automationName", "XCUITest");
        cap.setCapability("appium:webviewConnectTimeout", 2000);

        cap.setCapability("appium:derivedDataPath", findWebDriverAgentPath());
        try {
            this.ios = new IOSDriver(new URL("http://" + appiumConfig.appiumIOSIP + ":" + appiumConfig.appiumIOSPort + "/"), cap);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String findWebDriverAgentPath() {
        StringBuilder output = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", "find /Users/*/Library/Developer/Xcode/DerivedData -type d -name 'WebDriverAgent-*'");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.err.println("An error occurred while executing the command.");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return output.toString().trim();
    }
}
