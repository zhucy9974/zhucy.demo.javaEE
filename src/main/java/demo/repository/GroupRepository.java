package demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import demo.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{
	public Optional<Group> findById(Long id);
	
	@Query("SELECT g.id, g.name FROM Group g where g.status = 1")
	List<Group> findAllGroupsSimple();
	
	List<Group> findByUsers_Id(@Param("userId") Long userId);

}
