package com.example.training.services;

import com.example.training.DTO.RegisterRequest;
import com.example.training.models.Role;
import com.example.training.models.User;
import com.example.training.repositories.RoleRepository;
import com.example.training.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;     // ✅ thêm final
    private final RoleRepository roleRepository;       // ✅ thêm final

    public User register(User user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username đã tồn tại");
        }
        return userRepo.save(user);
    }

    public Optional<User> getById(Long id) {
        return userRepo.findById(id);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User update(Long id, User updated) {
        User user = userRepo.findById(id).orElseThrow();
        user.setUsername(updated.getUsername());
        user.setPassword(updated.getPassword());
        user.setRole(updated.getRole());
        return userRepo.save(user);
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    public boolean authenticate(String username, String password) {
        return userRepo.findByUsername(username)
                .map(u -> u.getPassword().equals(password))
                .orElse(false);
    }

    public User register(RegisterRequest request) {
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // ✅ Gán mặc định role có id = 2
        Role defaultRole = roleRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Role ID 2 not found"));
        user.setRole(defaultRole);

        return userRepo.save(user);
    }

}
