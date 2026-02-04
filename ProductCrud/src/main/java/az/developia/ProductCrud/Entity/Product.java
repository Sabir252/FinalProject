package az.developia.ProductCrud.Entity;

import java.util.jar.Attributes.Name;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String keyword;
	private String brand;
	private String category;
	private String description;
	private Double price;
	private String imgUrl;
	private Integer rating;
	private Integer quantity;
	@Column(name = "owner_id")
	private Long ownerId;

	
	

}
