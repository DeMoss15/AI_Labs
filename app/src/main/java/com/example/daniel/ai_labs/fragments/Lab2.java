package com.example.daniel.ai_labs.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.daniel.ai_labs.ChatBot;
import com.example.daniel.ai_labs.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Lab2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Lab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Lab2 extends Fragment {

    Button enter;
    TextView inputField;
    LinearLayout messagesField;
    final String TAG = "Message";
    final ChatBot MyBot = ChatBot.getInstance();

    private OnFragmentInteractionListener mListener;

    public Lab2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lab2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        final LayoutInflater inflater = getActivity().getLayoutInflater();
        enter = (Button) view.findViewById(R.id.enter);
        inputField = (TextView) view.findViewById(R.id.userMessageInput);
        messagesField = (LinearLayout) view.findViewById(R.id.messageField);

        sendMessage(false, "Привет! Я чатбот Кеша. А как тебя зовут?", inflater);

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
                        if (MyBot.isUNameSetted()) {
                            sendMessage(
                                    false,
                                    MyBot.generateAnswer(inputField.getText().toString()),
                                    inflater);
                        } else {
                            MyBot.setName(inputField.getText().toString());
                        }
                        inputField.setText("");
                        break;
                }
            }
        };

        enter.setOnClickListener(OnCLickListener);
    }

    private void sendMessage (boolean isUsersMessage, String message, LayoutInflater inflater){
        View messagePattern;

        if (message.equals("")) return;

        if (isUsersMessage) { //user?
            messagePattern = inflater.inflate(R.layout.user_message_pattern, messagesField, false);
            Log.d(TAG, "создание сообщения от юзера");

            TextView messageView = (TextView) messagePattern.findViewById(R.id.userMessage);
            messageView.setText(message);
        } else {
            messagePattern = inflater.inflate(R.layout.answer_pattern, messagesField, false);
            Log.d(TAG, "создание сообщения от Bota");

            TextView messageView = (TextView) messagePattern.findViewById(R.id.answerMessage);
            messageView.setText(message);
        }

        Log.d(TAG, "отправка сообщения в поле");
        messagesField.addView(messagePattern);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
