package com.example.transport_management_system1.Repository;

import com.example.transport_management_system1.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findAdminById(Integer id);
}
