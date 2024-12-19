package com.example.demo.services;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDTO updateUser(Long id, UserDTO updatedUserDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Mappage entre UserDTO et User
        User userToUpdate = UserMapper.INSTANCE.userDTOToUser(updatedUserDTO);

        user.setEmail(userToUpdate.getEmail());
        user.setUsername(userToUpdate.getUsername());
        user.setPassword(userToUpdate.getPassword());

        return UserMapper.INSTANCE.userToUserDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
