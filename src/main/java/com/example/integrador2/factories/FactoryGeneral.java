package com.example.integrador2.factories;

public abstract class FactoryGeneral {
    public static final int DERBY_JDBC = 1;
    public static final int MYSQL_JDBC = 2;
    private static FactoryMySQL fabricaMysql=new FactoryMySQL();
    private static FactoryDerby fabricaDerby= new FactoryDerby();
    public static FactoryGeneral getFactory(int whichFactory) {
        switch (whichFactory) {
            case DERBY_JDBC  : {
                return null;//fabricaDerby;
            }
            case MYSQL_JDBC : return fabricaMysql;
            default: return null;
        }
    }
}
