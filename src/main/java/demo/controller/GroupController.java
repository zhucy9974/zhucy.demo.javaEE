package demo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import demo.service.GroupService;
import demo.view.entityview.GroupView;
import demo.view.entityview.PageV;

@RequestMapping("/group")
@RestController
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@PostMapping(value = "patch", produces = "application/json;charset=UTF-8")
	public GroupView updateUser(@RequestBody GroupView groupV) {
		return this.groupService.createOrUpdate(groupV);
	}
	
	@PostMapping(value = "post", produces = "application/json;charset=UTF-8")
	public GroupView createUser(@RequestBody GroupView groupV) {
		return this.groupService.createOrUpdate(groupV);
	}

	@PostMapping(value = "delete", produces = "application/json;charset=UTF-8")
	public String delete(@RequestBody String param) {
		JsonObject jobj = new Gson().fromJson(param, JsonObject.class);
		this.groupService.deleteById(jobj.get("id").getAsLong());
		return "delete ok!";
	}
	
	@PostMapping(value = "get", produces = "application/json;charset=UTF-8")
	public PageV<GroupView> getUser(@RequestBody Map<String, Object> criterias){
		return this.groupService.getByCriteriasAndPagination(criterias);
	}

}
