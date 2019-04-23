package com.example.shop.web;

import com.example.shop.domain.User;
import com.example.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/rest/users")
public class UserResource {

    @Autowired
    UserRepository repo;

    @GetMapping("/all")
    public List<User> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable final int id) {
        Optional<User> user = repo.findById(id);

        if (user.isPresent())
            return user;
        else throw new NoSuchElementException("User doesn't exist");
    }

    @GetMapping("name/{name}")
    public Optional<List<User>> getUserByName(@PathVariable("name") final String name) {
        Optional<List<User>> users = repo.findByName(name);

        if (users.isPresent())
            return users;
        else throw new NoSuchElementException("Users with that name do not exist in DB");
    }

    @DeleteMapping("delete/{id}")
    public void deleteUserById(@PathVariable("id") final int id) {
        repo.deleteById(id);
    }

    @GetMapping("update/{id}/{name}")
    public User updateUser(@PathVariable("id") final int id, @PathVariable("name") final String name) {
        User user = repo.getOne(id);
        user.setName(name);

        return repo.save(user);
    }
}
