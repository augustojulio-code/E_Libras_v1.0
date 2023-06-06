package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.recursos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.example.e_libas_v_0_01.ActivitySlides;
import com.example.e_libas_v_0_01.MainFragmentMenu;
import com.example.e_libas_v_0_01.R;

public class Recursos {


    public void ratingBar(final Context context, int acertos){

        RatingBar ratingBar = new RatingBar(context);
        LinearLayout layout = new LinearLayout(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(12,12,12,12);

        ratingBar.setLayoutParams(lp);
        ratingBar.setNumStars(5);
        ratingBar.setRating(acertos);
        ratingBar.setEnabled(false);

        layout.addView(ratingBar);

        builder.setIcon(R.mipmap.elibraslogo);
        builder.setTitle(acertoMensagem(acertos));
        builder.setView(layout);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Fragment fragment = new Fragment();
                Activity activity = new Activity();
                activity.startActivity(new Intent(fragment.getContext(), MainFragmentMenu.class));

            }
        });
        builder.show();

    }

    public String acertoMensagem( int acertos){
        String mensagem;
        if(acertos == 0 || acertos <=2){

            mensagem="Continue tentando :D"; // voce pode melhorar, continue tentanto ...
        }
        else if(acertos==3 || acertos ==4){
            mensagem ="Muito bem";
        }
        else{
            mensagem = "Perfeito";
        }
        return mensagem;
    }

    public void sleepTime(final Fragment fragment){
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {


            }
        },2500);
    }

}
