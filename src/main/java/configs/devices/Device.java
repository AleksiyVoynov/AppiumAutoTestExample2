package configs.devices;

import configs.app.App;

public abstract class Device {
    public App app;
    public String os;
    public String origin;
    public String model;
    public String platformVersion;
    public String uDID;

    @Override
    public String toString() {
        return "Device{" +
                "app=" + app +
                ", os='" + os + '\'' +
                ", origin='" + origin + '\'' +
                ", model='" + model + '\'' +
                ", platformVersion='" + platformVersion + '\'' +
                '}';
    }
}
