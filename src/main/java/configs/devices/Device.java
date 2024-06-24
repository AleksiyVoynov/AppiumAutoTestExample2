package configs.devices;

import configs.app.App;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class Device {
    public App app;
    public DeviceType type;
    public String model;
    public String name;
    public String platformVersion;
    public String uDID;

    public DesiredCapabilities capabilities;

    public class DeviceType {
        public String os;
        public String origin;
        public String device;

        public DeviceType(String os, String origin, String device) {
            this.os = os;
            this.origin = origin;
            this.device = device;
        }

        @Override
        public String toString() {
            return "DeviceType {" +
                    "os='" + os + '\'' +
                    ", origin='" + origin + '\'' +
                    ", device='" + device + '\'' +
                    '}';
        }
    }
}
