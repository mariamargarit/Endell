package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AssignedValueDTO;
import dd.projects.ddshop.mappers.AssignedValueMapper;
import dd.projects.ddshop.repos.AssignedValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AssignedValueService {
    private final AssignedValueRepository assignedValueRepository;
    private final AssignedValueMapper assignedValueMapper;

    public AssignedValueService(AssignedValueRepository assignedValueRepository, AssignedValueMapper assignedValueMapper) {
        this.assignedValueRepository = assignedValueRepository;
        this.assignedValueMapper = assignedValueMapper;
    }
    public List<AssignedValueDTO> getAssignedValue() {
        return assignedValueRepository.findAll()
                .stream()
                .map(assignedValueMapper::toAssignedValueDTO)
                .collect(toList());
    }
}
