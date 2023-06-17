package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.com.example.controller;

import android.app.Activity;
import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.e_libas_v_0_01.AcitivityTestes;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Userscore;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Usuario;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.recursos.Recursos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
import java.util.concurrent.Executor;


public class UserController {
    FirebaseAuth firebaseAuth;
    DatabaseReference reference,databaseReference ;
    Query query = FirebaseDatabase.getInstance().getReference("Userscore").orderByChild("pontos").limitToLast(10);
    UserscoreController controller = new UserscoreController();
    Recursos recursos = new Recursos();

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

    public void lista(final ArrayList<String> arrayList, final ArrayAdapter<String> adapter){

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String namepontos = dataSnapshot.getValue(Userscore.class).toString();
                    arrayList.add(namepontos);

                    adapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public TextView retornoNivelPontos(final TextView view){

        final FirebaseUser user = firebaseAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Userscore").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String nivel = dataSnapshot.child("nivel").getValue().toString();
                String pontos = dataSnapshot.child("pontos").getValue().toString();

                view.setText("Nv: "+nivel+ " - Pontos: "+pontos);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    public void registerUserAuth(final Usuario userData, String userpassword){

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

            firebaseAuth.createUserWithEmailAndPassword(userData.getEmail(),userpassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                recursos.sleepTime();

                                userData.setIdUsuario(user.getUid());

                                if (task.isSuccessful()){

                                    registerUser(userData);
                                    return;

                                }

                            }
                        });
    }

    public void registerUser(final Usuario usuario){

        databaseReference = FirebaseDatabase.getInstance().getReference("Usuario").child(usuario.getIdUsuario());

        try {

            databaseReference.setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    Userscore userscore = new Userscore(usuario.getApelido(),0,1);

                    if(task.isSuccessful()){

                        controller.registerScore(userscore);
                        return;
                    }
                }
            });
        }
        catch (Exception e){

            System.out.println("ALGO DEU ERRADO CADASTRO USER"+ e);
        }


    }
}


