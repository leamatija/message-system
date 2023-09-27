package com.message.service;


import com.message.entity.Message;

import java.util.List;

public interface MessageService {

    Message getRealTimeMessage();
    List<Message> getAllMessagesOrdered();
}
