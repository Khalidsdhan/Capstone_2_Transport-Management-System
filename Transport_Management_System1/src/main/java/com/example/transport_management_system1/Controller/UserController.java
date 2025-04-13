package com.example.transport_management_system1.Controller;

import com.example.transport_management_system1.Api.ApiResponse;
import com.example.transport_management_system1.Model.User;
import com.example.transport_management_system1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }


    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@Valid @RequestBody User user ,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdate=userService.updateUser(id,user);
        if(isUpdate){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user is updated"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("user is not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        Boolean isDelete=userService.deleteUser(id);
        if(isDelete){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user is deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("user is not found"));
    }

}
