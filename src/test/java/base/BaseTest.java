package base;

import com.microsoft.playwright.Page;
import factory.PlaywrightFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected Page page;

    @BeforeEach
    void setup(){
        page = PlaywrightFactory.initBrowser();
    }

    @AfterEach
    void tearDown(){
        PlaywrightFactory.closeBrowser();
    }


}
