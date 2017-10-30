package com.example.daniel.ai_labs.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniel.ai_labs.Neuron;
import com.example.daniel.ai_labs.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Lab5 extends Fragment {

    @BindView(R.id.button_train)
    Button mButtonTrain;
    @BindView(R.id.button_reset)
    Button mButtonReset;
    @BindView(R.id.tv_iterations)
    TextView mTextViewInteraction;
    @BindView(R.id.tv_result)
    TextView mTextViewResult;
    @BindView(R.id.tv_sum)
    TextView mTextViewSum;
    @BindView(R.id.tv_weights_after)
    TextView mTextViewWeightAfter;
    @BindView(R.id.tv_weights_before)
    TextView mTextViewWeightBefore;
    @BindView(R.id.input_speed)
    TextView mTextViewSpeed;
    @BindView(R.id.input_vector)
    TextView mTextViewVector;
    @BindView(R.id.spinner_expected_results)
    Spinner mSpinnerExpectedResults;
    @BindView(R.id.spinner_vectors)
    Spinner mSpinnerVectors;

    String[] mVectors = {"not chosen",
            "1 1 1 1 0 0 0", "1 1 1 0 0 0 0", "1 1 1 1 0 1 0",
            "0 0 0 0 1 1 1", "0 0 0 1 1 1 1", "0 0 0 0 1 0 1"};

    Integer[] mResults = {0, 1};
    Neuron mMyNeuron = Neuron.getInstance();
    String mUsersInputVector = "";
    Double mUsersInputSpeed = null;

    public Lab5() {
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
        View view = inflater.inflate(R.layout.fragment_lab5, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //setting adapter for vector spinner
        ArrayAdapter<String> vectorsToSpinnerAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mVectors);
        vectorsToSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerVectors.setAdapter(vectorsToSpinnerAdapter);
        mSpinnerVectors.setPrompt("Vectors");

        //setting adapter for results spinner
        ArrayAdapter<Integer> resultsToSpinnerAdapter =
                new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_spinner_item, mResults);
        resultsToSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerExpectedResults.setAdapter(resultsToSpinnerAdapter);
        mSpinnerExpectedResults.setPrompt("Results");

        //setting template for input fields
        mTextViewVector.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                mSpinnerVectors.setSelection((int)mSpinnerVectors.getItemIdAtPosition(0));
                String text = textView
                        .getText()
                        .toString()
                        .replaceAll("\\D", "");                       ;

                if (text.length() != 7){
                    showToast("Wrong number of values!");
                } else {
                    text = text.replace("", " ");
                    text = text.substring(1, text.length() - 1);
                    mUsersInputVector = text;
                }

                textView.setText(text);
                return false;
            }
        });

        mTextViewSpeed.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (textView.getText().toString().isEmpty()) {
                    return false;
                } else {
                    double usersSpeed = Double.parseDouble(textView.getText().toString());

                    if (usersSpeed > 1.0d) {
                        showToast("Your speed makes training unnecessary!\nTry values under 1.0!");
                    } else {
                        mUsersInputSpeed = usersSpeed;
                    }
                }
                return false;
            }
        });

        mSpinnerVectors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0){
                    mTextViewVector.setText("");
                    mUsersInputVector = (String)mSpinnerVectors.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //setting on click action
        View.OnClickListener myOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == null || !(view instanceof TextView)) {
                    return; //нет свойства text
                }
                switch (view.getId()) {
                    case R.id.button_train: {
                        //TODO: validate data in input fields
                        if (mUsersInputVector.equals("") || mUsersInputSpeed == null){
                            showToast("Not enough values to train\nCommit your inputs, please");
                            break;
                        }

                        int[] vect = new int[7];
                        String[] strVect;

                        strVect = mUsersInputVector.split("\\s");

                        //parsing String[] to int[]
                        for (int i = 0; i < strVect.length; i++) {
                            vect[i] = Integer.parseInt(strVect[i]);
                        }

                        mMyNeuron.Train(
                                (Integer)mSpinnerExpectedResults.getSelectedItem(),
                                mUsersInputSpeed,
                                vect);

                        setOutputData(
                                mMyNeuron.getResultOfTraining(),
                                mMyNeuron.getNumOfIterations(),
                                mMyNeuron.getWeightsBefore(),
                                mMyNeuron.getWeightsAfter(),
                                mMyNeuron.getSum());

                        showToast("Neuron is trained");
                        break;
                    }
                    case R.id.button_reset:{
                        mMyNeuron.reset();
                        setOutputData(
                                mMyNeuron.getResultOfTraining(),
                                mMyNeuron.getNumOfIterations(),
                                mMyNeuron.getWeightsBefore(),
                                mMyNeuron.getWeightsAfter(),
                                mMyNeuron.getSum());

                        showToast("Neuron reset");
                        break;
                    }
                }
            }

            private void setOutputData(int result, int numberOfIterations,
                                       double[] weightsBefore, double[] weightsAfter,
                                       double sum) {
                mTextViewResult.setText(String.format(Locale.ENGLISH, "%d \n", result));
                mTextViewSum.setText(String.format(Locale.ENGLISH, "%.3f \n", sum));
                mTextViewInteraction.setText(String.format(Locale.ENGLISH, "%d \n", numberOfIterations));
                StringBuilder textWB = new StringBuilder();
                StringBuilder textWA = new StringBuilder();
                for (int i = 0; i < weightsBefore.length; i++) {
                    textWA.append(String.format(Locale.ENGLISH, "%.3f \n", weightsAfter[i]));
                    textWB.append(String.format(Locale.ENGLISH, "%.3f \n", weightsBefore[i]));
                }
                mTextViewWeightAfter.setText(textWA.toString());
                mTextViewWeightBefore.setText(textWB.toString());
            }
        };

        mButtonTrain.setOnClickListener(myOnClickListener);
        mButtonReset.setOnClickListener(myOnClickListener);
    }

    private void showToast(String s){
        Toast toast = Toast.makeText(getContext(), s, Toast.LENGTH_SHORT);
        toast.show();
    }
}
