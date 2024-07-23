package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    //@Query("Select r from Role r")
    List<Role> findAll();
}
