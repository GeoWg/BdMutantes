package com.example.george.bdmutantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


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

    public Mutante addMutante(String name, String skill){

        ContentValues values = new ContentValues();

        values.put(SimpleBDWrapper.MUTANTE_NAME, name);
        values.put(SimpleBDWrapper.MUTANTE_SKILL, skill);

        long mutanteId = database.insert(SimpleBDWrapper.MUTANTES, null, values);

        Cursor cursor = database.query(SimpleBDWrapper.MUTANTES, MUTANTE_TABLE_COLUMNS,
                SimpleBDWrapper.MUTANTE_ID + " = " + mutanteId, null, null, null, null);
        cursor.moveToFirst();
        Mutante newComment = parseMutante(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteMutante(int id){
        System.out.println("Removido id " + id);
        database.delete(SimpleBDWrapper.MUTANTES,SimpleBDWrapper.MUTANTE_ID + " = " + id, null);
    }

    public List getAllMutantes(){
        List mutantes = new ArrayList();

        Cursor cursor = database.query(SimpleBDWrapper.MUTANTES, MUTANTE_TABLE_COLUMNS, null,null,null,null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Mutante mutante = parseMutante(cursor);
            mutantes.add(mutante);
            cursor.moveToNext();
        }
        cursor.close();
        return mutantes;
    }

    public Mutante getMutanteById(int id){
        Cursor cursor = database.query(SimpleBDWrapper.MUTANTES, MUTANTE_TABLE_COLUMNS, SimpleBDWrapper.MUTANTE_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();

        Mutante mid = parseMutante(cursor);
        cursor.close();
        return mid;
    }

    private Mutante parseMutante(Cursor cursor){
        Mutante mutante = new Mutante();
        mutante.setId(cursor.getInt(0));
        mutante.setName(cursor.getString(1));
        mutante.setHabilidades(cursor.getString(2));
        return mutante;
    }

}
