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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class NumerosActivity extends AppCompatActivity {
    private EditText editTextNumero;
    private Button buttonVerificar, buttonListar;
    private TextView textViewListNum;
    private int i, clique, repeticoes, tentativa, numero, numeroAnterior;
    private View view;
    String  nome_usuario, texto;
    private ArrayList<Integer> listaNumerosMostrado=null;

    private Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros);


        Intent intent = getIntent();
        nome_usuario = intent.getStringExtra("nome_usuario");

        if(ler(nome_usuario)){
        }else{
            gravarUsuarioSP(nome_usuario);
        }

        editTextNumero = findViewById(R.id.editTextNumero);
        buttonListar = findViewById(R.id.botaoListNum);
        buttonVerificar = findViewById(R.id.botaoVerificar);
        textViewListNum = findViewById(R.id.textViewListNum);

        listaNumerosMostrado = new ArrayList<>();
        texto="";

        clique=0;
        repeticoes=tentativa+3;
        mostrarNumeros(repeticoes);
    }

    public void cliqueBotao(View botao){
        if(clique<repeticoes){

            if(listaNumerosMostrado.get(clique) ==Integer.parseInt((String) editTextNumero.getText().toString())){

                texto+=""+listaNumerosMostrado.get(clique)+",";
                textViewListNum.setText(texto);
                clique++;//se acerta a cor incrementa o clique ate finalizar a sequencia de cores correta
                editTextNumero.setText("");

            }else{
                criarDialogErro(clique);//se errar chama o Dialog e grava a tentativa
            }
        }
        if((clique+1)==repeticoes) {
            criarDialog(clique);//se acertar tudo chama o Dialog e grava a tentativa
        }
    }//cliqueBotao

    private void mostrarNumeros(int repeticao){
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
                                numeroAnterior = numero;
                                numero = getNumeroRand();

                                //wile para garantir que as cores nao vao repetir em sequencia
                                while (numeroAnterior == numero) {
                                    numero = getNumeroRand();
                                }
                                buttonListar.setText(""+numero);
                                listaNumerosMostrado.add(numero);
                            }else{
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

    private int getNumeroRand() {
       return rand.nextInt(60);
    }

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


    //metodo para mostrar Dialog com erro de clique
    private void criarDialogErro(int pontuacao){
        AlertDialog.Builder builder = new AlertDialog.Builder(NumerosActivity.this);
        builder.setTitle("Errou! \n Pontuação: "+pontuacao)
                .setPositiveButton("Tentar de novo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clique=0;
                        tentativa++;
                        gravarPontuacao(pontuacao, tentativa );//grava a pontuação desta tentativa
                        mostrarNumeros(repeticoes);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(NumerosActivity.this);
        builder.setTitle("Acertou todos, Parabens! \n Pontuação: "+pontuacao)
                .setPositiveButton("Tentar de novo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clique=0;
                        tentativa++;
                        gravarPontuacao(pontuacao, tentativa );//grava a pontuação desta tentativa
                        chamaNumerosActivity();
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
    private void chamaNumerosActivity(){
        Intent intent = new Intent(this, NumerosActivity.class);
        intent.putExtra("nome_usuario", nome_usuario);
        startActivity(intent);
    }//chamaNumerosActivity

}