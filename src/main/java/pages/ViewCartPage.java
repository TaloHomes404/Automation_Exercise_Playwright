package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ViewCartPage {

    private final Page page;

    //== Locators ==//
    private final Locator emptyCartInfo;
    private final Locator proceedToCheckoutButton;

    public ViewCartPage(Page page) {
        this.page = page;

        this.emptyCartInfo = page.locator("span#empty_cart");
        this.proceedToCheckoutButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed to Checkout"));

    }
}
