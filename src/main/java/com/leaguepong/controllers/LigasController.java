package com.leaguepong.controllers;

import java.math.BigInteger;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.leaguepong.entities.Liga;

@RestController
public class LigasController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@CrossOrigin(origins="*", maxAge = 3600)

	@GetMapping("/todas-ligas")
	public List<Liga> allLeagues(@RequestParam(required = false) String name,
			@RequestParam(required = false) String location) {
		List<Liga> ligaArr = new ArrayList<Liga>();
		String QUERY;
		if (name == null && location == null) {
			QUERY = "select * from leaguepong.ligas;";
		} else if (name != null && location == null) {
			QUERY = "select ligas.ID_LIGA, NOMBRE, PASSWORD, REGLAS, UBICACION, NUMERO_JUGADORES"
					+ " from leaguepong.ligas inner join leaguepong.usuarios_ligas on ligas.ID_LIGA"
					+ " = usuarios_ligas.ID_LIGA where NOMBRE LIKE \"%" + name + "%\";";
		} else if (name == null && location != null) {
			QUERY = "select ligas.ID_LIGA, NOMBRE, PASSWORD, REGLAS, UBICACION, NUMERO_JUGADORES"
					+ " from leaguepong.ligas inner join leaguepong.usuarios_ligas on ligas.ID_LIGA"
					+ " = usuarios_ligas.ID_LIGA where UBICACION LIKE \"%" + location + "%\";";
		} else {
			QUERY = "select ligas.ID_LIGA, NOMBRE, PASSWORD, REGLAS, UBICACION, NUMERO_JUGADORES"
					+ " from leaguepong.ligas inner join leaguepong.usuarios_ligas on ligas.ID_LIGA"
					+ " = usuarios_ligas.ID_LIGA where UBICACION LIKE \"%" + location + "%\" and NOMBRE LIKE \"%" + name
					+ "%\";";
		}
		List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
		for (Map<String, Object> result : results) {
			Liga liga = new Liga();
			liga.setId_liga(((BigInteger) result.get("ID_LIGA")).longValue());
			liga.setNombre((String) result.get("NOMBRE"));
			liga.setPassword((String) result.get("PASSWORD"));
			liga.setReglas((String) result.get("REGLAS"));
			liga.setUbicacion((String) result.get("UBICACION"));
			liga.setNumero_jugadores((Integer) result.get("NUMERO_JUGADORES"));
			ligaArr.add(liga);
		}
		return ligaArr;
	}
	@CrossOrigin(origins="*", maxAge = 3600)
	
	@GetMapping("{id}/mis-ligas")
	
	public List<Liga> myLeagues(@PathVariable long id) {
		final String QUERY = "select ligas.ID_LIGA, NOMBRE, PASSWORD, REGLAS, UBICACION, NUMERO_JUGADORES"
				+" from leaguepong.ligas inner join leaguepong.usuarios_ligas "
				+"on ligas.ID_LIGA = usuarios_ligas.ID_LIGA where ID_USUARIO ="+ id + "; ";
		
		List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
		List<Liga> ligaArr = new ArrayList<Liga>();

		for (Map<String, Object> result : results) {
			Liga liga = new Liga();
			liga.setId_liga(((BigInteger) result.get("ID_LIGA")).longValue());
			liga.setNombre((String) result.get("NOMBRE"));
			liga.setPassword((String) result.get("PASSWORD"));
			liga.setReglas((String) result.get("REGLAS"));
			liga.setUbicacion((String) result.get("UBICACION"));
			liga.setNumero_jugadores((Integer) result.get("NUMERO_JUGADORES"));
			ligaArr.add(liga);

		}
		return ligaArr;
	}

	@PostMapping("{id}/crear-liga")
	public ObjectNode createLeague(@PathVariable long id, @RequestBody Liga liga){

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		
		String queryCreateLeague = "insert into ligas set NOMBRE = \"" + liga.getNombre() + "\", PASSWORD = \""
				+ liga.getPassword() + "\", REGLAS= \"" + liga.getReglas() + "\", UBICACION = \""
				+ liga.getUbicacion() + "\", NUMERO_JUGADORES=" + liga.getNumero_jugadores() + ";";
		
		String querySetUserToleague = "insert into usuarios_ligas set id_usuario = "+id
				+", id_liga=(select id_liga from ligas where nombre ='"+liga.getNombre()+"'), is_admin = true;";
		try {
			jdbcTemplate.execute(queryCreateLeague);
			jdbcTemplate.execute(querySetUserToleague);
			objectNode.put("message", "Liga creada correctamente");
		} catch (Exception e) {
			objectNode.put("message", "Error al crear liga");
			objectNode.put("error", e.toString());
		}

		return objectNode;
	}
	
	@DeleteMapping("{id_liga}/eliminar-liga")
	public ObjectNode deleteLeague(@PathVariable long id_liga){

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		
		
		String queryDeleteMatchesOfLeague = "delete from partidos where id_liga = "+id_liga+";";
		String queryDeleteUsersOfLeague = "delete from usuarios_ligas where id_liga = "+id_liga+";";
		String queryDeleteLeague = "delete from ligas where id_liga = "+id_liga+";";
		try {
			jdbcTemplate.execute(queryDeleteMatchesOfLeague);
			jdbcTemplate.execute(queryDeleteUsersOfLeague);
			jdbcTemplate.execute(queryDeleteLeague);
			
			objectNode.put("message", "Liga eliminada correctamente");
		} catch (Exception e) {
			objectNode.put("message", "Error al eliminar liga");
			objectNode.put("error", e.toString());
		}

		return objectNode;
	}
}
