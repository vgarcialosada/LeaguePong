package com.leaguepong.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncrypt {
	
	
	// ENCRIPTAR CONTRASEÑA
	public static class SecuredPasswordGenerator {
		public static String encryptPwd(String pwd) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(pwd);
			return encodedPassword;
		}

	}
}