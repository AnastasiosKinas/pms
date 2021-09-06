package com.tasoskinas.Post.Management.System.service;

import com.tasoskinas.Post.Management.System.entity.User;
import com.tasoskinas.Post.Management.System.enums.Role;
import com.tasoskinas.Post.Management.System.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User fetchedUser = userRepository.findByUsername(username);
        //Logic to get the User from the Database
        return new org.springframework.security.core.userdetails.User(
            fetchedUser.getUsername(),
            fetchedUser.getPassword(),
            List.of((GrantedAuthority) () -> fetchedUser.getRole().toString()));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {
        Optional<User> result = userRepository.findById(theId);

        User theUser;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            // we didn't find the  category
            throw new RuntimeException("Did not find user id - " + theId);
        }

        return theUser;
    }

    @Override
    public void save(User theUser) {
        theUser.setPassword(new BCryptPasswordEncoder().encode(theUser.getPassword()));
        userRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
        if (userRepository.findById(theId).get().getRole() != Role.ADMIN) {
            userRepository.deleteById(theId);
        }
    }
}
