package com.involveininnovation.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.involveininnovation.chat.entities.Admin;

import java.util.UUID;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findById(UUID id);
    Admin findAdminByUsername(String username);
}
