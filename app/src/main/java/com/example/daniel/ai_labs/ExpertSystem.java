package com.example.daniel.ai_labs;

import android.content.Context;

/**
 * Created by Daniel on 29.09.2017.
 */

final public class ExpertSystem {

    private static ExpertSystem pointer = new ExpertSystem();
    private int[] mUserDecisions = {0, 0, 0};
    private static DBKnowlegeBase mKnowlegeBase;

    public static ExpertSystem getInstance(Context context) {
        mKnowlegeBase = new DBKnowlegeBase(context);
        return pointer;
    }

    public void clearDecisions() {
        for (int i = 0; i < mUserDecisions.length; i++)
            mUserDecisions[i] = 0;
    }

    public void addDecision(int userChoise) {
        for (int i = 0; i < mUserDecisions.length; i++)
            if (mUserDecisions[i] == 0) {
                mUserDecisions[i] = userChoise;
                return;
            }
    }

    public String[] getNewVariants() {
        String[] res;
        res =  mKnowlegeBase.getData(mUserDecisions);
        return res;
    }

    public void Destroy() {
        mKnowlegeBase.Destroy();
    }
}
