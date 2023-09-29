package com.message.service.impl;

import com.message.entity.Message;
import com.message.entity.MessageType;
import com.message.model.ScheduledMessage;
import com.message.repository.MessageRepository;
import com.message.repository.MessageTypeRepository;
import com.message.service.MessageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageTypeRepository messageTypeRepository;

    private static final List<ScheduledMessage> scheduledMessages = new ArrayList<>();


    @PostConstruct
    public void init(){
        initScheduledMessage();
    }

    @Override
    public Message getRealTimeMessage(LocalDateTime startDate,LocalDateTime endDate) {
        var dateTimeNow = LocalDateTime.now();
        var messages = messageRepository.findByCreatedAtBetweenAndScheduledFalseOrderByMessageTypePriorityAscCreatedAtAsc(startDate,endDate);
        var response = messages.stream()
                .filter(m -> m.getCreatedAt().plusSeconds(m.getMessageType().getDurationInSeconds()).isAfter(dateTimeNow))
                .filter(m -> m.getCreatedAt().isBefore(dateTimeNow))
                .map(m -> this.setMessageAndDurationLeft(dateTimeNow,m))
                .findFirst().orElse(this.getScheduledMessage(dateTimeNow));
        return response;
    }

    @Override
    public List<Message> getAllMessagesOrdered(LocalDateTime startDate,LocalDateTime endDate) {
        var messages = messageRepository.findByCreatedAtBetweenOrderByMessageTypePriorityAscCreatedAtAsc(startDate,endDate);
        messages.forEach(m -> System.err.println(m.getContent()+"  "+m.getMessageType().getPriority()+" "+m.getCreatedAt()));
        return messages;
    }

    @Override
    public Message addMessage(Message message) {
        message.setCreatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @Override
    public List<MessageType> getMessageTypes() {
        return messageTypeRepository.findAll();
    }

    public Message getScheduledMessage(LocalDateTime dateTimeNow){

        Message responseMsg = null;

        if (scheduledMessages.size()==0){
            initScheduledMessage();
        }
        ScheduledMessage currentScheduled = scheduledMessages.get(0);

        if(currentScheduled.isInProcess() && currentScheduled.getTriggerTime().plusSeconds(currentScheduled.getMessage()
                .getMessageType().getDurationInSeconds()).isAfter(dateTimeNow)){
            responseMsg = this.setScheduledMessageAndDurationLeft(dateTimeNow,currentScheduled);
        }
        else if(currentScheduled.isInProcess() && currentScheduled.getTriggerTime().plusSeconds(currentScheduled.getMessage()
                .getMessageType().getDurationInSeconds()).isBefore(dateTimeNow)){
            scheduledMessages.remove(currentScheduled);
        }
        else {
            scheduledMessages.get(0).setTriggerTime(dateTimeNow);
            scheduledMessages.get(0).setInProcess(true);
            responseMsg = scheduledMessages.get(0).getMessage();
        }

        if(responseMsg==null){
            return getScheduledMessage(dateTimeNow);
        }

        return responseMsg;
    }

    public void initScheduledMessage(){
        List<ScheduledMessage> scheduledMsgs = messageRepository
                .findAllByScheduledTrueOrderByMessageTypePriorityAsc().stream()
                .map(m -> new ScheduledMessage(m))
                .collect(Collectors.toList());
        scheduledMessages.addAll(scheduledMsgs);
    }

    private Message setScheduledMessageAndDurationLeft(LocalDateTime current, ScheduledMessage scheduledMessage ){
        Long timeLeft = ChronoUnit.SECONDS.between(current,scheduledMessage.getTriggerTime()
                .plusSeconds(scheduledMessage.getMessage().getMessageType().getDurationInSeconds()));
        scheduledMessage.getMessage().getMessageType().setDurationInSeconds(timeLeft.intValue());
        return scheduledMessage.getMessage();
    }

    public Message setMessageAndDurationLeft(LocalDateTime current,Message message){
        Long timeLeft = ChronoUnit.SECONDS.between(current,message.getCreatedAt().plusSeconds(message.getMessageType().getDurationInSeconds()));
        message.getMessageType().setDurationInSeconds(timeLeft.intValue());
        return message;
    }
}

