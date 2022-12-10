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
                if(email.getText().toString().equals("root") && password.getText().toString().equals("root")) {

                    //Utilisateur user = new Utilisateur(email.getText().toString(),password.getText().toString());

                    Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                    startActivity(openActivity);
                    finish();
                }
                else{
                    erreurmessagelogin.setText("Erreur: E-mail ou Mot de passe incorrect.");
                }
            }
        });
    }
}