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
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.recursos.Recursos;

public class AcitivityTestes extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_testes);

        final Recursos recursos = new Recursos();
        Button button = findViewById(R.id.botao_test);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 //recursos.ratingBar(context,3);


            }
        });


    }
}
