package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.dtos.UserOrderDTO;
import dd.projects.ddshop.entities.Cart;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.entities.User;
import dd.projects.ddshop.entities.UserOrder;
import org.mapstruct.*;

import javax.persistence.criteria.CriteriaBuilder;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserOrderMapper {
    @Mappings({
            @Mapping(target = "userId", qualifiedByName = "mappingUserDTO"),
            @Mapping(target = "cartId", qualifiedByName = "mappingCartDTO")
    })
    UserOrderDTO toUserOrderDTO(UserOrder userOrder);
    @Named(value = "mappingUserDTO")
    default Integer toUserDTO(User user){
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }
    @Named(value = "mappingCartDTO")
    default Integer toCartDTO(Cart cart){
        if ( cart == null ) {
            return null;
        }
        return cart.getId();
    }

}
