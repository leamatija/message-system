package com.message.service.impl;

import com.message.entity.Message;
import com.message.repository.MessageRepository;
import com.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message getRealTimeMessage() {
        var dateTime = LocalDateTime.now();
        var startDate= LocalDate.now().atTime(00, 00, 00);
        var endDate= LocalDate.now().atTime(23, 59, 59);
        var messages = messageRepository.findByStartTimeBetweenOrderByMessageTypePriorityAscStartTimeAsc(startDate,endDate);
        var response = messages.stream()
                .filter(m -> m.getStartTime().plusSeconds(m.getMessageType().getDurationInSeconds()).isAfter(dateTime))
                .filter(m -> m.getStartTime().isBefore(dateTime))
                .map(m ->{
                    Long timeLeft = ChronoUnit.SECONDS.between(dateTime,m.getStartTime().plusSeconds(m.getMessageType().getDurationInSeconds()));
                    m.getMessageType().setDurationInSeconds(timeLeft.intValue());
                    return m;
                })
                .findFirst().orElse(null);
        return response;
    }

    @Override
    public List<Message> getAllMessagesOrdered() {
        var startDate= LocalDate.now().atTime(00, 00, 00);
        var endDate= LocalDate.now().atTime(23, 59, 59);
        var messages = messageRepository.findByStartTimeBetweenOrderByMessageTypePriorityAscStartTimeAsc(startDate,endDate);
        messages.forEach(m -> System.err.println(m.getContent()+"  "+m.getMessageType().getPriority()+" "+m.getStartTime()));
        return messages;
    }
}
