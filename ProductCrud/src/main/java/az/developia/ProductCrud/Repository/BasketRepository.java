package az.developia.ProductCrud.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.ProductCrud.Entity.Basket;
import az.developia.ProductCrud.Entity.User;

public interface BasketRepository extends JpaRepository<Basket, Long> {
	Optional<Basket> findByUserIdAndProductId(Long userId, Long productId);

	List<Basket> deleteById(Basket basket);

	List<Basket> findByUserId(Long id);

	List<Basket> save(User basket);

}
