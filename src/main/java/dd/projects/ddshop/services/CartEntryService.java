package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CartEntryDTO;
import dd.projects.ddshop.entities.Cart;
import dd.projects.ddshop.entities.CartEntry;
import dd.projects.ddshop.entities.Variant;
import dd.projects.ddshop.mappers.CartEntryMapper;
import dd.projects.ddshop.mappers.CartMapper;
import dd.projects.ddshop.mappers.VariantMapper;
import dd.projects.ddshop.repos.CartEntryRepository;
import dd.projects.ddshop.repos.CartRepository;
import dd.projects.ddshop.repos.UserRepository;
import dd.projects.ddshop.repos.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class CartEntryService {
    private final CartEntryRepository cartEntryRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final VariantRepository variantRepository;
    private final CartEntryMapper cartEntryMapper;
    private final VariantMapper variantMapper;
    private final CartMapper cartMapper;


    @Autowired
    public CartEntryService (CartEntryRepository cartEntryRepository, CartRepository cartRepository, UserRepository userRepository, VariantRepository variantRepository, CartEntryMapper cartEntryMapper, VariantMapper variantMapper, CartMapper cartMapper){
        this.cartEntryRepository = cartEntryRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.variantRepository = variantRepository;
        this.cartEntryMapper = cartEntryMapper;
        this.variantMapper = variantMapper;
        this.cartMapper = cartMapper;
    }

    public CartEntryDTO createCartEntry (CartEntryDTO cartEntryDTO, int id) {
        Cart cart = cartRepository.findCartByUserIdAndValid(userRepository.getReferenceById(id), true);
        if(cart == null) {
            cart = new Cart(userRepository.getReferenceById(id));
            cartRepository.save(cart);
        }

        CartEntry cartEntry = cartEntryRepository.findCartEntryByVariantIdAndCartId(variantMapper.toVariant(cartEntryDTO.getVariantId()), cart);
        if(cartEntry == null) {
            Variant variant = variantMapper.toVariant(cartEntryDTO.getVariantId());
            CartEntry newCartEntry = new CartEntry(cartEntryDTO.getQuantity(), variant.getProductId().getPrice(), variant.getProductId().getPrice() * cartEntryDTO.getQuantity(), variant, cart, cartEntryDTO.getSelectedSize());
            cartEntryRepository.save(newCartEntry);
            cart.getCartEntry().add(newCartEntry);
            cart.setTotalPrice(cart.getTotalPrice() + newCartEntry.getTotalPricePerEntry());
        }
        else {
            cartEntry.setTotalPricePerEntry(cartEntry.getTotalPricePerEntry() + cartEntry.getPricePerPiece() * cartEntryDTO.getQuantity());
            cartEntry.setQuantity(cartEntry.getQuantity() + cartEntryDTO.getQuantity());
            cart.setTotalPrice(cart.getTotalPrice() + cartEntry.getPricePerPiece() * cartEntryDTO.getQuantity());
            cartEntryRepository.save(cartEntry);
        }
        cartRepository.save(cart);
        return cartEntryDTO;
    }

    public Optional<CartEntry> readCartEntry(Integer id) {
        return cartEntryRepository.findById(id);
    }

    public List<CartEntryDTO> getCartEntry() {
        return cartEntryRepository.findAll()
                .stream()
                .map(cartEntryMapper::toCartEntryDTO)
                .collect(toList());
    }

    public void updateCartEntry (Integer id, CartEntryDTO newCartEntryDTO) {
        CartEntry cartEntry = cartEntryRepository.findById(id).get();
        cartEntry.setQuantity(newCartEntryDTO.getQuantity());
        cartEntry.setPricePerPiece(newCartEntryDTO.getPricePerPiece());
        cartEntry.setTotalPricePerEntry(newCartEntryDTO.getTotalPricePerEntry());
        cartEntry.setVariantId(variantMapper.toVariant(newCartEntryDTO.getVariantId()));
//        cartEntry.setCartId(cartMapper.toCart(newCartEntryDTO.getCartId()));
        cartEntryRepository.save(cartEntry);
    }

    public void deleteCartEntry (Integer id) {
        CartEntry entry = cartEntryRepository.getReferenceById(id);
        Cart cart = entry.getCartId();
        cart.setTotalPrice(cart.getTotalPrice() - entry.getTotalPricePerEntry());
        cartEntryRepository.deleteById(id);
    }
}
