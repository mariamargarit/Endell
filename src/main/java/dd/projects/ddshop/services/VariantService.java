package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.VariantCreationDTO;
import dd.projects.ddshop.dtos.VariantDTO;
import dd.projects.ddshop.entities.*;
import dd.projects.ddshop.mappers.ProductMapper;
import dd.projects.ddshop.mappers.VariantMapper;
import dd.projects.ddshop.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class VariantService {

    private final VariantRepository variantRepository;
    private final VariantMapper variantMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final AssignedValueRepository assignedValueRepository;
    private final VariantCombinationRepository variantCombinationRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final BrandRepository brandRepository;
    @Autowired
    public VariantService (VariantRepository variantRepository, VariantMapper variantMapper, ProductRepository productRepository, ProductMapper productMapper, AssignedValueRepository assignedValueRepository, VariantCombinationRepository variantCombinationRepository, SubcategoryRepository subcategoryRepository, BrandRepository brandRepository){
        this.variantRepository = variantRepository;
        this.variantMapper = variantMapper;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.assignedValueRepository = assignedValueRepository;
        this.variantCombinationRepository = variantCombinationRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.brandRepository = brandRepository;
    }

    public void createVariant (VariantCreationDTO variantDTO, Integer id) {
        final Product product = productRepository.getReferenceById(id);
        Variant variant = variantMapper.toModel(variantDTO);
        variant.setProductId(product);
        variantRepository.save(variant);
    }

    public List<VariantDTO> getVariants() {
        return variantRepository.findAll()
                .stream()
                .map(variantMapper::toVariantDTO)
                .collect(toList());
    }

    public VariantDTO getVariant(Integer id) {
        return variantMapper.toVariantDTO(variantRepository.getReferenceById(id));
    }

    public List<VariantDTO> getVariantsBySubcategory(Integer id) {
        return variantRepository.findByProductSubcategory(id)
                .stream()
                .map(variantMapper::toVariantDTO)
                .collect(toList());
    }

    public List<VariantDTO> getVariantsByBrand(Integer id) {
        return variantRepository.findByProductBrand(id)
                .stream()
                .map(variantMapper::toVariantDTO)
                .collect(toList());
    }

    public List<VariantDTO> getVariantsByBrandName(String id) {
        Brand brand = brandRepository.findBrandByName(id);
        return variantRepository.findByProductBrand(brand.getId())
                .stream()
                .map(variantMapper::toVariantDTO)
                .collect(toList());
    }


    public void addAssignedValuesToVariant(Integer assignedValueId, Integer id) {
        AssignedValue assignedValue = assignedValueRepository.getReferenceById(assignedValueId);
        Variant variant = variantRepository.getReferenceById(id);
        if(!variant.getAssignedValueDTOList().contains(assignedValue)){
            variant.getAssignedValueDTOList().add(assignedValue);
            variantRepository.save(variant);
        }
    }

    public void updateVariant (int variantId, VariantDTO newVariant) {
        Variant variant = variantRepository.findById(variantId).get();
        variant.setAvailableQuantity(newVariant.getAvailableQuantity());
        variantRepository.save(variant);
    }

    public Variant readVariant(Integer id) {
        return variantRepository.getReferenceById(id);
    }

    public void deleteVariantById (int id) {
        variantRepository.deleteById(id);
    }
}

