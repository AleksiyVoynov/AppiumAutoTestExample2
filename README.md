# Requirements:
* MacOS Sonoma and above
* Appium 2.0 and above
* For Android, you should have the virtual device from Android Studio
* For iOS, you should have a simulator from Xcode and set up WebDriverAgent
* Set up Google Chrome on devices


# Guide:
1. Start the Appium server for Android or iOS.

    * For Android:
      ```sh
      appium -a 127.0.0.1 -p 4732 --session-override --log-timestamp --local-timezone --allow-insecure chromedriver_autodownload
      ```

    * For iOS:
      ```sh
      appium -a 0.0.0.0 -p 4723 --session-override --relaxed-security --driver-xcuitest-webdriveragent-port 8100 --log-timestamp --local-timezone
      ```

2. Appium settings can be found here:
   ```plaintext
   src/main/java/configs/AppiumConfig.java

3. Put your device settings here:
   ```plaintext
   src/main/java/configs/Config.java

4. Then run 'GoogleSearchTest'
   ```plaintext
   src/test/java/GoogleSearchTest.java
   
5. After run, you can generate report put to console next line:
   ```plaintext
   allure serve build/allure-results
