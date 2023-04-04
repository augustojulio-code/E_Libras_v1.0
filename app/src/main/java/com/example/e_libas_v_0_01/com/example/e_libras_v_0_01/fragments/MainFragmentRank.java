package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.e_libas_v_0_01.R;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.com.example.controller.UserController;

import java.util.ArrayList;
import java.util.Collections;

public class MainFragmentRank extends Fragment
{
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    UserController controller = new UserController();
    listaAdapter adapter;

    int medalhasimgs[]= { R.drawable.m10,R.drawable.m9,R.drawable.m8,R.drawable.m7,R.drawable.m6,R.drawable.m5,R.drawable.m4,R.drawable.m3,R.drawable.m2,R.drawable.m1};

    // Tramento de ranking de usuarios

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        final View view = inflater.inflate(R.layout.fragment_rank, container,false);

        listView = view.findViewById(R.id.listviewfragment);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new listaAdapter(getContext(),arrayList,medalhasimgs);
        controller.lista(arrayList,adapter);
        listView.setAdapter(adapter);
    }

    public class listaAdapter extends ArrayAdapter<String>
    {
        Context context;
        ArrayList<String> listauserscore;
        int imagens[];
        listaAdapter(Context c, ArrayList<String> lista, int imgs[])
        {
            super(c,R.layout.listview_custom_rank,R.id.customviewapelido,lista);

            this.context = c;
            this.listauserscore = lista;
            this.imagens = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View customlist = inflater.inflate(R.layout.listview_custom_rank,parent,false);

            ImageView medalhascustom = customlist.findViewById(R.id.listmedalhas);

            TextView apelido = customlist.findViewById(R.id.customviewapelido);


            medalhascustom.setImageResource(imagens[position]);
            apelido.setText(listauserscore.get(position));

            return customlist;
        }

    }


}
