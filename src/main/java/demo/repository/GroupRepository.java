package demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import demo.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{
	public Optional<Group> findById(Long id);

}
