package com.example.trabalhobimestral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private TextView textViewNome;
    private Button buttonRanking;
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



    }//onCreate


    public void clickRanking(View view) {



            Intent intent = new Intent(this, RankingActivity.class);
            intent.putExtra("nome_usuario", textViewNome.getText().toString());
            startActivity(intent);


    }//clicarRanking

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