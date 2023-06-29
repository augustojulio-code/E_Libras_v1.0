package com.example.e_libas_v_0_01;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.com.example.controller.UserController;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button login;
    private EditText txtedtemail,txtedtsenha;
    private TextView registrar, recuperarsenha;
    private UserController controller = new UserController();
    private ProgressDialog barra;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        barra = new ProgressDialog(this);

        txtedtemail =  findViewById(R.id.edttxtemail);
        txtedtsenha =  findViewById(R.id.edttxtsenha);

        login =  findViewById(R.id.btnlogin);

        registrar =  findViewById(R.id.txtviewsignup);
        recuperarsenha =  findViewById(R.id.txtrecuperarsenha);

        login.setOnClickListener(this);
        registrar.setOnClickListener(this);
        recuperarsenha.setOnClickListener(this);

    }

    private void verificauser() {
        String lemail = txtedtemail.getText().toString().trim();
        String lsenha = txtedtsenha.getText().toString().trim();

        if(TextUtils.isEmpty(lemail) || TextUtils.isEmpty(lsenha) )
        {
            Toast.makeText(this,"Preencha todos os campos",Toast.LENGTH_LONG).show();

            return;
        }


        try{
            controller.UserLogin(lemail,lsenha);
            startActivity(new Intent(getApplicationContext(),MainFragmentMenu.class));
        }
        catch(Exception e){
            Toast.makeText(LoginActivity.this,"Email ou senha Incorreto", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View view)
    {
        if (view == login)
        {
            verificauser();
        }
        if (view == registrar)
        {
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        if (view == recuperarsenha)
        {
            controller.ResetPasswordDialog(LoginActivity.this);
            barra.setMessage("Aguarde");
            barra.show();

        }
    }


}
