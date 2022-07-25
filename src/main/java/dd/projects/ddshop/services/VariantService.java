package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AssignedValueDTO;
import dd.projects.ddshop.dtos.VariantDTO;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.Variant;
import dd.projects.ddshop.mappers.ProductMapperImpl;
import dd.projects.ddshop.mappers.VariantMapperImpl;
import dd.projects.ddshop.repos.AssignedValueRepository;
import dd.projects.ddshop.repos.ProductRepository;
import dd.projects.ddshop.repos.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class VariantService {

    private final VariantRepository variantRepository;
    private final VariantMapperImpl variantMapper;
    private final ProductRepository productRepository;
    private final ProductMapperImpl productMapper;
    private final AssignedValueRepository assignedValueRepository;
    @Autowired
    public VariantService (VariantRepository variantRepository, VariantMapperImpl variantMapper, ProductRepository productRepository, ProductMapperImpl productMapper, AssignedValueRepository assignedValueRepository){
        this.variantRepository = variantRepository;
        this.variantMapper = variantMapper;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.assignedValueRepository = assignedValueRepository;
    }

    public void createVariant (VariantDTO variantDTO, Integer id) {
        final Product product = productRepository.getReferenceById(id);
        Variant variant = variantMapper.toVariant(variantDTO);
        variant.setProductId(product);
        variantRepository.save(variant);
    }

    public List<VariantDTO> getVariant() {
        return variantRepository.findAll()
                .stream()
                .map(variantMapper::toVariantDTO)
                .collect(toList());
    }

    public void updateVariant (int variantId, VariantDTO newVariant) {
        Variant variant = variantRepository.findById(variantId).get();
        variant.setPrice(newVariant.getPrice());
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

