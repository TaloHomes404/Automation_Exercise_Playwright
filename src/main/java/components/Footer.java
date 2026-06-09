package components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class Footer {

    private final Page page;

    //== Locators ==//
    private final Locator subscriptionEmailField;
    private final Locator subscriptionSendButton;


    public Footer(Page page){
        this.page = page;

        this.subscriptionEmailField = page.locator("input#susbscribe_email");
        this.subscriptionSendButton = page.locator("#subsribe");
    }

    public void subscribeWithEmail(String email){
        subscriptionEmailField.fill(email);
        subscriptionSendButton.click();
    }


}
