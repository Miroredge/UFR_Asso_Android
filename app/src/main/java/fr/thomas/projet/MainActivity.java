package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private View retour;
    private View accueil;
    LinearLayout layout;
    EditText email;
    EditText password;

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



        //----------- Création du text erreur -----------
        this.layout = (TableLayout) findViewById(R.id.TableLayoutLogin);
        TextView erreur = new TextView(this);
        erreur.setText("Erreur: E-mail ou mot de pass incorect.");
        erreur.setTextColor(getResources().getColor(R.color.bleuCustomFonce));
        erreur.setTextSize(25);


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

        this.accueil = findViewById(R.id.valider);
        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = (EditText) findViewById(R.id.EmailLoginPageText);
                password = (EditText) findViewById(R.id.PasswordLoginPageText);
                if(email.getText().toString().equals("root") && password.getText().toString().equals("root")) {
                    Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                    startActivity(openActivity);
                    finish();
                }
                else{
                    layout.addView(erreur);
                }
            }
        });
    }
}