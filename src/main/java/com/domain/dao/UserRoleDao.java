package com.domain.dao;

import com.domain.core.UserRole;

public class UserRoleDao extends GenericDao<UserRole> {

    public UserRoleDao() {
        super(UserRole.class);
    }

    public UserRole getRoleByName(String name) {
        openCurrentSession();
        UserRole role = (UserRole) getCurrentSession()
                .createQuery("FROM UserRole WHERE name=:name")
                .setParameter("name", name)
                .uniqueResult();
        closeCurrentSession();
        return role;
    }
}
