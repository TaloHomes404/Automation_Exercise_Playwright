package utils;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class PopupUtils {

    private final Page page;

    //Accept cookies popup variables
    private final Locator cookiesPopup;
    private final Locator acceptCookiesButton;

    //Ads popups
    private final FrameLocator adPopup;

    public PopupUtils(Page page) {
        this.page = page;

        this.acceptCookiesButton = page.locator("button.fc-cta-consent");
        this.cookiesPopup = page.locator(".fc-choice-dialog");

        this.adPopup = page.frameLocator("iframe[title='Advertisement']");
    }

    public void acceptCookiesIfPopupIsVisible(){
        if(cookiesPopup.isVisible()) { acceptCookiesButton.click(); }
    }

    public void closeAdPopup(){
        Locator closeAdButton = adPopup.locator(".close-button");
        if(closeAdButton.isVisible()) { closeAdButton.click(); }
    }

}
