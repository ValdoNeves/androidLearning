package com.valdoneves.janken;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class JankenActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TITULO_APPBAR = "Pedra, Papel ou Tesoura";

    private static final int PEDRA_ID = R.id.imagem_pedra;
    private static final int PAPEL_ID = R.id.imagem_papel;
    private static final int TESOURA_ID = R.id.imagem_tesoura;
    private static final int PADRAO_ID = R.id.imagem_padrao;

    private static final int PEDRA_IMAGEM = R.drawable.pedra;
    private static final int PAPEL_IMAGEM = R.drawable.papel;
    private static final int TESOURA_IMAGEM = R.drawable.tesoura;
    private TextView campoVitorias;
    private TextView campoDerrotas;

    private int vitorias;
    private int derrotas;

    private TextView textoVencedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janken);
        setTitle(TITULO_APPBAR);
        camposPlacar();
//        escolhePedra(pedra);
        textoVencedor = findViewById(R.id.texto_escolha_jogador);
        vitorias = 0;
        derrotas = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        inicializaImagensComoClicaveis();

    }

    private void inicializaImagensComoClicaveis() {
        ImageView pedra = findViewById(PEDRA_ID);
        pedra.setOnClickListener(this);

        ImageView papel = findViewById(PAPEL_ID);
        papel.setOnClickListener(this);

        ImageView tesoura = findViewById(TESOURA_ID);
        tesoura.setOnClickListener(this);
    }

    private void camposPlacar(){
        campoVitorias = findViewById(R.id.score_vitorias);
        campoDerrotas = findViewById(R.id.score_derrotas);
    }

    public String jogo(String jogador, String app){


        String resultado;


        if (jogador == app){
            //empate
            return resultado = "Empate";
        }else if(

                (jogador.equals("pedra") && app.equals("papel")) ||
                (jogador.equals("papel") && app.equals("tesoura")) ||
                (jogador.equals("tesoura") && app.equals("pedra"))
        ) {
            //app vence
            derrotas++;
            campoDerrotas.setText(String.valueOf(derrotas));
            return resultado = "Você perdeu!";
        }else{
            //voce vence
            vitorias++;
            campoVitorias.setText(String.valueOf(vitorias));
            return "Você ganhou!  ";
        }

    }

    private void vibrar(){
        Vibrator vibra = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long milisegundos = 100;
        vibra.vibrate(milisegundos);
    }

    @Override
    public void onClick(View view) {
        ImageView imagemAPP = findViewById(PADRAO_ID);

        int escolhaDoAPPAleatoria = new Random().nextInt(3);
        String[] opcoesApp = {"pedra", "papel", "tesoura"};
        String escolhaDoApp = opcoesApp[escolhaDoAPPAleatoria];

        switch (escolhaDoApp){
            case "pedra":
                imagemAPP.setImageResource(PEDRA_IMAGEM);
                break;
            case "papel":
                imagemAPP.setImageResource(PAPEL_IMAGEM);
                break;
            case "tesoura":
                imagemAPP.setImageResource(TESOURA_IMAGEM);
                break;
        }

        switch (view.getId()){
            case PEDRA_ID:
                String pedra = "pedra";
                textoVencedor.setText(jogo(pedra,escolhaDoApp));
                break;

            case PAPEL_ID:
                String papel = "papel";
                textoVencedor.setText(jogo(papel,escolhaDoApp));
                break;
//                Log.i("Botao Selecionado", "escolhePapel: " );
//                break;
            case TESOURA_ID:
                String tesoura = "tesoura";
                textoVencedor.setText(jogo(tesoura,escolhaDoApp));
                break;

//                Log.i("Botao Selecionado", "escolheTesoura: " );
//                break;
        }
        vibrar();
    }
}
