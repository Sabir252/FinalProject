package az.developia.ProductCrud.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.ProductCrud.Dto.ProductDto;
import az.developia.ProductCrud.Entity.Product;
import az.developia.ProductCrud.Entity.User;
import az.developia.ProductCrud.Repository.ProductRepository;
import az.developia.ProductCrud.Repository.UserRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;

	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public Optional<Product> getById(Long id) {
		return productRepository.findById(id);
	}

	public Product saveProduct(ProductDto dto) {
	    // 1. Sistemə daxil olan istifadəçinin adını (username) alırıq
	    String currentUsername = org.springframework.security.core.context.SecurityContextHolder
	            .getContext().getAuthentication().getName();

	    // 2. Həmin username-ə görə istifadəçini bazadan tapırıq (UserRepository lazımdır)
	    // Əgər UserRepository yoxdursa, mütləq @Autowired ilə əlavə et
	    User user = userRepository.findByUsername(currentUsername)
	            .orElseThrow(() -> new RuntimeException("İstifadəçi tapılmadı!"));

	    Product product = new Product();
	    // DTO-dan gələn datalar
	    product.setName(dto.getName());
	    product.setBrand(dto.getBrand());
	    product.setKeyword(dto.getKeyword());
	    product.setPrice(dto.getPrice());
	    product.setImgUrl(dto.getImgUrl());
	    product.setCategory(dto.getCategory());
	    product.setRating(dto.getRating());
	    product.setDescription(dto.getDescription());
	    product.setQuantity(dto.getQuantity());

	    // BAŞ QƏHRƏMAN: OwnerId-ni burada mənimsədirik
	    product.setOwnerId(user.getId()); 

	    return productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public List<Product> getByRating(Integer rating) {
		return productRepository.findByRatingGreaterThanEqual(rating);
	}

	public List<Product> getByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	public List<Product> search(String keyword) {
		return productRepository.findByBrandContainingIgnoreCase(keyword);
	}

	public List<Product> getPriceAsc() {
		return productRepository.findAllByOrderByPriceAsc();
	}

	public List<Product> getPriceDesc() {
		return productRepository.findAllByOrderByPriceDesc();
	}

	public Product updatedProduct(Long id, Product updatedProduct) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found!"));

		if (!product.getOwnerId().equals(updatedProduct.getOwnerId())) {
			throw new RuntimeException("You cannot update another user's product");
		}

		product.setName(updatedProduct.getName());
		product.setKeyword(updatedProduct.getKeyword());
		product.setBrand(updatedProduct.getBrand());
		product.setCategory(updatedProduct.getCategory());
		product.setDescription(updatedProduct.getDescription());
		product.setPrice(updatedProduct.getPrice());
		product.setQuantity(updatedProduct.getQuantity());
		product.setImgUrl(updatedProduct.getImgUrl());
		product.setRating(updatedProduct.getRating());

		return productRepository.save(product);
	}
}
