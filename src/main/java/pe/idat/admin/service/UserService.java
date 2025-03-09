package pe.idat.admin.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.idat.admin.model.User;
import pe.idat.admin.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public void saveUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        repository.save(new User(username, encodedPassword, "USER"));
    }
}
