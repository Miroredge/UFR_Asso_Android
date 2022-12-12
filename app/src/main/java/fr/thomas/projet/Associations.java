package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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


    int countArrayForSharedPref = 0;
    //private Context context = getApplicationContext();
    //SharedPreferences sharedPref = context.getSharedPreferences("my_prefs", MODE_PRIVATE);
    //SharedPreferences.Editor editor = prefs.edit();
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //----------- Creation de la page ---------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_associations);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
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

        //----------- List View -----------



        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Connection connect = DriverManager.getConnection(url, "ROOT", "root");
            Statement statement = connect.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT usr.`PSD`, usr.`EML`, aso.`NAM`, aso.`SIR_NBR` FROM usr INNER JOIN usr_has_aso_and_rol on USR_ROW_IDT = usr.ROW_IDT INNER JOIN aso ON ASO_ROW_IDT = aso.ROW_IDT WHERE usr.`EML` = '"+a+"'");

            while(resultset.next()){
                list.add(resultset.getString("NAM"));

                editor.putString("assoSIR_"+Integer.toString(countArrayForSharedPref), resultset.getString("SIR_NBR"));
                editor.commit();
                countArrayForSharedPref++;

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

                editor.putString("assoSIR", sharedPref.getString("assoSIR_"+Integer.toString(i), ""));

                for(int j = 0; j < countArrayForSharedPref; j++){
                    editor.remove("assoSIR_"+j);
                }
                countArrayForSharedPref = 0;
                editor.commit();

                Intent openActivity = new Intent(getApplicationContext(), InfoAssociation.class);
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