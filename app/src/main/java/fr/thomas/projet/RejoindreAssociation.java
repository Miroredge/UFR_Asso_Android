package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class RejoindreAssociation extends AppCompatActivity {

    TextView ErreurSelectAsso;

    private View btJoinAsso;

    String[] asso = {"asso1","asso2","asso3","asso4","asso5","asso6","asso7","asso6","asso7"};
    String item ="";

    AutoCompleteTextView AutoCompleteTxt;

    ArrayAdapter<String> adapterItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_rejoindre_assocition);

        AutoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItem=new ArrayAdapter<String>(this,R.layout.list_rejoindre,asso);

        AutoCompleteTxt.setAdapter(adapterItem);

        AutoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                item = parent.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Association :"+item,Toast.LENGTH_SHORT).show();
            }



        });

        this.ErreurSelectAsso = (TextView) findViewById(R.id.ErreurSelectAsso);

        this.btJoinAsso = findViewById(R.id.btJoinAsso);
        btJoinAsso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item==""){
                    ErreurSelectAsso.setText("Vous devez sélectionner une association");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Vous avez rejoin : " + item, Toast.LENGTH_SHORT).show();
                    Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                    startActivity(openActivity);
                    finish();
                }
            }
        });

    }
}