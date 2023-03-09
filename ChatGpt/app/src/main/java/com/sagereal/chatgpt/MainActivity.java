package com.sagereal.chatgpt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ChatMessage> messageList;
    private ChatListAdapter messageAdapter;
    private ListView messageListView;
    private EditText messageEditText;
    private ImageButton sendButton;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

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
                        messageText, // 消息文本
                        new Date(), // 时间戳
                        ChatMessage.SENT_BY_ME
                );

                // 将新消息添加到消息列表中
                messageList.add(newMessage);

                // 清空消息编辑框
                messageEditText.setText("");

                // 更新消息列表
                messageAdapter.notifyDataSetChanged();

                callAPI(messageText);
            }
        });
    }

    private void addToChat(String message,String sentBy){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new ChatMessage(message,new Date(),sentBy));
                messageAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addResponse(String response){
        messageList.remove(messageList.size()-1);
        addToChat(response,ChatMessage.SENT_BY_BOT);
    }

    private void callAPI(String question){
        //okhttp
        messageList.add(new ChatMessage("Typing... ", new Date(),ChatMessage.SENT_BY_BOT));

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model","text-davinci-003");
            jsonBody.put("prompt",question);
            jsonBody.put("max_tokens",4000);
            jsonBody.put("temperature",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonBody.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization","Bearer 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")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response due to "+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    JSONObject  jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        addResponse(result.trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    addResponse("Failed to load response due to "+response.body().toString());
                }
            }
        });
    }

}


