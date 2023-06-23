package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @PostMapping("/createSubcategory/{id}")
    ResponseEntity<Object> create(@RequestBody SubcategoryDTO subcategoryDTO, @PathVariable Integer id) {
        subcategoryService.createSubcategory(subcategoryDTO.getName(), id);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/getAllSubcategories")
    ResponseEntity<List<SubcategoryDTO>> read() {
        return new ResponseEntity<>(subcategoryService.getSubcategories(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getSubcategory/{id}")
    ResponseEntity<SubcategoryDTO> getSubcategory(@PathVariable Integer id) {
        return new ResponseEntity<>(subcategoryService.getSubcategory(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateSubcategory/{id}")
    ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody SubcategoryDTO subcategoryDTO) {
        subcategoryService.updateSubcategory(id, subcategoryDTO);
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteSubcategory/{id}")
    void delete(@PathVariable Integer id) {
        subcategoryService.deleteSubcategoryById(id);
    }
}
