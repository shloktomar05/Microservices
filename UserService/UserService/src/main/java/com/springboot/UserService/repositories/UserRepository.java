package com.springboot.UserService.repositories;

import com.springboot.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, String>{

    //custom methods

}
