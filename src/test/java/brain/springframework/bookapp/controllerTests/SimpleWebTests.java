package brain.springframework.bookapp.controllerTests;

import brain.springframework.bookapp.bookappApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.Assert.assertEquals;

/**
 * it’s easy enough to ask Spring Boot to start up the server on a randomly selected port. One way is to set the server.port property to 0 to ask Spring
 * Boot to select a random available port. @WebIntegrationTest accepts an array of
 * String for its value attribute. Each entry in the array is expected to be a name/value
 * pair, in the form name=value, to set properties for use in the test. To set server.port
 * you can use @WebIntegrationTest like this:
 * @WebIntegrationTest(value={"server.port=0"})
 * Or, because there’s only one property being set, it can take a simpler form:
 * @WebIntegrationTest("server.port=0")
 * Setting properties via the value attribute is handy in the general sense, but @WebIntegrationTest also offers a randomPort attribute for a more expressive way of asking
 * the server to be started on a random port. You can ask for a random port by setting
 * randomPort to true:
 * @WebIntegrationTest(randomPort=true)
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(
//        classes= bookappApplication.class)
//@WebIntegrationTest
@SpringBootTest
public class SimpleWebTests {

    /**
     *  First we’ll need to inject the chosen port as an instance variable. To make this convenient,
     *  Spring Boot sets a property with the name local.server.port to the value of
     * the chosen port. All we need to do is use Spring’s @Value to inject that property:
     * @Value("${local.server.port}")
     * private int port;
     */

    @Value("${local.server.port}")
    private int port;

    @Test(expected= HttpClientErrorException.class)
    public void pageNotFound() {
        try {
            RestTemplate rest = new RestTemplate();
            rest.getForObject(
                    /**
                     * Here we’ve traded the hardcoded 8080 for a {port} placeholder in the URL. By
                     * passing the port property as the last parameter in the getForObject() call,
                     * we can be
                     * assured that the placeholder will be replaced with whatever value was injected into
                     * port.
                     */
                    "http://localhost:{port}/bogusPage", String.class, port);
            fail("Should result in HTTP 404");
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            throw e;
        }
    }

    /**
     * Although this is a very simple test, it sufficiently demonstrates how to use the
     * @WebIntegrationTest to start the application with a server. The actual server that’s
     * started will be determined in the same way it would be if we were running the application at
     * the command line. By default, it starts Tomcat listening on port 8080. Optionally,
     * however, it could start Jetty or Undertow if either of those is in the classpath.
     *  The body of the test method is written assuming that the application is running
     * and listening on port 8080. It uses Spring’s RestTemplate to make a request for a nonexistent
     * page and asserts that the response from the server is an HTTP 404 (not
     * found). The test will fail if any other response is returned.
     */


}
