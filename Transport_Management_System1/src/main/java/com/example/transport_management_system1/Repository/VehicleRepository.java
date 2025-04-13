package com.example.transport_management_system1.Repository;

import com.example.transport_management_system1.Model.Vehicle;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {

    Vehicle findVehicleById(Integer id);

    Vehicle findVehicleByPlateNumber(String plateNumber);


    @Query("select v from Vehicle v order by v.distance desc")
    List<Vehicle> findAllOrderByDistanceDesc();

    @Query("select v from Vehicle v where v.maxLoadKg - v.distance <= 500")
    List<Vehicle> findVehiclesCloseToMaxLoad();

    Vehicle findVehicleByModel(String model);
}
