package com.kwsinfo.ocam.maintenance.ocamDefault.repository;

import com.kwsinfo.ocam.maintenance.ocamDefault.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    //    @Query(value = "SELECT oa.*, ou.*" +
//            "FROM OCAM_ACCOUNT oa LEFT JOIN OCAM_USER ou " +
//            "ON oa.USER_ID = ou.ID " +
//            "WHERE ou.NAME LIKE CONCAT('%',:name,'%') AND ou.PHONE LIKE CONCAT('%',:phone,'%')",
//            countQuery = "SELECT COUNT(*)" +
//                    "FROM OCAM_ACCOUNT oa LEFT JOIN OCAM_USER ou " +
//                    "ON oa.USER_ID = ou.ID " +
//                    "WHERE ou.NAME LIKE CONCAT('%',:name,'%') AND ou.PHONE LIKE CONCAT('%',:phone,'%')", nativeQuery = true)
    @Query("FROM Account a left join a.user u where u.name LIKE %:name% and u.phone LIKE %:phone%")
    Page<Account> getAccountAndUser(@Param("name") String name, @Param("phone") String phone, Pageable pageable);

    Account findAccountByUsername(String username);

    Account findAccountByUsernameAndIdNot(String username, Long id);


}
