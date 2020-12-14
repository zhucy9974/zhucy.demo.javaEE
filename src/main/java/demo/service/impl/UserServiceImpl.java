package demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import demo.entity.user.User;
import demo.repository.UserRepository;
import demo.service.UserService;
import demo.tool.DBTools;
import demo.view.entityview.PageV;
import demo.view.entityview.UserView;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EntityManager entityManager;

	public UserView findById(Long id) {
		Optional<User> userOp = userRepository.findById(id);
		if(userOp.isPresent()) {
			return new UserView(userOp.get());
		}else {
			return null;
		}
	}

	public UserView save(User user) {
		return new UserView(userRepository.save(user));
	}

	public UserView findNameHql(String name) {
		return new UserView(userRepository.findNameHql(name));
	}

	@Override
	public void deleteById(Long id) {
		this.userRepository.deleteById(id);

	}

	@Override
	public List<UserView> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		List<UserView> userViews = new ArrayList<>();
		users.forEach(user -> userViews.add(new UserView(user)));
		return userViews;
	}
	
	@Override
	public PageV<UserView> getUsersByPage(int page, int pageSize) {
		Pageable pageParam = PageRequest.of(page, pageSize);
		Page<User> pageUsers= this.userRepository.findAll(pageParam);
		PageV<UserView> pageUsersV = new PageV<>(pageUsers);
		List<UserView> userViews = new ArrayList<>();
		pageUsers.getContent().forEach(user -> userViews.add(new UserView(user)));
		pageUsersV.setElements(userViews);
		return pageUsersV;
	}

	@Override
	public UserView createUser(UserView userV) {
		if (userV.getPassword() == null) {
			userV.setPassword("12345678");
		}
		User user = this.userRepository.save(new User(userV));
		return new UserView(user);
	}

	/**
	 * juste un example
	 */
	@Override
	public void getByCriteria(String username) {

		// pour obtenir la session hibernate
		// Session s = this.entityManager.unwrap(Session.class); or
		// Session s = (Session) this.entityManager.getDelegate();

		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<User> q = cb.createQuery(User.class);
		Root<User> r = q.from(User.class);
		q.where(cb.equal(r.get("username"), cb.parameter(String.class, "un")));
		TypedQuery<User> query = this.entityManager.createQuery(q);
		query.setParameter("un", username);
		List<User> list = query.getResultList();
		// 已经过时
		// Criteria criteria = session.createCriteria(User.class);
	}

	@Override
	public List<UserView> getUsersByCriterias(Map<String, String> criterias) {
		List<User> users = DBTools.getElementsByCriteria(criterias, User.class, this.entityManager);
		List<UserView> userVs = new ArrayList<>();
		users.forEach(user->userVs.add(new UserView(user)));
		return userVs;
	}

	@Override
	public UserView updateUser(UserView userV) {
		User user = this.userRepository.save(new User(userV));
		return new UserView(user);
	}

}
