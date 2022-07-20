package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.AssignedValueDTO;
import dd.projects.ddshop.entities.AssignedValue;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AssignedValueMapper {
    AssignedValueDTO toAssignedValueDTO(AssignedValue assignedValue);
    AssignedValue toAssignedValue(AssignedValueDTO assignedValueDTO);
}
