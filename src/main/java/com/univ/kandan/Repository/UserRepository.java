package com.univ.kandan.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.univ.kandan.model.User;;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByEmail(String email);

}
