package demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demo.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public Optional<User> findById(Long id);// 查询用户通过id

	@SuppressWarnings("unchecked")
	public User save(User user);// 保存用户

	@Query(value = "SELECT u FROM User u WHERE name=:name")
	public User findNameHql(@Param("name") String name);

	// nativeQuery为true代表使用SQL语言
	@Query(value = "SELECT * FROM user WHERE name=?", nativeQuery = true)
	public User findNameSql(String name);

}
