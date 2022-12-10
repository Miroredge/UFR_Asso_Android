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

public class Evenement extends AppCompatActivity {

    private View acceuil;
    private View associations;
    private View profile;

    ListView listview;
    List list = new ArrayList();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //----------- Creation de la page ---------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_evenement);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //----------- Bouton accueil -----------

        this.acceuil = findViewById(R.id.Accueil_button);
        acceuil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                startActivity(openActivity);
                finish();
            }
        });

        //----------- Bouton Associations -----------

        this.associations = findViewById(R.id.Association_Button);
        associations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Associations.class);
                startActivity(openActivity);
                finish();
            }
        });

        //----------- Bouton Profile -----------

        this.profile = findViewById(R.id.Profile_button);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Profile.class);
                startActivity(openActivity);
                finish();
            }
        });

        //----------- List View -----------

        listview = (ListView) findViewById(R.id.ListView_Evenement);
        list.add("Text");
        list.add("Text");
        list.add("Text");
        list.add("Text");
        list.add("Text");
        list.add("Text");
        list.add("Text");
        list.add("Text");
        list.add("Text");
        list.add("Text");
        list.add("Text");
        list.add("Text");
        list.add("Text");
        list.add("Text");
        list.add("Text");

        adapter = new ArrayAdapter(Evenement.this, android.R.layout.simple_list_item_1,list);
        listview.setAdapter(adapter);
    }
}