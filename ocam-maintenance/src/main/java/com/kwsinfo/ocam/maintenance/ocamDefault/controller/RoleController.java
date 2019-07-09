package com.kwsinfo.ocam.maintenance.ocamDefault.controller;

import com.kwsinfo.ocam.maintenance.core.service.IMenuService;
import com.kwsinfo.ocam.maintenance.core.service.IRoleService;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Menu;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @Auther: zhangxinyu
 * @Date: 2019/3/6 17:18
 * @Description:
 */
@RestController
public class RoleController {

    @Autowired
    IRoleService<Role> iRoleService;

    @Autowired
    IMenuService<Menu> iMenuService;

    @GetMapping("role")
    public List<Role> getAllRole() {

        return iRoleService.getAllRole();
    }

    @GetMapping("roleMenu")
    public Menu getAllSubMenu() {

        return iMenuService.getload();
    }

    @PostMapping("role")
    public Role save(@RequestBody Role role) {

        return iRoleService.save(role);
    }

    @PutMapping("role")
    public Role update(@RequestBody Role role) {

        return iRoleService.update(role);
    }

    @PostMapping("role/menus")
    public Set<Menu> getPermissiosByRole(@RequestBody Set<Long> roleIds) {

        return iRoleService.getMenusByRoleId(roleIds);
    }

    @PutMapping("rolePermissions")
    public Role saveRolePermission(@RequestBody Role role) throws Exception {

        return iRoleService.saveRolePermission(role);
    }

    @DeleteMapping("role")
    public Boolean deleteRole(@RequestBody Role role) {

        return iRoleService.deleteRole(role);
    }

}
