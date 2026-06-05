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
    private final Locator adPopup;

    //Product page modal
    private final Locator cartModal;
    private final Locator viewCartModalLink;
    private final Locator continueShoppingButton;

    // Register / Login modal
    private final Locator registerOrLoginAccountLink;
    private final Locator continueOnCartButton;

    public PopupUtils(Page page) {
        this.page = page;

        this.acceptCookiesButton = page.locator("button.fc-cta-consent");
        this.cookiesPopup = page.locator(".fc-choice-dialog");

        this.adPopup = page.locator("iframe[title='Advertisement']").filter(new Locator.FilterOptions().setVisible(true)).first();

        this.cartModal = page.locator("div.modal-content");
        this.viewCartModalLink = cartModal.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("View Cart"));
        this.continueShoppingButton = cartModal.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Continue Shopping"));

        this.registerOrLoginAccountLink = cartModal.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Register / Login"));
        this.continueOnCartButton = cartModal.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Continue On Cart"));
    }

    public void acceptCookiesIfPopupIsVisible(){
        if(cookiesPopup.isVisible()) { acceptCookiesButton.click(); }
    }

    public void closeAdPopup() {
        Locator ads = page.locator("iframe[title='Advertisement']");

        if (ads.count() > 0) {
            ads.first().contentFrame().locator(".close-button").click();
        }
    }

    // PRODUCT PAGE MODAL HANDLER
    public void clickContinueShoppingFromModal(){
        if(cartModal.isVisible()) continueShoppingButton.click();
    }

    public void clickViewCartFromModal(){
        if(cartModal.isVisible()) viewCartModalLink.click();
    }

    // LOGIN / REGISTER MODAL HANDLER

    public void clickRegisterOrLoginAccountFromModal() { if(cartModal.isVisible()) registerOrLoginAccountLink.click();  }

    public void clickContinueOnCartFromModal() { if(cartModal.isVisible()) continueOnCartButton.click();  }

}
