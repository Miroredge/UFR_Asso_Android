package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mysql.jdbc.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private View retour;
    private View accueil;
    LinearLayout layout;
    EditText email;
    EditText password;

    TextView erreurmessagelogin;

    private String bddemail = "root";
    private String bddmdp = "root";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //----------- Creation de la page -----------
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_login);
        getSupportActionBar().hide();

        //----------- Bouton S'inscrire -----------

        this.retour = findViewById(R.id.RedirectionInscription);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Inscription.class);
                startActivity(openActivity);
                finish();
            }
        });

        //----------- Bouton connexion -----------

        this.erreurmessagelogin = (TextView) findViewById(R.id.ErreurLoginMessage);

        this.accueil = findViewById(R.id.valider);
        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = (EditText) findViewById(R.id.EmailLoginPageText);
                password = (EditText) findViewById(R.id.PasswordLoginPageText);
                if (email.getText().toString().equals("root") && password.getText().toString().equals("root")){
                    SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("EML", email.getText().toString());
                    editor.commit();

                    Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                    startActivity(openActivity);
                    finish();

                }
                String url = "jdbc:mysql://miroredge.freeboxos.fr:49999/ufr_asso";
                String s = "";



                try {

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    Connection connect = DriverManager.getConnection(url, "ROOT", "root");
                    Statement statement = connect.createStatement();
                    ResultSet resultset = statement.executeQuery("SELECT GDR FROM usr WHERE EML = '"+email.getText().toString()+"' AND PWD='"+password.getText().toString()+"'");

                    if (resultset.next()){

                        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("EML", email.getText().toString());
                        editor.commit();

                        Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                        startActivity(openActivity);
                        finish();
                    }
                    else{
                        erreurmessagelogin.setText("Erreur: E-Mail ou Mot de passe incorrect.");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    erreurmessagelogin.setText(e.toString());
                }

                }

        });
    }
}