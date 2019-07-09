package com.kwsinfo.ocam.maintenance.ocamDefault.service;

import com.kwsinfo.ocam.maintenance.core.IMenu;
import com.kwsinfo.ocam.maintenance.core.dao.IMenuPersistence;
import com.kwsinfo.ocam.maintenance.core.service.IMenuService;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Account;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Role;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * @Auther: zhangxinyu
 * @Date: 2019/2/25 13:43
 * @Description:
 */
@Slf4j
public class MenuServiceImpl<M extends IMenu> implements IMenuService<M> {

    @Setter
    private IMenuPersistence<M> menuPersistence;

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    @Override
    public M saveMenu(M menu, Long pid) {

        Objects.requireNonNull(menu);
        Objects.requireNonNull(pid);

        log.info("saveMenu begin with :{},{}", menu, pid);

        final String menuName = menu.getMenuName();
        final String path = menu.getPath();
        final Integer sort = menu.getSort();

        Objects.requireNonNull(menuName);
        Objects.requireNonNull(path);
        Objects.requireNonNull(sort);

        Permission permission = new Permission();

        permission.setKey(UUID.randomUUID().toString());

        permission.setName(menuName);

        M savedMenu = menuPersistence.save(menu, pid);

        log.info("saveMenu end");

        return savedMenu;
    }

    /**
     * 获取全部菜单
     *
     * @return
     */
    @Override
    public M getload() {

        return menuPersistence.load();

    }

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    @Override
    public M update(M menu, Long pid) {

        Objects.requireNonNull(menu);

        return menuPersistence.update(menu, pid);

    }

    /**
     * 删除菜单
     *
     * @param menu
     * @return
     */
    @Override
    public Boolean deleteMenu(M menu) {

        Objects.requireNonNull(menu);

        log.info("deleteMenu begin with");

        //获取需要删除的权限
        Set<Permission> permissions = menuPersistence.getPermissions(menu);

        //通过权限获得角色
        for (Permission permission : permissions) {

            if (permission.getRoles() != null) {

                for (Role role : permission.getRoles()) {

                    //删除角色权限关系表
                    role.getPermissions().remove(permission);

                }

            }

            if (permission.getAccounts() != null) {

                for (Account account : permission.getAccounts()) {

                    //删除账户权限关系表
                    account.getPermissions().remove(permission);

                }

            }

        }

        //删除menu及submenu及权限表
        menuPersistence.deleteMenu(menu);

        log.info("deleteMenu begin end");

        return true;
    }

}
