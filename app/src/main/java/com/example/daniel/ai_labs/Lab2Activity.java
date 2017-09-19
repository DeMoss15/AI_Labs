package com.example.daniel.ai_labs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Lab2Activity extends AppCompatActivity {

    ImageButton enter;
    TextView inputField;
    LinearLayout messagesField;
    final String TAG = "Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);

        final LayoutInflater inflater = getLayoutInflater();
        enter = (ImageButton)findViewById(R.id.enter);
        inputField = (TextView)findViewById(R.id.userMessage);
        messagesField = (LinearLayout)findViewById(R.id.messageField);
        Log.d(TAG, "Activity ");

        View.OnClickListener OnCLickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == null || !(v instanceof TextView)) {
                    return; //нет свойства text
                }
                switch (v.getId()) {
                    case R.id.enter:
                        sendMessage(true, inputField.getText().toString(), inflater);
                        Log.d(TAG, "вызов ф-ции");
                        break;
                }
            }
        };

        enter.setOnClickListener(OnCLickListener);
    }

    private void sendMessage (boolean autor, String message, LayoutInflater inflater){
        View messagePattern;

        if (message.equals("")) return;

        if (autor) { //user?
            messagePattern = inflater.inflate(R.layout.user_message_pattern, messagesField, false);
            Log.d(TAG, "создание сообщения от юзера");

            TextView messageView = (TextView)findViewById(R.id.userMessage);
            messageView.setText(message);
        } else {
            messagePattern = inflater.inflate(R.layout.answer_pattern, messagesField, false);

            TextView messageView = (TextView)findViewById(R.id.answerMessage);
            messageView.setText(message);
        }

        Log.d(TAG, "отправка сообщения в поле");
        messagesField.addView(messagePattern);
    }
}
