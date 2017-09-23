package com.example.daniel.ai_labs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Lab2Activity extends AppCompatActivity {

    Button enter;
    TextView inputField;
    LinearLayout messagesField;
    final String TAG = "Message";
    ChatDBHandler db = new ChatDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);

        final LayoutInflater inflater = getLayoutInflater();
        enter = (Button)findViewById(R.id.enter);
        inputField = (TextView)findViewById(R.id.userMessage);
        messagesField = (LinearLayout)findViewById(R.id.messageField);

        View.OnClickListener OnCLickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == null || !(v instanceof TextView)) {
                    Log.d(TAG, "нет свойства text");
                    return; //нет свойства text
                }
                switch (v.getId()) {
                    case R.id.enter:
                        sendMessage(true, inputField.getText().toString(), inflater);
                        break;
                }
            }
        };

        enter.setOnClickListener(OnCLickListener);
    }

    private void sendMessage (boolean autorsMarker, String message, LayoutInflater inflater){
        View messagePattern;

        if (message.equals("")) return;

        if (autorsMarker) { //user?
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
