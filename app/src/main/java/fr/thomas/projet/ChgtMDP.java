package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class ChgtMDP extends AppCompatActivity {

    TextView erreur;

    private View Valide;

    EditText MDP;
    EditText NEW;
    EditText CONFIRM;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_chgtmdp);


        this.erreur = (TextView) findViewById(R.id.messErreur);

        this.Valide = findViewById(R.id.valideChgt);
        Valide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MDP = (EditText) findViewById(R.id.ActuelMDP);
                NEW = (EditText) findViewById(R.id.NewMDP);
                CONFIRM = (EditText) findViewById(R.id.ConfirmMDP);

                if(MDP.getText().toString().isEmpty() || NEW.getText().toString().isEmpty() || CONFIRM.getText().toString().isEmpty()){
                    erreur.setText("Erreur: Veuillez remplir tous les champs");

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