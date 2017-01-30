package com.domain.dao;

import com.domain.core.User;

public class UserDao extends GenericDao<User> {

    public UserDao() {
        super(User.class);
    }

    public User getUserByLogin(String login) {
        openCurrentSession();
        User user = (User) getCurrentSession()
                .createQuery("from User where login=:login")
                .setParameter("login", login)
                .uniqueResult();
        closeCurrentSession();
        return user;
    }

    public User getUserByEmail(String email) {
        openCurrentSession();
        User user = (User) getCurrentSession()
                .createQuery("from User u where u.info.email=:email")
                .setParameter("email", email)
                .uniqueResult();
        closeCurrentSession();
        return user;
    }

}