//package dd.projects.ddshop.dtos;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import dd.projects.ddshop.entities.User;
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Objects;
//@Data
//public class UserDetailsImpl implements UserDetails {
//    private static final long serialVersionUID = 1L;
//
//    private int id;
//
//    private String email;
//
//    @JsonIgnore
//    private String password;
//
//    private String role;
//
//    public UserDetailsImpl(int id, String email, String password,
//                           GrantedAuthority authorities) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }
//
//    public static UserDetailsImpl build(User user) {
//        GrantedAuthority authorities = new SimpleGrantedAuthority(user.getRole().name());
//
//        return new UserDetailsImpl(
//                user.getId(),
//                user.getEmail(),
//                user.getPassword(),
//                authorities);
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        UserDetailsImpl user = (UserDetailsImpl) o;
//        return Objects.equals(id, user.id);
//    }
//}
