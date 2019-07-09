package com.kwsinfo.ocam.maintenance.core;

import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;

import java.util.Set;

public interface IRole {

    String getKey();

    Set<Permission> getPermissions();
}
