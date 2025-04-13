package com.example.transport_management_system1.Controller;

import com.example.transport_management_system1.Api.ApiResponse;
import com.example.transport_management_system1.Model.Vehicle;
import com.example.transport_management_system1.Service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/get")
    public ResponseEntity getAllVehicle(){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getAllVehicle());
    }


    @PostMapping("/add")
    public ResponseEntity addVehicle(@Valid @RequestBody Vehicle vehicle ,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isAdd=vehicleService.addVehicle(vehicle);
        if(isAdd){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Vehicle is added"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Vehicle is exist"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateVehicle(@PathVariable Integer id,@Valid @RequestBody Vehicle vehicle ,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdate=vehicleService.updateVehicle(id,vehicle);
        if(isUpdate){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Vehicle is updated"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Vehicle is not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteVehicle(@PathVariable Integer id){
        Boolean isDelete=vehicleService.deleteVehicle(id);
        if(isDelete){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Vehicle is deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Vehicle is not found"));
    }

    @GetMapping("/check-maintenance/{id}")
    public ResponseEntity checkMaintenance(@PathVariable Integer id) {
        Boolean needsMaintenance = vehicleService.checkMaintenance(id);

        if (needsMaintenance == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Vehicle is not found"));
        }

        if (needsMaintenance) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Vehicle needs maintenance."));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Vehicle is in good condition."));
        }
    }

    @GetMapping("/suggest-vehicle-type/{id}")
    public ResponseEntity suggestVehicleType(@PathVariable Integer id) {
        Vehicle vehicle = vehicleService.getById(id);
        if (vehicle == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Vehicle not found"));
        }

        String vehicleType = vehicleService.suggestVehicleType(vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Suggested vehicle type: " + vehicleType));
    }

    @GetMapping("/by-plate/{plateNumber}")
    public ResponseEntity getVehicleByPlateNumber(@PathVariable String plateNumber) {
        Vehicle vehicle = vehicleService.getVehicleByPlateNumber(plateNumber);
        if (vehicle == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Vehicle not found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    @GetMapping("/sorted-by-distance")
    public ResponseEntity getVehiclesSortedByDistance() {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getAllVehiclesSortedByDistance());
    }


    @GetMapping("/closeTo-max-load")
    public ResponseEntity getVehiclesCloseToMaxLoad() {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getVehiclesCloseToMaxLoad());
    }

    @GetMapping("/Choose-Vehicle/{model}")
    public ResponseEntity ChooseTheVehicleByModel(@PathVariable String model){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.ChooseTheVehicleByModel(model));
    }
}
