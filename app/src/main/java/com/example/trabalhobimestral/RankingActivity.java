package com.example.trabalhobimestral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class RankingActivity extends AppCompatActivity {
    private Button buttonVoltar;
    String nome_usuario;
    private TextView textViewScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Intent intent = getIntent();
        nome_usuario = intent.getStringExtra("nome_usuario");

        textViewScore = findViewById(R.id.textViewScore);
        buttonVoltar=findViewById(R.id.buttonVoltar);

        lerTudo();
    }

    public void lerTudo(){
        SharedPreferences sharedPreferences = getSharedPreferences("dados",MODE_PRIVATE);
        if (sharedPreferences.contains("nome_usuario")){
            if (sharedPreferences.getString("nome_usuario","sem nome").equals(nome_usuario) ){
                String texto="";
                Map<String, ?> prefsMap = sharedPreferences.getAll();
                ArrayList<String> lista = new ArrayList<String>((Collection<? extends String>) prefsMap.values());

                for (Map.Entry<String, ?> entry: prefsMap.entrySet()) {
                    Log.v("SharedPreferences", entry.getKey() + ":" +
                            entry.getValue().toString());
                    Toast.makeText(this, entry.getKey() + ":" +
                            entry.getValue().toString(), Toast.LENGTH_SHORT).show();
                    texto+=entry.getKey() + ":" +
                            entry.getValue().toString()+"\n"+ System.getProperty("line.separator");
                    ;
                }

                textViewScore.setText(texto);

            }

        }else{
            Toast.makeText(this, "ERRO no arquivo", Toast.LENGTH_SHORT).show();
        }//if




    }//lerTudo
    public void cliqueVoltar(View view){
        chamaMenuActivity();
    }//cliqueVoltar

    private void chamaMenuActivity(){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("nome_usuario", nome_usuario);
        startActivity(intent);
    }//chamaMenuActivity

    @Override
    public void onBackPressed(){ //Botão BACK padrão do android

       chamaMenuActivity();//O efeito ao ser pressionado do botão (no caso abre a activity)
        finishAffinity(); //Método para matar a activity e não deixa-lá indexada na pilhagem
        return;
    }

}