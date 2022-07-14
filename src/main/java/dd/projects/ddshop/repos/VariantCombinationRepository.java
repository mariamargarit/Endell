package dd.projects.ddshop.repos;

import dd.projects.ddshop.entities.VariantCombination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantCombinationRepository extends JpaRepository<VariantCombination, Integer> { }
