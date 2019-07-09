package com.kwsinfo.ocam.maintenance.ocamDefault.controller;

import com.kwsinfo.ocam.maintenance.core.service.IMenuService;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Auther: zhangxinyu
 * @Date: 2019/2/25 15:34
 * @Description:
 */
@RestController
public class MenuController {

    @Autowired
    IMenuService<Menu> iMenuService;

    @PostMapping("{pid}/menu")
    public Menu addMenu(@RequestBody Menu menu, @PathVariable Long pid) {

        return iMenuService.saveMenu(menu, pid);
    }

    @GetMapping("menu")
    public Menu getAllSubMenu() {

        return iMenuService.getload();
    }

    @PutMapping("{pid}/menu")
    public Menu updateMenu(@RequestBody Menu menu, @PathVariable Long pid) {

        return iMenuService.update(menu, pid);
    }

    @DeleteMapping("menu")
    public Boolean deleteMenu(@RequestBody Menu menu) {

        return iMenuService.deleteMenu(menu);
    }


}
