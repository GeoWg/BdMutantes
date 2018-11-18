package com.example.george.bdmutantes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarActivity extends AppCompatActivity {
    private MutanteOperations mutanteOperation;
    EditText etNome, etHabilidades;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        mutanteOperation = new MutanteOperations(this);
        mutanteOperation.open();

    }

    public void addMutante(View view){
        EditText nome = findViewById(R.id.etNome);
        EditText habilidades = findViewById(R.id.etHabilidades);
        Mutante mutanteNome = mutanteOperation.addMutante(nome.getText().toString(), habilidades.getText().toString());
    }
    @Override
    protected void onResume(){
        mutanteOperation.open();
        super.onResume();
    }

    @Override
    protected void onPause(){
        mutanteOperation.close();
        super.onPause();
    }
}
