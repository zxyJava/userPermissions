package com.kwsinfo.ocam.maintenance.core.dao;

import com.kwsinfo.ocam.maintenance.core.IPermission;


/**
 * @Auther: zhangxinyu
 * @Date: 2019/2/22 09:13
 * @Description:
 */
public interface IPermissionPersistence<P extends IPermission> {

    P save(P permission);


}
