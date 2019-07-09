package com.kwsinfo.ocam.maintenance.core.dao;

import com.kwsinfo.ocam.maintenance.core.IMenu;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;

import java.util.Set;


/**
 * @Auther: zhangxinyu
 * @Date: 2019/2/21 17:24
 * @Description:
 */
public interface IMenuPersistence<M extends IMenu> {

    M save(M menu, Long pid);

    M load();

    M update(M menu, Long pid);

    Boolean deleteMenu(M menu);

    Set<Permission> getPermissions(M menu);

    Set<M> getMenusByPermissions(Set<Permission> permissions);



}
