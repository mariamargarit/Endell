package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.mappers.ProductMapper;
import dd.projects.ddshop.mappers.ProductMapperImpl;
import dd.projects.ddshop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductMapperImpl productMapper;

    @Autowired
    public ProductService (ProductRepository productRepository, ProductMapperImpl productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public static Product getProductFromDTO(ProductDTO productDto, Subcategory subcategory) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSubcategoryId(subcategory);
        return product;
    }
    public Optional<Product> readProduct(Integer productId) {
        return productRepository.findById(productId);
    }

    public void createProduct (ProductDTO productDto) {
//        Product product = getProductFromDTO(productDto, subcategory);
//        productRepository.save(product);
        productRepository.save(productMapper.toProduct(productDto));
    }

    public List<ProductDTO> getProduct() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductDTO)
                .collect(toList());
    }

    public void updateProduct (int productId, ProductDTO newProductDTO) {
        Product product = productRepository.findById(productId).get();
        product.setName(newProductDTO.getName());
        product.setDescription(newProductDTO.getDescription());
        productRepository.save(product);
    }

    public void deleteProductById (int id) {
        productRepository.deleteById(id);
    }
}
