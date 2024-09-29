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

                String mensagemLimpar = String.format("Você limpou os dados. Preencha novamente os dados.");
                tvResultado.setText(mensagemLimpar);
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


                if (Nome.isEmpty() && Email.isEmpty() && strIdade.isEmpty() &&
                    strDisciplina.isEmpty() && strNota1bi.isEmpty() && strNota2bi.isEmpty()) {
                    tvErro.setText("Todos os campos estão vazios.");
                    tvResultado.setText(""); // Limpar resultados anteriores
                    return;
                }

                if (Email.isEmpty() && strIdade.isEmpty() &&
                    strDisciplina.isEmpty() && strNota1bi.isEmpty() && strNota2bi.isEmpty()) {
                    tvErro.setText("Os campos Email, Idade, Disciplina, Notas 1 bi e Notas 2 bi estão vazios .");
                    tvResultado.setText(""); // Limpar resultados anteriores
                    return;
                }

                if (strIdade.isEmpty() && strDisciplina.isEmpty() && strNota1bi.isEmpty() && strNota2bi.isEmpty()) {
                    tvErro.setText("Os campos Idade, Disciplina, Notas 1 bi e Notas 2 bi estão vazios.");
                    tvResultado.setText(""); // Limpar resultados anteriores
                    return;
                }

                if (strDisciplina.isEmpty() && strNota1bi.isEmpty() && strNota2bi.isEmpty()) {
                    tvErro.setText("Os campos Disciplina, Notas 1 bi e Notas 2 bi estão vazios.");
                    tvResultado.setText("");
                    return;
                }

                if (strNota1bi.isEmpty() && strNota2bi.isEmpty()) {
                    tvErro.setText("Os campos Notas 1 bi e Notas 2 bi estão vazios.");
                    tvResultado.setText(""); // Limpar resultados anteriores
                    return;
                }

                if (strNota2bi.isEmpty()) {
                    tvErro.setText("O campo Notas 2 bi está vazio.");
                    tvResultado.setText(""); // Limpar resultados anteriores
                    return;
                }

                if (strDisciplina.isEmpty()) {
                    tvErro.setText("O campo Disciplina está vazio.");
                    tvResultado.setText(""); // Limpar resultados anteriores
                    return;
                }

                if (Nome.isEmpty()) {
                    tvErro.setText("O campo Nome está vazio.");
                    tvResultado.setText(""); // Limpar resultados anteriores
                    return;
                }
                if (Email.isEmpty()) {
                    tvErro.setText("O campo Email está vazio.");
                    tvResultado.setText(""); // Limpar resultados anteriores
                    return;
                }
                if (strIdade.isEmpty()) {
                    tvErro.setText("O campo Idade está vazio.");
                    tvResultado.setText(""); // Limpar resultados anteriores
                    return;
                }
                if (strNota1bi.isEmpty()) {
                    tvErro.setText("O campo Notas do 1 bi está vazio.");
                    tvResultado.setText(""); // Limpar resultados anteriores
                    return;
                }




                if (Nome.isEmpty()) {
                    tvErro.setText("O campo de nome deve ser preenchido.");
                }
                if (Email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    tvErro.setText("O campo de email deve conter um endereço de email válido.");
                }
                if (strIdade.isEmpty()) {
                    tvErro.setText("O campo de idade deve ser preenchido.");
                } else {
                    try {
                        int idade = Integer.parseInt(strIdade);
                        if (idade <= 0) {
                            tvErro.setText("A idade deve ser um número positivo.");
                        }
                    } catch (NumberFormatException e) {
                        tvErro.setText("A idade deve ser um número válido.");
                    }
                }
                if (strDisciplina.isEmpty()) {
                    tvErro.setText("O campo de disciplina deve ser preenchido.");
                }
                if (strNota2bi.isEmpty() || strNota2bi.isEmpty()) {
                    tvErro.setText("Os campos de notas devem estar preenchidos.");
                } else {
                    try {
                        double nota1 = Double.parseDouble(strNota1bi);
                        double nota2 = Double.parseDouble(strNota2bi);
                        if (nota1 < 0 || nota1 > 10 || nota2 < 0 || nota2 > 10) {
                            tvErro.setText("As notas devem conter um valor entre 0 e 10.");
                        }
                    } catch (NumberFormatException e) {
                        tvErro.setText("As notas devem ser números válidos.");
                    }
                }


                // Cálculo da média
                double nota1 = Double.parseDouble(strNota1bi);
                double nota2 = Double.parseDouble(strNota2bi);
                double media = (nota1 + nota2) / 2;
                String resultado = "Nome: " + Nome + "\n" +
                        "Email: " + Email + "\n" +
                        "Idade: " + strIdade + "\n" +
                        "Disciplina: " + strDisciplina + "\n" +
                        "Notas 1o e 2o Bimestres: " + nota1 + ", " + nota2 + "\n" +
                        "Média: " + media + "\n" +
                        (media >= 6 ? "Aprovado" : "Reprovado");

                // Exibir o resultado
                tvResultado.setText(resultado);
            }


        });






    }




    }

