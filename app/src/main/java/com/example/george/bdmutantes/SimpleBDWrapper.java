package com.example.george.bdmutantes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SimpleBDWrapper extends SQLiteOpenHelper {
    public static final String MUTANTES = "Mutantes";
    public static final String MUTANTE_ID = "_id";
    public static final String MUTANTE_NAME = "_name";
    public static final String MUTANTE_SKILL = "_skill";

    private static final String DATABASE_NAME = "Mutantes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + MUTANTES + "(" + MUTANTE_ID +
            " integer primary key autoincrement, " + MUTANTE_NAME + " text not null, " + MUTANTE_SKILL +
            " text not null);";

    public SimpleBDWrapper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int ondVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + MUTANTES);
        onCreate(db);
    }
}
