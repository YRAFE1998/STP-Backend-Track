package application.model;

import application.model.entities.User;
import application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {


    @Autowired
    private UserService userService;

    private String userName;
    private String password;
    private List<GrantedAuthority> authorities;
    private long id;


    public UserDetails(User user){
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.authorities = user.getRole().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        this.id = user.getId();
    }

    public long getUserId(){
        return this.id;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
