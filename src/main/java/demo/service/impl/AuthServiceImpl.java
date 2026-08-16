package demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import demo.entity.User;
import demo.repository.UserRepository;
import demo.service.AuthService;
import demo.service.JwtService;

@Service
public class AuthServiceImpl implements AuthService {

	private final BCryptPasswordEncoder passwordEncoder =
			new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@Override
	public Map<String, String> checkLogin(Map<String, String> param) {
		String email = param.get("email");
		String submittedPassword = param.get("password");

		User user = userRepository.findByEmail(email);
		Map<String, String> response = new HashMap<>();

		response.put("status", "KO");

		if (user == null) {
			response.put("errorMsg", "600");
			return response;
		}

		String storedPassword = user.getPassword();
		boolean passwordValid;

		if (storedPassword != null && storedPassword.startsWith("$2")) {
			// Le mot de passe est déjà chiffré avec BCrypt
			passwordValid = passwordEncoder.matches(
					submittedPassword,
					storedPassword
			);
		} else {
			// Ancien mot de passe encore stocké en clair
			passwordValid = submittedPassword != null
					&& submittedPassword.equals(storedPassword);

			if (passwordValid) {
				// Migration automatique vers BCrypt
				user.setPassword(passwordEncoder.encode(submittedPassword));
				userRepository.save(user);
			}
		}

		if (!passwordValid) {
			response.put("errorMsg", "401");
			return response;
		}

		response.put("status", "OK");
		response.put("token", jwtService.createToken(user.getEmail()));
		response.put("username", user.getUsername());
		response.put("firstName", user.getFirstName());

		return response;
	}
}
