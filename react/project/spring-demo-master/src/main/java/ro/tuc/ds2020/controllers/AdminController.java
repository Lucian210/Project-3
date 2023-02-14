package ro.tuc.ds2020.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.tuc.ds2020.services.AdminService;

@RestController
@RequestMapping("/anunt")
@CrossOrigin
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }


}