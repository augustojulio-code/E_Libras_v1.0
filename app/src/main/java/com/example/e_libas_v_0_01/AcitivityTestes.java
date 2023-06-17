package com.example.e_libas_v_0_01;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.e_libas_v_0_01.R;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.com.example.controller.UserController;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Usuario;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.recursos.Recursos;

public class AcitivityTestes extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_testes);

        Button button = findViewById(R.id.botao_test);
        final UserController userController = new UserController();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario usuario = new Usuario();
                //usuario.setIdUsuario("teste");
                usuario.setNome("teste");
                usuario.setEmail("teste54321@gmail.com");
                usuario.setApelido("apelidoteste");
                userController.registerUserAuth(usuario,"123456");

                Toast.makeText(AcitivityTestes.this,"Deu certo",Toast.LENGTH_LONG).show();


            }
        });


    }
}
