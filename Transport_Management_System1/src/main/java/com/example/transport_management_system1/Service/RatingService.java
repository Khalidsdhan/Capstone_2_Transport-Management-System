package com.example.transport_management_system1.Service;

import com.example.transport_management_system1.Model.Rating;
import com.example.transport_management_system1.Repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final TransportRequestService transportRequestService;

    public List<Rating> getAllRating(){
        return ratingRepository.findAll();
    }

    public Boolean addRating(Rating rating){
        if(transportRequestService.getById(rating.getTransport_requestId())==null){
            return false;
        }
        ratingRepository.save(rating);
        return true;
    }


    public  Boolean updateRating(Integer id,Rating rating){
        Rating oldRating=ratingRepository.findRatingById(id);

        if(oldRating==null){
            return false;
        }
        oldRating.setScore(oldRating.getScore());
        oldRating.setComment(oldRating.getComment());
        oldRating.setRatedBy(oldRating.getRatedBy());
        oldRating.setRatedDriver(oldRating.getRatedDriver());
        oldRating.setRatingDate(oldRating.getRatingDate());
        oldRating.setTransport_requestId(oldRating.getTransport_requestId());

        ratingRepository.save(oldRating);
        return true;
    }

    public Boolean deleteRating(Integer id){
        Rating rating=ratingRepository.findRatingById(id);

        if(rating==null){
            return false;
        }
        ratingRepository.delete(rating);
        return true;
    }

//The Best Rated Driver
    public Rating getHighestRatedDriver() {
        List<Rating> ratings = ratingRepository.findTopByOrderByScore();
        if(ratings.isEmpty()) {
            return null;
        }
        return ratings.get(0);
    }

    //Delete all ratings for a specific driver
    public Boolean deleteAllRatingsForDriver(String driverId) {
        List<Rating> ratings = ratingRepository.findByRatedDriver(driverId);
        if (ratings.isEmpty()) {
            return false;
        }
        ratingRepository.deleteAll(ratings);
        return true;
    }

    //Driver deserves a bonus based on his rating
    public Boolean isDriverEligibleForBonus(Integer driverId) {
        Double averageRating = ratingRepository.findAverageRatingByDriver(driverId);
        return averageRating >= 4.5;
    }

}
