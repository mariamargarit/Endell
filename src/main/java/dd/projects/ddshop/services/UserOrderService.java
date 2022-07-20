package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.UserOrderDTO;
import dd.projects.ddshop.entities.UserOrder;
import dd.projects.ddshop.mappers.UserOrderMapperImpl;
import dd.projects.ddshop.repos.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderService {
    private final UserOrderRepository userOrderRepository;
    private final UserOrderMapperImpl userOrderMapper;

    @Autowired
    public UserOrderService(UserOrderRepository userOrderRepository, UserOrderMapperImpl userOrderMapper) {
        this.userOrderRepository = userOrderRepository;
        this.userOrderMapper = userOrderMapper;
    }
    public UserOrder createOrder(UserOrderDTO order) {
        return userOrderRepository.save(userOrderMapper.toUserOrder(order));
    }
    public List<UserOrder> getOrders() { return userOrderRepository.findAll(); }
    public UserOrderRepository getUserOrderRepository() { return userOrderRepository; }
    public void updateOrder(int id, UserOrder newOrder) {
        UserOrder order = userOrderRepository.findById(id).get();
        order.setPaymentType(newOrder.getPaymentType());
    }
    public void deleteOrderById(int id) { userOrderRepository.deleteById(id); }
}
