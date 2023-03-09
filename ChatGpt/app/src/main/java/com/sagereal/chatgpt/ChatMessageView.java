package com.sagereal.chatgpt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessageView extends LinearLayout {

    private TextView messageTextView,messageTextData;
    private ImageView avatarImageView;

    public ChatMessageView(Context context) {
        this(context, null);
    }

    public ChatMessageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.list_item_chat_message, this);
        messageTextView = findViewById(R.id.message_text_view);
        messageTextData = findViewById(R.id.message_data);
        avatarImageView = findViewById(R.id.avatar_image_view);
    }

    public void setMessageText(String text) {
        messageTextView.setText(text);
    }

    public void setMessageTextData(Date data) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        String strDate = format.format(data);
        messageTextData.setText(strDate);
    }

//    public void setAvatarImage(String url) {
//        new Thread(() -> {
//            try {
//                Bitmap bitmap = getBitmapFromURL(url);
//                avatarImageView.post(() -> avatarImageView.setImageBitmap(bitmap));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }
//
//    private Bitmap getBitmapFromURL(String src) throws IOException {
//        URL url = new URL(src);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setDoInput(true);
//        connection.connect();
//        InputStream input = connection.getInputStream();
//        Bitmap myBitmap = BitmapFactory.decodeStream(input);
//        return myBitmap;
//    }
}
