package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Associations extends AppCompatActivity {

    private View acceuil;
    private View associations;
    private View profile;

    private View adherants;
    private View evenement;
    private View tresorie;

    ListView listview;
    List list = new ArrayList();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //----------- Creation de la page ---------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_associations);
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

        //----------- Bouton Adhérants-----------

        this.adherants = findViewById(R.id.BouttonAdhérants);
        adherants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Adherant.class);
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

        //----------- Bouton Tresorie-----------

        this.tresorie = findViewById(R.id.BouttonTresorie);
        tresorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Tresorie.class);
                startActivity(openActivity);
            }
        });


        //----------- List View -----------

        listview = (ListView) findViewById(R.id.ListView_Evenement);
        list.add("Association 1");
        list.add("Association 2");
        list.add("Association 3");
        list.add("Association 4");
        list.add("Association 5");
        list.add("Association 6");

        adapter = new ArrayAdapter(Associations.this, android.R.layout.simple_list_item_1,list);
        listview.setAdapter(adapter);


    }
}