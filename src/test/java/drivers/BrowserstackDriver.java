package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;


public class BrowserstackDriver implements WebDriverProvider {

    public static WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("browserstack.user", config.getBrowserstackUser());
        caps.setCapability("browserstack.key", config.getBrowserstackKey());
        caps.setCapability("app", config.getApp());
        caps.setCapability("device", config.getDevice());
        caps.setCapability("os_version", config.getOsVersion());

        try {
            return new RemoteWebDriver(
                    new URL(config.getRemoteUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}