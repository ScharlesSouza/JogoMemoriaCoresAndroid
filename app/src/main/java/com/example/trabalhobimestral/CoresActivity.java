package com.example.trabalhobimestral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class CoresActivity extends AppCompatActivity {

    private int i;
    private View view;
    private String[] cores = {"rosa", "azul", "vermelho", "verde", "preto", "amarelo", "roxo", "marrom", "laranjado"};
    String cor;
    private Random rand = new Random();
    private TextView textViewContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cores);
        view = findViewById(R.id.layoutPrincipal);
        textViewContador = findViewById(R.id.textViewContador);
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
                while(i<=10){
                    i+=1;


                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            cor = getCoresRand();
                            textViewContador.setText(""+i);
                            //textViewContador.setTextColor(getResources().getColor(R.color.preto));
                            if(cor == "rosa")

                                 view.setBackgroundColor(getResources().getColor(R.color.branco));
                            if(cor == "preto")
                                //textViewContador.setTextColor(getResources().getColor(R.color.branco));
                                view.setBackgroundColor(getResources().getColor(R.color.preto));
                            if(cor == "verde")
                                view.setBackgroundColor(getResources().getColor(R.color.verde));
                            if(cor == "azul")
                                view.setBackgroundColor(getResources().getColor(R.color.azul));
                            if(cor == "vermelho")
                                view.setBackgroundColor(getResources().getColor(R.color.vermelho));
                            if(cor == "amarelo")
                                view.setBackgroundColor(getResources().getColor(R.color.amarelo));
                            if(cor == "laranjado")
                                view.setBackgroundColor(getResources().getColor(R.color.laranjado));
                            if(cor == "marrom")
                                view.setBackgroundColor(getResources().getColor(R.color.marrom));
                            if(cor == "roxo")
                                view.setBackgroundColor(getResources().getColor(R.color.roxo));


                        }//run
                    });//rundler.post
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException erro){

                    }
                }
            }//run
        }).start();//thread

    }//mostrarCores

}