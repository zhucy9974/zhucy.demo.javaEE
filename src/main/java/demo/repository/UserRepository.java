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

	@Query(value = "SELECT u FROM User u WHERE name=:name")
	public User findNameHql(@Param("name") String name);

	@Query(value = "SELECT * FROM user WHERE name=?", nativeQuery = true)
	public User findNameSql(String name);
	
	
	public User findByEmail(String email);

}
