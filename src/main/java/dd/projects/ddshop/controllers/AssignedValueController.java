package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.AssignedValueDTO;
import dd.projects.ddshop.services.AssignedValueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssignedValueController {
    private final AssignedValueService assignedValueService;

    public AssignedValueController(AssignedValueService assignedValueService) {
        this.assignedValueService = assignedValueService;
    }
    @GetMapping("/getAllAssignedValues")
    public ResponseEntity<List<AssignedValueDTO>> get() {
        return new ResponseEntity<>(assignedValueService.getAssignedValue(), HttpStatus.ACCEPTED);
    }
}
