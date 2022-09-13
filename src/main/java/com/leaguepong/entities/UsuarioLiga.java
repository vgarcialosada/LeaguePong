package com.leaguepong.entities;

public class UsuarioLiga extends Usuario{
	private long puntos;
	private long partidosJugados;

	public long getPuntos() {
		return puntos;
	}


	public long getPartidosJugados() {
		return partidosJugados;
	}


	public void setPuntos(long puntos) {
		this.puntos = puntos;
	}

	public void setPartidosJugados(long partidosJugados) {
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
