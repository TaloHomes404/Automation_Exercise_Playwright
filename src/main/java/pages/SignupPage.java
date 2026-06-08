package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import enums.Gender;
import models.User;

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

    private final Locator accountCreatedText;
    private final Locator emailExistsError;
    private final Locator createAccountButton;

    public SignupPage(Page page) {
        this.page = page;

        //Gender radio buttons
        this.maleTitleRadioButton = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Mr.").setExact(true));
        this.femaleTitleRadioButton = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Mrs.").setExact(true));

        //Credentials
        this.nameField = page.locator("[data-qa='name']");
        this.emailField = page.locator("[data-qa='email']");
        this.passwordField = page.locator("[data-qa='password']");

        //Date of birth
        this.dateOfBirthDaysCheckbox = page.locator("[data-qa='days']");
        this.dateOfBirthMonthsCheckbox = page.locator("[data-qa='months']");
        this.dateOfBirthYearsCheckbox = page.locator("[data-qa='years']");

        //Newsletter - Signup
        this.newsletterSignupCheckbox = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("newsletter"));
        this.specialOfferCheckbox = page.locator("#optin");

        //Address section
        this.firstNameField = page.locator("[data-qa='first_name']");
        this.lastNameField = page.locator("[data-qa='last_name']");
        this.addressField = page.locator("[data-qa='address']");
        this.countrySelectbox = page.locator("[data-qa='country']");
        this.stateField = page.locator("[data-qa='state']");
        this.cityField = page.locator("[data-qa='city']");
        this.zipcodeField = page.locator("[data-qa='zipcode']");
        this.phoneNumberField = page.locator("[data-qa='mobile_number']");

        this.accountCreatedText = page.locator("[data-qa='account-created']");
        this.emailExistsError = page.getByText("Email Address already exist!");
        this.createAccountButton = page.locator("[data-qa='create-account']");
    }

    public void signupForNewsletter() {
        newsletterSignupCheckbox.check();
    }

    public void signupForSpecialOffers(){
        specialOfferCheckbox.check();
    }

    public void selectGender(Gender gender) {
        switch (gender) {
            case FEMALE -> femaleTitleRadioButton.click();
            case MALE -> maleTitleRadioButton.click();
        }
    }

    public void enterPassword(String password){
        passwordField.fill(password);
    }

    public void enterDateOfBirth(int days, int months, int years){
        dateOfBirthDaysCheckbox.selectOption(String.valueOf(days));
        dateOfBirthMonthsCheckbox.selectOption(String.valueOf(months));
        dateOfBirthYearsCheckbox.selectOption(String.valueOf(years));
    }

    public void enterAddressInformations(String firstName, String lastName, String address){
        firstNameField.fill(firstName);
        lastNameField.fill(lastName);
        addressField.fill(address);
    }

    public void selectCountry(String country){
        countrySelectbox.selectOption(country);
    }

    public void enterAddressDetails(String state, String city, String zipcode){
        stateField.fill(state);
        cityField.fill(city);
        zipcodeField.fill(zipcode);
    }



    public void enterPhoneNumber(String phoneNumber){
        phoneNumberField.fill(phoneNumber);
    }

    public void clickCreateAccount(){
        createAccountButton.click();
    }

    public Locator getAccountCreatedText(){
        return accountCreatedText;
    }

    public void completeUserRegistration(User user){

        selectGender(user.getGender());

        enterPassword(user.getPassword());

        enterDateOfBirth(
                user.getBirthDay(),
                user.getBirthMonth(),
                user.getBirthYear());

        signupForNewsletter();

        signupForSpecialOffers();

        enterAddressInformations(
                user.getFirstName(),
                user.getLastName(),
                user.getAddress());

        selectCountry(user.getCountry());

        enterAddressDetails(
                user.getState(),
                user.getCity(),
                user.getZipcode());

        enterPhoneNumber(user.getPhoneNumber());

        clickCreateAccount();

    }

}

