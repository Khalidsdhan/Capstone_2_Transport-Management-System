package com.example.transport_management_system1.Service;

import com.example.transport_management_system1.Model.Vehicle;
import com.example.transport_management_system1.Repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final DriverService driverService;


    public List<Vehicle> getAllVehicle(){
        return vehicleRepository.findAll();
    }

    public Boolean addVehicle(Vehicle vehicle){
        if(driverService.getById(vehicle.getDriverId())==null){
            return false;
        }
        vehicleRepository.save(vehicle);
        return true;
    }





    public Boolean updateVehicle(Integer id,Vehicle vehicle){
        Vehicle oldVehicle=vehicleRepository.findVehicleById(id);

        if(oldVehicle==null){
            return false;
        }

        oldVehicle.setPlateNumber(oldVehicle.getPlateNumber());
        oldVehicle.setType(oldVehicle.getType());
        oldVehicle.setModel(oldVehicle.getModel());
        oldVehicle.setMaxLoadKg(oldVehicle.getMaxLoadKg());
        oldVehicle.setDriverId(oldVehicle.getDriverId());

        vehicleRepository.save(oldVehicle);
        return true;
    }

    public Boolean deleteVehicle(Integer id){
        Vehicle vehicle=vehicleRepository.findVehicleById(id);

        if(vehicle==null){
            return false;
        }
        vehicleRepository.delete(vehicle);
        return true;
    }

    public Vehicle getById(Integer id){
        return vehicleRepository.findVehicleById(id);
    }

    //Make sure the vehicle needs maintenance or not
    public Boolean checkMaintenance(Integer id) {
        Vehicle vehicle = vehicleRepository.findVehicleById(id);
        if (vehicle == null) {
            return false;
        }

        return vehicle.getDistance() >= 1500;
    }

    //Determines the best vehicle for your order based on the weight of your order
    public String suggestVehicleType(Vehicle vehicle) {
        if (vehicle.getMaxLoadKg() <= 1000) {
            return "VAN";
        } else if (vehicle.getMaxLoadKg() <= 3000) {
            return "truck";
        } else {
            return "trailer";
        }
    }

    // get Vehicle By PlateNumber
    public Vehicle getVehicleByPlateNumber(String plateNumber) {
        return vehicleRepository.findVehicleByPlateNumber(plateNumber);
    }


    //Sort vehicles based on the highest mileage if you want to sell a used vehicle.
    public List<Vehicle> getAllVehiclesSortedByDistance() {
        return vehicleRepository.findAllOrderByDistanceDesc();
    }

    //When he wants to take the highest load vehicles
    public List<Vehicle> getVehiclesCloseToMaxLoad() {
        return vehicleRepository.findVehiclesCloseToMaxLoad();
    }


    //When he wants to search for the newest model vehicles
    public Vehicle ChooseTheVehicleByModel(String model){
        return vehicleRepository.findVehicleByModel(model);
    }

}
