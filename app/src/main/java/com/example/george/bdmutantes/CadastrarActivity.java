package com.example.george.bdmutantes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class CadastrarActivity extends AppCompatActivity {

    private MutanteOperations mutanteOperation;
    EditText etNome, etHabilidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        mutanteOperation = new MutanteOperations(this);
        mutanteOperation.open();
    }

    //Adicionar um mutante no banco de dados
    public void addMutante(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        try {
            etNome = findViewById(R.id.etNome);
            etHabilidades = findViewById(R.id.etHabilidades);

            //Verifica se os campos não estão vazios
            if (!(etNome.getText().toString().equals("") || etHabilidades.getText().toString().equals(""))) {
                //Carrega a lista de mutantes cadastrados
                List<Mutante> mutantes = mutanteOperation.getAllMutantes();
                //Percorre a lista, se o mutante já estiver cadastrado, abre uma exceção
                for (Mutante m : mutantes) {
                    if (m.getName().equalsIgnoreCase(etNome.getText().toString())) {
                        String e = "Mutante já cadastrado.";
                        throw new Exception(e);
                    }
                }
            //Se os campos estiverem vazios, abre uma exceção
            } else {
                String e = "Os campos nome e habilidades não podem ser vazios.";
                throw new Exception(e);
            }
            //Se não cair na exceção, quer dizer que o mutante não existe e cadastra o novo mutante
            mutanteOperation.addMutante(etNome.getText().toString(), etHabilidades.getText().toString());
            //AlertDialog positivo
            builder.setTitle("Sucesso")
                    .setMessage("Mutante cadastrado com sucesso.")
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

    @Override
    protected void onResume() {
        mutanteOperation.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mutanteOperation.close();
        super.onPause();
    }
}
