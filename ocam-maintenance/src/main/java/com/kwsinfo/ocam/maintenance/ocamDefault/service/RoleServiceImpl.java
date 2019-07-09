package com.kwsinfo.ocam.maintenance.ocamDefault.service;

import com.kwsinfo.ocam.maintenance.core.IRole;
import com.kwsinfo.ocam.maintenance.core.dao.IMenuPersistence;
import com.kwsinfo.ocam.maintenance.core.dao.IRolePersistence;
import com.kwsinfo.ocam.maintenance.core.service.IRoleService;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Menu;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;
import lombok.Setter;

import java.util.List;
import java.util.Set;

/**
 * @Auther: zhangxinyu
 * @Date: 2019/3/6 17:11
 * @Description:
 */
public class RoleServiceImpl<R extends IRole> implements IRoleService<R> {

    @Setter
    private IRolePersistence<R> rolePersistence;

    @Setter
    private IMenuPersistence<Menu> menuPersistence;

    /**
     * 获取全部角色
     *
     * @return
     */
    @Override
    public List<R> getAllRole() {

        return rolePersistence.getAllRole();
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @Override
    public R save(R role) {

        return rolePersistence.save(role);
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @Override
    public R update(R role) {

        return rolePersistence.update(role);
    }

    /**
     * 通过角色id获取菜单
     *
     * @param roleIds
     * @return
     */
    @Override
    public Set<Menu> getMenusByRoleId(Set<Long> roleIds) {

        Set<Permission> permissios = rolePersistence.permissios(roleIds);

        Set<Menu> menus = menuPersistence.getMenusByPermissions(permissios);

        return menus;
    }

    /**
     * 保存角色权限关系表
     *
     * @param role
     * @return
     */
    @Override
    public R saveRolePermission(R role) {

        R roelById = rolePersistence.getRoelById(role);

        if (roelById.getPermissions() != null) {

            roelById.getPermissions().clear();

        }

        roelById.getPermissions().addAll(role.getPermissions());

        rolePersistence.save(role);

        return role;

    }

    @Override
    public Boolean deleteRole(R role) {

        return rolePersistence.deleteRole(role);
    }
}
