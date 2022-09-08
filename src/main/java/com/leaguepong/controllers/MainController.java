package com.leaguepong.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leaguepong.entities.Liga;

@RestController
public class MainController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/todas-ligas")
	public List<Liga> index() {
		final String QUERY = "select * from leaguepong.ligas;";
		List<Map<String, Object>> results = jdbcTemplate.queryForList(QUERY);
		List<Liga> ligaArr = new ArrayList<Liga>();
		
		for(Map<String, Object> result : results) {
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

}
