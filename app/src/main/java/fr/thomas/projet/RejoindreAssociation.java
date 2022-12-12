package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_rejoindre_assocition);

        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String a = sharedPref.getString("EML", "");

        //----------- Requete Nom Asso -----------

        String url = "jdbc:mysql://miroredge.freeboxos.fr:49999/ufr_asso";
        String s = "";
        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Connection connect = DriverManager.getConnection(url, "ROOT", "root");
            Statement statement = connect.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT aso.NAM FROM aso WHERE aso.nam NOT IN (SELECT aso.nam FROM aso INNER JOIN usr_has_aso_and_rol ON ASO_ROW_IDT = aso.ROW_IDT INNER JOIN usr ON USR_ROW_IDT = usr.ROW_IDT INNER JOIN rol ON ROL_ROW_IDT = rol.ROW_IDT WHERE usr.`EML` = '"+a+"' and `ASO_ROW_IDT` IS NOT NULL and `ROL_ROW_IDT` = (SELECT `ROW_IDT` FROM ROL WHERE `NAM` = 'MEMBER'));");


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

                    try {

                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        Connection connect = DriverManager.getConnection(url, "ROOT", "root");
                        Statement statement = connect.createStatement();
                        statement.executeUpdate("INSERT INTO usr_has_aso_and_rol (USR_ROW_IDT, ASO_ROW_IDT, ROL_ROW_IDT, CRE_ID, CRE_DAT, UPD_ID, UPD_DAT) VALUES ((Select ROW_IDT From usr Where EML='"+a+"'), (Select ROW_IDT From aso Where NAM='"+item+"'), (SELECT ROW_IDT FROM rol WHERE NAM = 'MEMBER'), 'INIT_SCRIPT', NOW(), 'INIT_SCRIPT', NOW() )");


                    } catch (SQLException e) {
                        e.printStackTrace();

                    }

                    Toast.makeText(getApplicationContext(), "Vous avez rejoins : " + item, Toast.LENGTH_SHORT).show();
                    Intent openActivity = new Intent(getApplicationContext(), Associations.class);
                    startActivity(openActivity);
                    finish();
                }
            }
        });

    }
}