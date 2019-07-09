package com.kwsinfo.ocam.maintenance.ocamDefault.dao;

import com.kwsinfo.ocam.maintenance.core.dao.IAccountPersistence;
import com.kwsinfo.ocam.maintenance.core.exception.NoParentException;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Account;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Permission;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Role;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.User;
import com.kwsinfo.ocam.maintenance.ocamDefault.repository.AccountRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class AccountPersistenceImpl implements IAccountPersistence<Account> {

    @Setter
    private AccountRepository accountRepository;

    @Override
    public Account save(Account account) {

        Account accountByUsername = accountRepository.findAccountByUsername(account.getUsername());

        if (accountByUsername != null) {

            throw new NoParentException("该用户名已存在");

        }

        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {

        Account accountByUsernameAndIdNot = accountRepository.findAccountByUsernameAndIdNot(account.getUsername(), account.getId());

        if (accountByUsernameAndIdNot != null) {

            throw new NoParentException("该用户名已存在");

        }

        return accountRepository.save(account);
    }

    @Override
    public Account findById(Object id) {

        if (!(id instanceof Long)) {
            log.info("查询的id不是数值型, return null");
            return new Account();
        }

        Optional<Account> byId = accountRepository.findById((Long) id);

        if (byId.isPresent()) {

            return byId.get();

        }

        throw new NoParentException("该账户不存在");
    }

    @Override
    public Account findByAccount(Account account) {

        Optional<Account> byId = accountRepository.findById(account.getId());

        if (byId.isPresent()) {

            return byId.get();

        }

        throw new NoParentException("该账户不存在");

    }

    @Override
    public Page<Account> getAccount(String name, String phone, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

//        return accountRepository.findAll((Specification<Account>) (root, criteriaQuery, criteriaBuilder) -> {
//
//            Join<Account, User> bJoin = root.join("user", JoinType.LEFT);
//            return bJoin.on(
//                    criteriaBuilder.like(bJoin.get("name"), "%" + name + "%")
//                    , criteriaBuilder.like(bJoin.get("phone"), "%" + phone + "%")
//            ).getOn();
//
//        }, pageable);

        return accountRepository.getAccountAndUser(name, phone, pageable);
    }

    @Override
    public Set<Role> getRolesByAccount(Account account) {

        return account.getRoles();
    }

    @Override
    public Set<Permission> getPermissionsByAccount(Account account) {

        return account.getPermissions();
    }

    @Override
    public Account saveState(Account account) {

        if (account.isEnabled()) {

            account.setEnabled(false);

        } else {

            account.setEnabled(true);

        }

        accountRepository.save(account);

        return account;
    }
}
