package com.bijay.smartcity_backend.Homescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bijay.smartcity_backend.Homepage.homepage;
import com.bijay.smartcity_backend.R;

public class MainActivity extends AppCompatActivity {
    private Button msignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msignin=findViewById(R.id.signin);

        msignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,homepage.class);
                startActivity(intent);
            }
        });
    }
}
