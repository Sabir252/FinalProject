package az.developia.ProductCrud.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.ProductCrud.Entity.*;

import az.developia.ProductCrud.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	java.util.List<Product> findByBrandContainingIgnoreCase(String brand);

	java.util.List<Product> findByRatingGreaterThanEqual(Integer rating);

	java.util.List<Product> findByCategory(String category);

	java.util.List<Product> findAllByOrderByPriceAsc();

	java.util.List<Product> findAllByOrderByPriceDesc();

	java.util.List<Product> findByOwnerId(Long ownerId);

	boolean existsByIdAndOwnerId(Long id, Long ownerId);
}
