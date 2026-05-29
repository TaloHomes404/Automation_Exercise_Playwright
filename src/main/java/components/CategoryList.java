package components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CategoryList {

    private final Page page;

    //== Locators ==//
    private final Locator womenCategory;
    private final Locator menCategory;
    private final Locator kidsCategory;

    public CategoryList(Page page){
        this.page = page;

        this.womenCategory = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Women"));
        this.menCategory = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Men"));
        this.kidsCategory = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Kids"));
    }

    public void clickWomenCategoryOnList(){ womenCategory.click(); }
    public void clickMenCategoryOnList(){ menCategory.click(); }
    public void clickKidsCategoryOnList(){ kidsCategory.click(); }

    public boolean isWomenCategoryExpanded(){
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Dress")).isVisible();
    }

    public boolean isMenCategoryExpanded(){
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Jeans")).isVisible();
    }

    public boolean isKidsCategoryExpanded(){
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Tops & Shirts")).isVisible();
    }


}
