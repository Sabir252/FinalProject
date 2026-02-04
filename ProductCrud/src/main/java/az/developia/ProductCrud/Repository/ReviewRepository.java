package az.developia.ProductCrud.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.ProductCrud.Entity.Review;
import az.developia.ProductCrud.Repository.*;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<az.developia.ProductCrud.Entity.Review> findByProductId(Long carId);
	List<az.developia.ProductCrud.Entity.Review> findByUserId(Long userId);
}
