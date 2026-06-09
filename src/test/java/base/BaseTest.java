package base;

import com.microsoft.playwright.Page;
import components.Topbar;
import factory.PlaywrightFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;
import utils.PopupUtils;
import utils.ScreenshotExtension;
import utils.TestResultWatcher;

@ExtendWith(TestResultWatcher.class)
public class BaseTest {

    // == PAGE == //
    protected Page page;

    //  == POM - Pages == //
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected SignupPage signupPage;
    protected ProductsPage productsPage;
    protected ViewCartPage viewCartPage;
    protected CheckoutPage checkoutPage;
    protected PaymentPage paymentPage;
    protected Topbar topbar;
    protected PopupUtils popupUtils;

    @BeforeEach
    void setup(){
        page = PlaywrightFactory.initBrowser();

        page.route("**/*", route -> {
            String url = route.request().url();

            if (url.contains("googlesyndication")
                    || url.contains("doubleclick")
                    || url.contains("googleads")) {

                route.abort();
            } else {
                route.resume();
            }
        });

        homePage = new HomePage(page);
        loginPage = new LoginPage(page);
        signupPage = new SignupPage(page);
        productsPage = new ProductsPage(page);
        viewCartPage = new ViewCartPage(page);
        checkoutPage = new CheckoutPage(page);
        paymentPage = new PaymentPage(page);
        popupUtils = new PopupUtils(page);
        topbar = new Topbar(page);
    }

    @AfterEach
    void tearDown(){
        PlaywrightFactory.closeBrowser();
    }

    public Page getPage() {
        return page;
    }
}
