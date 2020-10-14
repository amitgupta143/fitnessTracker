package com.deloitte.fitnesstracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.deloitte.fitnesstracker.vo.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmailId(String email);
}
