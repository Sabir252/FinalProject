package az.developia.ProductCrud.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.ProductCrud.Dto.ReviewDto;
import az.developia.ProductCrud.Entity.Review;
import az.developia.ProductCrud.Service.ReviewService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("product")
	public Review create(@Valid @RequestBody ReviewDto reviewDto) {
		return reviewService.create(reviewDto);
	}
	
	@GetMapping("/product/{productId}")
	public List<Review> getByProduct(@PathVariable Long productId){
		return reviewService.getByProduct(productId);
	}
	
	@DeleteMapping("{id}")
	public void deleteCommit(@PathVariable Long id) {
		reviewService.delete(id);
	}
	
	
}
