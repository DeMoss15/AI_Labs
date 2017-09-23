package com.example.daniel.ai_labs;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Daniel on 20.09.2017.
 */

public class ChatDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "chat.db";
    private static final String TABLE_NAME = "messages";
    private static final String KEY_ID = "ID";
    private static final String KEY_AUTOR = "AUTOR";
    private static final String KEY_MESSAGE = "MESSAGE";

    public ChatDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CHAT_TABLE = "CREATE TABLE "
                + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_AUTOR + " TEXT,"
                + KEY_MESSAGE + " TEXT "
                + ")";

        sqLiteDatabase.execSQL(CREATE_CHAT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public void addMessage(String autor, String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_AUTOR, autor);
        values.put(KEY_MESSAGE, message);

        db.insert(TABLE_NAME, null, values);
    }
}
