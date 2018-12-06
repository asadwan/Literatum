package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User {

    @Column(name = "user_type", length = 50, nullable = false)
    private String userType;

    @Id
    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password_hash", length = 128, nullable = false)
    private String passwordHash;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "token")
    private String token;


    @ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    @JoinTable( name = "user_license",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "license_id"))
    private Set<License> licenses = new HashSet<>();


    public User(String userType, String username, String passwordHash, String name) {
        this.userType = userType;
        this.username = username;
        this.passwordHash = passwordHash;
        this.name = name;
    }

    public User() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(Set<License> licenses) {
        this.licenses = licenses;
    }
}
