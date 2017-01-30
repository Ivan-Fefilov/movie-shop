package com.domain.core;

import javax.persistence.*;

@Entity
@Table(name = "USER_INFO")
public class UserInfo extends AbstractModel {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private UserAddress address;

    protected UserInfo() {
    }

    public UserInfo(String firstName, String lastName, String phoneNumber, String email, UserAddress address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // TODO: 14.12.2016 Check fetch and cascade userAddress
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "fk_address_id"))
    public UserAddress getAddress() {
        return address;
    }

    public void setAddress(UserAddress address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;

        UserInfo info = (UserInfo) o;

        if (getFirstName() != null ? !getFirstName().equals(info.getFirstName()) : info.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(info.getLastName()) : info.getLastName() != null)
            return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(info.getPhoneNumber()) : info.getPhoneNumber() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(info.getEmail()) : info.getEmail() != null) return false;
        return getAddress() != null ? getAddress().equals(info.getAddress()) : info.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }
}
