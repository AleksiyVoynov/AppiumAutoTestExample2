package pages;

import com.google.common.collect.ImmutableMap;
import configs.Config;
import configs.app.ChromeApp;
import configs.app.SafariApp;
import configs.devices.simulators.Android;
import configs.devices.simulators.IOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.NoSuchContextException;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.DriverCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BasePage extends Config {
    protected final AppiumDriver appiumDriver;

    public BasePage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void webViewContext() {
        if (device instanceof Android) {
            ((SupportsContextSwitching) appiumDriver).context(new ChromeApp(waitWebView()).webViewContext);
        } else if (device instanceof IOS) {
            ((SupportsContextSwitching) appiumDriver).context(new SafariApp(waitWebView()).webViewContext);
        }
    }

    public void nativeContext() {
        if (device instanceof Android) {
            appiumDriver.execute(DriverCommand.SWITCH_TO_CONTEXT, ImmutableMap.of("name", "NATIVE_APP"));
        } else if (device instanceof IOS){
            ((SupportsContextSwitching) appiumDriver).context("NATIVE_APP");
        }
    }

    private List<String> waitWebView() {
        List<String> contextNames = new ArrayList<>();

        int attempt = 3;
        int sizeList = 0;

        while (sizeList < 2 && attempt != 0) {
            Set<String> set = ((SupportsContextSwitching) appiumDriver).getContextHandles();
            contextNames.clear();
            contextNames.addAll(set);
            sizeList = contextNames.size();
            attempt--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return contextNames;
    }

    @Step("go to url")
    public void navigateTo(String url) {
        try {
            webViewContext();
            appiumDriver.get(url);
        } catch (NoSuchContextException ignored) {
            nativeContext();
            appiumDriver.get(url);
            webViewContext();
        }
    }
}
