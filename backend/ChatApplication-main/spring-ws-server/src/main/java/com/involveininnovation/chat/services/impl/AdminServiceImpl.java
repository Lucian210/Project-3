package com.involveininnovation.chat.services.impl;

import org.springframework.stereotype.Service;
import com.involveininnovation.chat.repositories.AdminRepository;
import com.involveininnovation.chat.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }
}
