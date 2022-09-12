package com.leaguepong.entities;

public class UsuarioLiga extends Usuario{
	private int puntos;
	private int partidosJugados;

	public int getPuntos() {
		return puntos;
	}


	public int getPartidosJugados() {
		return partidosJugados;
	}


	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public void setPartidosJugados(int partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

	@Override
	public String toString() {
		return "UsuarioLiga [puntos=" + puntos + ", partidosJugados=" + partidosJugados + "]";
	}

	public UsuarioLiga() {
		super();
	}
	
	
	
	
}
