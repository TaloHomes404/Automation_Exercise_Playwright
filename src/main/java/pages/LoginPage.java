package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import config.ConfigManager;
import io.qameta.allure.Step;

public class LoginPage {
    private final Page page;

    //== Locators ==//
    //Login Section
    private final Locator loginEmailField;
    private final Locator loginPasswordField;
    private final Locator loginButton;

    //Signup Section
    private final Locator usernameSignupField;
    private final Locator emailSignupField;
    private final Locator signupButton;

    public LoginPage(Page page){
        this.page = page;

        //Login
        this.loginEmailField = page.locator("[data-qa='login-email']");
        this.loginPasswordField = page.locator("[data-qa='login-password']");
        this.loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));

        //Signup
        this.usernameSignupField = page.locator("[data-qa='signup-name']");
        this.emailSignupField = page.locator("[data-qa='signup-email']");
        this.signupButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Signup"));
    }

    @Step("Open login page")
    public void open() { page.navigate(ConfigManager.BASE_URL + "login"); }

    @Step("Enter e-mail ({email}) and password ({password}) and login user")
    public void login(String email, String password){
        loginEmailField.fill(email);
        loginPasswordField.fill(password);
        loginButton.click();
    }

    @Step("Enter name ({name}) and e-mail ({email}) in pre-register fields and signup")
    public void preRegisterAndClickSignupButton(String name, String email){
        usernameSignupField.fill(name);
        emailSignupField.fill(email);
        signupButton.click();
    }

    public Locator incorrectCredentialsError(){
        return page.getByText("Your email or password is incorrect!");
    }
}
