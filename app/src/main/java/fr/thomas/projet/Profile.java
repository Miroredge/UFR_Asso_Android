package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Blob;
import java.io.ByteArrayInputStream;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Profile extends AppCompatActivity {

    private View acceuil;
    private View associations;
    private View profile;
    private TextView link;
    private View create;
    private View deco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //----------- Creation de la page ---------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profil);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
                Intent openActivity = new Intent(getApplicationContext(), Accueil.class);
                startActivity(openActivity);
            }
        });

        //----------- Bouton Deconnexion -----------

        this.deco = findViewById(R.id.deconnexion);
        deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(openActivity);
                finish();
            }
        });

        //Avatar
        /*Statement statement;
        ResultSet rs = statement.executeQuery("SELECT PRF_PIC FROM usr WHERE ");
        InputStream ISAvatar;
        Bitmap imgBM_Avatar = BitmapFactory.decodeStream(ISAvatar);


        ImageView avatar = findViewById(R.id.avatar);
        avatar.setImageBitmap(imgBM_Avatar);
*/

        //Nom Prenom

        TextView nom_prenom = findViewById(R.id.NOMPrenom);
        nom_prenom.setText("NOM Prenom");

        String url = "jdbc:mysql://miroredge.freeboxos.fr:49999/ufr_asso";
        String s = "";
        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

            String a = sharedPref.getString("EML", "");
            Connection connect = DriverManager.getConnection(url, "ROOT", "root");
            PreparedStatement statement = connect.prepareStatement("SELECT PSD, PRF_PIC FROM usr WHERE EML = ?");
            statement.setString(1, a);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                nom_prenom.setText(resultSet.getString(1));


                Blob imageBlob = resultSet.getBlob("PRF_PIC");
                byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, options);
                ImageView imageView = (ImageView) findViewById(R.id.avatar);
                imageView.setImageBitmap(imageBitmap);

                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // bouton edit
        ImageButton boutonEdit = (ImageButton) findViewById(R.id.editBouton);
        boutonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), editProfil.class);
                startActivity(openActivity);
            }
        });

// ---------------bouton discord ------------------
                Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.com/"));
                startActivity(intent);
            }
        });

        // ---------------bouton MDP ------------------
        this.create = findViewById(R.id.buttonMDP);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), ChgtMDP.class);
                startActivity(openActivity);
            }
        });
    }
}