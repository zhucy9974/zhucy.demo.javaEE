package demo.service;

import java.util.List;
import java.util.Map;

import demo.view.entityview.GroupView;
import demo.view.entityview.PageV;

public interface GroupService {
	public void deleteById(Long id);

	public GroupView createOrUpdate(GroupView userV);

	public PageV<GroupView> getByCriteriasAndPagination(Map<String, Object> criteriasAndPagination);
	
	public List<GroupView> getGroups(Long id);
}
