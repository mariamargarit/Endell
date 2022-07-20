package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CartDTO;
import dd.projects.ddshop.dtos.CartEntryDTO;
import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.entities.*;
import dd.projects.ddshop.mappers.CartMapperImpl;
import dd.projects.ddshop.mappers.UserMapperImpl;
import dd.projects.ddshop.repos.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapperImpl cartMapper;
    private final UserMapperImpl userMapper;

    @Autowired
    public CartService (CartRepository cartRepository, CartMapperImpl cartMapper, UserMapperImpl userMapper){
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.userMapper = userMapper;
    }
    public static Cart getCartFromDTO(CartDTO cartDTO, User user) {
        Cart cart = new Cart();
        cart.setTotalPrice(cartDTO.getTotalPrice());
        cart.setUserId(user);
        return cart;
    }
    public void createCart(CartDTO cartDTO){
        cartRepository.save(cartMapper.toCart(cartDTO));
    }
    public Cart readCart(Integer id) {
        return cartRepository.getReferenceById(id);
    }
    public List<CartDTO> getCarts() {
        return cartRepository.findAll()
                .stream()
                .map(cartMapper::toCartDTO)
                .collect(toList());
    }
    public void updateCart(int id, CartDTO newCartDTO) {
        Cart cart = cartRepository.findById(id).get();
        cart.setTotalPrice(newCartDTO.getTotalPrice());
        cart.setUserId(userMapper.toUser(newCartDTO.getUserId()));
        cartRepository.save(cart);
    }
    public void deleteCart(int id) { cartRepository.deleteById(id); }
}
