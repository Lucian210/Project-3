package ro.tuc.ds2020.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.tuc.ds2020.entities.UserAuth;

public interface UserRepository extends CrudRepository<UserAuth, Long> {
    UserAuth findByUsername(String username);
}
