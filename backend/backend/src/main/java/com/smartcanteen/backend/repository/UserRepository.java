package com.smartcanteen.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.smartcanteen.backend.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByRegisterNo(String registerNo);
}
