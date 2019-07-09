package com.kwsinfo.ocam.maintenance.ocamDefault.model;

import com.kwsinfo.ocam.maintenance.core.IAccount;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
public class Account extends BaseDataModal implements IAccount<User> {

    private String password;
    private String username;
    private String headurl;

    /**
     * 账户没有过期
     */
    private boolean accountNonExpired = true;
    /**
     * 账户没有被锁
     */
    private boolean accountNonLocked = true;
    /**
     * 密码没有过期
     */
    private boolean credentialsNonExpired = true;
    /**
     * 帐户可用
     */
    private boolean enabled = true;
    /**
     * 业务ID
     */
    private String externalId;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")}
    )
    private Set<Permission> permissions;


    /*@Override
    public Collection<? extends IPermission> getAllPermissions() {

        Set<Permission> allPermissions = new HashSet<>();
        if (getPermissions() != null) {
            allPermissions.addAll(permissions);
        }
        if (getRoles() != null) {
            roles.stream()
                    .filter(role -> role.getPermissions() != null)
                    .map(Role::getPermissions)
                    .forEach(allPermissions::addAll);
        }
        return allPermissions;
    }*/

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }


}
