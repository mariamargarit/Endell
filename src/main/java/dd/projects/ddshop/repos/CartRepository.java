package dd.projects.ddshop.repos;

import dd.projects.ddshop.entities.Cart;
import dd.projects.ddshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findCartByUserId(User userId);
    Cart findCartByUserIdAndValid(User userId, Boolean valid);
    List<Cart> findCartsByUserIdAndValid(User userId, Boolean valid);
}
