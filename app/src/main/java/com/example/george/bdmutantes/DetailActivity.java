package com.example.george.bdmutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    private MutanteOperations mutanteOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent it = getIntent();
        mutanteOperation = new MutanteOperations(this);
        mutanteOperation.open();
        Bundle params = it.getExtras();
        int mutanteId = params.getInt("mutanteId");
        Mutante mutante = mutanteOperation.getMutanteById(mutanteId);
        EditText nomeMutante = findViewById(R.id.etNome);
        EditText habilidadeMutante = findViewById(R.id.etHabilidades);

        if(it!=null){
            mutanteId = it.getIntExtra("mutanteId",3);
        }
        if (mutanteId == 0){
            nomeMutante.setText("");
            habilidadeMutante.setText("");
            Toast.makeText(this, "Mutante não encontrado", Toast.LENGTH_SHORT).show();
        } else {

            nomeMutante.setText(mutante.getName());
            habilidadeMutante.setText(mutante.getHabilidades());
        }
    }

    public void alteraMutante(View view){

    }

    public void deleteMutante(View view){
        Intent it = getIntent();
        Bundle params = it.getExtras();
        int mutanteId = params.getInt("mutanteId");
        mutanteOperation.deleteMutante(mutanteId);
        Toast.makeText(this, "O mutante foi deletado!", Toast.LENGTH_SHORT).show();
        Intent it2 = new Intent(this, ListarActivity.class);
        startActivity(it2);
    }

}

