package com.example.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	public class E14Controller {
		@GetMapping("/leaguepong")
		@ResponseBody
		public String getFactura(@PathVariable("id") int idFactura) {
			return "/html/main.html";
		}
}}

	