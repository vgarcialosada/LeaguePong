package com.leaguepong.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
@GetMapping("/login_redirect")
public String login() {return "index.html";}
}
