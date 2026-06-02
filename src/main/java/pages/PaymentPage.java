package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class PaymentPage {

    private final Page page;

    //== Locators ==//

    //Card details
    private final Locator nameOnCard;
    private final Locator cardNumber;
    private final Locator CVCNumber;
    private final Locator expirationRateMonth;
    private final Locator expirationRateYear;

    private final Locator confirmOrderButton;

    public PaymentPage(Page page) {
        this.page = page;

        this.nameOnCard = page.locator("[data-qa='name-on-card']");
        this.cardNumber = page.locator("[data-qa='card-number']");
        this.CVCNumber = page.locator("[data-qa='cvc']");
        this.expirationRateMonth = page.locator("[data-qa='expiry-month']");
        this.expirationRateYear = page.locator("[data-qa='expiry-year']");

        this.confirmOrderButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Pay and Confirm Order"));
    }

    public void enterCardDetails(String name, String number, String cvc, String expirationMonth, String expirationYear){
        nameOnCard.fill(name);
        cardNumber.fill(number);
        CVCNumber.fill(cvc);
        expirationRateMonth.fill(expirationMonth);
        expirationRateYear.fill(expirationYear);
    }

    public void confirmOrder(){ confirmOrderButton.click(); }

}
