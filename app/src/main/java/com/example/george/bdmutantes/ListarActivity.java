package com.example.george.bdmutantes;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListarActivity extends ListActivity {
    private MutanteOperations mutanteOperation;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_listar);
        mutanteOperation = new MutanteOperations(this);
        mutanteOperation.open();

        List values = mutanteOperation.getAllMutantes();
        //list.findViewById(R.id.list);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,values);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        Intent it = new Intent(this, DetailActivity.class);
        it.putExtra("mutanteId", position);
        startActivity(it);
    }
}
