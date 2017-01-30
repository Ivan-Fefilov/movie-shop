package com.web.viewmodels;

import com.web.viewmodels.binding.Param;

import javax.servlet.http.HttpServletRequest;

public class ShopVM extends AbstractVM {

    Long id;
    @Param
    String name;
    @Param
    String address;

    public ShopVM() {
    }

    public ShopVM(HttpServletRequest request) {
        super(request);
    }

    public ShopVM(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
