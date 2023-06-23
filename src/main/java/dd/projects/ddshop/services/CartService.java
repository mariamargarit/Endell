package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CartDTO;
import dd.projects.ddshop.entities.Cart;
import dd.projects.ddshop.entities.CartEntry;
import dd.projects.ddshop.entities.User;
import dd.projects.ddshop.mappers.CartMapper;
import dd.projects.ddshop.mappers.UserMapper;
import dd.projects.ddshop.repos.CartEntryRepository;
import dd.projects.ddshop.repos.CartRepository;
import dd.projects.ddshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final CartEntryRepository cartEntryRepository;

    @Autowired
    public CartService (CartRepository cartRepository, CartMapper cartMapper, UserMapper userMapper, UserRepository userRepository, CartEntryRepository cartEntryRepository){
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.cartEntryRepository = cartEntryRepository;
    }
    public void createCart(CartDTO cartDTO, int id){
        Cart cart = cartMapper.toCart(cartDTO);
        User user = userRepository.getReferenceById(id);
        cart.setUserId(user);
        cart.setValid(true);
        cartRepository.save(cart);
    }
    public Cart readCart(Integer id) {
        return cartRepository.getReferenceById(id);
    }

    public CartDTO getCarts(String email) {
        return cartMapper.toCartDTO(cartRepository.findCartByUserId(userRepository.findByEmail(email)));
    }

    public CartDTO getCurrentCart(String email) {
        Cart cart = cartRepository.findCartByUserIdAndValid(userRepository.findByEmail(email), true);
        if(cart == null) {
            cart = new Cart(userRepository.findByEmail(email));
            cartRepository.save(cart);
        }
        return cartMapper.toCartDTO(cart);
    }

    public void updateCart(int id, CartDTO newCartDTO) {
        Cart cart = cartRepository.findById(id).get();
        cart.setTotalPrice(newCartDTO.getTotalPrice());
        cartRepository.save(cart);
    }
    public void updateCartQuantity(int id, Integer newQuantity) {
        CartEntry cartEntry = cartEntryRepository.getReferenceById(id);
        Cart cart = cartEntry.getCartId();
        if(newQuantity<cartEntry.getQuantity()){
            cart.setTotalPrice(cart.getTotalPrice() - cartEntry.getPricePerPiece());
        } else {
            cart.setTotalPrice(cart.getTotalPrice() + cartEntry.getPricePerPiece());
        }
        cartEntry.setQuantity(newQuantity);
        cartEntry.setTotalPricePerEntry(cartEntry.getPricePerPiece() * newQuantity);
        cartEntryRepository.save(cartEntry);
        cartRepository.save(cart);
    }

    public void deleteCart(int id) { cartRepository.deleteById(id); }
}
