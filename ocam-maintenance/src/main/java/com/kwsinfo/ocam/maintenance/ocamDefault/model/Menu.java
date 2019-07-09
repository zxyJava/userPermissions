package com.kwsinfo.ocam.maintenance.ocamDefault.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kwsinfo.ocam.maintenance.core.IMenu;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @Auther: zhangxinyu
 * @Date: 2019/2/21 16:18
 * @Description:
 */
@Entity
@Getter
@Setter
public class Menu extends BaseDataModal implements IMenu {

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    private Menu parent;

    @JsonProperty
    public void setParent(Menu parent) {
        this.parent = parent;
    }

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 路径
     */
    private String path;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 权限标识（英文）
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "permissionIdentity", referencedColumnName = "permissionKey")
    private Permission permissionIdentity;

    /**
     * 路由名称
     */
    private String routerName;

    /**
     * 图标
     */
    private String icon;

    /**
     * 组件名
     */
    private String component;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "parent", fetch = FetchType.LAZY)
    @OrderBy("sort asc ")
    private Set<Menu> subMenus;

    public void load() {

        if (subMenus != null) {
            for (Menu subMenu : subMenus) {
                subMenu.load();
            }
        }

    }

    public Set<Permission> getPermissions(Set<Permission> permissionKeys) {

        if (subMenus != null) {
            for (Menu subMenu : subMenus) {

                permissionKeys.add(subMenu.getPermissionIdentity());

                subMenu.load();
            }

            return permissionKeys;

        }

        return permissionKeys;

    }


}
