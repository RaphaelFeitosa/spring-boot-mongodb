package com.springmongodb.springbootmongodb.service;
import com.springmongodb.springbootmongodb.domain.User;
import com.springmongodb.springbootmongodb.dto.UserDTO;
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

    public User insert(User user) {
        return this.userRepository.insert(user);
    }

    public void delete(String id) {
        findById(id);
        this.userRepository.deleteById(id);
    }

    public User update(User user){
        User newUser = findById(user.getId());
        updateData(newUser, user);

        return this.userRepository.save(newUser);
    }

    private void updateData(User newUser, User user) {

        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }

    public User fromDTO(UserDTO userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
    }
}
