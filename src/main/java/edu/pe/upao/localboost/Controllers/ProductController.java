package edu.pe.upao.localboost.Controllers;

import edu.pe.upao.localboost.Dtos.ProductDTO;
import edu.pe.upao.localboost.Models.Product;
import edu.pe.upao.localboost.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId){
        return productService.getProductById(productId).orElse(new Product());
    }
    @GetMapping
    private List<ProductDTO> getAllChallenge(){
        return productService.getAllProduct();
    }

    @PostMapping("/registerPro")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        try{
            String newProduct = productService.addProduct(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (IllegalStateException sms){
            return new ResponseEntity<>(sms.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
