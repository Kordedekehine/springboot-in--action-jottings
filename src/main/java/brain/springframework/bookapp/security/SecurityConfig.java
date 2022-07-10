package brain.springframework.bookapp.security;

import brain.springframework.bookapp.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReaderRepository readerRepository;

    /**
     * Configurartion classes that extend WebSecurityConfigurerAdapter can override
     * two different configure() methods. In SecurityConfig, the first configure()
     * method specifies that requests for “/” (which ReadingListController’s methods are
     * mapped to) require an authenticated user with the READER role. All other request
     * Require READER
     * access
     * Set login
     * form path
     * Define custom
     * UserDetailsService
     * Licensed to Thomas Snead <n.ordickan@gmail.com> www.it-ebooks.info
     * Overriding Spring Boot auto-configuration 53
     * paths are configured for open access to all users. It also designates /login as the path
     * for the login page as well as the login failure page (along with an error attribute).
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").access("hasRole('READER')")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true");
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth
               .userDetailsService(new UserDetailsService() {
                   @Override
                   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                       return readerRepository.findByUsername(username).orElseThrow( () ->
                               new UsernameNotFoundException("USERNAME DOES NOT EXIST"));
                   }
               });
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(new UserDetailsService() {
//                    @Override
//                    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                        return readerRepository.findOne(username).orElseThrow( () ->
//                                new UsernameNotFoundException("USERNAME DOES NOT EXIST"));
//                    }
//                });
//    }


}
