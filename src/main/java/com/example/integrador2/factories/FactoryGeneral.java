package com.example.integrador2.factories;

/**
 * Clase que permite elegir a que base de datos el usuario(main) quiere
 * realizar sus transacciones
 * @version 25/4/2024 A.0
 */
public abstract class FactoryGeneral {
    public static final int DERBY_JDBC = 1;
    public static final int MYSQL_JDBC = 2;
    private static FactoryMySQL fabricaMysql=new FactoryMySQL();
    private static FactoryDerby fabricaDerby= new FactoryDerby();

    /**
     * Metodo que devuelve uno de los Factorys disponibles para conecartarse a la base de datos
     * @param whichFactory es un numero que eligio el usuario, representa una opcion de la base de datos
     * @return devuelve uno de los factory disponibles
     */
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
