package az.developia.ProductCrud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import az.developia.ProductCrud.Dto.ProductDto;
import az.developia.ProductCrud.Entity.Product;
import az.developia.ProductCrud.Service.ProductService;
import az.developia.ProductCrud.Service.UserProductService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private UserProductService userProductService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Optional<Product> car = productService.getById(id);
        return car.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{q}")
    public List<Product> search(@PathVariable String q) {
        return productService.search(q);
    }

    @GetMapping("/rating/{rating}")
    public List<Product> getByRating(@PathVariable Integer rating) {
        return productService.getByRating(rating);
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return productService.getByCategory(category);
    }

    @GetMapping("/price/asc")
    public List<Product> priceAsc() {
        return productService.getPriceAsc();
    }

    @GetMapping("/price/desc")
    public List<Product> priceDesc() {
        return productService.getPriceDesc();
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto) {
        try {
            Product createdProduct = productService.saveProduct(productDto);
            return ResponseEntity.ok(createdProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Server error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            userProductService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Server error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatedProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        try {
            Product updatedProduct = userProductService.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Server error: " + e.getMessage());
        }
    }

    @GetMapping("/my-products")
    public ResponseEntity<?> myProducts() {
        try {
            List<Product> list = userProductService.getMyProducts();
            return ResponseEntity.ok(list); // BURA DİQQƏT: myProducts() yazma, sadece 'list' qaytar!
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}