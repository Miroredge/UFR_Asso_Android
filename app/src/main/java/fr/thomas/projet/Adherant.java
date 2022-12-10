package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class Adherant extends AppCompatActivity {

    GridView grid;

    String[] tab = new String[60];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_adherants);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

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