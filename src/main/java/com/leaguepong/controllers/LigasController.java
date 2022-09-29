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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.leaguepong.entities.Liga;

@RestController
public class LigasController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping("{id}/get-liga")
	public List<Liga> getLigaById(@PathVariable long id) {
		final String QUERY = "select ligas.ID_LIGA, NOMBRE, PASSWORD, REGLAS, UBICACION, NUMERO_JUGADORES"
				+ " from leaguepong.ligas where ID_LIGA =" + id + "; ";

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
	
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping("{id}/todas-ligas")
	public List<Liga> allLeagues(@PathVariable long id) {
		List<Liga> ligaArr = new ArrayList<Liga>();
		String QUERY;
			QUERY = "select distinct ligas.ID_LIGA, NOMBRE, PASSWORD, REGLAS, UBICACION, NUMERO_JUGADORES\r\n"
					+ " from leaguepong.ligas inner join usuarios_ligas on ligas.id_liga = usuarios_ligas.id_liga\r\n"
					+ " where usuarios_ligas.ID_USUARIO != "+id+" and nombre not in\r\n"
					+ " (select nombre from ligas inner join usuarios_ligas on usuarios_ligas.id_liga = ligas.id_liga where id_usuario="+id+");";
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

	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping("{id}/mis-ligas")

	public List<Liga> myLeagues(@PathVariable long id) {
		final String QUERY = "select ligas.ID_LIGA, NOMBRE, PASSWORD, REGLAS, UBICACION, NUMERO_JUGADORES"
				+ " from leaguepong.ligas inner join leaguepong.usuarios_ligas "
				+ "on ligas.ID_LIGA = usuarios_ligas.ID_LIGA where ID_USUARIO =" + id + "; ";

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

	/*
	 * Crea una liga a partir de un objeto Liga pasado desde el front Añade el
	 * usuario logeado a la liga y lo hace admin
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping("{id}/crear-liga")
	public ObjectNode createLeague(@PathVariable long id, @RequestBody Liga liga) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		String queryCreateLeague = "insert into ligas set NOMBRE = \"" + liga.getNombre() + "\", PASSWORD = \""
				+ liga.getPassword() + "\", REGLAS= \"" + liga.getReglas() + "\", UBICACION = \"" + liga.getUbicacion()
				+ "\", NUMERO_JUGADORES=" + liga.getNumero_jugadores() + ";";

		String querySetUserToleague = "insert into usuarios_ligas set id_usuario = " + id
				+ ", id_liga=(select id_liga from ligas where nombre ='" + liga.getNombre() + "'), is_admin = true;";

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

	// elimina todos los registros de una liga, partidos, usuarios y la liga
	@CrossOrigin(origins = "*", maxAge = 3600)
	@DeleteMapping("eliminar-liga/{id_liga}")
	public ObjectNode deleteLeague(@PathVariable long id_liga) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();

		String queryDeleteMatchesOfLeague = "delete from partidos where id_liga = " + id_liga + ";";
		String queryDeleteUsersOfLeague = "delete from usuarios_ligas where id_liga = " + id_liga + ";";
		String queryDeleteLeague = "delete from ligas where id_liga = " + id_liga + ";";
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
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping("{search}/{id}/buscar-ligas")
	public List<Liga> searchLeague(@PathVariable String search, @PathVariable long id) {
		final String QUERY = "select distinct ligas.ID_LIGA, NOMBRE, PASSWORD, REGLAS, UBICACION, NUMERO_JUGADORES"
				+ " from leaguepong.ligas inner join leaguepong.usuarios_ligas "
				+ "on ligas.ID_LIGA = usuarios_ligas.ID_LIGA where NOMBRE LIKE '%" + search + "%' or UBICACION LIKE '%" + search + "%' and nombre not in (select nombre from ligas inner join usuarios_ligas on usuarios_ligas.id_liga = ligas.id_liga where id_usuario="+id+");";
	
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
	
	/*
	 * Crea una liga a partir de un objeto Liga pasado desde el front Añade el
	 * usuario logeado a la liga y lo hace admin
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping("{id_liga}/modificar-liga")
	public ObjectNode modifyLeague(@PathVariable long id_liga, @RequestBody Liga liga) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		String queryModifyLeague = "update ligas set NOMBRE = \"" + liga.getNombre() + "\", REGLAS= \"" + liga.getReglas() + "\", UBICACION = \"" + liga.getUbicacion()
				+ "\", NUMERO_JUGADORES=" + liga.getNumero_jugadores() + " where id_liga = "+id_liga+";";
		System.out.println(queryModifyLeague);
		try {
			jdbcTemplate.execute(queryModifyLeague);
			objectNode.put("message", "Liga creada correctamente");
		} catch (Exception e) {
			objectNode.put("message", "Error al crear liga");
			objectNode.put("error", e.toString());
		}

		return objectNode;
	}
}
