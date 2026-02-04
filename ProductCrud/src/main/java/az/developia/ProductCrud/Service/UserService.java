package az.developia.ProductCrud.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import az.developia.ProductCrud.Dto.LoginRegisterDto;
import az.developia.ProductCrud.Dto.UserMeDto;
import az.developia.ProductCrud.Entity.Authority;
import az.developia.ProductCrud.Entity.User;
import az.developia.ProductCrud.Jwt.JwtUtil;
import az.developia.ProductCrud.Repository.AuthorityRepository;
import az.developia.ProductCrud.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthorityRepository authorityRepository;

    public void registerUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username boş ola bilməz");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Şifrə boş ola bilməz");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email boş ola bilməz");
        }

        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        user1.setSurname(user.getSurname());

     
        userRepository.save(user1);

        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        authority.setUsername(user.getUsername());
        authorityRepository.save(authority);
    }

    public String login(LoginRegisterDto loginRegisterDto) {
        User userEntity = userRepository.findByUsername(loginRegisterDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (!passwordEncoder.matches(loginRegisterDto.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Неверный пароль");
        }

        return jwtUtil.generateToken(loginRegisterDto.getUsername());
    }
    
    public UserMeDto me() {
    	User user = getCurrentUser();
    	UserMeDto dto = new UserMeDto();
    	dto.setId(user.getId());
    	dto.setUsername(user.getUsername());
    	dto.setName(user.getName());
    	dto.setSurname(user.getSurname());
    	dto.setEmail(user.getEmail());
    	return dto;
    }
    
    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    
}
