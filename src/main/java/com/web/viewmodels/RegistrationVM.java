package com.web.viewmodels;

import com.domain.core.User;
import com.domain.core.UserAddress;
import com.domain.core.UserInfo;
import com.web.viewmodels.binding.Param;

import javax.servlet.http.HttpServletRequest;

public class RegistrationVM extends AbstractVM {

    @Param
    String login;
    @Param
    String firstName;
    @Param
    String lastName;
    @Param
    String phoneNumber;
    @Param
    String email;
    @Param
    String country;
    @Param
    String city;
    @Param
    String street;
    @Param
    int homeNumber;
    @Param
    int apartmentNumber;
    @Param
    int postIndex;
    @Param
    String password;
    @Param
    String confirm;

    protected RegistrationVM() {
        super();
    }

    public RegistrationVM(HttpServletRequest request) {
        super(request);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public int getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(int postIndex) {
        this.postIndex = postIndex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public User toUser() {
        UserAddress address = new UserAddress(country, city, street, homeNumber, apartmentNumber, postIndex);
        UserInfo info = new UserInfo(firstName, lastName, phoneNumber, email, address);
        return new User(login, password, info);
    }
}
