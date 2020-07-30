package org.sharefiles.root.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sharefiles.root.annotations.password.PasswordStrength;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {


    // TODO: @Annotation which forces password to be at least 6 letters long

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "Bad username")
    private String username;

    //@Size(message = "Choose longer password. min 6 length", min = 6)
    //@NotBlank(message = "Bad password")
    @PasswordStrength
    private String password;


    @Transient
    private String comingFromUrl;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<UserRole> userRoles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (UserRole userRole : userRoles) grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
        return grantedAuthorities;
    }
}


