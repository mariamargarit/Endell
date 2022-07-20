package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.CartDTO;
import dd.projects.ddshop.entities.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CartMapper {
    CartDTO toCartDTO(Cart cart);
    Cart toCart(CartDTO cartDTO);
}
