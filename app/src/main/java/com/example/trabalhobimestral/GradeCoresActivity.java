package com.example.trabalhobimestral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class GradeCoresActivity extends AppCompatActivity {
    private Button botaoRosa,botaoVerde,botaoRoxo,botaoMarron,botaoAmarelo,botaoAzul,botaoPreto,botaoVermelho,botaolaranjado;
    private int i;
    private View view;
    private String[] cores = {"rosa", "azul", "vermelho", "verde", "preto", "amarelo", "roxo", "marrom", "laranjado"};
    String cor, corAnterior;
    private Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_cores);

        botaoAmarelo = findViewById(R.id.botao1);
        botaoAzul = findViewById(R.id.botao2);
        botaoPreto = findViewById(R.id.botao3);
        botaoMarron = findViewById(R.id.botao4);
        botaolaranjado = findViewById(R.id.botao5);
        botaoRosa = findViewById(R.id.botao6);
        botaoRoxo = findViewById(R.id.botao7);
        botaoVerde = findViewById(R.id.botao8);
        botaoVermelho = findViewById(R.id.botao9);


        mostrarCores();

    }


    public String getCoresRand(){
        return cores[rand.nextInt(cores.length)];
    }//getCoresRand
    public int getCoresIndice(){

        int indice =  rand.nextInt(cores.length);
        return indice;
    }//getCoresIndice

    private void mostrarCores(){
        i=0;
        Handler handler = new Handler();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while(i<=10) {
                    i += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            corAnterior = cor;
                            cor = getCoresRand();

                            //wile para garantir que as cores nao vao repetir em sequencia
                            while (corAnterior == cor) {
                                cor = getCoresRand();
                            }
                            escondeBotoes();
                            chamarBotao(cor);

                        }//run

                    });//rundler.post

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException erro) {

                    }
                }
            }//run
        }).start();//thread
        mostraBotoes();
    }//mostrarCores

    private void chamarBotao(String cor){
        if(cor == "rosa")
            botaoRosa.setVisibility(View.VISIBLE);
        if(cor == "preto")
            botaoPreto.setVisibility(View.VISIBLE);
        if(cor == "verde")
            botaoVerde.setVisibility(View.VISIBLE);
        if(cor == "azul")
            botaoAzul.setVisibility(View.VISIBLE);
        if(cor == "vermelho")
            botaoVermelho.setVisibility(View.VISIBLE);
        if(cor == "amarelo")
            botaoAmarelo.setVisibility(View.VISIBLE);
        if(cor == "laranjado")
            botaolaranjado.setVisibility(View.VISIBLE);
        if(cor == "marrom")
            botaoMarron.setVisibility(View.VISIBLE);
        if(cor == "roxo")
            botaoRoxo.setVisibility(View.VISIBLE);
    }
    private void mostraBotoes(){
            botaoRosa.setVisibility(View.VISIBLE);
            botaoPreto.setVisibility(View.VISIBLE);
            botaoVerde.setVisibility(View.VISIBLE);
            botaoAzul.setVisibility(View.VISIBLE);
            botaoVermelho.setVisibility(View.VISIBLE);
            botaoAmarelo.setVisibility(View.VISIBLE);
            botaolaranjado.setVisibility(View.VISIBLE);
            botaoMarron.setVisibility(View.VISIBLE);
            botaoRoxo.setVisibility(View.VISIBLE);
    }
    private void escondeBotoes(){
            botaoRosa.setVisibility(View.INVISIBLE);
            botaoPreto.setVisibility(View.INVISIBLE);
            botaoVerde.setVisibility(View.INVISIBLE);
            botaoAzul.setVisibility(View.INVISIBLE);
            botaoVermelho.setVisibility(View.INVISIBLE);
            botaoAmarelo.setVisibility(View.INVISIBLE);
            botaolaranjado.setVisibility(View.INVISIBLE);
            botaoMarron.setVisibility(View.INVISIBLE);
            botaoRoxo.setVisibility(View.INVISIBLE);
    }

}