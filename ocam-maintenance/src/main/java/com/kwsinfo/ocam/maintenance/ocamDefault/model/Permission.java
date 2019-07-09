package com.kwsinfo.ocam.maintenance.ocamDefault.model;


import com.kwsinfo.ocam.maintenance.core.IPermission;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Created by sunb on 2019/2/15.
 */
@Entity
@Getter
@Setter
public class Permission extends BaseDataModal implements IPermission {

    @Column
    private String name;

    @Column(name = "permissionKey", unique = true)
    private String key;

    @Column
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;


    @ManyToMany(mappedBy = "permissions")
    private Set<Account> accounts;





}
