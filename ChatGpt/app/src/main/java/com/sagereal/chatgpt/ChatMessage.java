package com.sagereal.chatgpt;

import java.util.Date;

public class ChatMessage {

    public static String SENT_BY_ME = "me";
    public static String SENT_BY_BOT="bot";

    private String messageText;
    private Date timestamp;
    private String sentBy;

    public ChatMessage(String messageText, Date timestamp, String sentBy) {
        this.messageText = messageText;
        this.timestamp = timestamp;
        this.sentBy = sentBy;
    }

    public String getMessageText() {
        return messageText;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public String getSentBy() {
        return sentBy;
    }

}


