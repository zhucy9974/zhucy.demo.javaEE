package demo.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import demo.entity.User;
import demo.service.UserService;
import demo.tool.DemoConstants;
import demo.view.entityview.PageV;
import demo.view.entityview.UserView;

@RequestMapping("/group")
@RestController
public class GroupController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value = "patch", produces = "application/json;charset=UTF-8")
	public UserView updateUser(@RequestBody UserView userV) {
		return this.userService.updateUser(userV);
	}
	
	@PostMapping(value = "post", produces = "application/json;charset=UTF-8")
	public UserView createUser(@RequestBody UserView userV) {
		return this.userService.createUser(userV);
	}

	@PostMapping(value = "delete", produces = "application/json;charset=UTF-8")
	public String delete(@RequestBody String param) {
		JsonObject jobj = new Gson().fromJson(param, JsonObject.class);
		this.userService.deleteById(jobj.get("id").getAsLong());
		return "delete ok!";
	}
	
	@PostMapping(value = "get", produces = "application/json;charset=UTF-8")
	public PageV<UserView> getUser(@RequestBody Map<String, Object> criterias){
		return this.userService.getUsersByCriteriasAndPagination(criterias);
	}

}
