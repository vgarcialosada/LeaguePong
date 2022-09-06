package com.leaguepong.entities;

public class Liga {
private int id_liga;
private String password;
private String reglas;
private String ubicacion;
private int numero_jugadores;
public Liga(int id_liga, String password, String reglas, String ubicacion, int numero_jugadores) {
	super();
	this.id_liga = id_liga;
	this.password = password;
	this.reglas = reglas;
	this.ubicacion = ubicacion;
	this.numero_jugadores = numero_jugadores;
}
public int getId_liga() {
	return id_liga;
}
public void setId_liga(int id_liga) {
	this.id_liga = id_liga;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getReglas() {
	return reglas;
}
public void setReglas(String reglas) {
	this.reglas = reglas;
}
public String getUbicacion() {
	return ubicacion;
}
public void setUbicacion(String ubicacion) {
	this.ubicacion = ubicacion;
}
public int getNumero_jugadores() {
	return numero_jugadores;
}
public void setNumero_jugadores(int numero_jugadores) {
	this.numero_jugadores = numero_jugadores;
}
}
