package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CartEntryDTO;
import dd.projects.ddshop.entities.*;
import dd.projects.ddshop.mappers.CartEntryMapper;
import dd.projects.ddshop.mappers.CartEntryMapperImpl;
import dd.projects.ddshop.mappers.CartMapperImpl;
import dd.projects.ddshop.mappers.VariantMapperImpl;
import dd.projects.ddshop.repos.CartEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class CartEntryService {
    private final CartEntryRepository cartEntryRepository;
    private final CartEntryMapperImpl cartEntryMapper;
    private final VariantMapperImpl variantMapper;
    private final CartMapperImpl cartMapper;


    @Autowired
    public CartEntryService (CartEntryRepository cartEntryRepository, CartEntryMapperImpl cartEntryMapper, VariantMapperImpl variantMapper, CartMapperImpl cartMapper){
        this.cartEntryRepository = cartEntryRepository;
        this.cartEntryMapper = cartEntryMapper;
        this.variantMapper = variantMapper;
        this.cartMapper = cartMapper;
    }

    public static CartEntry getCartEntryFromDTO(CartEntryDTO cartEntryDTO, Variant variant, Cart cart) {
        CartEntry cartEntry = new CartEntry();
        cartEntry.setQuantity(cartEntryDTO.getQuantity());
        cartEntry.setPricePerPiece(cartEntryDTO.getPricePerPiece());
        cartEntry.setTotalPricePerEntry(cartEntryDTO.getTotalPricePerEntry());
        cartEntry.setVariantId(variant);
        cartEntry.setCartId(cart);
        return cartEntry;
    }

    public void createCartEntry (CartEntryDTO cartEntryDTO) {
//        CartEntry cartEntry = getCartEntryFromDTO(cartEntryDTO, variant, cart);
        cartEntryRepository.save(cartEntryMapper.toCartEntry(cartEntryDTO));
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
        cartEntry.setCartId(cartMapper.toCart(newCartEntryDTO.getCartId()));
        cartEntryRepository.save(cartEntry);
    }

    public void deleteCartEntry (int id) {
        cartEntryRepository.deleteById(id);
    }
}
