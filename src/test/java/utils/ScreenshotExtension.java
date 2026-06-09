package utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.io.ByteArrayInputStream;

public class ScreenshotExtension {

    public static void attachScreenshot(Page page) {
        byte[] screenshot = page.screenshot(
                new Page.ScreenshotOptions().setFullPage(true)
        );

        Allure.addAttachment(
                "Fail Test Screenshot",
                "image/png",
                new ByteArrayInputStream(screenshot),
                "png"
        );
    }

    public static void attachCurrentUrl(Page page) {
        String url = page.url();

        Allure.addAttachment(
                "Current URL",
                "text/plain",
                url
        );
    }
}
