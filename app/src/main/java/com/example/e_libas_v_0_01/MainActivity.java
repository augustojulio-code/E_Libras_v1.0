package com.example.e_libas_v_0_01;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Userscore;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button registrar;
    private EditText edtemail, edtsenha, edtnome,edtapelido, edtnivel,edtpontos, edtconfirmasenha;
    private TextView txtviewLogin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()!= null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),MainFragmentMenu.class));
        }

       progressDialog = new ProgressDialog(this);


        registrar = findViewById(R.id.btnsalvar);

        edtemail = findViewById(R.id.txtemail);
        edtsenha = findViewById(R.id.txtsenha);
        edtconfirmasenha = findViewById(R.id.txtconfirma_senha);

        edtnome = findViewById(R.id.txtnomecompleto);
        edtapelido = findViewById(R.id.txtapelido);
        edtnivel = findViewById(R.id.txtnivelusuario);
        edtpontos = findViewById(R.id.txtpontosusuario);


        txtviewLogin = (TextView) findViewById(R.id.txtviewsignin);

        registrar.setOnClickListener(this);
        txtviewLogin.setOnClickListener(this);


    }




    private void registerUser()
    {

        databaseReference = FirebaseDatabase.getInstance().getReference("Usuario");

        final String email = edtemail.getText().toString().trim();
        String senha = edtsenha.getText().toString().trim();
        String confirma_senha = edtconfirmasenha.getText().toString().trim();
        final String nome = edtnome.getText().toString().trim();
        final String apelido = edtapelido.getText().toString().trim();
        final int nivel = Integer.parseInt(edtnivel.getText().toString().trim());
        final int pontos = Integer.parseInt(edtpontos.getText().toString().trim());

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(senha) || TextUtils.isEmpty(nome) || TextUtils.isEmpty(apelido) || TextUtils.isEmpty(confirma_senha))
        {
            Toast.makeText(this,"Preencha todos os campos",Toast.LENGTH_LONG).show();

            return;
        }
       if (!TextUtils.isEmpty(email))
           {
             String validaremail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                     "\\@" +
                     "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                     "(" +
                     "\\." +
                     "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                     ")+";

               Matcher matcher = Pattern.compile(validaremail).matcher(email);

               if(matcher.matches())
               {

                   if (senha.length() >=6)
                   {
                       if (senha.equals(confirma_senha))
                       {
                           progressDialog.setMessage("Registrando Usu??rio...");
                           progressDialog.show();


                           firebaseAuth.createUserWithEmailAndPassword(email, senha)
                                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                                   {
                                       @Override
                                       public void onComplete(@NonNull Task<AuthResult> task)
                                       {
                                           if(task.isSuccessful())
                                           {
                                               String id = firebaseAuth.getUid();
                                               Usuario user = new Usuario();

                                               user.setNome(nome);
                                               user.setEmail(email);
                                               user.setApelido(apelido);
                                               user.setIdUsuario(id);

                                               databaseReference
                                                       .child(FirebaseAuth.getInstance()
                                                               .getCurrentUser().getUid()).setValue(user)
                                                       .addOnCompleteListener(new OnCompleteListener<Void>()
                                                       {
                                                           @Override
                                                           public void onComplete(@NonNull Task<Void> task)
                                                           {
                                                               if (task.isSuccessful())
                                                               {

                                                                   Userscore score = new Userscore();

                                                                   score.setNivel(nivel);
                                                                   score.setPontos(pontos);
                                                                   score.setApelido(apelido);

                                                                   FirebaseDatabase.getInstance().getReference("Userscore")
                                                                           .child(FirebaseAuth.getInstance().getCurrentUser()
                                                                                   .getUid()).setValue(score).addOnCompleteListener(new OnCompleteListener<Void>()
                                                                   {
                                                                       @Override
                                                                       public void onComplete(@NonNull Task<Void> task)
                                                                       {
                                                                           if (task.isSuccessful())
                                                                           {
                                                                               finish();
                                                                               startActivity(new Intent(getApplicationContext(),MainFragmentMenu.class));

                                                                               Toast.makeText(MainActivity.this,"Registrado com Sucesso", Toast.LENGTH_LONG).show();
                                                                               progressDialog.dismiss();
                                                                           }
                                                                           else
                                                                           {
                                                                               progressDialog.dismiss();
                                                                               Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                                           }

                                                                       }
                                                                   });




                                                               }
                                                               else
                                                               {
                                                                   progressDialog.dismiss();
                                                                   Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                               }
                                                           }
                                                       });



                                               //limparCampos();
                                           }
                                           else
                                           {
                                               progressDialog.dismiss();
                                               Toast.makeText(MainActivity.this,"Email j?? cadastrado", Toast.LENGTH_LONG).show();

                                           }
                                       }
                                   });
                       }
                       else
                       {
                           Toast.makeText(MainActivity.this,"A senha e a confirma????o de senha devem ser iguais", Toast.LENGTH_LONG).show();
                       }
                   }
                   else
                   {
                       Toast.makeText(MainActivity.this,"A senha deve conter 6 caracteres no min??mo", Toast.LENGTH_LONG).show();
                   }

               }
               else
                   {
                       Toast.makeText(MainActivity.this,"DIgite um email Valido", Toast.LENGTH_LONG).show();
                   }
           }



    }

    @Override
    public void onClick(View view)
    {
        if (view == registrar)
        {

            registerUser();

        }
        if (view == txtviewLogin)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

    private void limparCampos()
    {
        edtemail.setText("");
        edtsenha.setText("");
    }

}
