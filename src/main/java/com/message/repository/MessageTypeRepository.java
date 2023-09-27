package com.message.repository;

import com.message.entity.Message;
import com.message.entity.MessageType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTypeRepository extends MongoRepository<MessageType,Integer> {
}
