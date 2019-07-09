package com.kwsinfo.ocam.maintenance.ocamDefault.controller;

import com.kwsinfo.ocam.maintenance.core.service.IAccountUserService;
import com.kwsinfo.ocam.maintenance.core.service.IMenuService;
import com.kwsinfo.ocam.maintenance.core.service.IRoleService;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Account;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Menu;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Role;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class AccountController {

    @Autowired
    IAccountUserService<Account, User> iAccountUserService;

    @Autowired
    IMenuService<Menu> iMenuService;

    @Autowired
    IRoleService<Role> iRoleService;


    @GetMapping("account/{id}")
    public Account getAccount(@PathVariable Long id) {
        final Account account = iAccountUserService.getAccount(id);
        return account;
    }

    @GetMapping("account")
    public Page<Account> getAccount(@RequestParam String name, @RequestParam String phone, @RequestParam int page, @RequestParam int size) {

        return iAccountUserService.getAccount(name, phone, page - 1, size);
    }

    @PostMapping("account")
    public Account saveAccountUser(@RequestBody Account account) {

        return iAccountUserService.saveAccount(account);
    }

    @PutMapping("account")
    public Account updateAccount(@RequestBody Account account) {

        return iAccountUserService.updateAccount(account);
    }

    @GetMapping("account/menu")
    public Menu getAllSubMenu() {

        return iMenuService.getload();
    }

    @GetMapping("account/{id}/selectedRoles")
    public Set<Role> getSelectedRoles(@PathVariable Long id) {

        return iAccountUserService.getSelectedRoles(id);
    }

    @GetMapping("account/{id}/notSelectedRoles")
    public Set<Role> getNotSelectedRoles(@PathVariable Long id) {

        return iAccountUserService.getNotSelectedRoles(id);
    }

    @PostMapping("account/role/menus")
    public Set<Menu> getPermissiosByRole(@RequestBody Set<Long> roleIds) {

        return iRoleService.getMenusByRoleId(roleIds);
    }

    @GetMapping("account/{id}/permissions/menus")
    public Set<Menu> getPermissiosByAccount(@PathVariable Long id) {

        return iAccountUserService.getPermissionsByAccount(id);
    }

    @PutMapping("account/saveRolesAndPermissions")
    public Account saveAccountRolesAndPermissions(@RequestBody Account account) {

        return iAccountUserService.saveAccountRolesAndPermissions(account);
    }

    @PutMapping("account/{id}/state")
    public Account saveState(@PathVariable Long id) {

        return iAccountUserService.saveState(id);
    }

}
