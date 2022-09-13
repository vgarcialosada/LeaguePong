package com.leaguepong.entities;

public class Usuario {
	
	private long id_usuario;
	private String nombre_usuario;
	private String password;
	private String mail;
	private String localidad;
	private int nivel;
	public long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public Usuario(long id_usuario, String nombre_usuario, String password, String mail, String localidad, int nivel) {
		super();
		this.id_usuario = id_usuario;
		this.nombre_usuario = nombre_usuario;
		this.password = password;
		this.mail = mail;
		this.localidad = localidad;
		this.nivel = nivel;
	}
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", nombre_usuario=" + nombre_usuario + ", password=" + password
				+ ", mail=" + mail + ", localidad=" + localidad + ", nivel=" + nivel + "]";
	}
	

}
