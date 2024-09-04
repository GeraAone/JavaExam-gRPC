package com.geralab.JavaExam.repository;

import com.geralab.JavaExam.entity.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//     @Lock(LockModeType.PESSIMISTIC_WRITE)
     List<User> findAllByAgeIsGreaterThanEqual(Integer age);
}
