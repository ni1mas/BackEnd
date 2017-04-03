package com.example.daos;

import com.example.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Robert on 04/04/2017.
 */

@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM User", nativeQuery = true)
    List<User> miFuncion(String email, String password);
}
