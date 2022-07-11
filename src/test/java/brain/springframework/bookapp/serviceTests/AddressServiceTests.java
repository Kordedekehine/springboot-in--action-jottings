package brain.springframework.bookapp.serviceTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * As you can see, AddressServiceTests is annotated with both @RunWith and @ContextConfiguration. @RunWith is given SpringJUnit4ClassRunner.class to enable Spring
 * integration testing.1
 *  Meanwhile, @ContextConfiguration specifies how to load the
 * application context. Here we’re asking it to load the Spring application context given
 * the specification defined in AddressBookConfiguration.
 */

/**
 * SpringJUnit4ClassRunner also
 * makes it possible to inject beans from the application context into the test itself via
 * autowiring. Because this test is targeting an AddressService bean, it is autowired into
 * the test. Finally, the testService() method makes calls to the address service and verifies the result
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//        classes=AddressBookConfiguration.class)
//public class AddressServiceTests {
//
//    @Autowired
//    private AddressService addressService;
//    @Test
//    public void testService() {
//        Address address = addressService.findByLastName("Sheman");
//        assertEquals("P", address.getFirstName());
//        assertEquals("Sherman", address.getLastName());
//        assertEquals("42 Wallaby Way", address.getAddressLine1());
//        assertEquals("Sydney", address.getCity());
//        assertEquals("New South Wales", address.getState());
//        assertEquals("2000", address.getPostCode());
//    }
//
//}
