package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.CartEntryDTO;
import dd.projects.ddshop.entities.CartEntry;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CartEntryMapper {
    CartEntryDTO toCartEntryDTO(CartEntry cartEntry);
    CartEntry toCartEntry(CartEntryDTO cartEntryDTO);
}
