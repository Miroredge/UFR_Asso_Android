package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InfoAssociation extends AppCompatActivity {

    private View adherents;
    private View evenement;
    private View tresorie;
    private TextView nomMonAsso;
    private TextView siretMonAsso;

    String siretAso;

    private View quitter;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    String SIRET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_association);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String url = "jdbc:mysql://miroredge.freeboxos.fr:49999/ufr_asso";

        SharedPreferences sharedPreff = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String a = sharedPreff.getString("EML", "");

        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        SIRET = sharedPref.getString("assoSIR", "");

        siretMonAsso= findViewById(R.id.SiretMonAsso);
        nomMonAsso= findViewById(R.id.NomMonAsso);

        siretMonAsso.setText("Siret : "+SIRET);

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Connection connect = DriverManager.getConnection(url, "ROOT", "root");
            Statement statement = connect.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT NAM FROM aso Where SIR_NBR='"+SIRET+"'");

            while(resultset.next()){

                siretAso=resultset.getString(1);

            }

            nomMonAsso.setText("Nom : "+siretAso);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //----------- Bouton Adh??rants-----------

        this.adherents = findViewById(R.id.BouttonAdh??rants);
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

        //----------- Bouton Quitter-----------



        this.quitter = findViewById(R.id.btQuitter);
        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    try {

                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        Connection connect = DriverManager.getConnection(url, "ROOT", "root");
                        Statement statement = connect.createStatement();
                        statement.executeUpdate("DELETE FROM usr_has_aso_and_rol WHERE USR_ROW_IDT = (SELECT `ROW_IDT` FROM USR WHERE EML = '"+a+"') AND ASO_ROW_IDT = (SELECT `ROW_IDT` FROM ASO WHERE `SIR_NBR` = '"+SIRET+"');");

                    } catch (SQLException e) {
                        e.printStackTrace();

                    }

                Intent openActivity = new Intent(getApplicationContext(), Associations.class);
                startActivity(openActivity);
            }
        });

    }
}