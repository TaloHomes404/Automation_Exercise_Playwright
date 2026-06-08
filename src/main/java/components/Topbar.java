package components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class Topbar {

    private final Page page;

    //== Locators ==//
    private final Locator homeTopbarItem;
    private final Locator productsTopbarItem;
    private final Locator cartTopbarItem;
    private final Locator loginTopbarItem;

    public Topbar(Page page){
        this.page = page;

        this.homeTopbarItem = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home"));
        this.productsTopbarItem = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Products"));
        this.cartTopbarItem = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart"));
        this.loginTopbarItem = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login"));

    }


    public void clickHome(){ homeTopbarItem.click(); }
    public void clickProducts(){ productsTopbarItem.click(); }
    public void clickCart(){ cartTopbarItem.click(); }
    public void clickLogin(){ loginTopbarItem.click(); }

    public Locator loggedUserLabel() { return page.getByText("Logged in as"); }

}
