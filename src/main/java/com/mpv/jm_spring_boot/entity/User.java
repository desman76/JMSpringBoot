package com.mpv.jm_spring_boot.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Поле не должно быть  пустым")
    @Column(name = "username")
    private String username;

    @Size(min = 1, message = "длина пароля должна быть не менее 1 символа")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Поле не должно быть  пустым")
    @Column(name = "f_name")
    private String firstName;

    @NotNull(message = "Поле не должно быть  пустым")
    @Min(value = 10, message = "минимальный возраст 10 лет")
    @Column(name = "age")
    private Integer age;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @ManyToMany()
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
        isEnabled = true;
    }

    public User(String username, String password,String firstName, int age) {
        this();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.age = age;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
        return isEnabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", isEnabled=" + isEnabled +
                ", roles=" + roles +
                '}';
    }
}
