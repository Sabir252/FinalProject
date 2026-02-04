package az.developia.ProductCrud.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    
    @NotBlank(message = "Car name is required")
    private String name;
    
    private String brand;
    private String category;
    private String size;
    
    @NotBlank(message = "Keyword is required")
    private String keyword;
    
    private String color;
    private String material;
    
    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;
    
    private String imgUrl;
    private Integer rating;
	private String description;
	private Long ownerId;
}