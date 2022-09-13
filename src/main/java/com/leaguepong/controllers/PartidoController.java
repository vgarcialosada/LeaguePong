package com.leaguepong.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class PartidoController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// update de partido para asignar ganadorusuario medainte datos POST pasados por
	@PutMapping(value = "{partido_id}/{ganador_id}/set_winner")
    public ObjectNode setwinner(@PathVariable(required=false,name="partido_id")long partido_id,
    		@PathVariable(required=false,name="ganador_id")long ganador_id){
			String QueryupdateWinner;	
			QueryupdateWinner = "update leaguepong.partidos set id_ganador= "+ganador_id+ " where id_partido=" + partido_id;
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
		}}
