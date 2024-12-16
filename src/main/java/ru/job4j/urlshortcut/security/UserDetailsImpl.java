package ru.job4j.urlshortcut.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.job4j.urlshortcut.model.Site;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    @Getter
    private final Long id;

    private final String name;

    @Getter
    private final Site site;

    @JsonIgnore
    @Getter
    private final String password;

    public UserDetailsImpl(Long id, String login, String password, Site site) {
        this.id = id;
        this.name = login;
        this.password = password;
        this.site = site;
    }

    public static UserDetailsImpl build(Site site) {
        return new UserDetailsImpl(site.getId(),
                site.getLogin(),
                site.getPassword(),
                site);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
