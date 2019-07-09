package com.kwsinfo.ocam.maintenance.ocamDefault.model;


import com.kwsinfo.ocam.maintenance.core.IUser;
import com.kwsinfo.ocam.maintenance.types.IdCardType;
import com.kwsinfo.ocam.maintenance.types.Sex;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by sunb on 17-6-27.
 */

@Entity
@Getter
@Setter
public class User extends BaseDataModal implements IUser {

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private int age;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    private Sex sex = Sex.UNKNOWN;

    /**
     * 业务ID
     */
    @Column
    private String externalId;

    /**
     * 证件类型
     */
    private IdCardType idCardType;

    /**
     * 证件号
     */
    private String idCardNumber;


}

