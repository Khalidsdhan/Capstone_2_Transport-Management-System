package com.example.transport_management_system1.Controller;

import com.example.transport_management_system1.Api.ApiResponse;
import com.example.transport_management_system1.Model.Driver;
import com.example.transport_management_system1.Service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/get")
    public ResponseEntity getAllDriver(){
        return ResponseEntity.status(HttpStatus.OK).body(driverService.getAllDriver());
    }


    @PostMapping("/add")
    public ResponseEntity addDriver(@Valid @RequestBody Driver driver, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        driverService.addUser(driver);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Driver is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateDriver(@PathVariable Integer id,@Valid @RequestBody Driver driver ,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdate=driverService.updateDriver(id,driver);
        if(isUpdate){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Driver is updated"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Driver is not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDriver(@PathVariable Integer id){
        Boolean isDelete=driverService.deleteDriver(id);
        if(isDelete){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Driver is deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Driver is not found"));
    }


}
