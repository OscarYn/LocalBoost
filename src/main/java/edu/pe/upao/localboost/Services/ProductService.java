package edu.pe.upao.localboost.Services;

import edu.pe.upao.localboost.Dtos.ProductDTO;
import edu.pe.upao.localboost.Models.Product;
import edu.pe.upao.localboost.Repositories.ProductRepository;
import edu.pe.upao.localboost.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productx = new ArrayList<>();
        for (Product challenge : products) {
            productx.add(new ProductDTO(challenge));
        }
        return productx;
    }
    private boolean isEmptyOrWhitespace(String value) {
        return value == null || value.trim().isEmpty();
    }
    public Optional<Product> getProductById(Long productId){
        return productRepository.findById(productId);
    }

    public String addProduct(Product product){
        if (isEmptyOrWhitespace(product.getCategory()) || isEmptyOrWhitespace(product.getName()) || isEmptyOrWhitespace(product.getDescription()) || isEmptyOrWhitespace(String.valueOf(product.getPrice())) || product.getUser() == null) {
            throw new IllegalStateException("Todos los campos son requeridos");
        }
        productRepository.save(product);
        return "Producto registrado correctamente";
    }

    public List<ProductDTO> searchProduct(Product product) {
        String category = product.getCategory();

        if (category == null || category.isEmpty()) {
            throw new IllegalStateException("Por favor ingresa la categoria");
        }

        List<Product> products = productRepository.findByCategory(category);

        if (products.isEmpty()) {
            throw new IllegalStateException("No existe esa categoria");
        }

        List<ProductDTO> productDTOs = products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
        return productDTOs;
    }
}
