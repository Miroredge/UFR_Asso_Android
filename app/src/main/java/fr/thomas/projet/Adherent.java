package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Adherent extends AppCompatActivity {

    private View accueil;
    private View associations;
    private View profile;

    GridView grid;

    ArrayList<String> listName=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_adherants);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //----------- Bouton accueil -----------

        this.accueil = findViewById(R.id.Accueil_button);
        accueil.setOnClickListener(new View.OnClickListener() {
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

//------------------------------------- PAGE ET NAVBAR -----------------------------------------

        String url = "jdbc:mysql://miroredge.freeboxos.fr:49999/ufr_asso";

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Connection connect = DriverManager.getConnection(url, "ROOT", "root");
            Statement statement = connect.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT FST_NAM,LST_NAM,PSD,EML FROM usr ");

            while(resultset.next()){
                listName.add(resultset.getString("FST_NAM"));
                listName.add(resultset.getString("LST_NAM"));
                listName.add(resultset.getString("PSD"));
                listName.add(resultset.getString("EML"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        grid=(GridView)findViewById(R.id.grid);
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listName);
        grid.setAdapter(adapter);

    }
}