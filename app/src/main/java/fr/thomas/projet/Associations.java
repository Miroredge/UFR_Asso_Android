package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Associations extends AppCompatActivity {

    private View associations;
    private Button adhérents;
    private Button trésorie;
    private Button event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_associations);

        this.adhérents=findViewById(R.id.btAdherents);

        adhérents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),Adherant.class);
                startActivity(otherActivity);
                finish();
            }
        });

        this.trésorie=findViewById(R.id.btTresorie);


        trésorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),Tresorie.class);
                startActivity(otherActivity);
                finish();
            }
        });

        this.event=findViewById(R.id.btEvent);

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),Evenement.class);
                startActivity(otherActivity);
                finish();
            }
        });

    }
}