package brain.springframework.bookapp.controllerTests;

import brain.springframework.bookapp.entity.Book;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The @WebAppConfiguration annotation declares that the application context created
 * by SpringJUnit4ClassRunner should be a WebApplicationContext (as opposed to a
 * basic non-web ApplicationContext).
 *  The setupMockMvc() method is annotated with JUnit’s @Before, indicating that it
 * should be executed before any test methods. It passes the injected WebApplicationContext into the webAppContextSetup() method and then calls build() to produce a
 * MockMvc instance, which is assigned to an instance variable for test methods to use.
 *  Now that we have a MockMvc, we’re ready to write some test methods. Let’s start
 * with a simple test method that performs an HTTP GET request against /readingList
 * and asserts that the model and view meet our expectations. The following homePage()
 * test method does what we need:
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = bookappApplication.class)
@SpringBootTest
@WebAppConfiguration  //enable web context testing
public class MockMvcWebTest {

    @Autowired
    private WebApplicationContext webContext; //injects webApplicationContext
    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders  //set up MockMvc
                .webAppContextSetup(webContext)
                .build();
    }


//@Test
//public void homePage() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/readingList"))
//        .andExpect(status().isOk())
//        .andExpect(view().name("readingList"))
//        .andExpect(model().attributeExists("books"))
//        .andExpect(model().attribute("books",
//        Matchers.is(Matchers.empty())));
//        }

    /**
     * As you can see, a lot of static methods are being used in this test method, including
     * static methods from Spring’s MockMvcRequestBuilders and MockMvcResultMatchers,
     * as well as from the Hamcrest library’s Matchers. Before we dive into the details of this
     * test method, let’s add a few static imports so that the code is easier to read:
     * import static org.hamcrest.Matchers.*;
     * import static org.springframework.test.web.servlet.request.
     *  ➥ MockMvcRequestBuilders.*;
     * import static org.springframework.test.web.servlet.result.
     *  ➥ MockMvcResultMatchers.*;
     * With those static imports in place, the test method can be rewritten like this:
     */

//@Test
//public void homePage() throws Exception {
//        mockMvc.perform(get("/readingList"))
//        .andExpect(status().isOk())
//        .andExpect(view().name("readingList"))
//        .andExpect(model().attributeExists("books"))
//        .andExpect(model().attribute("books", is(empty())));
//        }

    /**
     * Now the test method almost reads naturally. First it performs a GET request against
     * /readingList. Then it expects that the request is successful (isOk() asserts an HTTP 200
     * response code) and that the view has a logical name of readingList. It also asserts that
     * the model contains an attribute named books, but that attribute is an empty collection.
     * It’s all very straightforward.
     * The main thing to note here is that at no time is the application deployed to a web
     * server. Instead it’s run within a mocked out Spring MVC, just capable enough to handle
     * the HTTP requests we throw at it via the MockMvc instance.
     * Pretty cool, huh?
     */

    @Test
    public void postBook() throws Exception {
        mockMvc.perform(post("/readingList")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "BOOK TITLE")
                        .param("author", "BOOK AUTHOR")
                        .param("isbn", "1234567890")
                        .param("description", "DESCRIPTION"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/readingList"));
        //successfully performs post request

        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setReader("craig");
        expectedBook.setTitle("BOOK TITLE");
        expectedBook.setAuthor("BOOK AUTHOR");
        expectedBook.setIsbn("1234567890");
        expectedBook.setDescription("DESCRIPTION");
        //sets up expected book

//        mockMvc.perform(get("/readingList"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("readingList"))
//                .andExpect(model().attributeExists("books"))
//                .andExpect(model().attribute("books", hasSize(1)))
//                .andExpect(model().attribute("books",
//                        contains(samePropertyValuesAs(expectedBook))));
    }

    /**
     *  It’s actually two tests in one
     * method. The first part posts the book and asserts the results from that request. The
     * second part performs a fresh GET request against the home page and asserts that the
     * newly created book is in the model.
     *  When posting the book, we must make sure we set the content type to “application/
     * x-www-form-urlencoded” (with MediaType.APPLICATION_FORM_URLENCODED) as that
     * will be the content type that a browser will send when the book is posted in the running
     * application. We then use the MockMvcRequestBuilders’s param() method to set the
     * fields that simulate the form being submitted. Once the request has been performed,
     * we assert that the response is a redirect to /readingList.
     *  Assuming that much of the test method passes, we move on to part two. First, we
     * set up a Book object that contains the expected values. We’ll use this to compare with
     * the value that’s in the model after fetching the home page.
     *  Then we perform a GET request for /readingList. For the most part, this is no different than how we tested the home page before, except that instead of an empty collection in the model, we’re checking that it has one item, and that the item is the
     * same as the expected Book we created. If so, then our controller seems to be doing its
     * job of saving a book when one is posted to it.
     */
}
