package demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Group;
import demo.entity.User;
import demo.repository.GroupRepository;
import demo.repository.UserRepository;
import demo.service.UserService;
import demo.tool.DBTools;
import demo.view.entityview.GroupView;
import demo.view.entityview.PageV;
import demo.view.entityview.UserView;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private EntityManager entityManager;

	public UserView findById(Long id) {
		Optional<User> userOp = userRepository.findById(id);
		if (userOp.isPresent()) {
			return new UserView(userOp.get());
		} else {
			return null;
		}
	}

	@Override
	public void deleteById(Long id) {
		this.userRepository.deleteById(id);

	}

	@Override
	public UserView createOrUpdate(UserView userV) {
		if (userV.getId() == null) {
			userV.setPassword("12345678");
		}
		User user = this.userRepository.save(new User(userV));
		//Optional<User> op = Optional.ofNullable(null);
		//op.map(User::getUsername).ifPresent(name -> {});
		return new UserView(user);
	}

	@Override
	public PageV<UserView> getByCriteriasAndPagination(Map<String, Object> criteriasAndPagination) {
		//this.groupRepository.findByUsers_Id((long)23);
		return DBTools.getElementsWithPaginationByCriteria(criteriasAndPagination, 
				this.entityManager, User.class, (entity, list)->list.add(new UserView((User) entity)));
	}

}
