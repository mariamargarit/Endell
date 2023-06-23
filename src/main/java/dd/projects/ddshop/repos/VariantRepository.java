package dd.projects.ddshop.repos;

import dd.projects.ddshop.entities.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer> {
    @Query(
            "select v from Variant v where v.productId.subcategoryId.id=:id"
    )
    List<Variant> findByProductSubcategory(Integer id);
    @Query(
            "select v from Variant v where v.productId.brand.id=:id"
    )
    List<Variant> findByProductBrand(Integer id);
}
