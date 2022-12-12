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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.security.MessageDigest;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;



public class Inscription extends AppCompatActivity {

    private View login;
    private View accueil;
    LinearLayout layout;

    TextView erreurmessageregister;

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

        //----------- Boutton s'inscrire -----------

        this.erreurmessageregister = (TextView) findViewById(R.id.ErreurRegisterMessage);

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

                //Email.getText().toString();
                //Mdp.getText().toString();
                //MdpVerify.getText().toString();
                //NumTel.getText().toString();

                String url = "jdbc:mysql://miroredge.freeboxos.fr:49999/ufr_asso";


                try {

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    Connection connect = DriverManager.getConnection(url, "ROOT", "root");
                    Statement statement = connect.createStatement();
                    ResultSet resultset = statement.executeQuery("SELECT * FROM usr WHERE EML = '"+Email.getText().toString()+"'");

                    if(NomComplet.getText().toString().isEmpty() || Email.getText().toString().isEmpty() || NumTel.getText().toString().isEmpty() || Pseudo.getText().toString().isEmpty() || Mdp.getText().toString().isEmpty() || MdpVerify.getText().toString().isEmpty() || !CheckBoxRegles.isChecked()) {
                        erreurmessageregister.setText("Erreur: Veuillez remplir tous les champs");
                    }
                    else if (resultset.next()){
                        erreurmessageregister.setText("Erreur: E-Mail déjà utilisé.");
                    }
                    else if(statement.executeQuery("SELECT * FROM usr WHERE PSD = '"+Pseudo.getText().toString()+"'").next()){
                        erreurmessageregister.setText("Erreur: Pseudo déjà utilisé.");
                    }
                    else{
                        if(Mdp.getText().toString().equals(MdpVerify.getText().toString())){
                            String pseudo = Pseudo.getText().toString();
                            String email = Email.getText().toString();
                            String phone = NumTel.getText().toString();
                            String nom = NomComplet.getText().toString();
                            String mdp = Mdp.getText().toString();

                            String id = "";

                            for(int i = 0; i < 10; i++) {
                                Random r = new Random();
                                int low = 0;
                                int high = 10;
                                int result = r.nextInt(high-low) + low;
                                id += ""+result;
                            }

                            SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("EML", email);
                            editor.commit();

                            statement.executeUpdate("INSERT INTO usr (PSD, LST_NAM, FST_NAM, STU_NBR, GDR, EML, PHN_NBR, PHN_BOK, PWD, TMP_PWD, NTF, PRF_PIC, CRE_ID, CRE_DAT, UPD_ID, UPD_DAT) VALUES ('"+pseudo+"','"+nom+"','"+nom+"', '"+id+"', 'O', '"+email+"', '"+phone+"', TRUE, '"+mdp+"', TRUE, FALSE, NULL,'INIT_SCRIPT', NOW(), 'INIT_SCRIPT', NOW())");
                            Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                            startActivity(openActivity);
                            finish();
                        }
                        else{
                            erreurmessageregister.setText("Erreur: Les mots de passe ne correspondent pas.");
                        }
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    erreurmessageregister.setText(e.toString());
                }
            }
        });
    }
}