package com.valdoneves.contabilidade.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.valdoneves.contabilidade.R;
import com.valdoneves.contabilidade.dao.ClienteDAO;
import com.valdoneves.contabilidade.modelo.Cliente;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class BalancoFinanceiroActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "BALANÇO DO DIA";

    public static final String VALOR_TOTAL_DIARIO = "VALOR TOTAL";
    public static final String LUCRO_LIQUIDO_DO_DIA = "LUCRO DO DIA";


    public static final String NOME_CHEFE = "BRUNO J. CRUZ";
    public static final String NOME_FUNCIONARIO = "LUCAS ARAÚJO";
    public static final String NOME_FUNCIONARIO_MAIS_DESCONTO = NOME_FUNCIONARIO + "\nC/ Desconto";

    DecimalFormat df = new DecimalFormat("R$ " + "##0.00");

    ClienteDAO dao = new ClienteDAO();

    double porcentagemDeDescontoDoFuncionario = 0.3; //ganho de 30 porcento em cima do funcionario

    double valorDoSaldoDoChefe = 100.00;
    double valorDoSaldoDoFuncionarioTotal = 20;
    double valorDoSaldoDoFuncionarioComDesconto = valorDoSaldoDoFuncionarioTotal - (valorDoSaldoDoFuncionarioTotal * porcentagemDeDescontoDoFuncionario);
    double valorDoSaldoTotalDiario = valorDoSaldoDoChefe + valorDoSaldoDoFuncionarioTotal;
    double valorDoSaldoLiquidoDoDia = valorDoSaldoTotalDiario - valorDoSaldoDoFuncionarioComDesconto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_balanco_financeiro);
        setTitle(TITULO_APPBAR);

        defineNomeDosFuncionarios();
        logicaDosLucros();

        String texto = "tem que aparecer bastente coisa escrita";


        botaoAdicionaCliente();

        dao.salvaCliente(new Cliente(1, "valdo", "20",texto.substring(0,15)+"..."));
//        dao.salvaCliente(new Cliente(1, "valdo", "20","..."));

        dao.salvaCliente(new Cliente(2, "Lucas", "100",""));
        dao.salvaCliente(new Cliente(3, "Maxx","20",""));
        dao.salvaCliente(new Cliente(4, "Ben", "50",""));

    }

    private void botaoAdicionaCliente() {
        FloatingActionButton botaoAdicionaCliente = findViewById(R.id.activity_balanco_financeiro_fab_adiciona_cliente);
        abreFormularioNovoCliente(botaoAdicionaCliente);
    }

    private void abreFormularioNovoCliente(FloatingActionButton botaoAdicionaCliente) {
        botaoAdicionaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {

                startActivity(new Intent(BalancoFinanceiroActivity.this, NovoClienteFormActivity.class));
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }


    private void logicaDosLucros() {
        TextView campoDoSaldoDoChefe = findViewById(R.id.activity_balanco_financeiro_chefe_saldo);
        campoDoSaldoDoChefe.setText(df.format(valorDoSaldoDoChefe));

        TextView campoDoSaldoDoFuncionarioTotal = findViewById(R.id.activity_balanco_financeiro_funcionario_saldo_total);
        campoDoSaldoDoFuncionarioTotal.setText(df.format(valorDoSaldoDoFuncionarioTotal));

        TextView campoDoSaldoDoFuncionarioComDescontos = findViewById(R.id.activity_balanco_financeiro_funcionario_saldo_com_desconto);
        campoDoSaldoDoFuncionarioComDescontos.setText(df.format(valorDoSaldoDoFuncionarioComDesconto));

        TextView campoDoSaldoTotalDiario = findViewById(R.id.activity_balanco_financeiro_saldo_total_diario);
        campoDoSaldoTotalDiario.setText(df.format(valorDoSaldoTotalDiario));

        TextView campoDoSaldoLiquidoDoDia = findViewById(R.id.activity_balanco_financeiro_saldo_liquido_do_dia);
        campoDoSaldoLiquidoDoDia.setText(df.format(valorDoSaldoLiquidoDoDia));
    }

    private void defineNomeDosFuncionarios() {
        TextView campoChefe = findViewById(R.id.activity_balanco_financeiro_chefe);
        campoChefe.setText(NOME_CHEFE);

        TextView campoFuncionario = findViewById(R.id.activity_balanco_financeiro_funcionario);
        campoFuncionario.setText(NOME_FUNCIONARIO);

        TextView campoFuncionarioComDesconto = findViewById(R.id.activity_balanco_financeiro_funcionario_desconto);
        campoFuncionarioComDesconto.setText(NOME_FUNCIONARIO_MAIS_DESCONTO);

        TextView campoDoValorTotalDiario = findViewById(R.id.activity_balanco_financeiro_valor_total_diario);
        campoDoValorTotalDiario.setText(VALOR_TOTAL_DIARIO);

        TextView campoLucroLiquidoDoDia = findViewById(R.id.activity_balanco_financeiro_lucro_liquido_do_dia);
        campoLucroLiquidoDoDia.setText(LUCRO_LIQUIDO_DO_DIA);
    }

    private void configuraLista() {
        ListView listaDeClientes = findViewById(R.id.activity_balanco_financeiro_listView);
        final List<Cliente> clientes = dao.todos();

        listaDeClientes.setAdapter(new ArrayAdapter<>(BalancoFinanceiroActivity.this,
                android.R.layout.simple_list_item_1,
                clientes));

        listaDeClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Cliente clienteEscolhido = clientes.get(posicao);

                Intent vaiParaFormularioActivity = new Intent(BalancoFinanceiroActivity.this, NovoClienteFormActivity.class);
                vaiParaFormularioActivity.putExtra("cliente", (Serializable) clienteEscolhido);
                startActivity(vaiParaFormularioActivity);

            }
        });

    }
}
