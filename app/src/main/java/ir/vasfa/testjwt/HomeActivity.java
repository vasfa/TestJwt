package ir.vasfa.testjwt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        activity = this;

        Button exo = findViewById(R.id.exo1);
        exo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainActivity.class);
                intent.putExtra("type", "exo");
                intent.putExtra("link", "http://api.bepors.me/voice/919c68c5-5820-4a03-933f-d531ae575256");
                activity.startActivity(intent);
            }
        });
        Button exo2 = findViewById(R.id.exo2);
        exo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainActivity.class);
                intent.putExtra("type", "exo");
                intent.putExtra("link", "https://hw4.cdn.asset.aparat.com/aparat-video/bd333f4470d4a97f16d00918db6df36316945220-144p__64646.apt?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6IjQxOTZlZDA4ODFiNzM5ODlkNzZlOGE3Y2M5ZDkyZmJiIiwiZXhwIjoxNTY4NTc4ODk4LCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.K_Ur3nR-QCctwL45REbOVdMri4IY3Dhne-4EECkTonk");
                activity.startActivity(intent);
            }
        });

        Button jw = findViewById(R.id.jw1);
        jw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainActivity.class);
                intent.putExtra("type", "jw");
                intent.putExtra("link", "http://api.bepors.me/voice/919c68c5-5820-4a03-933f-d531ae575256");
                activity.startActivity(intent);
            }
        });
        Button jw2 = findViewById(R.id.jw2);
        jw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainActivity.class);
                intent.putExtra("type", "jw");
                intent.putExtra("link", "https://hw4.cdn.asset.aparat.com/aparat-video/bd333f4470d4a97f16d00918db6df36316945220-144p__64646.apt?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6IjQxOTZlZDA4ODFiNzM5ODlkNzZlOGE3Y2M5ZDkyZmJiIiwiZXhwIjoxNTY4NTc4ODk4LCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.K_Ur3nR-QCctwL45REbOVdMri4IY3Dhne-4EECkTonk");
                activity.startActivity(intent);
            }
        });
    }
}
