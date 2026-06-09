package utils;


import base.BaseTest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestWatcher;

import static utils.ScreenshotExtension.attachScreenshot;

public class TestResultWatcher implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(
            ExtensionContext context,
            Throwable throwable
    ) throws Throwable {

        BaseTest baseTest =
                (BaseTest) context.getRequiredTestInstance();

        if(baseTest.getPage() != null) {

            ScreenshotExtension.attachScreenshot(
                    baseTest.getPage()
            );

            ScreenshotExtension.attachCurrentUrl(
                    baseTest.getPage()
            );
        }

        throw throwable;
    }
}
