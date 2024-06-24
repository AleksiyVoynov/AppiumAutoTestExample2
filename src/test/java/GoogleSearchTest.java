import org.junit.jupiter.api.Test;
import pages.GoogleSearchPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest extends BaseTest {

    @Test
    public void testGoogleSearch() {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage(appiumDriver);
        var testData = "facebook";

        googleSearchPage.navigateTo("https://www.google.com");
        googleSearchPage.searchFor(testData);
        assertTrue(googleSearchPage.getSearchingResult(testData), "nothing was found");
    }
}
