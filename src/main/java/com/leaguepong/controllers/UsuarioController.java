package com.leaguepong.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leaguepong.entities.Liga;
import com.leaguepong.entities.Usuario;

@RestController
public class UsuarioController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	@GetMapping("/{id}/usuario")
	public List<Usuario> getUsuario(@PathVariable int id) {
		List<Usuario> userArr = new ArrayList<Usuario>();
		String QUERY;
			QUERY = "select ID_USUARIO, USERNAME, PASSWORD, MAIL, NIVEL from leaguepong.usuarios where id_usuario = " + id ;;
		List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
		for (Map<String, Object> result : results) {
			Usuario user = new Usuario();
			user.setId_usuario(((BigInteger) result.get("ID_USUARIO")).longValue());
			user.setNombre_usuario((String) result.get("USERNAME"));
			user.setPassword((String) result.get("PASSWORD"));
			user.setMail((String) result.get("MAIL"));
			user.setNivel((int) result.get("NIVEL"));
			userArr.add(user);
		}
		return userArr;
	}
	
	@GetMapping("/{id}/{fields}/{newData}/usuario")
	public List<Usuario> updateUsuario(@PathVariable int id) {
		List<Usuario> userArr = new ArrayList<Usuario>();
		String QUERY;
			QUERY = "select ID_USUARIO, USERNAME, PASSWORD, MAIL, NIVEL from leaguepong.usuarios where id_usuario = " + id ;;
		List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
		for (Map<String, Object> result : results) {
			Usuario user = new Usuario();
			user.setId_usuario(((BigInteger) result.get("ID_USUARIO")).longValue());
			user.setNombre_usuario((String) result.get("USERNAME"));
			user.setPassword((String) result.get("PASSWORD"));
			user.setMail((String) result.get("MAIL"));
			user.setNivel((int) result.get("NIVEL"));
			userArr.add(user);
		}
		return userArr;
	}
}
