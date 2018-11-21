package com.example.george.bdmutantes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class CadastrarActivity extends AppCompatActivity {

    private MutanteOperations mutanteOperation;
    EditText etNome, etHabilidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        etNome = findViewById(R.id.etNome);
        etHabilidades = findViewById(R.id.etHabilidades);

        //Depois de iniciar a Activity, se vier da main, os campos vem vazios, se vier da detail, vem preenchidos

        Intent it = getIntent();
        Bundle params = it.getExtras();
        int id = params.getInt("idMutante");

        mutanteOperation = new MutanteOperations(this);
        mutanteOperation.open();

        if(id != 0) {
            //etHabilidades.setText(mutanteOperation.getMutanteById(id).getHabilidade(), EditText.BufferType.EDITABLE);
            //nome.setText(mutanteOperation.getMutanteById(id).getNome(), EditText.BufferType.EDITABLE);
        }

    }

    public void addMutante(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        try{
            etNome = findViewById(R.id.etNome);
            etHabilidades = findViewById(R.id.etHabilidades);

            Intent it = getIntent();
            Bundle params = it.getExtras();
            int aux = params.getInt("aux");
            int id = params.getInt("idMutante");

            //Se os campos não estiverem vazios
            if(!(etNome.getText().toString().equals("") || etHabilidades.getText().toString().equals(""))){
                //Se veio da Main
                if(aux == 0){
                    List<Mutante> mutantes = mutanteOperation.getAllMutantes();
                    for (Mutante m : mutantes){
                        //Se o valor do campo nome for igual ao nome do mutante que está no for, chama a exceção negativa
                        if (m.getName().equalsIgnoreCase(etNome.getText().toString())){
                            String e = "Mutante já cadastrado.";
                            throw  new Exception(e);
                        }
                    }
                    //Se não cair na exceção, quer dizer que o mutante não existe e pode ser cadastrado
                    mutanteOperation.addMutante(etNome.getText().toString(), etHabilidades.getText().toString());
                }
                //Se veio da Detail
                if (aux == 1){
                    List<Mutante> mutantes = mutanteOperation.getAllMutantes();
                    for (Mutante m : mutantes){
                        //Se o valor do campo nome for igual ao nome do mutante que está no for e o campo id não, chama a exceção negativa
                        if (m.getName().equalsIgnoreCase(etNome.getText().toString()) && m.getId() != id){
                            String e = "Mutante já cadastrado.";
                            throw  new Exception(e);
                        }
                    }
                    //Se não cair na exceção, quer dizer que o mutante não existe e pode ser cadastrado
                    mutanteOperation.addMutante(etNome.getText().toString(), etHabilidades.getText().toString());
                }
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
            }
        } catch (Exception e){
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
