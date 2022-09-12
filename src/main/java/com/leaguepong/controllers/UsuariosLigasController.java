package com.leaguepong.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.leaguepong.entities.UsuarioLiga;

@RestController
public class UsuariosLigasController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//trae todos los usuarios de una liga en orden segun sus puntos, los partidos jugados y su id
	@GetMapping("usuarios/{id_liga}")
	public List<UsuarioLiga> usersLeague(@PathVariable long id_liga) {
		final String QUERY="select distinct NOMBRE_USUARIO as NOMBRE, usuarios.ID_USUARIO,"
				+ " count((select id_ganador where id_ganador = usuarios_ligas.ID_USUARIO)) as PUNTOS,"
				+ " count((select ID_GANADOR where ID_JUGADOR_1 = usuarios_ligas.ID_USUARIO or ID_JUGADOR_2 = usuarios_ligas.ID_USUARIO)) as PARTIDOS_JUGADOS from usuarios_ligas"
				+ "inner join usuarios on usuarios_ligas.ID_USUARIO = usuarios.ID_USUARIO"
				+ "inner join partidos on usuarios_ligas.ID_LIGA = partidos.ID_LIGA"
				+ "where usuarios_ligas.ID_LIGA = "+id_liga+" group by NOMBRE_USUARIO order by  PUNTOS desc;";
		
		List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
		List<UsuarioLiga> usuariosLigaArr = new ArrayList<UsuarioLiga>();
		
		for(Map<String, Object> usuario : results) {
			UsuarioLiga usuarioLiga = new UsuarioLiga();
			usuarioLiga.setNombre_usuario((String) usuario.get("NOMBRE"));
			usuarioLiga.setPuntos((int) usuario.get("PUNTOS"));
			usuarioLiga.setPuntos((int) usuario.get("PARTIDOS_JUGADOS"));
			usuariosLigaArr.add(usuarioLiga);
		}
		return usuariosLigaArr;
	}

	@PostMapping("{id}/add-usuario/{id_liga}")
	public ObjectNode AddUserToLeague(@PathVariable long id_liga, long id) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		
		final String queryAddUser="insert into usuarios_ligas set id_usuario = "+id+","
				+ "id_liga="+id_liga+", is_admin = false;";
		final String queryAddMatches="insert into partidos set id_jugador_1 = "+id+","
				+ "id_jugador_2 = , id_liga="+id_liga+";";
		
		try {
			jdbcTemplate.execute(queryAddUser);
			jdbcTemplate.execute(queryAddMatches);
			objectNode.put("message", "Usuario añadido correctamente");
		} catch (Exception e) {
			objectNode.put("message", "Error al unirte a la liga");
			objectNode.put("error", e.toString());
		}

		return objectNode;
	}
}
