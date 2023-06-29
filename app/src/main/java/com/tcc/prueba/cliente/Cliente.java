package com.tcc.prueba.cliente;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int idCliente;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String genero;

    public Cliente() {
    }

    public Cliente(int idCliente, String tipoIdentificacion, String numeroIdentificacion, String nombre, String primerApellido, String segundoApellido, String genero) {
        this.idCliente = idCliente;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.genero = genero;
    }

    public Cliente(String tipoIdentificacion, String numeroIdentificacion, String nombre, String primerApellido, String segundoApellido, String genero) {
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.genero = genero;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idCliente", idCliente);
            jsonObject.put("tipoIdentificacion", tipoIdentificacion);
            jsonObject.put("numeroIdentificacion", numeroIdentificacion);
            jsonObject.put("nombre", nombre);
            jsonObject.put("primerApellido", primerApellido);
            jsonObject.put("segundoApellido", segundoApellido);
            jsonObject.put("genero", genero);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
