package demo.controller;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import demo.entity.user.User;
import demo.entityView.UserView;
import demo.service.UserService;
import demo.tool.DemoConstants;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("")
	public String demo() {
		return "users";
	}

	@RequestMapping("findById")
	public UserView findById(Long id) {
		return userService.findById(id);
	}

	@RequestMapping("save")
	public UserView save() {
		User user = new User();
		user.setUsername("xingsfdz");
		user.setPassword("123456");
		user.setEmail("zhucy99@gmail.com");
		user.setStatus(1);
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		return userService.save(user);
	}
	
	@ResponseBody
	@RequestMapping(value = "createUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public UserView createUser(@RequestBody UserView userV) {
		return this.userService.createUser(userV);
	}

	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String delete(@RequestBody String param) {
		JsonObject jobj = new Gson().fromJson(param, JsonObject.class);
		this.userService.deleteById(jobj.get("id").getAsLong());
		return "delete ok!";
	}

	@RequestMapping("import")
	public String importData() {
		List<UserView> users = new Gson().fromJson(DemoConstants.USER_IMPORT,
				new TypeToken<List<UserView>>() {
				}.getType());

		users.forEach(userV -> {
			userService.save(new User(userV));
		});
		return "Ok";
	}
	
	@RequestMapping("getUsers")
	public List<UserView> getUser(){
		return this.userService.getAllUsers();
	}

}
