package com.example.george.bdmutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PesquisarActivity extends AppCompatActivity {

    EditText text;
    private MutanteOperations mutanteOperation;
    ArrayList<Mutante> mutantesName = new ArrayList<>();
    ArrayList<Mutante> mutantesSkill = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
    }

    protected void onStart() {
        super.onStart();
        names = new ArrayList<>();
    }

    public void pesquisar(View view) {
        text = findViewById(R.id.etPesquisar);
        if (text.length() == 0){
            Toast.makeText(this, "A pesquisa não pode ser vazia", Toast.LENGTH_SHORT).show();
        } else {
            mutanteOperation = new MutanteOperations(this);
            mutanteOperation.open();

            mutantesName = mutanteOperation.getMutanteByName(text.getText().toString());
            mutantesSkill = mutanteOperation.getMutanteBySkill(text.getText().toString());
            List<Mutante> mutantes = new ArrayList<>();
            mutantes.addAll(mutantesName);
            mutantes.addAll(mutantesSkill);
            for (Mutante m : mutantes){
                names.add(m.getName());
            }
            Intent it = new Intent(this, PesquisarListaActivity.class);
            Bundle params = new Bundle();
            params.putStringArrayList("names", names);
            it.putExtras(params);
            startActivity(it);
        }
    }
}