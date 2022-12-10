package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class Adherant extends AppCompatActivity {

    private View acceuil;
    private View associations;
    private View profile;

    GridView grid;
    String[] tab = new String[60];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_adherants);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //----------- Bouton accueil -----------

        this.acceuil = findViewById(R.id.Accueil_button);
        acceuil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                startActivity(openActivity);
                finish();
            }
        });

        //----------- Bouton Associations -----------

        this.associations = findViewById(R.id.Association_Button);
        associations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Associations.class);
                startActivity(openActivity);
                finish();
            }
        });

        //----------- Bouton Profile -----------

        this.profile = findViewById(R.id.Profile_button);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Profile.class);
                startActivity(openActivity);
                finish();
            }
        });
//------------------------------------- PAGE ET NAVBAR -----------------------------------------
        //Autres

        for(int i=0;i<tab.length;i+=4){
            tab[i]="Paul";
            tab[i+1]="Janvier";
            tab[i+2]="L2";
            tab[i+3]="2022/11/18";
        }

        grid=(GridView)findViewById(R.id.grid);
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,tab);
        grid.setAdapter(adapter);

    }
}