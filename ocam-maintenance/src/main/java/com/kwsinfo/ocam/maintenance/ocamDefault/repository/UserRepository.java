package com.kwsinfo.ocam.maintenance.ocamDefault.repository;

import com.kwsinfo.ocam.maintenance.ocamDefault.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sunb on 17-6-28.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByIdCardNumber(String idCardNumber);

    User findUserByIdCardNumberAndIdNot(String idCardNumber, Long id);

}
