package com.kwsinfo.ocam.maintenance.ocamDefault.dao;

import com.kwsinfo.ocam.maintenance.OcamMaintenanceApplication;
import com.kwsinfo.ocam.maintenance.ocamDefault.model.Account;
import com.kwsinfo.ocam.maintenance.springSupport.OcamAutoConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {OcamAutoConfigurator.class, OcamMaintenanceApplication.class})
@RunWith(SpringRunner.class)
@EnableJpaRepositories(basePackages = "com.kwsinfo.ocam.maintenance.ocamDefault.repository")
public class AccountPersistenceImplTest {

    @Autowired
    private AccountPersistenceImpl accountPersistence;

    @Test
    public void getAccount() {

        final Page<Account> li = accountPersistence.getAccount("li", "123", 1, 2);

        System.out.println(li.getTotalElements());
    }
}