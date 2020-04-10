package com.example.loteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

     private EditText numUm, numDois, numTres,numQuatro,numCinco,numSeis;
     private Button btnJoga;
     private EditText acertos, numerosSupresinha, numerosSorteados;
     private RadioButton radioSim, radioNao;


     /*PROBLEMAS A RESOLVER
     * Usuario inserindo numeros mariores que 60
     * Classe Random gerando numeros repetidos
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioNao = findViewById(R.id.radioNao);
        radioSim = findViewById(R.id.radioSim);
        btnJoga  = findViewById(R.id.btnJogar);
        acertos = findViewById(R.id.editAcertos);
        numerosSorteados = findViewById(R.id.editNumSorteados);
        numerosSupresinha = findViewById(R.id.editNumGerados);



        radioSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 habilitarCampos();
            }
        });

        radioNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                desabilitarCampos();
            }
        });



        btnJoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

         //verficar se o usuario nao marcou nada
         if(!radioSim.isChecked() && !radioNao.isChecked()){

             Toast.makeText(MainActivity.this,
                     "Devem ser inseridos no minimo 6 digitos", Toast.LENGTH_LONG).show();
         }
         else
           if(radioNao.isChecked()) {

               if (numUm.getText().toString().isEmpty() || numDois.getText().toString().isEmpty() ||
                       numTres.getText().toString().isEmpty() || numQuatro.getText().toString().isEmpty() &&
                       numCinco.getText().toString().isEmpty() || numSeis.getText().toString().isEmpty()
               ) {

                   Toast.makeText(MainActivity.this,
                           "Devem ser inseridos no minimo 6 digitos", Toast.LENGTH_LONG).show();

               } else {

                       compararList(guardarAposta(), gerarNumeros());
                   }


           } else {

                   compararList(gerarNumeros(), gerarNumeros());
           }

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        numUm = findViewById(R.id.edtUm);
        numDois = findViewById(R.id.edtDois);
        numTres = findViewById(R.id.edtTres);
        numQuatro = findViewById(R.id.edtQuatro);
        numCinco = findViewById(R.id.edtCinco);
        numSeis = findViewById(R.id.edtSeis);


    }

    public  ArrayList<Integer> guardarAposta(){

        ArrayList<Integer> numerosJogados = new ArrayList();

        numerosJogados.add(Integer.parseInt(numUm.getText().toString()));
        numerosJogados.add(Integer.parseInt(numDois.getText().toString()));
        numerosJogados.add(Integer.parseInt(numTres.getText().toString()));
        numerosJogados.add(Integer.parseInt(numQuatro.getText().toString()));
        numerosJogados.add(Integer.parseInt(numCinco.getText().toString()));
        numerosJogados.add(Integer.parseInt(numSeis.getText().toString()));


        System.out.println(numerosJogados);
        return numerosJogados;
    }

    public  ArrayList<Integer> gerarNumeros(){
        ArrayList<Integer> numeGerados = new ArrayList();
        Random randNumero = new Random();

        for (int i = 1; i < 7; i++ ){

            numeGerados.add(randNumero.nextInt(59)+1);

        }

        System.out.println(numeGerados);


        return numeGerados;

    }

    public void compararList(ArrayList numerosGerados, ArrayList numerosJogados){

        int i =0;

        for(int j = 0; j < 6; j++){

            if(numerosGerados.contains(numerosJogados.get(j))){

                i++;
            }

        }
        mostraResultado(numerosGerados,numerosJogados,i);
        System.out.println(i);


    }

    public void mostraResultado(ArrayList numGerados, ArrayList nummApostados, int num){



        numerosSorteados.setText(String.valueOf(numGerados));
        numerosSupresinha.setText(String.valueOf(nummApostados));
        acertos.setText(String.valueOf(num));

        numerosSorteados.setEnabled(false);
        numerosSupresinha.setEnabled(false);
        acertos.setEnabled(false);








    }

    public void  habilitarCampos(){

        numUm.setEnabled(false);
        numDois.setEnabled(false);
        numTres.setEnabled(false);
        numQuatro.setEnabled(false);
        numCinco.setEnabled(false);
        numSeis.setEnabled(false);




    }

    public void desabilitarCampos(){

        numUm.setEnabled(true);
        numDois.setEnabled(true);
        numTres.setEnabled(true);
        numQuatro.setEnabled(true);
        numCinco.setEnabled(true);
        numSeis.setEnabled(true);




    }
}
