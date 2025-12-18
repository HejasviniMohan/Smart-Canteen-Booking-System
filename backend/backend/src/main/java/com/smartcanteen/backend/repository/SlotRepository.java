package com.smartcanteen.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.smartcanteen.backend.model.Slot;

public interface SlotRepository extends MongoRepository<Slot, String> {
}
