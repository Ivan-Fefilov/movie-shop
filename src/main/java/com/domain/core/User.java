package com.domain.core;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APP_USER")
public class User extends AbstractModel {

    private String login;
    private String password;
    private UserRole role;
    private UserInfo info;

    private List<UserOrder> userOrderList;

    protected User() {

    }

    public User(String login, String password, UserInfo info) {
        this.login = login;
        this.password = password;
        this.info = info;
        this.userOrderList = new ArrayList<>();
    }

    public User(String login, String password, UserInfo info, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.info = info;
    }

    @Column(unique = true, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // TODO: 14.12.2016 Check fetch and cascade userInfo
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id", foreignKey = @ForeignKey(name = "fk_user_info_id"), nullable = false)
    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    // TODO: 14.12.2016 Check fetch and cascade userRole
    @OneToOne
    @JoinColumn(name = "user_role_id", foreignKey = @ForeignKey(name = "fk_user_role_id"), nullable = false)
    @Fetch(FetchMode.JOIN)
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    // TODO: 14.12.2016 Check fetch and cascade userOrderList
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "user")
    @Fetch(FetchMode.SELECT)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public List<UserOrder> getUserOrderList() {
        return userOrderList;
    }

    public void setUserOrderList(List<UserOrder> userOrderList) {
        this.userOrderList = userOrderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getRole() != null ? !getRole().equals(user.getRole()) : user.getRole() != null) return false;
        return getInfo() != null ? getInfo().equals(user.getInfo()) : user.getInfo() == null;
    }

    @Override
    public int hashCode() {
        int result = getLogin() != null ? getLogin().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getInfo() != null ? getInfo().hashCode() : 0);
        return result;
    }
}
