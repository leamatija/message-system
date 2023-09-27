package com.message.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("messagetype")
public class MessageType {

    @Id
    private String id;
    private String type;
    private Integer priority;
    private Integer durationInSeconds;

    public MessageType(String type, Integer priority, Integer durationInSeconds) {
        this.type = type;
        this.priority = priority;
        this.durationInSeconds = durationInSeconds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(Integer durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }
}
