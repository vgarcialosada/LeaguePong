package com.leaguepong.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.leaguepong.entities.UsuarioLiga;

@RestController
public class UsuariosLigasController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("usuarios/{id_liga}")
	public List<UsuarioLiga> usersLeague(@PathVariable long id_liga) {
		final String QUERY="select distinct NOMBRE_USUARIO as NOMBRE,\r\n"
				+ " count((select id_ganador where id_ganador = usuarios_ligas.ID_USUARIO)) as PUNTOS,\r\n"
				+ " count((select ID_GANADOR where ID_JUGADOR_1 = usuarios_ligas.ID_USUARIO or ID_JUGADOR_2 = usuarios_ligas.ID_USUARIO)) as PARTIDOS_JUGADOS from usuarios_ligas \r\n"
				+ "inner join usuarios on usuarios_ligas.ID_USUARIO = usuarios.ID_USUARIO\r\n"
				+ "inner join partidos on usuarios_ligas.ID_LIGA = partidos.ID_LIGA \r\n"
				+ "where usuarios_ligas.ID_LIGA = "+id_liga+" group by NOMBRE_USUARIO order by  PUNTOS desc;\r\n"
				+ "";
		
		List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
		List<UsuarioLiga> usuariosLigaArr = new ArrayList<UsuarioLiga>();
		
		for(Map<String, Object> usuario : results) {
			usuario.set
		}
		return usuariosLigaArr;
	}

}
