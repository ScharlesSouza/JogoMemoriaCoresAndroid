package com.example.trabalhobimestral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome;
    private Button buttonNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        buttonNext = findViewById(R.id.buttonNext);

    }//onCreate

    public void clicar(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("nome_usuario", editTextNome.getText().toString());
        startActivity(intent);
    }//clicar
}