package com.example.transport_management_system1.Repository;

import com.example.transport_management_system1.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);
}
