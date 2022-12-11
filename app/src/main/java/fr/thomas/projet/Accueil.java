package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Accueil extends AppCompatActivity{

    private View acceuil;
    private View associations;
    private View profile;

    private TextView messagebienvenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//------------------------------------- PAGE ET NAVBAR -----------------------------------------
        //----------- Creation de la page ---------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_acceuil);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //----------- Bouton accueil -----------

        this.acceuil = findViewById(R.id.Accueil_button);
        acceuil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                startActivity(openActivity);
            }
        });

        //----------- Bouton Associations -----------

        this.associations = findViewById(R.id.Association_Button);
        associations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Associations.class);
                startActivity(openActivity);
            }
        });

        //----------- Bouton Profile -----------

        this.profile = findViewById(R.id.Profile_button);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Profile.class);
                startActivity(openActivity);
            }
        });
//------------------------------------- PAGE ET NAVBAR -----------------------------------------
//------------------------------------- CONTENUE DE LA PAGE ------------------------------------

    //-----Message de bienvenue-----

        this.messagebienvenue = (TextView) findViewById(R.id.MessageDeBienvenue);
        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String a = sharedPref.getString("EML", "");
        messagebienvenue.setText(a);

        this.associations = findViewById(R.id.MessageDeBienvenue);
        associations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Associations.class);
                startActivity(openActivity);
            }
        });

    }
}