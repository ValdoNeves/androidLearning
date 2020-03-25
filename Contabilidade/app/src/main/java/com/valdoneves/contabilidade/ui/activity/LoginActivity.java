package com.valdoneves.contabilidade.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.valdoneves.contabilidade.R;

public class LoginActivity extends AppCompatActivity {

    private EditText campoLoginUsuario;
    private EditText campoLoginSenha ;

    public static final String NOME_APPBAR = "LOGIN";
    final String loginUsuarioOculto = "";
    final String loginSenhaOculta = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(NOME_APPBAR);

        inicializaUsuarios();

        FloatingActionButton botaoLogar = findViewById(R.id.activity_login_fab_botao_logar);
        botaoLogar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Quando o clique é dado devemos ler o conteúdo que esta dentro da EditText assim podemos fazer o if else
                // e dar continuidade ao codigo

                //amanha devo refatorar o mesmo
                String senha = campoLoginSenha.getText().toString();
                String usuario = campoLoginUsuario.getText().toString();
                loginValidacao(senha, usuario, loginUsuarioOculto, loginSenhaOculta);

            }

        });
    }

    private void loginValidacao(String senha, String usuario, String loginUsuarioOculto, String loginSenhaOculta) {
        if ( !usuario.equals(loginUsuarioOculto) || !senha.equals(loginSenhaOculta))
                Toast.makeText(LoginActivity.this, "    AINDA NÃO JOVEM!\nLogin ou senha invalidos", Toast.LENGTH_SHORT).show();
        else{
//                Toast.makeText(LoginActivity.this, "                   PARABENS!\n" +
//                        "MAS A TELA AINDA NÃO EXISTE", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, BalancoFinanceiroActivity.class));
        }
    }

    private void inicializaUsuarios() {
        campoLoginUsuario = findViewById(R.id.activity_login_text_usuario);
        campoLoginSenha = findViewById(R.id.activity_login_text_senha);
    }
}
