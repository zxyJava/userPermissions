package com.kwsinfo.ocam.maintenance.core.service;

import com.kwsinfo.ocam.maintenance.core.IRole;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Menu;

import java.util.List;
import java.util.Set;

/**
 * @Auther: zhangxinyu
 * @Date: 2019/3/6 17:10
 * @Description:
 */
public interface IRoleService<R extends IRole> {

    /**
     * 获取全部角色
     *
     * @return
     */
    List<R> getAllRole();

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    R save(R role);

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    R update(R role);

    /**
     * 通过角色id获取菜单
     *
     * @param roleIds
     * @return
     */
    Set<Menu> getMenusByRoleId(Set<Long> roleIds);

    /**
     * 保存角色权限关系表
     *
     * @param role
     * @return
     */
    R saveRolePermission(R role);

    /**
     * 删除角色
     *
     * @param role
     * @return
     */
    Boolean deleteRole(R role);

}
