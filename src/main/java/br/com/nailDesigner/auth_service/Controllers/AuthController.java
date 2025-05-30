package br.com.nailDesigner.auth_service.Controllers;

import br.com.nailDesigner.auth_service.Models.Role;
import br.com.nailDesigner.auth_service.Models.User;
import br.com.nailDesigner.auth_service.Repositories.UserRepository;
import br.com.nailDesigner.auth_service.Services.JwtService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    // DTOs
    public record LoginRequest(@NotBlank String username, @NotBlank String password) {}
    public record LoginResponse(String token) {}
    public record RegisterRequest(
        @NotBlank @Size(min = 3) String username,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6) String password
    ) {}

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        UserDetails userDetails = userRepository.findByUsername(request.username()).orElseThrow();
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username já existe.");
        }
        User newUser = new User();
        newUser.setUsername(request.username());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(Role.USER); // Papel padrão para novos registros
        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
    }
}
