package com.leaguepong.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.leaguepong.entities.Liga;
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
	public long getUserId(@PathVariable String username, String password) {
		long idUser = 1;
		String QUERY;
		QUERY = "select ID_USUARIO from leaguepong.usuarios where NOMBRE_USUARIO = \"" + username + " and PASSWORD = \""
				+ password;
		List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
		for (Map<String, Object> result : results) {
			Usuario user = new Usuario();
			idUser = ((BigInteger) result.get("ID_USUARIO")).longValue();
		}
		return idUser;
	}

	// update de usuario medainte datos POST pasados por formulario !!!! pendiente
	// pendiente encryptar nueva pwd
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping("/{id}/update_usuario")
	public ObjectNode updateUser(@PathVariable int id, @RequestBody Usuario user) {
		user.setId_usuario(id);
		System.out.println(user);
		String QueryUpdateUser = setUpdateString(user);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		try {
			jdbcTemplate.execute(QueryUpdateUser);
			objectNode.put("message", "usuario actualizado");
		} catch (Exception e) {
			objectNode.put("message", "Error al actualizar usuario");
			objectNode.put("error", e.toString());
		}

		return objectNode;
	}

	// crear string para update de usuario
	public String setUpdateString( Usuario usuario) {
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
		System.out.println(QUERYupdateUser);
		return QUERYupdateUser;
	}

}
