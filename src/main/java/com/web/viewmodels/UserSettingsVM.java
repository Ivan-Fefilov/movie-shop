package com.web.viewmodels;

import com.domain.core.User;
import com.web.viewmodels.binding.Param;

import javax.servlet.http.HttpServletRequest;

public class UserSettingsVM extends AbstractVM {

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

    protected UserSettingsVM() {
        super();
    }

    public UserSettingsVM(HttpServletRequest request) {
        super(request);
    }

    public UserSettingsVM(User user) {
        firstName = user.getInfo().getFirstName();
        lastName = user.getInfo().getLastName();
        phoneNumber = user.getInfo().getPhoneNumber();
        email = user.getInfo().getEmail();
        country = user.getInfo().getAddress().getCountry();
        city = user.getInfo().getAddress().getCity();
        street = user.getInfo().getAddress().getStreet();
        homeNumber = user.getInfo().getAddress().getHomeNumber();
        apartmentNumber = user.getInfo().getAddress().getApartmentNumber();
        postIndex = user.getInfo().getAddress().getPostIndex();
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

}
