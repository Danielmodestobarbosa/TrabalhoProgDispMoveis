package com.example.trabalhodispositivosmoveis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edNome;
    private EditText edEmail;
    private EditText edIdade;
    private EditText edDisciplina;
    private EditText edNtPriBimestre;
    private EditText edNtSegBimestre;
    private TextView tvResultado;
    private Button btEnviar;
    private Button btLimpar;
    private TextView tvErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edNome = findViewById(R.id.edNome);
        edEmail = findViewById(R.id.edEmail);
        edIdade = findViewById(R.id.edIdade);
        edDisciplina = findViewById(R.id.edDisciplina);
        edNtPriBimestre = findViewById(R.id.edNtPriBimestre);
        edNtSegBimestre = findViewById(R.id.edNtSegBimestre);
        tvResultado = findViewById(R.id.tvResultado);
        btEnviar = findViewById(R.id.btEnviar);
        btLimpar = findViewById(R.id.btLimpar);
        tvErro = findViewById(R.id.tvErro);

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparDados();
            }
            private void limparDados() {

                edNome.setText("");
                edEmail.setText("");
                edIdade.setText("");
                edDisciplina.setText("");
                edNtPriBimestre.setText("");
                edNtSegBimestre.setText("");
                tvResultado.setText("");
                tvErro.setText("");
            }

        });

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDados();
            }

            private void validarDados() {
                String Nome = edNome.getText().toString().trim();
                String Email = edEmail.getText().toString().trim();
                String strIdade = edIdade.getText().toString().trim();
                String strDisciplina = edDisciplina.getText().toString().trim();
                String strNota1bi = edNtPriBimestre.getText().toString().trim();
                String strNota2bi = edNtSegBimestre.getText().toString().trim();


                StringBuilder exception = new StringBuilder();

                if(Nome.isEmpty()){
                    exception.append("O campo de nome está vazio, é obrigatório informar um nome. \n");
                }



                if(Email.isEmpty()) {
                    exception.append("O campo email está vazio");
                }if((Email.contains("@"))){
                    if(Email.indexOf("@") > 3){
                        exception.append("E-mail inválido ou vazio. \n");
                    }
                }


                int idade = 0;
                try {
                    idade = Integer.parseInt(strIdade);
                    if(strIdade.isEmpty()){
                        exception.append("O campo idade está vazio");
                    }
                    if (idade <= 0) {
                        exception.append("A idade precisa ser um número positivo.\n");
                    }
                } catch (NumberFormatException e) {
                    exception.append("A idade precisa ser um número.\n");
                }

                double nota1 = 0;
                double nota2 = 0;
                try {
                    nota1 = Double.parseDouble(strNota1bi);
                    nota2 = Double.parseDouble(strNota2bi);
                    if(strNota1bi.isEmpty()){
                        exception.append("O campo de notas do 1 bimestre está vazio");
                    }
                    if(strNota2bi.isEmpty()){
                        exception.append("O campo de notas do 2 bimestre está vazio");
                    }
                    if (nota1 < 0 || nota1 > 10) {
                        exception.append("A nota 1 precisa ser um número entre 0 e 10.\n");
                    }
                    if (nota2 < 0 || nota2 > 10) {
                        exception.append("A nota 2 precisa ser um número entre 0 e 10.\n");
                    }
                } catch (NumberFormatException e) {
                    exception.append("As notas precisam ser números.\n");
                }

                if (exception.length() > 0) {
                    tvErro.setText(exception.toString());
                    tvResultado.setText("");
                    return;
                }

                double media = (nota1 + nota2) / 2;
                String resultado = media >= 6 ? "Aprovado" : "Reprovado";

                String resumo = String.format("Nome: %s\nEmail: %s\nIdade: %d\nDisciplina: " +
                                              " %s\nNotas 1º e 2º Bimestres: %.2f, %.2f\nMédia: %.2f\nSituação: %s",
                        Nome, Email, idade, strDisciplina, nota1, nota2, media, resultado);
                tvResultado.setText(resumo);
                tvErro.setText("");

                String mensagemLimpar = String.format("Você limpou os dados. Preencha novamente os dados.");
                tvResultado.setText(mensagemLimpar);
                tvErro.setText("");
            }
        });






    }




    }

