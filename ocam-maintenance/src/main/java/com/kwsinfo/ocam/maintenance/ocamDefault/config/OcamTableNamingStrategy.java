package com.kwsinfo.ocam.maintenance.ocamDefault.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class OcamTableNamingStrategy extends SpringPhysicalNamingStrategy {

    public transient static final String TABLE_NAME_PREFIX = "OCAM_";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {

        Identifier identifier = super.toPhysicalColumnName(name, context);

        String tableName = TABLE_NAME_PREFIX + identifier.getText().toUpperCase();

        return Identifier.toIdentifier(tableName);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {

        Identifier identifier = super.toPhysicalColumnName(name, jdbcEnvironment);

        String columnName = identifier.getText().toUpperCase();

        return Identifier.toIdentifier(columnName);
    }
}
