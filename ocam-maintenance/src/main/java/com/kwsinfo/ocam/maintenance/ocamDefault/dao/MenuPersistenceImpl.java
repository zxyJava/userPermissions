package com.kwsinfo.ocam.maintenance.ocamDefault.dao;

import com.kwsinfo.ocam.maintenance.core.dao.IMenuPersistence;
import com.kwsinfo.ocam.maintenance.core.exception.NoParentException;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Menu;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;
import com.kwsinfo.ocam.maintenance.ocamDefault.repository.MenuRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Auther: zhangxinyu
 * @Date: 2019/2/25 13:33
 * @Description:
 */
@Slf4j
public class MenuPersistenceImpl implements IMenuPersistence<Menu> {

    @Setter
    private MenuRepository menuRepository;

    @Override
    public Menu save(Menu menu, Long pid) {

        log.info("dao saveMenu begin with");

        Optional<Menu> getMenuById = menuRepository.findById(pid);

        if (getMenuById.isPresent()) {

            menu.setParent(getMenuById.get());

            menu.getPermissionIdentity().setName(menu.getMenuName());

            menu.getPermissionIdentity().setKey(UUID.randomUUID().toString());

            menuRepository.save(menu);

            return menu;

        }

        log.info("dao saveMenu end");

        throw new NoParentException("该父节点不存在");
    }

    public Menu load() {

        log.info("dao getAllMenu begin with ");

        Optional<Menu> menuRoot = menuRepository.findById(1L);

        if (menuRoot.isPresent()) {

            Menu menu = menuRoot.get();

            menu.load();

            log.info("dao getAllMenu end ");

            return menu;

        } else {

            log.info("dao getAllMenu null end ");
            return null;
        }

    }

    @Override
    public Menu update(Menu menu, Long pid) {

        Optional<Menu> getMenuById = menuRepository.findById(pid);

        if (getMenuById.isPresent()) {

            menu.setParent(getMenuById.get());

            menu.getPermissionIdentity().setName(menu.getMenuName());

        }

        menuRepository.save(menu);

        return menu;

    }

    @Override
    public Boolean deleteMenu(Menu menu) {

        menuRepository.delete(menu);

        return true;
    }

    @Override
    public Set<Permission> getPermissions(Menu menu) {

        log.info("dao getPermissions begin with ");

        Set<Permission> permissionIds = new HashSet<>();

        //获取当前菜单
        Optional<Menu> menuPresent = menuRepository.findById(menu.getId());

        if (menuPresent.isPresent()) {

            permissionIds.add(menuPresent.get().getPermissionIdentity());

            permissionIds = menu.getPermissions(permissionIds);

            return permissionIds;

        }

        log.info("dao getPermissions end ");

        return permissionIds;
    }

    @Override
    public Set<Menu> getMenusByPermissions(Set<Permission> permissions) {

        List<Menu> menus = menuRepository.findAllByPermissionIdentityIn(permissions);

        return new HashSet<>(menus);
    }

}
