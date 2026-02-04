package az.developia.ProductCrud.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import az.developia.ProductCrud.Dto.ReviewDto;
import az.developia.ProductCrud.Entity.Review;
import az.developia.ProductCrud.Entity.User;
import az.developia.ProductCrud.Repository.ReviewRepository;
import az.developia.ProductCrud.Repository.UserRepository;


@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Review create(ReviewDto reviewDto) {
		User user = getCurrentUser();
		Review review = new Review();
		review.setProductId(reviewDto.getProductId());
		review.setUserId(user.getId());
		review.setComment(reviewDto.getComment());
		review.setRating(reviewDto.getRating());
		return reviewRepository.save(review);
	}
	
	public List<Review> getByProduct(Long productId) {
		return reviewRepository.findByProductId(productId);
	}
	
	public void delete(Long id) {
		reviewRepository.deleteById(id);
	}
	 
    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
