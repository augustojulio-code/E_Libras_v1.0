package com.example.e_libas_v_0_01;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.com.example.controller.UserController;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button registrar;
    private EditText edtemail, edtsenha, edtnome,edtapelido, edtconfirmasenha;
    private TextView txtviewLogin;

    private ProgressDialog progressDialog;



    private Usuario userR = new Usuario();

    private UserController controller = new UserController();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       progressDialog = new ProgressDialog(this);


        registrar = findViewById(R.id.btnsalvar);

        edtemail = findViewById(R.id.txtemail);
        edtsenha = findViewById(R.id.txtsenha);
        edtconfirmasenha = findViewById(R.id.txtconfirma_senha);

        edtnome = findViewById(R.id.txtnomecompleto);
        edtapelido = findViewById(R.id.txtapelido);

        txtviewLogin = findViewById(R.id.txtviewsignin);

        registrar.setOnClickListener(this);
        txtviewLogin.setOnClickListener(this);


    }




    private void registerUser(){

        final String email = edtemail.getText().toString().trim();
        final String senha = edtsenha.getText().toString().trim();
        final String confirma_senha = edtconfirmasenha.getText().toString().trim();
        final String nome = edtnome.getText().toString().trim();
        final String apelido = edtapelido.getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(senha) || TextUtils.isEmpty(nome) || TextUtils.isEmpty(apelido) || TextUtils.isEmpty(confirma_senha))
        {
            Toast.makeText(this,"Preencha todos os campos",Toast.LENGTH_LONG).show();

            return;
        }
       if (!TextUtils.isEmpty(email))
           {
             String validaremail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                     "\\@" +
                     "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                     "(" +
                     "\\." +
                     "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                     ")+";

               Matcher matcher = Pattern.compile(validaremail).matcher(email);

               if(matcher.matches())
               {

                   if (senha.length() >=6)
                   {
                       if (senha.equals(confirma_senha))
                       {
                            progressDialog.show();

                            userR.setApelido(apelido);
                            userR.setEmail(email);
                            userR.setNome(nome);

                            controller.registerUserAuth(userR, senha);

                            progressDialog.dismiss();

                       }
                       else
                       {
                           Toast.makeText(MainActivity.this,"A senha e a confirmação de senha devem ser iguais", Toast.LENGTH_LONG).show();
                       }
                   }
                   else
                   {
                       Toast.makeText(MainActivity.this,"A senha deve conter 6 caracteres no minímo", Toast.LENGTH_LONG).show();
                   }

               }
               else
                   {
                       Toast.makeText(MainActivity.this,"Digite um email válido", Toast.LENGTH_LONG).show();
                   }
           }



    }

    @Override
    public void onClick(View view)
    {
        if (view == registrar)
        {

            registerUser();

        }
        if (view == txtviewLogin)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

}
