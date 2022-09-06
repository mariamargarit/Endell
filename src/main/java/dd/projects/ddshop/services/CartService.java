package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CartDTO;
import dd.projects.ddshop.entities.*;
import dd.projects.ddshop.mappers.CartMapperImpl;
import dd.projects.ddshop.mappers.UserMapperImpl;
import dd.projects.ddshop.repos.CartRepository;
import dd.projects.ddshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapperImpl cartMapper;
    private final UserMapperImpl userMapper;
    private final UserRepository userRepository;

    @Autowired
    public CartService (CartRepository cartRepository, CartMapperImpl cartMapper, UserMapperImpl userMapper, UserRepository userRepository){
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
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
    public void updateCart(int id, CartDTO newCartDTO) {
        Cart cart = cartRepository.findById(id).get();
        cart.setTotalPrice(newCartDTO.getTotalPrice());
        cartRepository.save(cart);
    }
    public void deleteCart(int id) { cartRepository.deleteById(id); }
}
