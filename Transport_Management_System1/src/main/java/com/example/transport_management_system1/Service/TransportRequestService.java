package com.example.transport_management_system1.Service;

import com.example.transport_management_system1.Model.TransportRequest;
import com.example.transport_management_system1.Model.Vehicle;
import com.example.transport_management_system1.Repository.TransportRequestRepository;
import com.example.transport_management_system1.Repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportRequestService {


    private final TransportRequestRepository transportRequestRepository;
    private final UserService userService;
    private final DriverService driverService;
    private final VehicleService vehicleService;
    private final VehicleRepository vehicleRepository;



    public List<TransportRequest> getAllTransportRequest() {
        return transportRequestRepository.findAll();
    }

    public Boolean addTransportRequest(TransportRequest transportRequest) {
        if (userService.getById(transportRequest.getUserId()) == null && driverService.getById(transportRequest.getDriverId()) == null) {
            return false;
        }

       Vehicle vehicle= vehicleService.getById(transportRequest.getVehicleId());
        vehicle.setDistance(transportRequest.getDistance() +vehicle.getDistance());
        vehicleRepository.save(vehicle);
        transportRequestRepository.save(transportRequest);
        return true;
    }

    public Boolean updateTransportRequest(Integer id, TransportRequest transportRequest) {
        TransportRequest oldTransportRequest = transportRequestRepository.findTransportRequestById(id);

        if (oldTransportRequest == null) {
            return false;
        }

        oldTransportRequest.setStartsFrom(oldTransportRequest.getStartsFrom());
        oldTransportRequest.setDestination(oldTransportRequest.getDestination());
        oldTransportRequest.setType(oldTransportRequest.getType());
        oldTransportRequest.setEstimatedWeight(oldTransportRequest.getEstimatedWeight());
        oldTransportRequest.setStatus(oldTransportRequest.getStatus());
        oldTransportRequest.setUserId(oldTransportRequest.getUserId());
        oldTransportRequest.setDriverId(oldTransportRequest.getDriverId());

        transportRequestRepository.save(oldTransportRequest);
        return true;
    }

    public Boolean deleteTransportRequest(Integer id) {
        TransportRequest transportRequest = transportRequestRepository.findTransportRequestById(id);

        if (transportRequest == null) {
            return false;
        }

        transportRequestRepository.delete(transportRequest);
        return true;
    }


    public TransportRequest getById (Integer id){
        return transportRequestRepository.findTransportRequestById(id);
    }

    //Reschedule a request if it is canceled by driver
    public Boolean rescheduleTransportRequest(Integer id, Integer newDriverId) {
        TransportRequest transportRequest = transportRequestRepository.findTransportRequestById(id);
        if (transportRequest == null){
            return false;
        }

        if (driverService.getById(newDriverId) == null){
            return false;
        }

        transportRequest.setDriverId(newDriverId);
        transportRequest.setStatus("accepted");
        transportRequestRepository.save(transportRequest);
        return true;
    }

    //calculate Price
    public Double calculatePrice(Double distance, Double estimatedWeight) {
        Double price = (distance * 2) + (estimatedWeight * 2);
        return price;
    }

    //generate report at status like accepted
    public List<TransportRequest> generateReport(String status) {
        if (status == null || status.isEmpty()) {
            return transportRequestRepository.findAll();
        } else {
            return transportRequestRepository.findAllByStatus(status);
        }
    }

    //Delayed requests
    public List<TransportRequest> getDelayedRequests() {
        return transportRequestRepository.findAllByExpectedDeliveryDateBefore(LocalDate.now());
    }



    //Special requests if his Weight like the methode
    public Boolean requiresSpecialShipping(Integer id) {
        TransportRequest transportRequest=transportRequestRepository.findTransportRequestById(id);
        return transportRequest.getEstimatedWeight()  > 3000 || "furniuer".equals(transportRequest.getType());
    }


    //calculate Delay Fine
    public Double calculateDelayFine(Integer requestId) {
        TransportRequest transportRequest = transportRequestRepository.findTransportRequestById(requestId);

        if (transportRequest == null) {
            return null;
        }

        LocalDate today = LocalDate.now();
        LocalDate expectedDate = transportRequest.getExpectedDeliveryDate();

        if (!today.isAfter(expectedDate)) {
            return 0.0;
        }

        double delayedDays = ChronoUnit.DAYS.between(expectedDate, today);
        double finePerDay = 50.0;

        return finePerDay * delayedDays;
    }


}


