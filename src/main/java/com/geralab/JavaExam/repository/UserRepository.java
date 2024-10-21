package com.geralab.JavaExam.repository;

import com.geralab.JavaExam.entity.Country;
import com.geralab.JavaExam.entity.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//     @Lock(LockModeType.PESSIMISTIC_WRITE)
     List<User> findAllByAgeIsGreaterThanEqual(Integer age);

     List<User> findUsersByAge(Integer age);

     @Query(value = "SELECT * FROM users " +
             "WHERE users.age > :age " +
             "AND users.country = :country"
             , nativeQuery = true)
     List<User> findUserFilteredByAgeAndCountry(Integer age, String country);
}
