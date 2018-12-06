package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "license", uniqueConstraints = {@UniqueConstraint(columnNames = {"license_id"})})
public class License {

    @Id
    @Column(name = "license_id")
    String licenseId;

    @Column(name = "license_title")
    String licenseTitle;

    @ManyToMany(mappedBy = "licenses", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    public License(String licenseId, String licenseTitle) {
        this.licenseId = licenseId;
        this.licenseTitle = licenseTitle;
    }

    public License() {}

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getLicenseTitle() {
        return licenseTitle;
    }

    public void setLicenseTitle(String licenseTitle) {
        this.licenseTitle = licenseTitle;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
