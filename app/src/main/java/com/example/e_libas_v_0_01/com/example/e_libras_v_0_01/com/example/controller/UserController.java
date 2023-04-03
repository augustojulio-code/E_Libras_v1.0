package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.com.example.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UserController {

    FirebaseAuth firebaseAuth;
    DatabaseReference reference,databaseReference ;
    Query query = FirebaseDatabase.getInstance().getReference("Userscore").orderByChild("pontos").limitToLast(10);
    ArrayAdapter<String> adapter;

    public TextView nickReturn(final TextView view){

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("Usuario").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!dataSnapshot.exists())
                {
                    return;
                }
                String nickI= dataSnapshot.child("apelido").getValue().toString();

                view.setText("Olá "+nickI);

        }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

        return view;

    }

    public TextView buttonEnableReturn(final Button button){

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("Userscore").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                if (!dataSnapshot.exists())
                {
                    return;
                }

                String pontos = dataSnapshot.child("pontos").getValue().toString();
                int int_pontos = Integer.parseInt(pontos);

                if (int_pontos< 1000)
                {
                    button.setEnabled(false);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return button;
    }

}


