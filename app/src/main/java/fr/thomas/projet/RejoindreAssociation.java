package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RejoindreAssociation extends AppCompatActivity {

    TextView ErreurSelectAsso;

    private View btJoinAsso;


    ArrayList<String> assos=new ArrayList<>();
    String item ="";

    AutoCompleteTextView AutoCompleteTxt;

    ArrayAdapter<String> adapterItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_rejoindre_assocition);

        //----------- Requete Nom Asso -----------

        String url = "jdbc:mysql://miroredge.freeboxos.fr:49999/ufr_asso";
        String s = "";
        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Connection connect = DriverManager.getConnection(url, "ROOT", "root");
            Statement statement = connect.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT NAM FROM aso");


            while(resultset.next()) {
                assos.add(resultset.getString("NAM"));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }


        AutoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItem=new ArrayAdapter<String>(this,R.layout.list_rejoindre,assos);

        AutoCompleteTxt.setAdapter(adapterItem);

        AutoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                item = parent.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Association :"+item,Toast.LENGTH_SHORT).show();
            }

        });

        this.ErreurSelectAsso = (TextView) findViewById(R.id.ErreurSelectAsso);

        this.btJoinAsso = findViewById(R.id.btJoinAsso);
        btJoinAsso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item==""){
                    ErreurSelectAsso.setText("Vous devez sélectionner une association");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Vous avez rejoin : " + item, Toast.LENGTH_SHORT).show();
                    Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                    startActivity(openActivity);
                    finish();
                }
            }
        });

    }
}