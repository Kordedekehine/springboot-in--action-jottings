package brain.springframework.bookapp.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

/**
 * You’ll also notice that Reader implements the UserDetails interface and several of
 * its methods. This makes it possible to use a Reader object to represent a user in Spring
 * Security. The getAuthorities() method is overridden to always grant users READER
 * authority. The isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), and isEnabled() methods are all implemented to return true so that
 * the reader account is never expired, locked, or revoked.
 */
@Entity
public class Reader implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * As you can see, Reader is annotated with @Entity to make it a JPA entity. In addition,
     * its username field is annotated with @Id to designate it as the entity’s ID. This seemed
     * like a natural choice, as the username should uniquely identify the Reader
     */
    @Id
    private String username;
    private String fullname;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * we grant access to the reader
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("READER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
