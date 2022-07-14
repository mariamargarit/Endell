package dd.projects.ddshop.repos;

import dd.projects.ddshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> { }
