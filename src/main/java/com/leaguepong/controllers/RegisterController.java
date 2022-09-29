package com.leaguepong.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		model.addAttribute("usuario", new Usuario());
		return "create_user";
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping("/register_user")
	public String registerSubmit(@RequestBody Usuario usuario) {
		Usuario newUser = usuario;
		Logger logger = (Logger) LoggerFactory.getLogger(RegisterController.class);
		createUserBDD(usuario);
		// model.addAttribute("usuario", usuario);
		return "ok";
	}

	public ObjectNode createUserBDD(@RequestBody(required = false) Usuario usuario) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		if (!userAlreadyExists(usuario.getNombre_usuario(), usuario.getMail())) {
			int idNewUser = (int) selectLastUserId() + 1;
			usuario.setId_usuario(idNewUser);
			String pwdEncriptada = PasswordController.encryptPassword(usuario.getPassword());
			String queryCreateUser = "insert into LEAGUEPONG.usuarios set NOMBRE_USUARIO = \""
					+ usuario.getNombre_usuario() + "\", PASSWORD = \"" + pwdEncriptada + "\", MAIL= \""
					+ usuario.getMail() + "\", LOCALIDAD = \"" + usuario.getLocalidad() + "\", NIVEL = "
					+ usuario.getNivel();

			jdbcTemplate.execute(queryCreateUser);
			objectNode.put("message", "Usuario creado correctamente");
		} else {
			objectNode.put("message", "usuario ya existe");
		}
		return objectNode;
	}

	public long selectLastUserId() {
		long 	result=0;
		try {
			 result = jdbcTemplate.queryForObject("SELECT MAX(id_usuario) FROM leaguepong.usuarios;", Long.class);
		} catch (Exception e) {
			return result;
		}
		if((Long)result==null){result=0;}
		return result;
	}

	public boolean userAlreadyExists(String username, String mail) {
		String sql = "SELECT count(*) FROM usuarios WHERE nombre_usuario = ? or mail = ?";
		int count = jdbcTemplate.queryForObject(sql, new Object[] { username, mail }, Integer.class);
		return count > 0;
	}
}
