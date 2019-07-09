package com.kwsinfo.ocam.maintenance.core.dao;

import com.kwsinfo.ocam.maintenance.core.IUser;

public interface IUserPersistence<U extends IUser> {

    U userByIdCardNumber(U user);

    U update(U user);
}
