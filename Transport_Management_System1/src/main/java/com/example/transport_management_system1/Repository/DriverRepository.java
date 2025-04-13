package com.example.transport_management_system1.Repository;

import com.example.transport_management_system1.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Integer> {

    Driver findDriverById(Integer id);
}
