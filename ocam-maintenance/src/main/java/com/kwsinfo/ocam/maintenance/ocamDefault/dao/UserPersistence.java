package com.kwsinfo.ocam.maintenance.ocamDefault.dao;

import com.kwsinfo.ocam.maintenance.core.dao.IUserPersistence;
import com.kwsinfo.ocam.maintenance.core.exception.NoParentException;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.User;
import com.kwsinfo.ocam.maintenance.ocamDefault.repository.UserRepository;
import lombok.Setter;

public class UserPersistence implements IUserPersistence<User> {

    @Setter
    private UserRepository userRepository;

    @Override
    public User userByIdCardNumber(User user) {

        User userByIdCardNumber = userRepository.findUserByIdCardNumber(user.getIdCardNumber());

        if (userByIdCardNumber != null) {

            throw new NoParentException("该身份账号已存在");

        }

        return userRepository.save(user);
    }

    @Override
    public User update(User user) {

        User UserByIdNotAndIdCardNumber = userRepository.findUserByIdCardNumberAndIdNot(user.getIdCardNumber(), user.getId());

        if (UserByIdNotAndIdCardNumber != null) {

            throw new NoParentException("该身份账号已存在");

        }

        return userRepository.save(user);
    }
}
