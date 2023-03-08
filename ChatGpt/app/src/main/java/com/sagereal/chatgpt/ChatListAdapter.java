package com.sagereal.chatgpt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class ChatListAdapter extends ArrayAdapter<ChatMessage> {

    private Context context;
    private ArrayList<ChatMessage> chatList;

    public ChatListAdapter(Context context, ArrayList<ChatMessage> chatList) {
        super(context, 0, chatList);
        this.context = context;
        this.chatList = chatList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage message = chatList.get(position);
        ChatMessageView messageView;

        if (convertView == null) {
            messageView = new ChatMessageView(context);
        } else {
            messageView = (ChatMessageView) convertView;
        }

        messageView.setMessageText(message.getMessageText());
        messageView.setMessageTextData(message.getTimestamp());
        messageView.setAvatarImage(message.getAvatarImageURL());

        return messageView;
    }
}


