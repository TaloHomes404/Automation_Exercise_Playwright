package factory;

import com.microsoft.playwright.*;
import config.ConfigManager;

public class PlaywrightFactory {

    //== Init vals ==//
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;


    //Methods - Browser start point and flush context/browser

    public static Page initBrowser(){
        playwright = Playwright.create();

        BrowserType browserType = switch (ConfigManager.BROWSER){
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> playwright.chromium();
        };

        browser = browserType.launch(
                new BrowserType.LaunchOptions().setHeadless(ConfigManager.HEADLESS).setSlowMo(1000)
        );

        context = browser.newContext();
        page = context.newPage();
        return page;
    }

    public static void closeBrowser(){
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }

}
