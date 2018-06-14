package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

//static filtering
@JsonIgnoreProperties(value = {"message", "time"})
//dynamic filtering per action - uses Jackson
@JsonFilter("messagefilter")
public class Message {

    private String fromUserId;

    private String toUserId;

    private String message;

//    @JsonIgnore
    private Date time;

    public Message() {
    }

    public Message(String fromUserId, String toUserId, String message, Date time) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.message = message;
        this.time = time;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
