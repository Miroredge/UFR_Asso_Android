package fr.thomas.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Profile extends AppCompatActivity {

    private View acceuil;
    private View associations;
    private View profile;
    private TextView link;

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
                Intent openActivity = new Intent(getApplicationContext(), Profile.class);
                startActivity(openActivity);
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
        nom_prenom.setText("ygjhvb");

        String url = "jdbc:mysql://localost:3306/ufr_asso";//?allowPublicKeyRetrieval=true&useSSL=false";
        String s = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://astenor.freeboxos.fr:32800/ufr_asso?allowPublicKeyRetrieval=true&useSSL=false", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT GDR FROM usr WHERE EML = 'aureane.user@gmail.com'");
            while (resultSet.next()) {
                nom_prenom.setText(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // bouton edit
        ImageButton boutonEdit = findViewById(R.id.editBouton);
        boutonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openActivity = new Intent(getApplicationContext(), editProfil.class);
                startActivity(openActivity);
            }
        });

// dans votre classe Java
                Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ouvre le lien internet dans le navigateur par défaut
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.com/"));
                startActivity(intent);
            }
        });
    }
}