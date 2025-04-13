package com.example.transport_management_system1.Repository;

import com.example.transport_management_system1.Model.TransportRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransportRequestRepository extends JpaRepository<TransportRequest,Integer> {

    TransportRequest findTransportRequestById(Integer id);

    List<TransportRequest> findAllByStatus(String status);

    List<TransportRequest> findAllByExpectedDeliveryDateBefore(LocalDate date);


}
