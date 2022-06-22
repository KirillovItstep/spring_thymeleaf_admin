package com.company.spring_thymeleaf_form.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
    public class UserService {
        @Autowired
        private UserRepository osRepository;

        public List<User> findAll() {
            return osRepository.findAll();
        }

        public User save(User os) {
           return osRepository.save(os);
        }

        public User findById(Long id) {
            return osRepository.findById(id).get();
        }

        public void deleteById(Long id) {
            osRepository.deleteById(id);
        }
    }
