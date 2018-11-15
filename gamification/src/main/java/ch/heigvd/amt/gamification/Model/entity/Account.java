package ch.heigvd.amt.gamification.Model.entity;

import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Account.findByEmail", query = "Select d from Account d where d.email = :email")
})
public class Account extends BaseEntity<Long> {

    private String email;

    private byte[] password;

    private byte[] salt;

    private String firstName;

    private String lastName;

    private String street;

    private Integer npa;

    private String city;

    private Boolean admin = false;

    private Boolean banned = false;

    private Boolean forceChangePassword = false;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNpa() {
        return npa;
    }

    public void setNpa(Integer npa) {
        this.npa = npa;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public Boolean getForceChangePassword() {
        return forceChangePassword;
    }

    public void setForceChangePassword(Boolean forceChangePassword) {
        this.forceChangePassword = forceChangePassword;
    }
}
