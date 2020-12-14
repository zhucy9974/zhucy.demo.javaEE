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
	public UserView createUser(UserView userV) {
		if (userV.getPassword() == null) {
			userV.setPassword("12345678");
		}
		User user = this.userRepository.save(new User(userV));
		return new UserView(user);
	}

	@Override
	public PageV<UserView> getUsersByCriteriasAndPagination(Map<String, Object> criteriasAndPagination) {
		
		int currentPage = (Integer) criteriasAndPagination.get("page");
		int pageSize = (Integer)criteriasAndPagination.get("pageSize");
		Map<String, String> criterias = (Map<String, String>)criteriasAndPagination.get("criterias");
		
		Map<String, Object> res = DBTools.getElementsByCriteria(criterias, 
				User.class, this.entityManager,
				currentPage, 
				pageSize);
		List<UserView> userVs = new ArrayList<>();
		((List<User>)res.get("results")).forEach(user -> userVs.add(new UserView(user)));
		return new PageV<>(userVs,(long) res.get("totelEl"), currentPage, pageSize);
	}

	@Override
	public UserView updateUser(UserView userV) {
		User user = this.userRepository.save(new User(userV));
		return new UserView(user);
	}

}
