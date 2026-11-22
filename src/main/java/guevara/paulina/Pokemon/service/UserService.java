package guevara.paulina.Pokemon.service;

import guevara.paulina.Pokemon.dto.UserRequest;
import guevara.paulina.Pokemon.exception.ResourceNotFoundException;
import guevara.paulina.Pokemon.model.User;
import guevara.paulina.Pokemon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya fue utilizado");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));
    }
}
