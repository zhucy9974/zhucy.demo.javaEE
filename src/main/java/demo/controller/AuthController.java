package demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.service.AuthService;

@RequestMapping("/auth")
@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping(value = "login", produces = "application/json; charset=UTF-8")
	public Map<String, String> login(@RequestBody Map<String, String> params) {
		return this.authService.checkLogin(params);
	}
}
