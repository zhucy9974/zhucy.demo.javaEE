package demo.service;

import java.util.List;
import java.util.Map;
import demo.entity.user.User;
import demo.view.entityview.UserView;

public interface UserService {
	public UserView findById(Long id);// 查询用户通过id

	public UserView save(User user);// 保存用户

	public UserView findNameHql(String name);
	
	public void deleteById(Long id);
	
	public List<UserView> getAllUsers();

	public UserView createUser(UserView userV);
	
	public void getByCriteria(String username);

	public List<UserView> getUsersByCriterias(Map<String, String> criterias);

}
