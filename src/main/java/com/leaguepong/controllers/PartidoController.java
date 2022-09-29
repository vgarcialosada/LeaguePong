package com.leaguepong.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.leaguepong.entities.Liga;
import com.leaguepong.entities.Partido;

@RestController
public class PartidoController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//GET DE PARTIDOS POR ID LIGA
	@CrossOrigin(origins = "*", maxAge = 3600)
		@GetMapping("{id_liga}/get-partidos")
		public List<Partido> getPartidos(@PathVariable long id_liga) {
			List<Partido> partidoArr = new ArrayList<Partido>();
			String QUERY;
			QUERY = "SELECT * FROM leaguepong.partidos where id_liga= " + id_liga;
			
			List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
			for (Map<String, Object> result : results) {
				Partido partido = new Partido();
				partido.setId_partido(((BigInteger) result.get("ID_PARTIDO")).longValue());
				partido.setId_jugador_1(((BigInteger) result.get("ID_JUGADOR_1")).longValue());
				partido.setId_jugador_2(((BigInteger) result.get("ID_JUGADOR_2")).longValue());
				partido.setId_jugador_2(((BigInteger) result.get("ID_JUGADOR_2")).longValue());
				partido.setId_ganador(((BigInteger) result.get("ID_GANADOR")).longValue());
				partidoArr.add(partido);
			}
			return partidoArr;
		}
		
	//GET DE PARTIDOS POR ID LIGA
		@CrossOrigin(origins = "*", maxAge = 3600)
			@GetMapping("{id_liga}/get-partidos-por-jugar")
			public List<Partido> getPartidosToPlay(@PathVariable long id_liga) {
				List<Partido> partidoArr = new ArrayList<Partido>();
				String QUERY;
				QUERY = "SELECT * FROM leaguepong.partidos where id_liga= " + id_liga +
						" and id_ganador is null";
				List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
				for (Map<String, Object> result : results) {
					Partido partido = new Partido();
					partido.setId_partido(((BigInteger) result.get("ID_PARTIDO")).longValue());
					partido.setId_jugador_1(((BigInteger) result.get("ID_JUGADOR_1")).longValue());
					partido.setId_jugador_2(((BigInteger) result.get("ID_JUGADOR_2")).longValue());
					partido.setId_jugador_2(((BigInteger) result.get("ID_JUGADOR_2")).longValue());
					partidoArr.add(partido);
				}
				return partidoArr;
			}
		
	// update de partido para asignar ganadorusuario medainte datos POST pasados por
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "{partido_id}/{ganador_id}/set-winner")
	public ObjectNode setwinner(@PathVariable(required = false, name = "partido_id") long partido_id,
			@PathVariable(required = false, name = "ganador_id") long ganador_id) {
		String QueryupdateWinner;
		QueryupdateWinner = "update leaguepong.partidos set id_ganador= " + ganador_id + " where id_partido="
				+ partido_id;
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		try {
			jdbcTemplate.execute(QueryupdateWinner);
			jdbcTemplate.execute(QueryupdateWinner);
			objectNode.put("message", "ganador actualizado");
		} catch (Exception e) {
			objectNode.put("message", "Error al actualizar ganador");
			objectNode.put("error", e.toString());
		}

		return objectNode;
	}


}
