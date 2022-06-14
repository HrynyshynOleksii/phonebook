package com.example.phonebookweb.repo;

import com.example.phonebookweb.config.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

}
