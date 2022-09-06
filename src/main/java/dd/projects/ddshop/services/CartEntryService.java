package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CartEntryDTO;
import dd.projects.ddshop.entities.*;
import dd.projects.ddshop.mappers.CartEntryMapperImpl;
import dd.projects.ddshop.mappers.CartMapperImpl;
import dd.projects.ddshop.mappers.VariantMapperImpl;
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
    private final CartEntryMapperImpl cartEntryMapper;
    private final VariantMapperImpl variantMapper;
    private final CartMapperImpl cartMapper;


    @Autowired
    public CartEntryService (CartEntryRepository cartEntryRepository, CartRepository cartRepository, UserRepository userRepository, VariantRepository variantRepository, CartEntryMapperImpl cartEntryMapper, VariantMapperImpl variantMapper, CartMapperImpl cartMapper){
        this.cartEntryRepository = cartEntryRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.variantRepository = variantRepository;
        this.cartEntryMapper = cartEntryMapper;
        this.variantMapper = variantMapper;
        this.cartMapper = cartMapper;
    }

    public CartEntryDTO createCartEntry (CartEntryDTO cartEntryDTO, int id) {
        Cart cart = cartRepository.findCartByUserId(userRepository.getReferenceById(id));
        if(cart == null) {
            cart = new Cart(userRepository.getReferenceById(id));
            cartRepository.save(cart);
        }

        CartEntry cartEntry = cartEntryRepository.findCartEntryByVariantIdAndCartId(variantMapper.toVariant(cartEntryDTO.getVariantId()), cart);
        if(cartEntry == null) {
            Variant variant = variantMapper.toVariant(cartEntryDTO.getVariantId());
            CartEntry newCartEntry = new CartEntry(cartEntryDTO.getQuantity(), variant.getPrice(), variant.getPrice() * cartEntryDTO.getQuantity(), variant, cart);
            cartEntryRepository.save(newCartEntry);
            cart.getCartEntry().add(newCartEntry);
            cart.setTotalPrice(cart.getTotalPrice() + variant.getPrice());
        }
        else {
            cartEntry.setTotalPricePerEntry(cartEntry.getTotalPricePerEntry() + cartEntry.getPricePerPiece());
            cartEntry.setQuantity(cartEntry.getQuantity() + cartEntryDTO.getQuantity());
            cart.setTotalPrice(cart.getTotalPrice() + cartEntry.getPricePerPiece());
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

    public void updateCartEntry (int id, CartEntryDTO newCartEntryDTO) {
        CartEntry cartEntry = cartEntryRepository.findById(id).get();
        cartEntry.setQuantity(newCartEntryDTO.getQuantity());
        cartEntry.setPricePerPiece(newCartEntryDTO.getPricePerPiece());
        cartEntry.setTotalPricePerEntry(newCartEntryDTO.getTotalPricePerEntry());
        cartEntry.setVariantId(variantMapper.toVariant(newCartEntryDTO.getVariantId()));
//        cartEntry.setCartId(cartMapper.toCart(newCartEntryDTO.getCartId()));
        cartEntryRepository.save(cartEntry);
    }

    public void deleteCartEntry (int id) {
        cartEntryRepository.deleteById(id);
    }
}
