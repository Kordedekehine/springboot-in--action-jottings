package brain.springframework.bookapp.securityTests;

import brain.springframework.bookapp.entity.Reader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SecurityTests {

    @Autowired
    private WebApplicationContext webContext; //injects webApplicationContext
    private MockMvc mockMvc;

  //  @Before
//    public void setupMockMvc() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(webContext)
//                .apply(springSecurity())
//                .build();
//    }
    /**
     * With Spring Security enabled, we can no longer simply request the home page and
     * expect an HTTP 200 response. If the request isn’t authenticated, we should expect a
     * redirect to the login page:
     */

//    @Test
//    public void homePage_unauthenticatedUser() throws Exception {
//        mockMvc.perform(get("/"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(header().string("Location",
//                        "http://localhost/login"));
//    }

/**
 * But how can we perform an authenticated request? Spring Security offers two annotations that can help:
 * ■ @WithMockUser—Loads the security context with a UserDetails using the given
 * username, password, and authorization
 * ■ @WithUserDetails—Loads the security context by looking up a UserDetails
 * object for the given username
 * In both cases, Spring Security’s security context is loaded with a UserDetails object
 * that is to be used for the duration of the annotated test method. The @WithMockUser
 * annotation is the most basic of the two. It allows you to explicitly declare a UserDetails
 * to be loaded into the security context:
 */

//@Test
//@WithMockUser(username="craig",
//        password="password",
//        roles="READER")
//public void homePage_authenticatedUser() throws Exception {
//
//}


/**
 * As you can see, @WithMockUser bypasses the normal lookup of a UserDetails object
 * and instead creates one with the values specified. For simple tests, this may be fine. But
 * for our test, we need a Reader (which implements UserDetails) instead of the generic
 * UserDetails that @WithMockUser creates. For that, we’ll need @WithUserDetails.
 *  The @WithUserDetails annotation uses the configured UserDetailsService to
 * load the UserDetails object. As you’ll recall from chapter 3, we configured a UserDetailsService
 * bean that looks up and returns a Reader object for a given username.
 * That’s perfect! So we’ll annotate our test method with @WithUserDetails, as shown in
 * the following listing
  */

//@Test
//@WithUserDetails("craig")
//public void homePage_authenticatedUser() throws Exception {
//    Reader expectedReader = new Reader();
//    expectedReader.setUsername("craig");
//    expectedReader.setPassword("password");
//    expectedReader.setFullname("Craig Walls");
//    mockMvc.perform(get("/"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("readingList"))
//            .andExpect(model().attribute("reader",
//                    samePropertyValuesAs(expectedReader)))
//            .andExpect(model().attribute("books", hasSize(0)));
//}
}
