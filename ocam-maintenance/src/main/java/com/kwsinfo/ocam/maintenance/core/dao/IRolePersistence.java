package com.kwsinfo.ocam.maintenance.core.dao;

import com.kwsinfo.ocam.maintenance.core.IRole;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Role;

import java.util.List;
import java.util.Set;

/**
 * @Auther: zhangxinyu
 * @Date: 2019/2/22 09:18
 * @Description:
 */
public interface IRolePersistence<R extends IRole> {

    List<R> getAllRole();

    R save(R role);

    R update(R role);

    Set<Permission> permissios(Set<Long> roleIds);

    R getRoelById(R role);

    Boolean deleteRole(R role);

    Set<Role> findRolesByIdNotIn(Set<Long> roleIds);



}
