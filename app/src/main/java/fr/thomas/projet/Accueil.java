package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

        String url = "jdbc:mysql://miroredge.freeboxos.fr:49999/ufr_asso";
        String s = "";

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Connection connect = DriverManager.getConnection(url, "ROOT", "root");
            Statement statement = connect.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT PSD FROM usr WHERE EML = '"+a+"'");

            while(resultset.next()){
                s=resultset.getString(1);

            }
            messagebienvenue.setText("Bienvenue " + s);

        } catch (SQLException e) {
            e.printStackTrace();
        }



        /*this.associations = findViewById(R.id.MessageDeBienvenue);
        associations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Associations.class);
                startActivity(openActivity);
            }
        });*/
        ListView resultsListView = (ListView) findViewById(R.id.listview_acceuil);

        HashMap<String, String> nameAddresses = new HashMap<>();
        nameAddresses.put("Notification", "Association 'NouvelleAsso' rejoint.");
        nameAddresses.put("Profil", "Votre profil a ??t?? cr??e.");
        nameAddresses.put("Association", "Tom a rejoint 'AssoTest.'");

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.liste_items_acceuil,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.text1, R.id.text2});


        Iterator it = nameAddresses.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        resultsListView.setAdapter(adapter);

    }
}