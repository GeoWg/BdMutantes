package com.example.george.bdmutantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MutanteOperations {
    private SimpleBDWrapper dbHelper;
    private String[] MUTANTE_TABLE_COLUMNS = { SimpleBDWrapper.MUTANTE_ID, SimpleBDWrapper.MUTANTE_NAME, SimpleBDWrapper.MUTANTE_SKILL};
    private SQLiteDatabase database;

    public MutanteOperations(Context context){
        dbHelper = new SimpleBDWrapper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    /*public Mutante addMutante(String name, String skill){

        ContentValues values = new ContentValues();

        values.put(SimpleBDWrapper.MUTANTE_NAME, name);
        values.put(SimpleBDWrapper.MUTANTE_SKILL, skill);

        int mutanteId = database.insert(SimpleBDWrapper.MUTANTES, null, values);

        Cursor cursor = database.query(SimpleBDWrapper.MUTANTES, MUTANTE_TABLE_COLUMNS,
                SimpleBDWrapper.MUTANTE_ID + " = " + mutanteId,)
    }*/
}
