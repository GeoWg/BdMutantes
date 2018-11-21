package com.example.george.bdmutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //int aux = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cadastrarMutante(View view){
        //Envia auxiliar com 0 para saber que veio da Main
        Intent it = new Intent(this, CadastrarActivity.class);
        //Bundle params = new Bundle();
        //params.putInt("aux", aux);
        //it.putExtras(params);
        startActivity(it);
    }

    public void listarMutante(View view){
        Intent it = new Intent(this, ListarActivity.class);
        startActivity(it);
    }

    public void pesquisarMutante(View view){
        Intent it = new Intent(this, PesquisarActivity.class);
        startActivity(it);
    }

    public void fecharApp(View view){
        System.exit(0);
    }
}
