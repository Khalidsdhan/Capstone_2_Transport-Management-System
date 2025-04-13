package com.example.transport_management_system1.Controller;

import com.example.transport_management_system1.Api.ApiResponse;
import com.example.transport_management_system1.Model.TransportRequest;
import com.example.transport_management_system1.Service.TransportRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transportRequest")
@RequiredArgsConstructor
public class TransportRequestController {

    private final TransportRequestService transportRequestService;

    @GetMapping("/get")
    public ResponseEntity getAllTransportRequest() {
        return ResponseEntity.status(HttpStatus.OK).body(transportRequestService.getAllTransportRequest());
    }


    @PostMapping("/add")
    public ResponseEntity addTransportRequest(@Valid @RequestBody TransportRequest transportRequest, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isAdd = transportRequestService.addTransportRequest(transportRequest);
        if (isAdd) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("TransportRequest is added"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("TransportRequest is exist"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateTransportRequest(@PathVariable Integer id, @Valid @RequestBody TransportRequest transportRequest, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdate = transportRequestService.updateTransportRequest(id, transportRequest);
        if (isUpdate) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("TransportRequest is updated"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("TransportRequest is not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTransportRequest(@PathVariable Integer id) {
        Boolean isDelete = transportRequestService.deleteTransportRequest(id);
        if (isDelete) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("TransportRequest is deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("TransportRequest is not found"));
    }

    @PutMapping("/reschedule/{id}/{newDriverId}")
    public ResponseEntity rescheduleRequest(@PathVariable Integer id, @PathVariable Integer newDriverId) {
        Boolean isRescheduled = transportRequestService.rescheduleTransportRequest(id, newDriverId);
        if (isRescheduled) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("The order has been rescheduled."));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to reschedule"));
    }


    @PostMapping("/calculate-price")
    public ResponseEntity calculatePrice(@RequestBody TransportRequest transportRequest) {
        Double price = transportRequestService.calculatePrice(transportRequest.getDistance(), transportRequest.getEstimatedWeight());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Approximate price:" + price + " SAR"));
    }

    @GetMapping("/report/{status}")
    public ResponseEntity getReport(@PathVariable String status) {
        List<TransportRequest> transportRequests = transportRequestService.generateReport(status);

        if (transportRequests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("There are no requests for this case."));
        }

        return ResponseEntity.status(HttpStatus.OK).body(transportRequests);
    }

    @GetMapping("/delayed-requests")
    public ResponseEntity getDelayedRequests() {
        List<TransportRequest> delayedRequests = transportRequestService.getDelayedRequests();

        if (delayedRequests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("There are no backorders currently."));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("We apologize for the delay, these orders are overdue."));
    }

    @GetMapping("/special-shipping/{id}")
    public ResponseEntity requiresSpecialShipping(@PathVariable Integer id) {
        TransportRequest transportRequest = transportRequestService.getById(id);

        if (transportRequest == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("TransportRequest not found"));
        }

        Boolean specialShippingRequired = transportRequestService.requiresSpecialShipping(id);
        if (specialShippingRequired) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Special shipping is required."));
        }
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Special shipping is not required."));
    }

    @GetMapping("/delay-fine/{id}")
    public ResponseEntity getDelayFine(@PathVariable Integer id) {
        Double fine = transportRequestService.calculateDelayFine(id);

        if (fine == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("TransportRequest not found or missing expected delivery date."));
        }

        if (fine == 0.0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("No delay. No fine."));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Delay fine is: " + fine + " SAR"));
    }



}
