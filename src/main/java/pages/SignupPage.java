package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SignupPage {

    private final Page page;

    //== Locators ==//

    //Gender radio buttons
    private final Locator maleTitleRadioButton;
    private final Locator femaleTitleRadioButton;

    //Credentials
    private final Locator nameField;
    private final Locator emailField;
    private final Locator passwordField;

    //Date of birth
    private final Locator dateOfBirthDaysCheckbox;
    private final Locator dateOfBirthMonthsCheckbox;
    private final Locator dateOfBirthYearsCheckbox;

    //Newsletter - Signup
    private final Locator newsletterSignupCheckbox;
    private final Locator specialOfferCheckbox;

    //Address section
    private final Locator firstNameField;
    private final Locator lastNameField;
    private final Locator addressField;
    private final Locator countrySelectbox;
    private final Locator stateField;
    private final Locator cityField;
    private final Locator zipcodeField;
    private final Locator phoneNumberField;

    private final Locator createAccountButton;

    public SignupPage(Page page) {
        this.page = page;

        //Gender radio buttons
        this.maleTitleRadioButton = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Mr"));
        this.femaleTitleRadioButton = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Mrs"));

        //Credentials
        this.nameField = page.locator("data-qa='name'");
        this.emailField = page.locator("data-qa='email'");
        this.passwordField = page.locator("data-qa='password'");

        //Date of birth
        this.dateOfBirthDaysCheckbox = page.locator("data-qa='days'");
        this.dateOfBirthMonthsCheckbox = page.locator("data-qa='months'");
        this.dateOfBirthYearsCheckbox = page.locator("data-qa='years'");

        //Newsletter - Signup
        this.newsletterSignupCheckbox = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("newsletter"));
        this.specialOfferCheckbox = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("optin"));

        //Address section
        this.firstNameField = page.locator("data-qa='first_name'");
        this.lastNameField = page.locator("data-qa='last-name'");
        this.addressField = page.locator("data-qa='address'");
        this.countrySelectbox = page.locator("data-qa='country'");
        this.stateField = page.locator("data-qa='state'");
        this.cityField = page.locator("data-qa='city'");
        this.zipcodeField = page.locator("data-qa='zipcode'");
        this.phoneNumberField = page.locator("data-qa='mobile_number'");

        this.createAccountButton = page.locator("data-qa='create-account'");

    }



}

