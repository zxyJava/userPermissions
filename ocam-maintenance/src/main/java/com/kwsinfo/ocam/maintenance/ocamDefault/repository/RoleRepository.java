package com.kwsinfo.ocam.maintenance.ocamDefault.repository;

import com.kwsinfo.ocam.maintenance.ocamDefault.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


/**
 * Created by sunb on 2019/2/15.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findRolesByIdNotIn(Set<Long> roleIds);

}
