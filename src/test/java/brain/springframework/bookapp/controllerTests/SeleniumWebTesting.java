package brain.springframework.bookapp.controllerTests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * Note- we're testing our web with selenium. Then we must have add the dependency first
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(
     //   classes=ReadingListApplication.class)
//@WebIntegrationTest(randomPort=true) //we want it to start on a random port
public class SeleniumWebTesting {

    //private static FirefoxDriver browser;

    @Value("${local.server.port}")
    private int port;

//    @BeforeClass
//    public static void openBrowser() {
//        browser = new FirefoxDriver();
//        browser.manage().timeouts()
//                .implicitlyWait(10, TimeUnit.SECONDS); //set up firefox driver
//    }
//
//    @AfterClass
//    public static void closeBrowser() {
//        browser.quit(); //shuts down browser
//    }

    /**
     * As with the simpler web test we wrote earlier, this class is annotated with @WebIntegrationTest
     * and sets randomPort to true so that the application will be started and run
     * with a server listening on a random port. And, as before, that port is injected into the
     * port property so that we can use it to construct URLs to the running application.
     *  The static openBrowser() method creates a new instance of FirefoxDriver, which
     * will open a Firefox browser (it will need to be installed on the machine running the
     * test). When we write our test method, we’ll perform browser operations through the
     * FirefoxDriver instance. The FirefoxDriver is also configured to wait up to 10
     * seconds when looking for any elements on the page (in case those elements are slow
     * to load).
     *  After the test has completed, we’ll need to shut down the Firefox browser.
     *  Therefore, closeBrowser() calls quit() on the FirefoxDriver instance to bring it down.
     * PICK YOUR BROWSER Although we’re testing with Firefox, Selenium also provides drivers
     * for several other browsers, including Internet Explorer, Google’s
     * Chrome, and Apple’s Safari. Not only can you use other browsers, it’s probably
     * a good idea to write your tests to use any and all browsers you want to support.
     * Now we can write our test method. As a reminder, we want to load the home page, fill
     * in and submit the form, and then assert that we land on a page that includes our newly
     * added book in the list. The following listing shows how to do this with Selenium.
     */


    /**
     * The very first thing that the test method does is use the FirefoxDriver to perform a
     * GET request for the reading list’s home page. It then looks for a <div> element on the
     * page and asserts that its text indicates that no books are in the list.
     *  The next several lines look for the fields in the form and use the driver’s sendKeys()
     *  method to simulate keystroke events on those field elements (essentially filling
     * in those fields with the given values). Finally, it looks for the <form> element and submits it.
     *  After the form submission is processed, the browser should land on a page with the
     * new book in the list. So the final few lines look for the <dt> and <dd> elements in that
     * list and assert that they contain the data that the test submitted in the form.
     *  When you run this test, you’ll see the browser pop up and load the reading-list
     * application. If you pay close attention, you’ll see the form filled out, as if by a ghost.
     * But it’s no spectre using your application—it’s the test.
     *  The main thing to notice about this test is that @WebIntegrationTest was able to
     * start up the application and server for us so that Selenium could start poking at it with
     * a web browser. But what’s especially interesting about how this works is that you can use
     * the test facilities of your IDE to run as many or as few of these tests as you want, without
     * having to rely on some plugin in your application’s build to start a server for you.
     *  If testing with Selenium is something that you think you’ll find useful, you should
     * check out Selenium WebDriver in Practice by Yujun Liang and Alex Collins (http://
     * manning.com/liang/), which goes into far more details about testing with Selenium.
     */

//    @Test
//    public void addBookToEmptyList() {
//        String baseUrl = "http://localhost:" + port;
//        browser.get(baseUrl);
//        assertEquals("You have no books in your book list",
//                browser.findElementByTagName("div").getText());
//        browser.findElementByName("title")
//.sendKeys("BOOK TITLE");
//        browser.findElementByName("author")
//                .sendKeys("BOOK AUTHOR");
//        browser.findElementByName("isbn")
//                .sendKeys("1234567890");
//        browser.findElementByName("description")
//                .sendKeys("DESCRIPTION");
//        browser.findElementByTagName("form")
//                .submit();
//        WebElement dl =
//                browser.findElementByCssSelector("dt.bookHeadline");
//        assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)",
//                dl.getText());
//        WebElement dt =
//                browser.findElementByCssSelector("dd.bookDescription");
//        assertEquals("DESCRIPTION", dt.getText());
//    }
}
