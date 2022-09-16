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

	@CrossOrigin(origins="*", maxAge = 3600)
	@PostMapping("/register_user")
	public String registerSubmit(@RequestBody Usuario usuario, Model model) {
		Usuario newUser = usuario;	
		Logger logger = (Logger) LoggerFactory.getLogger(RegisterController.class);
		createUserBDD(usuario);
			model.addAttribute("usuario", usuario);
			return "userResult";
		} 

	@CrossOrigin(origins="*", maxAge = 3600)
	public ObjectNode createUserBDD(@RequestBody(required = false) Usuario usuario) {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		try {
		if (!userAlreadyExists(usuario.getNombre_usuario(), usuario.getMail())) {
			int idNewUser = (int) selectLastUserId() + 1;
			usuario.setId_usuario(idNewUser);
			System.out.println(usuario);
			// String
			// pwdEncriptada=PasswordEncrypt.SecuredPasswordGenerator.encryptPwd(usuario.getPassword());
			String queryCreateUser = "insert into LEAGUEPONG.usuarios usuario"
					+ " NOMBRE_USUARIO = \"" + usuario.getNombre_usuario() + "\", PASSWORD = \""
					+ usuario.getPassword() + "\", MAIL= \"" + usuario.getMail() + "\", LOCALIDAD = \""
					+ usuario.getLocalidad() + "\", NIVEL=" + usuario.getNivel();
		
				jdbcTemplate.execute(queryCreateUser);
				objectNode.put("message", "Usuario creado correctamente");
		}
				else {
					objectNode.put("message", "usuario ya existe");
				}
		}
		 catch (Exception e) {
				objectNode.put("message", "Error al crear usuario");
				objectNode.put("error", e.toString());
			}
		 
		

		return objectNode;
	}

	@CrossOrigin(origins="*", maxAge = 3600)
	public long selectLastUserId() {
		long result = jdbcTemplate.queryForObject("SELECT MAX(id_usuario) FROM leaguepong.usuarios;", Long.class);
		return result;
	}
	
@CrossOrigin(origins="*", maxAge = 3600)
	public boolean userAlreadyExists(String username, String mail) {
		String sql = "SELECT count(*) FROM usuarios WHERE nombre_usuario = ? or mail = ?";
		int count = jdbcTemplate.queryForObject(sql, new Object[] { username, mail }, Integer.class);
		return count > 0;
	}
}
