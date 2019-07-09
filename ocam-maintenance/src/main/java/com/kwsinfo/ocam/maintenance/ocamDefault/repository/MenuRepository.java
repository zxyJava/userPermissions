package com.kwsinfo.ocam.maintenance.ocamDefault.repository;

import com.kwsinfo.ocam.maintenance.ocamDefault.model.Menu;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @Auther: zhangxinyu
 * @Date: 2019/2/22 09:15
 * @Description:
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByPermissionIdentityIn(Set<Permission> permissions);

}
