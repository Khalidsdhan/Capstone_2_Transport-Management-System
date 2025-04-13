package com.example.transport_management_system1.Service;

import com.example.transport_management_system1.Model.Admin;
import com.example.transport_management_system1.Repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;


    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }


    public void addAdmin(Admin admin){
        adminRepository.save(admin);
    }


    public Boolean updateAdmin(Integer id,Admin admin){
        Admin oldAdmin=adminRepository.findAdminById(id);

        if(oldAdmin==null){
            return false;
        }

        oldAdmin.setFullName(oldAdmin.getFullName());
        oldAdmin.setAdminName(oldAdmin.getAdminName());
        oldAdmin.setPassword(oldAdmin.getPassword());
        oldAdmin.setEmail(oldAdmin.getEmail());
        oldAdmin.setPhoneNumber(oldAdmin.getPhoneNumber());

        adminRepository.save(oldAdmin);
        return true;
    }

    public Boolean deleteAdmin(Integer id){
        Admin admin =adminRepository.findAdminById(id);

        if(admin==null){
            return false;
        }
        adminRepository.delete(admin);
        return true;
    }
}
