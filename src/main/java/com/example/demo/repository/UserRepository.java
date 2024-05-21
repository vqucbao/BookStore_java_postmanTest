package com.example.demo.repository;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.name like :keyword or u.dateOfBirth like :keyword  or u.phoneNumber like :keyword or u.email like :keyword or u.position like :keyword")
    List<User> findUserByKeywords(@Param("keyword") String keyword);

    @Query("SELECT e FROM User e")
    List<User> findUsers(Pageable pageable);

    boolean existsByName(String username);
}
