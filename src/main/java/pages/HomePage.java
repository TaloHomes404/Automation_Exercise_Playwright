package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import config.ConfigManager;
import io.qameta.allure.Step;

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

    @Step("Open home page")
    public void open() { page.navigate(ConfigManager.BASE_URL); }

    @Step("Add product with id {productId} to cart from home page")
    public void addProductToCart(int productId){
        page.locator("[data-product-id='" + productId + "']").first().click();
    }

    public void openProductDetails(String productId){
        page.locator("a[href='/product_details/" + productId + "']").click();
    }

}
