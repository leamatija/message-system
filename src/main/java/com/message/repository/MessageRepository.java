package com.message.repository;

import com.message.entity.Message;
import com.message.entity.MessageType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message,Integer> {

    List<Message> findByCreatedAtBetweenAndScheduledFalseOrderByMessageTypePriorityAscCreatedAtAsc(LocalDateTime startTime, LocalDateTime endDate);
    List<Message> findByCreatedAtBetweenOrderByMessageTypePriorityAscCreatedAtAsc(LocalDateTime startTime, LocalDateTime endDate);
    List<Message> findAllByScheduledTrueOrderByMessageTypePriorityAsc();


}