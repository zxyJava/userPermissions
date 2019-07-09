package com.kwsinfo.ocam.maintenance.core;

import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Role;

import java.util.Set;

/**
 * 跟spring-security 的 UserDetails一样
 */
public interface IAccount<U extends IUser> {

    String getPassword();

    String getUsername();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();

    String getExternalId();

    /**
     * 取得权限key
     *
     * @return
     */
    Set<Permission> getPermissions();

    /**
     * 取得所有角色key
     *
     * @return
     */
    Set<Role> getRoles();

    /**
     * @return
     *//*
    Collection<? extends IPermission> getAllPermissions();*/

    /**
     * 从account维护User
     *
     * @return
     */
    U getUser();

    void setUser(U user);
}
