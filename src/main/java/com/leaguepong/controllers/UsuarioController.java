package com.leaguepong.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.leaguepong.entities.Liga;
import com.leaguepong.entities.Usuario;

import ch.qos.logback.classic.Logger;

@RestController
public class UsuarioController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//get datos usuario
	@GetMapping("/{id}/usuario")
	public List<Usuario> getUsuario(@PathVariable int id) {
		List<Usuario> userArr = new ArrayList<Usuario>();
		String QUERY;
			QUERY = "select ID_USUARIO, USERNAME, PASSWORD, MAIL, NIVEL from leaguepong.usuarios where id_usuario = " + id ;
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
	
	//update de usuario medainte datos POST pasados por formulario !!!! pendiente encryptar nueva pwd
	@PostMapping("/{id}/update_usuario")
	public ObjectNode updateUser(@PathVariable int id, @ModelAttribute Usuario usuario, Model model) {
		String QUERYupdateUser;	
		QUERYupdateUser = "";
		if(usuario!=null) {
			QUERYupdateUser+="update LEAGUEPONG.USUARIOS set ";	
		if(usuario.getNombre_usuario()!=null) {QUERYupdateUser+="USERNAME = "+"'"+usuario.getNombre_usuario()+"'";}
		if(usuario.getPassword()!=null) {QUERYupdateUser+=",PASSWORD = "+"'"+usuario.getPassword()+"'";}
		if(usuario.getMail()!=null) {QUERYupdateUser+=",MAIL = "+"'"+usuario.getMail()+"'";}
		if(usuario.getLocalidad()!=null) {QUERYupdateUser+=",LOCALIDAD = "+"'"+usuario.getLocalidad()+"'";}
		QUERYupdateUser+=",NIVEL = "+usuario.getNivel();}
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		try {
			jdbcTemplate.execute(QUERYupdateUser);
			jdbcTemplate.execute(QUERYupdateUser);
			objectNode.put("message", "usuario actualizado");
		} catch (Exception e) {
			objectNode.put("message", "Error al actualizar usuario");
			objectNode.put("error", e.toString());
		}
		
		return objectNode;
	}

}
