package edu.pe.upao.localboost.Service;

import edu.pe.upao.localboost.Dtos.ProductDTO;
import edu.pe.upao.localboost.Models.Product;
import edu.pe.upao.localboost.Models.User;
import edu.pe.upao.localboost.Repositories.ProductRepository;
import edu.pe.upao.localboost.Services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testGetAllProduct() {
        // Configurar el comportamiento del repositorio mock
        List<Product> mockProducts = Arrays.asList(
                new Product(1L, "Product1", "Description1", 1, "Category1", new User()),
                new Product(2L, "Product2", "Description2", 1, "Category2", new User())
        );
        when(productRepository.findAll()).thenReturn(mockProducts);

        // Llamar al método y verificar el resultado
        List<ProductDTO> result = productService.getAllProduct();

        // Verificar que el método del repositorio fue llamado
        verify(productRepository, times(1)).findAll();

        // Verificar que la conversión se hizo correctamente
        assertEquals(2, result.size());
        // Puedes agregar más aserciones según tu lógica de conversión
    }
    @Test
    public void testGetProductById() {
        // Arrange (GIVEN)
        Long productId = 1L;
        Product mockProduct = new Product(productId, "Product1", "Description1", 10, "Category1", new User());
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        // Act (WHEN)
        Optional<Product> result = productService.getProductById(productId);

        // Assert (THEN)
        verify(productRepository, times(1)).findById(productId);
        assertTrue(result.isPresent());
        assertEquals(mockProduct, result.get());
    }

    @Test
    public void testAddProduct() {
        // Arrange (GIVEN)
        Product product = new Product(1L, "Product1", "Description1", 10, "Category1", new User());

        // Configurar el comportamiento del repositorio mock
        when(productRepository.save(product)).thenReturn(product);

        // Act (WHEN)
        String result = productService.addProduct(product);

        // Assert (THEN)
        verify(productRepository, times(1)).save(product);
        assertEquals("Producto registrado correctamente", result);
    }

}
