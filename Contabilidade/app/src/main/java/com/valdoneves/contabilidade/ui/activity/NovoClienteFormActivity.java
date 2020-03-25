package com.valdoneves.contabilidade.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.valdoneves.contabilidade.R;
import com.valdoneves.contabilidade.dao.ClienteDAO;
import com.valdoneves.contabilidade.modelo.Cliente;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NovoClienteFormActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "NOVO CLIENTE";

    ClienteDAO dao = new ClienteDAO();

    private EditText campoValor;
//    private TextView campoId;
    private EditText campoNome;
    private EditText campoObservacoes;
    private Integer id = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITULO_APPBAR);

        setContentView(R.layout.activity_novo_cliente_form);

        inicializaCamposDeTextos();

        configuraBotaoSalvar();

//        Intent dados = getIntent();
//        Cliente cliente = (Cliente) dados.getSerializableExtra("cliente");

        //toda vez que o botao for clicado devemos incrementar 1 no ID do cliente
        //verificar se o numero ja foi usado, se sim adicionar 1 se não manter o mesmo
        //o Id deve ser compartilhado entre os usuarios
//        campoId.setText(cliente.getId());

//        campoValor.setText(cliente.getValor());
//        campoObservacoes.setText(cliente.getObservacoes());
//        campoNome.setText(cliente.getNome());




    }

    private void configuraBotaoSalvar() {
        FloatingActionButton configuraBotaoSalvaCliente = findViewById(R.id.activity_novo_cliente_form_fab_adiciona_cliente);
        salvaOuAtualizaNovoCliente(configuraBotaoSalvaCliente);
    }

    private void salvaOuAtualizaNovoCliente(FloatingActionButton configuraBotaoSalvaCliente) {
        configuraBotaoSalvaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(campoNome.getText().length() == 0){
                    Toast.makeText(NovoClienteFormActivity.this, "Cliente, numero: " + id, Toast.LENGTH_SHORT).show();

                    Cliente clienteAnonimo = new Cliente(1, "Cliente, número:"+id, "20","nenhuma informação");
                    salva(clienteAnonimo);
                    finish();
                }else {
                    Toast.makeText(NovoClienteFormActivity.this, campoNome.getText() + ", numero: " + id, Toast.LENGTH_SHORT).show();
                    Cliente clienteCriado = criaCliente();
                    salva(clienteCriado);
                    finish();

                }
            }

        });
    }

    private void salva(Cliente clienteCriado) {
        dao.salvaCliente(clienteCriado);
        finish();
    }

    private Cliente criaCliente() {
        String novoNome = campoNome.getText().toString();
        String novoValor = campoValor.getText().toString();
        String novaObservacoes = campoObservacoes.getText().toString();

        return new Cliente(1, novoNome, novoValor,novaObservacoes);
    }


    private void inicializaCamposDeTextos() {
        campoNome = findViewById(R.id.activity_novo_cliente_form_cliente_nome);
        campoValor = findViewById(R.id.activity_novo_cliente_form_cliente_valor);
//        campoId = findViewById(R.id.activity_novo_cliente_form_cliente_id);
        campoObservacoes = findViewById(R.id.activity_novo_cliente_form_cliente_observacoes);
    }
}
