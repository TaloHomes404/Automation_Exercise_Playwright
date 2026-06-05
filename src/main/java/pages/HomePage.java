package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import config.ConfigManager;

public class HomePage {

    private final Page page;

    //== Locators ==//
    private final Locator sleevelessDressAddToCartButton;
    private final Locator sleevelessDressViewProductButton;

    public HomePage(Page page) {
        this.page = page;

        this.sleevelessDressAddToCartButton = page.locator("data-product-id='3'");
        this.sleevelessDressViewProductButton = page.locator("a[href='/product_details/3']");
    }

    public void open() { page.navigate(ConfigManager.BASE_URL); }

    public void addProductToCart(int productId){
        page.locator("[data-product-id='" + productId + "']").first().click();
    }

    public void openProductDetails(String productId){
        page.locator("a[href='/product_details/" + productId + "']").click();
    }

}
