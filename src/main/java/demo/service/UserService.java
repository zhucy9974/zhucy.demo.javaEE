package demo.service;

import java.util.List;
import java.util.Map;
import demo.entity.user.User;
import demo.view.entityview.PageV;
import demo.view.entityview.UserView;

public interface UserService {
	public UserView findById(Long id);

	public UserView save(User user);

	public UserView findNameHql(String name);
	
	public void deleteById(Long id);
	
	public List<UserView> getAllUsers();
	
	public PageV<UserView> getUsersByPage(Map<String, Object> criterias);

	public UserView createUser(UserView userV);
	
	public UserView updateUser(UserView userV);
	
	public void getByCriteria(String username);

	public PageV<UserView> getUsersByCriteriasAndPagination(Map<String, Object> criteriasAndPagination);

}
