package com.leaguepong.entities;

public class Partido {
private long id_partido;
private long id_jugador_1;
private long id_jugador_2;
private long id_ganador;

public long getId_partido() {
	return id_partido;
}
public void setId_partido(long id_partido) {
	this.id_partido = id_partido;
}
public long getId_jugador_1() {
	return id_jugador_1;
}
public void setId_jugador_1(long id_jugador_1) {
	this.id_jugador_1 = id_jugador_1;
}
public long getId_jugador_2() {
	return id_jugador_2;
}
public void setId_jugador_2(long id_jugador_2) {
	this.id_jugador_2 = id_jugador_2;
}
public long getId_ganador() {
	return id_ganador;
}
public void setId_ganador(long id_ganador) {
	this.id_ganador = id_ganador;
}
public Partido(long id_partido, long id_jugador_1, long id_jugador_2, long id_ganador) {
	super();
	this.id_partido = id_partido;
	this.id_jugador_1 = id_jugador_1;
	this.id_jugador_2 = id_jugador_2;
	this.id_ganador = id_ganador;
}
@Override
public String toString() {
	return "Partido [id_partido=" + id_partido + ", id_jugador_1=" + id_jugador_1 + ", id_jugador_2=" + id_jugador_2
			+ ", id_ganador=" + id_ganador + "]";
}

public Partido() {
	// TODO Auto-generated constructor stub
}
}
