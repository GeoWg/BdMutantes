package com.example.george.bdmutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cadastrarMutante(View view){
        Intent it = new Intent(this, CadastrarActivity.class);
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
