package demo.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Group;
import demo.entity.User;
import demo.repository.GroupRepository;
import demo.service.GroupService;
import demo.tool.DBTools;
import demo.view.entityview.GroupView;
import demo.view.entityview.PageV;
import demo.view.entityview.UserView;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private EntityManager entityManager;

	@Override
	public void deleteById(Long id) {
		groupRepository.deleteById(id);
	}

	@Override
	public GroupView createOrUpdate(GroupView groupV) {
		Group g = groupRepository.save(new Group(groupV));
		return new GroupView(g);
	}

	@Override
	public PageV<GroupView> getByCriteriasAndPagination(Map<String, Object> criteriasAndPagination) {
		return DBTools.getElementsWithPaginationByCriteria(criteriasAndPagination, 
				this.entityManager, Group.class, (entity, list)->list.add(new GroupView((Group) entity)));
	}

	@Override
	public List<GroupView> getGroups(Long id) {
		return groupRepository.findByUsers_Id(id).stream()
				.map(GroupView::new)
				.collect(Collectors.toList());
	}

}
