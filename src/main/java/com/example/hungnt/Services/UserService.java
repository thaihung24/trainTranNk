package com.example.hungnt.Services;

import com.example.hungnt.models.UsersEntity;
import com.example.hungnt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<UsersEntity> getAllUsers(){
        return (List<UsersEntity>)userRepository.findAll();
    }

    public Optional<UsersEntity> getUserById(long id) {
        return userRepository.findById(id);
    }
    public void addUser(UsersEntity user){
        userRepository.save(user);
    }
    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }
    public void updateUser(UsersEntity user){

        userRepository.save(user);
    }
    public List<UsersEntity> listUser(String key){
        if(key!=null){
            return userRepository.search(key);
        }
        return (List<UsersEntity>)userRepository.findAll();
    }
}
