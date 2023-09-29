package com.message.controller;

import com.message.entity.Message;
import com.message.entity.MessageType;
import com.message.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {


    @Autowired
    private  MessageService messageService;

    @GetMapping
    public ResponseEntity<Object> getRealTimeMessage(@RequestParam(required = false) LocalDateTime fromDate,
                                                     @RequestParam(required = false) LocalDateTime toDate){
        fromDate=fromDate!=null?fromDate:LocalDate.now().atTime(00, 00, 00);
        toDate=toDate!=null?toDate:LocalDate.now().atTime(23, 59, 59);

        return ResponseEntity.ok(messageService.getRealTimeMessage(fromDate,toDate));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Message>> getAllMessagesOrdered(@RequestParam(required = false) LocalDateTime fromDate,
                                                               @RequestParam(required = false) LocalDateTime toDate){
        fromDate=fromDate!=null?fromDate:LocalDate.now().atTime(00, 00, 00);
        toDate=toDate!=null?toDate:LocalDate.now().atTime(23, 59, 59);

        return ResponseEntity.ok(messageService.getAllMessagesOrdered(fromDate,toDate));
    }

    @PostMapping
    public ResponseEntity<Message> addMessage(@RequestBody Message message){
        return ResponseEntity.ok(messageService.addMessage(message));
    }

    @GetMapping("/type/list")
    public ResponseEntity<List<MessageType>> getMessageType(){
        return ResponseEntity.ok(messageService.getMessageTypes());
    }

}
