package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.com.example.controller;

import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Userscore;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Usuario;
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
        try {

            firebaseAuth.createUserWithEmailAndPassword(userData.getEmail(),userpassword)
                    .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                userData.setIdUsuario(user.getUid());
                                registerUser(userData);

                            }

                        }
                    });

        }
        catch (Exception e){

            System.out.println("ALGO DEU ERRADO LOGIN"+e);
        }
    }

    public void registerUser(Usuario usuario){

        /*databaseReference = FirebaseDatabase.getInstance().getReference("Usuario").child(user.getUid());



        try {

            databaseReference.setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                        //Userscore userscore = new Userscore(usuario.getApelido(),1,0);
                        //controller.registerScore(userscore);
                    return;
                    }
                }
            });
        }
        catch (Exception e){

            System.out.println("ALGO DEU ERRADO CADASTRO USER"+ e);
        }*/

        System.out.println("FUNCIONOU");
    }
}


