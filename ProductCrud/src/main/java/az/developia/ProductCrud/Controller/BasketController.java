package az.developia.ProductCrud.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.ProductCrud.Dto.BasketProductResponseDto;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/basket")
@Validated
public class BasketController {
	   @Autowired
	    private az.developia.ProductCrud.Service.BasketService basketService;

	    @PostMapping("/{productsId}")
	    public void addToBasket(@PathVariable @Min(1) Long productsId) {
	        basketService.addToBasket(productsId);
	    }

	    @GetMapping
	    public List<BasketProductResponseDto> myBaskets() {
	        return basketService.myBaskets();
	    }

	    @DeleteMapping("/{productsId}")
	    public void removeFromBasket(@PathVariable @Min(1) Long productsId) {
	        basketService.removeFromBasket(productsId);
	    }
}