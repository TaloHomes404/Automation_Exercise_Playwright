package components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class BrandsList {

    private final Page page;

    //== Locators ==//
    private final Locator poloBrand;
    private final Locator madameBrand;
    private final Locator babyhugBrand;


    public BrandsList(Page page){
        this.page = page;

        this.poloBrand = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Polo"));
        this.madameBrand = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Madame"));
        this.babyhugBrand = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Babyhug"));

    }


}
