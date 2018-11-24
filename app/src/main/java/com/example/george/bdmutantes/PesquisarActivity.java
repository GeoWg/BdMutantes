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

    //Recarrega a lista sempre que abrir a Activity
    protected void onStart() {
        super.onStart();
        names = new ArrayList<>();
    }

    public void pesquisar(View view) {
        text = findViewById(R.id.etPesquisar);
        //Verifica se o campo de pesquisa está vazio
        if (text.length() == 0){
            Toast.makeText(this, "A pesquisa não pode ser vazia", Toast.LENGTH_SHORT).show();
        } else {
            mutanteOperation = new MutanteOperations(this);
            mutanteOperation.open();

            //Cria uma lista com os mutantes com o exato mesmo nome
            //Cria uma lista com os mutantes que tenham uma das habilidades
            mutantesName = mutanteOperation.getMutanteByName(text.getText().toString());
            mutantesSkill = mutanteOperation.getMutanteBySkill(text.getText().toString());
            //Cria uma lista para armazenar todos os mutantes que atendem a pesquisa
            List<Mutante> mutantes = new ArrayList<>();
            //Primeiro adiciona todos os mutantes que a pesquisa equivale ao nome, depois adicioona os mutantes que a pesquisa equivale a habilidade, caso não seja repetido
            mutantes.addAll(mutantesName);
            for (Mutante m1 : mutantes){
                for (Mutante m2 :mutantesSkill){
                    if (m1.getName() != m2.getName()){
                        mutantes.add(m2);
                    }
                }
            }
            //Captura apenas o nome desses mutantes e adiciona em outra lista
            for (Mutante m : mutantes){
                names.add(m.getName());
            }
            //Envia a lista pra PesquisarListaActivity
            Intent it = new Intent(this, PesquisarListaActivity.class);
            Bundle params = new Bundle();
            params.putStringArrayList("names", names);
            it.putExtras(params);
            startActivity(it);
        }
    }
}