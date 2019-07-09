package com.kwsinfo.ocam.maintenance.core.dao;

import com.kwsinfo.ocam.maintenance.core.IAccount;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Account;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Role;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface IAccountPersistence<A extends IAccount> {

    A save(A account);

    A update(A account);

    A findById(Object s);

    A findByAccount(A account);

    Page<Account> getAccount(String name, String phone, int page, int size);

    Set<Role> getRolesByAccount(A account);

    Set<Permission> getPermissionsByAccount(A account);

    A saveState(A account);

}
