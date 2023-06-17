package com.example.e_libas_v_0_01;


import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.com.example.controller.UserController;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Usuario;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.recursos.Recursos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AcitivityTestes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_testes);

        Button button = findViewById(R.id.botao_test);
        final UserController userController = new UserController();
        final Recursos recursos = new Recursos();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario usuario = new Usuario();
                //usuario.setIdUsuario("teste");
                usuario.setNome("teste");
                usuario.setEmail("teste54321@gmail.com");
                usuario.setApelido("apelidoteste");

                userController.registerUserAuth(usuario,"123456");

            }
        });




    }

    public String text(){

        return "OK!!!!!!!!";
    }
}
