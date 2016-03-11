package com.codigoartesanal.lupa.repositories;

import com.codigoartesanal.lupa.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kkimvazquezangeles on 25/01/15.
 */
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
}
