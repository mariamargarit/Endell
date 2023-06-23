package dd.projects.ddshop.repos;

import dd.projects.ddshop.entities.Brand;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);
    List<Product> findBySubcategoryId(Subcategory subcategory);
    List<Product> findByBrand(Brand brand);
}
