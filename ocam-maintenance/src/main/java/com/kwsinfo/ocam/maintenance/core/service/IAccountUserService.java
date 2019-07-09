package com.kwsinfo.ocam.maintenance.core.service;

import com.kwsinfo.ocam.maintenance.core.IAccount;
import com.kwsinfo.ocam.maintenance.core.IUser;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Account;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Menu;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Role;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface IAccountUserService<A extends IAccount<U>, U extends IUser> {

    /**
     * 分别保存user和account
     *
     * @param account
     * @return
     */
    public A saveAccount(A account);

    A updateAccount(A account);

    /**
     * 通过id获取账户
     *
     * @param s
     * @return
     */
    A getAccount(Object s);

    /**
     * 获取列表
     *
     * @param name
     * @param phone
     * @param page
     * @param size
     * @return
     */
    Page<Account> getAccount(String name, String phone, int page, int size);


    /**
     * 查询默认选中的角色
     *
     * @param id
     * @return
     */
    Set<Role> getSelectedRoles(Long id);

    /**
     * 查询未被选中的角色
     *
     * @param accountId
     * @return
     */
    Set<Role> getNotSelectedRoles(Long accountId);

    /**
     * 获取账户下的权限
     *
     * @param id
     * @return
     */
    Set<Menu> getPermissionsByAccount(Long id);

    /**
     * 保存账户角色，账户权限关系表
     *
     * @param account
     * @return
     */
    A saveAccountRolesAndPermissions(A account);

    /**
     * 保存状态
     *
     * @param id
     * @return
     */
    A saveState(Long id);

}
