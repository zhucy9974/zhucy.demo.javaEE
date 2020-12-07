package demo.service;

import java.util.Map;

public interface AuthService {
	Map<String, String> checkLogin(Map<String, String> param);
}
