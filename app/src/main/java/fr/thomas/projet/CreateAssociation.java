package fr.thomas.projet;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAssociation extends AppCompatActivity {

    TextView erreur;

    private View valide;

    EditText Nom;
    EditText Location;
    EditText Siret;
    EditText Prix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_create_association);


        this.erreur = (TextView) findViewById(R.id.messErr);

        this.valide = findViewById(R.id.valideNewAsso);
        valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nom = (EditText) findViewById(R.id.CreateNomAsso);
                Location = (EditText) findViewById(R.id.CreateLocation);
                Siret = (EditText) findViewById(R.id.CreateSiret);
                Prix = (EditText) findViewById(R.id.CreatePrix);

                String url = "jdbc:mysql://miroredge.freeboxos.fr:49999/ufr_asso";

                if(Nom.getText().toString().isEmpty() || Location.getText().toString().isEmpty() || Siret.getText().toString().isEmpty() || Prix.getText().toString().isEmpty()){
                    erreur.setText("Erreur: Veuillez remplir tous les champs.");
                }
                else{

                    try {

                        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                        String email = sharedPref.getString("EML", "");

                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        Connection connect = DriverManager.getConnection(url, "ROOT", "root");
                        Statement statement = connect.createStatement();

                        String nom = Nom.getText().toString();
                        String location = Location.getText().toString();
                        String siret = Siret.getText().toString();
                        String prix = Prix.getText().toString();

                        statement.executeUpdate("INSERT INTO ufr_asso.aso (SIR_NBR, NAM, LOC, LGO, MBR_PCE, CRE_ID, CRE_DAT, UPD_ID, UPD_DAT) VALUES ('"+siret+"', '"+nom+"', '"+location+"', null, '"+prix+"', 'CREATION ASSO', Now(), 'CREATION ASSO', Now());");
                        statement.executeUpdate("INSERT INTO ufr_asso.usr_has_aso_and_rol (USR_ROW_IDT, ASO_ROW_IDT, ROL_ROW_IDT, CRE_ID, CRE_DAT, UPD_ID, UPD_DAT) VALUES ((SELECT ROW_IDT FROM USR WHERE usr.`EML` = '"+email+"'), (SELECT ROW_IDT FROM ASO WHERE aso.`NAM` = '"+nom+"'), (SELECT ROW_IDT FROM ROL WHERE NAM = 'ADMIN'), 'CREATION ASSO by User', Now(), 'CREATION ASSO by User', Now());");

                        erreur.setTextColor(getResources().getColor(R.color.Green));
                        erreur.setText("Votre association a été créée.");

                        Intent openActivity = new Intent(getApplicationContext(), Associations.class);
                        startActivity(openActivity);

                    } catch (SQLException e) {
                        e.printStackTrace();
                        erreur.setText(e.toString());
                    }

                }
            }
        });

    }
}