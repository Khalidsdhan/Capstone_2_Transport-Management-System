package com.example.transport_management_system1.Controller;

import com.example.transport_management_system1.Api.ApiResponse;
import com.example.transport_management_system1.Model.Rating;
import com.example.transport_management_system1.Service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping("/get")
    public ResponseEntity getAllRating(){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRating());
    }


    @PostMapping("/add")
    public ResponseEntity addRating(@Valid @RequestBody Rating rating, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isAdd=ratingService.addRating(rating);
        if(isAdd){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Rating is added"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Rating is exist"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateRating(@PathVariable Integer id,@Valid @RequestBody Rating rating ,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdate=ratingService.updateRating(id, rating);
        if(isUpdate){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Rating is updated"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Rating is not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRating(@PathVariable Integer id){
        Boolean isDelete=ratingService.deleteRating(id);
        if(isDelete){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Rating is deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Rating is not found"));
    }

    @GetMapping("/highest-rated-driver")
    public ResponseEntity getHighestRatedDriver() {
        Rating highestRatedDriver = ratingService.getHighestRatedDriver();

        if (highestRatedDriver == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No ratings found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(highestRatedDriver);
    }

    @DeleteMapping("/delete-all-ratings/{driverId}")
    public ResponseEntity deleteAllRatings(@PathVariable String driverId) {
        Boolean isDeleted = ratingService.deleteAllRatingsForDriver(driverId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("All ratings deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("No ratings found for this driver"));
    }

    @GetMapping("/bonus-eligible/{driverId}")
    public ResponseEntity checkDriverEligibilityForBonus(@PathVariable Integer driverId) {
        Boolean eligible = ratingService.isDriverEligibleForBonus(driverId);
        if(eligible) {
            return ResponseEntity.ok(new ApiResponse("Driver is eligible for bonus"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Driver is not eligible for bonus"));
    }
}
