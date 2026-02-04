package az.developia.ProductCrud.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserMeDto {
	private Long id;
	@NotBlank(message = "Username is required")
	private String username;
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Surname is required")
	private String surname;
	@NotBlank(message = "Email is required")
	private String email;
}
