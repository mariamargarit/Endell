package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.UserOrderDTO;
import dd.projects.ddshop.entities.UserOrder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserOrderMapper {
    UserOrderDTO toUserOrderDTO(UserOrder userOrder);
    UserOrder toUserOrder(UserOrderDTO userOrderDTO);
}
