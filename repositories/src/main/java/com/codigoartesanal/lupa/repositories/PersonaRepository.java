package com.codigoartesanal.lupa.repositories;

import com.codigoartesanal.lupa.model.Persona;
import com.codigoartesanal.lupa.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kkimvazquezangeles on 22/05/15.
 */
public interface PersonaRepository extends CrudRepository<Persona, Long> {
    Persona findByAdmin(User admin);
}
