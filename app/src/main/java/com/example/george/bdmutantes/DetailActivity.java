package com.example.george.bdmutantes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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

        if (it != null) {
            mutanteId = it.getIntExtra("mutanteId", 3);
        }
        if (mutanteId == 0) {
            nomeMutante.setText("");
            habilidadeMutante.setText("");
            Toast.makeText(this, "Mutante não encontrado", Toast.LENGTH_SHORT).show();
        } else {

            nomeMutante.setText(mutante.getName());
            habilidadeMutante.setText(mutante.getHabilidades());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent it = new Intent(this, ListarActivity.class);
            finish();
            startActivity(it);
        }
        return super.onKeyDown(keyCode, event);
    }

    public void alteraMutante(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        EditText nomeMutante = findViewById(R.id.etNome);
        EditText habilidadeMutante = findViewById(R.id.etHabilidades);
        Intent it = getIntent();
        Bundle params = it.getExtras();
        int mutanteId = params.getInt("mutanteId");
        try {
            if (!(nomeMutante.getText().toString().equals("") || habilidadeMutante.getText().toString().equals(""))) {
            } else {
                String e = "Os campos nome e habilidades não podem ser vazios.";
                throw new Exception(e);
            }
            mutanteOperation.updateMutante(nomeMutante.getText().toString(), habilidadeMutante.getText().toString(), mutanteId);

            builder.setTitle("Sucesso")
                    .setMessage("Mutante atualizado com sucesso.")
                    .setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            });
            builder.show();
        } catch (Exception e) {
            //AlertDialog negativo
            builder.setTitle("Erro")
                    .setMessage(e.getMessage())
                    .setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            builder.show();
        }
    }

    public void deleteMutante(View view) {
        Intent it = getIntent();
        Bundle params = it.getExtras();
        int mutanteId = params.getInt("mutanteId");
        mutanteOperation.deleteMutante(mutanteId);
        Toast.makeText(this, "O mutante foi deletado!", Toast.LENGTH_SHORT).show();
        Intent it2 = new Intent(this, ListarActivity.class);
        finish();
        startActivity(it2);
    }
}

