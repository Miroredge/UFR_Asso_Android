package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Evenement extends AppCompatActivity {

    private View retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //----------- Creation de la page ---------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_evenement);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //----------- Bouton Retour -----------

        this.retour = findViewById(R.id.Retour_Button_From_Evenement_To_Associations);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), Associations.class);
                startActivity(openActivity);
                finish();
            }
        });
    }
}