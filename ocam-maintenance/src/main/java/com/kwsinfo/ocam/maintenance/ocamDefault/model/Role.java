package com.kwsinfo.ocam.maintenance.ocamDefault.model;


import com.kwsinfo.ocam.maintenance.core.IRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sunb on 2019/2/15.
 */
@Entity
@Setter
@Getter
public class Role extends BaseDataModal implements IRole {

    @Column(name = "roleKey")
    private String key;

    @Column
    private String description;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")}
    )
    private Set<Permission> permissions;

}
