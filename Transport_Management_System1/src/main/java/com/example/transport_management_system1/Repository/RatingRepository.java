package com.example.transport_management_system1.Repository;

import com.example.transport_management_system1.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {

    Rating findRatingById(Integer id);

    @Query("select r from Rating r  order by r.score desc")
    List<Rating> findTopByOrderByScore();

    List<Rating> findByRatedDriver(String ratedDriver);

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.ratedDriver = :driverId")
    Double findAverageRatingByDriver(@PathVariable("driverId") Integer driverId);
}
