package fr.thomas.projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Accueil extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //----------- Creation de la page ---------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_navigation_main);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        navigationView = findViewById(R.id.navbar_bottom);
        getSupportFragmentManager().beginTransaction().replace(R.id.body, new Acceuil_fragment()).commit();
        navigationView.setSelectedItemId(R.id.navigation_menu);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.navigation_menu:
                        fragment = new Acceuil_fragment();
                        break;

                    case R.id.navigation_association:
                        fragment = new Associations_fragment();
                        break;

                    case R.id.navigation_profile:
                        fragment = new Profile_fragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body, fragment).commit();

                return true;
            }
        });

    }
}