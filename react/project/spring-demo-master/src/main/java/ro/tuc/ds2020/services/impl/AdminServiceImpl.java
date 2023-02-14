package ro.tuc.ds2020.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.Admin;
import ro.tuc.ds2020.repositories.AdminRepository;
import ro.tuc.ds2020.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }
}
