package com.leaguepong.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.leaguepong.entities.Admin;
import com.leaguepong.entities.UsuarioLiga;

@RestController
public class UsuariosLigasController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@CrossOrigin(origins="*", maxAge = 3600)
	//trae todos los usuarios de una liga en orden segun sus puntos, los partidos jugados y su id
	@GetMapping("usuarios/{id_liga}")
	public List<UsuarioLiga> usersLeague(@PathVariable long id_liga) {
		final String QUERY="select distinct NOMBRE_USUARIO as NOMBRE, usuarios.ID_USUARIO, \r\n"
				+ " count((select id_ganador where id_ganador = usuarios_ligas.ID_USUARIO)) as PUNTOS,\r\n"
				+ " count((select ID_GANADOR where ID_JUGADOR_1 = usuarios_ligas.ID_USUARIO or ID_JUGADOR_2 = usuarios_ligas.ID_USUARIO)) as PARTIDOS_JUGADOS from usuarios_ligas\r\n"
				+ "inner join usuarios on usuarios_ligas.ID_USUARIO = usuarios.ID_USUARIO\r\n"
				+ "inner join partidos on usuarios_ligas.ID_LIGA = partidos.ID_LIGA\r\n"
				+ "where usuarios_ligas.ID_LIGA = "+id_liga+" group by NOMBRE_USUARIO order by  PUNTOS desc;";
		
		List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
		List<UsuarioLiga> usuariosLigaArr = new ArrayList<UsuarioLiga>();
		
		for(Map<String, Object> usuario : results) {
			UsuarioLiga usuarioLiga = new UsuarioLiga();
			usuarioLiga.setNombre_usuario((String) usuario.get("NOMBRE"));
			usuarioLiga.setPuntos((long) usuario.get("PUNTOS"));
			usuarioLiga.setPartidosJugados((long) usuario.get("PARTIDOS_JUGADOS"));
			usuariosLigaArr.add(usuarioLiga);
		}
		return usuariosLigaArr;
	}

	@CrossOrigin(origins="*", maxAge = 3600)
//	meter un usuario a la liga y crear sus partidos contra todos los que ya esten dentro
	@PostMapping("add-usuario/{id_liga}/{id}")
	public ObjectNode addUserToLeague(@PathVariable Long id_liga,@PathVariable Long id ) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		
		
		final String knowUsersLeague="select id_usuario from usuarios_ligas where id_liga = "+id_liga+";";
		final String queryAddUser="insert into usuarios_ligas set id_usuario = "+id+", id_liga="+id_liga+", is_admin = false;";
		String queryAddUserMatches="insert into partidos set id_liga = "+id_liga+", id_jugador_2 = "+id+", id_jugador_1 =";
		
		try {
			List<Map<String, Object>> results = jdbcTemplate.queryForList(knowUsersLeague);
			jdbcTemplate.execute(queryAddUser);
			for(Map<String, Object> result : results) {
				queryAddUserMatches+=result.get("ID_USUARIO")+";";
				jdbcTemplate.execute(queryAddUserMatches);
				queryAddUserMatches="insert into partidos set id_liga = "+id_liga+", id_jugador_2 = "+id+", id_jugador_1 =";
			}

			objectNode.put("message", "Usuario añadido correctamente");
		} catch (Exception e) {
			objectNode.put("message", "Error al unirte a la liga");
			objectNode.put("error", e.toString());
		}

		return objectNode;
	}
	
	@CrossOrigin(origins="*", maxAge = 3600)
//	eliminar un usuario de la liga
	@DeleteMapping("delete-usuario/{id_liga}/{id}")
	public ObjectNode deleteUserOfLeague(@PathVariable Long id,@PathVariable Long id_liga ) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();

		final String queryDeleteMatchesOfUser="delete from partidos where id_jugador_1 = "+id+" or id_jugador_2 = "+id+" and id_liga = "+id_liga+";";
		final String queryDeleteUserFromLeague="delete from usuarios_ligas where id_usuario = "+id+" and id_liga = "+id_liga+";";
		
		try {
			jdbcTemplate.execute(queryDeleteMatchesOfUser);

			jdbcTemplate.execute(queryDeleteUserFromLeague);

			objectNode.put("message", "Usuario eliminado correctamente");
		} catch (Exception e) {
			objectNode.put("message", "Error al eliminar usuario de la liga");
			objectNode.put("error", e.toString());
		}
	
		return objectNode;
	}
	
	@CrossOrigin(origins="*", maxAge = 3600)
	//trae el estado de admin de una persona
	@GetMapping("admin-liga/{id}/{id_liga}")
	public List<Admin> usersLeague(@PathVariable long id, @PathVariable long id_liga) {
		final String QUERY="select usuarios_ligas.IS_ADMIN as ADMIN from usuarios_ligas "
				+ "inner join usuarios on usuarios_ligas.ID_USUARIO = usuarios.ID_USUARIO "
				+ "where usuarios_ligas.ID_LIGA = "+id_liga+" and usuarios_ligas.ID_USUARIO = "+id+";";
		
		List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
		List<Admin> adminLigaArr = new ArrayList<Admin>();
		
		for(Map<String, Object> usuario : results) {
			Admin admin = new Admin();
			admin.setNombre_usuario((String) usuario.get("NOMBRE"));
			admin.setAdmin((boolean) usuario.get("ADMIN"));
			adminLigaArr.add(admin);
		}
		return adminLigaArr;
	}
}
