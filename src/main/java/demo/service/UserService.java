package demo.service;

import java.util.List;
import java.util.Map;

import demo.view.entityview.GroupView;
import demo.view.entityview.PageV;
import demo.view.entityview.UserView;

public interface UserService {

	public void deleteById(Long id);

	public UserView createOrUpdate(UserView userV);

	public PageV<UserView> getByCriteriasAndPagination(Map<String, Object> criteriasAndPagination);

}
