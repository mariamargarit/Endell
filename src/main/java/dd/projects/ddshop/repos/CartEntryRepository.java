package dd.projects.ddshop.repos;

import dd.projects.ddshop.entities.Cart;
import dd.projects.ddshop.entities.CartEntry;
import dd.projects.ddshop.entities.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartEntryRepository extends JpaRepository<CartEntry, Integer> {
    CartEntry findCartEntryByVariantId(Variant variantId);
    CartEntry findCartEntryByVariantIdAndCartId(Variant variant, Cart cart);
}
