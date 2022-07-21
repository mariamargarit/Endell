package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.VariantDTO;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.Variant;
import dd.projects.ddshop.mappers.VariantMapperImpl;
import dd.projects.ddshop.repos.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class VariantService {

    private final VariantRepository variantRepository;
    private final VariantMapperImpl variantMapper;

    @Autowired
    public VariantService (VariantRepository variantRepository, VariantMapperImpl variantMapper){
        this.variantRepository = variantRepository;
        this.variantMapper = variantMapper;
    }

    public static Variant getVariantFromDTO(VariantDTO variantDTO, Product product){
        Variant variant = new Variant();
        variant.setPrice(variantDTO.getPrice());
        variant.setAvailableQuantity(variantDTO.getAvailableQuantity());
        variant.setAddedDate(variantDTO.getAddedDate());
        variant.setProductId(product);
        return variant;
    }

    public void createVariant (VariantDTO variantDTO) {
//        Variant variant = getVariantFromDTO(variantDTO, product);
//        variantRepository.save(variant);
        variantRepository.save(variantMapper.toVariant(variantDTO));
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

