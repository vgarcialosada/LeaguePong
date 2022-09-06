package com.leaguepong.entities;

public class Partido {
private int id_partido;
private int id_jugador_1;
private int id_jugador_2;
private int id_ganador;
public int getId_partido() {
	return id_partido;
}
public void setId_partido(int id_partido) {
	this.id_partido = id_partido;
}
public int getId_jugador_1() {
	return id_jugador_1;
}
public void setId_jugador_1(int id_jugador_1) {
	this.id_jugador_1 = id_jugador_1;
}
public int getId_jugador_2() {
	return id_jugador_2;
}
public void setId_jugador_2(int id_jugador_2) {
	this.id_jugador_2 = id_jugador_2;
}
public int getId_ganador() {
	return id_ganador;
}
public void setId_ganador(int id_ganador) {
	this.id_ganador = id_ganador;
}
public Partido(int id_partido, int id_jugador_1, int id_jugador_2, int id_ganador) {
	super();
	this.id_partido = id_partido;
	this.id_jugador_1 = id_jugador_1;
	this.id_jugador_2 = id_jugador_2;
	this.id_ganador = id_ganador;
}
}
