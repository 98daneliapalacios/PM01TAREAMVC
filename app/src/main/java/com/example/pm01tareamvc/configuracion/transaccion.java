package com.example.pm01tareamvc.configuracion;

public class transaccion {
    //nombre de la base de datos
    public static final String NameDatabase = "PMO1DB";

    //tablas de la DB en SQLite

    public static final String tablaEmpleados = "empleados";

    //campo de la tabla personas de la DB en SQLite
    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String apellido = "apellido";
    public static final String edad = "edad";
    public static final String direccion = "direccion";
    public static final String puesto = "puesto";

    //transacciones DDL(DATA DEFINITION LENGUAGE) tabla personas
    public static final String CreateTableEmpleados = "CREATE TABLE empleados (id INTEGER PRIMARY KEY AUTOINCREMENT "+
            ",nombre TEXT, apellido TEXT, edad INTEGER, direccion TEXT, puesto TEXT)";
    public static final String DROPTableEmpleados = "DROP TABLE IF EXISTS empleados";
}
