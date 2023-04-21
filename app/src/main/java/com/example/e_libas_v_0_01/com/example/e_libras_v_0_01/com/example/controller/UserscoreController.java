package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.com.example.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.e_libas_v_0_01.MainFragmentMenu;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Userscore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.v4.content.ContextCompat.startActivity;

public class UserscoreController {

    int pontos;

    FirebaseAuth firebaseAuth;
    DatabaseReference reference;



    public void registerScore(Userscore userscore){

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Userscore").child(user.getUid());

        reference.setValue(userscore).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    return;
                }
            }
        });

    }

    public int scoreReturn(){
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();


        reference = FirebaseDatabase.getInstance().getReference("Userscore").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String intVar = dataSnapshot.child("pontos").getValue().toString();
                pontos = Integer.parseInt(intVar);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });



        return pontos;
    }



}
