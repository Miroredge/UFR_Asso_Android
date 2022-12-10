package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class Inscription extends AppCompatActivity {

    private View login;
    private View accueil;
    LinearLayout layout;

    EditText NomComplet;
    EditText Email;
    EditText NumTel;
    EditText Pseudo;
    EditText Mdp;
    EditText MdpVerify;
    CheckBox CheckBoxRegles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_register);
        getSupportActionBar().hide();

        //----------- Boutton connexion (Retour) -----------

        this.login = findViewById(R.id.Retour);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(openActivity);
                finish();
            }
        });

        //----------- Création du text erreur -----------
        this.layout = (LinearLayout) findViewById(R.id.LinearLayoutRegister);
        TextView erreur = new TextView(this);
        erreur.setText("Erreur: E-mail ou mot de pass incorect.");
        erreur.setTextColor(getResources().getColor(R.color.bleuCustomFonce));
        erreur.setTextSize(25);

        //----------- Boutton s'inscrire -----------

        this.accueil = findViewById(R.id.valider);
        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NomComplet = (EditText) findViewById(R.id.NomCompletRegisterPage);
                Email = (EditText) findViewById(R.id.EmailRegisterPage);
                NumTel = (EditText) findViewById(R.id.NumTelephoneRegisterPage);
                Pseudo = (EditText) findViewById(R.id.PseudoRegisterPage);
                Mdp = (EditText) findViewById(R.id.MotDePasseRegisterPage);
                MdpVerify = (EditText) findViewById(R.id.VerificationMotDePasseRegisterPage);
                CheckBoxRegles = (CheckBox) findViewById(R.id.CheckBoxRegles);
                if(NomComplet.getText().toString().isEmpty() || Email.getText().toString().isEmpty() || NumTel.getText().toString().isEmpty()
                || Pseudo.getText().toString().isEmpty() || Mdp.getText().toString().isEmpty() || MdpVerify.getText().toString().isEmpty() || !CheckBoxRegles.isChecked()){
                    layout.addView(erreur);
                }
                else{
                    Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                    startActivity(openActivity);
                    finish();
                }
            }
        });
    }
}