package com.kwsinfo.ocam.maintenance.ocamDefault.dao;

import com.kwsinfo.ocam.maintenance.core.dao.IRolePersistence;
import com.kwsinfo.ocam.maintenance.core.exception.NoParentException;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Role;
import com.kwsinfo.ocam.maintenance.ocamDefault.repository.RoleRepository;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @Auther: zhangxinyu
 * @Date: 2019/3/1 15:40
 * @Description:
 */
public class RolePersistenceImpl implements IRolePersistence<Role> {

    @Setter
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {

        return roleRepository.findAll();
    }

    @Override
    public Role save(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {

        Optional<Role> byId = roleRepository.findById(role.getId());

        if (byId.isPresent()) {

            role.setPermissions(byId.get().getPermissions());

        }

        return roleRepository.save(role);
    }

    @Override
    public Set<Permission> permissios(Set<Long> roleIds) {

        List<Role> allById = roleRepository.findAllById(roleIds);

        Set<Permission> permissions = new HashSet<>();

        for (Role role : allById) {

            Optional<Role> byId = roleRepository.findById(role.getId());

            if (byId.isPresent()) {

                Set<Permission> permissionsbyRoleId = byId.get().getPermissions();

                permissions.addAll(permissionsbyRoleId);

            }

        }

        return permissions;

    }

    @Override
    public Role getRoelById(Role role) {

        Optional<Role> byId = roleRepository.findById(role.getId());

        if (byId.isPresent()) {

            return byId.get();

        }

        throw new NoParentException("该角色不存在");
    }

    @Override
    public Boolean deleteRole(Role role) {

        roleRepository.delete(role);

        return true;

    }

    @Override
    public Set<Role> findRolesByIdNotIn(Set<Long> roleIds) {

        return roleRepository.findRolesByIdNotIn(roleIds);
    }


}
