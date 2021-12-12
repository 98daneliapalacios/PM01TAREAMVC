package com.example.pm01tareamvc.tablas;

public class Empleados {

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String direccion;
    private String puesto;

    //PRIMER CONSTRUCTOR DE CLASE

    public Empleados(Integer id, String nombre, String apellido, Integer edad, String direccion, String puesto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.direccion = direccion;
        this.puesto = puesto;
    }

    //segundo constructor
    public Empleados(){

    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getPuesto() {
        return puesto;
    }
}
