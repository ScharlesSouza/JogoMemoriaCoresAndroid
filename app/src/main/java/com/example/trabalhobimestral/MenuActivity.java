package com.example.trabalhobimestral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private TextView textViewNome;
    private Button buttonRanking;
    private RadioButton rButtonCores, rButtonNumeros;
    private ProgressBar progressBar;
    private int i;

    private String[] cores = {"verde", "vermelha", "azul"};
    private Boolean[] respostas = {false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String nome_usuario = intent.getStringExtra("nome_usuario");

        textViewNome = findViewById(R.id.textViewNome);
        buttonRanking = findViewById(R.id.buttonRanking);
        textViewNome.setText(nome_usuario);

        rButtonCores = findViewById(R.id.rButtonCores);
        rButtonNumeros = findViewById(R.id.rButtonNumero);
        progressBar = findViewById(R.id.progressBar);

        rButtonCores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreDesafioCores();
            }

        });
        rButtonNumeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreDesafioNumeros();
            }

        });


    }//onCreate

    public void clickRanking(View view) {

        executarProgressBar();

    }//clicarRanking

    public void abreRanking(){
        Intent intent = new Intent(this, RankingActivity.class);
        intent.putExtra("nome_usuario", textViewNome.getText().toString());
        startActivity(intent);
    }//abreRanking

    private void abreDesafioNumeros() {
        Intent intent = new Intent(this, NumerosActivity.class);
        intent.putExtra("nome_usuario", textViewNome.getText().toString());
        startActivity(intent);
    }//abreDesafioNumeros

    private void abreDesafioCores() {
        Intent intent = new Intent(this, CoresActivity.class);
        intent.putExtra("nome_usuario", textViewNome.getText().toString());
        startActivity(intent);
    }//abreDesafioCores


    private void executarProgressBar(){
        i=0;
        Handler handler = new Handler();
        progressBar.setVisibility(View.VISIBLE);
        i=progressBar.getProgress();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(i<100){
                    i+=10;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(i);
                            if (i>=100){
                                abreRanking();
                            }//if
                        }//run
                    });//rundler.post
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException erro){

                    }
                }
            }//run
        }).start();//thread

    }//executaProgressBar

    private void abrirDesafio(){
        if(rButtonNumeros.isChecked()){

        }else if(rButtonCores.isChecked()){

        }
    }//abrirDesafio


    public Boolean ler(String nome){
        SharedPreferences sharedPreferences = getSharedPreferences("dados",MODE_PRIVATE);
        if (sharedPreferences.contains("nome_usuario") ) {
            if (sharedPreferences.getString("nome_usuario","sem nome").equals(nome) ){
                //Intent intent = new Intent(this, TerceiraActivity.class);
                //startActivity(intent);
                return true;
            }else
                return false;

        }else{
            return false;
        }//if
    }//ler
}//classe