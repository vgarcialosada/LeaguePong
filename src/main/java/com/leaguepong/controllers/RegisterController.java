package com.leaguepong.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.leaguepong.entities.Usuario;

import ch.qos.logback.classic.Logger;

@Controller
public class RegisterController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("usuarios", new Usuario());
		return "create_user";
	}

	@PostMapping("/register_user")
	public String registerSubmit(@ModelAttribute Usuario usuario, Model model) {
		Logger logger = (Logger) LoggerFactory.getLogger(RegisterController.class);
		logger.info(usuario.toString());
		//createUserBDD(usuario);
		model.addAttribute("usuario", usuario);
		return "userResult";
	}

	public long selectLastUserId() {
		long result = jdbcTemplate.queryForObject("SELECT MAX(id_usuario) FROM leaguepong.usuarios;", Long.class);
		return result;
	}

//	@PostMapping("/crear-usuario")
//	public ObjectNode createUserBDD(@RequestBody(required = false) Usuario usuario) {
//		int idNewUser = (int) selectLastUserId();
//		usuario.setId_usuario(idNewUser);
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode objectNode = mapper.createObjectNode();
//		String pwdEncriptada=PasswordEncrypt.SecuredPasswordGenerator.encryptPwd(usuario.getPassword());
//		String queryCreateUser = "insert into usuarios set ID_USUARIO= \""+usuario.getId_usuario() + "\", NOMBRE = \"" + usuario.getNombre_usuario() + "\", PASSWORD = \""
//				+ pwdEncriptada + "\", REGLAS= \"" + usuario.getMail() + "\", UBICACION = \"" + usuario.getLocalidad()
//				+ "\", NUMERO_JUGADORES=" + usuario.getNivel()+ "\", ENABLED= 1, ROLE= 1;";
//
//		System.out.println(queryCreateUser);
//		try {
//			jdbcTemplate.execute(queryCreateUser);
//			objectNode.put("message", "Usuario creado correctamente");
//		} catch (Exception e) {
//			objectNode.put("message", "Error al crear usuario");
//			objectNode.put("error", e.toString());
//		}
//
//		return objectNode;
//	}

}
