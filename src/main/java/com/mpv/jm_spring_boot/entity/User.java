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
    @Email(message = "email должен быть валидным")
    @Column(name = "email")
    private String email;

    @Size(min = 1, message = "длина пароля должна быть не менее 1 символа")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Поле не должно быть  пустым")
    @Column(name = "f_name")
    private String firstName;

    @NotBlank(message = "Поле не должно быть  пустым")
    @Column(name = "l_name")
    private String lastName;

    @NotNull(message = "Поле не должно быть  пустым")
    @Min(value = 10, message = "минимальный возраст 10 лет")
    @Column(name = "age")
    private Integer age;

    @ManyToMany()
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String email, String password,String firstName, String lastName, int age) {
        this();
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }
}
