package az.developia.ProductCrud.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import az.developia.ProductCrud.Dto.ProductDto;
import az.developia.ProductCrud.Entity.Product;
import az.developia.ProductCrud.Entity.User;
import az.developia.ProductCrud.Repository.ProductRepository;
import az.developia.ProductCrud.Repository.UserRepository;
import jakarta.validation.Valid;

import java.util.List;

@Service
public class UserProductService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public Product saveCar(ProductDto productDto) {
        User user = getCurrentUser();
        Product product = new Product();

        product.setName(product.getName());
		product.setKeyword(product.getKeyword());
		product.setBrand(product.getBrand());
		product.setCategory(product.getCategory());
		product.setDescription(product.getDescription());
		product.setPrice(product.getPrice());
		product.setQuantity(product.getQuantity());
		product.setImgUrl(product.getImgUrl());
		product.setRating(product.getRating());
        product.setOwnerId(user.getId()); 
        
        return productRepository.save(product);
    }
    
    public List<Product> getMyProducts() {
        User user = getCurrentUser();
        return productRepository.findByOwnerId(user.getId());
    }

    public void deleteProduct(Long id) {
        User user = getCurrentUser();
        boolean isOwner = productRepository.existsByIdAndOwnerId(id, user.getId());
    
        if (!isOwner) {
            throw new RuntimeException("Вы не можете удалить чужую машину");
        }
        
        productRepository.deleteById(id);
    }
    
    public Product updateProduct(Long id, Product updatedProduct) {
        User user = getCurrentUser();
        boolean isOwner = productRepository.existsByIdAndOwnerId(id, user.getId());
     
        if (!isOwner) {
            throw new RuntimeException("Siz başqasının maşınını yeniləyə bilməzsiniz");
        }
        
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maşın tapılmadı!"));

        product.setName(product.getName());
		product.setKeyword(product.getKeyword());
		product.setBrand(product.getBrand());
		product.setCategory(product.getCategory());
		product.setDescription(product.getDescription());
		product.setPrice(product.getPrice());
		product.setQuantity(product.getQuantity());
		product.setImgUrl(product.getImgUrl());
		product.setRating(product.getRating());
        product.setOwnerId(user.getId()); 
        
        return productRepository.save(product);
    }

}