package com.example.transport_management_system1.Service;

import com.example.transport_management_system1.Model.Driver;
import com.example.transport_management_system1.Repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;


    public List<Driver> getAllDriver(){
        return driverRepository.findAll();
    }


    public void addUser(Driver driver){
        driverRepository.save(driver);
    }


    public Boolean updateDriver(Integer id,Driver driver){
        Driver oldDriver=driverRepository.findDriverById(id);

        if(oldDriver==null){
            return false;
        }

        oldDriver.setFullName(oldDriver.getFullName());
        oldDriver.setUserName(oldDriver.getUserName());
        oldDriver.setPassword(oldDriver.getPassword());
        oldDriver.setEmail(oldDriver.getEmail());
        oldDriver.setPhoneNumber(oldDriver.getPhoneNumber());

        driverRepository.save(oldDriver);
        return true;
    }

    public Boolean deleteDriver(Integer id){
        Driver driver=driverRepository.findDriverById(id);

        if(driver==null){
            return false;
        }
        driverRepository.delete(driver);
        return true;
    }

    public Driver getById(Integer id){
        return driverRepository.findDriverById(id);
    }
}
