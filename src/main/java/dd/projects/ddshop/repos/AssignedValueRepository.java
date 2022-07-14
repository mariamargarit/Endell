package dd.projects.ddshop.repos;

import dd.projects.ddshop.entities.AssignedValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedValueRepository extends JpaRepository<AssignedValue, Integer> { }
