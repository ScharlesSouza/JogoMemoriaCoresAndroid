package com.example.trabalhobimestral;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GradeCoresActivity extends AppCompatActivity {
    private Button botaoRosa,botaoVerde,botaoRoxo,botaoMarron,botaoAmarelo,botaoAzul,botaoPreto,botaoVermelho,botaolaranjado;
    private TextView contCores;
    private ArrayList<String>  listaCoresMostrada=null;
    private int i, clique, repeticoes, tentativa;
    private View view;
    private String[] cores = {"preto","amarelo","rosa","vermelho", "verde","marrom", "azul","laranjado","roxo"};
    String cor, corAnterior, nome_usuario;
    private Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_cores);

        Intent intent = getIntent();
        nome_usuario = intent.getStringExtra("nome_usuario");

        if(ler(nome_usuario)){
        }else{
            gravarUsuarioSP(nome_usuario);
        }

        botaoPreto = findViewById(R.id.botao1Preto);
        botaoAmarelo = findViewById(R.id.botao2);
        botaoRosa = findViewById(R.id.botao3);
        botaoVermelho = findViewById(R.id.botao4);
        botaoVerde = findViewById(R.id.botao5);
        botaoMarron = findViewById(R.id.botao6);
        botaoAzul = findViewById(R.id.botao7);
        botaolaranjado = findViewById(R.id.botao8);
        botaoRoxo = findViewById(R.id.botao9);

        listaCoresMostrada = new ArrayList<>();

        contCores = findViewById(R.id.textViewContCores);

        clique=0;
        repeticoes=tentativa+3;
        mostrarCores(repeticoes);
        mostraBotoes();

    }


    public String getCoresRand(){
        return cores[rand.nextInt(cores.length)];
    }//getCoresRand
    public int getCoresIndice(){

        int indice =  rand.nextInt(cores.length);
        return indice;
    }//getCoresIndice

    public void cliqueBotao(View botao){
        if(clique<repeticoes){
            int tag =  Integer.parseInt((String) botao.getTag());
            Toast.makeText(getApplicationContext(), "Você clicou em "+cores[tag]+"!", Toast.LENGTH_SHORT).show();

            if(cores[tag] == listaCoresMostrada.get(clique)){
                clique++;//se acerta a cor incrementa o clique ate finalizar a sequencia de cores correta
            }else{
                criarDialogErro(clique);//se errar chama o Dialog e grava a tentativa
            }
        }
        if((clique+1)==repeticoes) {
            criarDialog(clique);//se acertar tudo chama o Dialog e grava a tentativa
        }
    }//cliqueBotao

    private String corBotao(int tag){
        return cores[tag];
    }//corBotao

    private void mostrarCores(int repeticao){
        i=0;
        Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(i<=repeticao) {
                    i += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(i<repeticao){
                                corAnterior = cor;
                                cor = getCoresRand();

                                //wile para garantir que as cores nao vao repetir em sequencia
                                while (corAnterior == cor) {
                                    cor = getCoresRand();
                                }
                                listaCoresMostrada.add(cor);
                                contCores.setText(""+i);
                                escondeBotoes();
                                chamarBotao(cor);
                            }else{
                                mostraBotoes();
                                contCores.setText("Vamos jogar:\n clique na cor seguindo a sequencia mostrada");
                            }
                        }//run
                    });//rundler.post

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException erro) {

                    }
                }
            }//run
        }).start();//thread
    }//mostrarCores

    private void gravarPontuacao(int pontuacao, int tentativa) {

        SharedPreferences sharedPreferences = getSharedPreferences("dados", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.contains("nome_usuario")){
            if (sharedPreferences.getString("nome_usuario","sem nome").equals(nome_usuario) ){
                editor.putString("pontuacao"+tentativa, String.valueOf(pontuacao));
                editor.putInt("Qnt_Tentativas", tentativa);
                Boolean resposta = editor.commit();
                if (resposta) {
                    Toast.makeText(this, "tentativa:"+tentativa+", Pontuação: "+pontuacao, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Não Salvo", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this, "ERRO no arquivo", Toast.LENGTH_SHORT).show();
        }//if

    }//gravar
    private void gravarUsuarioSP(String nome) {

        SharedPreferences sharedPreferences = getSharedPreferences("dados", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nome_usuario", nome);
        Boolean resposta = editor.commit();
        if (resposta) {
            Toast.makeText(this, "Usuario: "+nome, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Não Salvo", Toast.LENGTH_SHORT).show();
        }
    }//gravarUsuarioSP

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
    }//mostraBotoes
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
    }//escondeBotoes

    //metodo para mostrar Dialog com erro de clique
    private void criarDialogErro(int pontuacao){
        AlertDialog.Builder builder = new AlertDialog.Builder(GradeCoresActivity.this);
        builder.setTitle("Errou! \n Pontuação: "+pontuacao)
                .setPositiveButton("Tentar de novo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clique=0;
                        tentativa++;
                        gravarPontuacao(pontuacao, tentativa );//grava a pontuação desta tentativa
                    }
                })
                .setNegativeButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        chamaMenuActivity();//voltar para o menu
                    }
                });
        builder.show();
    }//criarDialogErro

    //metodo para mostrar Dialog com 100% de acertos
    private void criarDialog(int pontuacao){
        AlertDialog.Builder builder = new AlertDialog.Builder(GradeCoresActivity.this);
        builder.setTitle("Acertou todos, Parabens! \n Pontuação: "+pontuacao)
                .setPositiveButton("Tentar de novo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clique=0;
                        tentativa++;
                        gravarPontuacao(pontuacao, tentativa );//grava a pontuação desta tentativa
                        chamaGradeCoresActivity();
                    }
                })
                .setNegativeButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clique=0;
                        tentativa++;
                        gravarPontuacao(pontuacao, tentativa );//grava a pontuação desta tentativa
                        chamaMenuActivity();//voltar para o menu
                    }
                });
        builder.show();
    }//criarDialog

    //metodo para verificar se o usuario ja existe e pegar a quantidade de vezes que aquele usuariou tentou.
    public Boolean ler(String nome){
        SharedPreferences sharedPreferences = getSharedPreferences("dados",MODE_PRIVATE);
        if (sharedPreferences.contains("nome_usuario") ) {
            if (sharedPreferences.getString("nome_usuario","sem nome").equals(nome) ){
                tentativa = sharedPreferences.getInt("Qnt_Tentativas", 0);
                return true;
            }else
                return false;
        }else{
            return false;
        }//if
    }//ler

    //metodo para chamar a MenuActivity
    private void chamaMenuActivity(){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("nome_usuario", nome_usuario);
        startActivity(intent);
    }//chamaMenuActivity

    // metodo para chamar a chamaGradeCoresActivity
    private void chamaGradeCoresActivity(){
        Intent intent = new Intent(this, GradeCoresActivity.class);
        intent.putExtra("nome_usuario", nome_usuario);
        startActivity(intent);
    }//chamaGradeCoresActivity

}