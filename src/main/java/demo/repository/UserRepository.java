package demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public Optional<User> findById(Long id);

	@SuppressWarnings("unchecked")
	public User save(User user);

	@Query("SELECT u FROM User u WHERE username=:username")
	public User findByUserNameHql(@Param("username") String username);

	@Query(value = "SELECT * FROM app_user WHERE username=?1", nativeQuery = true)
	public User findByUserNameSql(String username);
	
	public User findByEmail(String email);

}
