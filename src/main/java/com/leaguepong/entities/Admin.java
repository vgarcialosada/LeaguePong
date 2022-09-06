package com.leaguepong.entities;

public class Admin extends Usuario{
	private Liga ligaAdmin;

	public Admin(int id_usuario, String nombre_usuario, String password, String mail, String localidad, int nivel, Liga ligaAdmin) {
		super(id_usuario, nombre_usuario, password, mail, localidad, nivel);
		// TODO Auto-generated constructor stub
	}

}
