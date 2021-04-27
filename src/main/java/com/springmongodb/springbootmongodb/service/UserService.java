package com.springmongodb.springbootmongodb.service;
import com.springmongodb.springbootmongodb.domain.User;
import com.springmongodb.springbootmongodb.repository.UserRepository;
import com.springmongodb.springbootmongodb.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(String id) {
            Optional<User> user = this.userRepository.findById(id);
            return user.orElseThrow(() -> new ObjectNotFoundException("User not Found"));
    }
}
