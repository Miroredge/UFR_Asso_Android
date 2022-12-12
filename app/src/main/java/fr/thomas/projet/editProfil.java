package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class editProfil extends AppCompatActivity {

    private View acceuil;
    private View associations;
    private View profile;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.layout_editprofil);
        //----------- Bouton accueil -----------

        this.acceuil = findViewById(R.id.Accueil_button);
        acceuil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                startActivity(openActivity);
            }
        });

        //----------- Bouton Associations -----------

        this.associations = findViewById(R.id.Association_Button);
        associations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Associations.class);
                startActivity(openActivity);
            }
        });

        //----------- Bouton Profile -----------

        this.profile = findViewById(R.id.Profile_button);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Profile.class);
                startActivity(openActivity);
            }
        });


    }

}
