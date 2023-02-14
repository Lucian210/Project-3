package com.involveininnovation.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import com.involveininnovation.chat.entities.UserAuth;

public interface UserRepository extends CrudRepository<UserAuth, Long> {
    UserAuth findByUsername(String username);
}
