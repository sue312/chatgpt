package com.sagereal.chatgpt;

import java.util.Date;

public class ChatMessage {
    private String senderName;
    private String messageText;
    private String avatarImageURL;
    private Date timestamp;
    private boolean isSentByMe;

    public ChatMessage(String senderName, String messageText, String avatarImageURL, Date timestamp, boolean isSentByMe) {
        this.senderName = senderName;
        this.messageText = messageText;
        this.avatarImageURL = avatarImageURL;
        this.timestamp = timestamp;
        this.isSentByMe = isSentByMe;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getAvatarImageURL() {
        return avatarImageURL;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public boolean isSentByMe() {
        return isSentByMe;
    }
}


