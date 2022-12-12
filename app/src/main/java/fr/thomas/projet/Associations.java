package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Associations extends AppCompatActivity {

    private View acceuil;
    private View associations;
    private View profile;

    private View adherents;
    private View evenement;
    private View tresorie;
    private View create;
    private View rejoindre;

    ListView listview;
    List list = new ArrayList<>();
    ArrayAdapter adapter;
    private ArrayAdapter<String> listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //----------- Creation de la page ---------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_associations);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String a = sharedPref.getString("EML", "");

        String url = "jdbc:mysql://miroredge.freeboxos.fr:49999/ufr_asso";


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



        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Connection connect = DriverManager.getConnection(url, "ROOT", "root");
            Statement statement = connect.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT usr.`PSD`, usr.`EML`, aso.`NAM` FROM usr INNER JOIN usr_has_aso_and_rol on USR_ROW_IDT = usr.ROW_IDT INNER JOIN aso ON ASO_ROW_IDT = aso.ROW_IDT WHERE usr.`EML` = '"+a+"'");

            while(resultset.next()){
                list.add(resultset.getString("NAM"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<String> listData = new ArrayList<>();
        listview = (ListView) findViewById(R.id.ListView_UsrAsso);

        adapter = new ArrayAdapter(Associations.this, android.R.layout.simple_list_item_1,list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                startActivity(openActivity);
            }
        });

        //----------- Bouton Creation asso-----------

        this.create = findViewById(R.id.CreateAsso);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), CreateAssociation.class);
                startActivity(openActivity);
            }
        });

        //----------- Bouton Rejoindre asso-----------

        this.rejoindre = findViewById(R.id.RejoindreAsso);
        rejoindre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), RejoindreAssociation.class);
                startActivity(openActivity);
            }
        });

    }
}