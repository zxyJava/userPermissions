package com.kwsinfo.ocam.maintenance.ocamDefault.dao;

import com.kwsinfo.ocam.maintenance.core.dao.IPermissionPersistence;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;
import com.kwsinfo.ocam.maintenance.ocamDefault.repository.PermissionRepository;
import lombok.Setter;

/**
 * @Auther: zhangxinyu
 * @Date: 2019/2/25 16:49
 * @Description:
 */
public class PermissionPersistence implements IPermissionPersistence<Permission> {

    @Setter
    private PermissionRepository permissionRepository;


    @Override
    public Permission save(Permission permission) {

        return permissionRepository.save(permission);
    }

}
