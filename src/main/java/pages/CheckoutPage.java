package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CheckoutPage {

    private final Page page;

    //== Locators ==//
    private final Locator placeOrderButton;
    private final Locator orderCommentField;

    //Checkout / Price values
    private final Locator totalAmountValue;

    //Products in Checkout Table
    private final Locator cartDescription;

    public CheckoutPage(Page page) {
        this.page = page;

        this.placeOrderButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Place Order"));
        this.orderCommentField = page.locator("textarea[name='message']");

        this.totalAmountValue = page.locator("p.cart_total_price");

        this.cartDescription = page.locator("td.cart_description");
    }

    public boolean containsProduct(String productName){
        return cartDescription.getByText(productName).count() > 0;
    }

    public void clickPlaceOrder(){
        placeOrderButton.click();
    }

}
