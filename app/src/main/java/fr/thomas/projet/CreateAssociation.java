package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAssociation extends AppCompatActivity {

    TextView erreur;

    private View Valide;

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

        this.Valide = findViewById(R.id.valideNewAsso);
        Valide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Nom = (EditText) findViewById(R.id.CreateNomAsso);
                Location = (EditText) findViewById(R.id.CreateLocation);
                Siret = (EditText) findViewById(R.id.CreateSiret);
                Prix = (EditText) findViewById(R.id.NomCompletRegisterPage);

                if(Nom.getText().toString().isEmpty() || Location.getText().toString().isEmpty() || Siret.getText().toString().isEmpty() || Prix.getText().toString().isEmpty() ){
                    erreur.setText("Erreur: Veuillez remplir tous les champs");
                }
                else{
                    erreur.setText("Création effectué.");
                }
            }

        });
    }
}