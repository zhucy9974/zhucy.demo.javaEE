package demo.service;

import java.util.Map;

import demo.view.entityview.GroupView;
import demo.view.entityview.PageV;

public interface GroupService {
	public void deleteById(Long id);

	public GroupView createOrUpdateUser(GroupView userV);

	public PageV<GroupView> getGroupsByCriteriasAndPagination(Map<String, Object> criteriasAndPagination);
}
