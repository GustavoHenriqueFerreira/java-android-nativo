package com.example.calculadoraimc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.calculadoraimc.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Começar aqui!!!!
        TextView resultadoClassificacao = findViewById(R.id.resultadoClassificacao);
        TextView resultadoCalculo = findViewById(R.id.resultadoCalculo);

        Button botaoConverter = findViewById(R.id.botaoConverter);
        botaoConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double imc = calcularImc();
                calcularClassificao(imc);
            }

            private Double calcularImc() {
                EditText altura = findViewById(R.id.inputAltura);
                EditText peso = findViewById(R.id.inputPeso);
                Double alturaConvertida = Double.parseDouble(altura.getText().toString());
                Double pesoConvertido = Double.parseDouble(peso.getText().toString());
                Double imc = pesoConvertido / (alturaConvertida * alturaConvertida);
                DecimalFormat df = new DecimalFormat("#.##");
                String imcFormatado = df.format(imc);
                resultadoCalculo.setText(imcFormatado);
                resultadoCalculo.setVisibility(View.VISIBLE);
                return Double.parseDouble(imcFormatado);
            };

            private void calcularClassificao(Double imc) {
                /*
                    Resultado	        Situação
                    Abaixo de 18,5      Filezinho!!
                    Entre 18,5 e 24,99	Diliça!!!!
                    Entre 25 e 29,99	Ta Top!!!
                    Acima de 30      	Oh la em casa!!!
                */
                String classificacao;
                if (imc < 18.5) {
                    classificacao = "Filezinho";
                } else if (imc < 25) {
                    classificacao = "Diliça";

                } else if (imc < 30) {
                    classificacao = "Ta Top!!!";

                } else {
                    classificacao = "Oh la em casa!!!";
                }

                resultadoClassificacao.setText(classificacao);
                resultadoClassificacao.setVisibility(View.VISIBLE);
            }
        });
    }
}