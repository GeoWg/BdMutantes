package com.example.george.bdmutantes;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PesquisarListaActivity extends ListActivity {

    private MutanteOperations mutanteOperation;
    ArrayList<String> names = new ArrayList<>();
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_listar);
        mutanteOperation = new MutanteOperations(this);
        mutanteOperation.open();

        Intent it = getIntent();
        Bundle params = it.getExtras();
        names = params.getStringArrayList("names");

        //Cria uma lista com os mutantes recebidos da PesquisarActivity
        List values = names;
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
