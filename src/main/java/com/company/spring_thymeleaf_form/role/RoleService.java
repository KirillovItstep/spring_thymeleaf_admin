package com.company.spring_thymeleaf_form.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class RoleService {
        @Autowired
        private RoleRepository roleRepository;

        public List<Role> findAll() {
            return roleRepository.findAll();
        }

        public Role save(Role firm) {
            return roleRepository.save(firm);
        }

        public Role findById(Long id) {
            return roleRepository.findById(id).get();
        }

        public void deleteById(Long id) {
            roleRepository.deleteById(id);
        }
    }
