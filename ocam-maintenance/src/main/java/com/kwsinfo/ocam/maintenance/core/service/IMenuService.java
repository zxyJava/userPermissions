package com.kwsinfo.ocam.maintenance.core.service;

import com.kwsinfo.ocam.maintenance.core.IMenu;

/**
 * @Auther: zhangxinyu
 * @Date: 2019/2/25 13:43
 * @Description:
 */
public interface IMenuService<M extends IMenu> {

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    public M saveMenu(M menu, Long pid);

    /**
     * 获取全部菜单
     *
     * @return
     */
    public M getload();

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    M update(M menu, Long pid);

    /**
     * 删除菜单
     *
     * @param menu
     * @return
     */
    Boolean deleteMenu(M menu);

}
