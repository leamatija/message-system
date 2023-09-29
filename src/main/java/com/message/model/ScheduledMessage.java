package com.message.model;

import com.message.entity.Message;

import java.time.LocalDateTime;

public class ScheduledMessage {
    private Message message;
    private LocalDateTime triggerTime;
    private boolean inProcess;

    public ScheduledMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public LocalDateTime getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(LocalDateTime triggerTime) {
        this.triggerTime = triggerTime;
    }

    public boolean isInProcess() {
        return inProcess;
    }

    public void setInProcess(boolean inProcess) {
        this.inProcess = inProcess;
    }
}
