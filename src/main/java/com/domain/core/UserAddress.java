package com.domain.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ADDRESS")
public class UserAddress extends AbstractModel {

    private String country;
    private String city;
    private String street;
    // TODO: 14.12.2016 Change int to Integer userAddress
    private int homeNumber;
    private int apartmentNumber;
    private int postIndex;

    public UserAddress() {
    }

    public UserAddress(String country, String city, String street, int homeNumber, int apartmentNumber, int postIndex) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
        this.apartmentNumber = apartmentNumber;
        this.postIndex = postIndex;
    }

    @Column(nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(nullable = false)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(nullable = false)
    public int getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    @Column(nullable = false)
    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Column(nullable = false)
    public int getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(int postIndex) {
        this.postIndex = postIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAddress)) return false;

        UserAddress address = (UserAddress) o;

        if (getHomeNumber() != address.getHomeNumber()) return false;
        if (getApartmentNumber() != address.getApartmentNumber()) return false;
        if (getPostIndex() != address.getPostIndex()) return false;
        if (getCountry() != null ? !getCountry().equals(address.getCountry()) : address.getCountry() != null)
            return false;
        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) return false;
        return getStreet() != null ? getStreet().equals(address.getStreet()) : address.getStreet() == null;
    }

    @Override
    public int hashCode() {
        int result = getCountry() != null ? getCountry().hashCode() : 0;
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + getHomeNumber();
        result = 31 * result + getApartmentNumber();
        result = 31 * result + getPostIndex();
        return result;
    }
}
