package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.e_libas_v_0_01.Previa_Atividade_1;
import com.example.e_libas_v_0_01.Previa_Atividade_2;
import com.example.e_libas_v_0_01.Previa_Atividade_3;
import com.example.e_libas_v_0_01.Previa_Atividade_4;
import com.example.e_libas_v_0_01.Previa_Atividade_5;
import com.example.e_libas_v_0_01.Previa_Atividade_7;
import com.example.e_libas_v_0_01.Previa_atividade_6;
import com.example.e_libas_v_0_01.R;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.com.example.controller.UserController;


public class MainFragmentHome extends Fragment implements View.OnClickListener {

    TextView nickname, txtHomeNivel;

    Button atividade, atividade2, atividade3, atividade4, atividade5, atividade6,atividade7;
    UserController controller = new UserController();

    //Tela principal e primeiras tarefas Inflando um layout dentro de um fragmento.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container, false);

        nickname = view.findViewById(R.id.txthomeusuario);
        txtHomeNivel = view.findViewById(R.id.txtHomeNivel);

         atividade = view.findViewById(R.id.botao_atividade);
         atividade2 = view.findViewById(R.id.botao_atividade_2);
         atividade3 = view.findViewById(R.id.botao_atividade_3);
         atividade4 = view.findViewById(R.id.botao_atividade_4);
         atividade5 = view.findViewById(R.id.botao_atividade_5);
         atividade6 = view.findViewById(R.id.botao_atividade_6);
         atividade7 = view.findViewById(R.id.botao_atividade_7);



        atividade.setOnClickListener(this);
        atividade2.setOnClickListener(this);
        atividade3.setOnClickListener(this);
        atividade4.setOnClickListener(this);
        atividade5.setOnClickListener(this);
        atividade6.setOnClickListener(this);
        atividade7.setOnClickListener(this);

        return view;
    }

    //Métodos que serão executados Assim que a tela carregar.
    @Override
    public void onStart() {
        super.onStart();

        controller.nickReturn(nickname);
        controller.retornoNivelPontos(txtHomeNivel);
        controller.buttonEnableReturn(atividade6);
        controller.buttonEnableReturn(atividade7);

    }

    //Chamando os metodos de click de cada botão
    @Override
    public void onClick(View view)
    {
        if (view == atividade)
        {
            startActivity(new Intent(getActivity(), Previa_Atividade_1.class));
        }
        if (view == atividade2)
        {
            startActivity(new Intent(getActivity(), Previa_Atividade_2.class));
        }
        if (view == atividade3)
        {
            startActivity(new Intent(getActivity(), Previa_Atividade_3.class));
        }
        if (view == atividade4)
        {
            startActivity(new Intent(getActivity(), Previa_Atividade_4.class));
        }
        if (view == atividade5)
        {
            startActivity(new Intent(getActivity(), Previa_Atividade_5.class));
        }
        if (view  == atividade6)
        {
            startActivity(new Intent(getActivity(), Previa_atividade_6.class));
        }
        if (view == atividade7)
        {
            startActivity(new Intent(getActivity(), Previa_Atividade_7.class));
        }

    }

}
