package dd.projects.ddshop.repos;

import dd.projects.ddshop.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Brand findBrandByName(String name);
}
