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

        ContentValues values = new ContentValues();
        values.put(KEY_CHOICE, "000");
        values.put(KEY_QUESTION, "Какой жанр вам нравится?");
        values.put(KEY_VARIANT1, "Метал");
        values.put(KEY_VARIANT2, "Джаз/Блюз");
        values.put(KEY_VARIANT3, "Классика");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "100");
        values.put(KEY_QUESTION, "Какой вокал вам нравится?");
        values.put(KEY_VARIANT1, "Чистый");
        values.put(KEY_VARIANT2, "Экстрим");
        values.put(KEY_VARIANT3, "Смешанный");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "110");
        values.put(KEY_QUESTION, "Какой язык исполнения предпочтительней?");
        values.put(KEY_VARIANT1, "Русский");
        values.put(KEY_VARIANT2, "Украинский");
        values.put(KEY_VARIANT3, "Английский");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "120");
        values.put(KEY_QUESTION, "Какой жанр вы любите?");
        values.put(KEY_VARIANT1, "Thrash metal");
        values.put(KEY_VARIANT2, "Death metal");
        values.put(KEY_VARIANT3, "Doom metal");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "130");
        values.put(KEY_QUESTION, "Какой жанр вы любите?");
        values.put(KEY_VARIANT1, "Melodic Death Metal");
        values.put(KEY_VARIANT2, "Melodic Metalcore");
        values.put(KEY_VARIANT3, "Alternative Metal");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "111");
        values.put(KEY_QUESTION, "Lumen");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "112");
        values.put(KEY_QUESTION, "Океан Ельзи");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "113");
        values.put(KEY_QUESTION, "Metallica");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "121");
        values.put(KEY_QUESTION, "Slayer");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "122");
        values.put(KEY_QUESTION, "Obituary");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "123");
        values.put(KEY_QUESTION, "Grave Worm");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "131");
        values.put(KEY_QUESTION, "Bullet for my Valentine");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "132");
        values.put(KEY_QUESTION, "Trivium");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "133");
        values.put(KEY_QUESTION, "Slipknot");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "200");
        values.put(KEY_QUESTION, "Какой темп музыки для вас предпочтительней?");
        values.put(KEY_VARIANT1, "Быстрый");
        values.put(KEY_VARIANT2, "Медленный");
        values.put(KEY_VARIANT3, "Средний");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();;
        values.put(KEY_CHOICE, "210 220 230");
        values.put(KEY_QUESTION, "Какой инструмент задаёт лейтмотив вашим любимым песням?");
        values.put(KEY_VARIANT1, "Саксофон");
        values.put(KEY_VARIANT2, "Гитара");
        values.put(KEY_VARIANT3, "Вокал");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "211");
        values.put(KEY_QUESTION, "Dave Koz");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "212");
        values.put(KEY_QUESTION, "Может вам все-таки метал нравится?)");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();;
        values.put(KEY_CHOICE, "213");
        values.put(KEY_QUESTION, "Alannah Myles");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "221");
        values.put(KEY_QUESTION, "Everette Harp");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "222");
        values.put(KEY_QUESTION, "Kelly Joe Phelps");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "223");
        values.put(KEY_QUESTION, "Beth Hart");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "231");
        values.put(KEY_QUESTION, "Средний саксофон");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "232");
        values.put(KEY_QUESTION, "Keb' Mo'");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "233");
        values.put(KEY_QUESTION, "JD McPherson");
        values.put(KEY_VARIANT1, "");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "300");
        values.put(KEY_QUESTION, "Классика");
        values.put(KEY_VARIANT1, "1");
        values.put(KEY_VARIANT2, "2");
        values.put(KEY_VARIANT3, "3");
        sqLiteDatabase.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_CHOICE, "310 320 330");
        values.put(KEY_QUESTION, "Классика 2");
        values.put(KEY_VARIANT1, "Вариант");
        values.put(KEY_VARIANT2, "");
        values.put(KEY_VARIANT3, "");
        sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public String[] getData(int[] userDecisions) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] result = {"" +
                "ERROR\nPage undefined", "", "", ""};

        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + KEY_CHOICE + " LIKE '%"
                + userDecisions[0] + "" + userDecisions[1] + "" + userDecisions[2] + "%'";

        //if adding month already in DB, return cursor
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            for (int i = 0; i <= userDecisions.length; i++)
                result[i] = cursor.getString(i+2);
        }

        return result;
    }


    public void Destroy() {
        onUpgrade(this.getWritableDatabase(), 1, 1);
    }
}
