package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ViewCartPage {

    private final Page page;

    //== Locators ==//
    private final Locator emptyCartInfo;
    private final Locator proceedToCheckoutButton;

    //Cart table fields
    private final Locator cartTableDescription;
    private final Locator cartTableQuantity;
    private final Locator cartTablePrice;
    private final Locator cartTableTotal;

    private final Locator deleteProductButton;


    public ViewCartPage(Page page) {
        this.page = page;

        this.emptyCartInfo = page.locator("span#empty_cart");
        this.proceedToCheckoutButton = page.getByText("Proceed To Checkout");

        this.cartTableDescription = page.locator("td.description");
        this.cartTableQuantity = page.locator("td.quantity");
        this.cartTablePrice = page.locator("td.price");
        this.cartTableTotal = page.locator("td.total");

        this.deleteProductButton = page.locator("a.cart_quantity_delete");
    }

    public void removeProduct(String product){
        Locator row = page.locator("tr")
                .filter(new Locator.FilterOptions().setHasText(product));
        row.locator(".cart_quantity_delete").click();
    }

    public void proceedToCheckout() { proceedToCheckoutButton.click(); }

    public int countItemsInCart(){
      return page.locator("tr").count();
    }

    public boolean isProductInCart(int productId){
        return page.locator("#product-" + productId).isVisible();
    }

    public int getQuantityOfProduct(int productId){
        Locator row = page.locator("tr#product-" + productId);
        return Integer.parseInt(row.locator("td.cart_quantity").textContent().trim());
    }

    public int getTotalCostOfProduct(int productId){
        Locator row = page.locator("tr#product-" + productId);
        return Integer.parseInt(row.locator(".cart_total p").textContent().replace("Rs.", "").trim());
    }

    public int getPriceOfProduct(int productId){
        Locator row = page.locator("tr#product-" + productId);
        return Integer.parseInt(row.locator(".cart_price p").textContent().replace("Rs.", "").trim());
    }

}
