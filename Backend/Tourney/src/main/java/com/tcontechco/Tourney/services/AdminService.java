package com.tcontechco.Tourney.services;

import com.tcontechco.Tourney.exceptions.ResourceDoesNotExist;
import com.tcontechco.Tourney.models.Admin;
import com.tcontechco.Tourney.repos.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class AdminService {
    private final AdminRepo adminRepo;

    @Autowired
    public AdminService(AdminRepo adminRepo){this.adminRepo=adminRepo;}

    public List<Admin> getAll(){
        return adminRepo.findAll();
    }

    public Admin getByID(Integer id){
        return adminRepo.findById(id).orElseThrow(()-> new ResourceDoesNotExist("admin not found"));
    }

    public Admin createAdmin(Admin admin){
        return adminRepo.save(admin);
    }
}
