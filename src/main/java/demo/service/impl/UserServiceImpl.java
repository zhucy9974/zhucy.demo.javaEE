package demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.internal.CollectionSubqueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import demo.dao.UserRepository;
import demo.entity.user.User;
import demo.service.UserService;
import demo.tool.DBTools;
import demo.tool.Iterables;
import demo.view.entityView.UserView;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userDao;

	@Autowired
	private EntityManager entityManager;

	public UserView findById(Long id) {
		return new UserView(userDao.findById(id).get());
	}

	public UserView save(User user) {
		return new UserView(userDao.save(user));
	}

	public UserView findNameHql(String name) {
		return new UserView(userDao.findNameHql(name));
	}

	@Override
	public void deleteById(Long id) {
		this.userDao.deleteById(id);

	}

	@Override
	public List<UserView> getAllUsers() {
		List<User> users = this.userDao.findAll();
		List<UserView> userViews = new ArrayList<UserView>();
		users.forEach(user -> {
			userViews.add(new UserView(user));
		});
		return userViews;
	}

	@Override
	public UserView createUser(UserView userV) {
		if (userV.getPassword() == null) {
			userV.setPassword("12345678");
		}
		User user = this.userDao.save(new User(userV));
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
		System.out.println(list.size());
		// 已经过时
		// Criteria criteria = session.createCriteria(User.class);
	}

	@Override
	public List<UserView> getUsersByCriterias(Map<String, String> criterias) {
		List<User> users = DBTools.getElementsByCriteria(criterias, User.class, this.entityManager);
		List<UserView> userVs = new ArrayList<UserView>();
		users.forEach(user->{
			userVs.add(new UserView(user));
		});
		return userVs;
	}

}
