package com.example.transport_management_system1.Service;

import com.example.transport_management_system1.Model.User;
import com.example.transport_management_system1.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }


    public void addUser(User user){
        userRepository.save(user);
    }


    public Boolean updateUser(Integer id,User user){
        User oldUser=userRepository.findUserById(id);

        if(oldUser==null){
            return false;
        }

        oldUser.setFullName(oldUser.getFullName());
        oldUser.setUserName(oldUser.getUserName());
        oldUser.setPassword(oldUser.getPassword());
        oldUser.setEmail(oldUser.getEmail());
        oldUser.setPhoneNumber(oldUser.getPhoneNumber());

        userRepository.save(oldUser);
        return true;
    }

    public Boolean deleteUser(Integer id){
        User user=userRepository.findUserById(id);

        if(user==null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }


    public User getById(Integer id){
        return userRepository.findUserById(id);
    }




}
