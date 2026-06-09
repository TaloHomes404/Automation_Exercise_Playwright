package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import config.ConfigManager;
import io.qameta.allure.Step;

public class ProductsPage {

    private final Page page;

    private final Locator searchProductField;
    private final Locator submitSearchButton;

    public ProductsPage(Page page) {
        this.page = page;

        this.searchProductField = page.locator("#search_product");
        this.submitSearchButton = page.locator("#submit_search");
    }

    @Step("Enter product name {name} and click search")
    public void searchForProduct(String name){
        searchProductField.fill(name);
        submitSearchButton.click();
    }

    public Locator searchedItems(){
        return page.locator(".productinfo p");
    }

    public Locator getProduct(String productName){
        return page.locator(".productinfo p")
                .filter(new Locator.FilterOptions().setHasText(productName));
    }


    @Step("Click \"View Product\" on product with id {productId}")
    public void openViewProductPage(int productId) { page.navigate(ConfigManager.BASE_URL + "product_details/" + productId); }

    @Step("Set quantity of product to {quantity} and add to cart")
    public void setQuantityAndAddToCart(int quantity){
        Locator quantityBox = page.locator("input#quantity");
        quantityBox.clear();
        quantityBox.fill(String.valueOf(quantity));
        page.getByText("Add to cart").click();
    }

    public void open() { page.navigate(ConfigManager.BASE_URL + "products"); }

    @Step("Add product with id {productId} to cart")
    public void addProductToCart(int productId){
        Locator featuredItemsSection = page.locator("div.features_items");
        Locator productColumn = featuredItemsSection.locator("div.productinfo");
        productColumn.locator("a[data-product-id='" + productId + "']").click();
    }

}
