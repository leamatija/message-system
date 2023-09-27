package com.message.controller;

import com.message.entity.Message;
import com.message.repository.MessageRepository;
import com.message.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {


    @Autowired
    private  MessageService messageService;

    @GetMapping
    public ResponseEntity<Object> getRealTimeMessage(){

        return ResponseEntity.ok(messageService.getRealTimeMessage());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Message>> getAllMessagesOrdered(){
        return ResponseEntity.ok(messageService.getAllMessagesOrdered());
    }

}
