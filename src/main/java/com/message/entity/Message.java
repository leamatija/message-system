package com.message.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("message")
public class Message {

    @Id
    private String id;
    private String content;
    private MessageType messageType;
    private boolean scheduled;
    private LocalDateTime createdAt;

    public Message(String content, MessageType messageType, boolean scheduled, LocalDateTime createdAt) {
        this.content = content;
        this.createdAt = createdAt;
        this.messageType = messageType;
        this.scheduled=scheduled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public boolean isScheduled() {
        return scheduled;
    }

    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }
}
