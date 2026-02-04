package az.developia.ProductCrud.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.ProductCrud.Dto.LoginRegisterDto;
import az.developia.ProductCrud.Dto.UserMeDto;
import az.developia.ProductCrud.Entity.User;
import az.developia.ProductCrud.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PostMapping("/register")
    public void register(@Valid @RequestBody User user) {
        userService.registerUser(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRegisterDto loginRegisterDto) {
        String token = userService.login(loginRegisterDto);
        return ResponseEntity.ok(Map.of("token", token));
    }
    
    @GetMapping("/me") 
    public UserMeDto me() {
    	return userService.me();
    }
}