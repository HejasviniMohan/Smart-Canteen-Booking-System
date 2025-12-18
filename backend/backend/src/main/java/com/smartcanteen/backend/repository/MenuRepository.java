package com.smartcanteen.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.smartcanteen.backend.model.MenuItem;

public interface MenuRepository extends MongoRepository<MenuItem, String> {

    List<MenuItem> findByCategory(String category);
    List<MenuItem> findByPopularTrue();
}
