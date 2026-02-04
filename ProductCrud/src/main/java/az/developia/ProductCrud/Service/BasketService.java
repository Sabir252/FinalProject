package az.developia.ProductCrud.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import az.developia.ProductCrud.Dto.BasketProductResponseDto;
import az.developia.ProductCrud.Entity.Basket;
import az.developia.ProductCrud.Entity.Product;
import az.developia.ProductCrud.Entity.User;
import az.developia.ProductCrud.Repository.BasketRepository;
import az.developia.ProductCrud.Repository.ProductRepository;
import az.developia.ProductCrud.Repository.UserRepository;

@Service
public class BasketService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private BasketRepository basketRepository;

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void addToBasket(Long productId) {
        User user = getCurrentUser();
        // Məhsulun mövcudluğunu yoxla
        productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        // Səbətdə bu istifadəçiyə aid bu məhsul varmı?
        Basket basket = basketRepository.findByUserIdAndProductId(user.getId(), productId).orElse(null);
        
        if (basket == null) {
            Basket newBasket = new Basket();
            newBasket.setUserId(user.getId());
            newBasket.setProductId(productId); // Səhv burada idi: arqumentdən gələn ID-ni qoyuruq
            newBasket.setQuantity(1); // Yeni əlavə ediləndə miqdar 1 olur
            basketRepository.save(newBasket);
        } else {
            basket.setQuantity(basket.getQuantity() + 1);
            basketRepository.save(basket);
        }
    }

    public List<BasketProductResponseDto> myBaskets() {
        User user = getCurrentUser();
        List<Basket> baskets = basketRepository.findByUserId(user.getId());
        
        return baskets.stream().map(basket -> {
            Product product = productRepository.findById(basket.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not Found"));
            
            BasketProductResponseDto dto = new BasketProductResponseDto();
            dto.setBasketId(basket.getId()); // Basket obyektindən ID-ni götürürük
            dto.setBrand(product.getBrand());
            dto.setCategory(product.getCategory());
            dto.setPrice(product.getPrice());
            dto.setImgUrl(product.getImgUrl());
            dto.setQuantity(basket.getQuantity()); // Product-dakı yox, Səbətdəki miqdarı göstəririk
            return dto;
        }).toList();
    }

    public void removeFromBasket(Long productId) {
        User user = getCurrentUser();
        Basket basket = basketRepository.findByUserIdAndProductId(user.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Product not found in basket"));
        
        // deleteById metodu adətən ID qəbul edir, obyekti silmək üçün delete istifadə edilir
        basketRepository.delete(basket);
    }
}