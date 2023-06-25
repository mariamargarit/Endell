package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.CartDTO;
import dd.projects.ddshop.entities.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CartMapper {
    CartDTO toCartDTO(Cart cart);
    Cart toCart(CartDTO cartDTO);
    List<CartDTO> toCartDTOList(List<Cart> cart);
}
