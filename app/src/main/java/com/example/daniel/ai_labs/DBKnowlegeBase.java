package com.example.daniel.ai_labs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Daniel on 29.09.2017.
 */

final public class DBKnowlegeBase extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "ExpertKnowlegeBase.db";
    private static final String TABLE_NAME = "QuestionsAndAnswers";
    private static final String KEY_ID = "id";
    private static final String KEY_CHOICE = "choise";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_VARIANT1 = "variant1";
    private static final String KEY_VARIANT2 = "variant2";
    private static final String KEY_VARIANT3 = "variant3";

    public DBKnowlegeBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE "
                + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_CHOICE + " TEXT,"
                + KEY_QUESTION + " TEXT,"
                + KEY_VARIANT1 + " TEXT,"
                + KEY_VARIANT2 + " TEXT,"
                + KEY_VARIANT3 + " TEXT "
                + ")";

        sqLiteDatabase.execSQL(CREATE_TABLE);

        ContentValues values = new ContentValues();;
        values.put(KEY_CHOICE, "000");
        values.put(KEY_QUESTION, "Как дела?");
        values.put(KEY_VARIANT1, "Отлично");
        values.put(KEY_VARIANT2, "Хреново");
        values.put(KEY_VARIANT3, "Норм");
        sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public String[] getData(int[] userDecisions) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] result = {"n", "u", "l", "l"};

        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + KEY_CHOICE + " LIKE '"
                + userDecisions[0] + "" + userDecisions[1] + "" + userDecisions[2] + "'";

        //if adding month already in DB, return cursor
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            for (int i = 0; i <= userDecisions.length; i++)
                result[i] = cursor.getString(i+2);
        }

        return result;
    }
}
