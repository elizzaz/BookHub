package com.example.demo.controllers;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

    @RestController
    @RequestMapping("/api/users")
    public class UserController {

        @Autowired
        private UserService userService;

        @GetMapping
        public List<UserDTO> getAllUsers() {
            return userService.getAllUsers();
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
            return userService.createUser(userDTO);
        }

        @GetMapping("/{id}")
        public UserDTO getUserById(@PathVariable Long id) {
            return userService.getUserById(id);
        }

        @PutMapping("/{id}")
        public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) {
            return userService.updateUser(id, updatedUserDTO);
        }

        @DeleteMapping("/{id}")
        public void deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
        }
    }
