package com.example.repository;

import com.example.entity.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SignUpRepository extends JpaRepository<SignUp, Long> {
    SignUp findBySignUpUserName(String userName);
}
