package com.example.george.bdmutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    private MutanteOperations mutanteOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        int mutanteId = 3;
        Intent it = getIntent();
        mutanteOperation = new MutanteOperations(this);
        mutanteOperation.open();
        EditText nomeMutante = findViewById(R.id.etNome);
        EditText habilidadeMutante = findViewById(R.id.etHabilidades);

        if(it!=null){
            mutanteId = it.getIntExtra("mutanteId",3);
        }
        if (mutanteId == 3){
            nomeMutante.setText("");
            habilidadeMutante.setText("");
            Toast.makeText(this, "Mutante não encontrado", Toast.LENGTH_SHORT).show();
        } else {
            Bundle params = it.getExtras();
            mutanteId = params.getInt("mutanteId");
            MutanteOperations mutanteOperations = new MutanteOperations(this);
            Mutante mutante = mutanteOperations.getMutanteById(mutanteId);
            nomeMutante.setText(mutante.getName());
            habilidadeMutante.setText(mutante.getHabilidades());
        }
    }

}
