package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class InfoAssociation extends AppCompatActivity {

    private View adherents;
    private View evenement;
    private View tresorie;
    private TextView nomMonAsso;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    String SIRET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_association);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        SIRET = sharedPref.getString("assoSIR", "");

        nomMonAsso= findViewById(R.id.NomMonAsso);

        nomMonAsso.setText(SIRET);


        //----------- Bouton Adhérants-----------

        this.adherents = findViewById(R.id.BouttonAdhérants);
        adherents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Adherent.class);
                startActivity(openActivity);
            }
        });

        //----------- Bouton Evenement-----------

        this.evenement = findViewById(R.id.BouttonEvenement);
        evenement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Evenement.class);
                startActivity(openActivity);
            }
        });

    }
}