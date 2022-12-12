package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tresorie);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.solde=findViewById(R.id.SoldeTrésorie);

        String url = "jdbc:mysql://miroredge.freeboxos.fr:49999/ufr_asso";

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Connection connect = DriverManager.getConnection(url, "ROOT", "root");
            Statement statement = connect.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT A.MBR_PCE FROM aso A,usr_has_aso_and_rol B,usr C WHERE A.ROW_IDT=B.ASO_ROW_IDT AND C.ROW_IDT=B.USR_ROW_IDT");

            //------CHANGER USR ET ASO AVEC LES DATA ------Pas bonne requete

            while(resultset.next()){
                money=resultset.getDouble("MBR_PCE");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        solde.setText("Solde : "+String.format("%.2f",money)+" €");
    }
}