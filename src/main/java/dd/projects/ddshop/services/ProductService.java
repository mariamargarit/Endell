package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.BrandDTO;
import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.entities.Brand;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.ProductAttribute;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.mappers.ProductMapper;
import dd.projects.ddshop.repos.BrandRepository;
import dd.projects.ddshop.repos.ProductRepository;
import dd.projects.ddshop.repos.SubcategoryRepository;
import dd.projects.ddshop.utils.ImageHostUtil;
import dd.projects.ddshop.validators.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductValidator productValidator;
    private final ProductMapper productMapper;
    private final SubcategoryRepository subcategoryRepository;
    private final ImageHostUtil imageHostUtil;
    private final BrandRepository brandRepository;
    @Autowired
    public ProductService (ProductRepository productRepository, ProductMapper productMapper, SubcategoryRepository subcategoryRepository, ImageHostUtil imageHostUtil, BrandRepository brandRepository){
        this.productRepository = productRepository;
        this.productValidator = new ProductValidator(productRepository, brandRepository);
        this.productMapper = productMapper;
        this.subcategoryRepository = subcategoryRepository;
        this.imageHostUtil = imageHostUtil;
        this.brandRepository = brandRepository;
    }

    public void createProduct (ProductDTO productDto, Integer id) {
        productValidator.validateProduct(productDto);
        Subcategory subcategory = subcategoryRepository.getReferenceById(id);
        Product product = new Product(productMapper.toProduct(productDto), subcategory);
        product.setVariants(new ArrayList<>());
        productRepository.save(product);
    }

    public List<ProductDTO> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductDTO)
                .collect(toList());
    }

    public ProductDTO getProduct(Integer id) {
        return productMapper.toProductDTO(productRepository.getReferenceById(id));
    }

    public List<ProductDTO> getProductsBySubcategory(Integer id) {
        Subcategory subcategory = subcategoryRepository.getReferenceById(id);
        return productRepository.findBySubcategoryId(subcategory)
                .stream()
                .map(productMapper::toProductDTO)
                .collect(toList());
    }

    public List<ProductDTO> getProductsByBrand(Integer id) {
        Brand brand = brandRepository.getReferenceById(id);
        return productRepository.findByBrand(brand)
                .stream()
                .map(productMapper::toProductDTO)
                .collect(toList());
    }

    public List<ProductDTO> getProductsByBrandName(String id) {
        Brand brand = brandRepository.findBrandByName(id);
        return productRepository.findByBrand(brand)
                .stream()
                .map(productMapper::toProductDTO)
                .collect(toList());
    }

    public void updateProduct (Integer id, ProductDTO newProductDTO) {
        final Product oldProduct = productMapper.toProduct(newProductDTO);
        final Product newProduct = productRepository.getReferenceById(id);
        newProduct.setBrand(oldProduct.getBrand());
        newProduct.setName(oldProduct.getName());
        String pictureUri = imageHostUtil.hostImage(String.valueOf(newProductDTO.hashCode()), newProductDTO.getImage());
        newProduct.setImage(pictureUri);

        productRepository.save(newProduct);
    }

    public void deleteProductById (Integer id) {
        productRepository.deleteById(id);
    }

    public void createBrand (BrandDTO brandDto) {
        productValidator.validateBrand(brandDto);
        Brand brand = new Brand(brandDto.getName());
        brand.setProducts(new ArrayList<>());
        brandRepository.save(brand);
    }

    public List<BrandDTO> getBrands() {
        return brandRepository.findAll()
                .stream()
                .map(productMapper::toBrandDTO)
                .collect(toList());
    }

    public void deleteBrandById (Integer id) {
        brandRepository.deleteById(id);
    }

    public void addBrandToProduct(Integer brandId, Integer productId) {
        Product product = productRepository.getReferenceById(productId);
        if(product.getBrand() == null ){
            product.setBrand(brandRepository.getReferenceById(brandId));
            productRepository.save(product);
        }
    }
}
