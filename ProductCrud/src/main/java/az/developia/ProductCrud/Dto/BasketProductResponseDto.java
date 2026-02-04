package az.developia.ProductCrud.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BasketProductResponseDto {
	private Long basketId;

	@NotBlank(message = "Product brand is required")
	private String brand;
	@NotBlank(message = "Product category is required")
	private String category;
	@NotNull(message = "Price is required")
	@Min(value = 0, message = "Price must be greater than or equal to 0")
	private Double price;
	@NotBlank(message = "Product img is required")
	private String imgUrl;

	@NotNull(message = "Quantity is required")
	@Min(value = 0, message = "Quantity must be greater than or equal to 0")
	private Integer quantity;
}
