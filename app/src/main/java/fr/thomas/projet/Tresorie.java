package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tresorie extends AppCompatActivity {

    private TextView solde;

    double money=0;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    String SIRET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tresorie);

        SharedPreferences sharedPreff = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String a = sharedPreff.getString("EML", "");

        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        SIRET = sharedPref.getString("assoSIR", "");

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.solde=findViewById(R.id.SoldeTrésorie);

        String url = "jdbc:mysql://miroredge.freeboxos.fr:49999/ufr_asso";

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Connection connect = DriverManager.getConnection(url, "ROOT", "root");
            Statement statement = connect.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT SUM(A.AMT) - SUM(B.AMT) FROM boo_ety as A JOIN boo_ety as B WHERE A.ROW_IDT = B.ROW_IDT AND A.ASO_ROW_IDT=B.ASO_ROW_IDT AND A.TYP='INCOME' AND B.TYP='PAYMENT'");

            //------CHANGER USR ET ASO AVEC LES DATA ------Pas bonne requete

            while(resultset.next()){
                money=resultset.getDouble(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        solde.setText("Solde : "+String.format("%.2f",money)+" €");
    }
}