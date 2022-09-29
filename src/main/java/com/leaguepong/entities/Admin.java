package com.leaguepong.entities;

public class Admin extends Usuario{
	private boolean admin;

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
