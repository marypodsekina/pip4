package app.service;

import app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import app.repositories.UserRepo;

import java.util.List;


@Service
public class UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User newUser){
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepo.save(newUser);
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public boolean deleteUserByUsername(String username) {
        if (userRepo.findByUsername(username) != null) {
            userRepo.deleteByUsername(username);
            return true;
        }
        return false;
    }

    public User getUser(String name){
        if(userRepo.existsById(name)){
            return userRepo.getOne(name);
        }
        else return null;
    }


}
