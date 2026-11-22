package guevara.paulina.Pokemon.controller;

import guevara.paulina.Pokemon.dto.LoginRequest;
import guevara.paulina.Pokemon.dto.LoginResponse;
import guevara.paulina.Pokemon.model.User;
import guevara.paulina.Pokemon.repository.UserRepository;
import guevara.paulina.Pokemon.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail());

        if(user == null){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "credenciales inválidas"));
        }

        if(!user.getPassword().equals(request.getPassword())){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "crenciales inválidas"));
        }

        String token = jwtService.generateToken(user.getEmail(), user.getPassword());

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
