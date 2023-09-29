package com.message.service;


import com.message.entity.Message;
import com.message.entity.MessageType;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageService {

    Message getRealTimeMessage(LocalDateTime startDate,LocalDateTime endDate);
    List<Message> getAllMessagesOrdered(LocalDateTime startDate,LocalDateTime endDate);
    Message addMessage(Message message);
    List<MessageType> getMessageTypes();
}
