package com.example.training.services;

import com.example.training.models.Role;
import com.example.training.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepo;

    public Optional<Role> getByName(String name) {
        return roleRepo.findByName(name);
    }
}
