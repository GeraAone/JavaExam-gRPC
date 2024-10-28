package com.geralab.JavaExam.service;


import com.geralab.JavaExam.entity.Role;
import com.geralab.JavaExam.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    @Transactional
    public List<String> getAllRoles() {
        List<String> res;
        try {
            res = roleRepository.findAll().stream().map(Role::getRoleName).toList();
        } catch(NoSuchElementException ex) {
            throw new NoSuchElementException("Roles cannot be found");
        }
        return res;
    }

    public Optional<Role> getRoleByRoleName() {
        return roleRepository.findRoleByRoleName("ROLE_USER");
    }
}
