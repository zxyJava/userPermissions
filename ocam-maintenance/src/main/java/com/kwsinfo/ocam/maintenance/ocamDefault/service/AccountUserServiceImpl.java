package com.kwsinfo.ocam.maintenance.ocamDefault.service;

import com.kwsinfo.ocam.maintenance.core.IAccount;
import com.kwsinfo.ocam.maintenance.core.IUser;
import com.kwsinfo.ocam.maintenance.core.dao.IAccountPersistence;
import com.kwsinfo.ocam.maintenance.core.dao.IMenuPersistence;
import com.kwsinfo.ocam.maintenance.core.dao.IRolePersistence;
import com.kwsinfo.ocam.maintenance.core.dao.IUserPersistence;
import com.kwsinfo.ocam.maintenance.core.service.IAccountUserService;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Account;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Menu;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Role;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class AccountUserServiceImpl<A extends IAccount<U>, U extends IUser> implements IAccountUserService<A, U> {

    @Setter
    private IUserPersistence<U> userPersistence;

    @Setter
    private IAccountPersistence<A> accountPersistence;

    @Setter
    private IRolePersistence<Role> rolePersistence;

    @Setter
    private IMenuPersistence<Menu> menuPersistence;

    @Override
    public A saveAccount(A account) {

        Objects.requireNonNull(account);

        final String username = account.getUsername();
        final String password = account.getPassword();

        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        userPersistence.userByIdCardNumber(account.getUser());

        A savedAccount = accountPersistence.save(account);

        return savedAccount;
    }

    @Override
    public A updateAccount(A account) {
        Objects.requireNonNull(account);

        final String username = account.getUsername();
        final String password = account.getPassword();

        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        A savedAccount = accountPersistence.update(account);

        final U user = account.getUser();
        if (user != null) {

            U savedUser = userPersistence.update(user);

            savedAccount.setUser(savedUser);
        }

        return savedAccount;
    }

    @Override
    public A getAccount(Object id) {
        return accountPersistence.findById(id);
    }

    @Override
    public Page<Account> getAccount(String name, String phone, int page, int size) {

        return accountPersistence.getAccount(name, phone, page, size);
    }

    @Override
    public Set<Role> getSelectedRoles(Long id) {

        A account = accountPersistence.findById(id);

        return accountPersistence.getRolesByAccount(account);

    }

    @Override
    public Set<Role> getNotSelectedRoles(Long accountId) {

        Set<Role> selectedRoles = this.getSelectedRoles(accountId);

        Set<Long> roleIds = new HashSet<>();

        for (Role selectedRole : selectedRoles) {

            roleIds.add(selectedRole.getId());

        }

        if (roleIds.isEmpty()) {

            return new HashSet<>(rolePersistence.getAllRole());
        }

        return rolePersistence.findRolesByIdNotIn(roleIds);
    }

    @Override
    public Set<Menu> getPermissionsByAccount(Long id) {

        A account = accountPersistence.findById(id);

        Set<Permission> permissions = accountPersistence.getPermissionsByAccount(account);

        Set<Menu> menus = menuPersistence.getMenusByPermissions(permissions);

        return menus;

    }

    @Override
    public A saveAccountRolesAndPermissions(A account) {

        A accountById = accountPersistence.findByAccount(account);

        if (accountById.getRoles() != null) {

            accountById.getRoles().clear();

        }

        accountById.getRoles().addAll(account.getRoles());

        if (accountById.getPermissions() != null) {

            accountById.getPermissions().clear();

        }

        accountById.getPermissions().addAll(account.getPermissions());

        accountPersistence.update(account);

        return account;
    }

    @Override
    public A saveState(Long id) {

        A accountById = accountPersistence.findById(id);

        return accountPersistence.saveState(accountById);
    }
}
