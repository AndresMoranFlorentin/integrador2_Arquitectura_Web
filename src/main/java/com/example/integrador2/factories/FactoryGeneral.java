package com.example.integrador2.factories;

public abstract class FactoryGeneral {
    public static final int DERBY_JDBC = 1;
    public static final int MYSQL_JDBC = 2;

    public static FactoryGeneral getFactory(int whichFactory) {
        switch (whichFactory) {
            case DERBY_JDBC  : {
                return FactoryDerby.getEntityManagerFactory();
            }
            case MYSQL_JDBC : return FactoryMySQL.getEntityManagerFactory();
            default: return null;
        }
    }
}
