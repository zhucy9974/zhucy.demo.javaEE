package demo.service;

import java.util.Map;
import demo.view.entityview.PageV;
import demo.view.entityview.UserView;

public interface UserService {

	public void deleteById(Long id);

	public UserView createUser(UserView userV);
	
	public UserView updateUser(UserView userV);

	public PageV<UserView> getUsersByCriteriasAndPagination(Map<String, Object> criteriasAndPagination);

}
