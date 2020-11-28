package demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import demo.dao.UserRepository;
import demo.entity.user.User;
import demo.entityView.UserView;
import demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userDao;
 
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
		users.forEach(user->{
			userViews.add(new UserView(user));
		});
		return userViews;
	}

	@Override
	public UserView createUser(UserView userV) {
		if(userV.getPassword()==null) {
			userV.setPassword("12345678");
		}
		User user = this.userDao.save(new User(userV));
		return new UserView(user);
	}

}
