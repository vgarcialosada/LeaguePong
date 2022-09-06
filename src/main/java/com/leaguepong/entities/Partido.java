package com.leaguepong.entities;

public class Partido {
private int id_partido;
private Usuario jugador_1;
private Usuario jugador_2;
private Usuario ganador;
public int getId_partido() {
	return id_partido;
}
public void setId_partido(int id_partido) {
	this.id_partido = id_partido;
}
public Usuario getJugador_1() {
	return jugador_1;
}
public void setJugador_1(Usuario jugador_1) {
	this.jugador_1 = jugador_1;
}
public Usuario getJugador_2() {
	return jugador_2;
}
public void setJugador_2(Usuario jugador_2) {
	this.jugador_2 = jugador_2;
}
public Usuario getGanador() {
	return ganador;
}
public void setGanador(Usuario ganador) {
	this.ganador = ganador;
}
public Partido(int id_partido, Usuario jugador_1, Usuario jugador_2, Usuario ganador) {
	super();
	this.id_partido = id_partido;
	this.jugador_1 = jugador_1;
	this.jugador_2 = jugador_2;
	this.ganador = ganador;
}
}
