package az.developia.ProductCrud.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewDto {
	private Long id;
	
	private Long productId;
	
	private Long userId;
	
	@NotBlank(message = "Comment is required")
	private String comment;
	
	@NotBlank(message = "Rating is required")
	private Integer rating;
	
	
}
