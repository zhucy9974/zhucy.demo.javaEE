package demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.User;
import demo.repository.UserRepository;
import demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Map<String, String> checkLogin(Map<String, String> param) {
		User user = this.userRepository.findByEmail(param.get("email"));
		Map<String, String> res = new HashMap<>();
		res.put("status", "KO");
		if (user == null) {
			res.put("errorMsg", "600");
		}else if(!StringUtils.equals(user.getPassword(), param.get("password"))){
			res.put("errorMsg", "401");
		}else {
			res.put("status", "OK");
			res.put("token", "1");
			res.put("username",user.getUsername());
			res.put("firstName", user.getFirstName());
		}
		return res;
	}

}
