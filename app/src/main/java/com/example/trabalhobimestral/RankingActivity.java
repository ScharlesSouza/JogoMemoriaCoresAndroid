package com.example.trabalhobimestral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RankingActivity extends AppCompatActivity {
    private Button buttonVoltar;
    String nome_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Intent intent = getIntent();
        nome_usuario = intent.getStringExtra("nome_usuario");

        buttonVoltar=findViewById(R.id.buttonVoltar);
    }
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