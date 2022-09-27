package com.leaguepong.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.leaguepong.entities.Usuario;

@RestController
public class UsuarioController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// get datos usuario
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping("/{id}/usuario")
	public List<Usuario> getUsuario(@PathVariable int id) {
		List<Usuario> userArr = new ArrayList<Usuario>();
		String QUERY;
		QUERY = "select ID_USUARIO, NOMBRE_USUARIO, PASSWORD, MAIL, LOCALIDAD,  NIVEL from leaguepong.usuarios where id_usuario = "
				+ id;
		List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
		for (Map<String, Object> result : results) {
			Usuario user = new Usuario();
			user.setId_usuario(((BigInteger) result.get("ID_USUARIO")).longValue());
			user.setNombre_usuario((String) result.get("NOMBRE_USUARIO"));
			user.setPassword((String) result.get("PASSWORD"));
			user.setLocalidad((String) result.get("LOCALIDAD"));
			user.setMail((String) result.get("MAIL"));
			user.setNivel((int) result.get("NIVEL"));
			userArr.add(user);
		}
		return userArr;
	}

	// get id usuario a partir de username y pwd
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping("/{username}/{password}/usuario")
	public List<Usuario> getUserId(@PathVariable String username,@PathVariable String password) {
		int idUser;
		List<Usuario> userArr = new ArrayList<Usuario>();
		String QUERY;
		String encryptedPwd = PasswordController.encryptPassword(password);
		QUERY = "select ID_USUARIO from leaguepong.usuarios where NOMBRE_USUARIO = \"" + username
				+ "\" and PASSWORD = \"" + encryptedPwd + "\";";
		List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
		for (Map<String, Object> result : results) {
			idUser = (int) ((BigInteger) result.get("ID_USUARIO")).longValue();
			userArr = getUsuario(idUser);

		}
		return userArr;
	}

	
	// get id usuario a partir de username y pwd
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping("/{username}/get-usuario")
	public List<Usuario> getUserIdByName(@PathVariable String username) {
		int idUser;
		List<Usuario> userArr = new ArrayList<Usuario>();
		String QUERY;
	
		QUERY = "select ID_USUARIO from leaguepong.usuarios where NOMBRE_USUARIO = '" + username+ "';";
		List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
		for (Map<String, Object> result : results) {
			idUser = (int) ((BigInteger) result.get("ID_USUARIO")).longValue();
			userArr = getUsuario(idUser);

		}
		return userArr;
	}

	// update de usuario medainte datos POST pasados por formulario !!!! pendiente
	// pendiente encryptar nueva pwd
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping("/{id}/update_usuario")
	public ObjectNode updateUser(@PathVariable int id, @RequestBody Usuario user) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		// verificacion si usuario o mail ya existen
		// if (userAlreadyExists(user.getNombre_usuario(), user.getMail())) {
		user.setId_usuario(id);
		String QueryUpdateUser = setUpdateString(user);
		try {
			jdbcTemplate.execute(QueryUpdateUser);
			objectNode.put("message", "usuario actualizado");
		} catch (Exception e) {
			objectNode.put("message", "Error al actualizar usuario");
			objectNode.put("error", e.toString());
		}
//		} else {
//			objectNode.put("message", "Usuario o mail ya existen");
//		}
		return objectNode;
	}

	// crear string para update de usuario
	public String setUpdateString(Usuario usuario) {
		String QUERYupdateUser = "";
		if (usuario != null) {
			QUERYupdateUser += "update LEAGUEPONG.USUARIOS set ";
			if (usuario.getNombre_usuario() != null) {
				QUERYupdateUser += "NOMBRE_USUARIO = " + "'" + usuario.getNombre_usuario() + "',";
			}
			if (usuario.getMail() != null) {
				QUERYupdateUser += "MAIL = " + "'" + usuario.getMail() + "',";
			}
			if (usuario.getLocalidad() != null) {
				QUERYupdateUser += "LOCALIDAD = " + "'" + usuario.getLocalidad() + "',";
			}
			QUERYupdateUser += "NIVEL = " + usuario.getNivel() + " ";
		}
		// si query acaba en , cambiamos por ;
		if (QUERYupdateUser.endsWith(",")) {
			QUERYupdateUser = QUERYupdateUser.substring(0, QUERYupdateUser.length() - 1) + " ";
		}

		QUERYupdateUser += "where id_usuario = " + usuario.getId_usuario() + ";";
		return QUERYupdateUser;
	}

//	public boolean userAlreadyExists(String username, String mail) {
//		String sql = "SELECT count(*) FROM usuarios WHERE nombre_usuario = ? or mail = ?";
//		int count = jdbcTemplate.queryForObject(sql, new Object[] { username, mail }, Integer.class);
//		return count > 0;
//	}

}
