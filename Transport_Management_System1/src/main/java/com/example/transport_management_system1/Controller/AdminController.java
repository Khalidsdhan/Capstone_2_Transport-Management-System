package com.example.transport_management_system1.Controller;

import com.example.transport_management_system1.Api.ApiResponse;
import com.example.transport_management_system1.Model.Admin;
import com.example.transport_management_system1.Service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @GetMapping("/get")
    public ResponseEntity getAllAdmin(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllAdmin());
    }


    @PostMapping("/add")
    public ResponseEntity addAdmin(@Valid @RequestBody Admin admin, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        adminService.addAdmin(admin);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Admin is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateAdmin(@PathVariable Integer id,@Valid @RequestBody Admin admin ,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdate=adminService.updateAdmin(id, admin);
        if(isUpdate){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Admin is updated"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Admin is not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAdmin(@PathVariable Integer id){
        Boolean isDelete=adminService.deleteAdmin(id);
        if(isDelete){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Admin is deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Admin is not found"));
    }
}
