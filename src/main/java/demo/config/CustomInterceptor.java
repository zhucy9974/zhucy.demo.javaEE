package demo.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;

import demo.service.JwtService;

public class CustomInterceptor implements HandlerInterceptor {

	private final JwtService jwtService;

	public CustomInterceptor(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler
	) throws IOException {

		// Laisser passer les requêtes de vérification CORS
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			return true;
		}

		String authorization = request.getHeader("Authorization");

		if (authorization == null || !authorization.startsWith("Bearer ")) {
			sendUnauthorized(response);
			return false;
		}

		String token = authorization.substring(7);

		try {
			jwtService.validateAndGetEmail(token);
			return true;
		} catch (JWTVerificationException exception) {
			sendUnauthorized(response);
			return false;
		}
	}

	private void sendUnauthorized(HttpServletResponse response)
			throws IOException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(
				"{\"status\":401,\"message\":\"Invalid or missing token\"}"
		);
	}
}
