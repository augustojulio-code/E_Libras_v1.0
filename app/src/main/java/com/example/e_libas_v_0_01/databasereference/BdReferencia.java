package com.example.e_libas_v_0_01.databasereference;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BdReferencia {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private final FirebaseUser user = firebaseAuth.getCurrentUser();

    public DatabaseReference referenceUsuario(){

        return databaseReference = FirebaseDatabase.getInstance().getReference("Usuario").child(user.getUid());

    }

    public DatabaseReference referenceUserscore(){

        return databaseReference = FirebaseDatabase.getInstance().getReference("Userscore").child(user.getUid());
    }

    public DatabaseReference referencePergunta(){

        return databaseReference = FirebaseDatabase.getInstance().getReference("Perguntas");
    }

    public DatabaseReference referenceRespostas(){

        return databaseReference = FirebaseDatabase.getInstance().getReference("Respostas");
    }

}
