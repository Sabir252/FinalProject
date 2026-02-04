package az.developia.ProductCrud.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRegisterDto {
	@NotBlank(message = "Username is required")
	String username;
	@NotBlank(message = "Password is required")
	String password;
}
