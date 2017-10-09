package com.example.daniel.ai_labs.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.daniel.ai_labs.ExpertSystem;
import com.example.daniel.ai_labs.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Lab4.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Lab4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Lab4 extends Fragment {

    private Button mVariant1;
    private Button mVariant2;
    private Button mVariant3;
    private Button mClear;
    private TextView mQuestion;
    private ExpertSystem mMyExpert;

    private OnFragmentInteractionListener mListener;

    public Lab4() {
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
        return inflater.inflate(R.layout.fragment_lab4, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mMyExpert = ExpertSystem.getInstance(getActivity().getBaseContext());
        mVariant1 = (Button) view.findViewById(R.id.answer1);
        mVariant2 = (Button) view.findViewById(R.id.answer2);
        mVariant3 = (Button) view.findViewById(R.id.answer3);
        mClear = (Button) view.findViewById(R.id.clear);
        mQuestion = (TextView) view.findViewById(R.id.questionView);

        changeText(0);
        mMyExpert.clearDecisions();

        View.OnClickListener OnCLickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int decision = 0;
                if (v == null || !(v instanceof TextView)) {
                    return; //нет свойства text
                }
                switch (v.getId()) {
                    case R.id.answer1:
                        decision = 1;
                        break;
                    case R.id.answer2:
                        decision = 2;
                        break;
                    case R.id.answer3:
                        decision = 3;
                        break;
                    case R.id.clear:
                        mMyExpert.clearDecisions();
                        break;
                }
                changeText(decision);
            }
        };

        mVariant1.setOnClickListener(OnCLickListener);
        mVariant2.setOnClickListener(OnCLickListener);
        mVariant3.setOnClickListener(OnCLickListener);
        mClear.setOnClickListener(OnCLickListener);
    }

    private void changeText(int decision) {
        mMyExpert.addDecision(decision);
        String[] text = mMyExpert.getNewVariants();
        if (text != null && text.length == 4) {
            mQuestion.setText(text[0]);
            mVariant1.setText(text[1]);
            mVariant2.setText(text[2]);
            mVariant3.setText(text[3]);
        }

        mVariant1.setEnabled(!mVariant1.getText().toString().equals(""));
        mVariant2.setEnabled(!mVariant2.getText().toString().equals(""));
        mVariant3.setEnabled(!mVariant3.getText().toString().equals(""));
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

    @Override
    public void onDestroy() {
        mMyExpert.Destroy();
        super.onDestroy();
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
