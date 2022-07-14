package dd.projects.ddshop.services;

import dd.projects.ddshop.dto.ProductDTO;
import dd.projects.ddshop.entities.Category;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public static Product getProductFromDto(ProductDTO productDto, Subcategory subcategory) {
        Product product = new Product();
        product.setSubcategory(subcategory);
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        return product;
    }
    public void createProduct(ProductDTO productDTO, Subcategory subcategory) {
        Product product = getProductFromDto(productDTO, subcategory);
        productRepository.save(product);
    }
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public Product updateProduct(Product product){ return productRepository.save(product);}
    public void deleteProductById(int id){ productRepository.deleteById(id);}
}
