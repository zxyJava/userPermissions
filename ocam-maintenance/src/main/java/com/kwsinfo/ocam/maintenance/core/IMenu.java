package com.kwsinfo.ocam.maintenance.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Menu;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;

import java.util.Set;

/**
 * @Auther: zhangxinyu
 * @Date: 2019/2/21 16:19
 * @Description:
 */
public interface IMenu {

    Integer getSort();

    String getPath();

    String getMenuName();

    @JsonIgnore
    Menu getParent();

    Permission getPermissionIdentity();

    void load();

    Set<Permission> getPermissions(Set<Permission> permissionKeys);


}
