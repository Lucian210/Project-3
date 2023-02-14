package ro.tuc.ds2020.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Admin;

import java.util.UUID;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findById(UUID id);
    Admin findAdminByUsername(String username);
}
