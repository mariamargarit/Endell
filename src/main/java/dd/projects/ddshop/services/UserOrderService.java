package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.UserOrderDTO;
import dd.projects.ddshop.entities.Address;
import dd.projects.ddshop.entities.Cart;
import dd.projects.ddshop.entities.User;
import dd.projects.ddshop.entities.UserOrder;
import dd.projects.ddshop.mappers.AddressMapper;
import dd.projects.ddshop.mappers.UserOrderMapper;
import dd.projects.ddshop.repos.AddressRepository;
import dd.projects.ddshop.repos.CartRepository;
import dd.projects.ddshop.repos.UserOrderRepository;
import dd.projects.ddshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderService {
    private final UserOrderRepository userOrderRepository;
    private final UserOrderMapper userOrderMapper;
    private final AddressMapper addressMapper;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public UserOrderService(UserOrderRepository userOrderRepository, UserOrderMapper userOrderMapper, AddressMapper addressMapper, UserRepository userRepository, CartRepository cartRepository, AddressRepository addressRepository) {
        this.userOrderRepository = userOrderRepository;
        this.userOrderMapper = userOrderMapper;
        this.addressMapper = addressMapper;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
    }
    public void createOrder(UserOrderDTO orderDTO) {
        Address address = addressMapper.toAddress(orderDTO.getDeliveryAddress());
        User user = userRepository.getReferenceById(orderDTO.getUserId());
        Cart cart = cartRepository.findCartByUserIdAndValid(user, true);
        UserOrder order = new UserOrder(orderDTO, address, user, cart);
        cart.setValid(false);
        cartRepository.save(cart);
        addressRepository.save(address);
        userOrderRepository.save(order);
    }
    public List<UserOrder> getOrders() { return userOrderRepository.findAll(); }
    public void updateOrder(int id, UserOrder newOrder) {
        UserOrder order = userOrderRepository.findById(id).get();
        order.setPaymentType(newOrder.getPaymentType());
    }
    public void deleteOrderById(int id) { userOrderRepository.deleteById(id); }
}
