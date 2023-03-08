package com.sagereal.chatgpt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ChatMessage> messageList;
    private ChatListAdapter messageAdapter;
    private ListView messageListView;
    private EditText messageEditText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化 UI 元素
        messageListView = findViewById(R.id.message_list_view);
        messageEditText = findViewById(R.id.message_edit_text);
        sendButton = findViewById(R.id.send_button);

        // 初始化消息列表和消息适配器
        messageList = new ArrayList<>();
        messageAdapter = new ChatListAdapter(this, messageList);
        messageListView.setAdapter(messageAdapter);

        // 设置发送按钮的点击事件
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取消息文本
                String messageText = messageEditText.getText().toString();

                // 创建新的聊天消息对象
                ChatMessage newMessage = new ChatMessage(
                        "Alice", // 发送者名称
                        messageText, // 消息文本
                        "https://example.com/avatar.png", // 头像 URL
                        new Date(), // 时间戳
                        true // 是否是我发送的消息
                );

                // 将新消息添加到消息列表中
                messageList.add(newMessage);

                // 清空消息编辑框
                messageEditText.setText("");

                // 更新消息列表
                messageAdapter.notifyDataSetChanged();
            }
        });
    }
}


